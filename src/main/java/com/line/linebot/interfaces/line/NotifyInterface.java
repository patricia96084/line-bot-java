package com.line.linebot.interfaces.line;

import org.springframework.http.ResponseEntity;

public interface NotifyInterface {

	ResponseEntity<String> postMsg(String msg, String... groupAuths);
}
