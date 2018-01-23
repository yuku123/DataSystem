package MyPanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.*;

public class XButtonPane   extends JPanel{	     
	private GridBagLayout gb = new GridBagLayout();
	private GridBagConstraints gbc = new GridBagConstraints();
	private JLabel jll;
	private JLabel[] jlm;
	private JLabel jlr;	
	private JButton[] jb;
	
	public XButtonPane( String[] textNames,int left , int right , int top , int bottom){
		setLayout(gb);
		jll = new JLabel();
		jlm = new JLabel[textNames.length];
		jlr = new JLabel();
		jb  = new JButton[textNames.length];
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(top, left, bottom,right); 
		gb.setConstraints(jll,gbc );
		add(jll);
		//每个按钮添加一个按钮和一个JLabel
		for( int i = 0 ; i<textNames.length && textNames !=null ;i++ ){
			gbc.fill = GridBagConstraints.NONE;
			jb[i] = new JButton(textNames[i]);
			gb.setConstraints(jb[i],gbc );
			add(jb[i]);
			jlm[i] = new JLabel();
			gb.setConstraints(jlm[i],gbc );
			add(jlm[i]);
		}
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;		
		jlr = new JLabel();
		gb.setConstraints(jlr,gbc );
		add(jlr);
		
	}
	
	
	public void addAL(int index ,ActionListener ac){
		jb[index].addActionListener(ac);
	}
}