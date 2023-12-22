package com.transit.rest.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="transit")
public class Transit {
    
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique=true)
    private String regnum; 
    private int passengers;
    private Boolean status;
    private Boolean completed;
    private Boolean intransit;
    private String startlocationlat;
    private String startlocationlng;
    private String currentlocationlat;
    private String currentlocationlng;
    private String destlocationlat;
    private String destlocationlng;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="vec_id",referencedColumnName = "id")
    private Vehicle vehicle;
    
}
