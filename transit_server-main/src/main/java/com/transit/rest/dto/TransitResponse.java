package com.transit.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.transit.rest.model.VecType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransitResponse {

    @JsonProperty("id")
    private Long id;

   @JsonProperty("regnum")
    private String regnum;

    @JsonProperty("passengers")
    private int passengers;

   @JsonProperty("capacity")
    private int capacity;

    @JsonProperty("status")
    private Boolean status;
    @JsonProperty("completed")
    private Boolean completed;
    @JsonProperty("intransit")
    private Boolean intransit;
    
    @JsonProperty("startlocation")
    private Cordinate startlocation;

    @JsonProperty("currentlocation")
    private Cordinate currentlocation;

    @JsonProperty("destlocation")
    private Cordinate destlocation;

    @JsonProperty("type")
    private VecType type;

    @JsonProperty("model")
    private String model;

    @JsonProperty("make")
    private String make;

    @JsonProperty("color")
    private String color;

    @JsonProperty("driver")
    private String driver;

    
}
