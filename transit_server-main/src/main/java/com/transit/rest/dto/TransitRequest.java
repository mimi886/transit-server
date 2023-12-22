package com.transit.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransitRequest {
    
    private String regnum;
    private String startlocationlat;
    private String startlocationlng;
    private String destlocationlat;
    private String destlocationlng;

}
