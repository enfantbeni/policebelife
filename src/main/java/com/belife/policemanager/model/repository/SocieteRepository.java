package com.belife.policemanager.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.entity.Banque;
import com.belife.policemanager.model.entity.Societe;

public interface SocieteRepository extends JpaRepository<Societe, Integer> {
	@Query("select s from Societe s where s.nomSociete = :nomSociete AND s.estSupprimer=false")
	Societe findSocieteByNom(@Param("nomSociete") String nomSociete);
	
	@Query("select s from Societe s where s.codeSociete = :codeSociete AND s.estSupprimer=false")
	Societe findSocieteByCode(@Param("codeSociete") String codeSociete);
	
	
	@Query("select s from Societe s where s.estSupprimer = :estSupprimer")
	List<Societe> findAllSocietes(@Param("estSupprimer") Boolean estSupprimer);
	
	@Query("select s from Societe s where s.idSocite <> :idSocite AND s.estSupprimer =false")
	List<Societe> findAllSocietesOmission(@Param("idSocite") Integer idSocite);
	
	@Query("select nomSociete from Societe s where s.estSupprimer = :estSupprimer")
	List<String> findAllNomSociete(@Param("estSupprimer") Boolean estSupprimer);
	
	@Query("select codeSociete from Societe s where s.nomSociete = :nomSociete AND s.estSupprimer =false")
	String findCodeSocieteByNomSociete(@Param("nomSociete") String nomSociete);
	

	

}
