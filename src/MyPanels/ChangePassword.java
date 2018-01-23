package MyPanels;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import MainFrame.Feature;
import MainFrame.SQLManager;

public class ChangePassword extends JPanel implements Feature {
	private String group;//ȫ�ֱ����Ĵ�ֵ
	private String name;//�û���
	private String password;//����
	private SQLManager SQLManager;
	// ������Ϣ��ʾ��ǩ����
	private JLabel jl1 = new JLabel("ԭʼ����");
	private JLabel jl2 = new JLabel("������");
	private JLabel jl3 = new JLabel("ȷ��������");
	// �������������
	private JPasswordField jpwf1 = new JPasswordField(10);
	private JPasswordField jpwf2 = new JPasswordField(10);
	private JPasswordField jpwf3 = new JPasswordField(10);

	private JButton jb1 = new JButton("ȷ��");
	private JButton jb2 = new JButton("����");

	// ������
	public ChangePassword(SQLManager SQLManager, String group,String name,String password) {
		this.name=name;
		this.password=password;
		this.group=group;
		this.SQLManager=SQLManager;
		this.initialComponents();
		this.initialComponentsConstruct();
		this.initialListener();
	}
	// ����ע��������ķ���

	/*
	 * //ʵ��ActionListener�ӿ��еķ��� public void actionPerformed(ActionEvent e) {
	 * if(e.getSource()==jpfArray[0]) { jpfArray[1].requestFocus(true); } else
	 * if(e.getSource()==jpfArray[1]) { jpfArray[2].requestFocus(true); } else
	 * if(e.getSource()==jpfArray[2]) { jbArray[0].requestFocus(true); } else
	 * if(e.getSource()==jbArray[1]) {//�������ð�ť�Ĵ������ //��������Ϣ��� for(int
	 * i=0;i<jpfArray.length;i++) { jpfArray[i].setText(""); } } else
	 * if(e.getSource()==jbArray[0]) {//����ȷ�ϰ�ť�Ĵ������ //�����ж������ʽ������ʽ�ַ��� String
	 * patternStr="[0-9a-zA-Z]{6,12}"; //��ȡ�û�����ľ����� String
	 * oldPwd=jpfArray[0].getText(); if(oldPwd.equals("")) {//�������
	 * JOptionPane.showMessageDialog(this,"������ԭʼ����","����",JOptionPane.
	 * ERROR_MESSAGE); return; } //��ȡ������ String newPwd=jpfArray[1].getText();
	 * if(newPwd.equals("")) {//������Ϊ��
	 * JOptionPane.showMessageDialog(this,"������������","����",JOptionPane.
	 * ERROR_MESSAGE); return; } if(!newPwd.matches(patternStr)) {//�������ʽ����ȷ
	 * JOptionPane.showMessageDialog(this,"����ֻ����6��12λ����ĸ������","����",JOptionPane.
	 * ERROR_MESSAGE); return; } //��ȡȷ������ String newPwd1=jpfArray[2].getText();
	 * if(!newPwd.equals(newPwd1)) {//��������ȷ�����벻ͬ
	 * JOptionPane.showMessageDialog(this,"ȷ�������������벻��","����",JOptionPane.
	 * ERROR_MESSAGE); return; }
	 * 
	 * } }
	 * 
	 */

	@Override
	public void initialComponents() {

	}

	@Override
	public void initialComponentsConstruct() {
		// TODO Auto-generated method stub
		this.setLayout(new BorderLayout());
		JPanel j = new JPanel();
		j.setLayout(new GridLayout(4, 2));
		j.add(jl1);
		j.add(jpwf1);
		j.add(jl2);
		j.add(jpwf2);
		j.add(jl3);
		j.add(jpwf3);
		j.add(jb1);
		j.add(jb2);
		this.add(j, BorderLayout.NORTH);
		

	}

	@Override
	public void initialComponentsPattern() {
		// TODO Auto-generated method stub
		

	}

	@Override
	public void initialListener() {
		// TODO Auto-generated method stub
		jb1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String oldpassword=String.valueOf(jpwf1.getPassword());
				String newpassword=String.valueOf(jpwf2.getPassword());
				String compassword=String.valueOf(jpwf3.getPassword());
				if(oldpassword.equals("")||newpassword.equals("")||compassword.equals("")){
					JOptionPane.showMessageDialog(null, "�����ն�����", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(!newpassword.equals(compassword)){
					JOptionPane.showMessageDialog(null, "ȷ�����벻һ�£����޸�", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(oldpassword.equals(newpassword)){
					JOptionPane.showMessageDialog(null, "ǰ��������ͬ���Һ��±ȣ�����ô�İ�", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(!oldpassword.equals(password)){
					JOptionPane.showMessageDialog(null, "�����������Ŷ���㲻������", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String sql="UPDATE pwmanager_table SET pw=password('"+newpassword+"') WHERE bm_name='"+group+"' and p_name='"+name+"';";
				int i=SQLManager.submit_insert(sql);
				if(i==1){
					JOptionPane.showMessageDialog(null, "�޸ĳɹ�,�´ε�½��Ч��ʹ��������", "�ɹ�", JOptionPane.OK_OPTION);
					return;
					
				}
				
				////////////////////������������
			}});
	}

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setSize(500, 500);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(new ChangePassword(new SQLManager(),"bumen1","bm1_01","pw_bm_01"));
		jf.setVisible(true);

	}

}
