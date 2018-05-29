package com.jeonguk.dao.impl;

import com.jeonguk.TestData;
import com.jeonguk.dao.CompanyDao;
import com.jeonguk.entity.Company;
import com.jeonguk.entity.CompanyType;
import com.jeonguk.service.CompanyService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CompanyDaoImplTest extends TestData {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CompanyDao comDao;

	@Test
	public void it_should_find_null() {
		String companyName = "Bad Company";
		Company foundCom = comDao.findByName(companyName);
		assertNull(foundCom);
	}

	@Test
	public void it_should_find_after_save() {
		String companyName = "Zheng Company";
		companyService.save(buildTestCompany(companyName, CompanyType.CUSTOMER));
		Company foundCom = comDao.findByName(companyName);
		assertNotNull(foundCom);
		assertEquals(companyName, foundCom.getName());
		assertEquals(CompanyType.CUSTOMER, foundCom.getType());
		assertNull(foundCom.getContacts());
	}

}
