package com.jeonguk.service;

import com.jeonguk.TestData;
import com.jeonguk.entity.Company;
import com.jeonguk.entity.CompanyType;
import com.jeonguk.entity.Contact;
import com.jeonguk.entity.ContactType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ContactServiceTest extends TestData {

	@Autowired
	private ContactService contactService;

	@Autowired
	private CompanyService companyService;

	@Test
	public void it_should_find_zero_contact_when_searchByName_with_non_exist_data() {
		List<Contact> found = contactService.searchByName("Bob", null);
		assertTrue(found.isEmpty());
	}

	@Test
	public void it_should_find_contact_when_searchByName_with_exist_data() {

		Company company = companyService.save(buildTestCompany("Zheng company", CompanyType.CUSTOMER));
		Contact contact = contactService.save(company, "John", "Zheng", ContactType.PRIMARY);
		assertNotNull(contact);
		List<Contact> found = contactService.searchByName("John", null);
		assertEquals(1, found.size());
		assertEquals("John", found.get(0).getFirstName());
		assertEquals("Zheng", found.get(0).getLastName());
		assertEquals(ContactType.PRIMARY, found.get(0).getType());
		assertEquals("Zheng company", found.get(0).getCompany().getName());

		contactService.delete(contact);
	}

}
