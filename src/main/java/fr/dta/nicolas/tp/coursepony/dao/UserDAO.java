package fr.dta.nicolas.tp.coursepony.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dta.nicolas.tp.coursepony.model.User;

public interface UserDAO extends JpaRepository<User, Long>{}
