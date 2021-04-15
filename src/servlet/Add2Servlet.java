package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Input;
import bean.Output;
import dao.OutputDao;

@WebServlet("/Add2Servlet")
public class Add2Servlet extends HttpServlet {
	public Add2Servlet() {
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Output output = new Output();
		Input input = new Input();
		String name_in = req.getParameter("name_in");
		String name_out = req.getParameter("name_out");
		output.setName_out(name_out);
		OutputDao outputDao = new OutputDao();
		String info;
		try { 
			Output output2 = outputDao.findOut(name_out);
			if(output2==null) {
				outputDao.addOutput(output);
				RequestDispatcher dispatcher =  req.getRequestDispatcher("/DirectServlet");
				dispatcher.forward(req, resp);
			}
			else {
				info="输出文件名为"+name_out+"的文件已存在！点击确定重新命名！";
				req.setAttribute("outputMessage", info);
				req.getRequestDispatcher("Fail2.jsp").forward(req, resp);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
