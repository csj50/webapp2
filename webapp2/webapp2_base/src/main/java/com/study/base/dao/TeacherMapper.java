package com.study.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.study.base.mybatis.annotation.MyBatis;
import com.study.base.vo.TeacherVo;
import com.study.domain.testBean.Teacher;

@MyBatis
public interface TeacherMapper {
	/**
	 * 根据id
	 */
	public Teacher getTeacherById(@Param("id") int id);
	/**
	 * 分页查询测试方法
	 * @param teacherVo
	 * @return
	 */
	public List<Teacher> getTeacherPageable(TeacherVo teacherVo);
}
