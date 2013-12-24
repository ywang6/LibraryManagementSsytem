package wyf.hxl;
import javax.swing.*;
import java.sql.*;
public class DataBase
{
	Connection con=null;//声明Connection引用
	Statement stat;
	ResultSet rs;
	int count;
	public static String message;//声明一个静态成员变量
	public static Login log;
	public DataBase(){
		try{//加载MySQL的驱动类，并创建数据库连接
			Class.forName("org.gjt.mm.mysql.Driver");	
			con=DriverManager.getConnection("jdbc:mysql://"+message+"/test","root","");
		 	stat=con.createStatement();//创建Statement对象
		}
		catch(Exception e){//如果从Login类传的参数不对，则提示出错
    	   	JOptionPane.showMessageDialog(log,"用户IP或端口号错误！！！",
    	   	              "信息",JOptionPane.INFORMATION_MESSAGE);
		}	
	}	
	public void selectDb(String sql){//声明select方法
		try{
			rs=stat.executeQuery(sql);
		}
		catch(Exception ie){ie.printStackTrace();}
	}	
	public int updateDb(String sql){//声明update方法
		try{
			sql = new String(sql.getBytes(),"ISO-8859-1");//转码
			count=stat.executeUpdate(sql);
		}
		catch(Exception ie){ie.printStackTrace();}
		return count;		
	}	
	public void dbClose(){//声明close方法		
		try{con.close();}
		catch(Exception e){e.printStackTrace();}	
	}
}