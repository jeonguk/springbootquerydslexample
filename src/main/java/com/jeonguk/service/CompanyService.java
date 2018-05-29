package com.jeonguk.service;

import com.jeonguk.entity.Company;
import com.jeonguk.entity.CompanyType;
import com.jeonguk.repository.CompanyJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class CompanyService {

	@Autowired
	private CompanyJpaRepository companyJpaRepo;

	public List<Company> searchByNameAndType(String companyName, CompanyType type) {

		return companyJpaRepo.findAll((root, query, cb) -> {

			Predicate matchType = cb.equal(root.<CompanyType> get("type"), type);
			Predicate returnPredicate = cb.and(matchType);
			if (!StringUtils.isEmpty(companyName)) {
				Predicate matchName = cb.equal(root.<String> get("name"), companyName);
				returnPredicate = cb.and(matchType, matchName);
			}

			return returnPredicate;
		});
	}

	public Company save(Company company) {
		return companyJpaRepo.save(company);
	}

	public void delete(Company company){
		companyJpaRepo.delete(company);
	}

}
