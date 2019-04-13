package com.belife.policemanager.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.entity.Banque;
import com.belife.policemanager.model.entity.Client;
import com.belife.policemanager.model.entity.ClientBanque;

public interface ClientBanqueRepository extends JpaRepository<ClientBanque, Integer>  {
	

	@Query("select idBanque from ClientBanque c where c.idClient = :idClient ")
	Banque findIdBanqueByIdClient(@Param("idClient") Client idClient);
	
	@Query("select c from ClientBanque c where c.idClient = :idClient ")
	ClientBanque findBanqueByIdClient(@Param("idClient") Client idClient);
	
}
