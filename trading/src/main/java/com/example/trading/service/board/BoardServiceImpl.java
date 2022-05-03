package com.example.trading.service.board;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.trading.model.board.BoardDAO;
import com.example.trading.model.board.BoardDTO;


@Service  // service빈
public class BoardServiceImpl implements BoardService {
	@Inject
	BoardDAO boardDao;
	
	@Override
	public void delete_attach(String file_name) {
		boardDao.delete_attach(file_name);
	}
	
	@Override
	public List<String> list_attach(int bno) {
		return boardDao.list_attach(bno);
	}
	
	@Transactional  //트랜젝션 처리
	@Override
	public void insert(BoardDTO dto) {
		boardDao.insert(dto);
		String[] files = dto.getFiles();
		if(files == null)
			return;
		for(String name : files) {
			boardDao.insert_attach(name);
		}
	}

	@Override
	public BoardDTO detail(int bno) {
		return boardDao.detail(bno);
	}
	
	@Transactional
	@Override
	public void update(BoardDTO dto) {
		boardDao.update(dto);
		String[] files = dto.getFiles();
		if(files == null)
			return;
		for(String name : files) {
			boardDao.update_attach(name,dto.getNum());
		}
	}

	@Override
	public void delete(int bno) {	
		boardDao.delete_reply_board(bno);
		boardDao.delete_attach_board(bno);
		boardDao.delete(bno);
	}

	@Override
	public List<BoardDTO> list(int start, int end, String search_option, String keyword) {
		return boardDao.list(start, end, search_option, keyword);
	}

	@Override
	public void increase_hit(int idx) {
		boardDao.increase_hit(idx);
	}

	@Override
	public int count(String search_option, String keyword) {
		return boardDao.count(search_option, keyword);
	}
}
