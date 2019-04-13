package com.belife.policemanager.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belife.policemanager.model.entity.Roles;
import com.belife.policemanager.model.entity.UtilisateurRoles;



@Repository
public interface UtilisateurModuleRepository extends JpaRepository<Roles, Integer> {

}
