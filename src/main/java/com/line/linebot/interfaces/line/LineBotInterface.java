package com.line.linebot.interfaces.line;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.message.*;
import com.linecorp.bot.model.message.flex.container.*;
import com.linecorp.bot.model.message.imagemap.*;
import com.linecorp.bot.model.message.template.Template;

public interface LineBotInterface {
	/**
	 * 訊息是否來自Line
	 * @param requestBody
	 * @param X_Line_Signature
	 * @return 訊息是否來自Line
	 */
	boolean checkPostFromLine(String requestBody, String X_Line_Signature);

	/**
	 * 藉由Line提供的API獲取使用者在Line上的使用者名稱
	 * @param userId
	 * @return 使用者在Line上的使用者名稱
	 */
	String getDisplayName(String userId);

	/**
	 * reply any kind of messages to certain event
	 * 
	 * @param replyToken - reply token of the event that allow bot to reply the
	 *                   event
	 * @param messages   - may be multiple message to reply the event
	 */
	ResponseEntity<String> reply(ReplyMessage replyMsg);

	/**
	 * 決定如何回應收到的事件
	 * 
	 * @param userInput: user input get from user
	 * @return may be multiple message to reply this input
	 */
	ReplyMessage decideResponse(Event event);

	TextMessage createTextMsg(String text);

	/**
	 * symbol '$' would be the placeholder of emojis<br>
	 * amount of the emojis should be under 20<br>
	 * amount of the text should be under 5000
	 * 
	 * @param text   - text constructed with '$' and original text
	 * @param emojis - list of emojis that would be put in text in order
	 * @return text message ready to be used as response
	 */
	TextMessage createTextMsg(String text, List<TextMessage.Emoji> emojis);

	ImageMessage createImageMsg(URI imageUri);

	ImageMessage createImageMsg(URI imageUri, URI previewImgUri);

	StickerMessage createStickerMsg(String packageId, String stickerId);

	LocationMessage createLocationMsg(String titleOfLocation, String address);

	LocationMessage createLocationMsg(String titleOfLocation, String address, double latitude, double longtitude);

	AudioMessage createAudioMsg(URI audioUri);

	AudioMessage createAudioMsg(URI audioUri, Long duration);

	VideoMessage createVideoMsg(URI videoUri, URI previewImgUri);

	/**
	 * create template message which could be buttons, carousel, confirm or image
	 * carousel template
	 * 
	 * @param previewText - the preview text that user see when they get the
	 *                    notification from line
	 * @param template    - template of this message which could be buttons,
	 *                    carousel, confirm or image carousel template
	 * @return template message ready to be used as a response
	 */
	TemplateMessage createTemplateMsg(String previewText, Template template);

	/**
	 * create image map message which is optional to contained video
	 * 
	 * @param previewText - the preview text that user see when they get the
	 *                    notification from line
	 * @param actions     - defined the area that can be interacted
	 * @param imageSize   - size of the image
	 * @param imageUri    - URI of the image, it's called base URI in doc
	 * @return image map message ready to be used as a response
	 */
	ImagemapMessage createImagemapMsg(String previewText, List<ImagemapAction> actions, ImagemapBaseSize imageSize,
			URI imageUri);

	ImagemapMessage createImagemapMsg(String previewText, List<ImagemapAction> actions, ImagemapBaseSize imageSize,
			URI imageUri, ImagemapVideo video);

	/**
	 * create flex message which could be either bubble or carousel
	 * 
	 * @param previewText - the preview text that user see when they get the
	 *                    notification from line
	 * @param content     - the bubble or carousel message itself. <br>
	 *                    it should be construct with sort of CSS structure(CSS
	 *                    flexible box) by its builder.
	 * @return flex message ready to be used as a response
	 */
	FlexMessage createFlexMsg(String previewText, FlexContainer content);

	void exceptionHandler(Exception e);
}
