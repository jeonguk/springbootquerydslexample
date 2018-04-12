package com.jeonguk.dao.impl;

import com.jeonguk.dao.CompanyDao;
import com.jeonguk.entity.Company;
import com.jeonguk.entity.CompanyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

@Component
public class CompanyDaoImpl implements CompanyDao {

	private static final String SQL_SEARCH_COMPANY_BY_NAME = "Select id CompanyID, name CompanyName, type from Company where name = :companyName";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public Company findByName(String companyName) {
		Company ret = null;
		try {
			ret = jdbcTemplate.queryForObject(SQL_SEARCH_COMPANY_BY_NAME,
					Collections.singletonMap("companyName", companyName), new CompanyRowMapper());
		} catch (IncorrectResultSizeDataAccessException e) {
			// ignore
		}
		return ret;
	}

	private static final class CompanyRowMapper implements RowMapper<Company> {
		public Company mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			final Company company = new Company();
			company.setId(resultSet.getLong("CompanyID"));
			company.setName(resultSet.getString("CompanyName"));
			company.setType(CompanyType.valueOf(resultSet.getString("type")));
			return company;
		}
	}

}