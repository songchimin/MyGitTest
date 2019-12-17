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

	// 공개 챌린지 리스트
	@RequestMapping("/category_hobby")
	
	public String category_hobby(Model model) {
		
		System.out.println("category_hobby");
		serach_public_list = dao.category_hobby();
		for(int i=0;i<serach_public_list.size();i++)	
		{
			System.out.println(serach_public_list.get(i));
		}
		
		model.addAttribute("search_list", serach_public_list);
		return "/search_list";
	}
	
	@RequestMapping("/category_life")
	
	public String category_life(Model model) {
		
		System.out.println("category_life");
		serach_public_list = dao.category_life();
		for(int i=0;i<serach_public_list.size();i++)	
		{
			System.out.println(serach_public_list.get(i));
		}
		
		model.addAttribute("search_list", serach_public_list);
		return "/search_list";
	}
	
	
	@RequestMapping("/category_capability")
	public String category_capability(Model model) {
		System.out.println("category_capability");
		serach_public_list = dao.category_capability();
		for(int i=0;i<serach_public_list.size();i++)	
		{
			System.out.println(serach_public_list.get(i));
		}
		model.addAttribute("search_list", serach_public_list);
		return "/search_list";
	}
	
	
	@RequestMapping("/category_health")
	public String category_health(Model model) {
		System.out.println("category_health");
		serach_public_list = dao.category_health();
		for(int i=0;i<serach_public_list.size();i++)	
		{
			System.out.println(serach_public_list.get(i));
		}
		model.addAttribute("search_list", serach_public_list);
		return "/search_list";
	}
	
	@RequestMapping("/category_relationship")
	public String category_relationship(Model model) {
		System.out.println("category_relationship");
		serach_public_list = dao.category_relationship();
		for(int i=0;i<serach_public_list.size();i++)	
		{
			System.out.println(serach_public_list.get(i));
		}
		model.addAttribute("search_list", serach_public_list);
		return "/search_list";
	}
	
	@RequestMapping("/category_asset")
	public String category_asset(Model model) {
		System.out.println("category_asset");
		serach_public_list = dao.category_asset();
		for(int i=0;i<serach_public_list.size();i++)	
		{
			System.out.println(serach_public_list.get(i));
		}
		model.addAttribute("search_list", serach_public_list);
		return "/search_list";
	}
	
	@RequestMapping("/bookmake")
	public void bookmake(HttpServletRequest request, 
		    @RequestParam("challenge_num") String challenge_num,
		    @RequestParam("member_id") String member_id){
		
		// 여기서 체크하는 메소드 만들어서 있으면 delete 없으면 insert
		if (dao.bookmakerExist(challenge_num, member_id) == 0) {
			   dao.bookmake(challenge_num,member_id);
			System.out.println("bookmarkerInsert");

		} else {
			dao.bookmakeDelete(challenge_num,member_id);
			System.out.println("bookmarkerDelete");
		}

	}
	 
}

	
