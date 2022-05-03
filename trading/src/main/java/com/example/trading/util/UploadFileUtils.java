package com.example.trading.util;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	//... 파라미터 갯수에 상관없이 뒤부터는 전부 String으로 보겠다는 의미
	static void makeDir(String uploadPath,String... paths) {  
		if(new File(paths[paths.length-1]).exists()) {
			return;
		}
		for(String path:paths) {
			File dirPath = new File(uploadPath + path);
			if(!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}
	static String calcPath(String upload_path) {
		Calendar cal = Calendar.getInstance();
		String year = "/" + cal.get(Calendar.YEAR);
		// 월은 +1을 해야함.
		String month = year + "/" + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		String path = month + "/" + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		makeDir(upload_path,year,month,path);
		return path;
	}
	public static String uploadFile(String uploadPath,String originalName,byte[] fileData) throws Exception {
		UUID uid = UUID.randomUUID();
		String filename = uid.toString() + "_" + originalName;
		String path = calcPath(uploadPath);
		File target = new File(uploadPath + path,filename);
		FileCopyUtils.copy(fileData, target);
		String str = uploadPath + path + "/" + filename;
		return str;
	}
}
