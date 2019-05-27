package com.belife.policemanager.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.entity.Connexion;
import com.belife.policemanager.model.entity.Utilisateur;

public interface ConnexionRepository extends JpaRepository<Connexion, Integer>{
	
	@Query("select c from Connexion c where c.idUtilisateur = :idUtilisateur")
	List<Connexion> findByIdentifiant(@Param("idUtilisateur") Utilisateur idUtilisateur);

}
