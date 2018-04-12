package com.jeonguk.dao;

import com.jeonguk.entity.Contact;

import java.util.List;

public interface ContactQuerydslDao {
	List<Contact> getContactById(Long id);
	List<Contact> getContactFromTypedQuery(Long id);
	List<Contact> getContactFromDynamicQuery(String firstName, String lastName);
}
