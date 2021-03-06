package org.sbjava.weixin_sb.domain.image;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.sbjava.weixin_sb.domain.InMessage;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class ImageInMessage extends InMessage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlElement(name="PicUrl")
	@JsonProperty("PicUrl")
	private String picUrl;
	
	@XmlElement(name="MediaId")
	@JsonProperty("MediaId")
	private String mediaId;
	
	public ImageInMessage() {
		super.setMsgType("image");
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	@Override
	public String toString() {
		return "ImageInMessage [picUrl=" + picUrl + ", mediaId=" + mediaId + ", getToUserName()=" + getToUserName()
				+ ", getFromUserName()=" + getFromUserName() + ", getCreateTime()=" + getCreateTime()
				+ ", getMsgType()=" + getMsgType() + ", getMsgId()=" + getMsgId() + "]";
	}

}
