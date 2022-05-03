package com.example.trading.model.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO{

	@Autowired
	SqlSession sqlSession;

	@Override
	public List<UserDTO> list() {
		return sqlSession.selectList("user.list");
	}
	
	@Override
	public UserDTO detail(String userid) {
		return sqlSession.selectOne("user.detail", userid);
	}

	@Override
	public void insert(UserDTO dto) {
		sqlSession.insert("user.insert", dto);
	}

	@Override
	public void delete(String userid) {
		sqlSession.delete("user.delete", userid);
	}

	@Override
	public void update(UserDTO dto) {
		sqlSession.update("user.update", dto);
	}

	@Override
	public void update_no_passwd(UserDTO dto) {
		sqlSession.update("user.update_no_passwd", dto);
	}

	@Override
	public String login(String userid, String passwd) {
		Map<String, String> map = new HashMap<>();
		map.put("userid", userid);
		map.put("passwd", passwd);
		String result = sqlSession.selectOne("user.login", map);
		return result;
	}

	@Override
	public boolean passwd_check(String userid, String passwd) {
		boolean result = false;
		Map<String, String> map = new HashMap<>();
		map.put("userid", userid);
		map.put("passwd", passwd);
		int count = sqlSession.selectOne("user.check_passwd", map);
		if (count == 1)
			result = true;
		return result;
	}
}
