package com.transit.rest.dto;

import java.util.Date;
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
public class VehicleResponse {
   private long id;
    private String regnum;
    private VecType type;
    private Status status;
    private String make;
    private String model;
    private String color;
    private Date createdat;
    private int capacity;  
}
