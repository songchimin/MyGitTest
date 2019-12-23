package com.study.springboot.selee;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.study.springboot.dao.SeleeDao;
import com.study.springboot.dto.CertificateDto;
import com.study.springboot.dto.CountDto;
import com.study.springboot.dto.FeedDto;
import com.study.springboot.dto.MapDto;

@Controller
@RequestMapping("/selee")
public class SelectListController {
	
	@Autowired
	SeleeDao dao;
	
	ArrayList<CertificateDto> certificateList;
	ArrayList<CountDto> count;
	ArrayList<FeedDto> feedList;
	ArrayList<MapDto> locationList;
	
    // 인증 list
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
	
    // 피드 list
    @RequestMapping("/feed_list")
    public String FeedList(Model model, 
          @RequestParam("member_id") String member_id) {
      
       System.out.println("FeedList");

       feedList = new ArrayList<>();
       feedList = dao.FeedList(member_id);
       
       model.addAttribute("list", feedList);
       return "/feed_list";
    }  
    
    // Map location list
    @RequestMapping("/location_list")
    public String LocationList(Model model, 
          @RequestParam("challenge_num") String challenge_num,
          @RequestParam("member_id") String member_id,
          @RequestParam("certificate_date") String certificate_date) {
      
       System.out.println("LocationList");
       String date = certificate_date.substring(2, 10);
       date = date.replace(".", "/");
       
       System.out.println(challenge_num +"  "+ member_id +"  "+ date);
       locationList = new ArrayList<>();
       locationList = dao.LocationList(challenge_num, member_id, date);
       
       System.out.println(locationList);
       
       model.addAttribute("location_list", locationList);
       return "/location_list";
    }  
}
