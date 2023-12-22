package com.transit.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transit.rest.model.Vehicle;
import java.util.List;


public interface VehicleRepository extends JpaRepository<Vehicle,Long>{
    
    List<Vehicle> findByRegnum(String regnum);
}
