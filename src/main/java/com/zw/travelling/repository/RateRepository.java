/**
 * @author : tadiewa
 * date: 8/15/2025
 */

package com.zw.travelling.repository;

import com.zw.travelling.constants.AllowanceType;
import com.zw.travelling.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    Optional<Rate> findByType(AllowanceType type);
}
