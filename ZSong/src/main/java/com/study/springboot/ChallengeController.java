package com.study.springboot;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springboot.dao.Web_ChallengeDao;
import com.study.springboot.dto.ChallengeDto;
import com.study.springboot.dto.PageInfo;

@Controller
public class ChallengeController {

	@Autowired
	Web_ChallengeDao dao;
	
	List<ChallengeDto> challenge;
	
	
	@RequestMapping("/register_challenge")
	public String register_challenge(HttpServletRequest request, Model model){
		
		
		HttpSession session = null;
		session = request.getSession();
		
		int nPage = 1;
		
		nPage = Integer.parseInt(request.getParameter("page"));
		String option = request.getParameter("findoption");
		String text = request.getParameter("findtext");
				
		
		int listCount = 7;	//한페이지당 보여줄 게시물 갯수
		int pageCount = 5; //하단에 보여줄 리스트 갯수
		
		int totalCount = dao.AllWaitchallengeCount();
				
		int totalPage = totalCount / listCount;
		if(totalCount % listCount > 0)
			totalPage++;
		
		int myCurPage = nPage;
		if(myCurPage > totalPage)
			myCurPage = totalPage;
		if(myCurPage < 1)
			myCurPage = 1;
		
		int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1;
		
		int endPage = startPage + pageCount - 1;
		if(endPage > totalPage)
			endPage = totalPage;

		PageInfo pinfo = new PageInfo();
		
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(myCurPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		request.setAttribute("page", pinfo);
		nPage = pinfo.getCurPage();
		session.setAttribute("cpage", nPage);
		
		int start = (nPage-1)* listCount + 1;
		int end = (nPage-1)*listCount + listCount;

		
		if( option == null ) {
			challenge = dao.All_RegisterWait_challenge(end, start);
		}else if(option.equals("옵션")) {
			challenge = dao.All_RegisterWait_challenge(end, start);
		}else {
			if(option.equals("카테고리")) {
				challenge = dao.FindRegisterWait_challenge_title(end, start, text);
			}else if(option.equals("제목")) {
				challenge = dao.FindRegisterWait_challenge_name(end, start, text);
			}else if(option.equals("날짜")) {
				challenge = dao.FindRegisterWait_challenge_name(end, start, text);
				//수정
			}else if(option.equals("구분")) {
				option = "challenge_public";
				if(text.equals("공개"))
					text = "0";
				else
					text = "1";
				
				challenge = dao.FindRegisterWait_challenge_public(end, start, text);
			}
		}
		
		model.addAttribute("challenge", challenge);

		return "/challenge/register_challenge";
	}
	
	
	@RequestMapping("/vote_public_challenge")
	public String vote_public_challenge(HttpServletRequest request, Model model){
		
		
		HttpSession session = null;
		session = request.getSession();
		
		int nPage = 1;
		
		nPage = Integer.parseInt(request.getParameter("page"));
		String option = request.getParameter("findoption");
		String text = request.getParameter("findtext");

		
		int listCount = 7;	//한페이지당 보여줄 게시물 갯수
		int pageCount = 5; //하단에 보여줄 리스트 갯수
		
		int totalCount = dao.public_challenge_count();
		 
		int totalPage = totalCount / listCount;
		if(totalCount % listCount > 0)
			totalPage++;
		
		int myCurPage = nPage;
		if(myCurPage > totalPage)
			myCurPage = totalPage;
		if(myCurPage < 1)
			myCurPage = 1;
		
		int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1;
		
		int endPage = startPage + pageCount - 1;
		if(endPage > totalPage)
			endPage = totalPage;

		PageInfo pinfo = new PageInfo();
		
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(myCurPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		request.setAttribute("page", pinfo);
		nPage = pinfo.getCurPage();
		session.setAttribute("cpage", nPage);
		
		int start = (nPage-1)* listCount + 1;
		int end = (nPage-1)*listCount + listCount;

		
		if( option == null ) {
			challenge = dao.public_challenge(end, start);
		}else if(option.equals("옵션")) {
			challenge = dao.public_challenge(end, start);
		}else {
			if(option.equals("카테고리")) {
//				challenge = dao.FindRegisterWait_challenge_title(end, start, text);
			}else if(option.equals("제목")) {
//				challenge = dao.FindRegisterWait_challenge_name(end, start, text);
			}else if(option.equals("날짜")) {
//				challenge = dao.FindRegisterWait_challenge_name(end, start, text);
				//수정
			}else if(option.equals("구분")) {
				option = "challenge_public";
				if(text.equals("공개"))
					text = "0";
				else
					text = "1";
				
//				challenge = dao.FindRegisterWait_challenge_public(end, start, text);
			}
		}
		
		
		for(int i=0 ; i < challenge.size() ; i++) {
			challenge.get(i).setCount(dao.challenge_vote_count(challenge.get(i).getChallenge_num()));
		}
		
		model.addAttribute("challenge", challenge);

		return "/challenge/vote_public_challenge";
	}
	
	
	@RequestMapping("/end_challenge")
	public String end_challenge(HttpServletRequest request, Model model){
		
		return "/challenge/end_challenge";
	}
	
	
	@RequestMapping("/challenge_content")
	public String challenge_content(HttpServletRequest request, Model model){
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		ChallengeDto dto = dao.challenge_content(num);
		model.addAttribute("challenge", dto);
		
		if(dto.getChallenge_public().equals("0"))
		{
			model.addAttribute("vote_comment", dao.challenge_content_vote_comment(num));
		}else if(dto.getChallenge_public().equals("1")) {
			
			return "/challenge/challenge_private_content";
		}
		return "/challenge/challenge_content";
	}
	
	
	@RequestMapping("/challenge_vote_content")
	public String challenge_vote_content(HttpServletRequest request, Model model){
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		ChallengeDto dto = dao.public_challenge_content(num);
		model.addAttribute("challenge", dto);
		model.addAttribute("vote_comment", dao.challenge_content_vote_comment(num));

		return "/challenge/challenge_vote_content";
	}
	
	
	
	@RequestMapping("/approval_public_challenge")
	public void approval_public_challenge(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException{
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		dao.approve_public_challenge(num);
		
		StringBuffer result = new StringBuffer("");
		result.append("1");
		response.getWriter().write(result.toString());
	}
	
	

	
	@RequestMapping("/challenge_content_popup")
	public String challenge_content_popup(HttpServletRequest request, Model model){
			
		int num = Integer.parseInt(request.getParameter("num"));
		
		model.addAttribute("challenge", dao.challenge_vote_content(num));
		
		return "/challenge/challenge_content_popup";
	}
	
	
	@RequestMapping("/approval_content")		// 라이트박스 페이지
	public void approval_content(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException{
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String name = request.getParameter("name");
		String type = request.getParameter("check");
		String title = request.getParameter("title");
		String frequency = request.getParameter("frequency");
		String date = request.getParameter("date");
		String arrDate[] = date.split(" - ");
		int fee = Integer.parseInt(request.getParameter("money"));
		fee = fee*10000;
		String time = request.getParameter("time");
		String detail = request.getParameter("detail");
		String expStr = request.getParameter("exp");
		int exp = Integer.parseInt(expStr.replace("exp", ""));
		
		
		arrDate[0]=arrDate[0].replace("/", "-");
		arrDate[1]=arrDate[1].replace("/", "-");
		
		arrDate[0]=arrDate[0]+" 00:00:00.0";
		arrDate[1]=arrDate[1]+" 23:59:59.9";
		
		
		Timestamp start=null;
		Timestamp end=null;	
		
        start = java.sql.Timestamp.valueOf(arrDate[0]);
        end = java.sql.Timestamp.valueOf(arrDate[1]);
		
	
	    System.out.println(num+" "+ name+" "+ title+" "+ type+" "+ frequency+" "+ start+" "+ end+" "+ fee+" "+ time+" "+ detail +" "+ exp);
	    
	    dao.ChallengeApproval(num, name, title, type, frequency, start, end, fee, time, detail, exp);
	    
		StringBuffer result = new StringBuffer("");

		result.append("1");
		
		response.getWriter().write(result.toString());
		
//		return "/account/stop_content";
	}
	
	@RequestMapping("/approval_private_content")		// 라이트박스 페이지
	public void approval_private_content(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException{
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		Timestamp start = null;
		Timestamp end = null;
		
		dao.PrivateChallengeApproval(num);
	    
		StringBuffer result = new StringBuffer("");

		result.append("1");
		
		response.getWriter().write(result.toString());
	}
	
	
	@RequestMapping("/ongoing_challenge")
	public String ongoing_challenge(HttpServletRequest request, Model model){
			
		HttpSession session = null;
		session = request.getSession();
		
		int nPage = 1;
		
		nPage = Integer.parseInt(request.getParameter("page"));
		String option = request.getParameter("findoption");
		String text = request.getParameter("findtext");
				
		
		int listCount = 7;	//한페이지당 보여줄 게시물 갯수
		int pageCount = 5; //하단에 보여줄 리스트 갯수
		
		int totalCount = dao.AllOngoingchallengeCount();
				
		int totalPage = totalCount / listCount;
		if(totalCount % listCount > 0)
			totalPage++;
		
		int myCurPage = nPage;
		if(myCurPage > totalPage)
			myCurPage = totalPage;
		if(myCurPage < 1)
			myCurPage = 1;
		
		int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1;
		
		int endPage = startPage + pageCount - 1;
		if(endPage > totalPage)
			endPage = totalPage;

		PageInfo pinfo = new PageInfo();
		
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(myCurPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		request.setAttribute("page", pinfo);
		nPage = pinfo.getCurPage();
		session.setAttribute("cpage", nPage);
		
		int start = (nPage-1)* listCount + 1;
		int end = (nPage-1)*listCount + listCount;
		
		if( option == null ) {
			challenge = dao.All_Ongoing_challenge(end, start);
		}else if(option.equals("옵션")) {
			challenge = dao.All_Ongoing_challenge(end, start);
		}else {
			if(option.equals("카테고리")) {
				challenge = dao.FindOngoing_challenge_title(end, start, text);
			}else if(option.equals("제목")) {
				challenge = dao.FindOngoing_challenge_name(end, start, text);
			}else if(option.equals("날짜")) {
				challenge = dao.FindOngoing_challenge_name(end, start, text);
				//수정
			}else if(option.equals("구분")) {
				option = "challenge_public";
				if(text.equals("공개"))
					text = "0";
				else
					text = "1";
				
				challenge = dao.FindOngoing_challenge_public(end, start, text);
			}
		}
		
		
		model.addAttribute("challenge", challenge);
		
		return "/challenge/ongoing_challenge";
	}
	
	
	
	@RequestMapping("/reward_challenge")
	public String reward_challenge(HttpServletRequest request, Model model){
			
		HttpSession session = null;
		session = request.getSession();
		
		int nPage = 1;
		
		nPage = Integer.parseInt(request.getParameter("page"));
		String option = request.getParameter("findoption");
		String text = request.getParameter("findtext");
				
		
		int listCount = 7;	//한페이지당 보여줄 게시물 갯수
		int pageCount = 5; //하단에 보여줄 리스트 갯수
		
		int totalCount = dao.reward_challenge_count();
				
		int totalPage = totalCount / listCount;
		if(totalCount % listCount > 0)
			totalPage++;
		
		int myCurPage = nPage;
		if(myCurPage > totalPage)
			myCurPage = totalPage;
		if(myCurPage < 1)
			myCurPage = 1;
		
		int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1;
		
		int endPage = startPage + pageCount - 1;
		if(endPage > totalPage)
			endPage = totalPage;

		PageInfo pinfo = new PageInfo();
		
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(myCurPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		request.setAttribute("page", pinfo);
		nPage = pinfo.getCurPage();
		session.setAttribute("cpage", nPage);
		
		int start = (nPage-1)* listCount + 1;
		int end = (nPage-1)*listCount + listCount;
		
		if( option == null ) {
			challenge = dao.reward_challenge(end, start);
		}else if(option.equals("옵션")) {
			challenge = dao.reward_challenge(end, start);
		}else {
//			if(option.equals("카테고리")) {
//				challenge = dao.FindOngoing_challenge_title(end, start, text);
//			}else if(option.equals("제목")) {
//				challenge = dao.FindOngoing_challenge_name(end, start, text);
//			}else if(option.equals("날짜")) {
//				challenge = dao.FindOngoing_challenge_name(end, start, text);
//				//수정
//			}else if(option.equals("구분")) {
//				option = "challenge_public";
//				if(text.equals("공개"))
//					text = "0";
//				else
//					text = "1";
//				
//				challenge = dao.FindOngoing_challenge_public(end, start, text);
//			}
		}
		
		
		model.addAttribute("challenge", challenge);
		
		return "/challenge/reward_challenge";
	}
	
	
	
	
	@RequestMapping("/ongoing_challenge_content")
	public String ongoing_challenge_content(HttpServletRequest request, Model model){
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		ChallengeDto dto = dao.ongoing_challenge_content(num);
		model.addAttribute("challenge", dto);
		
		
		return "/challenge/ongoing_challenge_content";
	}
}
