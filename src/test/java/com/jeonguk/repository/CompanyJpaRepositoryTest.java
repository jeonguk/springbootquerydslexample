package com.jeonguk.repository;

import com.jeonguk.TestData;
import com.jeonguk.entity.Company;
import com.jeonguk.entity.CompanyType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CompanyJpaRepositoryTest extends TestData {

	@Autowired
	private CompanyJpaRepository comRepo;

	@Test
	public void it_should_find_company_byTypeAndName_after_save_it() {
		comRepo.deleteAll();
		String name = "test company";
		comRepo.save(buildTestCompany(name, CompanyType.VENDOR));

		List<Company> rep = comRepo.findByTypeAndName(CompanyType.VENDOR, name);
		assertEquals(1, rep.size());
		assertEquals(name, rep.get(0).getName());
		assertEquals(CompanyType.VENDOR, rep.get(0).getType());
		assertEquals(0, rep.get(0).getContacts().size());
	}

}
