package com.line.linebot.serviceImpl;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.line.linebot.dao.UserAuthDao;
import com.line.linebot.dao.UserDao;
import com.line.linebot.interfaces.apiCaller.SystemCaller;
import com.line.linebot.service.LineBotService;
import com.line.linebot.service.apiService.CallerMapper;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.Button;
import com.linecorp.bot.model.message.flex.component.FlexComponent;
import com.linecorp.bot.model.message.flex.component.Image;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.profile.UserProfileResponse;
import com.linecorp.bot.model.response.BotApiResponse;

@Service
public class SysStatusBotServiceImpl extends LineBotService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private CallerMapper callerMapper;

	@Autowired
	private UserAuthDao userAuthDao;

	// @Autowired
	// private LineGroupDao lineGroupDao;

	@Value("${line.bot.channel-token}")
	private String CHANNEL_ACCESS_TOKEN;

	@Value("${line.bot.channel-secret}")
	private String CHANNEL_SECRET;

	private LineMessagingClient client;

	private Map<String, List<String>> systemList;

	public SysStatusBotServiceImpl() {
		super();
	}

	// might not be used because we can call method in Line package so as to check
	// if WebHook can be parse
	@Override
	public boolean checkPostFromLine(String requestBody, String X_Line_Signature) {
		String hashValue = "HmacSHA256";

		SecretKeySpec lineKey = new SecretKeySpec(CHANNEL_SECRET.getBytes(), hashValue);
		Mac mac;
		try {
			mac = Mac.getInstance(hashValue);
			mac.init(lineKey);
			byte[] source = requestBody.getBytes("UTF-8");
			String signature = Base64.encodeBase64String(mac.doFinal(source));
			if (signature.equals(X_Line_Signature)) {
				return true;
			}
		} catch (Exception e) {
			exceptionHandler(e);
		}
		return false;
	}

	@Override
	public String getDisplayName(String userId) {
		client = LineMessagingClient.builder(CHANNEL_ACCESS_TOKEN).build();
		CompletableFuture<UserProfileResponse> userProfileFuture = client.getProfile(userId);
		String displayName = "";
		try {
			UserProfileResponse userProfile = userProfileFuture.get();
			displayName = userProfile.getDisplayName();
		} catch (Exception e) {
			exceptionHandler(e);
		}
		return displayName;
	}

	// not checking if is approved user since we've checked as soon as receiving any
	// message
	public String getUserName(String userToken) {
		return userDao.getUserName(userToken);
	}

	@Override
	public ResponseEntity<String> reply(ReplyMessage replyMsg) {
		BotApiResponse botApiResponse;
		if (null == replyMsg) {
			return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
		}
		// TODO logging debug
		try {
			client = LineMessagingClient.builder(CHANNEL_ACCESS_TOKEN).build();
			botApiResponse = client.replyMessage(replyMsg).get();
		} catch (Exception e) {
			exceptionHandler(e);
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		System.out.println(botApiResponse);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@Override
	public ReplyMessage decideResponse(Event event) {
		// TODO: logging
		String info = String.format("accept %s", event.getClass().getSimpleName());
		System.out.println("INFO: " + info);

		if (isTextMessage(event)) {
			@SuppressWarnings("unchecked")
			MessageEvent<MessageContent> msgEvent = (MessageEvent<MessageContent>) event;
			if (isTextContent(msgEvent)) {
				@SuppressWarnings("unchecked")
				MessageEvent<TextMessageContent> textMsgEvent = (MessageEvent<TextMessageContent>) event;
				List<Message> response = replyToTextMsg(textMsgEvent);
				if (response.size() > 0) {
					ReplyMessage replyToText = new ReplyMessage(textMsgEvent.getReplyToken(), response);
					return replyToText;
				}
			}
			return replyDefaultResponse(msgEvent.getReplyToken());
		} else if (isPostback(event)) {
			PostbackEvent postbackEvent = (PostbackEvent) event;
			List<Message> response = replyToPostback(postbackEvent);
			if (response.size() > 0) {
				ReplyMessage replyPostback = new ReplyMessage(postbackEvent.getReplyToken(), response);
				return replyPostback;
			}
		}
		return null;
	}

	private boolean isTextMessage(Event event) {
		return event.getClass().equals(MessageEvent.class);
	}

	private boolean isTextContent(MessageEvent<MessageContent> msgEvent) {
		return msgEvent.getMessage().getClass().equals(TextMessageContent.class);
	}

	private boolean isPostback(Event event) {
		return event.getClass().equals(PostbackEvent.class);
	}

	/**
	 * 包裝無法辨識使用者意圖時的預設訊息
	 * 
	 * @param replyToken
	 * @return 預設訊息
	 */
	private ReplyMessage replyDefaultResponse(String replyToken) {
		ReplyMessage replyDefault = new ReplyMessage(replyToken, defaultResponse());
		return replyDefault;
	}

	/**
	 * 預設回覆的訊息
	 * 
	 * @return 預設回覆的訊息
	 */
	private TextMessage defaultResponse() {
		String defaultMsg = "很抱歉，系統不明白您的意思，或您詢問的系統尚未支援狀態查詢功能\n" + "請輸入「系統狀態」以獲得可查詢的系統清單，或告知您想查詢的系統。";
		TextMessage defaultResponse = createTextMsg(defaultMsg);
		return defaultResponse;
	}

	/**
	 * 接受到由自己從使用者聊天室發出的Postback event，解讀，產生該回的訊息
	 * 
	 * @param event
	 * @return 回覆Postback的訊息
	 */
	private List<Message> replyToPostback(PostbackEvent event) {
		List<Message> response = new ArrayList<Message>();
		String postbackParams = event.getPostbackContent().getData();
		String userId = event.getSource().getUserId();
		Map<String, String> paramMap = paramToMap(postbackParams);

		// TODO: logging
		String debug = String.format("從 %s 接收到參數：%s", getDisplayName(userId), paramMap.keySet().toString());
		System.out.println("DEBUG: " + debug);

		if (null != paramMap) {
			if (paramMap.keySet().contains("systemName") && paramMap.keySet().contains("status")) {
				if (canAccess(userId, paramMap.get("systemName"))) {
					return systemStatusResponse(paramMap.get("systemName"), paramMap.get("status"));
				} else {
					response.add(notAccessibleResponse());
				}
			} else {
				// TODO: logging known postback msg
				String warn = "unknown postback message: " + postbackParams;
				System.out.println(warn);
			}
		}
		return response;
	}

	/**
	 * 拆解postback event的參數（藏在data裡面）
	 * 
	 * @param postbackParams
	 * @return 參數與其值的Map（HashMap）
	 */
	private Map<String, String> paramToMap(String postbackParams) {
		if (postbackParams.contains("&") && postbackParams.contains("=")) {
			String[] diffParamStr = postbackParams.split("&");
			Map<String, String> paramMap = new HashMap<String, String>();
			for (String paramStr : diffParamStr) {
				String[] keyAndValue = paramStr.split("=");
				String paramName = (String) Array.get(keyAndValue, 0);
				String paramValue = (String) Array.get(keyAndValue, 1);
				paramMap.put(paramName, paramValue);
			}
			return paramMap;
		} else {
			return null;
		}
	}

	/**
	 * 產生含有對應系統狀態的訊息
	 * 
	 * @param systemName
	 * @param statusType
	 * @return 特定系統的特定狀態
	 */
	private List<Message> systemStatusResponse(String systemName, String apiActionType) {
		List<Message> response = new ArrayList<Message>();
		TextMessage messageTitle = createTextMsg(String.format("以下為%s的%s", systemName, apiActionType));
		response.add(messageTitle);
		SystemCaller systemCaller = callerMapper.getCaller(systemName);
		TextMessage messageContext = createTextMsg(systemCaller.callApi(apiActionType));
		response.add(messageContext);
		return response;
	}

	/**
	 * 依照使用者的文字訊息，產生對應的訊息
	 * 
	 * @param event
	 * @return 回覆文字訊息的訊息
	 */
	private List<Message> replyToTextMsg(MessageEvent<TextMessageContent> event) {
		String userInput = event.getMessage().getText();
		String userId = event.getSource().getUserId();

		// TODO: logging
		String debug = String.format("從 %s 接收到訊息：%s", getDisplayName(userId), event.getMessage().getText());
		System.out.println("DEBUG: " + debug);

		List<Message> response = new ArrayList<Message>();
		String mentionedSys = mentionedSystem(userInput);

		if (!isApprovedUser(userId)) {
			response.add(unknownUserResponse(userId));
			return response;
		}

		if (userInput.equals("系統狀態")) {
			response.add(showAccessibleSys(userId));
		} else if (null != mentionedSys) {
			if (canAccess(userId, mentionedSys)) {
				response.add(showStatusChoices(mentionedSys));
			} else {
				response.add(notAccessibleResponse());
			}
		}
		return response;
	}

	/**
	 * 產生回覆未知使用者的訊息<br>
	 * 未知使用者為Line token不在資料庫上的使用者
	 * 
	 * @param userId
	 * @return 回覆未知使用者的訊息
	 */
	private TextMessage unknownUserResponse(String userId) {
		TextMessage unknownUserResponse = createTextMsg("您並未註冊為可使用此查詢系統的員工，請洽詢此系統負責人或有權限的負責人，謝謝！");
		// TODO: logging
		String warning = String.format("Unknown user: %s (ID: %s)", getDisplayName(userId), userId);
		System.out.println(warning);
		return unknownUserResponse;
	}

	public boolean isApprovedUser(String userId) {
		return userDao.isApprovedUser(userId);
	}

	/**
	 * 產生回覆給沒有對應系統權限的使用者的訊息
	 * 
	 * @return 回覆無權限使用者的訊息
	 */
	private TextMessage notAccessibleResponse() {
		TextMessage notAccessibleResponse = createTextMsg("您尚未擁有查詢此系統的權限，請洽詢此系統負責人或有權限的負責人，謝謝！");
		return notAccessibleResponse;
	}

	private boolean canAccess(String userId, String systemName) {

		return true;
	}

	private FlexMessage showAccessibleSys(String userId) {
		// TODO: image uri
		URI imageUri = null;
		String templateTitle = "可查詢系統";
		String templateContext = "以下為您可查詢的系統，請點選您想查詢的系統";
		List<Action> buttonChoices = getSysChoices(userId);

		FlexMessage accessibleSysMsg = buildByBubbleTemplate(templateTitle, templateTitle, templateContext,
				buttonChoices, imageUri);
		return accessibleSysMsg;
	}

	private FlexMessage buildByBubbleTemplate(String previewText, String title, String context, List<Action> buttons,
			URI imageUri) {
		FlexMessage flexTemplate = loadFlexTemplate(previewText, "bubbleTemplate.json");

		if (null == flexTemplate) {
			// TODO: logging
			String error = "failed to load template";
			System.out.println(error);
			return null;
		}

		Bubble flexContainer = (Bubble) flexTemplate.getContents();
		Image coverImg = (Image) flexContainer.getHero();
		Box bodyBox = flexContainer.getBody();
		Text titleField = (Text) bodyBox.getContents().get(0);
		Text contextField = (Text) bodyBox.getContents().get(1);
		Box footerBox = flexContainer.getFooter();
		Button sampleButton = (Button) footerBox.getContents().get(0);

		if (null == imageUri) {
			coverImg = null;
		} else {
			coverImg = coverImg.toBuilder().url(imageUri).build();
		}
		flexContainer = flexContainer.toBuilder().hero(coverImg).build();

		titleField = titleField.toBuilder().text(title).build();
		contextField = contextField.toBuilder().text(context).build();
		bodyBox = bodyBox.toBuilder().contents(Arrays.asList(titleField, contextField)).build();
		flexContainer = flexContainer.toBuilder().body(bodyBox).build();

		List<FlexComponent> footerContents = new ArrayList<FlexComponent>();
		for (Action buttonAction : buttons) {
			Button tempButton = sampleButton.toBuilder().action(buttonAction).build();
			footerContents.add(tempButton);
		}
		footerBox = footerBox.toBuilder().contents(footerContents).build();
		flexContainer = flexContainer.toBuilder().footer(footerBox).build();

		flexTemplate = flexTemplate.toBuilder().contents(flexContainer).build();

		return flexTemplate;
	}

	private List<Action> getSysChoices(String userLineToken) {
		List<Action> sysChoices = new ArrayList<Action>();
		String userId = userDao.getUserId(userLineToken);
		System.out.println(userId);
		List<String> sysChoicesName = userAuthDao.getAccessibleSys(userId);
		for (String system : sysChoicesName) {
			Action sysChoice = new MessageAction(system, system);
			sysChoices.add(sysChoice);
		}
		System.out.println(sysChoicesName);
		return sysChoices;
	}

//	private List<String> _fakeSysChoices(String userId) {
//		List<String> sysChoicesStr = new ArrayList<String>(List.of("SDVX", "Project", "系統A"));
//		return sysChoicesStr;
//	}

	private String mentionedSystem(String userInputMsg) {
		String inputMsg = userInputMsg.toLowerCase();
		getSystemList();
		for (String keyword : systemList.keySet()) {
			if (inputMsg.contains(keyword.toLowerCase())) {
				return keyword;
			} else {
				for (String nickname : systemList.get(keyword)) {
					if (inputMsg.contains(nickname.toLowerCase())) {
						return keyword;
					}
				}
			}
		}
		return null;
	}
	
	private Map<String, List<String>> getSystemList(){
		if (systemList == null) {
			systemList = new HashMap<String, List<String>>();
			callerMapper.getSystemList().forEach((systemCaller) -> {
				systemList.put(systemCaller.getSystemName(), systemCaller.getSystemNickname());
			});
			return systemList;
		} else {
			return systemList;
		}
	}

	private FlexMessage showStatusChoices(String systemName) {
		// TODO: image uri
		URI imageUri = null;
		String templateTitle = systemName + "狀態";
		String templateContext = "請點選您想查看的內容";
		List<Action> buttonChoices = getStatusChoices(systemName);

		FlexMessage statusMsg = buildByBubbleTemplate(templateTitle, templateTitle, templateContext, buttonChoices,
				imageUri);

		return statusMsg;
	}

	private List<Action> getStatusChoices(String systemName) {
		List<Action> statusChoices = new ArrayList<Action>();
		SystemCaller systemCaller = callerMapper.getCaller(systemName);
		List<String> statusChoicesName = systemCaller.getAPIActions();

		for (String choiceName : statusChoicesName) {
			String data = String.format("systemName=%s&status=%s", systemName, choiceName);
			Action choiceBtn = new PostbackAction(systemName + choiceName, data);
			statusChoices.add(choiceBtn);
		}

		return statusChoices;
	}

//	private List<String> _fakeStatusChoices(String systemName) {
//		List<String> statusChoicesName = new ArrayList<String>(List.of("系統狀態", "批次結果", "某選擇A", "某選擇B", "某選擇C"));
//		return statusChoicesName;
//	}

	@Override
	public void exceptionHandler(Exception e) {
		// TODO logging
		e.printStackTrace();
	}

}
