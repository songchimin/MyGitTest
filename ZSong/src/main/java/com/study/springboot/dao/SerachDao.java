package com.study.springboot.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.Serach_ChallengeDto;

@Mapper
public interface SerachDao {
	public ArrayList<Serach_ChallengeDto> serach_list();

	public ArrayList<Serach_ChallengeDto> category_hobby();

	public ArrayList<Serach_ChallengeDto> category_life();

	public ArrayList<Serach_ChallengeDto> category_capability();

	public ArrayList<Serach_ChallengeDto> category_health();

	public ArrayList<Serach_ChallengeDto> category_relationship();

	public ArrayList<Serach_ChallengeDto> category_asset();

	public void bookmake(String challenge_num, String member_id); //북마크 추가

	public int bookmakerExist(String challenge_num, String member_id);

	public void bookmakeDelete(String challenge_num, String member_id);
}
