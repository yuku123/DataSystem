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
	private String group;//ȫ�ֱ����Ĵ�ֵ
	
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
	private JTextField jtf_name;// �ṩ��ְ��ݵ�ʱ�䣬�ṩ��������
	
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
		jcb = new JComboBox<String>(new String[] {"ϴƬ", "��Ƭ" });
		jl_name = new JLabel("����");
		jb_add = new JButton("���");
		jb_search = new JButton("����");
		jb_delete =new JButton("ɾ��");
		jtf_name = new JTextField(10);// ���������
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
				int op = JOptionPane.showConfirmDialog(null, "ȷ�����" + jtf_name.getText() +"?", "ȷ��", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(op == JOptionPane.CANCEL_OPTION || op == JOptionPane.CLOSED_OPTION ) return ;
				System.out.println("���봥���ڲ�");
				if (jtf_name.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "����������", "���ʧ��", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String name = jtf_name.getText();
				String kinds = jcb.getSelectedItem().toString();
				String sql = null;
				if (kinds.equals("ϴƬ")) {
					sql = "INSERT INTO mingfeng_project.xi_workers_table (xi_workperson,bm_name) VALUES ('" + name + "','"+ group +"');";
					
				}
				if (kinds.equals("��Ƭ")) {
					sql = "INSERT INTO mingfeng_project.pai_workers_table (pai_workperson,bm_name) VALUES ('" + name + "','"+ group +"');";
				}
				System.out.println(sql);
				int i = SQLManager.submit_insert(sql);
				if(kinds.equals("ϴƬ")){
					rs = SQLManager.submit_search("select T.x_id �ڲ���ʶ,T.xi_workperson ����  from xi_workers_table T where T.bm_name='"+group+"';");
				}else if(kinds.equals("��Ƭ")){
					rs = SQLManager.submit_search("select T.p_id �ڲ���ʶ,T.pai_workperson ����  from pai_workers_table T where T.bm_name='"+group+"';");
				}
				
				System.out.println("����������֮��ķ���ֵ" + i);
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
				if (kinds.equals("ϴƬ") && name.equals("")) {
					sql = " select T.x_id �ڲ���ʶ,T.xi_workperson ����  from xi_workers_table T where T.bm_name='"+group+"';";
				} else if (kinds.equals("ϴƬ") && !name.equals("")) {
					sql = "select T.x_id �ڲ���ʶ,T.xi_workperson ���� from xi_workers_table T where T.bm_name='"+group+"' and T.xi_workperson='"+name+"';";
				} else if (kinds.equals("��Ƭ") && name.equals("")) {
					sql = "select T.p_id �ڲ���ʶ,T.pai_workperson ���� from pai_workers_table T where T.bm_name='"+group+"';";
					System.out.print(sql);
				} else if (kinds.equals("��Ƭ") && !name.equals("")) {
					sql = "select T.p_id �ڲ���ʶ,T.pai_workperson ���� from pai_workers_table T where T.bm_name='"+group+"' and T.pai_workperson='"+name+"';";
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
				int op = JOptionPane.showConfirmDialog(null, "ȷ��ɾ������ɾ������Ա��������ص����й���������ɾ���������������������ֱ���޸ĸù�������", "ȷ��", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(op == JOptionPane.CANCEL_OPTION || op == JOptionPane.CLOSED_OPTION ) return ;
				int op2 = JOptionPane.showConfirmDialog(null, "���ٴ�ȷ�ϣ���ɾ���������ɾ����֮���������м�¼�����������Ա�����йص�������Ա������", "ȷ��", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(op2 == JOptionPane.CANCEL_OPTION || op == JOptionPane.CLOSED_OPTION ) return ;
				String kinds=jcb.getSelectedItem().toString();
				String sql=null;//ȡ��ɾ����sql��
				try{
					int i=jt.getSelectedRow();
					System.out.println("ѡ�������"+i);
					//jt.remove(i);
					String str=jt.getValueAt(i, 0).toString();//ȡ��x_id
					System.out.println(str);
					if (kinds.equals("ϴƬ")) {
						sql = "DELETE FROM mingfeng_project.xi_workers_table WHERE x_id ='"+str+"';";
					}  else if (kinds.equals("��Ƭ")) {
						sql = "DELETE FROM mingfeng_project.pai_workers_table WHERE p_id ='"+str+"';";
						System.out.print(sql);
					} 
				}catch(Exception ei){
					JOptionPane.showMessageDialog(null, "��ɶ���㻹ûѡɾ�ĸ���", "��ɶ���㻹ûѡɾ�ĸ���", JOptionPane.ERROR_MESSAGE);
				}
				SQLManager.submit_insert(sql);
				if (kinds.equals("ϴƬ")) {
					sql = " select T.x_id �ڲ���ʶ,T.xi_workperson ����  from xi_workers_table T where T.bm_name='"+group+"';";
				}  else if (kinds.equals("��Ƭ")) {
					sql = "select T.p_id �ڲ���ʶ,T.pai_workperson ���� from pai_workers_table T where T.bm_name='"+group+"';";
					System.out.print(sql);
				} 
				rs = SQLManager.submit_search(sql);
				repainth(rs);
			}});

	}

	private void repainth(ResultSet rs) {
		// TODO Auto-generated method stub
		System.out.println("����"+(jsp != null));
		if (jsp != null) {
			jps.remove(jsp);
			System.out.println("����"+(jsp != null));
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

	public void FitTableColumns(JTable myTable) { // ���ñ�������ݴ�С���Ĵ�С�ķ���
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
