package com.jeonguk;

import com.jeonguk.repository.CompanyJpaRepository;
import com.jeonguk.repository.ContactQuerydslRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ApplicationTest {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void it_should_find_companyJpaRepository() {
		String beanName = "companyJpaRepository";
		CompanyJpaRepository foundBean = applicationContext.getBean(beanName, CompanyJpaRepository.class);
		assertNotNull(foundBean);
	}

	@Test
	public void it_should_find_contactQuerydslRepository() {
		String beanName = "contactQuerydslRepository";
		ContactQuerydslRepository foundBean = applicationContext.getBean(beanName, ContactQuerydslRepository.class);
		assertNotNull(foundBean);
	}
}
