package com.belife.policemanager.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.belife.policemanager.model.entity.Roles;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
	
//	@Query("select r from Roles u where u.password = :password AND u.estSupprimer=false")
//	Utilisateur findByPassword(@Param("password") String password);
//	
//	@Query("select u from Utilisateur u where u.identifiant = :identifiant AND u.estSupprimer=false")
//	Utilisateur findByIdentifiant(@Param("identifiant") String identifiant);
	
	
	
	@Query("select r from Roles r" )
	List<Roles> findAllRoles();

}
