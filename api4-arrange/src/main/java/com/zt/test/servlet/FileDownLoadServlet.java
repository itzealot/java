package com.zt.test.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class FileDownLoadServlet extends HttpServlet {
	private static final long serialVersionUID = -3622487091178180833L;

	public static final Log log = LogFactory.getLog(FileDownLoadServlet.class);

	public static final String CONTENT_TYPE = "text/html; charset=utf-8";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String fileName = request.getParameter("fileName");

		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(super.getServletContext());
		Properties config = (Properties) wac.getBean("config");

		File file = new File(this.getMetadataZipTempDir(config).concat(File.separator).concat(fileName));
		if (file.exists()) {
			response.setContentType(CONTENT_TYPE);
			response.setContentType("application/x-msdownload");
			response.setContentLength((int) file.length());
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(fileName.getBytes("utf-8"), "iso8859-1"));
			BufferedInputStream bis = null;
			try {
				bis = new BufferedInputStream(new FileInputStream(file));
				byte[] b = new byte[1024];
				long k = 0;
				OutputStream os = response.getOutputStream();
				while (k < file.length()) {
					int j = bis.read(b, 0, 1024);
					k += j;
					os.write(b, 0, j);
				}
				os.flush();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			} finally {
				if (bis != null) {
					bis.close();
				}
			}
		}
	}

	private String getMetadataZipTempDir(Properties config) {
		String serverHome = System.getProperty(Constants.APUSIC_SERVER_HOME);
		String zipDir = serverHome + config.getProperty(Constants.ZIP_FILE_TEMP_DIR_PATH);
		return zipDir;
	}

}
