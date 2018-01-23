package MainFrame;
import MyPanels.*;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
public class MainFrame extends JFrame{
	private String group;//定义全局变量
	private String name;//用户名
	private String password;//密码

	//声明学院编号的引用
	String coll_id;
	//创建树的各个节点
	private DefaultMutableTreeNode dmtnRoot=
	        new DefaultMutableTreeNode(new MyNode("操作选项","0"));
	private DefaultMutableTreeNode dmtn1=
	        new DefaultMutableTreeNode(new MyNode("系统选项","1"));
	private DefaultMutableTreeNode dmtn2=
	        new DefaultMutableTreeNode(new MyNode("输入数据","2"));
	private DefaultMutableTreeNode dmtn3=
	        new DefaultMutableTreeNode(new MyNode("修改查询","3"));
	private DefaultMutableTreeNode dmtn4=
	        new DefaultMutableTreeNode(new MyNode("输出帐票","4"));
	private DefaultMutableTreeNode dmtn11=
	        new DefaultMutableTreeNode(new MyNode("退出","11"));
	private DefaultMutableTreeNode dmtn13=
	        new DefaultMutableTreeNode(new MyNode("密码修改","13"));
	private DefaultMutableTreeNode dmtn21=
	        new DefaultMutableTreeNode(new MyNode("创建项目","21"));
	private DefaultMutableTreeNode dmtn22=
	        new DefaultMutableTreeNode(new MyNode("日常流水表","22"));
	private DefaultMutableTreeNode dmtn23=
	        new DefaultMutableTreeNode(new MyNode("人员管理","23"));
	private DefaultMutableTreeNode dmtn24=
	        new DefaultMutableTreeNode(new MyNode("报告表","24"));
	private DefaultMutableTreeNode dmtn25=
	        new DefaultMutableTreeNode(new MyNode("合同","25"));
	private DefaultMutableTreeNode dmtn26=
	        new DefaultMutableTreeNode(new MyNode("工资结算单价","26"));
	private DefaultMutableTreeNode dmtn27=
	        new DefaultMutableTreeNode(new MyNode("产值计算单价","27"));
	private DefaultMutableTreeNode dmtn31=
	        new DefaultMutableTreeNode(new MyNode("项目","31"));
	private DefaultMutableTreeNode dmtn32=
	        new DefaultMutableTreeNode(new MyNode("日常流水表","32"));
	private DefaultMutableTreeNode dmtn33=
	        new DefaultMutableTreeNode(new MyNode("人员管理","33"));
	private DefaultMutableTreeNode dmtn34=
	        new DefaultMutableTreeNode(new MyNode("报告表","34"));
	private DefaultMutableTreeNode dmtn35=
	        new DefaultMutableTreeNode(new MyNode("合同管理","35"));
	private DefaultMutableTreeNode dmtn36=
	        new DefaultMutableTreeNode(new MyNode("洗片查询","36"));
	private DefaultMutableTreeNode dmtn37=
	        new DefaultMutableTreeNode(new MyNode("工资结算单价查询","37"));
	private DefaultMutableTreeNode dmtn38=
	        new DefaultMutableTreeNode(new MyNode("产值计算单价查询","38"));
	private DefaultMutableTreeNode dmtn41=
			new DefaultMutableTreeNode(new MyNode("输出表格","41"));
	
	//创建跟节点
	private DefaultTreeModel dtm=new DefaultTreeModel(dmtnRoot);
	//创建树状列表控件
	private JTree jt=new JTree(dtm);
	//创建左边滚动窗口
	private JScrollPane jspz=new JScrollPane(jt);
	//创建面板
	private JPanel jpy=new JPanel();
	//创建分割窗格
	private JSplitPane jsp1=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jspz,jpy);
	//数据库的引用，在构造函数中就进行第一步的连接
	private SQLManager SQLManager;
	
	/*
	 * /声明功能模块引用(声明语句将在后面各模块的开发过程中逐一添加）
	 */
	private ProCrePane ProCrePane;//项目输入
	private InsertDataPane InsertDataPane;//日常流水作业
	private InRePan InRePan;//报告输入
	private InsertPerson InsertPerson;//人员管理
	private OutputPanel OutputPanel;//输出帐票
	private SeRe sere;//查报告表
	private SeWR sewr;//查流水表
	private InHTPane inht ;//插入合同
	private SeHT seht ;//插入合同
	private SearchItem SearchItem;//查询-项目
	private ChangePassword ChangePassword;//修改密码
	private SeXi sexi;//洗片查询
	private InNP innp;//工资结算单价输入
	private SeNP senp;//工资结算单价查询
	private InWP inwp;//工资结算单价输入
	private SeWP sewp;//工资结算单价查询
	/*private insert_daily insert_daily;
	private insert_worker insert_worker;
	private insert_report insert_report;


	private modify_item modify_item;
	private modify_daily modify_daily;
	private modify_worker modify_worker;
	private modify_report modify_report;
	
	private output_personal output_personal;
	private output_daily output_daily;
	private output_item output_item;
	private output_wages output_wages;
	*/

	//声明卡片布局引用
	CardLayout cl;
	//构造函数
	public MainFrame()
	{
		//初始化数据库操作类
		SQLManager=new SQLManager();
		//初始化树状列表控件
		this.initialTree();
		//创建各功能模块对象
		this.initialPanel();
		//为节点注册监听器
		this.addListener();
		//初始化面板
		this.initialJpy();
		//初始化主窗体
		this.initialFrame();
	}
	public MainFrame(SQLManager SQLManager,String group,String name,String password)
	{
		this.group=group;
		this.name=name;
		this.password=password;
		//初始化数据库操作类
		this.SQLManager=SQLManager;
		//初始化树状列表控件
		this.initialTree();
		//创建各功能模块对象
		this.initialPanel();
		//为节点注册监听器
		this.addListener();
		//初始化面板
		this.initialJpy();
		//初始化主窗体
		this.initialFrame();
	}
	
	public void initialPanel()
	{//初始化各功能模块
	     //初始化代码在后面各模块的开发过程中逐一添加
	    /* 
	     *welcome=new Welcome("成绩管理系统")
	     */
		
		setIconImage(Toolkit.getDefaultToolkit().createImage("Testing.png"));
		ProCrePane=new ProCrePane(SQLManager,group);
		InsertDataPane=new InsertDataPane(SQLManager,group);
		InRePan = new InRePan(SQLManager,group);
		OutputPanel=new OutputPanel(group);
		InsertPerson=new InsertPerson(SQLManager,group);
		sere = new SeRe(SQLManager,group);
		sewr = new SeWR(SQLManager,group);
		inht = new InHTPane(SQLManager,group);
		seht = new SeHT(SQLManager,group);
		sexi = new SeXi(SQLManager,group);
		SearchItem=new SearchItem(SQLManager,group);
		ChangePassword=new ChangePassword(SQLManager,group,name,password);
		innp = new InNP(SQLManager,group);
		senp = new SeNP(SQLManager,group);
		inwp = new InWP(SQLManager,group);
		sewp = new SeWP(SQLManager,group);
	}
	//初始化树状列表控件的方法
	public void initialTree()
	{
		dmtnRoot.add(dmtn1);
		dmtnRoot.add(dmtn2);
		dmtnRoot.add(dmtn3);
		dmtnRoot.add(dmtn4);
		dmtn1.add(dmtn11);
		dmtn1.add(dmtn13);
		dmtn2.add(dmtn21);
		dmtn2.add(dmtn22);
		dmtn2.add(dmtn23);
		dmtn2.add(dmtn24);
		dmtn2.add(dmtn25);
		dmtn2.add(dmtn26);
		dmtn2.add(dmtn27);
		dmtn3.add(dmtn31);
		dmtn3.add(dmtn32);
		dmtn3.add(dmtn33);
		dmtn3.add(dmtn34);
		dmtn3.add(dmtn35);
		dmtn3.add(dmtn36);
		dmtn3.add(dmtn37);
		dmtn3.add(dmtn38);
		dmtn4.add(dmtn41);
	}
	public void initialJpy()
	{//将各功能模块添加到面板中
	    //将面板设置为卡片布局
		jpy.setLayout(new CardLayout());
		cl=(CardLayout)jpy.getLayout();
		
		/*
		 * 各功能模块将在后面各模块的开发过程中逐一添加
		*/
		jpy.add(ProCrePane, "ProCrePane");
		jpy.add(InsertDataPane,"InsertDataPane");
		jpy.add(InRePan,"InRePan");
		jpy.add(OutputPanel,"OutputPanel");
		jpy.add(InsertPerson,"InsertPerson");
		jpy.add(sere,"SearchReport");
		jpy.add(sewr,"SearchWorkload");
		jpy.add(inht,"InsertHT");
		jpy.add(seht,"SearchHT");
		jpy.add(SearchItem, "SearchItem");
		jpy.add(sexi, "SearchXi");
		jpy.add(ChangePassword, "ChangePassword");
		jpy.add(innp, "InsertNeiPrice");
		jpy.add(senp, "SearchNeiPrice");
		jpy.add(inwp, "InsertWaiPrice");
		jpy.add(sewp, "SearchWaiPrice");
	}
	public void initialFrame()
	{   //设置窗体的标题、大小及其可见性
		this.add(jsp1);
		jsp1.setDividerLocation(200);
		jsp1.setDividerSize(4);
		//Image image=new ImageIcon("ico.gif").getImage();  
		//this.setIconImage(image);
		this.setTitle("1.0版本企业级数据库写入读出");
		Dimension screenSize = Toolkit.
		             getDefaultToolkit().getScreenSize();
		int centerX=screenSize.width/2;
		int centerY=screenSize.height/2;
		int w=900;//本窗体宽度
		int h=650;//本窗体高度
		//设置窗体出现在屏幕中央
		this.setBounds(centerX-w/2,centerY-h/2-30,w,h);
		this.setVisible(true);
		//窗体全屏
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//设置关闭退出
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void addListener()
	{
		this.addComponentListener(new ComponentAdapter(){
			@Override public void componentResized(ComponentEvent e){
			    // write you code here
				jsp1.setDividerLocation(0.18);
				
			}});
		
		
		
		//为树状列表控件注册鼠标事件监听器
		jt.addMouseListener(
	           new MouseAdapter()
	           {
	           	  public void mouseClicked(MouseEvent e)
	           	  { 
	           	      DefaultMutableTreeNode dmtntemp=(DefaultMutableTreeNode)jt.getLastSelectedPathComponent();
					  MyNode mynode=(MyNode)dmtntemp.getUserObject();
					  String id=mynode.getId();
					  //根据id值显示不同的卡片
					  if(id.equals("0"))
					  {//欢迎页面
					        //cl.show(jpy,"welcome");
					  }
	           	      else if(id.equals("11"))
	           	      {//退出系统
	           	            int i=JOptionPane.showConfirmDialog(jpy,
	           	                 "您确认要退出出系统吗？","询问",
	           	                  JOptionPane.YES_NO_OPTION,
	           	                   JOptionPane.QUESTION_MESSAGE);
	           	      		if(i==0)
	           	      		{
	           	      			System.exit(0);
	           	      		}
	           	      }
	           	       else if(id.equals("13")){
	           	    	  cl.show(jpy,"ChangePassword");
	           	      }
	           	      else if(id.equals("22")){
	           	    	  cl.show(jpy,"InsertDataPane");
	           	      }
	           	      else if(id.equals("21")){
	           	    	  cl.show(jpy, "ProCrePane");
	           	      }
	           	      else if(id.equals("23")){
	           	    	  cl.show(jpy, "InsertPerson");
	           	      }
	           	      else if(id.equals("24")){
	           	    	  cl.show(jpy, "InRePan");
	           	      }else if(id.equals("31")){
	           	    	  cl.show(jpy, "SearchItem");
	           	      }else if(id.equals("41")){
	           	    	  cl.show(jpy, "OutputPanel");
	           	      }else if(id.equals("34")){
	           	    	  cl.show(jpy, "SearchReport");
	           	      }else if(id.equals("32")){
	           	    	  cl.show(jpy, "SearchWorkload");
	           	      }else if(id.equals("25")){
	           	    	  cl.show(jpy, "InsertHT");
	           	      }else if(id.equals("35")){
	           	    	  cl.show(jpy, "SearchHT");
	           	      }else if(id.equals("33")){
	           	    	  cl.show(jpy, "InsertPerson");
	           	      }else if(id.equals("36")){
	           	    	  cl.show(jpy, "SearchXi");
	           	      }else if(id.equals("26")){
	           	    	  cl.show(jpy, "InsertNeiPrice");
	           	      }else if(id.equals("37")){
	           	    	  cl.show(jpy, "SearchNeiPrice");
	           	      }else if(id.equals("27")){
	           	    	  cl.show(jpy, "InsertWaiPrice");
	           	      }else if(id.equals("38")){
	           	    	  cl.show(jpy, "SearchWaiPrice");
	           	      }
	              }
	           }
		                       );
		//将展开节点的鼠标点击次数设为1
		jt.setToggleClickCount(1);
	}
	//自定义的初始化树节点的数据对象的类
	class MyNode
	{
		private String values;
		private String id;
		public MyNode(String values,String id)
		{
			this.values=values;
			this.id=id;
		}
		public String toString()
		{
			return this.values;
		}
		public String getId()
		{
			return this.id;
		}
	}
	public static void main(String args[])	
	{
		new MainFrame();
	}

}
