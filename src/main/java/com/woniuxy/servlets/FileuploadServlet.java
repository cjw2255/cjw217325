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
			//ʹ��Ĭ�����ô�������������
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//��ȡ������
			ServletFileUpload upload = new ServletFileUpload(factory);
			//�ж��ϴ����Ƿ�Ϊmultipart/form-data����
			if (!upload.isMultipartContent(request)) {
				return;
			}
			//����request��������
			List<FileItem> fileItemList = upload.parseRequest(request);
			//����list����
			for (FileItem fileItem : fileItemList) {
				if (fileItem.isFormField()) {
					//��ͨ�ֶ�
					String name = fileItem.getFieldName();
					String value = fileItem.getString();
					System.out.println(name + "=" + value);
				}else {
					//�ϴ��ļ�
					//��ȡ�ϴ��ļ���
					String fileName = fileItem.getName();
					fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
					//��ȡ������
					in = fileItem.getInputStream();
					//��ȡ�ϴ��ļ�Ŀ¼
					 String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
					 // �ϴ��ļ�����������, ���ȴ���
					 File savePathDir = new File(savePath);
					 if (!savePathDir.exists()) {
						 savePathDir.mkdir();
					 }
					 //��ȡ�����
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
