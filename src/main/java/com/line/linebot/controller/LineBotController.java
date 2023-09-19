package com.line.linebot.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.line.linebot.serviceImpl.SysStatusBotServiceImpl;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.*;
import com.linecorp.bot.parser.*;

@RestController
@RequestMapping(value = "/services")
public class LineBotController {
	@Value("${line.bot.channel-token}")
	private String CHANNEL_ACCESS_TOKEN;

	@Value("${line.bot.channel-secret}")
	private String CHANNEL_SECRET;

	@Autowired
	private SysStatusBotServiceImpl lineBotService;

	@RequestMapping(value = "/sysStatus")
	public ResponseEntity<String> responseToLine(@RequestHeader("X-Line-Signature") String X_Line_Signature,
			@RequestBody String requestBody) {
		
		// 在parser嘗試handle的時候，就會檢查是否從line post過來，所以就沒再檢查了
		LineSignatureValidator lineSignValid = new LineSignatureValidator(CHANNEL_SECRET.getBytes());
		WebhookParser webhookParser = new WebhookParser(lineSignValid);

		try {
			CallbackRequest requestFromLine = webhookParser.handle(X_Line_Signature, requestBody.getBytes());
			webhookInfoLogging(requestFromLine);
			return dealWithRequest(requestFromLine);
		} catch (WebhookParseException e) {
			// TODO: logging
			System.out.println(requestBody);
			lineBotService.exceptionHandler(e);
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			lineBotService.exceptionHandler(e);
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

	private ResponseEntity<String> dealWithRequest(CallbackRequest requestFromLine) {
		for (Event event : requestFromLine.getEvents()) {
			ReplyMessage response = lineBotService.HandleDiffEventMode(event);
			if (null != response) {
				ResponseEntity<String> replyStatus = lineBotService.reply(response);
				if (replyStatus.getStatusCode() == HttpStatus.NOT_FOUND) {
					// TODO: logging
					return replyStatus;
				}
			}
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	private void webhookInfoLogging(CallbackRequest requestFromLine) {
		// TODO: logging
		String botUserId = requestFromLine.getDestination();
		int eventAmount = requestFromLine.getEvents().size();
		String info = String.format("Linebot(%s) get %d events from Line", botUserId, eventAmount);
		System.out.println("INFO: " + info);
	}

}
