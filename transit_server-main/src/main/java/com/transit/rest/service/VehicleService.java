package com.transit.rest.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.transit.rest.dto.TransitResponse;
import com.transit.rest.dto.Cordinate;
import com.transit.rest.dto.TransitRequest;
import com.transit.rest.dto.VehicleRequest;
import com.transit.rest.dto.VehicleResponse;
import com.transit.rest.model.Transit;
import com.transit.rest.model.User;
import com.transit.rest.model.Vehicle;
import com.transit.rest.repository.TransitRepository;
import com.transit.rest.repository.UserRepository;
import com.transit.rest.repository.VehicleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    private final UserRepository userRepository;

    private final TransitRepository transitRepository;

    public void createVehicle(VehicleRequest request) {
        
       Optional<User> optUser= userRepository.findByEmail(request.getEmail());

       if(optUser.isPresent()){
            User user = optUser.get();
            Vehicle vehicle=Vehicle.builder()
                            .regnum(request.getRegnum())
                            .type(request.getType())
                            .status(request.getStatus())
                            .make(request.getMake())
                            .model(request.getModel())
                            .createdat(new Date())
                            .capacity(request.getCapacity())
                            .color(request.getColor())
                            .licence(request.getLicence())
                            .user(user)
                            .build();

            vehicleRepository.save(vehicle);
            log.info("Vehicle is saved. Registration Number:"+request.getRegnum());
       }

       

    }

    public List<VehicleResponse> getAllVehicles() {
        List<Vehicle> vehicles=vehicleRepository.findAll();
        return vehicles.stream().map(this::mapToVechileResponse).toList();
    }
    
    private VehicleResponse mapToVechileResponse(Vehicle vehicle){
        return VehicleResponse.builder()
                        .id(vehicle.getId())
                        .regnum(vehicle.getRegnum())
                        .type(vehicle.getType())
                        .status(vehicle.getStatus())
                        .make(vehicle.getMake())
                        .model(vehicle.getModel())
                        .createdat(vehicle.getCreatedat())
                        .capacity(vehicle.getCapacity())
                        .color(vehicle.getColor())
                        .build();
    }

    public void createTransit(TransitRequest request) {

        List<Vehicle> vehicles = vehicleRepository.findByRegnum(request.getRegnum());
        
        if(vehicles.size()>0){
            Vehicle vehicle = vehicles.get(0);

            List<Transit> transits = transitRepository.findByRegnum(request.getRegnum());

            if(transits.size()>0){
                Transit transit = transits.get(0);

                transit.setCurrentlocationlat(request.getStartlocationlat());
                transit.setCurrentlocationlng(request.getStartlocationlng());
                transit.setCompleted(true);
                transit.setStatus(true);
                transit.setIntransit(true);
                transit.setVehicle(vehicle);
                transitRepository.save(transit);
                log.info("Transit is updated. Registration Number:"+request.getRegnum());
            }else{
                
            var transit = Transit.builder()
                                .regnum(request.getRegnum())
                                .passengers(vehicle.getCapacity())
                                .status(true)
                                .completed(true)
                                .intransit(true)
                                .startlocationlat(request.getStartlocationlat())
                                .startlocationlng(request.getStartlocationlng())
                                .currentlocationlat(request.getStartlocationlat())
                                .currentlocationlng(request.getStartlocationlng())
                                .destlocationlat(request.getDestlocationlat())
                                .destlocationlng(request.getDestlocationlng())
                                .vehicle(vehicle)
                                .build();

                transitRepository.save(transit);
                log.info("Transit is started. Registration Number:"+request.getRegnum());
            }

        }
    }

    public List<TransitResponse> getAllTransits() {
       List<Transit> transits=transitRepository.findAll();
       return transits.stream().map(this::mapToTransitResponse).toList();
    }

    private TransitResponse mapToTransitResponse(Transit transit){

        return TransitResponse.builder()
                        .id(transit.getId())
                        .regnum(transit.getRegnum())
                        .passengers(transit.getPassengers())
                        .capacity(transit.getVehicle().getCapacity())
                        .status(transit.getStatus())
                        .completed(transit.getCompleted())
                        .intransit(transit.getIntransit())
                        .startlocation(getStartCordinate(transit.getStartlocationlat(),transit.getStartlocationlng()))
                        .currentlocation(getCurrentCordinate(transit.getCurrentlocationlat(), transit.getCurrentlocationlng()))
                        .destlocation(getDestCordinate(transit.getDestlocationlat(), transit.getDestlocationlng()))
                        .type(transit.getVehicle().getType())
                        .model(transit.getVehicle().getModel())
                        .make(transit.getVehicle().getMake())
                        .color(transit.getVehicle().getColor())
                        .driver(transit.getVehicle().getUser().getFullname())
                        .build();

    }

    private Cordinate getStartCordinate(String startlocationlat, String startlocationlng) {
        return Cordinate.builder()
                        .lat(Float.parseFloat(startlocationlat))
                        .lng(Float.parseFloat(startlocationlng)) 
                        .build();
    }

      private Cordinate getCurrentCordinate(String currentlocationlat, String currentlocationlng) {
        return Cordinate.builder()
                        .lat(Float.parseFloat(currentlocationlat))
                        .lng(Float.parseFloat(currentlocationlng)) 
                        .build();
    }

      private Cordinate getDestCordinate(String destlocationlat, String destlocationlng) {
        return Cordinate.builder()
                        .lat(Float.parseFloat(destlocationlat))
                        .lng(Float.parseFloat(destlocationlng)) 
                        .build();
    }

}
