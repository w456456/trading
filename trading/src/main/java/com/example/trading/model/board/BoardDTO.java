package com.example.trading.model.board;

import java.util.Arrays;
import java.util.Date;

public class BoardDTO {
	private int num;
	private String title;
	private String cont;
	private String userid;
	private Date reg_date;
	private int cnt;
	private String name;
	private int cnt2;
	private String[] files;
	
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public int getCnt2() {
		return cnt2;
	}
	public void setCnt2(int cnt2) {
		this.cnt2 = cnt2;
	}
	public String[] getFiles() {
		return files;
	}
	public void setFiles(String[] files) {
		this.files = files;
	}
	@Override
	public String toString() {
		return "BoardDTO [num=" + num + ", title=" + title + ", cont=" + cont + ", userid=" + userid + ", cnt="
				+ cnt + ", name=" + name + ", cnt2=" + cnt2 + ", files=" + Arrays.toString(files) + "]";
	}
}
