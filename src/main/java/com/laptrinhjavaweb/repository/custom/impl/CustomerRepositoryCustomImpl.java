package com.laptrinhjavaweb.repository.custom.impl;


import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
//@Primary
public class CustomerRepositoryCustomImpl extends SimpleJdbcRepository<CustomerEntity> implements CustomerRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public List<CustomerEntity> getAllCustomer(Pageable pageable, CustomerSearchBuilder customerSearchBuilder) {
		StringBuilder sql = new StringBuilder(buildQueryFilter(customerSearchBuilder))
				.append(" LIMIT ").append(pageable.getPageSize()).append("\n")
				.append(" OFFSET ").append(pageable.getOffset());
		System.out.println("Final query: " + sql.toString());

		Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
		return query.getResultList();
	}

	@Override
	public int countTotalItem(CustomerSearchBuilder customerSearchBuilder) {
		String sql = buildQueryFilter(customerSearchBuilder);
		Query query = entityManager.createNativeQuery(sql.toString());
		return query.getResultList().size();
	}

	private String buildQueryFilter(CustomerSearchBuilder customerSearchBuilder) {
		StringBuilder sql = new StringBuilder("SELECT cus.* FROM customer as cus ");
		sql = buildSqlJoiningUsingBuilder(customerSearchBuilder, sql);
		sql.append(SystemConstant.ONE_EQUAL_ONE);
		sql = buildSqlCommonUsingBuilder(customerSearchBuilder, sql);
		sql = buildSqlSpeciaUsingBuilderl(customerSearchBuilder, sql);
		sql.append(" group by cus.id");

		return sql.toString();
	}

	// Builder partern

	private StringBuilder buildSqlJoiningUsingBuilder(CustomerSearchBuilder builder, StringBuilder sql) {
		if (builder.getStaffId() != null) {
			sql.append(" inner join assignmentcustomer as ac on ac.customer_id = cus.id ");
		}
		return sql;
	}
	
	private StringBuilder buildSqlCommonUsingBuilder(CustomerSearchBuilder builder, StringBuilder sql) {
		try {
			Field[] fields = CustomerSearchBuilder.class.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				String fieldName = field.getName();
				if (!fieldName.equals("staffId")) {
					Object objectValue = field.get(builder);
					if(objectValue != null && !objectValue.equals("")) {
						if(field.getType().getName().equals("java.lang.String")) {
							sql.append(" and cus."+fieldName.toLowerCase()+" like '%"+objectValue+"%'");
						} else if(field.getType().getName().equals("java.lang.Integer")) {
							sql.append(" and cus."+fieldName.toLowerCase()+" = "+objectValue+"" );
						}
					}
				}
			}
		} catch (Exception e) {
			return null;
		}
		return sql;
	}
	private StringBuilder buildSqlSpeciaUsingBuilderl(CustomerSearchBuilder builder, StringBuilder sql) {
		Long staffId = builder.getStaffId();

		if (staffId != null) {
			sql.append(" and ac.staff_id = " + staffId + "");
		}
		return sql;
	}

}
