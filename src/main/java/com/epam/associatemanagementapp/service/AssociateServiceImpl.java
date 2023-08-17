package com.epam.associatemanagementapp.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.associatemanagementapp.dtos.AssociateDTO;
import com.epam.associatemanagementapp.entites.Associate;
import com.epam.associatemanagementapp.entites.Batch;
import com.epam.associatemanagementapp.exceptions.AssociateException;
import com.epam.associatemanagementapp.repository.AssociateRepo;
import com.epam.associatemanagementapp.repository.BatchRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AssociateServiceImpl implements AssociateService{
	
	@Autowired
	AssociateRepo associateRepo;
	@Autowired
	BatchRepo batchRepo;
	@Autowired
	ModelMapper mapper;

	@Override
	public AssociateDTO addAssociate(AssociateDTO associateDto) {
		log.info("entering addAssociate method");
		Optional<Batch> batch=batchRepo.findById(associateDto.getBatch().getId());
		Associate associate=mapper.map(associateDto, Associate.class);
		if(batch.isPresent()) {
			associateRepo.save(associate);	
		}
		else {
			batchRepo.save(mapper.map(associateDto.getBatch(),Batch.class));
			associateRepo.save(associate);
		}
		log.info("exiting addAssociate method");
		return associateDto;
	}

	@Override
	public void deleteAssociate(int id) {
		log.info("entering deleteAssociate()");
		associateRepo.deleteById(id);
		log.info("exiting deleteAssociate()");
	}

	@Override
	public AssociateDTO updateAssociate(AssociateDTO associateDto) {
		log.info("entering updateAssociate()");
		if(associateRepo.existsById(associateDto.getId())) {
		 associateRepo.save(mapper.map(associateDto, Associate.class));
		 log.info("successfully modified");
		return associateDto;
		}
		else {
			log.info("Exception thorwn: associate with this id is not present");
			throw(new AssociateException("associate with this id is not present"));
		}
	}


	@Override
	public List<AssociateDTO> getAssociatesByGender(String gender) {
		log.info("fetching getAssociatesByGender");
		return associateRepo.findAllByGender(gender).stream().map(associate->mapper.map(associate,AssociateDTO.class)).toList();
	}

}
