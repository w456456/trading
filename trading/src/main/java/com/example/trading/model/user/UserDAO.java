package com.example.trading.model.user;

import java.util.List;

public interface UserDAO {
	List<UserDTO> list();
	UserDTO detail(String userid);
	void insert(UserDTO dto);
	void delete(String userid);
	void update(UserDTO dto);
	void update_no_passwd(UserDTO dto);
	String login(String userid, String passwd);
	boolean passwd_check(String userid, String passwd);
}
