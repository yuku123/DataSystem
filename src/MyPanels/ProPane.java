
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
	// 输入该输入行组的标题，每行的标题，和前后左右的的间隙		
	public ProPane(String title , String[] textNames,int left , int right , int top , int bottom){
		this.textNames = textNames;
	   //创建带标题的输入行组
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title, TitledBorder.LEFT, TitledBorder.TOP));
		setLayout(gb);
		//每一行由三个组件构成，左边标签，中间输入，右边标签
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

