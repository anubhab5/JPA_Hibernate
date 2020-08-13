package com.example.jpahibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpahibernate.model.Course;
import com.example.jpahibernate.repository.CourseRepo;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	CourseRepo cRepo;

	@GetMapping("/getCourseById/{id}")
	public Course findCourseById(@PathVariable int id) {
		return cRepo.getCourseById(id);
	}

	@PostMapping("/saveCourse")
	public Course saveCourse(@RequestBody Course c) {
		return cRepo.saveCourse(c);
	}
	
	@GetMapping("/getAllCourses")
	public List<Course> getAllCourses(){
		return cRepo.getAllCourses();
	}
	
	@GetMapping("/getAllCoursesByTypedQuery")
	public List<Course> getAllCoursesByTypedQuery(){
		return cRepo.getAllCoursesByTypedQuery();
	}
	
	@GetMapping("/getAllCoursesByWhere")
	public List<Course> getAllCoursesByWhere(@RequestParam(name="courseName") String name,
			@RequestParam(name="price") double price){
		return cRepo.getAllCoursesWhereQuery(name, price);
	}
	
	@GetMapping("/getAllCoursesBetween")
	public List<Course> getAllCoursesBetween(@RequestParam(name="lowerBound") double lowerBound,
			@RequestParam(name="upperBound") double upperBound){
		return cRepo.getAllCoursesWhereQuery2(lowerBound, upperBound);
	}
	
	@GetMapping("/getAllCoursesNamedquery")
	public List<Course> getAllCourseUsingNamedQuery() {
		return cRepo.getAllCoursesUsingNamedQuery();
	}
	
	@GetMapping("/getAllCoursesNativequery")
	public List<Course> getAllCourse_NativeQuery() {
		return cRepo.getAllCourses_Nativequery();
	}
}
