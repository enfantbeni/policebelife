package com.belife.policemanager.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.entity.BanquePrincipale;

public interface BanquePrincipaleRepository extends JpaRepository<BanquePrincipale, Integer>{
	
	@Query("select b from BanquePrincipale b where b.nomBanque = :nomBanque AND b.estSupprimer=false")
	BanquePrincipale findBanquePrincipaleByNom(@Param("nomBanque") String nomBanque);
	
	@Query("select b from BanquePrincipale b where b.codeBanque = :codeBanque AND b.estSupprimer=false")
	BanquePrincipale findBanquePrincipaleByCode(@Param("codeBanque") String codeBanque);
	
	@Query("select codeBanque from BanquePrincipale b where b.nomBanque = :nomBanque AND b.estSupprimer=false")
	String findCodeBanquePrincipale(@Param("nomBanque") String nomBanque);
	
	@Query("select b from BanquePrincipale b where b.estSupprimer = :estSupprimer AND b.estSupprimer=false")
	List<BanquePrincipale> findAllBanquePrincipales(@Param("estSupprimer") Boolean estSupprimer);
	
	@Query("select nomBanque from BanquePrincipale b where b.estSupprimer = :estSupprimer AND b.estSupprimer=false")
	List<String> findNomsBanquePrincipale(@Param("estSupprimer") Boolean estSupprimer);
	
	@Query("select b from BanquePrincipale b where b.nomBanque = :nomBanque AND b.codeBanque= :codeBanque AND b.estSupprimer=false")
	BanquePrincipale findBanquePrincipaleByNomAndCode(@Param("nomBanque") String nomBanque,@Param("codeBanque") String codeBanque);
	

}
