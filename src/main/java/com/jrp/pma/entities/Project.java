package com.jrp.pma.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level=AccessLevel.PRIVATE)
@EqualsAndHashCode(of= {"projectId","name"})
@ToString(of = {"projectId","name","stage","description"})
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long projectId;
	
	String name;
	String stage;
	String description;
	 
	public Project(String name, String stage, String description) {
		super();
		
		this.name = name;
		this.stage = stage;
		this.description = description;
	}
	 
	 
}
