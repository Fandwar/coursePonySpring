package fr.dta.nicolas.tp.coursepony.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dta.nicolas.tp.coursepony.dao.UserDAO;
import fr.dta.nicolas.tp.coursepony.exeption.ResourceNotFoundException;
import fr.dta.nicolas.tp.coursepony.model.User;


@RestController
@RequestMapping("/CoursePony/Users")
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
//	@Autowired
//	private JwtTokenProvider jwtTokenProvider;
	
	
	@CrossOrigin(origins = "*")
	@GetMapping("/")
	public List<User> getAll() {
		return userDAO.findAll();
	}
	
	@GetMapping("/{id}")
	public @Valid User getById(@PathVariable( "id" ) @Valid Long id ) {
		Optional<User> oUser = userDAO.findById(id);
		
		if (oUser.isPresent()) {
			return oUser.get();
		}
		throw new ResourceNotFoundException( "Course not found" );
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable( "id" ) @Valid Long id ) {
		userDAO.deleteById(id);
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/login")
	public @Valid User postUserLogin(@RequestBody @Valid User user, HttpServletRequest request ) throws NoSuchAlgorithmException, InvalidKeySpecException {
		//Query query = new Query();
		
		
		
		return userDAO.save(user);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/")
	public @Valid User save(@RequestBody @Valid User user ) {
		return userDAO.save(user);
	}
	
	@PutMapping("/{id}")
	public @Valid User update(@PathVariable(value = "id") Long userId, @RequestBody @Valid User user) {
		
		Optional<User> userFind = userDAO.findById(userId);
		if (userFind.isPresent()) {
			userFind.get().setName( user.getName() );
			userFind.get().setLast_name( user.getLast_name() );
			userFind.get().setLogin( user.getLogin() );
			userFind.get().setPass( user.getPass() );
			
			User userUp = userDAO.save(userFind.get());
			
			return userUp;
			
		}
		throw new ResourceNotFoundException( "Course not found" );	
	}

}
