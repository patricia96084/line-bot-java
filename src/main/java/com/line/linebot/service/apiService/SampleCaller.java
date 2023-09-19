package com.line.linebot.service.apiService;

import java.util.Arrays;
import java.util.List;

import com.line.linebot.interfaces.apiCaller.SystemCaller;

public class SampleCaller implements SystemCaller {
	private final String systemName = "測試系統";
	private List<String> nicknames;
	
	private List<String> apiActions;
	
	public SampleCaller() {
		this.nicknames = Arrays.asList("測試系統", "Sample system", "test system");
		this.apiActions = Arrays.asList("系統狀態", "批次情形", "運作機台", "近期錯誤訊息", "其他選項之類的");
	}

	@Override
	public List<String> getAPIActions() {
		return apiActions;
	}

	@Override
	public String callApi(String APIAction) {
		return String.format("測試系統的%s一切安好", APIAction);
	}

	@Override
	public List<String> getSystemNickname() {
		return nicknames;
	}

	public String getSystemName() {
		return systemName;
	}

}
