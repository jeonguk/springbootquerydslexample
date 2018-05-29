package com.jeonguk.service;

import com.jeonguk.TestData;
import com.jeonguk.entity.Company;
import com.jeonguk.entity.CompanyType;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CompanyServiceTest extends TestData {

	@Autowired
	private CompanyService companyService;

	@Test
	public void it_should_find_zero_company_when_searchByNameAndType_with_non_exist_data() {
		List<Company> found = companyService.searchByNameAndType("companyName", CompanyType.CUSTOMER);
		assertTrue(found.isEmpty());
	}

	@Test
	public void it_should_find_company_when_searchByNameAndType_with_exist_data() {
		String name = "Mary Company";
		Company company = companyService.save(buildTestCompany(name, CompanyType.CUSTOMER));
		List<Company> found = companyService.searchByNameAndType(name, CompanyType.CUSTOMER);
		assertFalse(found.isEmpty());
		assertEquals(name, found.get(0).getName());

		companyService.delete(company);
	}

}
