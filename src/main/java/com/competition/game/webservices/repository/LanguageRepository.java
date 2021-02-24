package com.competition.game.webservices.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.competition.game.webservices.model.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {

	@Query("SELECT u FROM Language u WHERE u.name = ?1")
	Optional<Language> findLanguageByName(String languageName);

}