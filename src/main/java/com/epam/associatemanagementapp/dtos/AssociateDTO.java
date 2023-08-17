package com.epam.associatemanagementapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssociateDTO {
	private int id;
	@NotBlank(message="name cannot be blank")
	private String name;
	@NotBlank(message="email cannot be blank")
	private String email;
	@Pattern(regexp = "[MF]")
	private String gender;
	private String college;
	@NotBlank(message="status is mandatory")
	private String status;
	private BatchDTO batch;

}
