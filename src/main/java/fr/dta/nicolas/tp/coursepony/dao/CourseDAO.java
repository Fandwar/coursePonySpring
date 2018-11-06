package fr.dta.nicolas.tp.coursepony.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import fr.dta.nicolas.tp.coursepony.model.Course;

@Repository
public interface CourseDAO extends JpaRepository<Course, Long> {}
