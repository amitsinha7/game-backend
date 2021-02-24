package com.competition.game.webservices.service.impl;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.competition.game.webservices.exception.RecordNotFoundException;
import com.competition.game.webservices.model.TaskStatus;
import com.competition.game.webservices.repository.TaskStatusRepository;
import com.competition.game.webservices.service.TaskStatusService;

@Service
public class TaskStatusServiceImpl implements TaskStatusService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	TaskStatusRepository taskStatusRepository;

	@Override
	public List<TaskStatus> getTasksAlreadyPerformed(String nickName, String languageName) {

		logger.debug("getTasksAlreadyPerformed started ");

		List<TaskStatus> tasks = taskStatusRepository.findTasksByNickNameAndLanguageName(nickName, languageName);

		if (tasks.size() > 0)
			return tasks;
		else
			return new ArrayList<>();

	}

	@Override
	@Transactional
	public TaskStatus createOrUpdateTaskStatus(TaskStatus taskStatus) {

		logger.debug("createOrUpdateTaskStatus method started {}", taskStatus);
		TaskStatus record = taskStatusRepository.findById(taskStatus.getTaskStatusId()).orElse(null);
		if (nonNull(record)) {
			logger.debug("update the record");
			record.setUpdateMadified(Calendar.getInstance());
			return taskStatusRepository.save(record);
		} else {
			logger.debug("save the record");
			taskStatus.setUpdateMadified(Calendar.getInstance());
			return taskStatusRepository.save(taskStatus);
		}

	}

	@Override
	public List<TaskStatus> findAllTaskStatus() throws RecordNotFoundException {
		logger.debug("getTopPlayer started ");

		List<TaskStatus> tasks = taskStatusRepository.findAll();

		if (tasks.size() > 0)
			return tasks;
		else
			throw new RecordNotFoundException("Top Player not exist in our system");
	}

}
