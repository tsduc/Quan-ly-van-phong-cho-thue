package com.laptrinhjavaweb.repository.custom.impl;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.entity.BuildingEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import com.laptrinhjavaweb.utils.MapUtils;
import com.laptrinhjavaweb.utils.NumberUtils;
import com.laptrinhjavaweb.utils.StringUtils;

@Repository
//@Primary
public class BuildingRepositoryCustomImpl extends SimpleJdbcRepository<BuildingEntity> implements BuildingRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public List<BuildingEntity> getAllBuilding(Pageable pageable, BuildingSearchBuilder buildingSearchBuilder) {
		StringBuilder sql = new StringBuilder(buildQueryFilter(buildingSearchBuilder))
				.append(" LIMIT ").append(pageable.getPageSize()).append("\n")
				.append(" OFFSET ").append(pageable.getOffset());
		System.out.println("Final query: " + sql.toString());

		Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
		return query.getResultList();
	}

	@Override
	public int countTotalItem(BuildingSearchBuilder buildingSearchBuilder) {
		String sql = buildQueryFilter(buildingSearchBuilder);
		Query query = entityManager.createNativeQuery(sql.toString());
		return query.getResultList().size();
	}

	private String buildQueryFilter(BuildingSearchBuilder buildingSearchBuilder) {
		StringBuilder sql = new StringBuilder("SELECT b.* FROM building as b ");
		sql = buildSqlJoiningUsingBuilder(buildingSearchBuilder, sql);
		sql.append(SystemConstant.ONE_EQUAL_ONE);
		sql = buildSqlCommonUsingBuilder(buildingSearchBuilder, sql);
		sql = buildSqlSpeciaUsingBuilderl(buildingSearchBuilder, sql);
		sql.append(" group by b.id");

		return sql.toString();
	}

	// Builder partern

	private StringBuilder buildSqlJoiningUsingBuilder(BuildingSearchBuilder builder, StringBuilder sql) {
		if (builder.getStaffId() != null) {
			sql.append(" inner join assignmentbuilding as ab on ab.building_id = b.id ");
		}
		return sql;
	}
	
	private StringBuilder buildSqlCommonUsingBuilder(BuildingSearchBuilder builder, StringBuilder sql) {
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				String fieldName = field.getName();
				if (!fieldName.equals("types") && !fieldName.startsWith("rentarea")
						&& !fieldName.equals("staffid") && !fieldName.startsWith("rentpirce")) {
					Object objectValue = field.get(builder);
					if(objectValue != null && !objectValue.equals("")) {
						if(field.getType().getName().equals("java.lang.String")) {
							sql.append(" and b."+fieldName.toLowerCase()+" like '%"+objectValue+"%'");
						} else if(field.getType().getName().equals("java.lang.Integer")) {
							sql.append(" and b."+fieldName.toLowerCase()+" = "+objectValue+"" );
						}
					}
				}
			}
		} catch (Exception e) {
			return null;
		}
		return sql;
	}
	
	private StringBuilder buildSqlSpeciaUsingBuilderl(BuildingSearchBuilder builder, StringBuilder sql) {
		Integer rentPirceFrom = builder.getRentPirceFrom();
		Integer rentPirceTo = builder.getRentPirceTo();
		Integer rentAreaFrom = builder.getRentAreaFrom();
		Integer rentAreaTo = builder.getRentAreaTo();
		Long staffId = builder.getStaffId();
		List<String> types = builder.getBuildingTypes();

		if (rentPirceFrom != null) {
			sql.append(" and b.rentpirce >= " + rentPirceFrom + "");
		}
		if (rentPirceTo != null) {
			sql.append(" and b.rentpirce <= " + rentPirceTo + "");
		}
		if (rentAreaFrom != null || rentAreaTo != null) {
			sql.append(" and EXISTS (select value from rentarea r where r.buildingid = b.id");
			if (rentAreaFrom != null) {
				sql.append(" and r.value >= ").append(rentAreaFrom);
			}
			if (rentAreaTo != null) {
				sql.append(" and r.value <= ").append(rentAreaTo);
			}
			sql.append(")");
		}
		if (staffId != null) {
			sql.append(" and ab.staff_id = " + staffId + "");
		}
		// java 8
		if (types != null && types.size() > 0) {
			sql.append(" and (");
			String sqlJoin = types.stream().map(item -> "type like '%" + item + "%'").collect(Collectors.joining(" or "));
			sql.append(sqlJoin);
			sql.append(")");
		}
		return sql;
	}

}
