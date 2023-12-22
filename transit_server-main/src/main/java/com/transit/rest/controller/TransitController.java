package com.transit.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.transit.rest.dto.TransitRequest;
import com.transit.rest.dto.TransitResponse;
import com.transit.rest.service.VehicleService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/transit")
@RequiredArgsConstructor
public class TransitController {

    private final VehicleService vehicleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransit(@RequestBody TransitRequest request) {
       vehicleService.createTransit(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TransitResponse> getAllTransits() {
        return vehicleService.getAllTransits();
    }
    
    
    
}
