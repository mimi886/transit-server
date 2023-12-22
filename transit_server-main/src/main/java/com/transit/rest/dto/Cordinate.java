package com.transit.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cordinate {
    @JsonProperty("lat")
    private float lat;

    @JsonProperty("lng")
    private float lng;
}
