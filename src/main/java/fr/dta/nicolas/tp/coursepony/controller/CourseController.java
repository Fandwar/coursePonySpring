package fr.dta.nicolas.tp.coursepony.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dta.nicolas.tp.coursepony.dao.CourseDAO;
import fr.dta.nicolas.tp.coursepony.exeption.ResourceNotFoundException;
import fr.dta.nicolas.tp.coursepony.model.Course;

@RestController
@RequestMapping("/CoursePony/Courses")
public class CourseController {

	@Autowired
	CourseDAO courseDAO;
	
	@GetMapping("/")
	public List<Course> getAll() {
		return courseDAO.findAll();
	}
	
	@GetMapping("/{id}")
	public @Valid Course getById(@PathVariable( "id" ) @Valid Long id ) {
		Optional<Course> oCourse = courseDAO.findById(id);
		
		if (oCourse.isPresent()) {
			return oCourse.get();
		}
		throw new ResourceNotFoundException( "Course not found" );
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable( "id" ) @Valid Long id ) {
		courseDAO.deleteById(id);
	}
	
	
	@PostMapping("/")
	public @Valid Course save(@RequestBody @Valid Course course ) {
		return courseDAO.save(course);
	}
	
	
	
	
	
	
}
