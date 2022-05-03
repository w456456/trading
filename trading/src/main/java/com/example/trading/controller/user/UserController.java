package com.example.trading.controller.user;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.trading.model.user.UserDAOImpl;
import com.example.trading.model.user.UserDTO;


@Controller
@RequestMapping("/user/*")
public class UserController {
	
	@Inject
	UserDAOImpl userDao;
	
	@RequestMapping("list.do")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		List<UserDTO> list = userDao.list();
		System.out.println(list);
		mav.setViewName("user/list");
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping("login.do")
	public String login() {
		return "user/login";
	}
	
	@RequestMapping("join.do")
	public String join() {
		return "user/join";
	}
	
	@RequestMapping("insert.do")
	public String insert(UserDTO dto) {
		userDao.insert(dto);
		return "user/login";
	}
	
	@RequestMapping("login_check.do")
	public ModelAndView login_check(UserDTO dto, HttpSession session, ModelAndView mav) {
		String name = userDao.login(dto.getUserid(), dto.getPasswd());
		if(name != null) {
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", name);
			mav.setViewName("redirect:/");
		} else {
			mav.setViewName("user/login");
			mav.addObject("message", "error");
		}
		return mav;
	}
	
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/user/login.do?message=logout";
	}
	
	@RequestMapping("detail/{userid}")
	public ModelAndView detail(@PathVariable String userid, ModelAndView mav) {
		mav.setViewName("/user/detail");
		mav.addObject("dto", userDao.detail(userid));
		return mav;
	}
	
	@RequestMapping("update.do")
	public String update(@ModelAttribute UserDTO dto, HttpSession session, Model model) {
		if(dto.getPasswd().equals("")) {
			userDao.update_no_passwd(dto);;
		} else {
			userDao.update(dto);			
		}
		session.setAttribute("userid", dto.getUserid());
		session.setAttribute("name", dto.getName());
		return "redirect:/user/detail/" + dto.getUserid();
	}
	
	@RequestMapping("passwd_check.do")
	public String passwd_check() {
		return "user/passwd_check";
	}
	
	@RequestMapping("password_check.do")
	public String password_check(HttpSession session, String passwd, Model model) {
		String userid = (String)session.getAttribute("userid");
		boolean result = userDao.passwd_check(userid, passwd);
		UserDTO dto = userDao.detail(userid);
		if(result) {
			model.addAttribute("dto", dto);
			return "user/edit";
		} else {
			 model.addAttribute("message", "error");
			 return "user/passwd_check";
		}
	}
}
