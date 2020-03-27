package com.jrp.pma.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString(of={"employeeId","firstName","lastName","email"})
@EqualsAndHashCode(of={"employeeId","firstName","lastName","email"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long employeeId;
	
	String firstName;
	String lastName;
	String email;
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	Project theProject;
	
	public Employee(String firstName, String lastName, String email) {
		
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	
	}
	
	
}
