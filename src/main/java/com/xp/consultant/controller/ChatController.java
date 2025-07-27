package com.xp.consultant.controller;

import com.xp.consultant.service.Consultantservice;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    @Autowired
    private Consultantservice consultantService;
    @RequestMapping("/chat")
    public String chat(String message) {
        return consultantService.chat(message);
    }
    }






//    @Autowired
//    private OpenAiChatModel openAiChatModel;
//    @RequestMapping("/chat")
//    public String chat(String message) {
//    String result = openAiChatModel.chat(message);
//    return result;
//    }



