package com.xp.consultant.controller;

import com.xp.consultant.service.Consultantservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {
    @Autowired
    private Consultantservice consultantService;

    @RequestMapping(value = "/chat",produces="text/html;charset=utf-8")
    public Flux<String> chat(String memoryId,String message) {
        Flux<String> result = consultantService.chat(memoryId,message);
        return result;
    }

}

//    @RequestMapping("/chat")
//    public String chat(String message) {
//
//        return consultantService.chat(message);
//    }







//    @Autowired
//    private OpenAiChatModel openAiChatModel;
//    @RequestMapping("/chat")
//    public String chat(String message) {
//    String result = openAiChatModel.chat(message);
//    return result;
//    }



