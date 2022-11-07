package com.demo.productmanagement.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.FileSystems;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UploadFileUtils {
	
//	private ServletContext app;
		String relativeImgPath = "src/assets/images";
	public String handleUploadFile(MultipartFile uploadedFile) {
//		String folderPath = "src/asserts";
		String absolutePath = FileSystems.getDefault().getPath(relativeImgPath).normalize().toAbsolutePath().toString();
		File myUploadFolder = new File(absolutePath);
		//kiểm tra folder upload file có tồn tại hay không
		//nếu không thì tạo mới thư mục
		if (!myUploadFolder.exists()) {
			myUploadFolder.mkdirs();
		}
		File savedFile = null;
		try {
			//lưu file vào thư mục đã chọn
//			String uuid = UUID.randomUUID().toString();
			SimpleDateFormat dateTimeFormat = new SimpleDateFormat("ddMMyyyyHHmmssSS");
			String uuid = dateTimeFormat.format(new Date()).toString();
			String fileName = uuid + "_" + uploadedFile.getOriginalFilename();
			savedFile = new File(myUploadFolder, fileName);
			uploadedFile.transferTo(savedFile);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return  (relativeImgPath+ savedFile.getName());
	}
}
