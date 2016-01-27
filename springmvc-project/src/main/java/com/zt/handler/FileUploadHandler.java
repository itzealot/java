package com.zt.handler;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadHandler {

	@RequestMapping("/testFileUpload")
	public String testFileUpload(@RequestParam("desc") String desc,
			@RequestParam("file") MultipartFile file, HttpServletRequest request)
			throws IOException {
		System.out.println("desc : " + desc);
		System.out.println("OriginalFilename : " + file.getOriginalFilename());
		System.out.println("InputStream : " + file.getInputStream());

		// To get the contextPath
		String contextPath = request.getContextPath();
		System.out.println("contextPath : " + contextPath);

		// To get the Servlet Context's real path
		String path = request.getSession().getServletContext()
				.getRealPath("upload");
		System.out.println("path : " + path);

		// To get the File's name
		String fileName = file.getOriginalFilename();

		// New File's upload dir
		File targetFile = new File(path, fileName);

		// not exist
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// transfer source file to target file
		file.transferTo(targetFile);

		System.out.println("fileUrl : " + request.getContextPath() + "/upload/"
				+ fileName);

		return "success";
	}
}
