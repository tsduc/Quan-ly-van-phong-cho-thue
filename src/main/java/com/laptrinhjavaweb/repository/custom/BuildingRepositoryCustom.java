package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BuildingRepositoryCustom extends JdbcRepositoryCustom<BuildingEntity> {

	List<BuildingEntity> getAllBuilding(Pageable pageable, BuildingSearchBuilder buildingSearchBuilder);

	int countTotalItem(BuildingSearchBuilder buildingSearchBuilder);
}
