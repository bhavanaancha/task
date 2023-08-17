package com.epam.associatemanagementapp.service;

import java.util.List;

import com.epam.associatemanagementapp.dtos.AssociateDTO;

public interface AssociateService {
	
	AssociateDTO addAssociate(AssociateDTO associateDto);
	void deleteAssociate(int id);
	AssociateDTO updateAssociate(AssociateDTO associateDto);
	List<AssociateDTO > getAssociatesByGender(String gender);

}
