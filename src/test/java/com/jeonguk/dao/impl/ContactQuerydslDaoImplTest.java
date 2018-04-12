package com.jeonguk.dao.impl;

import com.jeonguk.dao.ContactQuerydslDao;
import com.jeonguk.entity.Contact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ContactQuerydslDaoImplTest {

	@Autowired
	private ContactQuerydslDao contactDao;

	@Test
	public void it_should_not_found_Contact_when_not_exist_Querydsl() {
		List<Contact> found = contactDao.getContactById(1L);
		assertTrue(found.isEmpty());
	}

	@Test
	public void it_should_not_found_Contact_when_not_exist_TypedQuery() {
		List<Contact> found = contactDao.getContactFromTypedQuery(1L);
		assertTrue(found.isEmpty());
	}

	@Test
	public void it_should_not_found_Contact_when_not_exist_DynamicQuery(){
		List<Contact> found = contactDao.getContactFromDynamicQuery("Alex", "Zheng");
		assertTrue(found.isEmpty());
	}

}
