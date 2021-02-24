package com.competition.game.webservices.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "preLoadedTask", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "language_number", "preLoadedTaskId" }) })
public class PreLoadedTask implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "preLoadedTaskId")
	private int preLoadedTaskId;

	@ManyToOne
	@JoinColumn(name = "language_number")
	private Language language;

	@OneToMany(mappedBy = "preLoadedTask", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<TaskStatus> taskStatusList;

	@Column(name = "Description")
	@NotEmpty(message = "Task  Description is required")
	private String description;

	@Column(name = "Input")
	private String input;

	@Column(name = "Output")
	@NotEmpty(message = "Language number is required")
	private String output;

	@Column(name = "CompilerArgs")
	private String compilerArgs;

	public int getPreLoadedTaskId() {
		return preLoadedTaskId;
	}

	public void setPreLoadedTaskId(int preLoadedTaskId) {
		this.preLoadedTaskId = preLoadedTaskId;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Set<TaskStatus> getTaskStatusList() {
		return taskStatusList;
	}

	public void setTaskStatusList(Set<TaskStatus> taskStatusList) {
		this.taskStatusList = taskStatusList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getCompilerArgs() {
		return compilerArgs;
	}

	public void setCompilerArgs(String compilerArgs) {
		this.compilerArgs = compilerArgs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compilerArgs == null) ? 0 : compilerArgs.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((input == null) ? 0 : input.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((output == null) ? 0 : output.hashCode());
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
		PreLoadedTask other = (PreLoadedTask) obj;
		if (compilerArgs == null) {
			if (other.compilerArgs != null)
				return false;
		} else if (!compilerArgs.equals(other.compilerArgs))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (input == null) {
			if (other.input != null)
				return false;
		} else if (!input.equals(other.input))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (output == null) {
			if (other.output != null)
				return false;
		} else if (!output.equals(other.output))
			return false;
		return true;
	}

	public PreLoadedTask(int preLoadedTaskId, Language language, Set<TaskStatus> taskStatusList,
			@NotEmpty(message = "Task  Description is required") String description,
			@NotEmpty(message = "Task  Input is required") String input,
			@NotEmpty(message = "Language number is required") String output, String compilerArgs) {
		super();
		this.preLoadedTaskId = preLoadedTaskId;
		this.language = language;
		this.taskStatusList = taskStatusList;
		this.description = description;
		this.input = input;
		this.output = output;
		this.compilerArgs = compilerArgs;
	}

	public PreLoadedTask(int preLoadedTaskId, @NotEmpty(message = "Task  Description is required") String description,
			@NotEmpty(message = "Task  Input is required") String input,
			@NotEmpty(message = "Language number is required") String output, String compilerArgs) {
		super();
		this.preLoadedTaskId = preLoadedTaskId;
		this.description = description;
		this.input = input;
		this.output = output;
		this.compilerArgs = compilerArgs;
	}

	public PreLoadedTask() {
		super();

	}

	@Override
	public String toString() {
		return "PreLoadedTask [preLoadedTaskId=" + preLoadedTaskId + ", language=" + language + ", taskStatusList="
				+ taskStatusList + ", description=" + description + ", input=" + input + ", output=" + output
				+ ", compilerArgs=" + compilerArgs + "]";
	}

}
