package com.xp.consultant.service;


import dev.langchain4j.service.spring.AiService;

//    @AiService(
//            wiringMode = AiServiceWiringMode.EXPLICIT, //手动装配
//            chatModel = "openAiChatModel" //指定模型
//    )
@AiService
public interface Consultantservice {
    public String chat(String message);
}
