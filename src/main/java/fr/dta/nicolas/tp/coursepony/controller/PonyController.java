package fr.dta.nicolas.tp.coursepony.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dta.nicolas.tp.coursepony.exeption.ResourceNotFoundException;
import fr.dta.nicolas.tp.coursepony.model.Pony;
import fr.dta.nicolas.tp.coursepony.dao.PonyDAO;

@RestController
@RequestMapping("/CoursePony/Ponies")
public class PonyController {

	@Autowired
	PonyDAO ponyDAO;
	
	@GetMapping("/")
	public List<Pony> getAll() {
		return ponyDAO.findAll();
	}
	
	@GetMapping("/{id}")
	public Pony getById(@PathVariable( "id" ) @Valid Long id ) {
		Optional<Pony> oPony = ponyDAO.findById(id);
		
		if (oPony.isPresent()) {
			return oPony.get();
		}
		throw new ResourceNotFoundException( "Pony not found" );
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable( "id" ) @Valid Long id ) {
		ponyDAO.deleteById(id);
	}
	
	
	@PostMapping("/")  
	public Pony save(@RequestBody @Valid Pony pony ) {
		return ponyDAO.save(pony);
	}
	
	@PutMapping("/{id}")
	public @Valid Pony update(@PathVariable(value = "id") Long ponyId, @RequestBody @Valid Pony pony) {
		
		Optional<Pony> ponyBD = ponyDAO.findById(ponyId);
		if (ponyBD.isPresent()) {
			ponyBD.get().setName( pony.getName() );
			ponyBD.get().setColor( pony.getColor() );
			ponyBD.get().setAge( pony.getAge() );
			ponyBD.get().setWeight( pony.getWeight() );
			
			Pony ponyUp = ponyDAO.save(ponyBD.get());
			return ponyUp;
			
		}
		throw new ResourceNotFoundException( "Course not found" );	
	}
	
	
	
	
}
