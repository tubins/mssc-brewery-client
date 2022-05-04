package com.tubz.msscbreweryclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Customer DTO class.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private UUID id;
    private String customerName;
}
