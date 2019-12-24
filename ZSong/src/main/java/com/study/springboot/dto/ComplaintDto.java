package com.study.springboot.dto;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class ComplaintDto {

	private int complaint_num;
	private int challenge_num;
	private int comment_num;
	private String member_id;
	private String complaint_content;
	private String complaint_state;
	private String complaint_reply;
	private Timestamp complaint_date;
}
