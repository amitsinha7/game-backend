package com.competition.game.webservices.service.impl;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.competition.game.webservices.exception.CustomException;
import com.competition.game.webservices.model.Language;
import com.competition.game.webservices.model.Player;
import com.competition.game.webservices.model.PreLoadedTask;
import com.competition.game.webservices.model.Rextester;
import com.competition.game.webservices.model.TaskStatus;
import com.competition.game.webservices.repository.RextesterRepository;
import com.competition.game.webservices.service.RextesterService;
import com.competition.game.webservices.service.TaskStatusService;

@Service
public class RextesterServiceImpl implements RextesterService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final RestTemplate restTemplate;

	@Value("${url.rextester}")
	private String url;

	@Autowired
	RextesterRepository rextesterRepository;

	@Autowired
	TaskStatusService taskStatusService;

	public RextesterServiceImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(60))
				.setReadTimeout(Duration.ofSeconds(60)).build();

	}

	protected HttpHeaders createHttpHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
		// httpHeaders.set("Authorization", JwtToken.getJwtToken());
		return httpHeaders;
	}

	@Override
	public void submitChallenge(String program, Language lang, Player player, PreLoadedTask preLoadedTask)
			throws CustomException {

		logger.debug("submitChallenge Of RextesterServiceImpl");
		TaskStatus taskStatus = new TaskStatus();
		LinkedMultiValueMap<String, Object> request = new LinkedMultiValueMap<>();
		Rextester rextester = null;
		Rextester rextesterResponse = null;

		try {
			taskStatus.setLanguage(lang);
			taskStatus.setPlayer(player);
			taskStatus.setPreLoadedTask(preLoadedTask);
			taskStatus.setStatus("CREATED");
			taskStatus = this.taskStatusService.createOrUpdateTaskStatus(taskStatus);

			request.add("LanguageChoice", lang.getNumber());
			request.add("Program", program);
			if (preLoadedTask.getInput() != null && !preLoadedTask.getInput().isEmpty()) {
				request.add("Input", preLoadedTask.getInput());
			}
			if (preLoadedTask.getCompilerArgs() != null && !preLoadedTask.getCompilerArgs().isEmpty()) {
				request.add("CompilerArgs", preLoadedTask.getCompilerArgs());
			}
			HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(request,
					this.createHttpHeaders());
			rextesterResponse = this.restTemplate.postForObject(url, entity, Rextester.class);
			rextester = this.rextesterRepository.save(rextesterResponse);
			if (rextester.getResult().equals(taskStatus.getPreLoadedTask().getOutput())) {
				taskStatus.setStatus("COMPLETED");
				taskStatus = this.taskStatusService.createOrUpdateTaskStatus(taskStatus);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CustomException("220002");
		}
	}

}
