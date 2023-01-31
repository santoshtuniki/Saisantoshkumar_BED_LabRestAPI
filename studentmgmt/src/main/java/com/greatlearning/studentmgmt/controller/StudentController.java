package com.greatlearning.studentmgmt.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.studentmgmt.model.Student;
import com.greatlearning.studentmgmt.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@RequestMapping("/list")
	public String viewAllStudents(Model model) {
		List<Student> students = studentService.getAllStudents();
		model.addAttribute("Students", students);
		return "list-Students";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Student student = new Student();
		model.addAttribute("Student", student);
		return "Student-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(Model model, @RequestParam(name = "studentId") int id) {
		Student student = studentService.getStudentById(id);
		model.addAttribute("Student", student);
		return "Student-form";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam(name = "studentId") int id) {
		studentService.deleteByStudentId(id);
		return "redirect:/student/list";
	}

	@RequestMapping("/save")
	public String save(@RequestParam(name = "id") int id, @RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "lastName") String lastName, @RequestParam(name = "course") String course,
			@RequestParam(name = "country") String country) {
		Student student;
		if (id == 0) {
			student = new Student();
		} else {
			student = studentService.getStudentById(id);
		}
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setCourse(course);
		student.setCountry(country);
		studentService.addOrUpdate(student);
		return "redirect:/student/list";
	}

	@RequestMapping("/403")
	public ModelAndView accesssDenied(Principal user) {
		ModelAndView model = new ModelAndView();
		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", "You do not have permission to access this page!");
		}
		model.setViewName("403");
		return model;
	}
}
