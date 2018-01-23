
package MyPanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ProPane   extends JPanel{	     
	private GridBagLayout gb = new GridBagLayout();
	private GridBagConstraints gbc = new GridBagConstraints();
	private JLabel[] jlf;
	private JLabel[] jll;			
	private JTextField[] jt ;
	private String[] textNames;
	// �������������ı��⣬ÿ�еı��⣬��ǰ�����ҵĵļ�϶		
	public ProPane(String title , String[] textNames,int left , int right , int top , int bottom){
		this.textNames = textNames;
	   //�������������������
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title, TitledBorder.LEFT, TitledBorder.TOP));
		setLayout(gb);
		//ÿһ��������������ɣ���߱�ǩ���м����룬�ұ߱�ǩ
		jlf = new JLabel[textNames.length];
		jt = new JTextField[textNames.length];
		jll = new JLabel[textNames.length];
		for( int i = 0 ; i<textNames.length && textNames !=null ;i++ ){
			gbc.fill = GridBagConstraints.NONE;
			gbc.gridwidth = 1;
			gbc.insets = new Insets(top, left, bottom,right); 
			jlf[i] = new JLabel(textNames[i]);
			gb.setConstraints(jlf[i],gbc );
			add(jlf[i]);
			jt[i] = new JTextField(20);
			gb.setConstraints(jt[i],gbc );
			add(jt[i]);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			jll[i] = new JLabel();
			gb.setConstraints(jll[i],gbc );
			add(jll[i]);
		}
	}
	public String getText(int index){
		return jt[index].getText().trim();
	}
	
	public void clr(){
		for( int i = 0 ; i<textNames.length;i++ ){
			jt[i].setText("");
			jll[i].setText("");
			
		}
	}
	
}

