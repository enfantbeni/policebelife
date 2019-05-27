package com.belife.policemanager.model.aaronrepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.entity.AgenceBanque;

public interface AaronAgenceBanqueRepository extends JpaRepository<AgenceBanque, String>{
	
	@Query(" select a from AgenceBanque a ")
	List<AgenceBanque> findAllAgenceBanques();
	
	@Query(" select a from AgenceBanque a ")
	Page<AgenceBanque> findAllAgenceBanquesPage( Pageable pageable);
	
	@Query("select a from AgenceBanque a where a.codeGuichet = :codeGuichet ")
	AgenceBanque findAgenceBanquecodeGuichet(@Param("codeGuichet") String codeGuichet);
	
	@Query("select libelleAgence from AgenceBanque a where a.codeBanque = :codeBanque ")
	List<String> findAllLibelleAgenceBycodeBanque(@Param("codeBanque") String codeBanque);
	
	@Query("select codeGuichet from AgenceBanque a where a.codeBanque = :codeBanque ")
	List<String> findAllCodeAgenceBycodeBanque(@Param("codeBanque") String codeBanque);
	
	@Query("select codeGuichet from AgenceBanque a where a.codeBanque = :codeBanque ")
	String findCodeAgenceBycodeBanque(@Param("codeBanque") String codeBanque);
	
	@Query("select libelleAgence from AgenceBanque a  ")
	List<String> findAllLibelleAgence();
	
	@Query("select codeGuichet from AgenceBanque a where a.libelleAgence= :libelleAgence ")
	List<String> findCodesAgenceBanqueByLibelle(@Param("libelleAgence") String libelleAgence);
	
	@Query("select codeBanque from AgenceBanque a  ")
	List<String> findAllDistinctCodeAgenceBanque();

}
