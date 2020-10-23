package com.croods.websocket.controller;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.croods.websocket.model.Greeting;
import com.croods.websocket.model.HelloMessage;

@Controller
public class GreetingController {
	
	@RequestMapping("")
	public ModelAndView returnIndexPage() {
		return new ModelAndView("index");
	}
	

	 @MessageMapping("/hello")
	 @SendTo("/topic/greetings")
	 public Greeting greetings(HelloMessage message, SimpMessageHeaderAccessor ha) throws Exception {
		 InetSocketAddress ip = (InetSocketAddress) ha.getSessionAttributes().get("ip");
		 System.err.println(ip);
//		 try(final DatagramSocket socket = new DatagramSocket()) {
//			 socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
//			 ip = socket.getLocalAddress().getHostAddress();
//			 
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		 Thread.sleep(1000);
		 return new Greeting("("+ip+")----> "+HtmlUtils.htmlEscape(message.getName()));
	 }

}
