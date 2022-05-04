package com.example.trading.controller.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageUploadController {

	@RequestMapping("/imageUpload.do")
	public void imageUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		OutputStream out = null;
		PrintWriter printwriter = null;
		String filename = upload.getOriginalFilename();
		byte[] bytes = upload.getBytes();
		ServletContext application = request.getSession().getServletContext();
		String uploadPath = application.getRealPath("/WEB-INF/views/images/");
		System.out.println(uploadPath);
		out = new FileOutputStream(new File(uploadPath + filename));
		out.write(bytes);
		printwriter = response.getWriter();
		String fileUrl = request.getContextPath() + "/images/" + filename;
		printwriter.println("{\"filename\" : \"" + filename + "\", \"uploaded\" : 1, \"url\":\"" + fileUrl + "\"}");
		printwriter.flush();
	}
}
