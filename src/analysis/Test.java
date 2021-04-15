package analysis;

import java.io.*;
import java.util.*;

public class Test {

	public static void main(String[] args) {
		String str=new String();
		String s_inString = new String();
		String url_out = "/Users/jiaojiao/eclipse-workspace/Lex/WebContent/OutFile/Output";
		File inFile,outFile = new File(url_out);
		Scanner in = new Scanner(System.in);
		System.out.println("输入file读入文件,其他的直接输入：");
		s_inString = in.nextLine();
		boolean in_file = false;
		if(s_inString.equals("file")) {
			String url_in = "/Users/jiaojiao/eclipse-workspace/Lex/WebContent/InFile/In1";
			inFile = new File(url_in);
			str = readFile(inFile);
			in_file = true;
		}else {
			str = s_inString;
			in.close();
		}
		//清空上次输出
		if(outFile.exists())
			outFile.delete();
		int n = 0;//标识符序号
		ArrayList<String> list = seperate(str);//字符分割处理
		HashMap<String , Integer> hashMap  = new HashMap<String, Integer>();
		for (int i = 0; i < list.size(); i++) {
			//新的标识符n才+1
			if(identify(list.get(i))==1){
				if(!hashMap.containsKey(list.get(i))) {
				hashMap.put(list.get(i), n++);
				}
//				str = "(" + identify(list.get(i)) + "," +list.get(i) + ")";
				str = "(" + identify(list.get(i)) + "," +n+ ")";
			}
//			if(identify(list.get(i)) == 1) {
//				str="(1,"+n+")";
//				n++;
//			}
//			else if(identify(list.get(i)) == 3)
			else
				str="(" + identify(list.get(i)) + ",'" +list.get(i) + "')";
			System.out.println(str);
			writeFile(outFile,str);		//也可在文件中读取
		}
	}

    private static int identify(String str) {
    	// 识别整数数：0 | (1|2|3|4|5|6|7|8|9)(0|1|2|3|4|5|6|7|8|9)*
        String intPattern = "(0|[1-9]\\d*)"; 
     // 标识符：<字母>(<字母>|<数字>)*
        String idPattern = "[A-Za-z]([A-Za-z]|\\d)*";
    	//保留字；运算符；界符
    	List<String> list_res = Arrays.asList("if","else","then","while","do","begin","int","double","char","case","default","switch","break","end","begin","var","procedure","with","const","integer","search","void","main","scanf","printf","return");
    	List<String> list_ope = Arrays.asList("+","-","*","/","#",">","<","=",">=","<=",":=",":","**","==","!=","&&","!","||","<<",">>","~","&","?","%");
    	List<String> list_deli = Arrays.asList("(",")",",","[","]","{","}",".",";","'","'");
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
        String[] tmp = str.split("\\s");
        for (String t : tmp) {
            String tt = t.trim();
            if (!t.isEmpty()) {
                if (identify(tt) != -1)
                    list.add(tt);
                else {
                    // 继续分割子序列
                    list.add(tt.charAt(0)+"");
                    //System.out.println("ttcharAt(0): "+tt);

                    for(int i=1;i<tt.length();i++) {
                        if(identify(tt.charAt(i)+"")==5) {
                        	//分界符只能是为单个字符
                            list.add(tt.charAt(i)+"");
                        }else if(identify(tt.charAt(i)+"")==4) {
                            String temp=list.get(list.size()-1);
                            if(identify(temp.charAt(temp.length()-1)+"")==4)
                                list.set(list.size()-1, list.get(list.size()-1)+tt.charAt(i));
                            else
                                list.add(tt.charAt(i)+"");

                        }else if(identify(tt.charAt(i)+"")==2){
                            if(identify(list.get(list.size()-1))==2)
                                list.set(list.size()-1, list.get(list.size()-1)+tt.charAt(i));
                            else
                                list.add(tt.charAt(i)+"");

                        }else {    //标识符
                            if(identify(list.get(list.size()-1))==1||identify(list.get(list.size()-1))==3)
                                list.set(list.size()-1, list.get(list.size()-1)+tt.charAt(i));
                            else
                                list.add(tt.charAt(i)+"");
                        }
                    }
                }
            }
        }
        return list;
    }
	//读文件
	public static String readFile(File file) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s = null;
			while ((s = br.readLine()) != null) {
				result.append(System.lineSeparator() + s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	//写文件
	public static void writeFile(File file,String str) {
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