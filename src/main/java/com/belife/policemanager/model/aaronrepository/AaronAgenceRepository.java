package com.belife.policemanager.model.aaronrepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.aaronentity.AaronAgence;
import com.belife.policemanager.model.entity.Agence;

public interface AaronAgenceRepository extends JpaRepository<AaronAgence, Integer>{
	
	@Query("select a from AaronAgence a where a.idAgence = :idAgence ")
	AaronAgence findAgenceByIdAgence(@Param("idAgence") Integer idAgence);
	
	@Query("select a from AaronAgence a where a.codeDirect = :codeDirect ")
	AaronAgence findAgenceByCodeDirect(@Param("codeDirect") String codeDirect);
	
	@Query("select a from AaronAgence a where a.nomDirect = :nomDirect ")
	AaronAgence findAgenceByNomDirect(@Param("nomDirect") String nomDirect);
		
	
	@Query("select a from AaronAgence a ")
	Page<AaronAgence> findAllAgencePage( Pageable pageable);
	
	@Query("select a from AaronAgence a where a.idAgence <> :idAgence ")
	List<AaronAgence> findAllAgencesOmission(@Param("idAgence") Integer idAgence);
	
	
	@Query("select nomDirect from AaronAgence a ")
	List<String> findAllNomDirects();
	
	@Query("select idAgence from AaronAgence a where a.nomDirect = :nomDirect ")
	Integer findidDirect(@Param("nomDirect") String nomDirect);
	
	@Query("select nomDirect from AaronAgence a where a.idAgence = :idAgence")
	String findNomDirect(@Param("idAgence") Integer idAgence);

}
