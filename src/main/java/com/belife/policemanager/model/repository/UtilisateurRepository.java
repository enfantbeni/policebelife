package com.belife.policemanager.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.belife.policemanager.model.entity.Societe;
import com.belife.policemanager.model.entity.Utilisateur;



@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
	
	@Query("select u from Utilisateur u where u.password = :password")
	Utilisateur findByPassword(@Param("password") String password);
	
	
	@Query("select u from Utilisateur u where u.nomEtPrenom = :nomEtPrenom AND u.idUtilisateur<> :idUtilisateur")
	Utilisateur findUtilisateurByNomPrenomModif(@Param("nomEtPrenom") String nomEtPrenom, @Param("idUtilisateur") Integer idUtilisateur);
	
	
	@Query("select fonction from Utilisateur u where u.identifiant = :identifiant ")
	String fonctionUtilisateur(@Param("identifiant") String identifiant);
	
	
	@Query("select u from Utilisateur u where u.identifiant = :identifiant ")
	Utilisateur findByIdentifiant(@Param("identifiant") String identifiant);
	
	@Query("select u from Utilisateur u where u.identifiant = :identifiant AND u.idUtilisateur<> :idUtilisateur")
	Utilisateur findUtilisateurByIdentifiantModif(@Param("identifiant") String identifiant, @Param("idUtilisateur") Integer idUtilisateur);
	
	@Query("select u from Utilisateur u  ORDER BY dateCreation DESC ")
	List<Utilisateur> findAllUtilisateur();
	
	@Query("select idUtilisateur from Utilisateur u where u.identifiant = :identifiant")
	Integer  findIdUtilisateur(@Param("identifiant") String identifiant);
	
	
	@Query(" select u from Utilisateur u ")
	Page<Utilisateur> findAllUtilisateurPage( Pageable pageable);
	
}