package com.xp.consultant.config;

import com.xp.consultant.Repository.RedisChatMemoryStore;
import com.xp.consultant.service.Consultantservice;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.ClassPathDocumentLoader;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;
import java.util.List;

@Configuration
public class CommonConfig {
    @Autowired
    private OpenAiChatModel openAiChatModel;
    @Autowired
    private RedisChatMemoryStore   redisChatMemoryStore;
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
    public ChatMemory chatMemory() {
        MessageWindowChatMemory memory = MessageWindowChatMemory.builder()
                .maxMessages(20)
                .build();
        return memory;
    }

    //构建ChatMemoryProvider对象
    @Bean
    public ChatMemoryProvider chatMemoryProvider() {
        ChatMemoryProvider chatMemoryProvider = new ChatMemoryProvider() {
            @Override
            public ChatMemory get(Object memoryId) {
           return  MessageWindowChatMemory.builder()
                   .id(memoryId)
                    .maxMessages(20)
                   .chatMemoryStore((ChatMemoryStore) redisChatMemoryStore)//配置ChatMemoryStore
                    .build();
            }
        };
return chatMemoryProvider;
    }

    //构建向量数据库操作对象
    @Bean
    public EmbeddingStore store(){
        //1.加载文档进内存
        List<Document> documents = ClassPathDocumentLoader.loadDocuments("content");
        //2.构建向量数据库操作对象  操作的是内存版本的向量数据库
        InMemoryEmbeddingStore store = new InMemoryEmbeddingStore();
        //3.构建一个EmbeddingStoreIngestor对象,完成文本数据切割,向量化, 存储
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .embeddingStore(store)
                .build();
        ingestor.ingest(documents);
        return store;
    }
    @Bean
    public ContentRetriever contentRetriever(EmbeddingStore store){
        return EmbeddingStoreContentRetriever.builder()
                .embeddingStore(store)//设置向量数据库操作对象
                .minScore(0.5)//设置最小分数
                .maxResults(3)//设置最大片段数量
                .build();
    }
}
