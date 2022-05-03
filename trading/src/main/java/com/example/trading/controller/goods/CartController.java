package com.example.trading.controller.goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.trading.model.goods.CartDAO;
import com.example.trading.model.goods.CartDTO;

@Controller
@RequestMapping("/goods/cart/*")
public class CartController {
	@Inject
	CartDAO cartDao;
	
	@RequestMapping("delete.do")
	public String delete(int cart_id) {
		cartDao.delete(cart_id);
		return "redirect:/goods/cart/list.do";
	}
	@RequestMapping("deleteAll.do")
	public String deleteAll(HttpSession session) {
		String userid = (String)session.getAttribute("userid");
		if(userid != null) {
			cartDao.delete_all(userid);
		}
		return "redirect:/goods/cart/list.do";
	}
	@RequestMapping("update.do")
	public String update(int[] amount,int[] cart_id,HttpSession session) {
		String userid =  (String) session.getAttribute("userid");
		if(userid == null) {
			return "redirect:/user/login.do";
		}
		for(int i=0;i<cart_id.length;i++) {
			if(amount[i] ==0) {
				cartDao.delete(cart_id[i]);
			} else {
				CartDTO dto = new CartDTO();
				dto.setUserid(userid);
				dto.setCart_id(cart_id[i]);
				dto.setAmount(amount[i]);
				cartDao.modify(dto);
			}
		}
		return "redirect:/goods/cart/list.do";
	}
	@RequestMapping("list.do")
	public ModelAndView list(HttpSession session,ModelAndView mav) {
		Map<String,Object> map = new HashMap<>();
		String userid = (String)session.getAttribute("userid");
		if(userid != null) {
			List<CartDTO> list = cartDao.list(userid);
			int sumMoney = cartDao.sum_money(userid);
			int fee = sumMoney >= 30000 ? 0:2500; //3만원 이상이면 배송료 면제
			map.put("sumMoney", sumMoney);
			map.put("fee", fee);
			map.put("sum", sumMoney+fee);
			map.put("list", list);
			map.put("count", list.size());
			mav.setViewName("goods/cart_list");
			mav.addObject("map",map);
			return mav;
		} else {
			return new ModelAndView("user/login");
		}
	}
	@RequestMapping("insert.do")
	public String insert(CartDTO dto,HttpSession session) {
		String userid = (String)session.getAttribute("userid");
		if(userid == null) {
			return "redirect:/user/login.do";
		}
		dto.setUserid(userid);
		cartDao.insert(dto);
		return "redirect:/goods/cart/list.do";
	}
	@RequestMapping("complete.do")
	public String complete(HttpSession session) {
		String userid = (String)session.getAttribute("userid");
		cartDao.update_complete(userid);
		return "redirect:/goods/cart/list.do";
	}
}
