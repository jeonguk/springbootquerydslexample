package com.jeonguk.service;

import com.jeonguk.entity.Company;
import com.jeonguk.entity.Contact;
import com.jeonguk.entity.ContactType;
import com.jeonguk.entity.QContact;
import com.jeonguk.repository.ContactQuerydslRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

	@Autowired
	private ContactQuerydslRepository contactDslRepo;

	public List<Contact> searchByName(String firstName, String lastName) {
		List<Contact> ret = new ArrayList<>();
		QContact contEquation = QContact.contact;
		Predicate cnt = contEquation.firstName.contains(firstName);
		Iterable<Contact> contacts = contactDslRepo.findAll(cnt);
		contacts.forEach(e -> {
			ret.add(e);
		});
		return ret;
	}

	public Contact save(Company company, String firstName, String lastName, ContactType type) {
		Contact contact = new Contact();
		contact.setCompany(company);
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setType(type);
		return contactDslRepo.save(contact);
	}

	public void delete(Contact contact) {
		contactDslRepo.delete(contact);
	}

}
