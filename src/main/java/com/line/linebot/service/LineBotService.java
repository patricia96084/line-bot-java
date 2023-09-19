package com.line.linebot.service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.line.linebot.commons.util.BaseUtils;
import com.line.linebot.interfaces.line.LineBotInterface;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.message.AudioMessage;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.ImagemapMessage;
import com.linecorp.bot.model.message.LocationMessage;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.TextMessage.Emoji;
import com.linecorp.bot.model.message.VideoMessage;
import com.linecorp.bot.model.message.flex.container.FlexContainer;
import com.linecorp.bot.model.message.imagemap.*;
import com.linecorp.bot.model.message.template.Template;

public class LineBotService implements LineBotInterface {

	public LineBotService() {
	}

	@Override
	public String getDisplayName(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkPostFromLine(String requestBody, String X_Line_Signature) {
		return false;
	}

	@Override
	public ResponseEntity<String> reply(ReplyMessage replyMsg) {
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * 依照Event狀態決定反應，只有Active才可能有ReplyToken用以回應<br>
	 * 其餘狀態已有預設method打log，但不會額外從bot發送訊息
	 * 
	 * @param event - Line發過來的event
	 * @return 若為可回應的Event，則打包要回的訊息及ReplyToken成ReplyMessage給reply使用
	 */
	public ReplyMessage HandleDiffEventMode(Event event) {
		switch (event.getMode()) {
		case STANDBY:
			reactToStandbyEvent(event);
		case ACTIVE:
			return decideResponse(event);
		default:
			reactToUnknownEvent(event);
		}
		return null;
	}

	private void reactToUnknownEvent(Event event) {
		// TODO: logging
		String unknownWarning = String.format("Unknown event occurred at %s", event.getTimestamp().toString());
		System.out.println("WARNING: " + unknownWarning);
		System.out.println(event.toString());
	}

	private void reactToStandbyEvent(Event event) {
		// TODO: logging
		String cantReplyWarning = String.format("Get %s that can't reply at %s from user %s",
				event.getClass().getName(), event.getTimestamp().toString(),
				getDisplayName(event.getSource().getUserId()));
		System.out.println("WARNING: " + cantReplyWarning);
	}

	@Override
	public ReplyMessage decideResponse(Event event) {
		return null;
	}

	@Override
	public TextMessage createTextMsg(String text) {
		if (text.length() >= 5000) {
			// TODO: raise exception or warn for upcoming "bad request" response
			return null;
		}
		TextMessage textMsg = new TextMessage(text);
		return textMsg;
	}

	@Override
	public TextMessage createTextMsg(String text, List<Emoji> emojis) {
		if (text.length() >= 5000 || emojis.size() >= 20) {
			// TODO: raise exception or warn for upcoming "bad request" response
			return null;
		} else if (emojis.size() == 0) {
			return createTextMsg(text);
		}
		TextMessage textMsg = TextMessage.builder().emojis(emojis).text(text).build();
		return textMsg;
	}

	@Override
	public ImageMessage createImageMsg(URI imageUri) {
		return createImageMsg(imageUri, imageUri);
	}

	@Override
	public ImageMessage createImageMsg(URI imageUri, URI previewImgUri) {
		ImageMessage imgMsg = new ImageMessage(imageUri, previewImgUri);
		return imgMsg;
	}

	@Override
	public StickerMessage createStickerMsg(String packageId, String stickerId) {
		StickerMessage stickerMsg = new StickerMessage(packageId, stickerId);
		return stickerMsg;
	}

	@Override
	public LocationMessage createLocationMsg(String titleOfLocation, String address) {
		// TODO: auto fill latitude and longtitude
		return null;
	}

	@Override
	public LocationMessage createLocationMsg(String titleOfLocation, String address, double latitude,
			double longtitude) {
		LocationMessage locationMsg = new LocationMessage(titleOfLocation, address, latitude, longtitude);
		return locationMsg;
	}

	@Override
	public AudioMessage createAudioMsg(URI audioUri) {
		// TODO: auto fill duration
		return null;
	}

	@Override
	public AudioMessage createAudioMsg(URI audioUri, Long duration) {
		AudioMessage audioMsg = new AudioMessage(audioUri, duration);
		return audioMsg;
	}

	@Override
	public VideoMessage createVideoMsg(URI videoUri, URI previewImgUri) {
		VideoMessage videoMsg = new VideoMessage(videoUri, previewImgUri);
		return videoMsg;
	}

	@Override
	public TemplateMessage createTemplateMsg(String previewText, Template template) {
		TemplateMessage templateMsg = new TemplateMessage(previewText, template);
		return templateMsg;
	}

	@Override
	public ImagemapMessage createImagemapMsg(String previewText, List<ImagemapAction> actions,
			ImagemapBaseSize imageSize, URI imageUri) {
		ImagemapMessage imgMapMsg = ImagemapMessage.builder().baseSize(imageSize).altText(previewText).baseUrl(imageUri)
				.actions(actions).build();
		return imgMapMsg;
	}

	@Override
	public ImagemapMessage createImagemapMsg(String previewText, List<ImagemapAction> actions,
			ImagemapBaseSize imageSize, URI imageUri, ImagemapVideo video) {
		ImagemapMessage imgMapMsg = ImagemapMessage.builder().actions(actions).altText(previewText).baseSize(imageSize)
				.baseUrl(imageUri).video(video).build();
		return imgMapMsg;
	}

	@Override
	public FlexMessage createFlexMsg(String previewText, FlexContainer content) {
		FlexMessage flexMsg = new FlexMessage(previewText, content);
		return flexMsg;
	}

	public FlexMessage createFlexMsg(String previewText, String jsonContent) {
		FlexContainer content = (FlexContainer) BaseUtils.jsonString2Object(jsonContent, FlexContainer.class);
		return createFlexMsg(previewText, content);
	}

	/**
	 * 讀取放在resources/static/flexMsgTemplate/的flex message模板<br>
	 * 模板可從https://developers.line.biz/flex-simulator/獲得
	 * 
	 * @param previewText
	 * @param templateFileName
	 * @return 轉換成Flex message的模板
	 */
	public FlexMessage loadFlexTemplate(String previewText, String templateFileName) {
		File filePath;
		try {
			filePath = new ClassPathResource("static/flexMsgTemplate/" + templateFileName).getFile();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		FlexContainer flexMsgTemplate = (FlexContainer) BaseUtils.jsonFile2Object(filePath, FlexContainer.class);
		FlexMessage flexMsg = createFlexMsg(previewText, flexMsgTemplate);
		return flexMsg;
	}

	@Override
	public void exceptionHandler(Exception e) {
		e.printStackTrace();
	}

}
