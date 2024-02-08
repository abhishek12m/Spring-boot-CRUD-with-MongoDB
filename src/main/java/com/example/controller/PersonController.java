package com.example.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.PersonNotFound;
import com.example.model.Person;
import com.example.service.PersonService;
import com.mongodb.MongoException;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping("/getAll")
	public List<Person> getAll() {
		return personService.getAllPersons();
	}

	@GetMapping("/get/{id}")
	public Person getById(@PathVariable String id) {
		return personService.getPersonById(id).orElseThrow(() -> new PersonNotFound(id));
	}

	@DeleteMapping("/delete/{id}")
	public String deletePerson(@PathVariable String id) {
		personService.deletePersonById(id);
		return "Person deleted with id " + id;
	}

	@PostMapping("/save")
	public ResponseEntity<?> savePerson(@Valid @RequestBody Person person) {
		try {
			return ResponseEntity.ok(personService.savePerson(person));
		} catch (ValidationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (MongoException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error saving person");
		}
	}

	@PutMapping("/update/{id}")
	public Person updatePerson(@Valid @RequestBody Person updateDetails, @PathVariable String id) {
		return personService.updatePerson(id, updateDetails);
	}

}
