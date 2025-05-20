package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTemplateTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testString() {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        
        // 测试设置字符串
        ops.set("name", "张三");
        System.out.println("String测试 - 设置name: " + ops.get("name"));
        
        // 测试设置过期时间
        ops.set("tempKey", "临时数据", 5, TimeUnit.SECONDS);
        System.out.println("String测试 - 设置临时数据: " + ops.get("tempKey"));
        
        // 测试递增
        ops.set("counter", 1);
        ops.increment("counter");
        System.out.println("String测试 - 递增后的值: " + ops.get("counter"));
    }

    @Test
    public void testList() {
        ListOperations<String, Object> ops = redisTemplate.opsForList();
        
        // 测试从左侧添加
        ops.leftPush("list", "第一个元素");
        ops.leftPush("list", "第二个元素");
        
        // 测试从右侧添加
        ops.rightPush("list", "第三个元素");
        
        // 获取列表所有元素
        List<Object> list = ops.range("list", 0, -1);
        System.out.println("List测试 - 所有元素: " + list);
        
        // 测试弹出元素
        Object leftPop = ops.leftPop("list");
        System.out.println("List测试 - 左侧弹出的元素: " + leftPop);
    }

    @Test
    public void testSet() {
        SetOperations<String, Object> ops = redisTemplate.opsForSet();
        
        // 添加元素
        ops.add("set", "A", "B", "C", "D");
        
        // 获取所有元素
        Set<Object> members = ops.members("set");
        System.out.println("Set测试 - 所有元素: " + members);
        
        // 判断元素是否存在
        Boolean isMember = ops.isMember("set", "A");
        System.out.println("Set测试 - A是否存在: " + isMember);
        
        // 移除元素
        ops.remove("set", "D");
        System.out.println("Set测试 - 移除D后的元素: " + ops.members("set"));
    }

    @Test
    public void testHash() {
        HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
        
        // 添加哈希表字段
        ops.put("user:1", "name", "李四");
        ops.put("user:1", "age", "25");
        ops.put("user:1", "city", "北京");
        
        // 获取所有字段和值
        System.out.println("Hash测试 - 所有字段和值: " + ops.entries("user:1"));
        
        // 获取指定字段的值
        System.out.println("Hash测试 - name字段的值: " + ops.get("user:1", "name"));
        
        // 删除字段
        ops.delete("user:1", "city");
        System.out.println("Hash测试 - 删除city后的数据: " + ops.entries("user:1"));
    }

    @Test
    public void testZSet() {
        ZSetOperations<String, Object> ops = redisTemplate.opsForZSet();
        
        // 添加带分数的元素
        ops.add("ranking", "小明", 90.0);
        ops.add("ranking", "小红", 95.0);
        ops.add("ranking", "小张", 88.0);
        
        // 获取分数范围内的元素
        Set<Object> scoreRange = ops.rangeByScore("ranking", 85, 100);
        System.out.println("ZSet测试 - 85-100分的学生: " + scoreRange);
        
        // 获取元素排名
        Long rank = ops.rank("ranking", "小明");
        System.out.println("ZSet测试 - 小明的排名: " + rank);
        
        // 获取元素分数
        Double score = ops.score("ranking", "小红");
        System.out.println("ZSet测试 - 小红的分数: " + score);
    }
}
