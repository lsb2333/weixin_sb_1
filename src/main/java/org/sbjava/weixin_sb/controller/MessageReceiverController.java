package org.sbjava.weixin_sb.controller;

import java.io.StringReader;

import javax.xml.bind.JAXB;

import org.sbjava.weixin_sb.domain.InMessage;
import org.sbjava.weixin_sb.service.MessageTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


// 控制器 : 负责接收用户的请求参数、调用业务逻辑层代码、返回视图/结果给客户端（浏览器）
// @Controller  基于JSP的控制器
// @RestController 符合RESTful风格的WEB服务的控制器
// RESTful通过不同的请求方法调用不同的处理程序，返回的结果仅仅是数据，不包含视图（HTML、JSP）
@RestController
// @RequestMapping表示的含义：URL跟控制器的关系映射
@RequestMapping("/lsb/weixin/receiver")
public class MessageReceiverController {

	//日志记录器
	private static final Logger LOG = LoggerFactory.getLogger(MessageReceiverController.class);
	
	
	@GetMapping // 只处理GET请求
	public String echo(//
			@RequestParam("signature") String signature, //
			@RequestParam("timestamp") String timestamp, //
			@RequestParam("nonce") String nonce, //
			@RequestParam("echostr") String echostr//
	) {
		// 正常来讲，需要把timestamp和nonce放入一个数组，并进行排序
		// 接着把排序后的两个元素拼接成一个新的String
		// 使用SHA-1算法对新的String进行加密
		// 最后把加密的结果跟signature进行比较，如果相同表示验证通过，返回echostr

		// 原路返回echostr的值，返回以后微信公众号平台就能够认为：服务器对接成功
		return echostr;
	}
	
	
	@PostMapping
	//@RequestBody注解表示把请求内容获取出来，并且 转换为String传入给xml参数
	public String onMessage(
			@RequestParam("signature") String signature, //
			@RequestParam("timestamp") String timestamp, //
			@RequestParam("nonce") String nonce, //
			@RequestBody String xml) {
		//接收消息
		LOG.debug("接收用户发送给公众号的信息：\n-------------------\n"
				+"{}\n----------------------------\n",xml);
		
		
		
		//截取消息类型
		//<MsgType><![CDATA[text]]></MsgType>
		String type = xml.substring(xml.indexOf("<MsgType><![CDATA[") + 18);
		type = type.substring(0, type.indexOf("]]></MsgType>"));
		
		Class<InMessage> cla = MessageTypeMapper.getClass(type);
		
		//使用JAXB完成XML转换为java对象的操作 
		InMessage inMessage = JAXB.unmarshal(new StringReader(xml), cla);
		
		LOG.debug("转换得到的消息对象 \n{}\n", inMessage.toString());
		
		//由于后面会把消息放入队列中，所以这里直接返回success。
		return "success";
	}
}

