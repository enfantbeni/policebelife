package com.belife.policemanager.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.entity.Banque;

public interface BanqueRepository extends JpaRepository<Banque, Integer> {
	
	
	@Query("select b from Banque b where b.nomBanque = :nomBanque AND b.idBanque <> :idBanque ")
	Banque findBanquePrincipaleByNom(@Param("nomBanque") String nomBanque,  @Param("idBanque") Integer idBanque);
	
	@Query("select b from Banque b where b.codeBanque = :codeBanque AND b.idBanque <> :idBanque ")
	Banque findBanquePrincipaleByCode(@Param("codeBanque") String codeBanque, @Param("idBanque") Integer idBanque);
	
	@Query("select b from Banque b where b.nomBanque = :nomBanque  ")
	Banque findBanquePrincipaleByNomBanque(@Param("nomBanque") String nomBanque );
	
	@Query("select b from Banque b where b.codeBanque = :codeBanque  ")
	Banque findBanquePrincipaleByCodeBanque(@Param("codeBanque") String codeBanque );
	
	@Query("select codeBanque from Banque b where b.nomBanque = :nomBanque ")
	String findCodeBanquePrincipale(@Param("nomBanque") String nomBanque);
	
	@Query("select b from Banque b ")
	List<Banque> findAllBanquePrincipales();
	
	@Query("select nomBanque from Banque b where b.status = :status ")
	List<String> findAllNomBanque(@Param("status") String status);
	
	@Query("select codeBanque from Banque b where b.status = :status ")
	List<String> findAllCodeBanque(@Param("status") String status);
	
	@Query("select codeBanque from Banque b")
	List<String> findAllCodeBanque();
	
	@Query("select b from Banque b where b.nomBanque = :nomBanque AND b.codeBanque= :codeBanque")
	Banque findBanquePrincipaleByNomAndCode(@Param("nomBanque") String nomBanque,@Param("codeBanque") String codeBanque);
	
	@Query(" select b from Banque b ")
	Page<Banque> findAllBanquePage( Pageable pageable);
		
	
}
