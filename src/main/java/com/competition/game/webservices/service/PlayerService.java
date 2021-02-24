package com.competition.game.webservices.service;

import com.competition.game.webservices.api.v1.PlayerRequest;
import com.competition.game.webservices.exception.CustomException;
import com.competition.game.webservices.exception.RecordNotFoundException;
import com.competition.game.webservices.model.Player;

public interface PlayerService {

	Player validateAndCreatePlayer(PlayerRequest playerRequest) throws CustomException;

	boolean validatePlayerNickName(String nickName) throws RecordNotFoundException;

	Player getPlayer(String nickName) throws RecordNotFoundException;

}
