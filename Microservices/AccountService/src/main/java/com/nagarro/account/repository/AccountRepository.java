package com.nagarro.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.nagarro.account.entity.AccountEntity;

@Repository
@EnableJpaRepositories
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

	AccountEntity findByCustomerId(int customerId);

	void deleteByCustomerId(int customerId);

}
