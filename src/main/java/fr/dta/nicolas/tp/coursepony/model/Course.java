package fr.dta.nicolas.tp.coursepony.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String location;
	
	@Column
	private Date date;
	
	@Column
	@ManyToMany
	@JoinTable(name="course_pony",  joinColumns=@JoinColumn(name="course_id"), inverseJoinColumns=@JoinColumn(name="ponies_id"))
	private List<Pony> ponies = new ArrayList<Pony>();
	
	public Course() {	
	}
	
	public Course(String location, Date date) {
		this.location = location;
		this.date = date;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Pony> getPonies() {
		return ponies;
	}

	public void setPonies(List<Pony> ponies) {
		this.ponies = ponies;
	}
	
	
	

	
	
	
}
	
	
	
	
	
	
