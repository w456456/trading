package com.example.trading.model.notice;

import java.util.Date;

public class NoticeDTO {
	private int num;
	private String title;
	private String cont;
	private String adminid;
	private Date reg_date;
	private int cnt;
	private String name;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public String getAdminid() {
		return adminid;
	}
	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setRegdate(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "BoardDTO [num=" + num + ", title=" + title + ", cont=" + cont + ", adminid=" + adminid + ", cnt="
				+ cnt + ", name=" + name + "]";
	}
}
