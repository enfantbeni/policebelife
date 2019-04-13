package com.belife.policemanager.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.entity.Agence;

public interface AgenceRepository extends JpaRepository<Agence, Integer> {
	
	@Query("select a from Agence a where a.idAgence = :idAgence AND a.estSupprimer=false")
	Agence findAgenceByIdAgence(@Param("idAgence") Integer idAgence);
	
	@Query("select a from Agence a where a.codeDirect = :codeDirect AND a.estSupprimer=false")
	Agence findAgenceByCodeDirect(@Param("codeDirect") String codeDirect);
	
	@Query("select a from Agence a where a.nomDirect = :nomDirect AND a.estSupprimer=false")
	Agence findAgenceByNomDirect(@Param("nomDirect") String nomDirect);
		
	@Query("select a from Agence a where a.estSupprimer = :estSupprimer AND a.estSupprimer=false")
	List<Agence> findAllAgences(@Param("estSupprimer") Boolean estSupprimer);
	
	@Query("select a from Agence a where a.idAgence <> :idAgence AND a.estSupprimer=false")
	List<Agence> findAllAgencesOmission(@Param("idAgence") Integer idAgence);
	
	
	@Query("select nomDirect from Agence a where a.estSupprimer = :estSupprimer AND a.estSupprimer=false")
	List<String> findAllNomDirects(@Param("estSupprimer") Boolean estSupprimer);
	
	@Query("select idAgence from Agence a where a.nomDirect = :nomDirect AND a.estSupprimer=false")
	Integer findidDirect(@Param("nomDirect") String nomDirect);
	
	@Query("select nomDirect from Agence a where a.idAgence = :idAgence AND a.estSupprimer=false")
	String findNomDirect(@Param("idAgence") Integer idAgence);
	

	
}
