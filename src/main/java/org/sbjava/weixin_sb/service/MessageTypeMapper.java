package org.sbjava.weixin_sb.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.sbjava.weixin_sb.domain.InMessage;
import org.sbjava.weixin_sb.domain.event.EventInMessage;
import org.sbjava.weixin_sb.domain.image.ImageInMessage;
import org.sbjava.weixin_sb.domain.link.LinkInMessage;
import org.sbjava.weixin_sb.domain.location.LocationInMessage;
import org.sbjava.weixin_sb.domain.shortvideo.ShortVideoInMessage;
import org.sbjava.weixin_sb.domain.text.TextInMessage;
import org.sbjava.weixin_sb.domain.video.VideoInMessage;
import org.sbjava.weixin_sb.domain.voice.VoiceInMessage;

public class MessageTypeMapper {
	
	private static Map<String, Class<? extends InMessage>> typeMap = new ConcurrentHashMap<>();
	
	//通过一个Map记录了消息类型和类的关系
	static {
		typeMap.put("text",TextInMessage.class);
		typeMap.put("image",ImageInMessage.class);
		typeMap.put("voice",VoiceInMessage.class);
		typeMap.put("video",VideoInMessage.class);
		typeMap.put("shortvideo",ShortVideoInMessage.class);
		typeMap.put("location",LocationInMessage.class);
		typeMap.put("link",LinkInMessage.class);
		typeMap.put("event",EventInMessage.class);
	}
	
	//通过消息类型获取对应的类
	@SuppressWarnings("unchecked")
	public static <T extends InMessage> Class<T> getClass(String type){
		return (Class<T>) typeMap.get(type);
	}
	
}
