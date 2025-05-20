package com.xp.dao.impl;

import com.xp.dao.StuDao;
import com.xp.pojo.Stu;
import com.xp.utils.XmlParserUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

@Component //将当前对象交给IOC容器管理,成为IOC容器的bean
public class StuDaoA implements StuDao {
    @Override
    public List<Stu> listStu() {
        try {
            // 使用 getResourceAsStream 直接获取输入流
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("stu.xml");
            if (inputStream == null) {
                throw new RuntimeException("无法找到 stu.xml 文件");
            }
            
            // 修改 XmlParserUtils 以接受 InputStream
            List<Stu> stuList = XmlParserUtils.parse(inputStream, Stu.class);
            return stuList;
            
        } catch (Exception e) {
            System.out.println("加载文件时出错: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("无法读取 stu.xml 文件", e);
        }
    }
}
