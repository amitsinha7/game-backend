package com.competition.game.webservices.service.impl;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.game.webservices.exception.RecordNotFoundException;
import com.competition.game.webservices.model.Language;
import com.competition.game.webservices.repository.LanguageRepository;
import com.competition.game.webservices.service.LanguageService;

@Service
public class LanguageServiceImpl implements LanguageService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LanguageRepository languagesRepository;

	@Override
	public List<Language> getAllLanguages() {
		logger.debug("getAllEmployees method started");

		List<Language> languageList = languagesRepository.findAll();
		if (languageList.size() > 0)
			return languageList;
		else
			return new ArrayList<>();
	}

	@Override
	public Language getLanguage(String languageName) throws RecordNotFoundException {
		logger.debug("getLanguage method started {}", languageName);

		Language language = languagesRepository.findLanguageByName(languageName).orElse(null);

		if (nonNull(language))
			return language;
		else
			throw new RecordNotFoundException("No language record exist for languageChoice provided");
	}

	@Override
	public boolean validateLanguageName(String languageName) throws RecordNotFoundException {
		logger.debug("validateLanguage method started {}", languageName);

		Language language = languagesRepository.findLanguageByName(languageName).orElse(null);
		if (nonNull(language))
			return true;
		else
			throw new RecordNotFoundException("No language record exist ... Try with different language");
	}

}
