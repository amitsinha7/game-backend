package com.competition.game.webservices.service.impl;

import static java.util.Objects.nonNull;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.competition.game.webservices.exception.RecordNotFoundException;
import com.competition.game.webservices.model.PreLoadedTask;
import com.competition.game.webservices.repository.PreLoadedTaskRepository;
import com.competition.game.webservices.service.PreLoadedTaskService;

@Service
public class PreLoadedTaskServiceImpl implements PreLoadedTaskService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PreLoadedTaskRepository preLoadedTaskRepository;

	@Override
	public List<PreLoadedTask> getTasksForLanguageName(String languageName) throws RecordNotFoundException {
		logger.debug("getTasksForLanguageChoice started ");

		List<PreLoadedTask> preLoadedTasks = preLoadedTaskRepository.findTasksForLanguageName(languageName);

		if (preLoadedTasks.size() > 0)
			return preLoadedTasks;
		else
			throw new RecordNotFoundException("No Task Available For Language Selected");
	}

	@Override
	public PreLoadedTask getPreLoadedTask(int preLoadedTaskId) throws RecordNotFoundException {
		logger.debug("getTasksForLanguageChoice started ");

		PreLoadedTask preLoadedTask = preLoadedTaskRepository.findById(preLoadedTaskId).orElse(null);

		if (nonNull(preLoadedTask))
			return preLoadedTask;
		else
			throw new RecordNotFoundException("No Task record exist for given input ...  Try Again with other task");
	}

	@Override
	@Transactional
	public void createTask(PreLoadedTask preLoadedTask) {
		preLoadedTaskRepository.save(preLoadedTask);

	}

}
