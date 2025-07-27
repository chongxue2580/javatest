package com.xp.consultant.config;

import com.xp.consultant.service.Consultantservice;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class CommonConfig {
    @Autowired
    private OpenAiChatModel openAiChatModel;
//    @Bean
//    public Consultantservice consultantService() {
//     Consultantservice consultantService = AiServices.builder(Consultantservice.class)
//             .chatModel(openAiChatModel)
//             .build();
//        return consultantService;
//
//
//    }



    @AiService
    public interface Consultantservice {
        String chat(String message);
    }
    @Bean
    public ChatMemory chatMemory(){
    MessageWindowChatMemory memory = MessageWindowChatMemory.builder()
            .maxMessages(20)
            .build();
    return memory;
}

}
