package com.woniuxy.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.woniuxy.entities.PageBean;
import com.woniuxy.entities.Student;
import com.woniuxy.tools.DriverManage;

public class StudentDao {
	//查看所有信息
	public List<Student> getAllStu(PageBean<Student> pageBean){
		List<Student> list = new ArrayList<>();
		Connection conn = DriverManage.getConnection();
		String sql = "select * from student where 1=1";
		try {
			//拼接分页sql语句
			sql = sql + " limit ?,?";
			PreparedStatement ps = conn.prepareStatement(sql);
			int count = 0;
			//从哪一页开始显示
			ps.setInt(count+1, (pageBean.getCurrentPage()-1)*pageBean.getPageSize());
			//每页显示的条目数
			ps.setInt(count+2, pageBean.getPageSize());
			ResultSet re = ps.executeQuery();
			while(re.next()) {
				int sid = re.getInt("sid");
				String sname = re.getString("sname");
				int sage = re.getInt("sage");
				String sex = re.getString("sex");
				Student student = new Student(sid, sname, sage, sex);
				list.add(student);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			DriverManage.closeConnection(conn);
		}
	}

	public int getTotalCount() {
		// TODO Auto-generated method stub
		Connection conn = DriverManage.getConnection();
		String sql = "select count(sid) as 'num' from student";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet re = ps.executeQuery();
			if (re.next()) {
				return re.getInt("num");
			}
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally {
			DriverManage.closeConnection(conn);
		}
	}
}
