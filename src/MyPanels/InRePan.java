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
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.UIManager;

import java.awt.FlowLayout;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.regex.*;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.border.EtchedBorder;
import javax.swing.event.*;

public class InRePan extends JPanel {
	

	/**
	 * Create the panel.
	 */
    
    public static final Pattern pattern = Pattern.compile("(\\d+)|(\\d+\\.\\d+)");
    public static final Pattern pattern2 = Pattern.compile("\\d\\d\\d\\d\\d\\d\\d\\d");
    
    private SQLManager sm;//获取数据库管理
    private String group;//全局变量的代值
	private JTextField calTF;//存放计算器调用的文本框
	
    //顶部容器
	private JPanel top;   //顶部容器
	private JLabel label0; //指令单号label
	private MyComboBox_TextFieldPane cb0;//指令单号输入下拉框
	private JLabel label1; //委托单位label
	private JComboBox<String> cb1; //委托单位下拉选择框
	private JLabel label2;//工程名称label
	private JComboBox<String> cb2;//工程名称下拉选择框
	private JLabel label_3;//top容器最右侧空白label,可添加状态
	
	//中部容器
	private JScrollPane middle;
	private JPanel middle_pane;
	private JPanel mid_left;
	private JPanel mid_right;
	
	//中右侧
	private JLabel x_label;
	private JTextField x_zhang;
	private JLabel x_zhang_label;
	private JLabel guangpuquan_ge_label;
	private JLabel biaopei_ri_label;
	private JLabel cehou_dian_label;
	private JLabel shentou_dao_label;
	private JLabel shentou_mi_label;
	private JLabel cifen_dao_label;
	private JLabel cifen_mi_label;
	private JLabel y_label;
	private JTextField y_zhang;
	private JLabel y_zhang_label;
	private JLabel chaosheng_label;
	private JTextField chaosheng_mi;
	private JLabel chaosheng_mi_label;
	private JTextField chaosheng_dao;
	private JLabel chaosheng_dao_label;
	private JLabel cifen_label;
	private JTextField cifen_mi;
	private JTextField cifen_dao;
	private JLabel shentou_label;
	private JTextField shentou_mi;
	private JTextField shentou_dao;
	private JLabel cehou;
	private JTextField cehou_dian;
	private JLabel biaopei_label;
	private JTextField biaopei_ri;
	private JLabel guangpuquan_label;
	private JTextField guangpuquan_dian;
	private JLabel guangpuquan_dian_label;
	private JTextField guangpuquan_ge;
	private JLabel guangpuban_label;
	private JTextField guangpuban_dian;
	private JLabel guangpuban_dian_label;
	private JTextField guangpuban_ge;
	private JLabel guangpuban_ge_label;
	private JLabel yingdu_label;
	private JTextField yingdu_dian;
	private JLabel yingdu_dian_label;
	private JLabel damo_label;
	private JTextField damo_mi;
	private JLabel damo_mi_label;
	private JLabel tofd_label;
	private JTextField tofd_mi;
	private JLabel tofd_mi_label;
	private JLabel lashen_label;
	private JTextField lashen_jian;
	private JLabel lashen_jian_label;
	private JLabel wanqu_label;
	private JTextField wanqu_jian;
	private JLabel wanqu_jian_label;
	private JLabel chongji_label;
	private JTextField chongji_jian;
	private JLabel chongji_jian_label;
	private JLabel tie_label;
	private JTextField tie_dian;
	private JLabel tie_dian_label;
	private JLabel jinxiang_label;
	private JTextField jinxiang_jian;
	private JLabel jinxiang_jian_label;
	private JLabel diankou_label;
	private JTextField diankou_amount;
	private JLabel diankou_amount_label;
	private JLabel qita1_label;
	private JTextField qita1_amount;
	private JLabel qita2_label;
	private JTextField qita2_amount;
	private JLabel qita3_label;
	private JTextField qita3_amount;
	private JLabel anci_label;
	private JTextField x_ci;
	private JTextField y_ci;
	private JTextField chaosheng_ci;
	private JTextField cifen_ci;
	private JTextField shentou_ci;
	private JTextField cehou_ci;
	private JTextField guangpuquan_ci;
	private JTextField guangpuban_ci;
	private JTextField yingdu_ci;
	private JTextField damo_ci;
	private JTextField tofd_ci;
	private JTextField lashen_ci;
	private JTextField wanqu_ci;
	private JTextField chongji_ci;
	private JTextField tie_ci;
	private JTextField jinxiang_ci;
	
	//中左侧
	private JLabel report_num_label;
	private JTextField report_num;
	private JLabel report_num_star;
	private JLabel report_date_label;
	private JTextField report_date;
	private JLabel person_send_label;
	private JTextField person_send;
	private JLabel qianzheng_num_label;
	private JTextField qianzheng_num;
	private JLabel qianzheng_date_label;
	private JTextField qianzheng_date;
	private JLabel settle_num_label;
	private JTextField settle_num;
	private JLabel settle_date_label;
	private JTextField settle_date;
	private JLabel settle_money_label;
	private JTextField settle_money;
	private JLabel invoice_num_label;
	private JTextField invoice_num;
	private JLabel invoice_date_label;
	private JTextField invoice_date;
	private JLabel invoice_money_label;
	private JTextField invoice_money;
	private JLabel collected_date_label;
	private JTextField collected_date;
	private JLabel collected_amount_label;
	private JTextField collected_amount;
	private JLabel report_beizhu_label;
	private JPanel report_beizhu_panel;
	private JTextArea report_beizhu;
	
	//底部按钮
	private JPanel bottom;
	private JLabel label;
	private JButton inReJB;
	private JLabel label_1;
	private JButton resetJB;
	private JLabel label_2;
	private JButton renewJB;
	private JButton calJB;


	
	
	public InRePan( SQLManager s, String group) {
		this.group=group;
		
		this.sm=s;
		setLayout(new BorderLayout(0, 0));
		init();


	}
	
	
	@SuppressWarnings("unchecked")
	private void init(){
		
		
		//TODO 顶部容器
		top = new JPanel();
		top.setBorder(new TitledBorder(null, "\u9009\u62E9\u6307\u4EE4\u5355\u53F7\u6216\u8005\u5DE5\u7A0B\u540D\u79F0\u548C\u59D4\u6258\u5355\u4F4D", TitledBorder.LEFT, TitledBorder.TOP, null, Color.RED));
		add(top, BorderLayout.NORTH);
		GridBagLayout gbl_top = new GridBagLayout();
		gbl_top.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_top.rowHeights = new int[]{0, 0};
		gbl_top.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_top.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		top.setLayout(gbl_top);
		
		// TODO 指令单号label
		label0 = new JLabel("\u6307\u4EE4\u5355\u53F7");
		label0.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_label0 = new GridBagConstraints();
		gbc_label0.insets = new Insets(0, 0, 0, 5);
		gbc_label0.gridx = 0;
		gbc_label0.gridy = 0;
		top.add(label0, gbc_label0);
		
		//TODO 指令单号输入下拉框
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
		
		//TODO 委托单位label
		label1 = new JLabel("\u59D4\u6258\u5355\u4F4D");
		GridBagConstraints gbc_label1 = new GridBagConstraints();
		gbc_label1.insets = new Insets(0, 0, 0, 5);
		gbc_label1.gridx = 2;
		gbc_label1.gridy = 0;
		top.add(label1, gbc_label1);
		
		//TODO 委托单位下拉选择框
		cb1 = new JComboBox<String>();
		cb1.setModel( (DefaultComboBoxModel<String>)sm.search(3, 0));
		cb1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// 筛选符合条件的工程名称
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
		
		//TODO 工程名称label
		label2 = new JLabel("\u5DE5\u7A0B\u540D\u79F0");
		GridBagConstraints gbc_label2 = new GridBagConstraints();
		gbc_label2.insets = new Insets(0, 0, 0, 5);
		gbc_label2.gridx = 4;
		gbc_label2.gridy = 0;
		top.add(label2, gbc_label2);
		
		//TODO工程名称下拉选择框
		cb2 = new JComboBox<String>();
		cb2.setModel( (DefaultComboBoxModel<String>)sm.search(4, 0));
		cb2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// 筛选指令单号
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
		
		//TODO top容器最右侧空白label,可添加状态
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
		

		
		//中部容器
		middle = new JScrollPane();
		add(middle, BorderLayout.CENTER);
		
		middle_pane = new JPanel();
		middle.setViewportView(middle_pane);
		middle_pane.setLayout(new BoxLayout(middle_pane, BoxLayout.X_AXIS));
		
		mid_left = new JPanel();
		mid_left.setBorder(new TitledBorder(null, "\u62A5\u544A\u4FE1\u606F", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		middle_pane.add(mid_left);
		GridBagLayout gbl_mid_left = new GridBagLayout();
		gbl_mid_left.columnWidths = new int[]{0, 0, 13, 0};
		gbl_mid_left.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mid_left.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_mid_left.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mid_left.setLayout(gbl_mid_left);
		
		/*
		 * 中部容器左侧
		 * 
		 * 
		 */	
		
		//TODO 报告
		report_num_label = new JLabel("\u62A5\u544A\u7F16\u53F7");
		GridBagConstraints gbc_report_num_label = new GridBagConstraints();
		gbc_report_num_label.insets = new Insets(0, 0, 5, 5);
		gbc_report_num_label.anchor = GridBagConstraints.EAST;
		gbc_report_num_label.gridx = 0;
		gbc_report_num_label.gridy = 0;
		mid_left.add(report_num_label, gbc_report_num_label);
		
		report_num = new JTextField();
		GridBagConstraints gbc_report_num = new GridBagConstraints();
		gbc_report_num.anchor = GridBagConstraints.WEST;
		gbc_report_num.insets = new Insets(0, 0, 5, 5);
		gbc_report_num.gridx = 1;
		gbc_report_num.gridy = 0;
		mid_left.add(report_num, gbc_report_num);
		report_num.setColumns(10);
		
		report_num_star = new JLabel("*");
		report_num_star.setForeground(Color.RED);
		GridBagConstraints gbc_report_num_star = new GridBagConstraints();
		gbc_report_num_star.fill = GridBagConstraints.BOTH;
		gbc_report_num_star.anchor = GridBagConstraints.WEST;
		gbc_report_num_star.insets = new Insets(0, 5, 5, 0);
		gbc_report_num_star.gridx = 2;
		gbc_report_num_star.gridy = 0;
		mid_left.add(report_num_star, gbc_report_num_star);
		
		report_date_label = new JLabel("\u62A5\u544A\u65E5\u671F");
		GridBagConstraints gbc_report_date_label = new GridBagConstraints();
		gbc_report_date_label.anchor = GridBagConstraints.EAST;
		gbc_report_date_label.insets = new Insets(0, 0, 5, 5);
		gbc_report_date_label.gridx = 0;
		gbc_report_date_label.gridy = 1;
		mid_left.add(report_date_label, gbc_report_date_label);
		
		report_date = new JTextField();
		GridBagConstraints gbc_report_date = new GridBagConstraints();
		gbc_report_date.anchor = GridBagConstraints.WEST;
		gbc_report_date.insets = new Insets(0, 0, 5, 5);
		gbc_report_date.gridx = 1;
		gbc_report_date.gridy = 1;
		mid_left.add(report_date, gbc_report_date);
		report_date.setColumns(10);
		
		person_send_label = new JLabel("\u9001\u62A5\u4EBA");
		GridBagConstraints gbc_person_send_label = new GridBagConstraints();
		gbc_person_send_label.anchor = GridBagConstraints.EAST;
		gbc_person_send_label.insets = new Insets(0, 0, 5, 5);
		gbc_person_send_label.gridx = 0;
		gbc_person_send_label.gridy = 2;
		mid_left.add(person_send_label, gbc_person_send_label);
		
		person_send = new JTextField();
		GridBagConstraints gbc_person_send = new GridBagConstraints();
		gbc_person_send.anchor = GridBagConstraints.WEST;
		gbc_person_send.insets = new Insets(0, 0, 5, 5);
		gbc_person_send.gridx = 1;
		gbc_person_send.gridy = 2;
		mid_left.add(person_send, gbc_person_send);
		person_send.setColumns(10);
		
		//TODO 签证单
		qianzheng_num_label = new JLabel("\u7B7E\u8BC1\u5355\u53F7");
		GridBagConstraints gbc_qianzheng_num_label = new GridBagConstraints();
		gbc_qianzheng_num_label.anchor = GridBagConstraints.EAST;
		gbc_qianzheng_num_label.insets = new Insets(15, 0, 5, 5);
		gbc_qianzheng_num_label.gridx = 0;
		gbc_qianzheng_num_label.gridy = 3;
		mid_left.add(qianzheng_num_label, gbc_qianzheng_num_label);
		
		qianzheng_num = new JTextField();
		GridBagConstraints gbc_qianzheng_num = new GridBagConstraints();
		gbc_qianzheng_num.anchor = GridBagConstraints.WEST;
		gbc_qianzheng_num.insets = new Insets(15, 0, 5, 5);
		gbc_qianzheng_num.gridx = 1;
		gbc_qianzheng_num.gridy = 3;
		mid_left.add(qianzheng_num, gbc_qianzheng_num);
		qianzheng_num.setColumns(10);
		
		qianzheng_date_label = new JLabel("\u7B7E\u8BC1\u5355\u7B7E\u56DE\u65E5\u671F");
		GridBagConstraints gbc_qianzheng_date_label = new GridBagConstraints();
		gbc_qianzheng_date_label.anchor = GridBagConstraints.EAST;
		gbc_qianzheng_date_label.insets = new Insets(0, 0, 5, 5);
		gbc_qianzheng_date_label.gridx = 0;
		gbc_qianzheng_date_label.gridy = 4;
		mid_left.add(qianzheng_date_label, gbc_qianzheng_date_label);
		
		qianzheng_date = new JTextField();
		GridBagConstraints gbc_qianzheng_date = new GridBagConstraints();
		gbc_qianzheng_date.anchor = GridBagConstraints.WEST;
		gbc_qianzheng_date.insets = new Insets(0, 0, 5, 5);
		gbc_qianzheng_date.gridx = 1;
		gbc_qianzheng_date.gridy = 4;
		mid_left.add(qianzheng_date, gbc_qianzheng_date);
		qianzheng_date.setColumns(10);
		
		//TODO 结算单
		settle_num_label = new JLabel("\u7ED3\u7B97\u7F16\u53F7");
		GridBagConstraints gbc_settle_num_label = new GridBagConstraints();
		gbc_settle_num_label.anchor = GridBagConstraints.EAST;
		gbc_settle_num_label.insets = new Insets(15, 0, 5, 5);
		gbc_settle_num_label.gridx = 0;
		gbc_settle_num_label.gridy = 5;
		mid_left.add(settle_num_label, gbc_settle_num_label);
		
		settle_num = new JTextField();
		GridBagConstraints gbc_settle_num = new GridBagConstraints();
		gbc_settle_num.anchor = GridBagConstraints.WEST;
		gbc_settle_num.insets = new Insets(15, 0, 5, 5);
		gbc_settle_num.gridx = 1;
		gbc_settle_num.gridy = 5;
		mid_left.add(settle_num, gbc_settle_num);
		settle_num.setColumns(10);
		
		settle_date_label = new JLabel("\u7ED3\u7B97\u65F6\u95F4");
		GridBagConstraints gbc_settle_date_label = new GridBagConstraints();
		gbc_settle_date_label.anchor = GridBagConstraints.EAST;
		gbc_settle_date_label.insets = new Insets(0, 0, 5, 5);
		gbc_settle_date_label.gridx = 0;
		gbc_settle_date_label.gridy = 6;
		mid_left.add(settle_date_label, gbc_settle_date_label);
		
		settle_date = new JTextField();
		GridBagConstraints gbc_settle_date = new GridBagConstraints();
		gbc_settle_date.anchor = GridBagConstraints.WEST;
		gbc_settle_date.insets = new Insets(0, 0, 5, 5);
		gbc_settle_date.gridx = 1;
		gbc_settle_date.gridy = 6;
		mid_left.add(settle_date, gbc_settle_date);
		settle_date.setColumns(10);
		
		settle_money_label = new JLabel("\u7ED3\u7B97\u91D1\u989D");
		GridBagConstraints gbc_settle_money_label = new GridBagConstraints();
		gbc_settle_money_label.anchor = GridBagConstraints.EAST;
		gbc_settle_money_label.insets = new Insets(0, 0, 5, 5);
		gbc_settle_money_label.gridx = 0;
		gbc_settle_money_label.gridy = 7;
		mid_left.add(settle_money_label, gbc_settle_money_label);
		
		settle_money = new JTextField();
		GridBagConstraints gbc_settle_money = new GridBagConstraints();
		gbc_settle_money.anchor = GridBagConstraints.WEST;
		gbc_settle_money.insets = new Insets(0, 0, 5, 5);
		gbc_settle_money.gridx = 1;
		gbc_settle_money.gridy = 7;
		mid_left.add(settle_money, gbc_settle_money);
		settle_money.setColumns(10);
		
		//TODO 发票
		invoice_num_label = new JLabel("\u53D1\u7968\u53F7");
		GridBagConstraints gbc_invoice_num_label = new GridBagConstraints();
		gbc_invoice_num_label.anchor = GridBagConstraints.EAST;
		gbc_invoice_num_label.insets = new Insets(15, 0, 5, 5);
		gbc_invoice_num_label.gridx = 0;
		gbc_invoice_num_label.gridy = 8;
		mid_left.add(invoice_num_label, gbc_invoice_num_label);
		
		invoice_num = new JTextField();
		GridBagConstraints gbc_invoice_num = new GridBagConstraints();
		gbc_invoice_num.anchor = GridBagConstraints.WEST;
		gbc_invoice_num.insets = new Insets(15, 0, 5, 5);
		gbc_invoice_num.gridx = 1;
		gbc_invoice_num.gridy = 8;
		mid_left.add(invoice_num, gbc_invoice_num);
		invoice_num.setColumns(10);
		
		invoice_date_label = new JLabel("\u5F00\u7968\u65E5\u671F");
		GridBagConstraints gbc_invoice_date_label = new GridBagConstraints();
		gbc_invoice_date_label.anchor = GridBagConstraints.EAST;
		gbc_invoice_date_label.insets = new Insets(0, 0, 5, 5);
		gbc_invoice_date_label.gridx = 0;
		gbc_invoice_date_label.gridy = 9;
		mid_left.add(invoice_date_label, gbc_invoice_date_label);
		
		invoice_date = new JTextField();
		GridBagConstraints gbc_invoice_date = new GridBagConstraints();
		gbc_invoice_date.anchor = GridBagConstraints.WEST;
		gbc_invoice_date.insets = new Insets(0, 0, 5, 5);
		gbc_invoice_date.gridx = 1;
		gbc_invoice_date.gridy = 9;
		mid_left.add(invoice_date, gbc_invoice_date);
		invoice_date.setColumns(10);
		
		invoice_money_label = new JLabel("\u5F00\u7968\u989D");
		GridBagConstraints gbc_invoice_money_label = new GridBagConstraints();
		gbc_invoice_money_label.anchor = GridBagConstraints.EAST;
		gbc_invoice_money_label.insets = new Insets(0, 0, 5, 5);
		gbc_invoice_money_label.gridx = 0;
		gbc_invoice_money_label.gridy = 10;
		mid_left.add(invoice_money_label, gbc_invoice_money_label);
		
		invoice_money = new JTextField();
		GridBagConstraints gbc_invoice_money = new GridBagConstraints();
		gbc_invoice_money.anchor = GridBagConstraints.WEST;
		gbc_invoice_money.insets = new Insets(0, 0, 5, 5);
		gbc_invoice_money.gridx = 1;
		gbc_invoice_money.gridy = 10;
		mid_left.add(invoice_money, gbc_invoice_money);
		invoice_money.setColumns(10);
		
		//TODO 收款
		collected_date_label = new JLabel("\u6536\u6B3E\u65E5\u671F");
		GridBagConstraints gbc_collected_date_label = new GridBagConstraints();
		gbc_collected_date_label.anchor = GridBagConstraints.EAST;
		gbc_collected_date_label.insets = new Insets(15, 0, 5, 5);
		gbc_collected_date_label.gridx = 0;
		gbc_collected_date_label.gridy = 11;
		mid_left.add(collected_date_label, gbc_collected_date_label);
		
		collected_date = new JTextField();
		GridBagConstraints gbc_collected_date = new GridBagConstraints();
		gbc_collected_date.anchor = GridBagConstraints.WEST;
		gbc_collected_date.insets = new Insets(15, 0, 5, 5);
		gbc_collected_date.gridx = 1;
		gbc_collected_date.gridy = 11;
		mid_left.add(collected_date, gbc_collected_date);
		collected_date.setColumns(10);
		
		collected_amount_label = new JLabel("\u6536\u6B3E\u989D");
		GridBagConstraints gbc_collected_amount_label = new GridBagConstraints();
		gbc_collected_amount_label.anchor = GridBagConstraints.EAST;
		gbc_collected_amount_label.insets = new Insets(0, 0, 5, 5);
		gbc_collected_amount_label.gridx = 0;
		gbc_collected_amount_label.gridy = 12;
		mid_left.add(collected_amount_label, gbc_collected_amount_label);
		
		collected_amount = new JTextField();
		GridBagConstraints gbc_collected_amount = new GridBagConstraints();
		gbc_collected_amount.anchor = GridBagConstraints.WEST;
		gbc_collected_amount.insets = new Insets(0, 0, 5, 5);
		gbc_collected_amount.gridx = 1;
		gbc_collected_amount.gridy = 12;
		mid_left.add(collected_amount, gbc_collected_amount);
		collected_amount.setColumns(10);
		
		report_beizhu_label = new JLabel("\u5907\u6CE8");
		GridBagConstraints gbc_report_beizhu_label = new GridBagConstraints();
		gbc_report_beizhu_label.anchor = GridBagConstraints.EAST;
		gbc_report_beizhu_label.insets = new Insets(20, 0, 0, 5);
		gbc_report_beizhu_label.gridx = 0;
		gbc_report_beizhu_label.gridy = 13;
		mid_left.add(report_beizhu_label, gbc_report_beizhu_label);
		
		//TODO 报告备注
		report_beizhu_panel = new JPanel();
		report_beizhu_panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_report_beizhu_panel = new GridBagConstraints();
		gbc_report_beizhu_panel.insets = new Insets(20, 0, 0, 0);
		gbc_report_beizhu_panel.gridwidth = 2;
		gbc_report_beizhu_panel.anchor = GridBagConstraints.NORTHWEST;
		gbc_report_beizhu_panel.gridx = 1;
		gbc_report_beizhu_panel.gridy = 13;
		mid_left.add(report_beizhu_panel, gbc_report_beizhu_panel);
		report_beizhu_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		report_beizhu = new JTextArea();
		report_beizhu_panel.add(report_beizhu);
		report_beizhu.setRows(5);
		report_beizhu.setLineWrap(true);
		report_beizhu.setColumns(20);
		
		
		
		/*
		 * 中部容器右侧
		 * 
		 * 
		 */				
		mid_right = new JPanel();
		mid_right.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u62A5\u544A\u5DE5\u4F5C\u91CF", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GREEN));
		middle_pane.add(mid_right);
		GridBagLayout gbl_mid_right = new GridBagLayout();
		gbl_mid_right.columnWidths = new int[]{85, 27, 0, 0, 0, 0, 0};
		gbl_mid_right.rowHeights = new int[]{0, 21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mid_right.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_mid_right.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		mid_right.setLayout(gbl_mid_right);
		
		anci_label = new JLabel("\u6309\u6B21\u7B97\u6B21\u6570");
		GridBagConstraints gbc_anci_label = new GridBagConstraints();
		gbc_anci_label.insets = new Insets(0, 0, 5, 0);
		gbc_anci_label.gridx = 5;
		gbc_anci_label.gridy = 0;
		mid_right.add(anci_label, gbc_anci_label);
		
		//TODO x光
		x_label = new JLabel("X\u5149");
		GridBagConstraints gbc_x_label = new GridBagConstraints();
		gbc_x_label.anchor = GridBagConstraints.EAST;
		gbc_x_label.insets = new Insets(0, 0, 5, 5);
		gbc_x_label.gridx = 0;
		gbc_x_label.gridy = 1;
		mid_right.add(x_label, gbc_x_label);
		
		x_zhang = new JTextField();
		x_zhang.setColumns(3);
		GridBagConstraints gbc_x_zhang = new GridBagConstraints();
		gbc_x_zhang.anchor = GridBagConstraints.NORTHEAST;
		gbc_x_zhang.insets = new Insets(0, 0, 5, 5);
		gbc_x_zhang.gridx = 1;
		gbc_x_zhang.gridy = 1;
		mid_right.add(x_zhang, gbc_x_zhang);
		x_zhang.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		x_zhang_label = new JLabel("\u5F20");
		GridBagConstraints gbc_x_zhang_label = new GridBagConstraints();
		gbc_x_zhang_label.anchor = GridBagConstraints.WEST;
		gbc_x_zhang_label.insets = new Insets(0, 0, 5, 5);
		gbc_x_zhang_label.gridx = 2;
		gbc_x_zhang_label.gridy = 1;
		mid_right.add(x_zhang_label, gbc_x_zhang_label);
		
		x_ci = new JTextField();
		x_ci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(x_ci.getText().trim().equals("")) x_ci.setBackground(new Color(255, 255, 255));
				else x_ci.setBackground(new Color(255, 255, 0));
			}
		});
		x_ci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		GridBagConstraints gbc_x_ci = new GridBagConstraints();
		gbc_x_ci.insets = new Insets(0, 0, 5, 0);
		gbc_x_ci.fill = GridBagConstraints.HORIZONTAL;
		gbc_x_ci.gridx = 5;
		gbc_x_ci.gridy = 1;
		mid_right.add(x_ci, gbc_x_ci);
		x_ci.setColumns(10);
		
		//TODO 计算器JB
		calJB = new JButton("\u8BA1\u7B97\u5668");
		calJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Calculator(calTF);
				calTF=null;
			}
		});
		GridBagConstraints gbc_calJB = new GridBagConstraints();
		gbc_calJB.insets = new Insets(0, 0, 5, 5);
		gbc_calJB.gridx = 3;
		gbc_calJB.gridy = 1;
		mid_right.add(calJB, gbc_calJB);

		
		
		//TODO γ射线
		y_label = new JLabel("\u03B3\u5C04\u7EBF");
		GridBagConstraints gbc_y_label = new GridBagConstraints();
		gbc_y_label.anchor = GridBagConstraints.EAST;
		gbc_y_label.insets = new Insets(0, 0, 5, 5);
		gbc_y_label.gridx = 0;
		gbc_y_label.gridy = 2;
		mid_right.add(y_label, gbc_y_label);
		
		y_zhang = new JTextField();
		y_zhang.setColumns(3);
		GridBagConstraints gbc_y_zhang = new GridBagConstraints();
		gbc_y_zhang.anchor = GridBagConstraints.EAST;
		gbc_y_zhang.insets = new Insets(0, 0, 5, 5);
		gbc_y_zhang.gridx = 1;
		gbc_y_zhang.gridy = 2;
		mid_right.add(y_zhang, gbc_y_zhang);
		y_zhang.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		y_zhang_label = new JLabel("\u5F20");
		GridBagConstraints gbc_y_zhang_label = new GridBagConstraints();
		gbc_y_zhang_label.anchor = GridBagConstraints.WEST;
		gbc_y_zhang_label.insets = new Insets(0, 0, 5, 5);
		gbc_y_zhang_label.gridx = 2;
		gbc_y_zhang_label.gridy = 2;
		mid_right.add(y_zhang_label, gbc_y_zhang_label);
		
		y_ci = new JTextField();
		y_ci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(y_ci.getText().trim().equals("")) y_ci.setBackground(new Color(255, 255, 255));
				else y_ci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_y_ci = new GridBagConstraints();
		gbc_y_ci.insets = new Insets(0, 0, 5, 0);
		gbc_y_ci.fill = GridBagConstraints.HORIZONTAL;
		gbc_y_ci.gridx = 5;
		gbc_y_ci.gridy = 2;
		mid_right.add(y_ci, gbc_y_ci);
		y_ci.setColumns(10);
		y_ci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		
		//TODO 超声
		chaosheng_label = new JLabel("\u8D85\u58F0");
		GridBagConstraints gbc_chaosheng_label = new GridBagConstraints();
		gbc_chaosheng_label.anchor = GridBagConstraints.EAST;
		gbc_chaosheng_label.insets = new Insets(0, 0, 5, 5);
		gbc_chaosheng_label.gridx = 0;
		gbc_chaosheng_label.gridy = 3;
		mid_right.add(chaosheng_label, gbc_chaosheng_label);
		
		chaosheng_mi = new JTextField();
		chaosheng_mi.setColumns(3);
		GridBagConstraints gbc_chaosheng_mi = new GridBagConstraints();
		gbc_chaosheng_mi.anchor = GridBagConstraints.EAST;
		gbc_chaosheng_mi.insets = new Insets(0, 0, 5, 5);
		gbc_chaosheng_mi.gridx = 1;
		gbc_chaosheng_mi.gridy = 3;
		mid_right.add(chaosheng_mi, gbc_chaosheng_mi);
		chaosheng_mi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		chaosheng_mi_label = new JLabel("\u7C73");
		GridBagConstraints gbc_chaosheng_mi_label = new GridBagConstraints();
		gbc_chaosheng_mi_label.anchor = GridBagConstraints.WEST;
		gbc_chaosheng_mi_label.insets = new Insets(0, 0, 5, 5);
		gbc_chaosheng_mi_label.gridx = 2;
		gbc_chaosheng_mi_label.gridy = 3;
		mid_right.add(chaosheng_mi_label, gbc_chaosheng_mi_label);
		
		chaosheng_dao = new JTextField();
		chaosheng_dao.setColumns(3);
		GridBagConstraints gbc_chaosheng_dao = new GridBagConstraints();
		gbc_chaosheng_dao.anchor = GridBagConstraints.EAST;
		gbc_chaosheng_dao.insets = new Insets(0, 0, 5, 5);
		gbc_chaosheng_dao.gridx = 3;
		gbc_chaosheng_dao.gridy = 3;
		mid_right.add(chaosheng_dao, gbc_chaosheng_dao);
		chaosheng_dao.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		chaosheng_dao_label = new JLabel("\u9053");
		GridBagConstraints gbc_chaosheng_dao_label = new GridBagConstraints();
		gbc_chaosheng_dao_label.anchor = GridBagConstraints.EAST;
		gbc_chaosheng_dao_label.insets = new Insets(0, 0, 5, 5);
		gbc_chaosheng_dao_label.gridx = 4;
		gbc_chaosheng_dao_label.gridy = 3;
		mid_right.add(chaosheng_dao_label, gbc_chaosheng_dao_label);
		
		chaosheng_ci = new JTextField();
		chaosheng_ci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(chaosheng_ci.getText().trim().equals("")) chaosheng_ci.setBackground(new Color(255, 255, 255));
				else chaosheng_ci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_chaosheng_ci = new GridBagConstraints();
		gbc_chaosheng_ci.insets = new Insets(0, 0, 5, 0);
		gbc_chaosheng_ci.fill = GridBagConstraints.HORIZONTAL;
		gbc_chaosheng_ci.gridx = 5;
		gbc_chaosheng_ci.gridy = 3;
		mid_right.add(chaosheng_ci, gbc_chaosheng_ci);
		chaosheng_ci.setColumns(10);
		chaosheng_ci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		//TODO 磁粉
		cifen_label = new JLabel("\u78C1\u7C89");
		GridBagConstraints gbc_cifen_label = new GridBagConstraints();
		gbc_cifen_label.anchor = GridBagConstraints.EAST;
		gbc_cifen_label.insets = new Insets(0, 0, 5, 5);
		gbc_cifen_label.gridx = 0;
		gbc_cifen_label.gridy = 4;
		mid_right.add(cifen_label, gbc_cifen_label);
		
		cifen_mi = new JTextField();
		cifen_mi.setColumns(3);
		GridBagConstraints gbc_cifen_mi = new GridBagConstraints();
		gbc_cifen_mi.anchor = GridBagConstraints.EAST;
		gbc_cifen_mi.insets = new Insets(0, 0, 5, 5);
		gbc_cifen_mi.gridx = 1;
		gbc_cifen_mi.gridy = 4;
		mid_right.add(cifen_mi, gbc_cifen_mi);
		cifen_mi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		cifen_mi_label = new JLabel("\u7C73");
		GridBagConstraints gbc_cifen_mi_label = new GridBagConstraints();
		gbc_cifen_mi_label.anchor = GridBagConstraints.WEST;
		gbc_cifen_mi_label.insets = new Insets(0, 0, 5, 5);
		gbc_cifen_mi_label.gridx = 2;
		gbc_cifen_mi_label.gridy = 4;
		mid_right.add(cifen_mi_label, gbc_cifen_mi_label);
		
		cifen_dao = new JTextField();
		cifen_dao.setColumns(3);
		GridBagConstraints gbc_cifen_dao = new GridBagConstraints();
		gbc_cifen_dao.anchor = GridBagConstraints.EAST;
		gbc_cifen_dao.insets = new Insets(0, 0, 5, 5);
		gbc_cifen_dao.gridx = 3;
		gbc_cifen_dao.gridy = 4;
		mid_right.add(cifen_dao, gbc_cifen_dao);
		cifen_dao.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		cifen_dao_label = new JLabel("\u9053");
		GridBagConstraints gbc_cifen_dao_label = new GridBagConstraints();
		gbc_cifen_dao_label.anchor = GridBagConstraints.EAST;
		gbc_cifen_dao_label.insets = new Insets(0, 0, 5, 5);
		gbc_cifen_dao_label.gridx = 4;
		gbc_cifen_dao_label.gridy = 4;
		mid_right.add(cifen_dao_label, gbc_cifen_dao_label);
		
		cifen_ci = new JTextField();
		cifen_ci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(cifen_ci.getText().trim().equals("")) cifen_ci.setBackground(new Color(255, 255, 255));
				else cifen_ci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_cifen_ci = new GridBagConstraints();
		gbc_cifen_ci.insets = new Insets(0, 0, 5, 0);
		gbc_cifen_ci.fill = GridBagConstraints.HORIZONTAL;
		gbc_cifen_ci.gridx = 5;
		gbc_cifen_ci.gridy = 4;
		mid_right.add(cifen_ci, gbc_cifen_ci);
		cifen_ci.setColumns(10);
		cifen_ci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		//TODO 渗透
		shentou_label = new JLabel("\u6E17\u900F");
		GridBagConstraints gbc_shentou_label = new GridBagConstraints();
		gbc_shentou_label.anchor = GridBagConstraints.EAST;
		gbc_shentou_label.insets = new Insets(0, 0, 5, 5);
		gbc_shentou_label.gridx = 0;
		gbc_shentou_label.gridy = 5;
		mid_right.add(shentou_label, gbc_shentou_label);
		
		shentou_mi = new JTextField();
		shentou_mi.setColumns(3);
		GridBagConstraints gbc_shentou_mi = new GridBagConstraints();
		gbc_shentou_mi.anchor = GridBagConstraints.EAST;
		gbc_shentou_mi.insets = new Insets(0, 0, 5, 5);
		gbc_shentou_mi.gridx = 1;
		gbc_shentou_mi.gridy = 5;
		mid_right.add(shentou_mi, gbc_shentou_mi);
		shentou_mi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		shentou_mi_label = new JLabel("\u7C73");
		GridBagConstraints gbc_shentou_mi_label = new GridBagConstraints();
		gbc_shentou_mi_label.anchor = GridBagConstraints.WEST;
		gbc_shentou_mi_label.insets = new Insets(0, 0, 5, 5);
		gbc_shentou_mi_label.gridx = 2;
		gbc_shentou_mi_label.gridy = 5;
		mid_right.add(shentou_mi_label, gbc_shentou_mi_label);
		
		shentou_dao = new JTextField();
		shentou_dao.setColumns(3);
		GridBagConstraints gbc_shentou_dao = new GridBagConstraints();
		gbc_shentou_dao.anchor = GridBagConstraints.EAST;
		gbc_shentou_dao.insets = new Insets(0, 0, 5, 5);
		gbc_shentou_dao.gridx = 3;
		gbc_shentou_dao.gridy = 5;
		mid_right.add(shentou_dao, gbc_shentou_dao);
		shentou_dao.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		shentou_dao_label = new JLabel("\u9053");
		GridBagConstraints gbc_shentou_dao_label = new GridBagConstraints();
		gbc_shentou_dao_label.anchor = GridBagConstraints.EAST;
		gbc_shentou_dao_label.insets = new Insets(0, 0, 5, 5);
		gbc_shentou_dao_label.gridx = 4;
		gbc_shentou_dao_label.gridy = 5;
		mid_right.add(shentou_dao_label, gbc_shentou_dao_label);
		
		shentou_ci = new JTextField();
		shentou_ci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(shentou_ci.getText().trim().equals("")) shentou_ci.setBackground(new Color(255, 255, 255));
				else shentou_ci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_shentou_ci = new GridBagConstraints();
		gbc_shentou_ci.insets = new Insets(0, 0, 5, 0);
		gbc_shentou_ci.fill = GridBagConstraints.HORIZONTAL;
		gbc_shentou_ci.gridx = 5;
		gbc_shentou_ci.gridy = 5;
		mid_right.add(shentou_ci, gbc_shentou_ci);
		shentou_ci.setColumns(10);
		shentou_ci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		//TODO 测厚
		cehou = new JLabel("\u6D4B\u539A");
		GridBagConstraints gbc_cehou = new GridBagConstraints();
		gbc_cehou.anchor = GridBagConstraints.EAST;
		gbc_cehou.insets = new Insets(0, 0, 5, 5);
		gbc_cehou.gridx = 0;
		gbc_cehou.gridy = 6;
		mid_right.add(cehou, gbc_cehou);
		
		cehou_dian = new JTextField();
		cehou_dian.setColumns(3);
		GridBagConstraints gbc_cehou_dian = new GridBagConstraints();
		gbc_cehou_dian.anchor = GridBagConstraints.EAST;
		gbc_cehou_dian.insets = new Insets(0, 0, 5, 5);
		gbc_cehou_dian.gridx = 1;
		gbc_cehou_dian.gridy = 6;
		mid_right.add(cehou_dian, gbc_cehou_dian);
		cehou_dian.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		cehou_dian_label = new JLabel("\u70B9");
		GridBagConstraints gbc_cehou_dian_label = new GridBagConstraints();
		gbc_cehou_dian_label.anchor = GridBagConstraints.WEST;
		gbc_cehou_dian_label.insets = new Insets(0, 0, 5, 5);
		gbc_cehou_dian_label.gridx = 2;
		gbc_cehou_dian_label.gridy = 6;
		mid_right.add(cehou_dian_label, gbc_cehou_dian_label);
		
		cehou_ci = new JTextField();
		cehou_ci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(cehou_ci.getText().trim().equals("")) cehou_ci.setBackground(new Color(255, 255, 255));
				else cehou_ci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_cehou_ci = new GridBagConstraints();
		gbc_cehou_ci.insets = new Insets(0, 0, 5, 0);
		gbc_cehou_ci.fill = GridBagConstraints.HORIZONTAL;
		gbc_cehou_ci.gridx = 5;
		gbc_cehou_ci.gridy = 6;
		mid_right.add(cehou_ci, gbc_cehou_ci);
		cehou_ci.setColumns(10);
		cehou_ci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		//TODO 表面配合
		biaopei_label = new JLabel("\u8868\u9762\u914D\u5408");
		GridBagConstraints gbc_biaopei_label = new GridBagConstraints();
		gbc_biaopei_label.anchor = GridBagConstraints.EAST;
		gbc_biaopei_label.insets = new Insets(0, 0, 5, 5);
		gbc_biaopei_label.gridx = 0;
		gbc_biaopei_label.gridy = 7;
		mid_right.add(biaopei_label, gbc_biaopei_label);
		
		biaopei_ri = new JTextField();
		biaopei_ri.setColumns(3);
		GridBagConstraints gbc_biaopei_ri = new GridBagConstraints();
		gbc_biaopei_ri.anchor = GridBagConstraints.EAST;
		gbc_biaopei_ri.insets = new Insets(0, 0, 5, 5);
		gbc_biaopei_ri.gridx = 1;
		gbc_biaopei_ri.gridy = 7;
		mid_right.add(biaopei_ri, gbc_biaopei_ri);
		biaopei_ri.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		biaopei_ri_label = new JLabel("\u5DE5\u65E5");
		GridBagConstraints gbc_biaopei_ri_label = new GridBagConstraints();
		gbc_biaopei_ri_label.anchor = GridBagConstraints.WEST;
		gbc_biaopei_ri_label.insets = new Insets(0, 0, 5, 5);
		gbc_biaopei_ri_label.gridx = 2;
		gbc_biaopei_ri_label.gridy = 7;
		mid_right.add(biaopei_ri_label, gbc_biaopei_ri_label);
		
		//TODO 全定量
		guangpuquan_label = new JLabel("\u5168\u5B9A\u91CF");
		GridBagConstraints gbc_guangpuquan_label = new GridBagConstraints();
		gbc_guangpuquan_label.anchor = GridBagConstraints.EAST;
		gbc_guangpuquan_label.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuquan_label.gridx = 0;
		gbc_guangpuquan_label.gridy = 8;
		mid_right.add(guangpuquan_label, gbc_guangpuquan_label);
		
		guangpuquan_dian = new JTextField();
		guangpuquan_dian.setColumns(3);
		GridBagConstraints gbc_guangpuquan_dian = new GridBagConstraints();
		gbc_guangpuquan_dian.anchor = GridBagConstraints.EAST;
		gbc_guangpuquan_dian.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuquan_dian.gridx = 1;
		gbc_guangpuquan_dian.gridy = 8;
		mid_right.add(guangpuquan_dian, gbc_guangpuquan_dian);
		guangpuquan_dian.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		guangpuquan_dian_label = new JLabel("\u70B9");
		GridBagConstraints gbc_guangpuquan_dian_label = new GridBagConstraints();
		gbc_guangpuquan_dian_label.anchor = GridBagConstraints.WEST;
		gbc_guangpuquan_dian_label.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuquan_dian_label.gridx = 2;
		gbc_guangpuquan_dian_label.gridy = 8;
		mid_right.add(guangpuquan_dian_label, gbc_guangpuquan_dian_label);
		
		guangpuquan_ge = new JTextField();
		guangpuquan_ge.setColumns(3);
		GridBagConstraints gbc_guangpuquan_ge = new GridBagConstraints();
		gbc_guangpuquan_ge.anchor = GridBagConstraints.EAST;
		gbc_guangpuquan_ge.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuquan_ge.gridx = 3;
		gbc_guangpuquan_ge.gridy = 8;
		mid_right.add(guangpuquan_ge, gbc_guangpuquan_ge);
		guangpuquan_ge.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		guangpuquan_ge_label = new JLabel("\u4E2A\u5143\u7D20");
		GridBagConstraints gbc_guangpuquan_ge_label = new GridBagConstraints();
		gbc_guangpuquan_ge_label.anchor = GridBagConstraints.EAST;
		gbc_guangpuquan_ge_label.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuquan_ge_label.gridx = 4;
		gbc_guangpuquan_ge_label.gridy = 8;
		mid_right.add(guangpuquan_ge_label, gbc_guangpuquan_ge_label);
		
		guangpuquan_ci = new JTextField();
		guangpuquan_ci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(guangpuquan_ci.getText().trim().equals("")) guangpuquan_ci.setBackground(new Color(255, 255, 255));
				else guangpuquan_ci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_guangpuquan_ci = new GridBagConstraints();
		gbc_guangpuquan_ci.insets = new Insets(0, 0, 5, 0);
		gbc_guangpuquan_ci.fill = GridBagConstraints.HORIZONTAL;
		gbc_guangpuquan_ci.gridx = 5;
		gbc_guangpuquan_ci.gridy = 8;
		mid_right.add(guangpuquan_ci, gbc_guangpuquan_ci);
		guangpuquan_ci.setColumns(10);
		guangpuquan_ci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		//TODO 半定量光谱
		guangpuban_label = new JLabel("\u534A\u5B9A\u91CF");
		GridBagConstraints gbc_guangpuban_label = new GridBagConstraints();
		gbc_guangpuban_label.anchor = GridBagConstraints.EAST;
		gbc_guangpuban_label.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuban_label.gridx = 0;
		gbc_guangpuban_label.gridy = 9;
		mid_right.add(guangpuban_label, gbc_guangpuban_label);
		
		guangpuban_dian = new JTextField();
		guangpuban_dian.setColumns(3);
		GridBagConstraints gbc_guangpuban_dian = new GridBagConstraints();
		gbc_guangpuban_dian.anchor = GridBagConstraints.EAST;
		gbc_guangpuban_dian.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuban_dian.gridx = 1;
		gbc_guangpuban_dian.gridy = 9;
		mid_right.add(guangpuban_dian, gbc_guangpuban_dian);
		guangpuban_dian.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		guangpuban_dian_label = new JLabel("\u70B9");
		GridBagConstraints gbc_guangpuban_dian_label = new GridBagConstraints();
		gbc_guangpuban_dian_label.anchor = GridBagConstraints.WEST;
		gbc_guangpuban_dian_label.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuban_dian_label.gridx = 2;
		gbc_guangpuban_dian_label.gridy = 9;
		mid_right.add(guangpuban_dian_label, gbc_guangpuban_dian_label);
		
		guangpuban_ge = new JTextField();
		guangpuban_ge.setColumns(3);
		GridBagConstraints gbc_guangpuban_ge = new GridBagConstraints();
		gbc_guangpuban_ge.anchor = GridBagConstraints.EAST;
		gbc_guangpuban_ge.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuban_ge.gridx = 3;
		gbc_guangpuban_ge.gridy = 9;
		mid_right.add(guangpuban_ge, gbc_guangpuban_ge);
		guangpuban_ge.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		guangpuban_ge_label = new JLabel("\u4E2A\u5143\u7D20");
		GridBagConstraints gbc_guangpuban_ge_label = new GridBagConstraints();
		gbc_guangpuban_ge_label.anchor = GridBagConstraints.EAST;
		gbc_guangpuban_ge_label.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuban_ge_label.gridx = 4;
		gbc_guangpuban_ge_label.gridy = 9;
		mid_right.add(guangpuban_ge_label, gbc_guangpuban_ge_label);
		
		guangpuban_ci = new JTextField();
		guangpuban_ci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(guangpuban_ci.getText().trim().equals("")) guangpuban_ci.setBackground(new Color(255, 255, 255));
				else guangpuban_ci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_guangpuban_ci = new GridBagConstraints();
		gbc_guangpuban_ci.insets = new Insets(0, 0, 5, 0);
		gbc_guangpuban_ci.fill = GridBagConstraints.HORIZONTAL;
		gbc_guangpuban_ci.gridx = 5;
		gbc_guangpuban_ci.gridy = 9;
		mid_right.add(guangpuban_ci, gbc_guangpuban_ci);
		guangpuban_ci.setColumns(10);
		guangpuban_ci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		//TODO 硬度
		yingdu_label = new JLabel("\u786C\u5EA6");
		GridBagConstraints gbc_yingdu_label = new GridBagConstraints();
		gbc_yingdu_label.anchor = GridBagConstraints.EAST;
		gbc_yingdu_label.insets = new Insets(0, 0, 5, 5);
		gbc_yingdu_label.gridx = 0;
		gbc_yingdu_label.gridy = 10;
		mid_right.add(yingdu_label, gbc_yingdu_label);
		
		yingdu_dian = new JTextField();
		yingdu_dian.setColumns(3);
		GridBagConstraints gbc_yingdu_dian = new GridBagConstraints();
		gbc_yingdu_dian.anchor = GridBagConstraints.EAST;
		gbc_yingdu_dian.insets = new Insets(0, 0, 5, 5);
		gbc_yingdu_dian.gridx = 1;
		gbc_yingdu_dian.gridy = 10;
		mid_right.add(yingdu_dian, gbc_yingdu_dian);
		yingdu_dian.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		yingdu_dian_label = new JLabel("\u70B9");
		GridBagConstraints gbc_yingdu_dian_label = new GridBagConstraints();
		gbc_yingdu_dian_label.anchor = GridBagConstraints.WEST;
		gbc_yingdu_dian_label.insets = new Insets(0, 0, 5, 5);
		gbc_yingdu_dian_label.gridx = 2;
		gbc_yingdu_dian_label.gridy = 10;
		mid_right.add(yingdu_dian_label, gbc_yingdu_dian_label);
		
		yingdu_ci = new JTextField();
		yingdu_ci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(yingdu_ci.getText().trim().equals("")) yingdu_ci.setBackground(new Color(255, 255, 255));
				else yingdu_ci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_yingdu_ci = new GridBagConstraints();
		gbc_yingdu_ci.insets = new Insets(0, 0, 5, 0);
		gbc_yingdu_ci.fill = GridBagConstraints.HORIZONTAL;
		gbc_yingdu_ci.gridx = 5;
		gbc_yingdu_ci.gridy = 10;
		mid_right.add(yingdu_ci, gbc_yingdu_ci);
		yingdu_ci.setColumns(10);
		yingdu_ci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		//TODO 打磨
		damo_label = new JLabel("\u6253\u78E8");
		GridBagConstraints gbc_damo_label = new GridBagConstraints();
		gbc_damo_label.anchor = GridBagConstraints.EAST;
		gbc_damo_label.insets = new Insets(0, 0, 5, 5);
		gbc_damo_label.gridx = 0;
		gbc_damo_label.gridy = 11;
		mid_right.add(damo_label, gbc_damo_label);
		
		damo_mi = new JTextField();
		damo_mi.setColumns(3);
		GridBagConstraints gbc_damo_mi = new GridBagConstraints();
		gbc_damo_mi.anchor = GridBagConstraints.EAST;
		gbc_damo_mi.insets = new Insets(0, 0, 5, 5);
		gbc_damo_mi.gridx = 1;
		gbc_damo_mi.gridy = 11;
		mid_right.add(damo_mi, gbc_damo_mi);
		damo_mi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		damo_mi_label = new JLabel("\u7C73");
		GridBagConstraints gbc_damo_mi_label = new GridBagConstraints();
		gbc_damo_mi_label.anchor = GridBagConstraints.WEST;
		gbc_damo_mi_label.insets = new Insets(0, 0, 5, 5);
		gbc_damo_mi_label.gridx = 2;
		gbc_damo_mi_label.gridy = 11;
		mid_right.add(damo_mi_label, gbc_damo_mi_label);
		
		damo_ci = new JTextField();
		damo_ci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(damo_ci.getText().trim().equals("")) damo_ci.setBackground(new Color(255, 255, 255));
				else damo_ci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_damo_ci = new GridBagConstraints();
		gbc_damo_ci.insets = new Insets(0, 0, 5, 0);
		gbc_damo_ci.fill = GridBagConstraints.HORIZONTAL;
		gbc_damo_ci.gridx = 5;
		gbc_damo_ci.gridy = 11;
		mid_right.add(damo_ci, gbc_damo_ci);
		damo_ci.setColumns(10);
		damo_ci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		//TODO TOFD
		tofd_label = new JLabel("TOFD");
		GridBagConstraints gbc_tofd_label = new GridBagConstraints();
		gbc_tofd_label.anchor = GridBagConstraints.EAST;
		gbc_tofd_label.insets = new Insets(0, 0, 5, 5);
		gbc_tofd_label.gridx = 0;
		gbc_tofd_label.gridy = 12;
		mid_right.add(tofd_label, gbc_tofd_label);
		
		tofd_mi = new JTextField();
		tofd_mi.setColumns(3);
		GridBagConstraints gbc_tofd_mi = new GridBagConstraints();
		gbc_tofd_mi.anchor = GridBagConstraints.EAST;
		gbc_tofd_mi.insets = new Insets(0, 0, 5, 5);
		gbc_tofd_mi.gridx = 1;
		gbc_tofd_mi.gridy = 12;
		mid_right.add(tofd_mi, gbc_tofd_mi);
		tofd_mi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		tofd_mi_label = new JLabel("\u7C73");
		GridBagConstraints gbc_tofd_mi_label = new GridBagConstraints();
		gbc_tofd_mi_label.anchor = GridBagConstraints.WEST;
		gbc_tofd_mi_label.insets = new Insets(0, 0, 5, 5);
		gbc_tofd_mi_label.gridx = 2;
		gbc_tofd_mi_label.gridy = 12;
		mid_right.add(tofd_mi_label, gbc_tofd_mi_label);
		
		tofd_ci = new JTextField();
		tofd_ci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(tofd_ci.getText().trim().equals("")) tofd_ci.setBackground(new Color(255, 255, 255));
				else tofd_ci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_tofd_ci = new GridBagConstraints();
		gbc_tofd_ci.insets = new Insets(0, 0, 5, 0);
		gbc_tofd_ci.fill = GridBagConstraints.HORIZONTAL;
		gbc_tofd_ci.gridx = 5;
		gbc_tofd_ci.gridy = 12;
		mid_right.add(tofd_ci, gbc_tofd_ci);
		tofd_ci.setColumns(10);
		tofd_ci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		//TODO 拉伸
		lashen_label = new JLabel("\u62C9\u4F38");
		GridBagConstraints gbc_lashen_label = new GridBagConstraints();
		gbc_lashen_label.anchor = GridBagConstraints.EAST;
		gbc_lashen_label.insets = new Insets(0, 0, 5, 5);
		gbc_lashen_label.gridx = 0;
		gbc_lashen_label.gridy = 13;
		mid_right.add(lashen_label, gbc_lashen_label);
		
		lashen_jian = new JTextField();
		lashen_jian.setColumns(3);
		GridBagConstraints gbc_lashen_jian = new GridBagConstraints();
		gbc_lashen_jian.anchor = GridBagConstraints.EAST;
		gbc_lashen_jian.insets = new Insets(0, 0, 5, 5);
		gbc_lashen_jian.gridx = 1;
		gbc_lashen_jian.gridy = 13;
		mid_right.add(lashen_jian, gbc_lashen_jian);
		lashen_jian.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		lashen_jian_label = new JLabel("\u4EF6");
		GridBagConstraints gbc_lashen_jian_label = new GridBagConstraints();
		gbc_lashen_jian_label.anchor = GridBagConstraints.WEST;
		gbc_lashen_jian_label.insets = new Insets(0, 0, 5, 5);
		gbc_lashen_jian_label.gridx = 2;
		gbc_lashen_jian_label.gridy = 13;
		mid_right.add(lashen_jian_label, gbc_lashen_jian_label);
		
		lashen_ci = new JTextField();
		lashen_ci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(lashen_ci.getText().trim().equals("")) lashen_ci.setBackground(new Color(255, 255, 255));
				else lashen_ci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_lashen_ci = new GridBagConstraints();
		gbc_lashen_ci.insets = new Insets(0, 0, 5, 0);
		gbc_lashen_ci.fill = GridBagConstraints.HORIZONTAL;
		gbc_lashen_ci.gridx = 5;
		gbc_lashen_ci.gridy = 13;
		mid_right.add(lashen_ci, gbc_lashen_ci);
		lashen_ci.setColumns(10);
		lashen_ci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		//TODO 弯曲
		wanqu_label = new JLabel("\u5F2F\u66F2");
		GridBagConstraints gbc_wanqu_label = new GridBagConstraints();
		gbc_wanqu_label.anchor = GridBagConstraints.EAST;
		gbc_wanqu_label.insets = new Insets(0, 0, 5, 5);
		gbc_wanqu_label.gridx = 0;
		gbc_wanqu_label.gridy = 14;
		mid_right.add(wanqu_label, gbc_wanqu_label);
		
		wanqu_jian = new JTextField();
		wanqu_jian.setColumns(3);
		GridBagConstraints gbc_wanqu_jian = new GridBagConstraints();
		gbc_wanqu_jian.anchor = GridBagConstraints.EAST;
		gbc_wanqu_jian.insets = new Insets(0, 0, 5, 5);
		gbc_wanqu_jian.gridx = 1;
		gbc_wanqu_jian.gridy = 14;
		mid_right.add(wanqu_jian, gbc_wanqu_jian);
		wanqu_jian.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		wanqu_jian_label = new JLabel("\u4EF6");
		GridBagConstraints gbc_wanqu_jian_label = new GridBagConstraints();
		gbc_wanqu_jian_label.anchor = GridBagConstraints.WEST;
		gbc_wanqu_jian_label.insets = new Insets(0, 0, 5, 5);
		gbc_wanqu_jian_label.gridx = 2;
		gbc_wanqu_jian_label.gridy = 14;
		mid_right.add(wanqu_jian_label, gbc_wanqu_jian_label);
		
		wanqu_ci = new JTextField();
		wanqu_ci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(wanqu_ci.getText().trim().equals("")) wanqu_ci.setBackground(new Color(255, 255, 255));
				else wanqu_ci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_wanqu_ci = new GridBagConstraints();
		gbc_wanqu_ci.insets = new Insets(0, 0, 5, 0);
		gbc_wanqu_ci.fill = GridBagConstraints.HORIZONTAL;
		gbc_wanqu_ci.gridx = 5;
		gbc_wanqu_ci.gridy = 14;
		mid_right.add(wanqu_ci, gbc_wanqu_ci);
		wanqu_ci.setColumns(10);
		wanqu_ci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		//TODO 冲击
		chongji_label = new JLabel("\u51B2\u51FB");
		GridBagConstraints gbc_chongji_label = new GridBagConstraints();
		gbc_chongji_label.anchor = GridBagConstraints.EAST;
		gbc_chongji_label.insets = new Insets(0, 0, 5, 5);
		gbc_chongji_label.gridx = 0;
		gbc_chongji_label.gridy = 15;
		mid_right.add(chongji_label, gbc_chongji_label);
		
		chongji_jian = new JTextField();
		chongji_jian.setColumns(3);
		GridBagConstraints gbc_chongji_jian = new GridBagConstraints();
		gbc_chongji_jian.anchor = GridBagConstraints.EAST;
		gbc_chongji_jian.insets = new Insets(0, 0, 5, 5);
		gbc_chongji_jian.gridx = 1;
		gbc_chongji_jian.gridy = 15;
		mid_right.add(chongji_jian, gbc_chongji_jian);
		chongji_jian.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		chongji_jian_label = new JLabel("\u4EF6");
		GridBagConstraints gbc_chongji_jian_label = new GridBagConstraints();
		gbc_chongji_jian_label.anchor = GridBagConstraints.WEST;
		gbc_chongji_jian_label.insets = new Insets(0, 0, 5, 5);
		gbc_chongji_jian_label.gridx = 2;
		gbc_chongji_jian_label.gridy = 15;
		mid_right.add(chongji_jian_label, gbc_chongji_jian_label);
		
		chongji_ci = new JTextField();
		chongji_ci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(chongji_ci.getText().trim().equals("")) chongji_ci.setBackground(new Color(255, 255, 255));
				else chongji_ci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_chongji_ci = new GridBagConstraints();
		gbc_chongji_ci.insets = new Insets(0, 0, 5, 0);
		gbc_chongji_ci.fill = GridBagConstraints.HORIZONTAL;
		gbc_chongji_ci.gridx = 5;
		gbc_chongji_ci.gridy = 15;
		mid_right.add(chongji_ci, gbc_chongji_ci);
		chongji_ci.setColumns(10);
		chongji_ci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		//TODO 铁素体
		tie_label = new JLabel("\u94C1\u7D20\u4F53");
		GridBagConstraints gbc_tie_label = new GridBagConstraints();
		gbc_tie_label.anchor = GridBagConstraints.EAST;
		gbc_tie_label.insets = new Insets(0, 0, 5, 5);
		gbc_tie_label.gridx = 0;
		gbc_tie_label.gridy = 16;
		mid_right.add(tie_label, gbc_tie_label);
		
		tie_dian = new JTextField();
		tie_dian.setColumns(3);
		GridBagConstraints gbc_tie_dian = new GridBagConstraints();
		gbc_tie_dian.anchor = GridBagConstraints.EAST;
		gbc_tie_dian.insets = new Insets(0, 0, 5, 5);
		gbc_tie_dian.gridx = 1;
		gbc_tie_dian.gridy = 16;
		mid_right.add(tie_dian, gbc_tie_dian);
		tie_dian.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		tie_dian_label = new JLabel("\u70B9");
		GridBagConstraints gbc_tie_dian_label = new GridBagConstraints();
		gbc_tie_dian_label.anchor = GridBagConstraints.WEST;
		gbc_tie_dian_label.insets = new Insets(0, 0, 5, 5);
		gbc_tie_dian_label.gridx = 2;
		gbc_tie_dian_label.gridy = 16;
		mid_right.add(tie_dian_label, gbc_tie_dian_label);
		
		tie_ci = new JTextField();
		tie_ci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(tie_ci.getText().trim().equals("")) tie_ci.setBackground(new Color(255, 255, 255));
				else tie_ci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_tie_ci = new GridBagConstraints();
		gbc_tie_ci.insets = new Insets(0, 0, 5, 0);
		gbc_tie_ci.fill = GridBagConstraints.HORIZONTAL;
		gbc_tie_ci.gridx = 5;
		gbc_tie_ci.gridy = 16;
		mid_right.add(tie_ci, gbc_tie_ci);
		tie_ci.setColumns(10);
		tie_ci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		//TODO 金相
		jinxiang_label = new JLabel("\u91D1\u76F8");
		GridBagConstraints gbc_jinxiang_label = new GridBagConstraints();
		gbc_jinxiang_label.anchor = GridBagConstraints.EAST;
		gbc_jinxiang_label.insets = new Insets(0, 0, 5, 5);
		gbc_jinxiang_label.gridx = 0;
		gbc_jinxiang_label.gridy = 17;
		mid_right.add(jinxiang_label, gbc_jinxiang_label);
		
		jinxiang_jian = new JTextField();
		jinxiang_jian.setColumns(3);
		GridBagConstraints gbc_jinxiang_jian = new GridBagConstraints();
		gbc_jinxiang_jian.anchor = GridBagConstraints.EAST;
		gbc_jinxiang_jian.insets = new Insets(0, 0, 5, 5);
		gbc_jinxiang_jian.gridx = 1;
		gbc_jinxiang_jian.gridy = 17;
		mid_right.add(jinxiang_jian, gbc_jinxiang_jian);
		jinxiang_jian.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		jinxiang_jian_label = new JLabel("\u4EF6");
		GridBagConstraints gbc_jinxiang_jian_label = new GridBagConstraints();
		gbc_jinxiang_jian_label.anchor = GridBagConstraints.WEST;
		gbc_jinxiang_jian_label.insets = new Insets(0, 0, 5, 5);
		gbc_jinxiang_jian_label.gridx = 2;
		gbc_jinxiang_jian_label.gridy = 17;
		mid_right.add(jinxiang_jian_label, gbc_jinxiang_jian_label);
		
		jinxiang_ci = new JTextField();
		jinxiang_ci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(jinxiang_ci.getText().trim().equals("")) jinxiang_ci.setBackground(new Color(255, 255, 255));
				else jinxiang_ci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_jinxiang_ci = new GridBagConstraints();
		gbc_jinxiang_ci.insets = new Insets(0, 0, 5, 0);
		gbc_jinxiang_ci.fill = GridBagConstraints.HORIZONTAL;
		gbc_jinxiang_ci.gridx = 5;
		gbc_jinxiang_ci.gridy = 17;
		mid_right.add(jinxiang_ci, gbc_jinxiang_ci);
		jinxiang_ci.setColumns(10);
		jinxiang_ci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		//TODO 点口
		diankou_label = new JLabel("\u70B9\u53E3/\u7279\u914D");
		GridBagConstraints gbc_diankou_label = new GridBagConstraints();
		gbc_diankou_label.anchor = GridBagConstraints.EAST;
		gbc_diankou_label.insets = new Insets(0, 0, 5, 5);
		gbc_diankou_label.gridx = 0;
		gbc_diankou_label.gridy = 18;
		mid_right.add(diankou_label, gbc_diankou_label);
		
		diankou_amount = new JTextField();
		diankou_amount.setColumns(3);
		GridBagConstraints gbc_diankou_amount = new GridBagConstraints();
		gbc_diankou_amount.anchor = GridBagConstraints.EAST;
		gbc_diankou_amount.insets = new Insets(0, 0, 5, 5);
		gbc_diankou_amount.gridx = 1;
		gbc_diankou_amount.gridy = 18;
		mid_right.add(diankou_amount, gbc_diankou_amount);
		diankou_amount.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		diankou_amount_label = new JLabel("\u5929");
		GridBagConstraints gbc_diankou_amount_label = new GridBagConstraints();
		gbc_diankou_amount_label.anchor = GridBagConstraints.WEST;
		gbc_diankou_amount_label.insets = new Insets(0, 0, 5, 5);
		gbc_diankou_amount_label.gridx = 2;
		gbc_diankou_amount_label.gridy = 18;
		mid_right.add(diankou_amount_label, gbc_diankou_amount_label);
		
		//TODO 其他1
		qita1_label = new JLabel("\u5176\u4ED61");
		qita1_label.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_qita1_label = new GridBagConstraints();
		gbc_qita1_label.anchor = GridBagConstraints.EAST;
		gbc_qita1_label.insets = new Insets(0, 0, 5, 5);
		gbc_qita1_label.gridx = 0;
		gbc_qita1_label.gridy = 19;
		mid_right.add(qita1_label, gbc_qita1_label);
		
		qita1_amount = new JTextField();
		qita1_amount.setColumns(3);
		GridBagConstraints gbc_qita1_amount = new GridBagConstraints();
		gbc_qita1_amount.anchor = GridBagConstraints.EAST;
		gbc_qita1_amount.insets = new Insets(0, 0, 5, 5);
		gbc_qita1_amount.gridx = 1;
		gbc_qita1_amount.gridy = 19;
		mid_right.add(qita1_amount, gbc_qita1_amount);
		qita1_amount.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		//TODO 其他2
		qita2_label = new JLabel("\u5176\u4ED62");
		GridBagConstraints gbc_qita2_label = new GridBagConstraints();
		gbc_qita2_label.anchor = GridBagConstraints.EAST;
		gbc_qita2_label.insets = new Insets(0, 0, 5, 5);
		gbc_qita2_label.gridx = 2;
		gbc_qita2_label.gridy = 19;
		mid_right.add(qita2_label, gbc_qita2_label);
		
		qita2_amount = new JTextField();
		qita2_amount.setColumns(3);
		GridBagConstraints gbc_qita2_amount = new GridBagConstraints();
		gbc_qita2_amount.anchor = GridBagConstraints.WEST;
		gbc_qita2_amount.insets = new Insets(0, 0, 5, 5);
		gbc_qita2_amount.gridx = 3;
		gbc_qita2_amount.gridy = 19;
		mid_right.add(qita2_amount, gbc_qita2_amount);
		qita2_amount.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		//TODO 其他3
		qita3_label = new JLabel("\u5176\u4ED63");
		GridBagConstraints gbc_qita3_label = new GridBagConstraints();
		gbc_qita3_label.anchor = GridBagConstraints.EAST;
		gbc_qita3_label.insets = new Insets(0, 0, 5, 5);
		gbc_qita3_label.gridx = 4;
		gbc_qita3_label.gridy = 19;
		mid_right.add(qita3_label, gbc_qita3_label);
		
		qita3_amount = new JTextField();
		qita3_amount.setColumns(3);
		GridBagConstraints gbc_qita3_amount = new GridBagConstraints();
		gbc_qita3_amount.anchor = GridBagConstraints.WEST;
		gbc_qita3_amount.insets = new Insets(0, 0, 5, 0);
		gbc_qita3_amount.gridx = 5;
		gbc_qita3_amount.gridy = 19;
		mid_right.add(qita3_amount, gbc_qita3_amount);
		qita3_amount.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		
		//TODO 底部容器
		bottom = new JPanel();
		add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		label = new JLabel("                    ");
		bottom.add(label);
		
		inReJB = new JButton("\u63D0\u4EA4\u62A5\u544A\u5355");
		inReJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insetToReport();
			}
		});
		bottom.add(inReJB);
		
		label_1 = new JLabel("                   ");
		bottom.add(label_1);
		
		resetJB = new JButton("\u91CD\u7F6E");
		resetJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				renewAction();
				report_num.setText(""); report_date.setText(""); qianzheng_num.setText(""); qianzheng_date.setText(""); person_send.setText(""); settle_num.setText(""); settle_date.setText("");settle_money.setText(""); invoice_num.setText(""); invoice_money.setText(""); invoice_date.setText(""); collected_amount.setText(""); collected_date.setText(""); x_zhang.setText(""); x_ci.setText("");  y_zhang.setText(""); y_ci.setText("");  chaosheng_mi.setText(""); chaosheng_dao.setText(""); chaosheng_ci.setText("");  cifen_mi.setText(""); cifen_dao.setText(""); cifen_ci.setText("");  shentou_mi.setText(""); shentou_dao.setText(""); shentou_ci.setText("");  cehou_dian.setText(""); cehou_ci.setText("");  biaopei_ri.setText(""); guangpuquan_dian.setText(""); guangpuquan_ge.setText(""); guangpuquan_ci.setText("");  guangpuban_dian.setText(""); guangpuban_ge.setText(""); guangpuban_ci.setText("");  yingdu_dian.setText(""); yingdu_ci.setText("");  damo_mi.setText(""); damo_ci.setText("");  tofd_mi.setText(""); tofd_ci.setText("");  lashen_jian.setText(""); lashen_ci.setText(""); wanqu_jian.setText(""); wanqu_ci.setText("");  chongji_jian.setText(""); chongji_ci.setText(""); tie_dian.setText(""); tie_ci.setText("");  jinxiang_jian.setText(""); jinxiang_ci.setText(""); diankou_amount.setText(""); qita1_amount.setText(""); qita2_amount.setText(""); qita3_amount.setText(""); report_beizhu.setText("");
				
			}
		});
		bottom.add(resetJB);
		
		label_2 = new JLabel("              ");
		bottom.add(label_2);
		
		renewJB = new JButton("\u5237\u65B0");
		renewJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				renewAction();
			}
		});
		bottom.add(renewJB);
	}
	
	//TODO 计算器
	private void toField(FocusEvent e){
		calTF = (JTextField)e.getComponent();
		
	}
	
	//TODO 刷新下拉框的方法
	@SuppressWarnings("unchecked")
	private void renewAction(){
		//指令单号
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
		
		//委托单位
		top.remove(cb1);
		cb1 = new JComboBox<String>();
		cb1.setModel( (DefaultComboBoxModel<String>)sm.search(3, 0));
		cb1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// 筛选符合条件的工程名称
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
		
		
		//项目名称
		top.remove(cb2);
		cb2 = new JComboBox<String>();
		cb2.setModel( (DefaultComboBoxModel<String>)sm.search(4, 0));
		cb2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// 筛选指令单号
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
	
	
	
	private int insetToReport(){
		int op = JOptionPane.showConfirmDialog(this, "确认添加该报告编号？", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(op == JOptionPane.CANCEL_OPTION || op == JOptionPane.CLOSED_OPTION ) return 1;
		
		String[] data = new String[58];
		//0处理指令单号，不能为空
		data[0] = cb0.getText().trim();
		if(data[0].equals("")) {JOptionPane.showMessageDialog(null, "请输入指令单号", "失败", JOptionPane.ERROR_MESSAGE);return 1;}
		//1处理报告编号，不为空
		data[1] = report_num.getText().trim();
		if(data[1].equals("")) {JOptionPane.showMessageDialog(null, "请输入报告编号", "失败", JOptionPane.ERROR_MESSAGE);return 2;}
		//2处理报告日期
		data[2] = report_date.getText().trim();
		if( (!data[2].equals("")) && (!pattern2.matcher(data[2]).matches()) ) {JOptionPane.showMessageDialog(null, "报告日期格式应为，如：20160101", "失败", JOptionPane.ERROR_MESSAGE);return 3;}
		//3处理签证单号
		data[3] = qianzheng_num.getText().trim();
		//4处理签证单签回日期
		data[4] = qianzheng_date.getText().trim();
		if( (!data[4].equals("")) && (!pattern2.matcher(data[4]).matches()) ) {JOptionPane.showMessageDialog(null, "签证单签回日期格式应为，如：20160101", "失败", JOptionPane.ERROR_MESSAGE);return 3;}
		//5处理送报人
		data[5] = person_send.getText().trim();
		//6结算编号
		data[6] = settle_num.getText().trim();
		//7结算时间
		data[7] = settle_date.getText().trim();
		if( (!data[7].equals("")) && (!pattern2.matcher(data[7]).matches()) ) {JOptionPane.showMessageDialog(null, "结算时间格式应为，如：20160101", "失败", JOptionPane.ERROR_MESSAGE);return 4;}
		//8结算金额
		data[8] = settle_money.getText().trim();
		//9发票号
		data[9] = invoice_num.getText().trim();
		//10开票额
		data[10] = invoice_money.getText().trim();
		//11开票日期
		data[11] = invoice_date.getText().trim();
		if( (!data[11].equals("")) && (!pattern2.matcher(data[11]).matches()) ) {JOptionPane.showMessageDialog(null, "开票日期格式应为，如：20160101", "失败", JOptionPane.ERROR_MESSAGE);return 5;}
		//12收款额
		data[12] = collected_amount.getText().trim();
		//13收款日期
		data[13] = collected_date.getText().trim();
		if( (!data[13].equals("")) && (!pattern2.matcher(data[13]).matches()) ) {JOptionPane.showMessageDialog(null, "收款日期格式应为，如：20160101", "失败", JOptionPane.ERROR_MESSAGE);return 6;}
		
		//14处理x光/张
		data[14] = x_zhang.getText().trim();
		//15处理X光/次
		data[15] = x_ci.getText().trim();
		//16处理y/张
		data[16] = y_zhang.getText().trim();
		//17处理y/次
		data[17] = y_ci.getText().trim();
		//18处理超声/米
		data[18] = chaosheng_mi.getText().trim();
		//19处理超声/道
		data[19] = chaosheng_dao.getText().trim();
		//20处理超声/次	
		data[20] = chaosheng_ci.getText().trim();
		//21处理磁粉/米
		data[21] = cifen_mi.getText().trim();
		//22处理磁粉/道
		data[22] = cifen_dao.getText().trim();
		//23处理磁粉/次	
		data[23] = cifen_ci.getText().trim();		
		//24处理渗透/米
		data[24] = shentou_mi.getText().trim();
		//25处理渗透/道
		data[25] = shentou_dao.getText().trim();
		//26处理渗透/次	
		data[26] = shentou_ci.getText().trim();	
		//27处理测厚/点
		data[27] = cehou_dian.getText().trim();
		//28处理测厚/次
		data[28] = cehou_ci.getText().trim();
		//29处理表面配合/工日
		data[29] = biaopei_ri.getText().trim();
		//30处理全定量/点
		data[30] = guangpuquan_dian.getText().trim();
		//31处理全定量/个元素
		data[31] = guangpuquan_ge.getText().trim();
		//32处理全定量/次
		data[32] = guangpuquan_ci.getText().trim();
		//33处理半定量/点
		data[33] = guangpuban_dian.getText().trim();
		//34处理半定量/个元素
		data[34] = guangpuban_ge.getText().trim();
		//35处理半定量/次
		data[35] = guangpuban_ci.getText().trim();
		//36处理硬度/点
		data[36] = yingdu_dian.getText().trim();
		//37处理硬度/次
		data[37] = yingdu_ci.getText().trim();
		//38处理打磨/米
		data[38] = damo_mi.getText().trim();
		//39处理打磨/次
		data[39] = damo_ci.getText().trim();
		//40处理tofd/米
		data[40] = tofd_mi.getText().trim();
		//41处理tofd/次
		data[41] = tofd_ci.getText().trim();
		//42处理拉伸/件
		data[42] = lashen_jian.getText().trim();
		//43处理拉伸/次
		data[43] = lashen_ci.getText().trim();
		//44处理弯曲/件
		data[44] = wanqu_jian.getText().trim();
		//45处理弯曲/次
		data[45] = wanqu_ci.getText().trim();
		//46处理冲击/件
		data[46] = chongji_jian.getText().trim();
		//47处理冲击/次
		data[47] = chongji_ci.getText().trim();
		//48处理铁素体/点
		data[48] = tie_dian.getText().trim();
		//49处理铁素体/次
		data[49] = tie_ci.getText().trim();
		//50处理金相/件
		data[50] = jinxiang_jian.getText().trim();
		//51处理金相/次
		data[51] = jinxiang_ci.getText().trim();
		//52处理点口、特检院配合、技术指导 数量
		data[52] = diankou_amount.getText().trim();
		//53处理其他项目1数量
		data[53] = qita1_amount.getText().trim();
		//54处理其他项目2数量
		data[54] = qita2_amount.getText().trim();
		//55处理其他项目3数量
		data[55] = qita3_amount.getText().trim();
		//56处理备注
		data[56] = report_beizhu.getText().trim();
		data[57] = group;
		
		sm.insert_report_table(data);
		
		return 0;
		
		
		
	}
	
	

}