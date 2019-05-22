package com.belife.policemanager.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.entity.Agence;

public interface AgenceRepository extends JpaRepository<Agence, Integer> {
	
	@Query("select a from Agence a where a.idAgence = :idAgence ")
	Agence findAgenceByIdAgence(@Param("idAgence") Integer idAgence);
	
	@Query("select a from Agence a where a.codeDirect = :codeDirect ")
	Agence findAgenceByCodeDirect(@Param("codeDirect") String codeDirect);
	
	@Query("select a from Agence a where a.nomDirect = :nomDirect ")
	Agence findAgenceByNomDirect(@Param("nomDirect") String nomDirect);
		
	
	@Query("select a from Agence a ")
	Page<Agence> findAllAgencePage( Pageable pageable);
	
	@Query("select a from Agence a where a.idAgence <> :idAgence ")
	List<Agence> findAllAgencesOmission(@Param("idAgence") Integer idAgence);
	
	
	@Query("select nomDirect from Agence a ")
	List<String> findAllNomDirects();
	
	@Query("select idAgence from Agence a where a.nomDirect = :nomDirect ")
	Integer findidDirect(@Param("nomDirect") String nomDirect);
	
	@Query("select nomDirect from Agence a where a.idAgence = :idAgence")
	String findNomDirect(@Param("idAgence") Integer idAgence);
		
}
