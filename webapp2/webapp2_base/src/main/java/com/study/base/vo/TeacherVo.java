package com.study.base.vo;

import com.study.base.mybatis.page.Pageable;

public class TeacherVo extends Pageable {
	int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
