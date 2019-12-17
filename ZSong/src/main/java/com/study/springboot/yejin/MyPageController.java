package com.study.springboot.yejin;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.springboot.dao.MyPageDao;
import com.study.springboot.dto.MemberDto;
import com.study.springboot.dto.MyPageCdateDto;

@Controller
@RequestMapping("/yejin")
public class MyPageController {
	
	@Autowired
	MyPageDao dao;
	
	ArrayList<MemberDto> mypagelist;
	ArrayList<MyPageCdateDto> challengelist;
	
	@RequestMapping("/mypage_main_list")
	public String Mypage_Main_List(Model model, @RequestParam("member_id") String member_id) {
		
		mypagelist = dao.MyPageMainList(member_id);

		model.addAttribute("list", mypagelist);

		return "/mypage";
	}
	
	@RequestMapping("/mypage_challege_date")
	public String MyPageCertificateDate(Model model, @RequestParam("member_num") int member_num) {
		
		challengelist = dao.MyPageCertificateDate(member_num);
		
		model.addAttribute("list", challengelist);
		
		return "/mypage_challengeDate";
	}
	
	@RequestMapping("/mypage_memo_select")
	public String MyPageMemoList(Model model,
			@RequestParam("member_id") String member_id) {
		
	//	challengelist = dao.MyPageCertificateDate(member_num);
		
		model.addAttribute("list", challengelist);
		
		return "/mypage_challengeDate";
	}
	
	@RequestMapping("/mypage_memo_insert")
	public String MyPageMemoInsert(Model model, @RequestParam("member_num") int member_num) {
		
		challengelist = dao.MyPageCertificateDate(member_num);
		
		model.addAttribute("list", challengelist);
		
		return "/mypage_challengeDate";
	}
}
