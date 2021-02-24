package com.competition.game.webservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.competition.game.webservices.model.PreLoadedTask;

@Repository
public interface PreLoadedTaskRepository extends JpaRepository<PreLoadedTask, Integer> {

	@Query("FROM PreLoadedTask g where g.language.name = :name")
	List<PreLoadedTask> findTasksForLanguageName(@Param("name") String name);
}