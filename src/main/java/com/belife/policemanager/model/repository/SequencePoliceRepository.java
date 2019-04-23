package com.belife.policemanager.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.entity.Client;
import com.belife.policemanager.model.entity.Sequence;
import com.belife.policemanager.model.entity.SequencePolice;

public interface SequencePoliceRepository extends JpaRepository<SequencePolice, Integer> {
	
	@Query("select s from SequencePolice s where s.idSequencePolice = :idSequencePolice AND s.estSupprimer=false ORDER BY dateCreation DESC")
	SequencePolice findSequencePoliceByIdSequence(@Param("idSequencePolice") Integer idSequencePolice);
	
	@Query("select s from SequencePolice s where s.seqPolice = :seqPolice AND s.estSupprimer=false")
	SequencePolice findSequencePoliceBySeqPolice(@Param("seqPolice") String seqPolice);
	
	@Query("select s from SequencePolice s where s.estSupprimer = :estSupprimer ORDER BY dateCreation DESC")
	List<SequencePolice> findAllSequencePolices(@Param("estSupprimer") Boolean estSupprimer);
	
	@Query("select seqPolice from SequencePolice s where s.estSupprimer=false ORDER BY dateCreation DESC  ")
	String derniereSequencePoliceBySeqPolice();
	
	@Query("select s from SequencePolice s where s.idSequence= :idSequence AND s.estSupprimer=false ORDER BY dateCreation DESC")
	List<SequencePolice> listeSequencePoliceByIdSequence(@Param("idSequence") Sequence idSequence);
	
	@Query("select seqPolice from SequencePolice s where s.idSequence= :idSequence AND s.estSupprimer=false ORDER BY dateCreation DESC")
	List<String> listeSeqPoliceByIdSequence(@Param("idSequence") Sequence idSequence);
	
	@Query("select s from SequencePolice s where s.estSupprimer = :estSupprimer order by s.dateCreation desc")
	Page<SequencePolice> findAllSequencePolices(@Param("estSupprimer") Boolean estSupprimer, Pageable pageable);	
	
	@Query("select s from SequencePolice s where s.idSequence= :idSequence AND s.estSupprimer = :estSupprimer order by s.dateCreation desc")
	Page<SequencePolice> findAllSequencePolicesBySeq( @Param("idSequence") Sequence idSequence, @Param("estSupprimer") Boolean estSupprimer, Pageable pageable);	
	
	

}
