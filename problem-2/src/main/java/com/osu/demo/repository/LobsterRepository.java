package com.osu.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.osu.demo.api.model.LobsterInfo;
import com.osu.demo.model.Lobster;

/**
 * The Interface LobsterRepository.
 */
@Repository
public interface LobsterRepository extends JpaRepository<Lobster, Integer>{

	/**
	 * Find all lobster.
	 *
	 * @return the {@code List<LobsterInfo>}
	 */
	@Query("select new com.osu.demo.api.model.LobsterInfo(l.lobsterId,l.name, l.description, l.kind) from Lobster l ORDER BY l.lobsterId")
	List<LobsterInfo> findAllLobster();
	
	/**
	 * Find lobster by id.
	 *
	 * @param lobsterId the {@code Integer}
	 * @return the {@code LobsterInfo}
	 */
	@Query("select new com.osu.demo.api.model.LobsterInfo(l.lobsterId,l.name, l.description, l.kind) " +
	           "from Lobster l where l.lobsterId = :lobsterId")
	LobsterInfo findLobsterById(@Param("lobsterId") Integer lobsterId);
	
	/**
	 * Exists lobster by name ignore case.
	 *
	 * @param name the {@code String}
	 * @return true, if successful
	 */
	boolean existsLobsterByNameIgnoreCase(String name);

}
