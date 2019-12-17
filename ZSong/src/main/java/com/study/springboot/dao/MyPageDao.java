package com.study.springboot.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.MemberDto;
import com.study.springboot.dto.MyPageCdateDto;

@Mapper	
public interface MyPageDao {
	
	public ArrayList<MemberDto> MyPageMainList(String member_id);
	public ArrayList<MyPageCdateDto> MyPageCertificateDate(int member_num);
}
