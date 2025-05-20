package com.example.service.impl;

import com.example.common.Result;
import com.example.entity.Course;
import com.example.mapper.CourseMapper;
import com.example.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

  private final CourseMapper courseMapper;

  public CourseServiceImpl(CourseMapper courseMapper) {
    this.courseMapper = courseMapper;
  }

  @Override
  public Result<List<Course>> findAll() {
    try {
      List<Course> courses = courseMapper.findAll();
      return Result.success(courses);
    } catch (Exception e) {
      return Result.error("获取课程列表失败：" + e.getMessage());
    }
  }

  @Override
  public Result<Course> findById(Long id) {
    try {
      if (id == null) {
        return Result.error("课程ID不能为空");
      }
      Course course = courseMapper.findById(id);
      if (course == null) {
        return Result.error("课程不存在");
      }
      return Result.success(course);
    } catch (Exception e) {
      return Result.error("获取课程信息失败：" + e.getMessage());
    }
  }

  @Override
  @Transactional
  public Result<Course> save(Course course) {
    try {
      // 数据校验
      if (!validateCourse(course)) {
        return Result.error("课程信息不完整");
      }

      // 检查课程编号是否已存在
      Course existingCourse = courseMapper.findByCourseNo(course.getCourseNo());
      if (existingCourse != null) {
        return Result.error("课程编号已存在");
      }

      int result = courseMapper.insert(course);
      if (result > 0) {
        return Result.success(course, "添加成功");
      } else {
        return Result.error("添加失败");
      }
    } catch (Exception e) {
      return Result.error("添加失败：" + e.getMessage());
    }
  }

  @Override
  @Transactional
  public Result<Course> update(Course course) {
    try {
      // 数据校验
      if (course.getId() == null) {
        return Result.error("课程ID不能为空");
      }
      if (!validateCourse(course)) {
        return Result.error("课程信息不完整");
      }

      // 检查课程是否存在
      Course existingCourse = courseMapper.findById(course.getId());
      if (existingCourse == null) {
        return Result.error("课程不存在");
      }

      // 检查课程编号是否与其他课程重复
      Course courseWithSameNo = courseMapper.findByCourseNo(course.getCourseNo());
      if (courseWithSameNo != null && !courseWithSameNo.getId().equals(course.getId())) {
        return Result.error("课程编号已存在");
      }

      int result = courseMapper.update(course);
      if (result > 0) {
        return Result.success(course, "更新成功");
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
        return Result.error("课程ID不能为空");
      }

      // 检查课程是否存在
      Course course = courseMapper.findById(id);
      if (course == null) {
        return Result.error("课程不存在");
      }

      // TODO: 检查是否有学生选择了该课程

      int result = courseMapper.deleteById(id);
      if (result > 0) {
        return Result.success(null, "删除成功");
      } else {
        return Result.error("删除失败");
      }
    } catch (Exception e) {
      return Result.error("删除失败：" + e.getMessage());
    }
  }

  private boolean validateCourse(Course course) {
    return course != null &&
        StringUtils.hasText(course.getCourseNo()) &&
        StringUtils.hasText(course.getName()) &&
        course.getTeacherId() != null;
  }
}