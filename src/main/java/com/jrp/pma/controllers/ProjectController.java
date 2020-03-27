package com.jrp.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	ProjectRepository projectRepository;
	
	@GetMapping("/new")
	public String displayProjectFomr(Model model) {
		
		Project aProject = new Project();
		model.addAttribute("project",aProject);
		return "new-project";
	}
	
	
	@PostMapping("/save")
	public String createProject(Project project , Model model) {
		
		projectRepository.save(project);
		return "redirect:/projects/new";
	}
}
