package com.belife.policemanager.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.entity.Client;
import com.belife.policemanager.model.entity.ClientPlan;

public interface ClientPlanRepository extends JpaRepository<ClientPlan, Integer> {
	
	@Query("select c from ClientPlan c where c.idClient = :idClient ")
	ClientPlan findClientPlanByIdClient(@Param("idClient") Client idClient);
	
	

}
