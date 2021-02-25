package com.competition.game.webservices;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.competition.game.webservices.endpoint.v1.ChallengeController;
import com.competition.game.webservices.exception.CustomExceptionHandler;
import com.competition.game.webservices.model.Language;
import com.competition.game.webservices.model.TaskStatus;
import com.competition.game.webservices.repository.LanguageRepository;
import com.competition.game.webservices.repository.TaskStatusRepository;
import com.competition.game.webservices.service.LanguageService;
import com.competition.game.webservices.service.PlayerService;
import com.competition.game.webservices.service.RextesterService;
import com.competition.game.webservices.service.TaskStatusService;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(classes = GameApplication.class)
public class GameApplicationTests {

	@Autowired
	private LanguageService languagesService;

	@Autowired
	private LanguageRepository languagesRepository;

	@Autowired
	private RextesterService rextesterService;

	@Autowired
	private TaskStatusService taskService;
	
	@Autowired
	TaskStatusRepository taskRepository;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private CustomExceptionHandler exceptionTranslator;

	@Autowired
	private MappingJackson2HttpMessageConverter jacksonMessageConverter;

	private Language languages;

	private MockMvc restLanguagesMockMvc;

	@Before
	public void setup() {
		/*
		 * MockitoAnnotations.initMocks(false); ChallengeController challengeController
		 * = new ChallengeController(languagesService, rextesterService, taskService,
		 * playerService); this.restLanguagesMockMvc =
		 * MockMvcBuilders.standaloneSetup(challengeController)
		 * .setControllerAdvice(exceptionTranslator)
		 * .setConversionService(TestUtil.createFormattingConversionService())
		 * .setMessageConverters(jacksonMessageConverter).build();
		 */
	}

	@Before
	public void initTest() {
		this.languages = createEntity();
	}

	public static Language createEntity() {
		Language task = new Language();
		return task;
	}
	
	@Test
	public void generateData() throws Exception {
			
		List<Language> langs= languagesRepository.findAll();
		for (Language language : langs) {
			
			System.out.println(language.toString());
	
		}
	}
}
