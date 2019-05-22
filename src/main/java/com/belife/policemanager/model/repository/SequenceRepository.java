package com.belife.policemanager.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.entity.Agence;
import com.belife.policemanager.model.entity.Sequence;

public interface SequenceRepository extends JpaRepository<Sequence, Integer> {
	
	@Query("select s from Sequence s where s.idSequence = :idSequence AND s.estSupprimer=false ORDER BY dateCreation DESC")
	Sequence findSequencePoliceByIdSequence(@Param("idSequence") Integer idSequence);
	
	@Query("select s from Sequence s where s.idAgence = :idAgence AND s.estSupprimer=false ORDER BY dateCreation DESC")
	List<Sequence> findSequenceListeByIdSequence(@Param("idAgence") Agence idAgence);
	
	@Query("select seq from Sequence s where s.idAgence = :idAgence AND s.estSupprimer=false ORDER BY dateCreation DESC")
	List<String> findSequenceByIdAgence(@Param("idAgence") Agence idAgence);
	
	@Query("select s from Sequence s where s.seq = :seq AND s.estSupprimer=false")
	Sequence findSequenceBySeq(@Param("seq") String seq);
	
	@Query("select seq from Sequence s where s.estSupprimer=false")
	List<String> findAllSeq();
	
	@Query("select s from Sequence s where s.estSupprimer = :estSupprimer AND s.estSupprimer=false ORDER BY dateCreation DESC")
	List<Sequence> findAllSequences(@Param("estSupprimer") Boolean estSupprimer);
	
	@Query("select s from Sequence s where s.estSupprimer = :estSupprimer GROUP BY s.idSequence ORDER BY dateCreation DESC")
	List<Sequence> findAllSequencesGouper(@Param("estSupprimer") Boolean estSupprimer);
	
	@Query("select s from Sequence s where s.estSupprimer = :estSupprimer AND  s.seq <> :seqSession AND s.seq = :seq   GROUP BY s.idSequence ORDER BY dateCreation DESC")
	Sequence findSequenceModifBySeq(@Param("seq") String seq, @Param("seqSession") String seqSession, @Param("estSupprimer") Boolean estSupprimer);
	
//	
//	@Query("select s from Sequence s where s.estSupprimer = :estSupprimer GROUP BY s.idSequence ORDER BY dateCreation DESC")
//	List<Sequence> findAllSequencesGouper(@Param("estSupprimer") Boolean estSupprimer);
//	
//	
	

}
