package com.belife.policemanager.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.entity.Banque;
import com.belife.policemanager.model.entity.Client;
import com.belife.policemanager.model.entity.ClientSociete;
import com.belife.policemanager.model.entity.Societe;

public interface ClientSocieteRepository  extends JpaRepository<ClientSociete, Integer>{
	
	@Query("select idSociete from ClientSociete s where s.idClient = :idClient ")
	Societe findSocieteByIdClient(@Param("idClient") Client idClient);
	
	@Query("select s from ClientSociete s where s.idClient = :idClient ")
	ClientSociete findSocieteByClient(@Param("idClient") Client idClient);

}
