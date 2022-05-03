package com.example.trading.model.board;

import java.util.List;

public interface BoardDAO {
	void delete_attach(String file_name);
	void delete_attach_board(int num);
	void delete_reply_board(int num);
	
	List<String> list_attach(int num);
	void insert_attach(String file_name);
	void update_attach(String file_name,int num);
	void insert(BoardDTO dto);
	BoardDTO detail(int num);
	void update(BoardDTO dto);
	void delete(int num);
	List<BoardDTO> list(int start,int end,String search_option,String keyword);
	void increase_hit(int num);
	int count(String search_option, String keyword);
}
