package wyf.hxl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
public class BorrowBook extends JPanel implements ActionListener{
	//创建分割方向为上下的JSplitePane对象
      private JSplitPane jsp1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true);
	private JPanel jp2=new JPanel();
	//创建按钮数组
	int flag;
	String sql;
	DataBase db;
	private JButton jb2=new JButton("确定");
	private JLabel jl3=new JLabel("您要借阅或预约的书号");
	private JLabel jl4=new JLabel("请输入您的学号");
	//在jsp1添加文本框
	private JTextField jtxt3=new JTextField();
	private JTextField jtxt4=new JTextField();
	//在jp2设置单选框
	private JRadioButton[] jrbArray=
    {new JRadioButton("借阅图书",true),new JRadioButton("预约图书")};
    private ButtonGroup bg=new ButtonGroup();
	Vector<String> head = new Vector<String>();	//创建标题
	{
		head.add("书号");
		head.add("书名");
		head.add("作者");
		head.add("出版社");
		head.add("是否借阅");
		head.add("是否预约");
	}	
	Vector<Vector> data=new Vector<Vector>();//表格数据向量集合    
    DefaultTableModel dtm=new DefaultTableModel(data,head);//创建表格模型   
	JTable jt=new JTable(dtm); //创建Jtable对象	
	JScrollPane jspn=new JScrollPane(jt);//将JTable放进滚动窗体
    public BorrowBook()
    {
    	this.setLayout(new GridLayout(1,1));
    	//把jsp2设置到jsp1的上部窗格
    	jsp1.setTopComponent(jp2);
    	//设置jsp1的下部窗格
    	jsp1.setBottomComponent(jspn);
    	//设置jsp1，jsp2中分割条的初始位置
    	jsp1.setDividerLocation(100);//设置分割控件位置
    	jsp1.setDividerSize(4);//设置分割控件宽度
    	jp2.setLayout(null);    	
		jb2.setBounds(380,20,100,20);//设置按钮的大小与位置
    	//将按钮添加进JPanel
		jp2.add(jb2);
		jb2.addActionListener(this);
		//设置JLabel的坐标
    	jl3.setBounds(80,60,130,20);
    	jl4.setBounds(330,60,100,20);
    	//把JLabel添加进JPanel
    	jp2.add(jl3);
    	jp2.add(jl4);	
    	jtxt3.setBounds(220,60,100,20);
    	jtxt4.setBounds(430,60,100,20);
    	jp2.add(jtxt3);
    	jp2.add(jtxt4);
    	for(int i=0;i<2;i++)
    	{
    		jrbArray[i].setBounds(70+i*150,20,150,20);
    		jp2.add(jrbArray[i]);
    		bg.add(jrbArray[i]);
    	}
    	this.add(jsp1);
    	//设置窗体的标题，大小位置及可见性
        this.setBounds(10,10,800,600);
        this.setVisible(true);  
    }	
    //为事件加载的监听器加上处理事件
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jb2){
			if(jtxt4.getText().equals("")){//为输入为空的情况进行处理
				JOptionPane.showMessageDialog(this,"输入不能为空，请重新输入！！！",
				                      "信息",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			//查询学号文本中所输学号是否存在于STUDENT表中
           	sql="select * from STUDENT where StuNO="+Integer.parseInt(jtxt4.getText().trim());
            db=new DataBase();
			db.selectDb(sql);					
			Vector<Vector> vtemp = new Vector<Vector>();
			try{
				if(!(db.rs.next())){//若学号错误，输出提示对话框
					JOptionPane.showMessageDialog(this,"输入了错误的学号","消息",
					                              JOptionPane.INFORMATION_MESSAGE);
				}
				else{//得到输入学号的学生的姓名和班级
					String stuName=db.rs.getString(2).trim();
					String classes=db.rs.getString(5).trim();
					stuName = new String(stuName.getBytes("ISO-8859-1"),"gb2312");
					classes = new String(classes.getBytes("ISO-8859-1"),"gb2312");
					//若学号正确，则检查该学生是否有权限借书或预约
					if(db.rs.getString(8).trim().equals("否")){//若无权限则输出提示对话框
						JOptionPane.showMessageDialog(this,"您无此权限！！","消息",
					                              JOptionPane.INFORMATION_MESSAGE);
					}
					else{//若有权限，则查找所输入的书号是否存在于Book表中
						sql="select * from Book where BookNO="
						    +Integer.parseInt(jtxt3.getText().trim());
						db.selectDb(sql);
						do{//str6存Book表中记录中第6项，str7存第7项
							String str6=null;
							String str7=null;
							//定义输入书号所对应书的书名和作者
							String bookName=null;
							String author=null;
							if(!(db.rs.next())){//若Book表中没有该书号，则输出提示对话框
								JOptionPane.showMessageDialog(this,	"没有您要查找的内容",
								                 "消息",JOptionPane.INFORMATION_MESSAGE);
							}
						   
							Vector<String> v = new Vector<String>();
							for(int i=1;i<=7;i++){//顺序达到所搜到的结果中的各项记录
								if(i==5){//
									str6=db.rs.getString(i+1);
									str6=new String(str6.getBytes("ISO-8859-1"),"gb2312");
									v.add(str6);
								}
								if(i==6){//
									str7=db.rs.getString(i+1);
									str7=new String(str7.getBytes("ISO-8859-1"),"gb2312");
									v.add(str7);
								}
								if(i==2){//
									bookName=db.rs.getString(i).trim();
									bookName=new String(bookName.getBytes("ISO-8859-1"),"gb2312");
									v.add(bookName);
								}
								if(i==3){//
									author=db.rs.getString(i).trim();
									author=new String(author.getBytes("ISO-8859-1"),"gb2312");
									v.add(author);	
								}	
								if(i==1){//
									String str=db.rs.getString(i).trim();
									str=new String(str.getBytes("ISO-8859-1"),"gb2312");
									v.add(str);
								}
								if(i==4){//
									String str=db.rs.getString(i).trim();
									str=new String(str.getBytes("ISO-8859-1"),"gb2312");
									v.add(str);
								}
							}
							vtemp.add(v);//更新结果框中的内容
							dtm.setDataVector(vtemp,head);
							jt.updateUI();
							jt.repaint();	
					        
							if(jrbArray[0].isSelected()){//选择了借图书
								if(str6.trim().equals("是")){//用户想借的书已经被借走，输出提示信息框
									JOptionPane.showMessageDialog(this,
									"此书已经被借","消息",JOptionPane.INFORMATION_MESSAGE);
								}
							    else if(str7.trim().equals("是")){//用户想借的书已被约不能借，提示
									JOptionPane.showMessageDialog(this,
									"此书已经被预约，不能再借","消息",JOptionPane.INFORMATION_MESSAGE);
								}
								else{// 创建日期对象，以获得当前日期来记录借书时间和应还时间-
									Date now =new Date();
									sql="update BOOK set Borrowed='是' where BookNO="
									     +Integer.parseInt(jtxt3.getText().trim());
									db.updateDb(sql);//成功，则设置该书的Borrowed项为"是",输出借书成功信息框
									JOptionPane.showMessageDialog(this,
									"借书成功","消息",JOptionPane.INFORMATION_MESSAGE);
									sql="insert into RECORD values("+Integer.parseInt(jtxt3.getText().trim())+","
									    +Integer.parseInt(jtxt4.getText().trim())+",'"+(now.getYear()+1900)+"."
									    +(now.getMonth()+1)+"."+now.getDate()+"',"+"'"+(now.getYear()+1900)+"."
									    +(now.getMonth()+2)+"."+now.getDate()+"','否','否')";
									db.updateDb(sql);//将该书记录插入Record表中
								}
							}	
							if(jrbArray[1].isSelected()){//选择了预约图书
								if(str7.trim().equals("是")){	//该书已经被预约，输出提示信息框
									JOptionPane.showMessageDialog(this,
									"此书已经被预约","消息",JOptionPane.INFORMATION_MESSAGE);
								}
								else{//预约成功，设置预约项Ordered为是，表示该书已经被预约
									sql="update BOOK set Ordered='是' where BookNO="
									     +Integer.parseInt(jtxt3.getText().trim());
									db.updateDb(sql);
									//输出预约成功信息框
									JOptionPane.showMessageDialog(this,
									"预约成功","消息",JOptionPane.INFORMATION_MESSAGE);	
									sql="insert into ORDERREPORT values("+Integer.parseInt(jtxt3.getText().trim())
										+",'"+stuName+"','"+classes+"','"+bookName+"',"
									    +Integer.parseInt(jtxt4.getText().trim())+",'"+author+"')";
									db.updateDb(sql);
								}
							}
						}
						while(db.rs.next());							
					}
				}
			}
			catch(Exception ex){ex.printStackTrace();}
			db.dbClose();//关闭数据库链接
		}
	}
}
