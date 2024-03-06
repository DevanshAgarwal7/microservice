package com.nagarro.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.nagarro.customer.entity.CustomerEntity;

@Repository
@EnableJpaRepositories
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>{

	CustomerEntity findByEmail(String email);

	CustomerEntity findByPhoneNumber(String phoneNumber);

	
}
