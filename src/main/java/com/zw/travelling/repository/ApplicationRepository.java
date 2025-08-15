/**
 * @author : tadiewa
 * date: 8/15/2025
 */

package com.zw.travelling.repository;

import com.zw.travelling.constants.ApplicationStatus;
import com.zw.travelling.model.Applicationz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ApplicationRepository extends JpaRepository<Applicationz, Long> {
    List<Applicationz> findByEmployeeIdOrderByApplicationIdDesc(Long employeeId);
    List<Applicationz> findByStatusOrderByApplicationIdAsc(ApplicationStatus status);
}
