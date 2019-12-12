package com.study.springboot.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;


import com.study.springboot.dto.Serach_ChallengeDto;

@Mapper
public interface SerachDao {
	public ArrayList<Serach_ChallengeDto> serach_list();
}
