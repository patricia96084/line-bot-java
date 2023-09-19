package com.line.linebot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class NotifyController {
	private final String NOTIFYAPI = "https://notify-api.line.me/api/notify";
	
	public NotifyController() {}
	
	@RequestMapping(value="/alert")
	public ResponseEntity<String> systemAlertException(){
		// TODO: know the structure of https
		String sysErrorCode = getSysErrorCode();
		for (String groupAuthorization : getGroupAuthorization()) {
			ResponseEntity<String> alertStatus = alertToGroup(groupAuthorization, sysErrorCode);
			// logging
			String groupName = getGroupName();
			if (alertStatus.getStatusCode() == HttpStatus.OK) {
				String info = "Successfully post to "+groupName;
				System.out.println("INFO: "+info);
			} else {
				String httpStatus = alertStatus.getStatusCode().toString();
				String warning = String.format("%s\nFailed to post to %s", httpStatus, groupName);
				System.err.println(warning);
			}
		}
		// TODO: should be put in try and catch, there must be some situation need to catch exceptions
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
	
	private String getSysErrorCode() {
		// TODO: get the exception detail from the request
		String sysErrorCode = "";
		return sysErrorCode;
	}
	
	private String getGroupName() {
		String groupName = "";
		// TODO: get group name
		return groupName;
	}
	
	private List<String> getGroupAuthorization() {
		List<String> groupAuthorizations = new ArrayList<String>();
		// TODO: get authorization code by request header and the list of which group need to be called
		return groupAuthorizations;
	}
	
	public ResponseEntity<String> alertToGroup(String groupAuthorization, String sysErrorCode) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(groupAuthorization);
		String url = NOTIFYAPI + "?message=" + sysErrorCode;
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange(url,  HttpMethod.POST, request, String.class);
		return response;
	}
	
}
