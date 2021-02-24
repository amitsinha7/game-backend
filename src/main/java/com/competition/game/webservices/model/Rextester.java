package com.competition.game.webservices.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Rextester")
public class Rextester implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@JsonProperty("Warnings")
	@Column(name = "Warnings")
	public String warnings;

	@JsonProperty("Errors")
	@Column(name = "Errors")
	public String errors;

	@JsonProperty("Result")
	@Column(name = "Result")
	public String result;

	@JsonProperty("Stats")
	@Column(name = "Stats")
	public String stats;

	@JsonProperty("Files")
	@Column(name = "Files")
	public String files;

	@JsonProperty("NotLoggedIn")
	@Column(name = "NotLoggedIn")
	public boolean notLoggedIn;

	public String getWarnings() {
		return warnings;
	}

	public void setWarnings(String warnings) {
		this.warnings = warnings;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getStats() {
		return stats;
	}

	public void setStats(String stats) {
		this.stats = stats;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public boolean isNotLoggedIn() {
		return notLoggedIn;
	}

	public void setNotLoggedIn(boolean notLoggedIn) {
		this.notLoggedIn = notLoggedIn;
	}

	public Rextester(Long id, String warnings, String errors, String result, String stats, String files,
			boolean notLoggedIn) {
		super();
		this.id = id;
		this.warnings = warnings;
		this.errors = errors;
		this.result = result;
		this.stats = stats;
		this.files = files;
		this.notLoggedIn = notLoggedIn;
	}

	public Rextester() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errors == null) ? 0 : errors.hashCode());
		result = prime * result + ((files == null) ? 0 : files.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (notLoggedIn ? 1231 : 1237);
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
		result = prime * result + ((stats == null) ? 0 : stats.hashCode());
		result = prime * result + ((warnings == null) ? 0 : warnings.hashCode());
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
		Rextester other = (Rextester) obj;
		if (errors == null) {
			if (other.errors != null)
				return false;
		} else if (!errors.equals(other.errors))
			return false;
		if (files == null) {
			if (other.files != null)
				return false;
		} else if (!files.equals(other.files))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (notLoggedIn != other.notLoggedIn)
			return false;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		if (stats == null) {
			if (other.stats != null)
				return false;
		} else if (!stats.equals(other.stats))
			return false;
		if (warnings == null) {
			if (other.warnings != null)
				return false;
		} else if (!warnings.equals(other.warnings))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rextester [id=" + id + ", warnings=" + warnings + ", errors=" + errors + ", result=" + result
				+ ", stats=" + stats + ", files=" + files + ", notLoggedIn=" + notLoggedIn + "]";
	}

}
