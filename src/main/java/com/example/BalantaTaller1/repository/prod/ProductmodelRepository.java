package com.example.BalantaTaller1.repository.prod;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BalantaTaller1.model.prod.Productmodel;

@Repository
public interface ProductmodelRepository extends JpaRepository<Productmodel, Integer>{

}
