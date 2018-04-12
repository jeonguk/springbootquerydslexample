package com.jeonguk;

import com.jeonguk.entity.Company;
import com.jeonguk.entity.CompanyType;
import com.jeonguk.entity.Contact;
import com.jeonguk.entity.ContactType;

public class TestData {

	public TestData() {
		super();
	}

	protected Company buildTestCompany(String name, CompanyType type) {
		Company company = new Company();
		company.setName(name);
		company.setType(type);
		return company;
	}

	protected Contact buildTestContact(String firstName, String lastName, ContactType type) {
		Contact contact = new Contact();
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setType(type);
		return contact;
	}

}
