package com.jrp.pma.entities;

import java.util.ArrayList;
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
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;



@Getter
@Setter
@Entity
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@EqualsAndHashCode(of= {"projectId","name"})
@ToString(of = {"projectId","name","stage","description"})
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="project_seq")
	long projectId;
	
	String name;
	String stage;
	String description;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
			fetch = FetchType.LAZY)
	@JoinTable(name="project_employee", 
	joinColumns=@JoinColumn(name="project_id"), inverseJoinColumns = @JoinColumn(name="employee_id"))	
	List<Employee> employees; 
	
	public Project(String name, String stage, String description) {
		super();
		
		this.name = name;
		this.stage = stage;
		this.description = description;
	}
	 
	// convenience method:
	public void addEmployee(Employee emp) {
		if(employees==null) {
			employees = new ArrayList<>();
		}
		employees.add(emp);
	}
	 
}
