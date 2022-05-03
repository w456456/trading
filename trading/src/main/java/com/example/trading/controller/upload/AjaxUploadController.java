package com.example.trading.controller.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.trading.service.board.BoardService;
import com.example.trading.util.UploadFileUtils;

@Controller
public class AjaxUploadController {
	@Inject
	BoardService boardService;
	
	//@Resource(name = "upload_path")   //servlet-context에 정의된 bean
	String upload_path = "c:/upload";
	
	@RequestMapping("/upload/ajax_form")
	public String upload_form() {
		return "/upload/ajax_form";
	}
	
	// ResponseEntity 데이터 + Http상태코드 리턴
	@ResponseBody //페이지로 포워드되지 않고 데이터를 리턴하는 경우
	@RequestMapping(value="/upload/ajax_upload",produces="text/plain;charset=utf-8")
	public ResponseEntity<String> ajax_upload(MultipartFile file) throws Exception {
		String filename = UploadFileUtils.uploadFile(upload_path, file.getOriginalFilename(), file.getBytes());
		return new ResponseEntity<String>(filename,HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping("/upload/display_file")
	public ResponseEntity<byte[]> display_file(String file_name) throws Exception {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		try {
			HttpHeaders headers = new HttpHeaders();  //http header 객체
			in = new FileInputStream(file_name);
			// uuid를 제외한 실제 파일 이름
			file_name = file_name.substring(file_name.indexOf("_")+1);
			// ContentType 설정
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition", "attachment; filename=\""+file_name+"\"");
			// 첨부파일과 OK코드 리턴
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			if(in != null) in.close();
		}
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value="/upload/delete_file",method=RequestMethod.POST)
	public ResponseEntity<String> delete_file(String file_name) {
		//첨부파일삭제
		// /2022/05/15/uuid파일이름
		// 윈도우\ 리눅스/
		new File(file_name.replace("/", File.separator)).delete();
		boardService.delete_attach(file_name); //레코드 삭제
		// 문자열과 Ok코드 리턴
		return new ResponseEntity<String>("deleted",HttpStatus.OK);
	}
}
