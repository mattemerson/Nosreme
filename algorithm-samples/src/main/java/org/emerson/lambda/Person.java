package org.emerson.lambda;

import java.time.LocalDate;

public class Person {

	public enum Sex {
		MALE, FEMALE
	}
	
	private String name;
	private LocalDate birthday;
	private Sex gender;
	private String emailAddress;
	private int age;
	
	public Person(String name, LocalDate birthday, Sex gender, String emailAddress, int age)
	{
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
		this.emailAddress = emailAddress;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public Sex getGender() {
		return gender;
	}

	public void setGender(Sex gender) {
		this.gender = gender;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString()
	{
		return "(" + getName() + "," + getAge() + "," + getGender() + "," + getEmailAddress() + "," + getBirthday() + ")";
	}
	
	// Looks like a toString to me
	public void printPerson()
	{
		System.out.println(toString()); 
	}
}
