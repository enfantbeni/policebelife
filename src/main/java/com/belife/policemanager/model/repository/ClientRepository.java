package com.belife.policemanager.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	@Query("select c from Client c where c.status = :status ")
	List<Client> findAllClients(@Param("status") Boolean estSupprimer);
	
	@Query("select c from Client c where c.idClient = :idClient AND c.status= :status")
	Client findClientById(@Param("idClient") Integer idClient, @Param("status") String status);
																																																																
	@Query("select c from Client c where c.status = :status AND c.codeAgence= :codeAgence order by c.dateCreation desc")
	Page<Client> findAllClientsPage(@Param("status") String status,@Param("codeAgence") String codeAgence , Pageable pageable);	
	
	@Query("select numeroPolice from Client c where c.sequencePolice = :sequencePolice AND c.status= :status ORDER BY dateCreation DESC")
	List<String> findAllNumeroPolicesByIdSequence(@Param("sequencePolice") String sequencePolice, @Param("status") String status);
	
	@Query("select c from Client c where c.numeroPolice = :numeroPolice AND c.status= :status")
	Client findClientByNumeroPolice(@Param("numeroPolice") String numeroPolice, @Param("status") String status);
	
	@Query("select c from Client c where c.status = :status AND c.numeroPolice = :numeroPolice AND c.produit = :produit AND c.genreAssure = :genreAssure AND c.nomAssure = :nomAssure AND  c.nomClient = :nomClient AND c.matriculeClient = :matriculeClient AND c.numeroCompte = :numeroCompte AND c.nomSource = :nomSource AND c.codeSource = :codeSource AND c.periodicite = :periodicite AND c.couverture = :couverture AND c.prime = :prime AND c.datePrelevement = :datePrelevement AND c.dateSoumission = :dateSoumission AND c.dateNaissance = :dateNaissance  AND c.profession = :profession AND c.employeur  = :employeur AND c.ville  = :ville AND c.adressePostale  = :adressePostale AND c.telephone1= :telephone1 AND c.telephone2= :telephone2 AND c.nomComPreContrat= :nomComPreContrat AND c.dateRealisation= :dateRealisation AND c.codeAgent= :codeAgent AND c.codeAgence= :codeAgence    ")
	Client findClientByAllParameters(@Param("status") String status, @Param("numeroPolice") String numeroPolice, @Param("produit") String produit, @Param("genreAssure") String genreAssure,@Param("nomAssure") String nomAssure,@Param("nomClient") String nomClient, @Param("matriculeClient") String matriculeClient,@Param("numeroCompte") String numeroCompte, @Param("nomSource") String nomSource, @Param("codeSource") String codeSource, @Param("periodicite") String periodicite, @Param("couverture") Long couverture,  @Param("prime") Long prime, @Param("datePrelevement") String datePrelevement, @Param("dateSoumission") String dateSoumission, @Param("dateNaissance") String dateNaissance, @Param("profession") String profession, @Param("employeur") String employeur, @Param("ville") String ville, @Param("adressePostale") String adressePostale, @Param("telephone1") String telephone1, @Param("telephone2") String telephone2,  @Param("nomComPreContrat") String nomComPreContrat,  @Param("dateRealisation") String dateRealisation, @Param("codeAgent") String codeAgent, @Param("codeAgence") String codeAgence );
	
	
}
																													
																																				
																																					