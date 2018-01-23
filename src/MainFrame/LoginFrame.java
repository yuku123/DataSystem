package MainFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import MainFrame.*;



public class LoginFrame extends JFrame implements ActionListener{
	
	private String group;//全局变量
	private String name;//用户名
	private String password;//密码
	
	private SQLManager SQLManager;//数据库的本地变量
	private ResultSet rs;
	private JPanel jp=new JPanel();//创建用来存放空间的容器
	private JLabel jl0=new JLabel("部     门     组");//创建部门标签
	private JLabel jl1=new JLabel("用    户    名");
	private JLabel jl2=new JLabel("密            码");
	private JLabel jl3=new JLabel("");//正在登陆提示标签
	//用户名和密码输入框
	private JTextField jtf=new JTextField();
	private JPasswordField jpwf=new JPasswordField();
	private JRadioButton[] jrbArray=//创建单选按钮数组
	        {
	        	new JRadioButton("普通用户",true),
	        	new JRadioButton("管理人员")
	        };
	//创建组
	private ButtonGroup bg=new ButtonGroup();
	//创建操作按钮
	private JButton jb1=new JButton("登    陆");
	private JButton jb2=new JButton("重    置");
	private JButton jb3=new JButton("网络设置");
	//创建下拉框
	private JComboBox jcb=null;

	
	//构造器
	public LoginFrame()
	{ 
		SQLManager= new SQLManager();
		initialFrame();//初始化界面
		this.addListener();
		
	    
		
	}
	public void addListener(){
		this.jb1.addActionListener(this);//为登陆按钮注册监听器
		this.jtf.addActionListener(this);//为用户名文本框注册监听器按下回车之后，进行跳转
		this.jpwf.addActionListener(this);//为用户名密码框注册监听器
		this.jb2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jtf.setText("");
				jpwf.setText("");
			}});
		
		jb3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 弹出网络设置Dialog
				new NetDialog();
			}
			
		});
		
	}
	@SuppressWarnings("unchecked")
	public void initialFrame()
	{
		//设为空布局
		jp.setLayout(null);
		this.jl0.setBounds(30, 20, 130, 25);
		this.jp.add(jl0);
		//得到jcb
		Vector v=new Vector();
		this.rs=SQLManager.submit_search("select t.bm_name from bumen_table t;");
		try {
			while(rs.next()){
				v.add(rs.getObject(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jcb=new JComboBox(v);
		jcb.setBounds(120, 20, 130, 25);
		this.jp.add(jcb);
		this.jl1.setBounds(30,60,110,25);
		this.jp.add(jl1);
		this.jtf.setBounds(120,60,130,25);
		this.jp.add(jtf);
		this.jl2.setBounds(30,100,110,25);
		this.jp.add(jl2);
		this.jpwf.setBounds(120,100,130,25);
		this.jpwf.setEchoChar('*');
		this.jp.add(jpwf);
		this.bg.add(jrbArray[0]);
		this.bg.add(jrbArray[1]);
		this.jrbArray[0].setBounds(40,140,100,25);
		this.jp.add(jrbArray[0]);
		this.jrbArray[1].setBounds(145,140,100,25);
		this.jp.add(jrbArray[1]);
		this.jb1.setBounds(5,170,80,30);
		this.jp.add(jb1);
		this.jb2.setBounds(90,170,80,30);
		this.jp.add(jb2);
		this.jb3.setBounds(175,170,100,30);
		this.jp.add(jb3);
		this.jl3.setBounds(40,210,150,25);
		this.jp.add(jl3);
		this.add(jp);
		//设置窗口的标题、大小、位置以及可见性
		this.setTitle("登陆");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX=screenSize.width/2;
		int centerY=screenSize.height/2;
		int w=300;//本窗体宽度
		int h=280;//本窗体高度
		this.setBounds(centerX-w/2,centerY-h/2-100,w,h);//设置窗体出现在屏幕中央
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	//实现ActionListener接口中的方法
	public void actionPerformed(ActionEvent e)
	{
		String kinds=jcb.getSelectedItem().toString();//取得部门种类编号
		group=kinds;//对全局变量进行赋值
		String name=jtf.getText();
		this.name=name;
		String password=String.valueOf(jpwf.getPassword());
		this.password=password;
		if(e.getSource()==jtf){//当输入用户名并回车时
			this.jpwf.requestFocus(true);
		}
		else if(e.getSource()==jpwf){//当输入密码并回车时	
			this.jb1.requestFocus(true);
		}
		if(name.equals("")||password.equals("")){
			JOptionPane.showMessageDialog(null, "用户名与密码错误", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(e.getSource()==jb1){
			//进行查询，登陆，
			//如果普通的用户进行了登陆
			if(jrbArray[0].isSelected()){
				rs=SQLManager.submit_search("select * from pwmanager_table T where T.bm_name='"+kinds+"' and T.p_name='"+name+"' and T.pw=password('"+password+"')");
				try {
					while(!rs.next()){
						JOptionPane.showMessageDialog(null, "检查一下用户名与密码", "无查询结果", JOptionPane.ERROR_MESSAGE);
						return;
					}
					System.out.println("此时全局变量是："+group);
					new MainFrame(this.SQLManager,group,name,password);
					//SQLManager.close;
					this.dispose();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				};
				
			}
			if(jrbArray[1].isSelected())
			try {
				rs=SQLManager.submit_search("select * from mana_table T where T.m_name='"+name+"' and T.pw=password('"+password+"')");
				while(!rs.next()){
					JOptionPane.showMessageDialog(null, "检查一下用户名与密码", "无查询结果", JOptionPane.ERROR_MESSAGE);
					return;
				}
				new ManagerFrame(this.SQLManager);
				//SQLManager.close;
				this.dispose();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	

	
	private class NetDialog extends JDialog {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final JPanel contentPanel = new JPanel();
		private JTextField address;
		private Properties props;
		private JTextField port;

		/**
		 * Create the dialog.
		 */
		public NetDialog() {
			initFrame();
			initData();		
		}

		private void initFrame(){
			setResizable(false);
			setBounds(100, 100, 450, 300);
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			GridBagLayout gbl_contentPanel = new GridBagLayout();
			gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
			gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
			gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			contentPanel.setLayout(gbl_contentPanel);
			
			JLabel addressLabel = new JLabel("\u670D\u52A1\u5668\u5730\u5740");
			GridBagConstraints gbc_addressLabel = new GridBagConstraints();
			gbc_addressLabel.insets = new Insets(10, 5, 5, 5);
			gbc_addressLabel.anchor = GridBagConstraints.EAST;
			gbc_addressLabel.gridx = 0;
			gbc_addressLabel.gridy = 0;
			contentPanel.add(addressLabel, gbc_addressLabel);
		
		
			address = new JTextField();
			GridBagConstraints gbc_address = new GridBagConstraints();
			gbc_address.insets = new Insets(10, 0, 5, 20);
			gbc_address.fill = GridBagConstraints.HORIZONTAL;
			gbc_address.gridx = 1;
			gbc_address.gridy = 0;
			contentPanel.add(address, gbc_address);
			address.setColumns(30);
			
			JLabel port_label = new JLabel("\u7AEF\u53E3\u53F7");
			GridBagConstraints gbc_port_label = new GridBagConstraints();
			gbc_port_label.anchor = GridBagConstraints.EAST;
			gbc_port_label.insets = new Insets(10, 5, 5, 5);
			gbc_port_label.gridx = 0;
			gbc_port_label.gridy = 1;
			contentPanel.add(port_label, gbc_port_label);
			
			port = new JTextField();
			GridBagConstraints gbc_port = new GridBagConstraints();
			gbc_port.insets = new Insets(10, 0, 5, 20);
			gbc_port.fill = GridBagConstraints.HORIZONTAL;
			gbc_port.gridx = 1;
			gbc_port.gridy = 1;
			contentPanel.add(port, gbc_port);
			port.setColumns(30);
		
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton okButton = new JButton("\u4FEE\u6539");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					props = new Properties();
					try {
						props.load(new FileInputStream("mysql.ini"));
						props.setProperty("url" , address.getText().trim());
						props.setProperty("port" , port.getText().trim());
						props.store(new FileOutputStream("mysql.ini"), "modify the data"); 
						JOptionPane.showMessageDialog(null, "修改成功,请重新启动软件", "成功", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} catch (FileNotFoundException e1) {
						System.out.println("找不到 mysql.ini 文件");
						JOptionPane.showMessageDialog(null, "找不到 mysql.ini 文件", "修改失败", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (IOException e1) {
						System.out.println("文件加载错误");
						JOptionPane.showMessageDialog(null, "打不开 mysql.ini 文件", "修改失败", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		
		
			JButton cancelButton = new JButton("\u53D6\u6D88");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
			
			setVisible(true);
		}
		
		
		private void initData(){
			props = new Properties();
			try {
				props.load(new FileInputStream("mysql.ini"));
				String ul = props.getProperty("url");
				String pot = props.getProperty("port");
				address.setText(ul);
				port.setText(pot);

			} catch (FileNotFoundException e) {
				System.out.println("找不到 mysql.ini 文件");
				JOptionPane.showMessageDialog(null, "找不到 mysql.ini 文件", "修改失败", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("文件加载错误");
				JOptionPane.showMessageDialog(null, "打不开 mysql.ini 文件", "修改失败", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}

	
	
	
	
	
	public static void main(String args[])
	{
			//创建登陆窗体对象
		    
			LoginFrame login=new LoginFrame();
	}
}
