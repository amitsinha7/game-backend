package com.competition.game.webservices.constant;

import java.util.HashMap;
import java.util.Map;

public final class Constants {
	public static final String STATS = "Stats";
	public static final String FILES = "Files";
	public static final String RESULT = "Result";
	public static final String RESPONSE = "response";
	public static final String WARNINGS = "Warnings";
	public static final String ERRORS = "Errors";
	public static final String NOT_LOGGED_IN = "NotLoggedIn";
	
	public static final Map<String, String> errorStore = new HashMap<String, String>();

	static {
		errorStore.put("110001", "Something went wrong, please try again later.");
		errorStore.put("110002", "Bad request, please check request.");
		errorStore.put("110003", "IOException Occured");
		errorStore.put("110004", "JPA Transaction failed Try After Some Time");
		
		
		errorStore.put("220001", "User Already Present .. Please try with other deatils");
		errorStore.put("220002", "Something Wrong On Submission");
	}
}
