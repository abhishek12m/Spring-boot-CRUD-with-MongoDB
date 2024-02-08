package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.PersonNotFound;
import com.example.model.Person;
import com.example.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public List<Person> getAllPersons() {
		// TODO Auto-generated method stub
		return personRepository.findAll();
	}

	public Optional<Person> getPersonById(String id) {
		// TODO Auto-generated method stub
		return personRepository.findById(id);
	}

	public void deletePersonById(String id) {
		// TODO Auto-generated method stub
		personRepository.deleteById(id);

	}

	public Person savePerson(Person person) {
		// TODO Auto-generated method stub
//		person.setId(null);
		return personRepository.save(person);
	}

	public Person updatePerson(String id, Person updateDetails) {
		// TODO Auto-generated method stub
		Optional<Person> optionalPerson = personRepository.findById(id);

		if (optionalPerson.isPresent()) {
			Person existingPerson = optionalPerson.get();

			existingPerson.setName(updateDetails.getName());
			existingPerson.setAge(updateDetails.getAge());

			return personRepository.save(existingPerson);
		} else {
			throw new PersonNotFound(id);
		}
	}

}
