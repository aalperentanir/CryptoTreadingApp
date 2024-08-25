package com.alialperen.chatbot.controller;

import com.alialperen.chatbot.entity.PromptBody;
import com.alialperen.chatbot.response.ApiResponse;
import com.alialperen.chatbot.service.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;

    @PostMapping("/chat")
    public ResponseEntity<ApiResponse> getCoinDetails(@RequestBody PromptBody body) throws Exception {

        ApiResponse response = chatbotService.getCoinDetails(body.getPrompt());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/chat/simple")
    public ResponseEntity<String> simpleChat(@RequestBody PromptBody body) throws Exception {
        String response = chatbotService.simpleChat(body.getPrompt());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
