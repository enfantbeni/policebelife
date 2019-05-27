package com.belife.policemanager.model.aaronrepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.belife.policemanager.model.aaronentity.AaronAgent;
import com.belife.policemanager.model.entity.Commercial;

public interface AaronAgentRepository extends JpaRepository<AaronAgent, String> {
	
	@Query("select c from Commercial c where c.codeAgent = :codeAgent")
	Commercial findAgentByCodeAgent(@Param("codeAgent") String codeAgent);
	
	@Query("select codeAgent from Commercial c where c.codeAgence = :codeAgence")
	List<String> findAllCodeAgentByCodeAgence(@Param("codeAgence") String codeAgence);
	
	@Query("select codeAgent from Commercial c ")
	List<String> findAllCodeAgent();
	
	@Query("select nomAgent from Commercial c where c.codeAgence = :codeAgence")
	List<String> findAllNomAgentByCodeAgence(@Param("codeAgence") String codeAgence);
		
	@Query("select c from Commercial c")
	List<Commercial> findAllAgents();
	
	
	@Query("select c from Commercial c ")
	Page<Commercial> findAllAgentsPage(Pageable pageable);

}
