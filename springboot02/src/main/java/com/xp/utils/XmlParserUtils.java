package com.xp.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class XmlParserUtils {
    
    public static <T> List<T> parse(String file, Class<T> targetClass) {
        try {
            // 获取类加载器中的资源流
            InputStream inputStream = XmlParserUtils.class.getClassLoader().getResourceAsStream(file);
            if (inputStream == null) {
                throw new RuntimeException("找不到文件: " + file);
            }
            return parse(inputStream, targetClass);
        } catch (Exception e) {
            throw new RuntimeException("解析XML文件失败", e);
        }
    }

    public static <T> List<T> parse(InputStream inputStream, Class<T> targetClass) {
        try {
            // 创建 DOM4J 的解析器对象
            SAXReader saxReader = new SAXReader();
            // 通过解析器对象读取 xml 文件
            Document document = saxReader.read(inputStream);
            // 获取根标签
            org.dom4j.Element rootElement = document.getRootElement();
            // 获取子标签集合
            List<org.dom4j.Element> elements = rootElement.elements();

            List<T> list = new ArrayList<>();
            
            // 遍历集合
            for (org.dom4j.Element element : elements) {
                // 创建一个对应的实体类对象
                Constructor<T> constructor = targetClass.getDeclaredConstructor();
                constructor.setAccessible(true);
                T t = constructor.newInstance();

                // 获取 element 下的所有子标签
                List<org.dom4j.Element> subElements = element.elements();
                // 遍历子标签集合
                for (org.dom4j.Element subElement : subElements) {
                    // 获取子标签的标签名
                    String tagName = subElement.getName();
                    // 获取子标签的标签值
                    String tagValue = subElement.getText();
                    
                    // 通过反射，将值封装到对象的属性中
                    try {
                        Field field = targetClass.getDeclaredField(tagName);
                        field.setAccessible(true);
                        
                        // 判断字段类型并转换
                        if (field.getType().equals(Integer.class)) {
                            field.set(t, Integer.parseInt(tagValue));
                        } else {
                            field.set(t, tagValue);
                        }
                    } catch (NoSuchFieldException e) {
                        // 如果找不到对应的字段，继续处理下一个
                        continue;
                    }
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException("解析XML文件失败", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}