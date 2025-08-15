/**
 * @author : tadiewa
 * date: 8/15/2025
 */


package com.zw.travelling.service;

import com.zw.travelling.constants.AllowanceType;
import com.zw.travelling.constants.ApplicationStatus;
import com.zw.travelling.dto.CreateApplicationRequest;
import com.zw.travelling.model.Applicationz;
import com.zw.travelling.model.Rate;
import com.zw.travelling.repository.ApplicationRepository;
import com.zw.travelling.repository.RateRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Service
public class ServiceIntImpl  {
    private final ApplicationRepository appRepo;

    private final RateRepository rateRepo;

    public ServiceIntImpl(ApplicationRepository appRepo, RateRepository rateRepo) {
        this.appRepo = appRepo; this.rateRepo = rateRepo;
    }

    private double calculateAmount(CreateApplicationRequest r) {
        long days = ChronoUnit.DAYS.between(r.departureDate(), r.arrivalDate()) + 1; // inclusive
        if (days <= 0) {
            throw new IllegalArgumentException("Arrival date must be on/after departure date");
        }

        Rate rate = rateRepo.findByType(r.allowanceType())
                .orElseThrow(() -> new IllegalArgumentException("Rate not found for type"));

        if (r.allowanceType() == AllowanceType.APPROVED) {
            return days * coalesce(rate.getApprovedDailyTotal());
        } else {
            int daily = coalesce(rate.getBreakfast())
                    + coalesce(rate.getLunch())
                    + coalesce(rate.getDinner())
                    + coalesce(rate.getAccommodation());
            return days * daily;
        }
    }

    private int coalesce(Integer value) {
        return value == null ? 0 : value;
    }





    @Transactional
    public Applicationz create(CreateApplicationRequest r) {
        double amount = calculateAmount(r);
        Applicationz app = Applicationz.builder()
                .employeeId(r.employeeId())
                .departureDate(r.departureDate())
                .departureTime(r.departureTime())
                .departureCity(r.departureCity())
                .arrivalDate(r.arrivalDate())
                .arrivalTime(r.arrivalTime())
                .arrivalCity(r.arrivalCity())
                .allowanceType(r.allowanceType())
                .tsAmount(amount)
                .status(ApplicationStatus.PENDING)
                .build();
        return appRepo.save(app);
    }

    public List<Applicationz> listForEmployee(Long employeeId) {
        return appRepo.findByEmployeeIdOrderByApplicationIdDesc(employeeId);
    }

    public List<Applicationz> listPending() {
        return appRepo.findByStatusOrderByApplicationIdAsc(ApplicationStatus.PENDING);
    }

    @Transactional
    public Applicationz decide(Long id, String decision, String comments) {
        log.info("Deciding application {} with decision: {}, comments: {}", id, decision, comments);
        Applicationz app = appRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));

        if ("APPROVED".equalsIgnoreCase(decision)) {
            app.setStatus(ApplicationStatus.APPROVED);
            app.setSupervisorComments(null);
        } else if ("REJECT".equalsIgnoreCase(decision)) {
            app.setStatus(ApplicationStatus.REJECTED);
            app.setSupervisorComments(comments);
        } else {
            throw new IllegalArgumentException("Decision must be APPROVED or REJECT");
        }
        return appRepo.save(app);
    }
}