package com.example.trading.model.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public BoardDTO detail(int num) {
		return sqlSession.selectOne("board.detail",num);
	}
	
	@Override
	public void update(BoardDTO dto) {
		sqlSession.update("board.update",dto);
	}

	@Override
	public void delete(int num) {
		sqlSession.delete("board.delete",num);
	}
	
	@Override
	public List<BoardDTO> list(int start, int end, String search_option, String keyword) {
		Map<String,Object> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		map.put("start", start);
		map.put("end", end);
		return sqlSession.selectList("board.list",map);
	}
	
	@Override
	public void increase_hit(int num) {
		sqlSession.update("board.increase_hit",num);
	}
	
	@Override
	public void delete_attach(String file_name) {
		sqlSession.delete("board.delete_attach",file_name);
	}
	
	@Override
	public void delete_attach_board(int num) {
		sqlSession.delete("board.delete_attach_board",num);
	}
	
	@Override
	public void delete_reply_board(int num) {
		sqlSession.delete("reply.delete_reply_board",num);
	}
	
	@Override
	public List<String> list_attach(int num) {
		return sqlSession.selectList("board.list_attach",num);
	}
	
	@Override
	public void insert_attach(String file_name) {
		Map<String,Object> map = new HashMap<>();
		map.put("file_name", file_name);
		sqlSession.insert("board.insert_attach",map);
	}
	
	@Override
	public void update_attach(String file_name, int num) {
		Map<String,Object> map = new HashMap<>();
		map.put("file_name", file_name);
		map.put("num", num);
		sqlSession.insert("board.update_attach",map);
	}

	@Override
	public void insert(BoardDTO dto) {
		sqlSession.insert("board.insert",dto);
	}

	@Override
	public int count(String search_option, String keyword) {
		Map<String,Object> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		return sqlSession.selectOne("board.count",map);
	}
}
