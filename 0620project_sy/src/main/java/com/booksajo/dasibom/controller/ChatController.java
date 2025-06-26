package com.booksajo.dasibom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {
	
	@RequestMapping("/chat.do")
    public String chatPage(@RequestParam("sellerId") String sellerId, Model model) {
        model.addAttribute("sellerId", sellerId);
        return "chat"; // chat.jsp ?†å?çîÎß?
    }
}