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
	private JButton jb1_1 = new JButton("����");
	private JButton jb1_2 = new JButton("����");
	private JButton jb1_3 = new JButton("ɾ��");

	private JComboBox jcb = new JComboBox();// ���������
	private JTextField jtf2 = new JTextField(5);// ������˵�����
	private JPasswordField jtf3=new JPasswordField(5);
	private JButton jb2_1 = new JButton("���");
	private JButton jb2_2 = new JButton("����");
	private JButton jb2_3 = new JButton("�޸�");
	private JButton jb2_4 = new JButton("ɾ��");

	private JPanel jp = new JPanel();// �ܳ�װ
	private JPanel jp1 = new JPanel();// ��һ����װ
	private JPanel jp2 = new JPanel();// �ڶ�����װ
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
		// ���ô��ڵı��⡢��С��λ���Լ��ɼ���
		this.setTitle("���Ȩ�޹������");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = screenSize.width / 2;
		int centerY = screenSize.height / 2;
		int w = 600;// ��������
		int h = 600;// ������߶�
		this.setBounds(centerX - w / 2, centerY - h / 2 - 100, w, h);// ���ô����������Ļ����
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
		jp1.add(new JLabel("����������"));
		jp1.add(jtf1);
		jp1.add(jb1_1);
		jp1.add(jb1_2);
		jp1.add(jb1_3);

		jp2.add(jcb);
		jp2.add(new JLabel("����"));
		jp2.add(jtf2);
		jp2.add(new JLabel("����"));
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
		jp1.setBorder(BorderFactory.createTitledBorder("������Ŀ��"));
		jp2.setBorder(BorderFactory.createTitledBorder("���������"));
		
		jtf1.setToolTipText("���뽫�������������");
		jtf2.setToolTipText("������ӹ�����Ա�û���");
		
		jb2_3.setToolTipText("�޸�����ʱ(�û�������)�������������뽫Ҫ�޸ĵ��û��������봦���������룬����޸ļ���");
	}

	@Override
	public void initialListener() {
	
		String name=jtf2.getText();//ȡ�����������ӹ�����Ա���˻�
		System.out.println();
		//�����һ������ʱ��
		jb1_1.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String group=jtf1.getText();//ȡ�������������
				if(group.equals("")){
					JOptionPane.showMessageDialog(null, "�����������", "����", JOptionPane.ERROR_MESSAGE);
					return;
				
				}
				String sql= "INSERT INTO mingfeng_project.bumen_table (bm_name) VALUES ('"+group+"');";
				System.out.println("��䣺"+sql);
				int i=SQLManager.submit_insert(sql);
				if(i==1){System.out.println("�����ɹ�");}
				sql="select t.bm_id �ڲ���ʶ,t.bm_name ��Ŀ��� from bumen_table t";
				rs=SQLManager.submit_search(sql);
				repainth(rs);
				updatejcb();

			}});

		//��������������ʱ��
		jb1_2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sql=null;
				String group=jtf1.getText();//ȡ�������������
				if(group.equals("")){
					sql="select t.bm_id �ڲ���ʶ,t.bm_name ��Ŀ��� from bumen_table t ";
				}else
				sql="select t.bm_id �ڲ���ʶ,t.bm_name ��Ŀ��� from bumen_table t where t.bm_name='"+group+"'";
				rs=SQLManager.submit_search(sql);
				repainth(rs);
			}});
		//������ɾ����ʱ��
		jb1_3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sql=null;
				try{
					int i=jt.getSelectedRow();
					System.out.println("ѡ�������"+i);
					//jt.remove(i);
					String str=jt.getValueAt(i, 0).toString();//ȡ��x_id
					System.out.println(str);
					sql = "DELETE FROM bumen_table WHERE bm_id ='"+str+"';";
					System.out.print(sql);
				} catch(Exception ei){
					JOptionPane.showMessageDialog(null, "�㻹ûѡɾ�ĸ���", "�㻹ûѡɾ�ĸ���", JOptionPane.ERROR_MESSAGE);
					return;
				}
				SQLManager.submit_insert(sql);
				sql="select t.bm_id �ڲ���ʶ,t.bm_name ��Ŀ��� from bumen_table t ";
				rs=SQLManager.submit_search(sql);
				repainth(rs);
			}});
		//���ڶ��Ž�����ӵ�ʱ��
		jb2_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				kinds=jcb.getSelectedItem().toString();
				String name=jtf2.getText();
				String password=String.valueOf(jtf3.getPassword());
				if(name.equals("")){
					JOptionPane.showMessageDialog(null, "������Ա�û���", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String sql="INSERT INTO pwmanager_table(bm_name, p_name, pw) VALUES ('"+kinds+"', '"+name+"', password('"+password+"'));";
				SQLManager.submit_insert(sql);
				rs=SQLManager.submit_search("select T.pw_id �ڲ�id,T.bm_name ������,T.p_name �û���,T.pw ���� from pwmanager_table T where T.bm_name='"+jcb.getSelectedItem().toString()+"'");
				repainth(rs);
			
			}});
		//������������ʱ��
		jb2_2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String kinds=jcb.getSelectedItem().toString();
				String name=jtf2.getText();
				String sql=null;
				if(name.equals("")){
					sql="select T.pw_id �ڲ�id,T.bm_name ������,T.p_name �û���,T.pw ���� from pwmanager_table T where T.bm_name='"+kinds+"';";
				}else if(!name.equals("")){
					sql="select T.pw_id �ڲ�id,T.bm_name ������,T.p_name �û���,T.pw ���� from pwmanager_table T where T.bm_name='"+kinds+"' and T.p_name='"+name+"';";
				}
				rs=SQLManager.submit_search(sql);
				repainth(rs);
			}});
		//�����޸ĵ�ʱ��
		jb2_3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sql=null;
				String name=jtf2.getText();
				String password=String.valueOf(jtf3.getPassword());
				if(name.equals("")||password.equals("")){
					JOptionPane.showMessageDialog(null, "�޸�����ʱ(�û�������)�������������뽫Ҫ�޸ĵ��û��������봦���������룬����޸ļ���", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try{
					int i=jt.getSelectedRow();
					System.out.println("ѡ�������"+i);
					//jt.remove(i);
					String str=jt.getValueAt(i, 0).toString();//ȡ��x_id
					System.out.println(str);
					sql = "UPDATE pwmanager_table SET p_name='"+name+"',pw=password('"+password+"') WHERE pw_id='"+str+"';";
					System.out.print(sql);
				} catch(Exception ei){
					JOptionPane.showMessageDialog(null, "�㻹ûѡ�޸��ĸ���", "�㻹ûѡ�޸��ĸ���", JOptionPane.ERROR_MESSAGE);
					return;
				
				}
				SQLManager.submit_insert(sql);
				sql="select T.pw_id �ڲ�id,T.bm_name ������,T.p_name �û���,T.pw ���� from pwmanager_table T where T.bm_name='"+jcb.getSelectedItem().toString()+"'";
				rs=SQLManager.submit_search(sql);
				repainth(rs);
			}});
		//����ɾ����ʱ��
		jb2_4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sql=null;
				try{
					int i=jt.getSelectedRow();
					System.out.println("ѡ�������"+i);
					//jt.remove(i);
					String str=jt.getValueAt(i, 0).toString();//ȡ��x_id
					System.out.println(str);
					sql = "DELETE FROM pwmanager_table WHERE pw_id ='"+str+"';";
					System.out.print(sql);
				} catch(Exception ei){
					JOptionPane.showMessageDialog(null, "�㻹ûѡɾ�ĸ���", "�㻹ûѡɾ�ĸ���", JOptionPane.ERROR_MESSAGE);
					return;
				}
				SQLManager.submit_insert(sql);
				sql="select T.pw_id �ڲ�id,T.bm_name ������,T.p_name �û���,T.pw ���� from pwmanager_table T where T.bm_name='"+jcb.getSelectedItem().toString()+"'";
				rs=SQLManager.submit_search(sql);
				repainth(rs);
			}});
	}
	private void repainth(ResultSet rs) {
		// TODO Auto-generated method stub
		System.out.println("����"+(jsp != null));
		if (jsp != null) {
			this.remove(jsp);
			System.out.println("����"+(jsp != null));
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
			System.out.println("����"+(jsp != null));
		}*/
	    this.updatejcb();
		this.validate();
	}
	//jcb�ĸ�������
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
