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
	
	private JComboBox<String> jcb;//�ṩ����Ʊ������ѡ��
	private JLabel jldate;//��ʶ����Ʊ������--ʱ��
	private JTextField jtf1,jtf2;//�ṩʱ������Ŀո񣬷ֱ��ָ��ʱ��1��ʼ��ʱ��2����
	private JLabel jl_zhi;//������ť����������Ԥ����ǰ��������Ʊ��������
	private JButton jb_output;//�ṩ��Ʊ�����İ�ť
	private JButton jb_clear;//�ṩ��ս����������ݵĹ���
	private JScrollPane jsp;//�ṩ���JTable�Ĺ�������
	private JTable jt;//�ṩ������ť��������֮������ݴ�ű��
	JTextPane jl_explation=new JTextPane();//�ṩ����˵��
	private String group;//���ݽ�����ȫ�ֱ���
	private String kinds;
	private String date1;
	private String date2;
	private String output;
	//�����Ĺ��캯��
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
		jcb=new JComboBox<String>(new String[]{"��ˮ��","��Ա����������","ϴƬ��������","�����(��׼)"});
		jldate=new JLabel("ʱ��");
		jtf1=new JTextField(10);
		jtf2=new JTextField(10);
		jl_zhi=new JLabel("��");
		jb_output=new JButton("�����Ʊ");
		jb_clear=new JButton("���");
		jl_explation.setText("����ճ���ˮ���������������������Ʊ��ʱ��ڵ�\n"
				+ "�磺���������2016��10��10��(����)��2016��10��30��(����)��\n"
				+ "�����룺20161010��20161030");
		
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
				if(kinds.equals("��ˮ��")){
					jl_explation.setText("����ճ���ˮ���������������������Ʊ��ʱ��ڵ�\n"
							+ "�磺���������2016��10��10��(����)��2016��10��30��(����)��\n"
							+ "�����룺20161010��20161030");
				}
				if(kinds.equals("��Ա����������")){
					jl_explation.setText("������˹������������������������ʱ��ڵ�\n"
							+ "��Ϊ�߼��ɲο������ˮ��");
				}
				if(kinds.equals("�����")){
					jl_explation.setText("�������������������������ʱ��ڵ�\n"
							+ "����1����Ҫ�������2016��10��15�յ���Ʊ\n"
							+ "�����ʱ������Ϊ��20160101��20161015\n"
							+ "����2����Ҫ�������16��10�·ݵ�����\n"
							+ "�����ʱ������Ϊ��20160101,20161031");
				}
				if(kinds.equals("ϴƬ��������")){
					jl_explation.setText("");
				}
				if(kinds.equals("�����(��׼)")){
					jl_explation.setText("�������������������������ʱ��ڵ�\n"
							+ "����1����Ҫ�������2016��10��15�յ���Ʊ\n"
							+ "�����ʱ������Ϊ��20160101��20161015\n"
							+ "����2����Ҫ�������16��10�·ݵ�����\n"
							+ "�����ʱ������Ϊ��20160101,20161031");
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
			JOptionPane.showMessageDialog(null, "������������", "����ʧ��", JOptionPane.ERROR_MESSAGE);
			return;
		}
		kinds=jcb.getSelectedItem().toString();//�����Ʊ������
		date1=jtf1.getText();//���ʱ��
		date2=jtf2.getText();//���ʱ��
		//���������Ʊ��ʱ��
		if(e.getSource()==jb_output){
			JFileChooser jfc=new JFileChooser();
			int i=jfc.showSaveDialog(null);
			if(i==jfc.APPROVE_OPTION){
				File path=jfc.getSelectedFile();//��ȡ�Ի���ľ����ַ
				output=path.toString().replace('\\', '/')+".xls";
				if(!output.endsWith(".xls")){
					System.out.println("cuowi");
				}
				System.out.println(output);
				System.out.println(path.toString().replace('\\', '/'));
				//�����߳̽������أ�������ɾͿ��Խ��з���
				Thread th=new Thread(){
					public void run(){
						try {
							System.out.print("�߳̿�ʼ");
							new ExcelBean(output,kinds,date1,date2,group);
							
						} catch (SQLException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "ݔ��ʧ��", "excel�ڲ���������", JOptionPane.ERROR_MESSAGE);
							return;
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "ݔ��ʧ��", "excel�ڲ���������", JOptionPane.ERROR_MESSAGE);
							return;
						}
						System.out.println("�߳����");
						
					}
				};
				th.start();
			}
		}
		if(e.getSource()==jb_clear){
			System.out.println("�����׿�");
			jtf1.setText("");
			jtf2.setText("");
		}
		//���������ʱ��
		if(e.getSource()==jl_zhi){
			//��SQLmanage����rs
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
	//�ṩ��Ϣ���õ���ȷ���
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
