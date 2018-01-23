package MyPanels;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import MainFrame.SQLManager;
public class ProCrePane extends JPanel {
	private String group;//ȫ�ֱ����Ĵ�ֵ

	private JPanel midPanel= new JPanel(); 
    private XButtonPane bottomPanel;
    private ScrollPane sp = new ScrollPane();
    private ProPane propane1;
    private ProPane propane2;
   
    private SQLManager sm;
    /*
     * �ṩ����������/
     */
    public ProCrePane(SQLManager sm,String group){
    	this.group=group;
    	this.sm=sm;
    	init();
    }


    private void init(){
    	setLayout(new BorderLayout());
    	midPanel.setLayout(new BoxLayout(midPanel,BoxLayout.X_AXIS));
    	//���̱������ָ����ţ�ί�е�λ����Ŀ����
        propane1=new ProPane("���̱�ʶ", new String[]{"ָ����","ί�е�λ","��������"}, 10, 10, 10, 10);
    	midPanel.add(propane1);
    	propane2=new ProPane("ѡ����",new String[]{
    			"ָ��´�����" ,"ί�е�λ��ϵ��","ί�е�λ��ϵ�˵绰","��������ϵ��","����ʱ��","����ʱ��"},5,5,5,5);
    	midPanel.add(propane2);
    	sp.add(midPanel);
    	bottomPanel = new XButtonPane(new String[]{"ȷ��","����","ȡ��"}, 5, 5, 5, 5);
    	add(sp,BorderLayout.CENTER);
    	add(bottomPanel,BorderLayout.SOUTH);
    	bottomPanel.addAL(0, new TiJiaoActionListener() );
    	bottomPanel.addAL(1, new chongzhiActionListener() );
    }
    //ȷ�ϰ�ť�ļ�����
    private class  TiJiaoActionListener implements ActionListener{
		@Override
		//�����б��л�ȡ������ı���Ϣ
		public void actionPerformed(ActionEvent e){
			int op = JOptionPane.showConfirmDialog(null, "ȷ����Ӹù��̣�", "ȷ��", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(op == JOptionPane.CANCEL_OPTION || op == JOptionPane.CLOSED_OPTION ) return ;
			// TODO Auto-generated method stub
			String[] textStr= new String[10];
			for(int i = 0;i<3 ;i++){
				textStr[i] = propane1.getText(i);
			}
			for(int i = 3;i<9 ;i++){
				textStr[i] = propane2.getText(i-3);
			}
		
			//9bu����
			textStr[9] = group;
			for(int i=0;i<textStr.length;i++){
				System.out.println(textStr[i]);
			}
			
			sm.insertPro(textStr);
			
			
		}
    }
		//���ð�ť�ļ�����
	    private class  chongzhiActionListener implements ActionListener{
			@Override
			//�����б��л�ȡ������ı���Ϣ
			public void actionPerformed(ActionEvent e){
				// ��������ı�
				propane1.clr();
				propane2.clr();			
			}	
      }

}
