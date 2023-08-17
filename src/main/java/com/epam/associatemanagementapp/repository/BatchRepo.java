package com.epam.associatemanagementapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.associatemanagementapp.entites.Batch;

public interface BatchRepo extends JpaRepository<Batch, Integer>{
//	Optional<Batch> batch=findByBatchName();
}
