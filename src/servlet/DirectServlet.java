package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Output;
import dao.OutputDao;

@WebServlet("/DirectServlet")
public class DirectServlet extends HttpServlet{
	public DirectServlet() {
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String str=new String();
		String name_out = req.getParameter("name_out");
		String s_inString = req.getParameter("name_in");
		String url_out = "/Users/jiaojiao/eclipse-workspace/Lex/WebContent/static/"+name_out;
		File outFile = new File(url_out);
			str = s_inString;
		int n = 0;//标识符序号
		ArrayList<String> list = seperate(str);//字符分割处理
		HashMap<String , Integer> hashMap  = new HashMap<String, Integer>();
		for (int i = 0; i < list.size(); i++) {
			//新的标识符n才+1
			if(identify(list.get(i))==1){
				if(!hashMap.containsKey(list.get(i))) {
				hashMap.put(list.get(i), n);
				}
//				str = "(" + identify(list.get(i)) + "," +list.get(i) + ")";
				str = "(" + identify(list.get(i)) + "," +n+ ")";
				n++;
			}
//			if(identify(list.get(i)) == 1) {
//				str="(1,"+n+")";
//				n++;
//			}
//			else if(identify(list.get(i)) == 3)
			else
				str="(" + identify(list.get(i)) + ",'" +list.get(i) + "')";
			System.out.println(str);
			write(outFile,str);		//也可在文件中读取
		}
		req.setAttribute("url_out", url_out);
		req.getRequestDispatcher("/Read.jsp").forward(req, resp);
	}
	private static int identify(String str) {
    	// 识别整数数：0 | (1|2|3|4|5|6|7|8|9)(0|1|2|3|4|5|6|7|8|9)*
        String intPattern = "(0|[1-9]\\d*)"; 
     // 标识符：<字母>(<字母>|<数字>)*
        String idPattern = "[A-Za-z]([A-Za-z]|\\d)*";
    	//保留字；运算符；界符
    	List<String> list_res = Arrays.asList("if","else","then","while","do","begin","int","double","char","case","default","switch","break","end","begin","var","procedure","with","const","integer","search","void","main","scanf","printf","return");
    	List<String> list_ope = Arrays.asList("+","-","*","/","#",">","<","=",">=","<=",":=",":","**","==","!=","&&","!","||","<<",">>","~","&","?","%");
    	List<String> list_deli = Arrays.asList("(",")",",","[","]","{","}",".",";","'","'","\"");
    	//保留字：3
    	if(list_res.contains(str))
    		return 3;
    	//标识符：1
    	else if(str.matches(idPattern))
            return 1;
    	//常数：2
    	else if(str.matches(intPattern))
    		return 2;
    	//运算符：4
    	else if(list_ope.contains(str))
    		return 4;
    	//界符：5 
    	if(list_deli.contains(str))
    		return 5;
    	//其他：-1
    	else 
    		return -1;

    }
//     预处理（分割字符）
    private static ArrayList<String> seperate(String str) {
        ArrayList<String> list = new ArrayList<>();
        String[] s = str.split("\\s");
        for (String now : s ) {
            if (!now.isEmpty()) {
                if (identify(now) != -1)
                    list.add(now);
                else {
                    // 继续分割子序列
                    list.add(now.charAt(0)+"");
                    for(int i=1;i<now.length();i++) {
                        if(identify(now.charAt(i)+"")==5) {
                        	//分界符只能是为单个字符
                            list.add(now.charAt(i)+"");
                        }else if(identify(now.charAt(i)+"")==4) {
                            String temp=list.get(list.size()-1);
                            if(identify(temp.charAt(temp.length()-1)+"")==4)
                                list.set(list.size()-1, list.get(list.size()-1)+now.charAt(i));
                            else
                                list.add(now.charAt(i)+"");

                        }else if(identify(now.charAt(i)+"")==2){
                            if(identify(list.get(list.size()-1))==2)
                                list.set(list.size()-1, list.get(list.size()-1)+now.charAt(i));
                            else
                                list.add(now.charAt(i)+"");

                        }else {    //标识符
                            if(identify(list.get(list.size()-1))==1||identify(list.get(list.size()-1))==3)
                                list.set(list.size()-1, list.get(list.size()-1)+now.charAt(i));
                            else
                                list.add(now.charAt(i)+"");
                        }
                    }
                }
            }
        }
        return list;
    }
	//写文件
	public static void write(File file,String str) {
	     try {
	             FileWriter fw = new FileWriter(file,true);
	             if(!file.exists())
	            	 file.createNewFile(); 
	             fw.append(str+"\n");
	             fw.flush();
	             fw.close();
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
		
	}
}
