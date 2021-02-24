package com.competition.game.webservices.api.v1;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RextesterRequest {

	@NotEmpty
	private String nickName;

	@NotEmpty
	private String languageName;

	@NotEmpty
	private String program;

	@NotNull
	private int preLoadedTaskId;

	@NotEmpty
	private String description;

	private String output;

	private String input;

	private String compilerArgs;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getCompilerArgs() {
		return compilerArgs;
	}

	public void setCompilerArgs(String compilerArgs) {
		this.compilerArgs = compilerArgs;
	}

	public RextesterRequest() {
	}

	public int getPreLoadedTaskId() {
		return preLoadedTaskId;
	}

	public void setPreLoadedTaskId(int preLoadedTaskId) {
		this.preLoadedTaskId = preLoadedTaskId;
	}

	public RextesterRequest(@NotEmpty String nickName, @NotEmpty String languageName, @NotEmpty String program,
			@NotEmpty int preLoadedTaskId, @NotEmpty String description, @NotEmpty String output, String input,
			String compilerArgs) {
		super();
		this.nickName = nickName;
		this.languageName = languageName;
		this.program = program;
		this.preLoadedTaskId = preLoadedTaskId;
		this.description = description;
		this.output = output;
		this.input = input;
		this.compilerArgs = compilerArgs;
	}

	public RextesterRequest(@NotEmpty String program, @NotEmpty String description, @NotEmpty String output,
			String input, String compilerArgs) {
		super();
		this.program = program;
		this.description = description;
		this.output = output;
		this.input = input;
		this.compilerArgs = compilerArgs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compilerArgs == null) ? 0 : compilerArgs.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((input == null) ? 0 : input.hashCode());
		result = prime * result + ((output == null) ? 0 : output.hashCode());
		result = prime * result + ((program == null) ? 0 : program.hashCode());
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
		RextesterRequest other = (RextesterRequest) obj;
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
		if (output == null) {
			if (other.output != null)
				return false;
		} else if (!output.equals(other.output))
			return false;
		if (program == null) {
			if (other.program != null)
				return false;
		} else if (!program.equals(other.program))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RextesterRequest [nickName=" + nickName + ", languageName=" + languageName + ", program=" + program
				+ ", preLoadedTaskId=" + preLoadedTaskId + ", description=" + description + ", output=" + output
				+ ", input=" + input + ", compilerArgs=" + compilerArgs + "]";
	}

}