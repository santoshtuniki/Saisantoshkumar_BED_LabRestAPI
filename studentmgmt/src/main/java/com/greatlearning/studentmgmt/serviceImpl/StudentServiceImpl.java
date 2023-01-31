package com.greatlearning.studentmgmt.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.studentmgmt.model.Student;
import com.greatlearning.studentmgmt.repository.StudentRepository;
import com.greatlearning.studentmgmt.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public void addOrUpdate(Student student) {
		studentRepository.save(student);
	}

	@Override
	public void deleteByStudentId(int id) {
		studentRepository.deleteById(id);
	}

	@Override
	public Student getStudentById(int id) {
		return studentRepository.findById(id).get();
	}
}
