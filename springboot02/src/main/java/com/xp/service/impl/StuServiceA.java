package com.xp.service.impl;

import com.xp.dao.StuDao;
import com.xp.pojo.Stu;
import com.xp.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuServiceA implements StuService {

    @Autowired // 运行时,需要从IOC容器中获取该类型对象,赋值给该变量
    private StuDao stuDao;

    @Override
    public List<Stu> listStu() {
        // 1. 调用dao, 获取数据
        List<Stu> stuList = stuDao.listStu();

        // 2. 对数据进行转换处理
        for (Stu stu : stuList) {
            int score = stu.getScore();
            String grade;
            if (score >= 90) {
                grade = "优";
            } else if (score >= 80) {
                grade = "良";
            } else if (score >= 60) {
                grade = "中";
            } else {
                grade = "不及格";
            }
            stu.setGrades(grade);
        }
        return stuList;
    }
}
