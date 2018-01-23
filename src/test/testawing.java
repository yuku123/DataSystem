package test;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class testawing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame jf=new JFrame();
		JButton jb=new JButton();
		jf.add(jb);
		jf.add(jb);
		jf.add(new JLabel("ceshi"));
		jf.pack();
		jf.setSize(500, 500);
		jf.setVisible(true);
		

	}

}
