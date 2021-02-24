package com.competition.game.webservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.competition.game.webservices.model.TaskStatus;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatus, Integer> {

	@Query("FROM TaskStatus ts where ts.player.nickName = :nickName and ts.language.name = :name ")
	List<TaskStatus> findTasksByNickNameAndLanguageName(@Param("nickName") String nickName, @Param("name") String name);

}