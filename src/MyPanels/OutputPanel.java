package MyPanels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import MainFrame.DBTableModel;
import MainFrame.ExcelBean;
import MainFrame.Feature;
import MainFrame.SQLManager;

public class OutputPanel extends JPanel implements Feature,ActionListener{
	private SQLManager sm;
	private ResultSet rs;
	
	private JPanel jp_top=new JPanel();
	private JPanel jp_bottom=new JPanel();
	
	private JComboBox<String> jcb;//提供出帐票的类型选项
	private JLabel jldate;//标识出帐票的条件--时间
	private JTextField jtf1,jtf2;//提供时间输入的空格，分别代指从时间1开始到时间2结束
	private JLabel jl_zhi;//搜索按钮，单击可以预览当前条件下帐票的输出结果
	private JButton jb_output;//提供帐票出力的按钮
	private JButton jb_clear;//提供清空界面所有数据的功能
	private JScrollPane jsp;//提供存放JTable的滚动容器
	private JTable jt;//提供搜索按钮操作完了之后的数据存放表格
	JTextPane jl_explation=new JTextPane();//提供解释说明
	private String group;//传递进来的全局变量
	private String kinds;
	private String date1;
	private String date2;
	private String output;
	//正常的构造函数
	public OutputPanel(String group){
		this.group=group;
		this.initialComponents();
		this.initialComponentsConstruct();
		this.initialListener();
	}
	public OutputPanel(SQLManager sm){
		this.sm=sm;
	}

	@Override
	public void initialComponents() {
		// TODO Auto-generated method stub
		//
		jcb=new JComboBox<String>(new String[]{"流水表","人员工作工作量","洗片工作量表","报告表(标准)"});
		jldate=new JLabel("时间");
		jtf1=new JTextField(10);
		jtf2=new JTextField(10);
		jl_zhi=new JLabel("至");
		jb_output=new JButton("输出帐票");
		jb_clear=new JButton("清空");
		jl_explation.setText("输出日常流水表：左右两个是填入输出帐票的时间节点\n"
				+ "如：‘我想输出2016年10月10号(包含)到2016年10月30号(包含)’\n"
				+ "则输入：20161010，20161030");
		
	}

	@Override
	public void initialComponentsConstruct() {
		// TODO Auto-generated method stub
		this.setLayout(new BorderLayout());
		
		jp_top.add(jcb);
		jp_top.add(jldate);
		jp_top.add(jtf1);
		jp_top.add(jl_zhi);
		jp_top.add(jtf2);
		
		
		jp_bottom.add(jb_output);
		jp_bottom.add(jb_clear);
		
		this.add(jp_top,BorderLayout.NORTH);
		this.add(jl_explation, BorderLayout.CENTER);
		this.add(jp_bottom, BorderLayout.SOUTH);
		
	}

	@Override
	public void initialComponentsPattern() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialListener() {
		//jl_zhi.addActionListener(this);
		jb_output.addActionListener(this);
		jb_clear.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jtf1.setText("");
				jtf2.setText("");
			}});
		jcb.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				String kinds=jcb.getSelectedItem().toString();
				if(kinds.equals("流水表")){
					jl_explation.setText("输出日常流水表：左右两个是填入输出帐票的时间节点\n"
							+ "如：‘我想输出2016年10月10号(包含)到2016年10月30号(包含)’\n"
							+ "则输入：20161010，20161030");
				}
				if(kinds.equals("人员工作工作量")){
					jl_explation.setText("输出工人工作量表：左右两个框是输出的时间节点\n"
							+ "行为逻辑可参考输出流水表");
				}
				if(kinds.equals("报告表")){
					jl_explation.setText("输出报告表：左右两个框是输出的时间节点\n"
							+ "例子1：我要输出截至2016年10月15日的帐票\n"
							+ "则输出时间条件为：20160101，20161015\n"
							+ "例子2：我要输出截至16年10月份的数据\n"
							+ "则输出时间条件为：20160101,20161031");
				}
				if(kinds.equals("洗片工作量表")){
					jl_explation.setText("");
				}
				if(kinds.equals("报告表(标准)")){
					jl_explation.setText("输出报告表：左右两个框是输出的时间节点\n"
							+ "例子1：我要输出截至2016年10月15日的帐票\n"
							+ "则输出时间条件为：20160101，20161015\n"
							+ "例子2：我要输出截至16年10月份的数据\n"
							+ "则输出时间条件为：20160101,20161031");
				}
			}});
	}
	
	public static void main(String []agrs){
		JFrame jf=new JFrame();
		jf.setSize(500, 500);
		jf.add(new OutputPanel("bumen1"));
		jf.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!check()){
			JOptionPane.showMessageDialog(null, "请检查搜索条件", "搜索失败", JOptionPane.ERROR_MESSAGE);
			return;
		}
		kinds=jcb.getSelectedItem().toString();//获得帐票的种类
		date1=jtf1.getText();//获得时间
		date2=jtf2.getText();//获得时间
		//点击出出帐票的时候
		if(e.getSource()==jb_output){
			JFileChooser jfc=new JFileChooser();
			int i=jfc.showSaveDialog(null);
			if(i==jfc.APPROVE_OPTION){
				File path=jfc.getSelectedFile();//获取对话框的具体地址
				output=path.toString().replace('\\', '/')+".xls";
				if(!output.endsWith(".xls")){
					System.out.println("cuowi");
				}
				System.out.println(output);
				System.out.println(path.toString().replace('\\', '/'));
				//启动线程进行下载，下载完成就可以进行废弃
				Thread th=new Thread(){
					public void run(){
						try {
							System.out.print("线程开始");
							new ExcelBean(output,kinds,date1,date2,group);
							
						} catch (SQLException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "輸出失敗", "excel内部出现问题", JOptionPane.ERROR_MESSAGE);
							return;
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "輸出失敗", "excel内部出现问题", JOptionPane.ERROR_MESSAGE);
							return;
						}
						System.out.println("线程完成");
						
					}
				};
				th.start();
			}
		}
		if(e.getSource()==jb_clear){
			System.out.println("进入亲空");
			jtf1.setText("");
			jtf2.setText("");
		}
		//点击搜索的时候
		if(e.getSource()==jl_zhi){
			//用SQLmanage返回rs
			if(jsp!=null){
				this.remove(jsp);
			}
			if(rs!=null){
				jt=new JTable(new DBTableModel(rs));
				jsp=new JScrollPane(jt);
				this.add(jsp,BorderLayout.CENTER);
			}
			System.out.println(kinds);
			
		}
		
	}
	//提供信息设置的正确与否
	private boolean check(){
		try{
			int date1=Integer.valueOf(jtf1.getText());
			int date2=Integer.valueOf(jtf2.getText());
			if(date1<date2||date1>10000101||date2>10000101||date1<99991231||date2<99991231)
				return true;
				else 
				return false;
		}catch(Exception e){
			return false;
		}
	}

}
