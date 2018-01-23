package MyPanels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import MainFrame.Feature;
import MainFrame.SQLManager;
import MainFrame.DBTableModel;

public class InsertPerson extends JPanel implements Feature {
	private String group;//全局变量的代值
	
	private JPanel jp = new JPanel();
	private JPanel jp_add = new JPanel();
	private JPanel jp_search = new JPanel();
	
	private JComboBox<String> jcb;
	private JLabel jl_name;
	private JLabel jl_date;
	private JButton jb_delete;
	private JButton jb_add;
	private JButton jb_search;
	private SQLManager SQLManager;
	private JScrollPane jsp = new JScrollPane();
	private DBTableModel DBTableModel;
	private JTable jt;
	private ResultSet rs;
	private JTextField jtf_name;// 提供入职年份的时间，提供搜索条件
	
	JPanel jps=new JPanel();
	

	private JPanel jpw = this;
	

	public InsertPerson(SQLManager SQLManager, String group) {
		this.group=group;
		this.SQLManager = SQLManager;
		this.initialComponents();
		this.initialComponentsConstruct();
		this.initialListener();
	}

	@Override
	public void initialComponents() {
		// TODO Auto-generated method stub
		jcb = new JComboBox<String>(new String[] {"洗片", "拍片" });
		jl_name = new JLabel("名字");
		jb_add = new JButton("添加");
		jb_search = new JButton("搜索");
		jb_delete =new JButton("删除");
		jtf_name = new JTextField(10);// 名字输入框
	}

	@Override
	public void initialComponentsConstruct() {
		jp_add.add(jcb);
		jp_add.add(jl_name);
		jp_add.add(jtf_name);
		jp_add.add(jb_add);
		jp_add.add(jb_search);
		jp_add.add(jb_delete);
		jp.setLayout(new BorderLayout());
		this.setLayout(new BorderLayout());

		// ResultSet rs=SQLManager.submit_search("select workperson,person_prop
		// from workers_table");
		// DBTableModel=new DBTableModel(rs);
		// JTable jt=new JTable(DBTableModel);
		// jt.setPreferredScrollableViewportSize(new Dimension(10,100));
		// jt.setAutoResizeMode(0);
		// FitTableColumns(jt);
		// jt.getTableHeader().setPreferredSize(new
		// Dimension(jt.getTableHeader().getPreferredSize().width,40));
		// jsp=new JScrollPane(jt);

		jp.add(jp_add, BorderLayout.NORTH);

		this.add(jp, BorderLayout.NORTH);
		
		jps.setSize(50, 50);
		this.add(jps, BorderLayout.CENTER);
		//this.add(jsp,BorderLayout.CENTER);
		this.revalidate();

	}

	@Override
	public void initialComponentsPattern() {
		// TODO Auto-generated method stub
	//	jps.setSize(50, 50);

	}

	@Override
	public void initialListener() {
		// TODO Auto-generated method stub
		jb_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int op = JOptionPane.showConfirmDialog(null, "确认添加" + jtf_name.getText() +"?", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(op == JOptionPane.CANCEL_OPTION || op == JOptionPane.CLOSED_OPTION ) return ;
				System.out.println("进入触发内部");
				if (jtf_name.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "输入人名字", "添加失败", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String name = jtf_name.getText();
				String kinds = jcb.getSelectedItem().toString();
				String sql = null;
				if (kinds.equals("洗片")) {
					sql = "INSERT INTO mingfeng_project.xi_workers_table (xi_workperson,bm_name) VALUES ('" + name + "','"+ group +"');";
					
				}
				if (kinds.equals("拍片")) {
					sql = "INSERT INTO mingfeng_project.pai_workers_table (pai_workperson,bm_name) VALUES ('" + name + "','"+ group +"');";
				}
				System.out.println(sql);
				int i = SQLManager.submit_insert(sql);
				if(kinds.equals("洗片")){
					rs = SQLManager.submit_search("select T.x_id 内部标识,T.xi_workperson 人名  from xi_workers_table T where T.bm_name='"+group+"';");
				}else if(kinds.equals("拍片")){
					rs = SQLManager.submit_search("select T.p_id 内部标识,T.pai_workperson 人名  from pai_workers_table T where T.bm_name='"+group+"';");
				}
				
				System.out.println("处理插入语句之后的返回值" + i);
				repainth(rs);
			}

		});
		jb_search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String kinds = jcb.getSelectedItem().toString();
				String name = jtf_name.getText();
				String sql = null;
				if (kinds.equals("洗片") && name.equals("")) {
					sql = " select T.x_id 内部标识,T.xi_workperson 人名  from xi_workers_table T where T.bm_name='"+group+"';";
				} else if (kinds.equals("洗片") && !name.equals("")) {
					sql = "select T.x_id 内部标识,T.xi_workperson 人名 from xi_workers_table T where T.bm_name='"+group+"' and T.xi_workperson='"+name+"';";
				} else if (kinds.equals("拍片") && name.equals("")) {
					sql = "select T.p_id 内部标识,T.pai_workperson 人名 from pai_workers_table T where T.bm_name='"+group+"';";
					System.out.print(sql);
				} else if (kinds.equals("拍片") && !name.equals("")) {
					sql = "select T.p_id 内部标识,T.pai_workperson 人名 from pai_workers_table T where T.bm_name='"+group+"' and T.pai_workperson='"+name+"';";
					System.out.print(sql);
				}
				rs = SQLManager.submit_search(sql);
				repainth(rs);
			}
		});
		jb_delete.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int op = JOptionPane.showConfirmDialog(null, "确认删除？若删除该人员，与其相关的所有工作量将被删除！！若名字输入错误请直接修改该工人名字", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(op == JOptionPane.CANCEL_OPTION || op == JOptionPane.CLOSED_OPTION ) return ;
				int op2 = JOptionPane.showConfirmDialog(null, "请再次确认，该删除不可逆的删除与之关联的所有记录，包括与该人员工作有关的其他人员工作？", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(op2 == JOptionPane.CANCEL_OPTION || op == JOptionPane.CLOSED_OPTION ) return ;
				String kinds=jcb.getSelectedItem().toString();
				String sql=null;//取得删除的sql文
				try{
					int i=jt.getSelectedRow();
					System.out.println("选择的行数"+i);
					//jt.remove(i);
					String str=jt.getValueAt(i, 0).toString();//取得x_id
					System.out.println(str);
					if (kinds.equals("洗片")) {
						sql = "DELETE FROM mingfeng_project.xi_workers_table WHERE x_id ='"+str+"';";
					}  else if (kinds.equals("拍片")) {
						sql = "DELETE FROM mingfeng_project.pai_workers_table WHERE p_id ='"+str+"';";
						System.out.print(sql);
					} 
				}catch(Exception ei){
					JOptionPane.showMessageDialog(null, "那啥，你还没选删哪个呢", "那啥，你还没选删哪个呢", JOptionPane.ERROR_MESSAGE);
				}
				SQLManager.submit_insert(sql);
				if (kinds.equals("洗片")) {
					sql = " select T.x_id 内部标识,T.xi_workperson 人名  from xi_workers_table T where T.bm_name='"+group+"';";
				}  else if (kinds.equals("拍片")) {
					sql = "select T.p_id 内部标识,T.pai_workperson 人名 from pai_workers_table T where T.bm_name='"+group+"';";
					System.out.print(sql);
				} 
				rs = SQLManager.submit_search(sql);
				repainth(rs);
			}});

	}

	private void repainth(ResultSet rs) {
		// TODO Auto-generated method stub
		System.out.println("外面"+(jsp != null));
		if (jsp != null) {
			jps.remove(jsp);
			System.out.println("里面"+(jsp != null));
		}
		DBTableModel = new DBTableModel(rs);
		jt = new JTable(DBTableModel);
		jsp = new JScrollPane(jt);
		
		//jps.add(jsp);
		DefaultTableColumnModel dcm = (DefaultTableColumnModel)jt.getColumnModel(); 
	    dcm.getColumn(0).setMinWidth(0); 
	    dcm.getColumn(0).setMaxWidth(0); 	
		jps.add(jsp);
		this.revalidate();
		
	}

	public void FitTableColumns(JTable myTable) { // 设置表格随内容大小更改大小的方法
		JTableHeader header = myTable.getTableHeader();
		int rowCount = myTable.getRowCount();
		Enumeration columns = myTable.getColumnModel().getColumns();
		while (columns.hasMoreElements()) {
			TableColumn column = (TableColumn) columns.nextElement();
			int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
			int width = (int) myTable.getTableHeader().getDefaultRenderer()
					.getTableCellRendererComponent(myTable, column.getIdentifier(), false, false, -1, col)
					.getPreferredSize().getWidth();
			for (int row = 0; row < rowCount; row++) {
				int preferedWidth = (int) myTable.getCellRenderer(row, col)
						.getTableCellRendererComponent(myTable, myTable.getValueAt(row, col), false, false, row, col)
						.getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth);
			}
			header.setResizingColumn(column);
			column.setWidth(width + myTable.getIntercellSpacing().width);
		}
	}

	public static void main(String agrs[]) {
		JFrame jf = new JFrame();
		jf.setSize(500, 500);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(new InsertPerson(new SQLManager(),"bumen1"));
		jf.setVisible(true);
	}

}
