package com.study.android.mytry.mypage.setup;

public class ComplaintItem {

    private int complaint_num;
    private int challenge_num;
    private int comment_num;
    private String complaint_content;
    private String complaint_state;
    private String complaint_reply;
    private String complaint_date;

    public ComplaintItem(int complaint_num, int challenge_num, int comment_num, String complaint_content, String complaint_state, String complaint_reply, String complaint_date) {
        this.complaint_num = complaint_num;
        this.challenge_num = challenge_num;
        this.comment_num = comment_num;
        this.complaint_content = complaint_content;
        this.complaint_state = complaint_state;
        this.complaint_reply = complaint_reply;
        this.complaint_date = complaint_date;
    }


    public int getComment_num() {
        return comment_num;
    }

    public String getComplaint_state() {
        return complaint_state;
    }

    public String getComplaint_date() {
        return complaint_date;
    }

}
