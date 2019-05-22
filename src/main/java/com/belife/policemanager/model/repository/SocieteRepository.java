package com.belife.policemanager.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.entity.Societe;

public interface SocieteRepository extends JpaRepository<Societe, String> {
	
	@Query(" select s from Societe s")
	List<Societe> findAllSociete();
	
	@Query(" select s from Societe s ")
	Page<Societe> findAllSocietePage( Pageable pageable);
	
	@Query("select s from Societe s where s.codeSociete = :codeSociete ")
	Societe findSocieteByCodeSocieteSociete(@Param("codeSociete") String codeSociete); 
	
	@Query("select codeSociete from Societe s where s.nomSociete = :nomSociete AND s.status= :status ")
	List<String> findCodesSocieteByNomSociete(@Param("nomSociete") String nomSociete, @Param("status") String status); 
	
	@Query("select nomSociete from Societe s where s.status= :status")
	List<String> findAllNomSociete(@Param("status") String status);
	
	@Query("select codeSociete from Societe s where s.status= :status")
	List<String> findAllCodeSociete(@Param("status") String status);
	
	

}
