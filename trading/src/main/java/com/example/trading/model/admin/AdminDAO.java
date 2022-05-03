package com.example.trading.model.admin;

import java.util.List;

public interface AdminDAO {
	List<AdminDTO> list();
	AdminDTO detail(String adminid);
	void insert(AdminDTO dto);
	void delete(String adminid);
	void update(AdminDTO dto);
	void update_no_passwd(AdminDTO dto);
	String login(String adminid, String passwd);
	boolean passwd_check(String adminid, String passwd);
}
