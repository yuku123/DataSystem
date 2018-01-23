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
	private String group;//全局变量的代值
	private String name;//用户名
	private String password;//密码
	private SQLManager SQLManager;
	// 创建信息提示标签数组
	private JLabel jl1 = new JLabel("原始密码");
	private JLabel jl2 = new JLabel("新密码");
	private JLabel jl3 = new JLabel("确认新密码");
	// 创建密码框数组
	private JPasswordField jpwf1 = new JPasswordField(10);
	private JPasswordField jpwf2 = new JPasswordField(10);
	private JPasswordField jpwf3 = new JPasswordField(10);

	private JButton jb1 = new JButton("确认");
	private JButton jb2 = new JButton("重置");

	// 构造器
	public ChangePassword(SQLManager SQLManager, String group,String name,String password) {
		this.name=name;
		this.password=password;
		this.group=group;
		this.SQLManager=SQLManager;
		this.initialComponents();
		this.initialComponentsConstruct();
		this.initialListener();
	}
	// 集体注册监听器的方法

	/*
	 * //实现ActionListener接口中的方法 public void actionPerformed(ActionEvent e) {
	 * if(e.getSource()==jpfArray[0]) { jpfArray[1].requestFocus(true); } else
	 * if(e.getSource()==jpfArray[1]) { jpfArray[2].requestFocus(true); } else
	 * if(e.getSource()==jpfArray[2]) { jbArray[0].requestFocus(true); } else
	 * if(e.getSource()==jbArray[1]) {//按下重置按钮的处理代码 //将输入信息清空 for(int
	 * i=0;i<jpfArray.length;i++) { jpfArray[i].setText(""); } } else
	 * if(e.getSource()==jbArray[0]) {//按下确认按钮的处理代码 //用于判断密码格式的正则式字符串 String
	 * patternStr="[0-9a-zA-Z]{6,12}"; //获取用户输入的旧密码 String
	 * oldPwd=jpfArray[0].getText(); if(oldPwd.equals("")) {//旧密码空
	 * JOptionPane.showMessageDialog(this,"请输入原始密码","错误",JOptionPane.
	 * ERROR_MESSAGE); return; } //获取新密码 String newPwd=jpfArray[1].getText();
	 * if(newPwd.equals("")) {//新密码为空
	 * JOptionPane.showMessageDialog(this,"请输入新密码","错误",JOptionPane.
	 * ERROR_MESSAGE); return; } if(!newPwd.matches(patternStr)) {//新密码格式不正确
	 * JOptionPane.showMessageDialog(this,"密码只能是6到12位的字母或数字","错误",JOptionPane.
	 * ERROR_MESSAGE); return; } //获取确认密码 String newPwd1=jpfArray[2].getText();
	 * if(!newPwd.equals(newPwd1)) {//新密码与确认密码不同
	 * JOptionPane.showMessageDialog(this,"确认密码与新密码不符","错误",JOptionPane.
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
					JOptionPane.showMessageDialog(null, "三个空都填上", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(!newpassword.equals(compassword)){
					JOptionPane.showMessageDialog(null, "确认密码不一致，请修改", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(oldpassword.equals(newpassword)){
					JOptionPane.showMessageDialog(null, "前后密码相同，我很懵比，这怎么改啊", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(!oldpassword.equals(password)){
					JOptionPane.showMessageDialog(null, "旧密码输错了哦，你不是真人", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String sql="UPDATE pwmanager_table SET pw=password('"+newpassword+"') WHERE bm_name='"+group+"' and p_name='"+name+"';";
				int i=SQLManager.submit_insert(sql);
				if(i==1){
					JOptionPane.showMessageDialog(null, "修改成功,下次登陆生效，使用新密码", "成功", JOptionPane.OK_OPTION);
					return;
					
				}
				
				////////////////////巴拉巴拉巴拉
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
