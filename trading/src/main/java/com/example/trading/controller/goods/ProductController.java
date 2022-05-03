package com.example.trading.controller.goods;

import java.io.File;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.trading.model.goods.ProductDAO;
import com.example.trading.model.goods.ProductDTO;

@Controller
@RequestMapping("/goods/product/*")
public class ProductController {
	@Inject
	ProductDAO productDao;
	
	@RequestMapping("write.do")
	public String write() {
		return "/goods/product_write";
	}
	@RequestMapping("insert.do")
	public String insert(ProductDTO dto,HttpServletRequest request) {
		String filename = "-";
		if(!dto.getFile1().isEmpty()) {
			filename = dto.getFile1().getOriginalFilename();
			try {
				ServletContext application = request.getSession().getServletContext();
				
				//배포경로 지정
				String path = application.getRealPath("/WEB-INF/views/images/");
				new File(path).mkdir();
				dto.getFile1().transferTo(new File(path+filename));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dto.setFilename(filename);
		productDao.insert(dto);
		return "redirect:/goods/product/list.do";
	}
	@RequestMapping("list.do")
	public ModelAndView list(ModelAndView mav) {
		mav.setViewName("/goods/product_list");
		mav.addObject("list",productDao.list());
		return mav;
	}
	@RequestMapping("edit/{product_code}")
	public ModelAndView edit(@PathVariable("product_code") int product_code,ModelAndView mav) {
		mav.setViewName("/goods/product_edit");
		mav.addObject("dto",productDao.detail(product_code));
		return mav;
	}
	@RequestMapping("update.do")
	public String update(ProductDTO dto,HttpServletRequest request) {
		String filename = "-";
		if(!dto.getFile1().isEmpty()) {
			filename = dto.getFile1().getOriginalFilename();
			try {
				ServletContext application = request.getSession().getServletContext();
				String path = application.getRealPath("/WEB-INF/views/images/");
				new File(path).mkdir();
				dto.getFile1().transferTo(new File(path+filename));
			} catch (Exception e) {
				e.printStackTrace();
			}
			dto.setFilename(filename);
		} else {
			ProductDTO dto2 = productDao.detail(dto.getProduct_code());
			dto.setFilename(dto2.getFilename());
		}
		productDao.update(dto);
		return "redirect:/goods/product/list.do";
	}
	@RequestMapping("delete.do")
	public String delete(int product_code,HttpServletRequest request) {
		String filename = productDao.file_info(product_code);
		if(filename != null && !filename.equals("-")) {
			ServletContext application = request.getSession().getServletContext();
			String path = application.getRealPath("/WEB-INF/views/images/");
			File f = new File(path+filename);
			if(f.exists()) f.delete();
		}
		productDao.delete_cart(product_code);
		productDao.delete(product_code);
		return "redirect:/goods/product/list.do";
	}

	@RequestMapping("detail/{product_code}") /* {product_code}: @PathVariable */
	public ModelAndView detail(@PathVariable int product_code,ModelAndView mav) {
		mav.setViewName("/goods/product_detail");
		mav.addObject("dto",productDao.detail(product_code));
		return mav;
	}
}
