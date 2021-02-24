package com.competition.game.webservices.service;

import java.util.List;

import com.competition.game.webservices.exception.RecordNotFoundException;
import com.competition.game.webservices.model.PreLoadedTask;

public interface PreLoadedTaskService {

	List<PreLoadedTask> getTasksForLanguageName(String languageName) throws RecordNotFoundException;

	PreLoadedTask getPreLoadedTask(int preLoadedTaskId) throws RecordNotFoundException;

	void createTask(PreLoadedTask preLoadedTask);

}
