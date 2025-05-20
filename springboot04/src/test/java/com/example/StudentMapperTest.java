package com.example;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // 测试完成后自动回滚，不影响数据库数据
public class StudentMapperTest {

  @Autowired
  private StudentMapper studentMapper;

  @Test
  public void testInsert() {
    Student student = new Student();
    student.setStudentNo("2024003");
    student.setName("测试学生");
    student.setGender("男");
    student.setGrade("2024级");

    int result = studentMapper.insert(student);
    System.out.println("插入结果：" + result);
    System.out.println("插入后的ID：" + student.getId());
    assertTrue(result > 0);
    assertNotNull(student.getId());
  }

  @Test
  public void testSelect() {
    // 查询所有
    List<Student> students = studentMapper.selectList(null);
    System.out.println("所有学生：");
    students.forEach(System.out::println);
    assertFalse(students.isEmpty());

    // 条件查询
    QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("grade", "2024级");
    List<Student> grade2024Students = studentMapper.selectList(queryWrapper);
    System.out.println("\n2024级学生：");
    grade2024Students.forEach(System.out::println);

    // Lambda条件查询
    LambdaQueryWrapper<Student> lambdaWrapper = new LambdaQueryWrapper<>();
    lambdaWrapper.eq(Student::getGender, "女");
    List<Student> femaleStudents = studentMapper.selectList(lambdaWrapper);
    System.out.println("\n女生：");
    femaleStudents.forEach(System.out::println);

    // 分页查询
    Page<Student> page = new Page<>(1, 2); // 第1页，每页2条
    Page<Student> studentPage = studentMapper.selectPage(page, null);
    System.out.println("\n分页查询结果：");
    System.out.println("总记录数：" + studentPage.getTotal());
    System.out.println("当前页记录：");
    studentPage.getRecords().forEach(System.out::println);
  }

  @Test
  public void testUpdate() {
    // 先插入一个测试学生
    Student student = new Student();
    student.setStudentNo("2024004");
    student.setName("待更新学生");
    student.setGender("男");
    student.setGrade("2024级");
    studentMapper.insert(student);

    // 更新信息
    student.setName("已更新学生");
    int result = studentMapper.updateById(student);
    System.out.println("更新结果：" + result);
    assertTrue(result > 0);

    // 验证更新
    Student updated = studentMapper.selectById(student.getId());
    assertEquals("已更新学生", updated.getName());
    System.out.println("更新后的学生信息：" + updated);
  }

  @Test
  public void testDelete() {
    // 先插入一个测试学生
    Student student = new Student();
    student.setStudentNo("2024005");
    student.setName("待删除学生");
    student.setGender("男");
    student.setGrade("2024级");
    studentMapper.insert(student);

    // 删除
    int result = studentMapper.deleteById(student.getId());
    System.out.println("删除结果：" + result);
    assertTrue(result > 0);

    // 验证删除
    Student deleted = studentMapper.selectById(student.getId());
    assertNull(deleted);
  }
}