package com.line.linebot.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.line.linebot.interfaces.line.NotifyInterface;

@Service
public class NotifyService implements NotifyInterface {
	private final String NOTIFY_API = "https://notify-api.line.me/api/notify";

	@Override
	public ResponseEntity<String> postMsg(String msg, String... groupAuths) {
		for (String groupAuth : groupAuths) {
			ResponseEntity<String> response = postMsg(msg, groupAuth);
			postResponseLogging(response);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	public void postResponseLogging(ResponseEntity<String> response) {
		switch (response.getStatusCodeValue()) {
		case 200:
			// TODO: logging
			String info = "Message sent successfully";
			System.out.println("INFO: "+info);
			break;
		case 400:
		case 500:
		case 401:
		default:
			// TODO: logging
			String error = String.format("%d %s", response.getStatusCodeValue(), response.getBody());
			System.out.println("ERROR: "+error);
		}
	}

	public ResponseEntity<String> postMsg(String msg, String groupAuth) {
		if (msg.length() >= 1000) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(groupAuth);
		String url = NOTIFY_API + "?message=" + msg;
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
		return response;
	}
}
