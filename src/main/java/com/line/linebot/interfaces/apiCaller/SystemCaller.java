package com.line.linebot.interfaces.apiCaller;

import java.util.List;

public interface SystemCaller {
	public List<String> getAPIActions();

	public String callApi(String APIAction);

	public List<String> getSystemNickname();

	public String getSystemName();
}
