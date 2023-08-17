package com.epam.associatemanagementapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.associatemanagementapp.dtos.AssociateDTO;
import com.epam.associatemanagementapp.service.AssociateServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/associates")
@Slf4j
public class AssociateController {
	@Autowired
	private AssociateServiceImpl associateService;

	@GetMapping("/{gender}")
	ResponseEntity<List<AssociateDTO>> getAllAssociates(@PathVariable String gender){
		log.info("executing getMapping ");
		return new ResponseEntity<>(associateService.getAssociatesByGender(gender),HttpStatus.OK);
	}
	@PostMapping()
	ResponseEntity<AssociateDTO> addBatch(@RequestBody @Valid AssociateDTO associateDto){
		log.info("executing postMapping ");
		return new ResponseEntity<>(associateService.addAssociate(associateDto),HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteAssociate(@PathVariable int id) {
		log.info("executing deleteMapping ");
		associateService.deleteAssociate(id);
	}
	@PutMapping()
	ResponseEntity<AssociateDTO> updateBatch(@RequestBody @Valid AssociateDTO associateDto){
		log.info("executing putMapping ");
		return new ResponseEntity<>(associateService.updateAssociate(associateDto),HttpStatus.OK);
	}

}
