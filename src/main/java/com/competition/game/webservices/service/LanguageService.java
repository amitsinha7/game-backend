package com.competition.game.webservices.service;

import java.util.List;

import com.competition.game.webservices.exception.RecordNotFoundException;
import com.competition.game.webservices.model.Language;

public interface LanguageService {

	List<Language> getAllLanguages();

	boolean validateLanguageName(String languageName) throws RecordNotFoundException;

	Language getLanguage(String languageName) throws RecordNotFoundException;

}
