package com.epam.associatemanagementapp.controllertest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.associatemanagementapp.controller.AssociateController;
import com.epam.associatemanagementapp.dtos.AssociateDTO;
import com.epam.associatemanagementapp.dtos.BatchDTO;
import com.epam.associatemanagementapp.service.AssociateServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AssociateController.class)
 class AssociateControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private AssociateServiceImpl associateService;
	
	private AssociateDTO associateDto;
	private BatchDTO batchDto;
	
	@BeforeEach()
	void setUp() {
		 batchDto=new BatchDTO(1,"RD","Java",new Date(2023-05-26),new Date(2023-05-26));
		 associateDto=new AssociateDTO(1,"bhavana","bhavana@gmail.com","M","RVR&JC","half done",batchDto);
	}
	
	@Test
	void testGetAllAssociates() throws Exception {
		Mockito.when(associateService.getAssociatesByGender("F")).thenReturn(List.of(associateDto));
		mockMvc.perform(get("/associates/F")).andExpect(status().isOk());
	}
	@Test
	void testAddAssociate() throws Exception {
		Mockito.when(associateService.addAssociate(associateDto)).thenReturn(associateDto);
		mockMvc.perform(post("/associates").contentType(MediaType.APPLICATION_JSON)
		.content(new ObjectMapper().writeValueAsString(associateDto))).andExpect(status().isOk());
	}
	@Test
	void testDeleteAssociate() throws Exception{
		Mockito.doNothing().when(associateService).deleteAssociate(1);
		mockMvc.perform(delete("/associates/1")).andExpect(status().isNoContent());
	}
	@Test
	void testUpdateAssociate() throws Exception{
		Mockito.when(associateService.updateAssociate(associateDto)).thenReturn(associateDto);
		mockMvc.perform(put("/associates").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(associateDto))).andExpect(status().isOk());
	}

	
	@Test
	void testValidationFailureCase() throws Exception{
		AssociateDTO dummy=new AssociateDTO();
		Mockito.when(associateService.addAssociate(dummy)).thenReturn(dummy);
		mockMvc.perform(post("/associates").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(dummy))).andExpect(status().isBadRequest());
	}
	@Test
	void testRunTimeExceptionCase() throws Exception{
		Mockito.doNothing().when(associateService).deleteAssociate(2);
		mockMvc.perform(delete("/associates/f")).andExpect(status().isInternalServerError());
	}
	
}
