package com.transit.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transit.rest.model.Transit;
import java.util.List;


public interface TransitRepository extends JpaRepository<Transit,Long>{
    
        List<Transit> findByRegnum(String regnum);
}
