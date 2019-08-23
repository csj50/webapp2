package com.study.base.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.study.base.dao.TeacherMapper;
import com.study.domain.testBean.Teacher;

@Component
public class CacheTestComp {

	@Autowired
	TeacherMapper teacherMapper;
	
	@Cacheable(value="cache.test", key="#id")
	public Teacher getTeacherById(int id) {
		return teacherMapper.getTeacherById(id);
	}
}
