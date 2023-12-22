package com.transit.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.transit.rest.model.Status;
import com.transit.rest.model.VecType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {
    private String regnum;
    private VecType type;
    private Status status;
    private String make;
    private String model;
    private String color;
    private int capacity;
    private String email;
    private String licence;
}
