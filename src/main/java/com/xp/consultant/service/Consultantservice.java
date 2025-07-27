package com.xp.consultant.service;


import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;



//指定模型= "openAiStreamingChatModel"

@AiService(
            wiringMode = AiServiceWiringMode.EXPLICIT, //手动装配
            chatModel = "openAiChatModel",
            streamingChatModel = "openAiStreamingChatModel",
        chatMemory = "chatMemory"
    )

public interface Consultantservice {
//用于聊天的方法
//    @SystemMessage("你是智能管家")
    @SystemMessage(fromResource="system.txt")
//@UserMessage("你是智能小助手{{it}}")
//    @UserMessage("你是智能小助手{{msg}}")

    public Flux<String> chat (String message);

}
