package com.epam.associatemanagementapp.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.associatemanagementapp.dtos.AssociateDTO;
import com.epam.associatemanagementapp.dtos.BatchDTO;
import com.epam.associatemanagementapp.entites.Associate;
import com.epam.associatemanagementapp.entites.Batch;
import com.epam.associatemanagementapp.exceptions.AssociateException;
import com.epam.associatemanagementapp.repository.AssociateRepo;
import com.epam.associatemanagementapp.repository.BatchRepo;
import com.epam.associatemanagementapp.service.AssociateServiceImpl;

@ExtendWith(MockitoExtension.class)
 class AssociateServiceTest {
	@Mock
	private AssociateRepo associateRepo;
	@InjectMocks
	private AssociateServiceImpl associateService;
	@Mock
	private ModelMapper mapper;
	@Mock
	private BatchRepo batchRepo;
	private Associate associate;
	private Batch batch;
	private AssociateDTO associateDto;
	private BatchDTO batchDto;
	
	@BeforeEach()
	void setUp() {
		batch=new Batch();
		 batchDto=new BatchDTO(1,"RD","Java",new Date(2023-05-26),new Date(2023-05-26));
		 associate=new Associate(1,"bhavana","bhavana@gmail.com","M","RVR&JC","half done",batch);
		 associateDto=new AssociateDTO(1,"bhavana","bhavana@gmail.com","M","RVR&JC","half done",batchDto);
	}
	
	@Test
	void testGetAllAssociates() {
		Mockito.when(associateRepo.findAllByGender("F")).thenReturn(List.of(associate));
		Mockito.when(mapper.map(associate, AssociateDTO.class)).thenReturn(associateDto);
		assertEquals(List.of(associateDto),associateService.getAssociatesByGender("F"));
	}
	@Test
	void testAddAssociate() {
		Mockito.when(batchRepo.findById(1)).thenReturn(Optional.of(batch));
		Mockito.when(mapper.map(associateDto, Associate.class)).thenReturn(associate);
		Mockito.when(associateRepo.save(associate)).thenReturn(associate);
		assertEquals(associateDto,associateService.addAssociate(associateDto));
	}
	@Test
	void testAddAssociateToNonExistingBatch() {
		Mockito.when(batchRepo.findById(1)).thenReturn(Optional.empty());
		Mockito.when(mapper.map(associateDto, Associate.class)).thenReturn(associate);
		Mockito.when(mapper.map(batchDto, Batch.class)).thenReturn(batch);
		Mockito.when(batchRepo.save(batch)).thenReturn(batch);
		Mockito.when(associateRepo.save(associate)).thenReturn(associate);
		assertEquals(associateDto,associateService.addAssociate(associateDto));
		
	}
	@Test
	void testDeleteAssociate() {
		Mockito.doNothing().when(associateRepo).deleteById(1);
		associateService.deleteAssociate(1);
	}
	@Test
	void testUpdateAssociate() {
		Mockito.when(associateRepo.existsById(1)).thenReturn(true);
		Mockito.when(mapper.map(associateDto, Associate.class)).thenReturn(associate);
		Mockito.when(associateRepo.save(associate)).thenReturn(associate);
		assertEquals(associateDto,associateService.updateAssociate(associateDto));
	}
	@Test
	void testGetters() {
		Associate dummyAssociate=new Associate();
		Batch batch=new Batch(1,"RD","Java",new Date(2023-6-21),new Date(2023-7-13),List.of(associate));
		dummyAssociate.setBatch(batch);
		dummyAssociate.setId(1);
		dummyAssociate.setCollege("RVR");
		dummyAssociate.setEmail("bhavana@gmail.com");
		dummyAssociate.setGender("F");
		dummyAssociate.setStatus("partial");
		dummyAssociate.setName("bhavana");
		AssociateDTO dummyAssociateDto=new AssociateDTO();
		dummyAssociateDto.setBatch(batchDto);
		dummyAssociateDto.setCollege("RVR");
		dummyAssociateDto.setEmail("bhavana@gmail.com");
		dummyAssociateDto.setGender("F");
		dummyAssociateDto.setStatus("partial");
		dummyAssociateDto.setName("bhavana");
		Batch dummyBatch=new Batch();
		dummyBatch.setAssociates(List.of(associate));
		dummyBatch.setEndDate(new Date(2023-9-21));
		dummyBatch.setStartDate(new Date(2023-6-11));
		dummyBatch.setId(1);
		dummyBatch.setName("RD");
		dummyBatch.setPractice("Java");
		assertEquals(List.of(associate),dummyBatch.getAssociates());
		assertEquals(new Date(2023-9-21),dummyBatch.getEndDate());
		assertEquals(new Date(2023-6-11),dummyBatch.getStartDate());
		assertEquals("RD",dummyBatch.getName());
		assertEquals("Java",dummyBatch.getPractice());
		assertEquals(1,dummyBatch.getId());
		assertEquals(batch,dummyAssociate.getBatch());
		assertEquals("bhavana@gmail.com", dummyAssociate.getEmail());
		assertEquals("RVR",dummyAssociate.getCollege());
		assertEquals("F",dummyAssociate.getGender());
		assertEquals("partial",dummyAssociate.getStatus());
		assertEquals("bhavana",dummyAssociate.getName());
		assertEquals(1,dummyAssociate.getId());
		assertEquals(batchDto,dummyAssociateDto.getBatch());
		assertEquals("bhavana@gmail.com", dummyAssociateDto.getEmail());
		assertEquals("RVR",dummyAssociateDto.getCollege());
		assertEquals("F",dummyAssociateDto.getGender());
		assertEquals("partial",dummyAssociateDto.getStatus());
		assertEquals("bhavana",dummyAssociateDto.getName());
	}
	@Test
	void testUpdateInvalidAssociate() {
		Mockito.when(associateRepo.existsById(1)).thenReturn(false);
		assertThrows(AssociateException.class,()->associateService.updateAssociate(associateDto));
	}
}
