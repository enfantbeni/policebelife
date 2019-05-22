package com.belife.policemanager.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.entity.Client;
import com.belife.policemanager.model.entity.Lbb;

public interface ConsultationLBRepository extends JpaRepository<Lbb, String>{
	
	@Query("select l from Lbb l")
	Page<Lbb> findAllConsultation(Pageable pageable);		
	
	@Query("select l from Lbb l where l.LBPOLN = :LBPOLN")
	Lbb findConsultationByLBPOLN(@Param("LBPOLN") String LBPOLN);
}
