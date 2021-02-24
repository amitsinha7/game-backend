package com.competition.game.webservices.endpoint.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.competition.game.webservices.api.v1.ErrorInfo;
import com.competition.game.webservices.api.v1.PlayerRequest;
import com.competition.game.webservices.api.v1.ResponseDTO;
import com.competition.game.webservices.api.v1.RextesterRequest;
import com.competition.game.webservices.constant.Constants;
import com.competition.game.webservices.exception.CustomException;
import com.competition.game.webservices.exception.RecordNotFoundException;
import com.competition.game.webservices.helper.Helper;
import com.competition.game.webservices.model.Language;
import com.competition.game.webservices.model.Player;
import com.competition.game.webservices.model.PreLoadedTask;
import com.competition.game.webservices.model.TaskStatus;
import com.competition.game.webservices.service.LanguageService;
import com.competition.game.webservices.service.PlayerService;
import com.competition.game.webservices.service.PreLoadedTaskService;
import com.competition.game.webservices.service.RextesterService;
import com.competition.game.webservices.service.TaskStatusService;

@RestController
@RequestMapping("${api.path}")
@CrossOrigin(origins = "${cross.origins}")
public class ChallengeController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LanguageService languagesService;

	@Autowired
	private RextesterService rextesterService;

	@Autowired
	private TaskStatusService taskStatusService;

	@Autowired
	PreLoadedTaskService preLoadedTaskService;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private Helper helper;

	private Random randomGenerator = new Random();

	// Constructor for Integration Testing
	public ChallengeController(LanguageService languagesService, RextesterService rextesterService,
			TaskStatusService taskStatusService, PlayerService playerService,
			PreLoadedTaskService preLoadedTaskService) {
		this.languagesService = languagesService;
		this.rextesterService = rextesterService;
		this.playerService = playerService;
		this.taskStatusService = taskStatusService;
		this.preLoadedTaskService = preLoadedTaskService;
	}

	// Create New Player
	@PostMapping("/createTask")
	public ResponseEntity<ResponseDTO> createTask(@RequestBody PreLoadedTask preLoadedTask) throws CustomException {

		ResponseDTO response = new ResponseDTO();
		try {
			this.preLoadedTaskService.createTask(preLoadedTask);
			response.setMessage("Created Task");
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setErrorInfo(getErrorInfo("110001"));
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ResponseDTO>(response, new HttpHeaders(), HttpStatus.CREATED);
	}

	// Create New Player
	@PostMapping("/createPlayer")
	public ResponseEntity<ResponseDTO> createPlayer(@Valid @RequestBody PlayerRequest playerRequest)
			throws CustomException {

		logger.debug("createPlayer method started {}", playerRequest);
		ResponseDTO response = new ResponseDTO();
		try {
			Player player = this.playerService.validateAndCreatePlayer(playerRequest);
			response.setPlayer(player);
		} catch (CustomException ce) {
			response.setErrorInfo(getErrorInfo(ce.getMessage()));
			if (ce.getMessage().equals("220001")) {
				return new ResponseEntity<ResponseDTO>(response, HttpStatus.CONFLICT);
			}
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setErrorInfo(getErrorInfo("110001"));
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ResponseDTO>(response, new HttpHeaders(), HttpStatus.CREATED);
	}

	// API to Get Top Player
	@GetMapping("/getTopPlayer")
	public ResponseEntity<ResponseDTO> getTopPlayer() {

		logger.debug("/v1/getTopPlayer method started");
		ResponseDTO response = new ResponseDTO();

		try {
			List<TaskStatus> completedTasks = this.taskStatusService.findAllTaskStatus();
			response.setTopPlayers(this.helper.getTopPlayer(completedTasks));
		} catch (RecordNotFoundException e) {
			logger.error(e.getMessage());
			ErrorInfo errorInfo = new ErrorInfo();
			errorInfo.setErrorMessage(e.getMessage());
			response.setErrorInfo(errorInfo);
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			response.setErrorInfo(getErrorInfo("110001"));
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ResponseDTO>(response, new HttpHeaders(), HttpStatus.OK);
	}

	// API to Get Random Task For a Player
	@GetMapping("/getRandomTaskForPlayer")
	public ResponseEntity<ResponseDTO> getRandomTaskForPlayer(@Valid @RequestParam String nickName,
			@Valid @RequestParam String languageName) {

		logger.debug("/v1/getRandomTaskForPlayer method started");
		ResponseDTO response = new ResponseDTO();
		List<PreLoadedTask> randomTasksForPlayer;
		try {
			if (this.playerService.validatePlayerNickName(nickName)
					&& this.languagesService.validateLanguageName(languageName)) {
				randomTasksForPlayer = this.helper.randomTasksForPlayer(nickName, languageName);
				int index = randomGenerator.nextInt(randomTasksForPlayer.size());
				response.setPreLoadedTask(this.helper.removeUnWantedData(randomTasksForPlayer.get(index)));
			}
		} catch (RecordNotFoundException e) {
			logger.error(e.getMessage());
			ErrorInfo errorInfo = new ErrorInfo();
			errorInfo.setErrorMessage(e.getMessage());
			response.setErrorInfo(errorInfo);
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			response.setErrorInfo(getErrorInfo("110001"));
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ResponseDTO>(response, new HttpHeaders(), HttpStatus.OK);
	}

	// API to submit challenges
	@PostMapping("/submitChallenge")
	public ResponseEntity<ResponseDTO> submitChallenges(@Valid @ModelAttribute RextesterRequest rextesterReq)
			throws CustomException {

		logger.debug("/v1/submitChallenge method started");
		ResponseDTO response = new ResponseDTO();

		try {

			Language lang = this.languagesService.getLanguage(rextesterReq.getLanguageName());
			Player player = this.playerService.getPlayer(rextesterReq.getNickName());
			PreLoadedTask preLoadedTask = this.preLoadedTaskService.getPreLoadedTask(rextesterReq.getPreLoadedTaskId());
			if (this.helper.validatePreLoadedTask(rextesterReq, preLoadedTask)) {
				this.rextesterService.submitChallenge(rextesterReq.getProgram(), lang, player, preLoadedTask);
				response.setMessage("Thank you for your active participation");
			} else {
				throw new RecordNotFoundException("No Task record Mismatch for given input");
			}

		} catch (RecordNotFoundException e) {
			logger.error(e.getMessage());
			ErrorInfo errorInfo = new ErrorInfo();
			errorInfo.setErrorMessage(e.getMessage());
			response.setErrorInfo(errorInfo);
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.NOT_FOUND);
		} catch (CustomException ce) {
			response.setErrorInfo(getErrorInfo(ce.getMessage()));
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.BAD_GATEWAY);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			response.setErrorInfo(getErrorInfo("110001"));
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ResponseDTO>(response, new HttpHeaders(), HttpStatus.OK);
	}

	// API to Get Top Player
	@GetMapping("/getAllLanguages")
	public ResponseEntity<ResponseDTO> getAllLanguages() {

		logger.debug("/v1/getAllLanguages method started");
		ResponseDTO response = new ResponseDTO();

		try {
			List<Language> languages = this.languagesService.getAllLanguages();
			List<Language> langs = new ArrayList<Language>();
			languages.forEach(lang -> {
				lang.setTasks(null);
				langs.add(lang);
			});
			if (languages.size() > 0) {
				response.setLanguages(languages);
			} else {
				throw new RecordNotFoundException("Language Unavailable");
			}
			response.setLanguages(langs);
		} catch (RecordNotFoundException e) {
			logger.error(e.getMessage());
			ErrorInfo errorInfo = new ErrorInfo();
			errorInfo.setErrorMessage(e.getMessage());
			response.setErrorInfo(errorInfo);
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			response.setErrorInfo(getErrorInfo("110001"));
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ResponseDTO>(response, new HttpHeaders(), HttpStatus.OK);
	}

	private ErrorInfo getErrorInfo(String errorCode) {
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(errorCode);
		errorInfo.setErrorMessage(Constants.errorStore.get(errorCode));
		return errorInfo;
	}
}
