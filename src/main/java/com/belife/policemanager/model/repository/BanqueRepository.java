package com.belife.policemanager.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.entity.Banque;
import com.belife.policemanager.model.entity.BanquePrincipale;

public interface BanqueRepository extends JpaRepository<Banque, Integer> {
	
	
	@Query("select b from Banque b where b.codeGuichet = :codeGuichet AND b.estSupprimer=false")
	Banque findBanqueByCodeGuichet(@Param("codeGuichet") String codeGuichet);
	
	@Query("select b from Banque b where b.nomGuichet = :nomGuichet AND b.estSupprimer=false")
	Banque findBanqueByNomGuichet(@Param("nomGuichet") String nomGuichet);
	
	@Query("select nomGuichet from Banque b where b.idBanquePrincipale = :idBanquePrincipale AND b.estSupprimer=false")
	List<String> findNomGuichets(@Param("idBanquePrincipale") BanquePrincipale idBanquePrincipale);
	
	@Query("select codeGuichet from Banque b where b.nomGuichet = :nomGuichet AND b.estSupprimer=false")
	String findCodeGuichetByNomGuichet(@Param("nomGuichet") String nomGuichet);
	
	@Query("select b from Banque b where b.estSupprimer = :estSupprimer AND b.estSupprimer=false")
	List<Banque> findAllBanques(@Param("estSupprimer") Boolean estSupprimer);
	
	
	@Query("select idBanquePrincipale from Banque b where b.nomGuichet = :nomGuichet AND b.estSupprimer=false")
	BanquePrincipale findBanquePrincipaleByNomGuichet(@Param("nomGuichet") String nomGuichet);
	

}
