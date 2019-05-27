package com.belife.policemanager.model.aaronrepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.aaronentity.AaronUtilisateur;

public interface AaronUtilisateurRepository extends JpaRepository<AaronUtilisateur, Integer>{
	
	@Query("select u from AaronUtilisateur u where u.password = :password")
	AaronUtilisateur findByPassword(@Param("password") String password);
	
	
	@Query("select u from AaronUtilisateur u where u.nomEtPrenom = :nomEtPrenom AND u.idUtilisateur<> :idUtilisateur")
	AaronUtilisateur findUtilisateurByNomPrenomModif(@Param("nomEtPrenom") String nomEtPrenom, @Param("idUtilisateur") Integer idUtilisateur);
	
	
	@Query("select fonction from AaronUtilisateur u where u.identifiant = :identifiant ")
	String fonctionUtilisateur(@Param("identifiant") String identifiant);
	
	
	@Query("select u from AaronUtilisateur u where u.identifiant = :identifiant ")
	AaronUtilisateur findByIdentifiant(@Param("identifiant") String identifiant);
	
	@Query("select u from AaronUtilisateur u where u.identifiant = :identifiant AND u.idUtilisateur<> :idUtilisateur")
	AaronUtilisateur findUtilisateurByIdentifiantModif(@Param("identifiant") String identifiant, @Param("idUtilisateur") Integer idUtilisateur);
	
	@Query("select u from AaronUtilisateur u  ORDER BY dateCreation DESC ")
	List<AaronUtilisateur> findAllUtilisateur();
	
	@Query("select idUtilisateur from AaronUtilisateur u where u.identifiant = :identifiant")
	Integer  findIdUtilisateur(@Param("identifiant") String identifiant);
	
	
	@Query(" select u from AaronUtilisateur u ")
	Page<AaronUtilisateur> findAllUtilisateurPage( Pageable pageable);

}
