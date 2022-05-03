package com.example.trading.controller.admin;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.trading.model.admin.AdminDAO;
import com.example.trading.model.admin.AdminDTO;


@Controller
@RequestMapping("/admin/*")
public class AdminController {
	
	@Inject
	AdminDAO adminDao;
	
	@RequestMapping("login.do")
	public String login() {
		return "admin/login";
	}
	
	@RequestMapping("join.do")
	public String join() {
		return "admin/join";
	}
	
	@RequestMapping("insert.do")
	public String insert(AdminDTO dto) {
		adminDao.insert(dto);
		return "admin/login";
	}
	
	@RequestMapping("login_check.do")
	public ModelAndView login_check(AdminDTO dto, HttpSession session, ModelAndView mav) {
		String name = adminDao.login(dto.getAdminid(), dto.getPasswd());
		if(name != null) {
			session.setAttribute("adminid", dto.getAdminid());
			session.setAttribute("name", name);
			mav.setViewName("redirect:/");
		} else {
			mav.setViewName("admin/login");
			mav.addObject("message", "error");
		}
		return mav;
	}
	
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/admin/login.do?message=logout";
	}
	
	@RequestMapping("detail/{adminid}")
	public ModelAndView detail(@PathVariable String adminid, ModelAndView mav) {
		mav.setViewName("/admin/detail");
		mav.addObject("dto", adminDao.detail(adminid));
		return mav;
	}
	
	@RequestMapping("update.do")
	public String update(@ModelAttribute AdminDTO dto, HttpSession session, Model model) {
		if(dto.getPasswd().equals("")) {
			adminDao.update_no_passwd(dto);;
		} else {
			adminDao.update(dto);			
		}
		session.setAttribute("adminid", dto.getAdminid());
		session.setAttribute("name", dto.getName());
		return "redirect:/admin/detail/" + dto.getAdminid();
	}
	
	@RequestMapping("passwd_check.do")
	public String passwd_check() {
		return "admin/passwd_check";
	}
	
	@RequestMapping("password_check.do")
	public String password_check(HttpSession session, String passwd, Model model) {
		String adminid = session.getAttribute("adminid").toString();
		boolean result = adminDao.passwd_check(adminid, passwd);
		AdminDTO dto = adminDao.detail(adminid);
		if(result) {
			model.addAttribute("dto", dto);
			return "admin/edit";
		} else {
			 model.addAttribute("message", "error");
			 return "admin/passwd_check";
		}
	}
}
