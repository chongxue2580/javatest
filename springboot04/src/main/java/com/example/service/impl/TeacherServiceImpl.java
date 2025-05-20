package com.example.service.impl;

import com.example.common.Result;
import com.example.entity.Teacher;
import com.example.mapper.TeacherMapper;
import com.example.mapper.CourseMapper;
import com.example.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
  private final TeacherMapper teacherMapper;
  private final CourseMapper courseMapper;

  public TeacherServiceImpl(TeacherMapper teacherMapper, CourseMapper courseMapper) {
    this.teacherMapper = teacherMapper;
    this.courseMapper = courseMapper;
  }

  @Override
  public Result<List<Teacher>> findAll() {
    try {
      List<Teacher> teachers = teacherMapper.findAll();
      return Result.success(teachers);
    } catch (Exception e) {
      return Result.error("获取教师列表失败：" + e.getMessage());
    }
  }

  @Override
  public Result<Teacher> findById(Long id) {
    try {
      if (id == null) {
        return Result.error("教师ID不能为空");
      }
      Teacher teacher = teacherMapper.findById(id);
      if (teacher == null) {
        return Result.error("教师不存在");
      }
      return Result.success(teacher);
    } catch (Exception e) {
      return Result.error("获取教师信息失败：" + e.getMessage());
    }
  }

  @Override
  @Transactional
  public Result<Teacher> save(Teacher teacher) {
    try {
      // 数据校验
      if (!validateTeacher(teacher)) {
        return Result.error("教师信息不完整");
      }

      // 检查教师编号是否已存在
      Teacher existingTeacher = teacherMapper.findByTeacherNo(teacher.getTeacherNo());
      if (existingTeacher != null) {
        return Result.error("教师编号已存在");
      }

      int result = teacherMapper.insert(teacher);
      if (result > 0) {
        return Result.success(teacher, "添加成功");
      } else {
        return Result.error("添加失败");
      }
    } catch (Exception e) {
      return Result.error("添加失败：" + e.getMessage());
    }
  }

  @Override
  @Transactional
  public Result<Teacher> update(Teacher teacher) {
    try {
      // 数据校验
      if (teacher.getId() == null) {
        return Result.error("教师ID不能为空");
      }
      if (!validateTeacher(teacher)) {
        return Result.error("教师信息不完整");
      }

      // 检查教师是否存在
      Teacher existingTeacher = teacherMapper.findById(teacher.getId());
      if (existingTeacher == null) {
        return Result.error("教师不存在");
      }

      // 检查教师编号是否与其他教师重复
      Teacher teacherWithSameNo = teacherMapper.findByTeacherNo(teacher.getTeacherNo());
      if (teacherWithSameNo != null && !teacherWithSameNo.getId().equals(teacher.getId())) {
        return Result.error("教师编号已存在");
      }

      int result = teacherMapper.update(teacher);
      if (result > 0) {
        return Result.success(teacher, "更新成功");
      } else {
        return Result.error("更新失败");
      }
    } catch (Exception e) {
      return Result.error("更新失败：" + e.getMessage());
    }
  }

  @Override
  @Transactional
  public Result<Void> deleteById(Long id) {
    try {
      if (id == null) {
        return Result.error("教师ID不能为空");
      }

      // 检查教师是否存在
      Teacher teacher = teacherMapper.findById(id);
      if (teacher == null) {
        return Result.error("教师不存在");
      }

      // 检查是否有关联的课程
      if (courseMapper.countByTeacherId(id) > 0) {
        return Result.error("该教师已关联课程，请先删除相关课程后再试");
      }

      int result = teacherMapper.deleteById(id);
      if (result > 0) {
        return Result.success(null, "删除成功");
      } else {
        return Result.error("删除失败");
      }
    } catch (Exception e) {
      return Result.error("删除失败：" + e.getMessage());
    }
  }

  private boolean validateTeacher(Teacher teacher) {
    return teacher != null &&
        StringUtils.hasText(teacher.getTeacherNo()) &&
        StringUtils.hasText(teacher.getName());
  }
}