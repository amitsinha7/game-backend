package com.competition.game.webservices.service;

import com.competition.game.webservices.exception.CustomException;
import com.competition.game.webservices.model.Language;
import com.competition.game.webservices.model.Player;
import com.competition.game.webservices.model.PreLoadedTask;

public interface RextesterService {

	void submitChallenge(String program, Language lang, Player player, PreLoadedTask preLoadedTask)
			throws CustomException;
}
