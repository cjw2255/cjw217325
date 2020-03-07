package com.woniuxy.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woniuxy.daos.StudentDao;
import com.woniuxy.entities.PageBean;
import com.woniuxy.entities.Student;

/**
 * Servlet implementation class StuServlet
 */
public class StuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		StudentDao studentDao = new StudentDao();
		/*
		 * 分页处理
		 */
		//设置总行数
		PageBean<Student> pageBean = new PageBean<Student>();
		int totalCount = studentDao.getTotalCount();
		pageBean.setTotalCount(totalCount);
		//设置每页显示的条目数
		int pageSize = 5;
		String newPageSize =request.getParameter("pageSize");
		if (newPageSize!=null) {
			pageSize = Integer.parseInt(newPageSize);
		}
		pageBean.setPageSize(pageSize);
		//设置当前页
		int currentPage = 1;
		String newCurrentPage =request.getParameter("currentPage");
		if (newCurrentPage!=null) {
			currentPage = Integer.parseInt(newCurrentPage);
		}
		if (currentPage<1) {
			currentPage = 1;
		}
		if (currentPage > pageBean.getPages()) {
			currentPage = pageBean.getPages();
			if (pageBean.getPages() == 0) {
				currentPage = 1;
			}
		}
		pageBean.setCurrentPage(currentPage);
		//设置每页显示的数据
		List<Student> list = studentDao.getAllStu(pageBean);
		pageBean.setData(list);
		request.setAttribute("student", pageBean);
		request.getRequestDispatcher("StuView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
