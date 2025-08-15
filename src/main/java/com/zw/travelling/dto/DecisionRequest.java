/**
 * @author : tadiewa
 * date: 8/15/2025
 */


package com.zw.travelling.dto;

import jakarta.validation.constraints.NotBlank;

public record DecisionRequest(
        @NotBlank
        String decision,
        String comments
) {}