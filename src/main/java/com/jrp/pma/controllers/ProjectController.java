package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping
	public String displayProjects(Model model) {
		List<Project> projects = projectRepository.findAll();
		model.addAttribute("projects", projects);
		return "projects/list-projects";
	}
	
	@GetMapping("/new")
	public String displayProjectFomr(Model model) {
		
		Project aProject = new Project();
		
		
		List<Employee> allEmployees = employeeRepository.findAll();
		
		model.addAttribute("allEmployees",allEmployees);
		model.addAttribute("project",aProject);
		
		return "projects/new-project";
	}
	
	
	@PostMapping("/save")
	public String createProject(Project project, Model model) {
		
		//Iterable<Employee> listEmployees = employeeRepository.findAllById(employees);
		projectRepository.save(project);
		/*listEmployees.forEach(entity -> {
			entity.setProject(project);
			employeeRepository.save(entity);
		});*/
		
		return "redirect:/projects";
	}
}
