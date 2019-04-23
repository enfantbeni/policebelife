package com.belife.policemanager.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.entity.Sequence;

public interface SequenceRepository extends JpaRepository<Sequence, Integer> {
	
	@Query("select s from Sequence s where s.idSequence = :idSequence AND s.estSupprimer=false ORDER BY dateCreation DESC")
	Sequence findSequencePoliceByIdSequence(@Param("idSequence") Integer idSequence);
	
	@Query("select s from Sequence s where s.seq = :seq AND s.estSupprimer=false")
	Sequence findSequenceBySeq(@Param("seq") String seq);
	
	@Query("select seq from Sequence s where s.estSupprimer=false")
	List<String> findAllSeq();
	
	@Query("select s from Sequence s where s.estSupprimer = :estSupprimer AND s.estSupprimer=false ORDER BY dateCreation DESC")
	List<Sequence> findAllSequences(@Param("estSupprimer") Boolean estSupprimer);


}
