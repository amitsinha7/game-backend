package com.competition.game.webservices.service;

import java.util.List;

import com.competition.game.webservices.exception.RecordNotFoundException;
import com.competition.game.webservices.model.TaskStatus;

public interface TaskStatusService {

	List<TaskStatus> getTasksAlreadyPerformed(String nickName, String languageName);

	TaskStatus createOrUpdateTaskStatus(TaskStatus taskStatus);

	List<TaskStatus> findAllTaskStatus() throws RecordNotFoundException;

}
