package com.jeonguk.dao;

import com.jeonguk.entity.Company;

public interface CompanyDao {
	Company findByName(String companyName);
}
