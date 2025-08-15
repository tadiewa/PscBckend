/**
 * @author : tadiewa
 * date: 8/15/2025
 */


package com.zw.travelling.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zw.travelling.constants.AllowanceType;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateApplicationRequest(
        @NotNull Long employeeId,
        @NotNull @JsonFormat(pattern = "yyyy-MM-dd") LocalDate departureDate,
        @NotNull @JsonFormat(pattern = "HH:mm:ss") LocalTime departureTime,
        @NotBlank String departureCity,
        @NotNull @JsonFormat(pattern = "yyyy-MM-dd") LocalDate arrivalDate,
        @NotNull @JsonFormat(pattern = "HH:mm:ss") LocalTime arrivalTime,
        @NotBlank String arrivalCity,
        @NotNull AllowanceType allowanceType
) {}
