package com.example.trading.model.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository
public class NoticeDAOImpl implements NoticeDAO {
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public NoticeDTO detail(int num) {
		return sqlSession.selectOne("notice.detail",num);
	}
	
	@Override
	public void update(NoticeDTO dto) {
		sqlSession.update("notice.update",dto);
	}

	@Override
	public void delete(int num) {
		sqlSession.delete("notice.delete",num);
	}
	
	@Override
	public List<NoticeDTO> list(int start, int end, String search_option, String keyword) {
		Map<String,Object> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		map.put("start", start);
		map.put("end", end);
		return sqlSession.selectList("notice.list",map);
	}
	
	@Override
	public void increase_hit(int num) {
		sqlSession.update("notice.increase_hit",num);
	}

	@Override
	public void insert(NoticeDTO dto) {
		sqlSession.insert("notice.insert",dto);
	}

	@Override
	public int count(String search_option, String keyword) {
		Map<String,Object> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		return sqlSession.selectOne("notice.count",map);
	}
}
