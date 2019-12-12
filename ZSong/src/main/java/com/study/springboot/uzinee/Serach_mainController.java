package com.study.springboot.uzinee;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.springboot.dao.SerachDao;
import com.study.springboot.dto.Serach_ChallengeDto;

@Controller
@RequestMapping("/uzinee")
public class Serach_mainController {

	@Autowired
	SerachDao dao;

	ArrayList<Serach_ChallengeDto> serach_public_list;

	// 공개 챌린지 리스트
	@RequestMapping("/serach_public_list")
	
	public String serach_public_list(Model model) {
		
		System.out.println("serach_public_list");
		serach_public_list = dao.serach_list();
		for(int i=0;i<serach_public_list.size();i++)	
		{
			System.out.println(serach_public_list.get(i));
		}
		
		model.addAttribute("search_list", serach_public_list);
		return "/search_list";
	}

}

	
