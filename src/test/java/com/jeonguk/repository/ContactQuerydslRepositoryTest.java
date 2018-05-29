package com.jeonguk.repository;

import com.jeonguk.TestData;
import com.jeonguk.entity.Company;
import com.jeonguk.entity.CompanyType;
import com.jeonguk.entity.Contact;
import com.jeonguk.entity.ContactType;
import com.jeonguk.service.CompanyService;
import com.jeonguk.service.ContactService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContactQuerydslRepositoryTest extends TestData {

	@Autowired
	private ContactQuerydslRepository demo;

	@Autowired
	private ContactService contactService;

	@Autowired
	private CompanyService companyService;

	@Test
	public void it_should_find_contact_after_saved() {
		Company company = companyService.save(buildTestCompany("Zheng JCG", CompanyType.CUSTOMER));
		Contact mary = contactService.save(company, "Mary", "Zheng", ContactType.PRIMARY);

		long contactId = 1L;
		Contact found = demo.findOne(contactId);
		assertNotNull(found);
		assertEquals("Mary", found.getFirstName());
		assertEquals("Zheng", found.getLastName());
		assertEquals(ContactType.PRIMARY, found.getType());

		Contact alex = contactService.save(company, "Alex", "Zheng", ContactType.SECONDARY);
		List<Contact> rets = demo.findByCompany(company);

		assertNotNull(rets);
		assertEquals(2, rets.size());

		contactService.delete(mary);
		contactService.delete(alex);
	}

}
