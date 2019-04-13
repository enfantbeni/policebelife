package com.belife.policemanager.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.belife.policemanager.model.entity.Agence;
import com.belife.policemanager.model.entity.Utilisateur;



@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
	
	@Query("select u from Utilisateur u where u.password = :password AND u.estSupprimer=false")
	Utilisateur findByPassword(@Param("password") String password);
	
	@Query("select fonction from Utilisateur u where u.identifiant = :identifiant AND u.estSupprimer=false")
	String fonctionUtilisateur(@Param("identifiant") String identifiant);
	
	@Query("select u from Utilisateur u where u.identifiant = :identifiant AND u.estSupprimer=false")
	Utilisateur findByIdentifiant(@Param("identifiant") String identifiant);
	
	@Query("select u from Utilisateur u where u.estSupprimer = :estSupprimer AND u.estSupprimer=false")
	List<Utilisateur> findAllUtilisateur(@Param("estSupprimer") Boolean estSupprimer);
	
	@Query("select idUtilisateur from Utilisateur u where u.identifiant = :identifiant AND u.estSupprimer=false")
	Integer  findIdUtilisateur(@Param("identifiant") String identifiant);
	
	
	
	
}