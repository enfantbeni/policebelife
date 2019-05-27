package com.belife.policemanager.model.aaronrepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.aaronentity.AaronAgence;
import com.belife.policemanager.model.aaronentity.AaronSequence;
import com.belife.policemanager.model.entity.Agence;

public interface AaronSequenceRepository extends JpaRepository<AaronSequence, Integer>{
	
	@Query("select s from AaronSequence s where s.idSequence = :idSequence AND s.estSupprimer=false ORDER BY dateCreation DESC")
	AaronSequence findSequencePoliceByIdSequence(@Param("idSequence") Integer idSequence);
	
	@Query("select s from AaronSequence s where s.idAgence = :idAgence AND s.estSupprimer=false ORDER BY dateCreation DESC")
	List<AaronSequence> findSequenceListeByIdSequence(@Param("idAgence") AaronAgence idAgence);
	
	@Query("select seq from AaronSequence s where s.idAgence = :idAgence AND s.estSupprimer=false ORDER BY dateCreation DESC")
	List<String> findSequenceByIdAgence(@Param("idAgence") AaronAgence idAgence);
	
	@Query("select s from AaronSequence s where s.seq = :seq AND s.estSupprimer=false")
	AaronSequence findSequenceBySeq(@Param("seq") String seq);
	
	@Query("select s from AaronSequence s where s.idAgence = :idAgence AND s.estSupprimer=false")
	List<AaronSequence> findAllSequenceByIdAgence(@Param("idAgence") AaronAgence idAgence);
	
	@Query("select seq from AaronSequence s where s.estSupprimer=false")
	List<String> findAllSeq();
	
	
	@Query("select s from AaronSequence s order by s.dateCreation desc")
	Page<AaronSequence> findAllAaronSequencesPage(Pageable pageable);	
	
	
	@Query("select s from AaronSequence s where s.estSupprimer = :estSupprimer AND s.estSupprimer=false ORDER BY dateCreation DESC")
	List<AaronSequence> findAllSequences(@Param("estSupprimer") Boolean estSupprimer);
	
	@Query("select s from AaronSequence s where s.estSupprimer = :estSupprimer GROUP BY s.idSequence ORDER BY dateCreation DESC")
	List<AaronSequence> findAllSequencesGouper(@Param("estSupprimer") Boolean estSupprimer);
	
	@Query("select s from AaronSequence s where s.estSupprimer = :estSupprimer AND  s.seq <> :seqSession AND s.seq = :seq   GROUP BY s.idSequence ORDER BY dateCreation DESC")
	AaronSequence findSequenceModifBySeq(@Param("seq") String seq, @Param("seqSession") String seqSession, @Param("estSupprimer") Boolean estSupprimer);
	

}
