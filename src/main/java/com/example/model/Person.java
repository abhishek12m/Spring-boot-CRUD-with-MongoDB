package com.example.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "personDetails")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

	@Id
	private String id;
	@NotBlank(message = "Name can not be blank")
	private String name;
	@Min(value = 18, message = "age must be atleast 18")
	private int age;

}
