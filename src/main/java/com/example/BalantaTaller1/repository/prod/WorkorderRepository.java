package com.example.BalantaTaller1.repository.prod;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BalantaTaller1.model.prod.Workorder;

public interface WorkorderRepository extends JpaRepository<Workorder, Integer>{

}
