package com.example.trading.model.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<AdminDTO> list() {
		return sqlSession.selectList("admin.list");
	}
	
	@Override
	public AdminDTO detail(String adminid) {
		return sqlSession.selectOne("admin.detail", adminid);
	}

	@Override
	public void insert(AdminDTO dto) {
		sqlSession.insert("admin.insert", dto);
	}

	@Override
	public void delete(String adminid) {
		sqlSession.delete("admin.delete", adminid);
	}

	@Override
	public void update(AdminDTO dto) {
		sqlSession.update("admin.update", dto);
	}
	
	@Override
	public void update_no_passwd(AdminDTO dto) {
		sqlSession.update("admin.update_no_passwd", dto);
	}

	@Override
	public String login(String adminid, String passwd) {
		Map<String, String> map = new HashMap<>();
		map.put("adminid", adminid);
		map.put("passwd", passwd);
		String result = sqlSession.selectOne("admin.login", map);
		return result;
	}
	
	@Override
	public boolean passwd_check(String adminid, String passwd) {
		boolean result = false;
		Map<String, String> map = new HashMap<>();
		map.put("adminid", adminid);
		map.put("passwd", passwd);
		int count = sqlSession.selectOne("admin.check_passwd", map);
		if(count == 1) result = true;
		return result;
	}

}
