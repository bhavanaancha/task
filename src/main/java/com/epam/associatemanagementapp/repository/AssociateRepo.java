package com.epam.associatemanagementapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.associatemanagementapp.entites.Associate;

public interface AssociateRepo extends JpaRepository<Associate, Integer>{
	List<Associate> findAllByGender(String gender);
}
