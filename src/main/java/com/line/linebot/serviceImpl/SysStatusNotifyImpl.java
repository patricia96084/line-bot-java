package com.line.linebot.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.line.linebot.service.NotifyService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysStatusNotifyImpl extends NotifyService {

	public SysStatusNotifyImpl() {
		super();
	}

	public ResponseEntity<String> postSysException(String errorCode, List<String> groupAuths) {
		List<String> msgParts = splitMsg(errorCode);
		for (String groupAuth : groupAuths) {
			for (String msg : msgParts) {
				ResponseEntity<String> response = postMsg(msg, groupAuth);
				postResponseLogging(response);
			}
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	public List<String> splitMsg(String errorCode) {
	    // Give the list the right capacity to start with. You could use an array
	    // instead if you wanted.
		int size = 999;
	    List<String> ret = new ArrayList<String>((errorCode.length() + size - 1) / size);

	    for (int start = 0; start < errorCode.length(); start += size) {
	        ret.add(errorCode.substring(start, Math.min(errorCode.length(), start + size)));
	    }
	    return ret;
	}
}
