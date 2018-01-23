package MyPanels;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import MainFrame.SQLManager;
public class ProCrePane extends JPanel {
	private String group;//全局变量的代值

	private JPanel midPanel= new JPanel(); 
    private XButtonPane bottomPanel;
    private ScrollPane sp = new ScrollPane();
    private ProPane propane1;
    private ProPane propane2;
   
    private SQLManager sm;
    /*
     * 提供两个构造器/
     */
    public ProCrePane(SQLManager sm,String group){
    	this.group=group;
    	this.sm=sm;
    	init();
    }


    private void init(){
    	setLayout(new BorderLayout());
    	midPanel.setLayout(new BoxLayout(midPanel,BoxLayout.X_AXIS));
    	//工程必须填的指令单单号，委托单位，项目名称
        propane1=new ProPane("工程标识", new String[]{"指令单编号","委托单位","工程名称"}, 10, 10, 10, 10);
    	midPanel.add(propane1);
    	propane2=new ProPane("选填项",new String[]{
    			"指令单下达日期" ,"委托单位联系人","委托单位联系人电话","本部门联系人","开工时间","结束时间"},5,5,5,5);
    	midPanel.add(propane2);
    	sp.add(midPanel);
    	bottomPanel = new XButtonPane(new String[]{"确认","重置","取消"}, 5, 5, 5, 5);
    	add(sp,BorderLayout.CENTER);
    	add(bottomPanel,BorderLayout.SOUTH);
    	bottomPanel.addAL(0, new TiJiaoActionListener() );
    	bottomPanel.addAL(1, new chongzhiActionListener() );
    }
    //确认按钮的监听器
    private class  TiJiaoActionListener implements ActionListener{
		@Override
		//从所有表单中获取输入的文本信息
		public void actionPerformed(ActionEvent e){
			int op = JOptionPane.showConfirmDialog(null, "确认添加该工程？", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(op == JOptionPane.CANCEL_OPTION || op == JOptionPane.CLOSED_OPTION ) return ;
			// TODO Auto-generated method stub
			String[] textStr= new String[10];
			for(int i = 0;i<3 ;i++){
				textStr[i] = propane1.getText(i);
			}
			for(int i = 3;i<9 ;i++){
				textStr[i] = propane2.getText(i-3);
			}
		
			//9bu部门
			textStr[9] = group;
			for(int i=0;i<textStr.length;i++){
				System.out.println(textStr[i]);
			}
			
			sm.insertPro(textStr);
			
			
		}
    }
		//重置按钮的监听器
	    private class  chongzhiActionListener implements ActionListener{
			@Override
			//从所有表单中获取输入的文本信息
			public void actionPerformed(ActionEvent e){
				// 清空所有文本
				propane1.clr();
				propane2.clr();			
			}	
      }

}
