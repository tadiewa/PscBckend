/**
 * @author : tadiewa
 * date: 8/15/2025
 */


package com.zw.travelling.model;

import com.zw.travelling.constants.AllowanceType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rate {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private AllowanceType type;
    private Integer breakfast;
    private Integer lunch;
    private Integer dinner;
    private Integer accommodation;
    private Integer approvedDailyTotal;
}