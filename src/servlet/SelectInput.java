package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Input;
import dao.InputDao;

@WebServlet("/SelectInput")
public class SelectInput extends HttpServlet {
	public SelectInput() {
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		InputDao inputDao = new InputDao();
		try {
			ArrayList<Input> inputs = inputDao.QueryAll();
			HttpSession inSession = req.getSession();
			inSession.setAttribute("inlist", inputs);
			req.getRequestDispatcher("FileIn.jsp").forward(req, resp);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		Input input = new Input();
//		int id = Integer.parseInt(req.getParameter("id"));
//		String name_in = req.getParameter("name_in");
//		input.setName_in(name_in);
//		InputDao outputDao = new InputDao();
//		String info;
//		try {
//			Input output2 = outputDao.findIn(id);
//			if(output2==null) {
//				ArrayList<Input> outputs = outputDao.QueryAll();
//				HttpSession dSession = req.getSession();
//				dSession.setAttribute("inputs", outputs);
//				req.getRequestDispatcher("/ShowInput.jsp").forward(req, resp);;
//			}
//			else {
//				info="输出文件名为"+name_in+"的文件已存在！点击确定重新命名！";
//				req.setAttribute("outputMessage", info);
//				req.getRequestDispatcher("InputError.jsp").forward(req, resp);
//			}
//			
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
