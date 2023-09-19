package com.line.linebot.service.apiService;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.line.linebot.interfaces.apiCaller.SystemCaller;

@Service
public class CallerMapper {
	private List<SystemCaller> systemList;
	
	public CallerMapper() {
		systemList = Arrays.asList(new SampleCaller());
	}

	public SystemCaller getCaller(String systemName) {
		if (systemName != "") {
			switch (systemName) {
			default:
				return new SampleCaller();
			}
		} else {
			return null;
		}
	}

	public List<SystemCaller> getSystemList() {
		return systemList;
	}
}
