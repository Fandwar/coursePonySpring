package fr.dta.nicolas.tp.coursepony.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dta.nicolas.tp.coursepony.model.Pony;

@Repository
public interface PonyDAO extends JpaRepository<Pony, Long> {}