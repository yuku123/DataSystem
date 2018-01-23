package MyPanels;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.border.TitledBorder;

import MainFrame.SQLManager;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;








import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.UIManager;

import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.regex.*;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.border.EtchedBorder;

public class InHTPane extends JPanel {
	

	/**
	 * �����ͬ�����벿��
	 */
	
	private SQLManager sm;//��ȡ���ݿ����
	private String group;//ȫ�ֱ����Ĵ�ֵ
    //��������
	private JPanel top;   //��������
	private JLabel label0; //ָ���label
	private MyComboBox_TextFieldPane cb0;//ָ�������������
	private JLabel label1; //ί�е�λlabel
	private JComboBox<String> cb1; //ί�е�λ����ѡ���
	private JLabel label2;//��������label
	private JComboBox<String> cb2;//������������ѡ���
	private JLabel label_3;//top�������Ҳ�հ�label,�����״̬
	
	//�в�����
	private JScrollPane mid_scrollPane;
	private JPanel mid;
	private JLabel ht_num_label;
	private JTextField ht_num;
	private JLabel ht_file_label;
	private JTextField ht_file;
	private JButton liulanJB;
	private JLabel ht_price_label;
	private JPanel ht_price_panel;
	private JTextArea ht_price;
	
	
	//�ײ���ť
	private JPanel bottom;
	private JLabel label;
	private JButton inHTJB;
	private JLabel label_1;
	private JButton resetJB;
	private JLabel label_2;
	private JButton renewJB;
	
	
	public InHTPane(SQLManager s, String group) {
		this.group=group;
		this.sm=s;
		setLayout(new BorderLayout(0, 0));
		init();
	}

	@SuppressWarnings("unchecked")
	private void init(){
		
		//TODO �½���������
		top = new JPanel();
		top.setBorder(new TitledBorder(null, "\u9009\u62E9\u6307\u4EE4\u5355\u53F7\u6216\u8005\u5DE5\u7A0B\u540D\u79F0\u548C\u59D4\u6258\u5355\u4F4D", TitledBorder.LEFT, TitledBorder.TOP, null, Color.RED));
		add(top, BorderLayout.NORTH);
		GridBagLayout gbl_top = new GridBagLayout();
		gbl_top.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_top.rowHeights = new int[]{0, 0};
		gbl_top.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_top.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		top.setLayout(gbl_top);
		
		// TODO ָ���label
		label0 = new JLabel("\u6307\u4EE4\u5355\u53F7");
		label0.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_label0 = new GridBagConstraints();
		gbc_label0.insets = new Insets(0, 0, 0, 5);
		gbc_label0.gridx = 0;
		gbc_label0.gridy = 0;
		top.add(label0, gbc_label0);
		
		//TODO ָ�������������
		cb0 = new MyComboBox_TextFieldPane((ArrayList<String>)sm.search(0, 1),10);
		cb0.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				cb1.setModel( (DefaultComboBoxModel<String>)sm.search(3, 0));
				cb1.setSelectedItem((String)sm.search(1,2,new String[]{cb0.getText()}));
				cb2.setModel( (DefaultComboBoxModel<String>)sm.search(4, 0));
				cb2.setSelectedItem((String)sm.search(2,2,new String[]{cb0.getText()}));
			}
		});
		GridBagConstraints gbc_cb0 = new GridBagConstraints();
		gbc_cb0.weighty = 10.0;
		gbc_cb0.weightx = 10.0;
		gbc_cb0.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb0.insets = new Insets(0, 0, 0, 5);
		gbc_cb0.gridx = 1;
		gbc_cb0.gridy = 0;
		top.add(cb0, gbc_cb0);
		
		//TODO ί�е�λlabel
		label1 = new JLabel("\u59D4\u6258\u5355\u4F4D");
		GridBagConstraints gbc_label1 = new GridBagConstraints();
		gbc_label1.insets = new Insets(0, 0, 0, 5);
		gbc_label1.gridx = 2;
		gbc_label1.gridy = 0;
		top.add(label1, gbc_label1);
		
		//TODO ί�е�λ����ѡ���
		cb1 = new JComboBox<String>();
		cb1.setModel( (DefaultComboBoxModel<String>)sm.search(3, 0));
		cb1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// ɸѡ���������Ĺ�������
				if((String)cb1.getSelectedItem() != ""){
				cb2.setModel((DefaultComboBoxModel<String>)sm.search(5, 0,new String[]{(String)cb1.getSelectedItem()}));
				}else{
					cb2.setModel( (DefaultComboBoxModel<String>)sm.search(4, 0));
				}	
			}
		});
		cb1.setSelectedIndex(-1);
		cb1.setMaximumRowCount(15);
		GridBagConstraints gbc_cb1 = new GridBagConstraints();
		gbc_cb1.weighty = 10.0;
		gbc_cb1.weightx = 10.0;
		gbc_cb1.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb1.insets = new Insets(0, 0, 0, 5);
		gbc_cb1.gridx = 3;
		gbc_cb1.gridy = 0;
		top.add(cb1, gbc_cb1);
		
		//TODO ��������label
		label2 = new JLabel("\u5DE5\u7A0B\u540D\u79F0");
		GridBagConstraints gbc_label2 = new GridBagConstraints();
		gbc_label2.insets = new Insets(0, 0, 0, 5);
		gbc_label2.gridx = 4;
		gbc_label2.gridy = 0;
		top.add(label2, gbc_label2);
		
		//TODO������������ѡ���
		cb2 = new JComboBox<String>();
		cb2.setModel( (DefaultComboBoxModel<String>)sm.search(4, 0));
		cb2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// ɸѡָ���
				if((String)cb1.getSelectedItem() != "" && cb1.getSelectedItem()!=""){
				
				cb0.setText((String)sm.search(7,2,new String[]{(String)cb1.getSelectedItem(),(String)cb2.getSelectedItem()}));
				}	
			}
		});
		cb2.setSelectedIndex(-1);
		cb2.setMaximumRowCount(15);
		GridBagConstraints gbc_cb2 = new GridBagConstraints();
		gbc_cb2.weighty = 10.0;
		gbc_cb2.weightx = 10.0;
		gbc_cb2.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb2.insets = new Insets(0, 0, 0, 5);
		gbc_cb2.gridx = 5;
		gbc_cb2.gridy = 0;
		top.add(cb2, gbc_cb2);
		
		//TODO top�������Ҳ�հ�label,�����״̬
		label_3 = new JLabel("");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.weighty = 5.0;
		gbc_label_3.weightx = 5.0;
		gbc_label_3.ipady = 1;
		gbc_label_3.ipadx = 1;
		gbc_label_3.fill = GridBagConstraints.BOTH;
		gbc_label_3.gridx = 6;
		gbc_label_3.gridy = 0;
		top.add(label_3, gbc_label_3);
		
		//TODO �в�����
		mid_scrollPane = new JScrollPane();
		add(mid_scrollPane, BorderLayout.CENTER);
		
		mid = new JPanel();
		mid_scrollPane.setViewportView(mid);
		GridBagLayout gbl_mid = new GridBagLayout();
		gbl_mid.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_mid.rowHeights = new int[]{0, 0, 0, 0};
		gbl_mid.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_mid.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0};
		mid.setLayout(gbl_mid);
		
		//TODO ��ͬ���
		ht_num_label = new JLabel("\u5408\u540C\u7F16\u53F7");
		GridBagConstraints gbc_ht_num_label = new GridBagConstraints();
		gbc_ht_num_label.insets = new Insets(0, 0, 5, 5);
		gbc_ht_num_label.gridx = 0;
		gbc_ht_num_label.gridy = 0;
		mid.add(ht_num_label, gbc_ht_num_label);
		
		ht_num = new JTextField();
		GridBagConstraints gbc_ht_num = new GridBagConstraints();
		gbc_ht_num.insets = new Insets(0, 0, 5, 5);
		gbc_ht_num.gridx = 1;
		gbc_ht_num.gridy = 0;
		mid.add(ht_num, gbc_ht_num);
		ht_num.setColumns(15);
		
		//TODO ɨ���
		ht_file_label = new JLabel("\u5408\u540C\u626B\u63CF\u4EF6");
		GridBagConstraints gbc_ht_file_label = new GridBagConstraints();
		gbc_ht_file_label.insets = new Insets(0, 0, 5, 5);
		gbc_ht_file_label.gridx = 0;
		gbc_ht_file_label.gridy = 1;
		mid.add(ht_file_label, gbc_ht_file_label);
		
		ht_file = new JTextField();
		ht_file.setEditable(false);
		GridBagConstraints gbc_ht_file = new GridBagConstraints();
		gbc_ht_file.insets = new Insets(0, 0, 5, 5);
		gbc_ht_file.gridx = 1;
		gbc_ht_file.gridy = 1;
		mid.add(ht_file, gbc_ht_file);
		ht_file.setColumns(15);
		
		liulanJB = new JButton("\u6D4F\u89C8");
		liulanJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPDF();
			}
		});
		GridBagConstraints gbc_liulanJB = new GridBagConstraints();
		gbc_liulanJB.insets = new Insets(0, 0, 5, 5);
		gbc_liulanJB.gridx = 2;
		gbc_liulanJB.gridy = 1;
		mid.add(liulanJB, gbc_liulanJB);
		
		//TODO ��ͬ��
		ht_price_label = new JLabel("\u5408\u540C\u4EF7");
		GridBagConstraints gbc_ht_price_label = new GridBagConstraints();
		gbc_ht_price_label.anchor = GridBagConstraints.NORTHEAST;
		gbc_ht_price_label.insets = new Insets(0, 0, 5, 5);
		gbc_ht_price_label.gridx = 0;
		gbc_ht_price_label.gridy = 2;
		mid.add(ht_price_label, gbc_ht_price_label);
		
		ht_price_panel = new JPanel();
		ht_price_panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_ht_price_panel = new GridBagConstraints();
		gbc_ht_price_panel.anchor = GridBagConstraints.NORTHWEST;
		gbc_ht_price_panel.gridheight = 2;
		gbc_ht_price_panel.gridwidth = 2;
		gbc_ht_price_panel.insets = new Insets(0, 0, 0, 5);
		gbc_ht_price_panel.gridx = 1;
		gbc_ht_price_panel.gridy = 2;
		mid.add(ht_price_panel, gbc_ht_price_panel);
		ht_price_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		ht_price = new JTextArea();
		ht_price.setRows(5);
		ht_price.setLineWrap(true);
		ht_price.setColumns(20);
		ht_price_panel.add(ht_price);
		
		
		//TODO �ײ�����
		bottom = new JPanel();
		add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		label = new JLabel("                    ");
		bottom.add(label);
		
		//TODO ��Ӻ�ͬ��ť
		inHTJB = new JButton("��Ӻ�ͬ");
		inHTJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insetToHT();
			}
		});
		bottom.add(inHTJB);
		
		label_1 = new JLabel("                   ");
		bottom.add(label_1);
		
		//TODO ���ð�ť
		resetJB = new JButton("����");
		resetJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		bottom.add(resetJB);
		
		label_2 = new JLabel("              ");
		bottom.add(label_2);
		
		//TODO ˢ�°�ť
		renewJB = new JButton("ˢ��");
		renewJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				renewAction();
			}
		});
		bottom.add(renewJB);
	}
		
//TODO ���ļ�·���ŵ��ı�����
	private void addPDF(){
		JFileChooser jfc=new JFileChooser();
		jfc.setMultiSelectionEnabled(false);
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int jfcr=jfc.showDialog(this,"�ύ");
		if(jfcr==JFileChooser.APPROVE_OPTION){
			File jfcf=jfc.getSelectedFile();//��ȡ�Ի���ľ����ַ
			String jfcp = jfcf.getPath();
			System.out.println(jfcp);
			ht_file.setText(jfcp);
			System.out.println(ht_file.getText().trim().substring(ht_file.getText().lastIndexOf('\\')+1));
		}
	}
	
	
	
	
	private int insetToHT(){
		int op = JOptionPane.showConfirmDialog(this, "ȷ����Ӻ�ͬ��", "ȷ��", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(op == JOptionPane.CANCEL_OPTION || op == JOptionPane.CLOSED_OPTION ) return 1;
		
		String data[] = new String[6];
		//0����ָ��ţ�����Ϊ��
		data[0] = cb0.getText().trim();
		if(data[0].equals("")) {JOptionPane.showMessageDialog(null, "������ָ���", "ʧ��", JOptionPane.ERROR_MESSAGE);return 1;}
		//1�������ţ���Ϊ��
		data[1] = ht_num.getText().trim();
		if(data[1].equals("")) {JOptionPane.showMessageDialog(null, "�������ͬ���", "ʧ��", JOptionPane.ERROR_MESSAGE);return 2;}
		//2��ͬ������
		data[2] = ht_price.getText().trim();
		//4��ͬɨ���
		data[4] = ht_file.getText().trim();
		//3ɨ���ȫ��
		if(!data[4].equals("")) data[3] = ht_file.getText().trim().substring(ht_file.getText().lastIndexOf('\\')+1);
		else data[3] = "";
		
		//5����
		data[5] = group;
		sm.insert_hetong_table(data);
		
		return 0;
		
	}
	
	private void reset(){
		
		renewAction();
		ht_num.setText("");ht_price.setText("");ht_file.setText("");
		updateUI();
		
	}
	
	//TODO ˢ�°�ť
	@SuppressWarnings("unchecked")
	private void renewAction(){
		
		//����ָ���
		top.remove(cb0);
		cb0 = new MyComboBox_TextFieldPane((ArrayList<String>)sm.search(0, 1),10);
		cb0.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				cb1.setModel( (DefaultComboBoxModel<String>)sm.search(3, 0));
				cb1.setSelectedItem((String)sm.search(1,2,new String[]{cb0.getText()}));
				cb2.setModel( (DefaultComboBoxModel<String>)sm.search(4, 0));
				cb2.setSelectedItem((String)sm.search(2,2,new String[]{cb0.getText()}));
			}
		});
		GridBagConstraints gbc_cb0 = new GridBagConstraints();
		gbc_cb0.weighty = 10.0;
		gbc_cb0.weightx = 10.0;
		gbc_cb0.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb0.insets = new Insets(0, 0, 0, 5);
		gbc_cb0.gridx = 1;
		gbc_cb0.gridy = 0;
		top.add(cb0, gbc_cb0);
		
		//ί�е�λ
		top.remove(cb1);
		cb1 = new JComboBox<String>();
		cb1.setModel( (DefaultComboBoxModel<String>)sm.search(3, 0));
		cb1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// ɸѡ���������Ĺ�������
				if((String)cb1.getSelectedItem() != ""){
				cb2.setModel((DefaultComboBoxModel<String>)sm.search(5, 0,new String[]{(String)cb1.getSelectedItem()}));
				}else{
					cb2.setModel( (DefaultComboBoxModel<String>)sm.search(4, 0));
				}	
			}
		});
		cb1.setSelectedIndex(-1);
		cb1.setMaximumRowCount(15);
		GridBagConstraints gbc_cb1 = new GridBagConstraints();
		gbc_cb1.weighty = 10.0;
		gbc_cb1.weightx = 10.0;
		gbc_cb1.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb1.insets = new Insets(0, 0, 0, 5);
		gbc_cb1.gridx = 3;
		gbc_cb1.gridy = 0;
		top.add(cb1, gbc_cb1);
		
		//������������ѡ���
		top.remove(cb2);
		cb2 = new JComboBox<String>();
		cb2.setModel( (DefaultComboBoxModel<String>)sm.search(4, 0));
		cb2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// ɸѡָ���
				if((String)cb1.getSelectedItem() != "" && cb1.getSelectedItem()!=""){
				
				cb0.setText((String)sm.search(7,2,new String[]{(String)cb1.getSelectedItem(),(String)cb2.getSelectedItem()}));
				}	
			}
		});
		cb2.setSelectedIndex(-1);
		cb2.setMaximumRowCount(15);
		GridBagConstraints gbc_cb2 = new GridBagConstraints();
		gbc_cb2.weighty = 10.0;
		gbc_cb2.weightx = 10.0;
		gbc_cb2.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb2.insets = new Insets(0, 0, 0, 5);
		gbc_cb2.gridx = 5;
		gbc_cb2.gridy = 0;
		top.add(cb2, gbc_cb2);
		
		updateUI();
		
	}
	
	
	
}
