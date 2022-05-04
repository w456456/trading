package com.example.trading.controller.upload;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {
	
	//첨부파일 저장 디렉토리
//	@Resource(name = "upload_path")
	String upload_path = "d:/study/upload";
	
	@RequestMapping("/upload/input.do")
	public String input() {
		return "upload/input";
	}
	
	@RequestMapping("/upload/upload.do")
	public ModelAndView upload(MultipartFile file, ModelAndView mav) throws Exception{
		String savedName = file.getOriginalFilename(); //파일 이름
		savedName = uploadFile(savedName, file.getBytes()); //uuid를 추가한 파일 이름
		mav.setViewName("upload/upload_result"); //jsp 페이지 이름
		mav.addObject("saved_name", savedName); //jsp 페이지에 전달한 변수 저장
		return mav; //page로 포워드
	}
	
	public String uploadFile(String originalName, byte[] fileData) throws Exception{
		UUID uid = UUID.randomUUID(); //Universal Unique IDentifier 범용 고유 식별자
		String savedName = uid.toString() + "_" + originalName; //파일 이름 중복 방지
		File target = new File(upload_path, savedName); //파일 참조 변수
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}
}
