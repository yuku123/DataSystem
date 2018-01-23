package MainFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;

import MyPanels.OutputPanel;

public class ManagerFrame extends JFrame implements Feature {
	private JTextField jtf1 = new JTextField(10);
	private JButton jb1_1 = new JButton("创建");
	private JButton jb1_2 = new JButton("搜索");
	private JButton jb1_3 = new JButton("删除");

	private JComboBox jcb = new JComboBox();// 组别下拉框
	private JTextField jtf2 = new JTextField(5);// 新添加人的名字
	private JPasswordField jtf3=new JPasswordField(5);
	private JButton jb2_1 = new JButton("添加");
	private JButton jb2_2 = new JButton("搜索");
	private JButton jb2_3 = new JButton("修改");
	private JButton jb2_4 = new JButton("删除");

	private JPanel jp = new JPanel();// 总承装
	private JPanel jp1 = new JPanel();// 第一条承装
	private JPanel jp2 = new JPanel();// 第二条承装
	private ResultSet rs;
	private SQLManager SQLManager;
	private JScrollPane jsp;
	private DBTableModel DBTableModel;
	private JTable jt;
	String kinds;

	public ManagerFrame(SQLManager SQLManager) {
		this.SQLManager = SQLManager;
		this.initialComponents();

		this.initialComponentsConstruct();
		this.initialComponentsPattern();
		this.initialListener();
		// 设置窗口的标题、大小、位置以及可见性
		this.setTitle("最高权限管理面板");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = screenSize.width / 2;
		int centerY = screenSize.height / 2;
		int w = 600;// 本窗体宽度
		int h = 600;// 本窗体高度
		this.setBounds(centerX - w / 2, centerY - h / 2 - 100, w, h);// 设置窗体出现在屏幕中央
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void initialComponents() {

		// TODO Auto-generated method stub
		Vector v = new Vector();
		this.rs = SQLManager.submit_search("select t.bm_name from bumen_table t;");
		try {
			while (rs.next()) {
				v.add(rs.getObject(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jcb = new JComboBox(v);

	}

	@Override
	public void initialComponentsConstruct() {

		// TODO Auto-generated method stub
		jp1.add(new JLabel("新组别的名字"));
		jp1.add(jtf1);
		jp1.add(jb1_1);
		jp1.add(jb1_2);
		jp1.add(jb1_3);

		jp2.add(jcb);
		jp2.add(new JLabel("人名"));
		jp2.add(jtf2);
		jp2.add(new JLabel("密码"));
		jp2.add(jtf3);
		jp2.add(jb2_1);
		jp2.add(jb2_2);
		jp2.add(jb2_3);
		jp2.add(jb2_4);
		jp.setLayout(new BorderLayout());
		jp.add(jp1, BorderLayout.NORTH);
		jp.add(jp2, BorderLayout.SOUTH);

		this.setLayout(new BorderLayout());
		this.add(jp, BorderLayout.NORTH);
		//this.add(jsp, BorderLayout.CENTER);

	}

	@Override
	public void initialComponentsPattern() {
		// TODO Auto-generated method stub
		jp1.setBorder(BorderFactory.createTitledBorder("增加项目组"));
		jp2.setBorder(BorderFactory.createTitledBorder("增加组别人"));
		
		jtf1.setToolTipText("填入将创建的组别名称");
		jtf2.setToolTipText("填入添加管理人员用户名");
		
		jb2_3.setToolTipText("修改密码时(用户名不变)，在人名处填入将要修改的用户名，密码处填入新密码，点击修改即可");
	}

	@Override
	public void initialListener() {
	
		String name=jtf2.getText();//取得在组别下添加工作人员的账户
		System.out.println();
		//当添加一个组别的时候
		jb1_1.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String group=jtf1.getText();//取得添加组别的名字
				if(group.equals("")){
					JOptionPane.showMessageDialog(null, "输入分组名字", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				
				}
				String sql= "INSERT INTO mingfeng_project.bumen_table (bm_name) VALUES ('"+group+"');";
				System.out.println("语句："+sql);
				int i=SQLManager.submit_insert(sql);
				if(i==1){System.out.println("创建成功");}
				sql="select t.bm_id 内部标识,t.bm_name 项目组别 from bumen_table t";
				rs=SQLManager.submit_search(sql);
				repainth(rs);
				updatejcb();

			}});

		//当组别进行搜索的时候
		jb1_2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sql=null;
				String group=jtf1.getText();//取得添加组别的名字
				if(group.equals("")){
					sql="select t.bm_id 内部标识,t.bm_name 项目组别 from bumen_table t ";
				}else
				sql="select t.bm_id 内部标识,t.bm_name 项目组别 from bumen_table t where t.bm_name='"+group+"'";
				rs=SQLManager.submit_search(sql);
				repainth(rs);
			}});
		//当进行删除的时候
		jb1_3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sql=null;
				try{
					int i=jt.getSelectedRow();
					System.out.println("选择的行数"+i);
					//jt.remove(i);
					String str=jt.getValueAt(i, 0).toString();//取得x_id
					System.out.println(str);
					sql = "DELETE FROM bumen_table WHERE bm_id ='"+str+"';";
					System.out.print(sql);
				} catch(Exception ei){
					JOptionPane.showMessageDialog(null, "你还没选删哪个呢", "你还没选删哪个呢", JOptionPane.ERROR_MESSAGE);
					return;
				}
				SQLManager.submit_insert(sql);
				sql="select t.bm_id 内部标识,t.bm_name 项目组别 from bumen_table t ";
				rs=SQLManager.submit_search(sql);
				repainth(rs);
			}});
		//当第二排进行添加的时候
		jb2_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				kinds=jcb.getSelectedItem().toString();
				String name=jtf2.getText();
				String password=String.valueOf(jtf3.getPassword());
				if(name.equals("")){
					JOptionPane.showMessageDialog(null, "工作人员用户名", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String sql="INSERT INTO pwmanager_table(bm_name, p_name, pw) VALUES ('"+kinds+"', '"+name+"', password('"+password+"'));";
				SQLManager.submit_insert(sql);
				rs=SQLManager.submit_search("select T.pw_id 内部id,T.bm_name 分组名,T.p_name 用户名,T.pw 密码 from pwmanager_table T where T.bm_name='"+jcb.getSelectedItem().toString()+"'");
				repainth(rs);
			
			}});
		//当按下搜索的时候
		jb2_2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String kinds=jcb.getSelectedItem().toString();
				String name=jtf2.getText();
				String sql=null;
				if(name.equals("")){
					sql="select T.pw_id 内部id,T.bm_name 分组名,T.p_name 用户名,T.pw 密码 from pwmanager_table T where T.bm_name='"+kinds+"';";
				}else if(!name.equals("")){
					sql="select T.pw_id 内部id,T.bm_name 分组名,T.p_name 用户名,T.pw 密码 from pwmanager_table T where T.bm_name='"+kinds+"' and T.p_name='"+name+"';";
				}
				rs=SQLManager.submit_search(sql);
				repainth(rs);
			}});
		//进行修改的时候
		jb2_3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sql=null;
				String name=jtf2.getText();
				String password=String.valueOf(jtf3.getPassword());
				if(name.equals("")||password.equals("")){
					JOptionPane.showMessageDialog(null, "修改密码时(用户名不变)，在人名处填入将要修改的用户名，密码处填入新密码，点击修改即可", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try{
					int i=jt.getSelectedRow();
					System.out.println("选择的行数"+i);
					//jt.remove(i);
					String str=jt.getValueAt(i, 0).toString();//取得x_id
					System.out.println(str);
					sql = "UPDATE pwmanager_table SET p_name='"+name+"',pw=password('"+password+"') WHERE pw_id='"+str+"';";
					System.out.print(sql);
				} catch(Exception ei){
					JOptionPane.showMessageDialog(null, "你还没选修改哪个呢", "你还没选修改哪个呢", JOptionPane.ERROR_MESSAGE);
					return;
				
				}
				SQLManager.submit_insert(sql);
				sql="select T.pw_id 内部id,T.bm_name 分组名,T.p_name 用户名,T.pw 密码 from pwmanager_table T where T.bm_name='"+jcb.getSelectedItem().toString()+"'";
				rs=SQLManager.submit_search(sql);
				repainth(rs);
			}});
		//进行删除的时候
		jb2_4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sql=null;
				try{
					int i=jt.getSelectedRow();
					System.out.println("选择的行数"+i);
					//jt.remove(i);
					String str=jt.getValueAt(i, 0).toString();//取得x_id
					System.out.println(str);
					sql = "DELETE FROM pwmanager_table WHERE pw_id ='"+str+"';";
					System.out.print(sql);
				} catch(Exception ei){
					JOptionPane.showMessageDialog(null, "你还没选删哪个呢", "你还没选删哪个呢", JOptionPane.ERROR_MESSAGE);
					return;
				}
				SQLManager.submit_insert(sql);
				sql="select T.pw_id 内部id,T.bm_name 分组名,T.p_name 用户名,T.pw 密码 from pwmanager_table T where T.bm_name='"+jcb.getSelectedItem().toString()+"'";
				rs=SQLManager.submit_search(sql);
				repainth(rs);
			}});
	}
	private void repainth(ResultSet rs) {
		// TODO Auto-generated method stub
		System.out.println("外面"+(jsp != null));
		if (jsp != null) {
			this.remove(jsp);
			System.out.println("里面"+(jsp != null));
		}
		DBTableModel= new DBTableModel(rs);
		jt = new JTable(DBTableModel);
		jsp = new JScrollPane(jt);
		
		//jps.add(jsp);
		DefaultTableColumnModel dcm = (DefaultTableColumnModel)jt.getColumnModel(); 
	    dcm.getColumn(0).setMinWidth(0); 
	    dcm.getColumn(0).setMaxWidth(0); 	
	    this.add(jsp,BorderLayout.CENTER);
	    /*
	     * if (jcb != null) {
			this.remove(jcb);
			System.out.println("里面"+(jsp != null));
		}*/
	    this.updatejcb();
		this.validate();
	}
	//jcb的更新问题
	private void updatejcb(){
		jcb.removeAllItems();;
		Vector v = new Vector();
		this.rs = SQLManager.submit_search("select t.bm_name from bumen_table t;");
		try {
			while (rs.next()) {
				jcb.addItem(rs.getObject(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jcb.setSelectedItem(kinds);
		jcb.repaint();
		jcb.updateUI();
	}
	public static void main(String[] args) {
		new ManagerFrame(new SQLManager());
	}

}
