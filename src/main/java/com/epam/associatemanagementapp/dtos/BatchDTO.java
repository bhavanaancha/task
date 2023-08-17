package com.epam.associatemanagementapp.dtos;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchDTO {
	private int id;
	@NotBlank(message="name cannot be blank")
	private String name;
	@NotBlank(message="practice cannot be blank")
	private String practice;
	@NotBlank(message="Start date cannot be blank")
	private Date startDate;
	@NotBlank(message="end date cannot be blank")
	private Date endDate;
	

}
