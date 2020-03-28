package com.jrp.pma.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="employee_seq")
	long employeeId;
	
	String firstName;
	String lastName;
	String email;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
			fetch = FetchType.LAZY)
	@JoinTable(name="project_employee", 
	joinColumns=@JoinColumn(name="employee_id"), inverseJoinColumns = @JoinColumn(name="project_id"))
	List<Project> projects;
	
	public Employee(String firstName, String lastName, String email) {
		
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	
	}
	
	
}
