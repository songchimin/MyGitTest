package com.study.springboot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.springboot.dao.SeleeDao;
import com.study.springboot.dto.CertificateDto;
import com.study.springboot.dto.ChallengeDto;
import com.study.springboot.dto.CountDto;
import com.study.springboot.dto.MemberDto;

@Controller
@RequestMapping("/selee")
public class SeleeController {

	@Autowired
	SeleeDao dao;
	
	List<MemberDto> userlist;
	ArrayList<ChallengeDto> challengelist;
	ArrayList<CertificateDto> certificateList;
	ArrayList<CountDto> count;
	
	// 피드추가
	@RequestMapping("/feed_insert") 
	public void feed_insert(HttpServletRequest request, 
			@RequestParam("member_id") String member_id, 
			@RequestParam("challenge_num") String challenge_num,
			@RequestParam("challenge_title") String challenge_title, 
			@RequestParam("feed_update_time") String feed_update_time, 
			@RequestParam("feed_image") String feed_image, 
			@RequestParam("feed_comment") String feed_comment) {
		
		System.out.println("feed_insert");
		
		dao.feed_insert(member_id, challenge_num, challenge_title, feed_update_time, feed_image, feed_comment);
		dao.certificate_update(challenge_num, member_id);
	}
	
	// 인증화면
	@RequestMapping("/certificate_list")
	public String CertificateList(Model model, 
			@RequestParam("member_id") String member_id) {
		
		System.out.println("CertificateList");
		
		count  = new ArrayList<>();
		certificateList = new ArrayList<>();
		
		certificateList = dao.CertificateList(member_id);
		
		System.out.println(certificateList.size());
		for(int i = 0 ; i < certificateList.size() ; i++)
		{
			System.out.println("challenge_num : "+ certificateList.get(i).getChallenge_num());
			
			CountDto dto = new CountDto();
			
			dto.setAll_count(dao.CertificateList_count(certificateList.get(i).getChallenge_num() , member_id));
			dto.setCheck_count(dao.CertificateList_check_count(certificateList.get(i).getChallenge_num() , member_id));
			
			count.add(i, dto);
		}
		
		model.addAttribute("count", count);
		model.addAttribute("list", certificateList);
		return "/certificate_list";
	}   
	
	// 참가하기
	@RequestMapping("/record_insert") 
	public void record_insert(HttpServletRequest request, 
			@RequestParam("challenge_num") String challenge_num,
			@RequestParam("member_id") String member_id, 
			@RequestParam("all_count") String all_count, 
			@RequestParam("principal") String principal) {
		
		System.out.println("record_insert");
		
		dao.record_insert(challenge_num, member_id, all_count, principal);
	}
	
	@RequestMapping("/certificate_insert") 
	public void certificate_insert(HttpServletRequest request, 
			@RequestParam("challenge_num") String challenge_num,
			@RequestParam("member_id") String member_id, 
			@RequestParam("challenge_start") String challenge_start, 
			@RequestParam("challenge_end") String challenge_end,
			@RequestParam("challenge_frequency") String challenge_frequency,
			@RequestParam("all_count") int all_count){
		
		System.out.println("certificate_insert");
		System.out.println(challenge_start);
		System.out.println(challenge_end);
		
		String certificate_date = null;
		try {
	        if (challenge_frequency.equals("everyday")) {
	            certificate_date = challenge_start;
	    		for(int i=0; i < all_count; i++) {	
	    			dao.certificate_insert(challenge_num, member_id, certificate_date);
					certificate_date = addDate(certificate_date,0,0,1);
	    		}
	        } else if (challenge_frequency.equals("weekday")) {
	            certificate_date = challenge_start;
	    		for(int i=0; i < all_count; i++) {
	    			for(int j = 0; j < 5; j++) {
	        			dao.certificate_insert(challenge_num, member_id, certificate_date);
	    				certificate_date = addDate(certificate_date,0,0,1);
	    				i++;
	    			}
					certificate_date = addDate(certificate_date,0,0,2);
	    		}
	        } else if (challenge_frequency.equals("everyotherday")) {
	            certificate_date = challenge_start;
	    		for(int i=0; i < all_count; i++) {
	    			for(int j = 0; j < 3; j++) {
	        			dao.certificate_insert(challenge_num, member_id, certificate_date);
	    				certificate_date = addDate(certificate_date,0,0,2);
	    				i++;
	    			}
					certificate_date = addDate(certificate_date,0,0,2);
	    		}
	        } else if (challenge_frequency.equals("weekend")) {
	            certificate_date = challenge_start;
	    		for(int i=0; i < all_count; i++) {
	    			for(int j = 0; j < 2; j++) {
	        			dao.certificate_insert(challenge_num, member_id, certificate_date);
	    				certificate_date = addDate(certificate_date,0,0,1);
	    				i++;
	    			}
					certificate_date = addDate(certificate_date,0,0,5);
	    		}
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String addDate(String dt, int y, int m, int d) throws Exception  {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cal = Calendar.getInstance();
		Date date = (Date) format.parse(dt);
		cal.setTime(date);
        cal.add(Calendar.YEAR, y);		//년 더하기
        cal.add(Calendar.MONTH, m);		//년 더하기
        cal.add(Calendar.DATE, d);		//년 더하기

		return format.format(cal.getTime());
	}	
}
