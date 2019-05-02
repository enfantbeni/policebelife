package com.belife.policemanager.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.entity.Agence;
import com.belife.policemanager.model.entity.Client;
import com.belife.policemanager.model.entity.Sequence;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	@Query("select c from Client c where c.estSupprimer = :estSupprimer AND c.estSupprimer=false")
	List<Client> findAllClients(@Param("estSupprimer") Boolean estSupprimer);
	
	@Query("select c from Client c where c.idClient = :idClient AND c.estSupprimer=false")
	Client findClientById(@Param("idClient") Integer idClient);
																																																																
	@Query("select c from Client c where c.estSupprimer = :estSupprimer AND c.idAgence= :idAgence order by c.dateCreation desc")
	Page<Client> findAllClientsPage(@Param("estSupprimer") Boolean estSupprimer,@Param("idAgence") Agence idAgence , Pageable pageable);	
	
	@Query("select numeroPolice from Client c where c.idSequence = :idSequence AND c.estSupprimer=false ORDER BY dateCreation DESC")
	List<String> findAllNumeroPolicesByIdSequence(@Param("idSequence") Sequence idSequence);
	
	@Query("select c from Client c where c.numeroPolice = :numeroPolice AND c.estSupprimer=false")
	Client findClientByNumeroPolice(@Param("numeroPolice") String numeroPolice);
	
	
}
																													
																																				
																																					