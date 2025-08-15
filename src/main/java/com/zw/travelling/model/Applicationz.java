/**
 * @author : tadiewa
 * date: 8/15/2025
 */


package com.zw.travelling.model;

import com.zw.travelling.constants.AllowanceType;
import com.zw.travelling.constants.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Applicationz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    private Long employeeId;

    private LocalDate departureDate;
    private LocalTime departureTime;
    private String departureCity;

    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    private String arrivalCity;

    @Enumerated(EnumType.STRING)
    private AllowanceType allowanceType;

    private Double tsAmount; // calculated total

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @Column(columnDefinition = "TEXT")
    private String supervisorComments;
}
