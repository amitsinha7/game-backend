package com.competition.game.webservices.service.impl;

import static java.util.Objects.nonNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.competition.game.webservices.api.v1.PlayerRequest;
import com.competition.game.webservices.exception.CustomException;
import com.competition.game.webservices.exception.RecordNotFoundException;
import com.competition.game.webservices.model.Player;
import com.competition.game.webservices.repository.PlayerRepository;
import com.competition.game.webservices.service.PlayerService;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PlayerRepository playerRepository;

	@Override
	public Player validateAndCreatePlayer(PlayerRequest playerRequest) throws CustomException {

		logger.debug("validateAndCreatePlayer of PlayerServiceImpl");

		Player playerRet = null;

		try {
			playerRet = playerRepository.findById(playerRequest.getNickName()).orElse(null);
			if (nonNull(playerRet)) {
				throw new CustomException("110004");
			} else {
				Player p = new Player();
				p.setNickName(playerRequest.getNickName());
				p.setName(playerRequest.getName());
				if (playerRequest.getEmailId() != null) {
					p.setEmailId(playerRequest.getEmailId());
				}
				playerRet = playerRepository.save(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("220001");
		}
		return playerRet;
	}

	@Override
	public boolean validatePlayerNickName(String nickName) throws RecordNotFoundException {
		Player player = playerRepository.findById(nickName).orElse(null);
		if (nonNull(player))
			return true;
		else
			throw new RecordNotFoundException("No player exist for given nick name ... Try with different Player details");
	}

	@Override
	public Player getPlayer(String nickName) throws RecordNotFoundException {
		Player player = playerRepository.findById(nickName).orElse(null);
		if (nonNull(player))
			return player;
		else
			throw new RecordNotFoundException("No player exist for given nick name ... Try with different Player details");
	}
}
