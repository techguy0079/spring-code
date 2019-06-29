package com.spring.training.repo;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.spring.training.model.CompanyRevenue;


@Repository
@Qualifier(value = "companyRevenueRepository")
@EnableJpaRepositories(basePackages="com.spring.training.repo")
public interface CompanyRevenueRepository extends JpaRepository<CompanyRevenue,Long>{
}
