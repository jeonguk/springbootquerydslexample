package com.jeonguk.dao.impl;

import com.jeonguk.dao.ContactQuerydslDao;
import com.jeonguk.entity.Contact;
import com.jeonguk.entity.QContact;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Compare the JPAQuery to TypedQuery ( non-JpaRepository)
 */
@Component
public class ContactQuerydslDaoImpl implements ContactQuerydslDao {

	@Autowired
	EntityManager em;

	@Override
	public List<Contact> getContactById(Long id) {
		QContact contact = QContact.contact;
		JPAQuery<Contact> query = new JPAQuery<Contact>(em);
		query.from(contact).where(contact.id.eq(id));
		return query.fetch();
	}

	@Override
	public List<Contact> getContactFromTypedQuery(Long id) {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Contact> contactQuery = builder.createQuery(Contact.class);
		Root<Contact> root = contactQuery.from(Contact.class);
		ParameterExpression<Long> value = builder.parameter(Long.class);
		contactQuery.select(root).where(builder.lt(root.get("id"), value));

		TypedQuery<Contact> query = em.createQuery(contactQuery);
		query.setParameter(value, id);

		return query.getResultList();
	}

	@Override
	public List<Contact> getContactFromDynamicQuery(String firstName, String lastName) {
		QContact contact = QContact.contact;
		JPAQuery<Contact> query = new JPAQuery<Contact>(em);

		BooleanExpression matchFirstName = contact.firstName.eq(firstName);
		BooleanExpression matchLastName = contact.lastName.eq(lastName);

		query.from(contact).where(matchFirstName, matchLastName).orderBy(contact.lastName.asc());

		return query.fetch();
	}

}
