package com.transit.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.transit.rest.dto.VehicleRequest;
import com.transit.rest.dto.VehicleResponse;
import com.transit.rest.service.VehicleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createVehicle(@RequestBody VehicleRequest request){
        vehicleService.createVehicle(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VehicleResponse> getAllVehicles(){
        return vehicleService.getAllVehicles();
    }
    
}
