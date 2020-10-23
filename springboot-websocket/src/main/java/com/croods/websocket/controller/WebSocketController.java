package com.croods.websocket.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.croods.websocket.model.WebSocketMessage;

import lombok.extern.slf4j.Slf4j;

@Controller
public class WebSocketController {

	@RequestMapping("")
	public ModelAndView returnIndexPage() {
		return new ModelAndView("index");
	}

	private SimpMessagingTemplate simpMessagingTemplate; // 1
	private Set<String> connectedUsers; // 2

	public WebSocketController(SimpMessagingTemplate simpMessagingTemplate) {
		this.simpMessagingTemplate = simpMessagingTemplate; // 1
		connectedUsers = new HashSet<>(); // 2
	}

	@MessageMapping("/register") // 3
	@SendToUser("/queue/newMember")
	public Set<String> registerUser(String webChatUsername) {
		if (!connectedUsers.contains(webChatUsername)) {
			connectedUsers.add(webChatUsername);
			simpMessagingTemplate.convertAndSend("/topic/newMember", webChatUsername); // 4
			return connectedUsers;
		} else {
			return new HashSet<>();
		}
	}

	@MessageMapping("/unregister") // 5
	@SendTo("/topic/disconnectedUser")
	public String unregisterUser(String webChatUsername) {
		connectedUsers.remove(webChatUsername);
		return webChatUsername;
	}

	@MessageMapping("/message") // 6
	public void greeting(WebSocketMessage message) {
		simpMessagingTemplate.convertAndSendToUser(message.toWhom, "/msg", message);
	}

//	 @MessageMapping("/hello")
//	 @SendTo("/topic/greetings")
//	 public Greeting greetings(HelloMessage message, SimpMessageHeaderAccessor ha) throws Exception {
//		 InetSocketAddress ip = (InetSocketAddress) ha.getSessionAttributes().get("ip");
//		 System.err.println(ip);
//		 Thread.sleep(1000);
//		 return new Greeting("("+ip+")----> "+HtmlUtils.htmlEscape(message.getName()));
//	 }

}
