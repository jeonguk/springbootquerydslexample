package com.jeonguk.repository;

import com.jeonguk.entity.Company;
import com.jeonguk.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

public interface ContactQuerydslRepository extends JpaRepository<Contact, Long>, QueryDslPredicateExecutor<Contact> {
	public List<Contact> findByCompany(Company company);
}
