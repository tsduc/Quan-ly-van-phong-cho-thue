package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.CustomerEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerRepositoryCustom extends JdbcRepositoryCustom<CustomerEntity> {

	List<CustomerEntity> getAllCustomer(Pageable pageable, CustomerSearchBuilder customerSearchBuilder);

	int countTotalItem(CustomerSearchBuilder customerSearchBuilder);
}
