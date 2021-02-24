package com.competition.game.webservices.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

@Entity
@Table(name = "taskStatus", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "language_number", "Nick_Name", "preLoadedTaskId" }) })
public class TaskStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int taskStatusId;

	@ManyToOne
	@JoinColumn(name = "language_number")
	private Language language;

	@ManyToOne
	@JoinColumn(name = "Nick_Name")
	private Player player;

	@ManyToOne
	@JoinColumn(name = "preLoadedTaskId")
	private PreLoadedTask preLoadedTask;

	@Version
	@Column(name = "CREATION_TIMESTAMP")
	private Calendar updateMadified;

	@Column(name = "CREATION_TIMESTAMP", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Calendar dateCreated;

	@Column(name = "status")
	private String status;

	public int getTaskStatusId() {
		return taskStatusId;
	}

	public void setTaskStatusId(int taskStatusId) {
		this.taskStatusId = taskStatusId;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public PreLoadedTask getPreLoadedTask() {
		return preLoadedTask;
	}

	public void setPreLoadedTask(PreLoadedTask preLoadedTask) {
		this.preLoadedTask = preLoadedTask;
	}

	public Calendar getUpdateMadified() {
		return updateMadified;
	}

	public void setUpdateMadified(Calendar updateMadified) {
		this.updateMadified = updateMadified;
	}

	public Calendar getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Calendar dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TaskStatus [taskStatusId=" + taskStatusId + ", language=" + language + ", player=" + player
				+ ", preLoadedTask=" + preLoadedTask + ", updateMadified=" + updateMadified + ", dateCreated="
				+ dateCreated + ", status=" + status + "]";
	}

	public TaskStatus(int taskStatusId, Language language, Player player, PreLoadedTask preLoadedTask,
			Calendar updateMadified, Calendar dateCreated, String status) {
		super();
		this.taskStatusId = taskStatusId;
		this.language = language;
		this.player = player;
		this.preLoadedTask = preLoadedTask;
		this.updateMadified = updateMadified;
		this.dateCreated = dateCreated;
		this.status = status;
	}

	public TaskStatus() {
		super();
	}

	public TaskStatus(int taskStatusId, Calendar updateMadified, Calendar dateCreated, String status) {
		super();
		this.taskStatusId = taskStatusId;
		this.updateMadified = updateMadified;
		this.dateCreated = dateCreated;
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + taskStatusId;
		result = prime * result + ((updateMadified == null) ? 0 : updateMadified.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskStatus other = (TaskStatus) obj;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (taskStatusId != other.taskStatusId)
			return false;
		if (updateMadified == null) {
			if (other.updateMadified != null)
				return false;
		} else if (!updateMadified.equals(other.updateMadified))
			return false;
		return true;
	}

}
