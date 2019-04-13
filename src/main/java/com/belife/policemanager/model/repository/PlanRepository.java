package com.belife.policemanager.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer> {
	
	
	@Query("select planDuree from Plan p ")
	List<String> findPlanDurees();
	
	@Query("select p from Plan p where p.planDuree = :planDuree")
	Plan findPlanByPlanDuree(@Param("planDuree") String planDuree);

}


