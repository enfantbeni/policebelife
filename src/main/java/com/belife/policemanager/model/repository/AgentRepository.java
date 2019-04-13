package com.belife.policemanager.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.entity.Agence;
import com.belife.policemanager.model.entity.Agent;
import com.belife.policemanager.model.entity.Client;

public interface AgentRepository extends JpaRepository<Agent, Integer> {
	
	@Query("select a from Agent a where a.codeAgent = :codeAgent AND a.estSupprimer=false")
	Agent findAgentByCodeAgent(@Param("codeAgent") String codeAgent);
	
//	@Query("select a from Agence a where a.nomDirect = :nomDirect AND a.estSupprimer=false")
//	Agence findAgenceByNomDirect(@Param("nomDirect") String nomDirect);
//	
//	@Query("select a from Agence a where a.codeDirect = :codeDirect AND a.estSupprimer=false")
//	List<Agence> findCodeAgences(@Param("codeDirect") String codeDirect);
//	
	@Query("select a from Agent a where a.estSupprimer = :estSupprimer")
	List<Agent> findAllAgents(@Param("estSupprimer") Boolean estSupprimer);
	
	@Query("select codeAgent from Agent a where a.idAgence = :idAgence AND a.estSupprimer =false")
	List<String> findAllCodeAgents(@Param("idAgence") Agence agence);
	
	
	@Query("select a from Agent a where a.idAgence = :idAgence AND a.estSupprimer=false")
	List<Agent> findAgentsByIdAgence(@Param("idAgence") Agence idAgence);
	
	@Query("select a from Agent a where a.estSupprimer = :estSupprimer")
	Page<Agent> findAllAgentsPage(@Param("estSupprimer") Boolean estSupprimer, Pageable pageable);

}
