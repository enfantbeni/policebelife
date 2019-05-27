package com.belife.policemanager.model.aaronrepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.aaronentity.AaronBanque;
import com.belife.policemanager.model.entity.Banque;

public interface AaronBanqueRepository extends JpaRepository<AaronBanque, Integer>{
	
	@Query("select b from AaronBanque b where b.nomBanque = :nomBanque AND b.idBanque <> :idBanque ")
	AaronBanque findBanquePrincipaleByNom(@Param("nomBanque") String nomBanque,  @Param("idBanque") Integer idBanque);
	
	@Query("select b from AaronBanque b where b.codeBanque = :codeBanque AND b.idBanque <> :idBanque ")
	AaronBanque findBanquePrincipaleByCode(@Param("codeBanque") String codeBanque, @Param("idBanque") Integer idBanque);
	
	@Query("select b from AaronBanque b where b.nomBanque = :nomBanque  ")
	AaronBanque findBanquePrincipaleByNomBanque(@Param("nomBanque") String nomBanque );
	
	@Query("select b from AaronBanque b where b.codeBanque = :codeBanque  ")
	AaronBanque findBanquePrincipaleByCodeBanque(@Param("codeBanque") String codeBanque );
	
	@Query("select codeBanque from AaronBanque b where b.nomBanque = :nomBanque ")
	String findCodeBanquePrincipale(@Param("nomBanque") String nomBanque);
	
	@Query("select b from AaronBanque b ")
	List<AaronBanque> findAllBanquePrincipales();
	
	@Query("select nomBanque from AaronBanque b where b.status = :status ")
	List<String> findAllNomBanque(@Param("status") String status);
	
	@Query("select codeBanque from AaronBanque b where b.status = :status ")
	List<String> findAllCodeBanque(@Param("status") String status);
	
	@Query("select codeBanque from AaronBanque b")
	List<String> findAllCodeBanque();
	
	@Query("select b from AaronBanque b where b.nomBanque = :nomBanque AND b.codeBanque= :codeBanque")
	AaronBanque findBanquePrincipaleByNomAndCode(@Param("nomBanque") String nomBanque,@Param("codeBanque") String codeBanque);
	
	@Query(" select b from AaronBanque b ")
	Page<AaronBanque> findAllBanquePage( Pageable pageable);

}
