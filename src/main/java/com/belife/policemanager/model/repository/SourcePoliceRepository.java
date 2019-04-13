package com.belife.policemanager.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.entity.SourcePolice;

public interface SourcePoliceRepository extends JpaRepository<SourcePolice, Integer> {
	
	@Query("select sp from SourcePolice sp where sp.source = :source AND sp.estSupprimer=false")
	SourcePolice findBySource(@Param("source") String source);
	
	@Query("select genrePolice from SourcePolice sp where sp.source = :source AND sp.estSupprimer=false")
	String findNature(@Param("source") String source);
	
	@Query("select source from SourcePolice sp where sp.genrePolice = :genrePolice AND sp.estSupprimer=false")
	List<String> findSourcePrelements(@Param("genrePolice") String genrePolice);
	
	@Query("select sp from SourcePolice sp where sp.genrePolice = :genrePolice AND sp.estSupprimer=false")
	SourcePolice findByNature(@Param("genrePolice") String genrePolice);
	
	@Query("select sp from SourcePolice sp where sp.estSupprimer = :estSupprimer AND sp.estSupprimer=false")
	List<SourcePolice> findAllSourcePrelevement(@Param("estSupprimer") Boolean estSupprimer);

}
