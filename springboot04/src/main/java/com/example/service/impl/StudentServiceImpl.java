package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.Result;
import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import com.example.service.StudentService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "student") // 指定缓存名称
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    @Cacheable(key = "'list'") // 使用list作为缓存key
    public Result<List<Student>> findAll() {
        try {
            List<Student> students = studentMapper.selectList(null);
            return Result.success(students);
        } catch (Exception e) {
            return Result.error("获取学生列表失败：" + e.getMessage());
        }
    }

    @Override
    @Cacheable(key = "#id") // 使用学生id作为缓存key
    public Result<Student> findById(Long id) {
        try {
            if (id == null) {
                return Result.error("学生ID不能为空");
            }

            Student student = studentMapper.selectById(id);
            if (student == null) {
                return Result.error("学生不存在");
            }

            return Result.success(student);
        } catch (Exception e) {
            return Result.error("获取学生信息失败：" + e.getMessage());
        }
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true) // 清除所有学生相关的缓存
    public Result<Student> save(Student student) {
        try {
            // 数据校验
            if (!validateStudent(student)) {
                return Result.error("学生信息不完整");
            }

            // 检查学号是否已存在
            QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("student_no", student.getStudentNo());
            Student existingStudent = studentMapper.selectOne(queryWrapper);
            if (existingStudent != null) {
                return Result.error("学号已存在");
            }

            int result = studentMapper.insert(student);
            if (result > 0) {
                return Result.success(student, "添加成功");
            } else {
                return Result.error("添加失败");
            }
        } catch (Exception e) {
            return Result.error("添加失败：" + e.getMessage());
        }
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true) // 清除所有学生相关的缓存
    public Result<Student> update(Student student) {
        try {
            // 数据校验
            if (student.getId() == null) {
                return Result.error("学生ID不能为空");
            }
            if (!validateStudent(student)) {
                return Result.error("学生信息不完整");
            }

            // 检查学生是否存在
            Student existingStudent = studentMapper.selectById(student.getId());
            if (existingStudent == null) {
                return Result.error("学生不存在");
            }

            // 检查学号是否与其他学生重复
            QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("student_no", student.getStudentNo())
                    .ne("id", student.getId());
            Student studentWithSameNo = studentMapper.selectOne(queryWrapper);
            if (studentWithSameNo != null) {
                return Result.error("学号已存在");
            }

            int result = studentMapper.updateById(student);
            if (result > 0) {
                return Result.success(student, "更新成功");
            } else {
                return Result.error("更新失败");
            }
        } catch (Exception e) {
            return Result.error("更新失败：" + e.getMessage());
        }
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true) // 清除所有学生相关的缓存
    public Result<Void> deleteById(Long id) {
        try {
            if (id == null) {
                return Result.error("学生ID不能为空");
            }

            // 检查学生是否存在
            Student student = studentMapper.selectById(id);
            if (student == null) {
                return Result.error("学生不存在");
            }

            int result = studentMapper.deleteById(id);
            if (result > 0) {
                return Result.success(null, "删除成功");
            } else {
                return Result.error("删除失败");
            }
        } catch (Exception e) {
            return Result.error("删除失败：" + e.getMessage());
        }
    }

    private boolean validateStudent(Student student) {
        return student != null &&
                StringUtils.hasText(student.getStudentNo()) &&
                StringUtils.hasText(student.getName()) &&
                StringUtils.hasText(student.getGender()) &&
                StringUtils.hasText(student.getGrade());
    }

}