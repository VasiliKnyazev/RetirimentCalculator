package com.luxoft.rcalculator.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetirementResultDTO {
    private Integer userAge;
    private Integer presentYear;
    private Integer canRetireYear;
    private String canRetireMessage;
}
