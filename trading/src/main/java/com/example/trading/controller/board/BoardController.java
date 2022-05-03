package com.example.trading.controller.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.trading.model.board.BoardDTO;
import com.example.trading.model.board.ReplyDAO;
import com.example.trading.service.board.BoardService;
import com.example.trading.service.board.PageUtil;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	@Inject
	BoardService boardService;
	
	@Inject
	ReplyDAO replyDao;
	
	@RequestMapping("write.do")
	public String write() {
		return"board/custom_write";
	}
	
	@RequestMapping("insert.do")
	public String insert(BoardDTO dto, HttpSession session){
		String userid = (String)session.getAttribute("userid");
		dto.setUserid(userid);
		boardService.insert(dto);
		return "redirect:/board/list.do";
	}
	
	@RequestMapping("list.do")
	public ModelAndView list(@RequestParam(defaultValue="1") int cur_page,
							 @RequestParam(defaultValue="all") String search_option,
							 @RequestParam(defaultValue="") String keyword)
	{
		int count = boardService.count(search_option,keyword);
		PageUtil page_info = new PageUtil(count,cur_page);
		int start = page_info.getPageBegin();
		int end = page_info.getPageEnd();
		List<BoardDTO>list = boardService.list(start,end,search_option,keyword);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/custom_list");
		Map<String,Object>map = new HashMap<>();
		map.put("list",list);
		map.put("count",count);
		map.put("search_option",search_option);
		map.put("keyword",keyword);
		map.put("page_info",page_info);
		mav.addObject("map",map);
		return mav;
	}
	
	@RequestMapping("detail.do")
	public ModelAndView detail(int num,int cur_page,String search_option,String keyword) {
		boardService.increase_hit(num);//조회수증가처리
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/custom_view");
		mav.addObject("dto",boardService.detail(num));
		mav.addObject("count",replyDao.count(num));
		mav.addObject("cur_page",cur_page);
		mav.addObject("search_option",search_option);
		mav.addObject("keyword",keyword);
		return mav;
	}
	
	@RequestMapping("update.do")
	public String update(BoardDTO dto){
		boardService.update(dto);
		return "redirect:/board/list.do";
	}
	@RequestMapping("delete.do")
	public String delete(int num){
		boardService.delete(num);
		return "redirect:/board/list.do";
	}
	@RequestMapping("list_attach/{num}")
	@ResponseBody
	public List<String>list_attach(@PathVariable("num")int num) {
		return boardService.list_attach(num);
	}
}
