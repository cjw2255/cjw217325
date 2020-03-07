package com.woniuxy.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileuploadServlet
 */
public class FileuploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileuploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		InputStream in = null;
		OutputStream out = null;
		
		try {
			//使用默认设置创建解析器工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//获取解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			//判断上传表单是否为multipart/form-data类型
			if (!upload.isMultipartContent(request)) {
				return;
			}
			//解析request的输入流
			List<FileItem> fileItemList = upload.parseRequest(request);
			//迭代list集合
			for (FileItem fileItem : fileItemList) {
				if (fileItem.isFormField()) {
					//普通字段
					String name = fileItem.getFieldName();
					String value = fileItem.getString();
					System.out.println(name + "=" + value);
				}else {
					//上传文件
					//获取上传文件名
					String fileName = fileItem.getName();
					fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
					//获取输入流
					in = fileItem.getInputStream();
					//获取上传文件目录
					 String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
					 // 上传文件名若不存在, 则先创建
					 File savePathDir = new File(savePath);
					 if (!savePathDir.exists()) {
						 savePathDir.mkdir();
					 }
					 //获取输出流
					 out = new FileOutputStream(savePath + "\\" + fileName);
					 int len = 0;
					 byte[] buffer = new byte[1024];
					 while ((len=in.read(buffer)) > 0) {
						 out.write(buffer, 0, len);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (in != null) {
				 in.close();
			}
			if (out != null) {
				 out.close();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
