package MyPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;

import MainFrame.DBTableModel;
import MainFrame.SQLManager;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.FlowLayout;

import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SeRe extends JPanel {
	

	/**
	 * 查询报告表.
	 */
	
	
	private SQLManager sm;
	private String group;//全局变量的代值
	 //TODO 顶部容器
	 private JPanel top ;
	 private JLabel label0;
	 private MyComboBox_TextFieldPane cb0;
	 private JLabel label1 ;
	 private JComboBox<String> cb1 ;
	 private JLabel label2 ;
	 private JComboBox<String> cb2 ;
	 private JLabel label_3;
	 private JLabel report_num_label;
	 private JTextField report_num;
	 private JLabel report_date1_label;
	 private JTextField report_date1;
	 private JLabel report_date2_label;
	 private JTextField report_date2;
	 private JLabel qianzheng_num_label;
	 private JTextField qianzheng_num;
	 private JLabel qianzheng_date1_label;
	 private JTextField qianzheng_date1;
	 private JLabel qianzheng_date2_label;
	 private JTextField qianzheng_date2;
	 private JLabel settle_num_label;
	 private JTextField settle_num;
	 private JLabel settle_date1_label;
	 private JTextField settle_date1;
	 private JLabel settle_date2_label;
	 private JTextField settle_date2;
	 private JLabel invoice_num_label;
	 private JTextField invoice_num;
	 private JLabel invoice_date1_label;
	 private JTextField invoice_date1;
	 private JLabel invoice_date2_label;
	 private JTextField invoice_date2;
	 private JLabel collected_date1_label;
	 private JTextField collected_date1;
	 private JLabel collected_date2_label;
	 private JTextField collected_date2;
	 
	//TODO 底部容器
	 private JPanel bottom;
	 private JLabel label4;
	 private JButton searchJB;
	 private JLabel label_5;
	 private JButton modifyJB;
	 private JLabel label_6;
	 private JButton deleteJB;
	 
	 
	 //中部容器
	 private ResultSet rs;
	 private JScrollPane jsp;
	 private DBTableModel DBTableModel;
	 private JTable jt;
	 private JLabel label_4;
	 private JButton renewJB;
	 
	public SeRe(SQLManager s, String group) {
		this.group=group;
		this.sm=s;
		setLayout(new BorderLayout(0, 0));
		init();

		

	}

	
	@SuppressWarnings("unchecked")
	private void init(){
		top = new JPanel();
		top.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u67E5\u8BE2\u6761\u4EF6", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 0, 0)));
		add(top, BorderLayout.NORTH);
		GridBagLayout gbl_top = new GridBagLayout();
		gbl_top.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_top.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_top.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_top.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		top.setLayout(gbl_top);
		
		// TODO 指令单号label
		label0 = new JLabel("\u6307\u4EE4\u5355\u53F7");
		label0.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_label0 = new GridBagConstraints();
		gbc_label0.anchor = GridBagConstraints.EAST;
		gbc_label0.insets = new Insets(0, 0, 5, 5);
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
		gbc_cb0.insets = new Insets(0, 0, 5, 5);
		gbc_cb0.gridx = 1;
		gbc_cb0.gridy = 0;
		top.add(cb0, gbc_cb0);
		
		//TODO 委托单位label
		label1 = new JLabel("\u59D4\u6258\u5355\u4F4D");
		GridBagConstraints gbc_label1 = new GridBagConstraints();
		gbc_label1.anchor = GridBagConstraints.EAST;
		gbc_label1.insets = new Insets(0, 0, 5, 5);
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
		gbc_cb1.insets = new Insets(0, 0, 5, 5);
		gbc_cb1.gridx = 3;
		gbc_cb1.gridy = 0;
		top.add(cb1, gbc_cb1);
		
		//TODO 工程名称label
		label2 = new JLabel("\u5DE5\u7A0B\u540D\u79F0");
		GridBagConstraints gbc_label2 = new GridBagConstraints();
		gbc_label2.insets = new Insets(0, 0, 5, 5);
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
		gbc_cb2.insets = new Insets(0, 0, 5, 5);
		gbc_cb2.gridx = 5;
		gbc_cb2.gridy = 0;
		top.add(cb2, gbc_cb2);
		
		//TODO top容器最右侧空白label,可添加状态
		label_3 = new JLabel("");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.insets = new Insets(0, 0, 5, 0);
		gbc_label_3.weighty = 5.0;
		gbc_label_3.weightx = 5.0;
		gbc_label_3.ipady = 1;
		gbc_label_3.ipadx = 1;
		gbc_label_3.fill = GridBagConstraints.BOTH;
		gbc_label_3.gridx = 6;
		gbc_label_3.gridy = 0;
		top.add(label_3, gbc_label_3);
		
		//TODO 报告查询条件
		report_num_label = new JLabel("\u62A5\u544A\u7F16\u53F7");
		GridBagConstraints gbc_report_num_label = new GridBagConstraints();
		gbc_report_num_label.anchor = GridBagConstraints.EAST;
		gbc_report_num_label.insets = new Insets(0, 0, 5, 5);
		gbc_report_num_label.gridx = 0;
		gbc_report_num_label.gridy = 1;
		top.add(report_num_label, gbc_report_num_label);
		
		report_num = new JTextField();
		GridBagConstraints gbc_report_num = new GridBagConstraints();
		gbc_report_num.fill = GridBagConstraints.HORIZONTAL;
		gbc_report_num.insets = new Insets(0, 0, 5, 5);
		gbc_report_num.gridx = 1;
		gbc_report_num.gridy = 1;
		top.add(report_num, gbc_report_num);
		report_num.setColumns(10);
		
		report_date1_label = new JLabel("\u62A5\u544A\u65E5\u671F \u4ECE");
		GridBagConstraints gbc_report_date1_label = new GridBagConstraints();
		gbc_report_date1_label.anchor = GridBagConstraints.EAST;
		gbc_report_date1_label.insets = new Insets(0, 0, 5, 5);
		gbc_report_date1_label.gridx = 2;
		gbc_report_date1_label.gridy = 1;
		top.add(report_date1_label, gbc_report_date1_label);
		
		report_date1 = new JTextField();
		GridBagConstraints gbc_report_date1 = new GridBagConstraints();
		gbc_report_date1.fill = GridBagConstraints.HORIZONTAL;
		gbc_report_date1.insets = new Insets(0, 0, 5, 5);
		gbc_report_date1.gridx = 3;
		gbc_report_date1.gridy = 1;
		top.add(report_date1, gbc_report_date1);
		report_date1.setColumns(10);
		
		report_date2_label = new JLabel("\u81F3");
		GridBagConstraints gbc_report_date2_label = new GridBagConstraints();
		gbc_report_date2_label.insets = new Insets(0, 0, 5, 5);
		gbc_report_date2_label.gridx = 4;
		gbc_report_date2_label.gridy = 1;
		top.add(report_date2_label, gbc_report_date2_label);
		
		report_date2 = new JTextField();
		GridBagConstraints gbc_report_date2 = new GridBagConstraints();
		gbc_report_date2.fill = GridBagConstraints.HORIZONTAL;
		gbc_report_date2.insets = new Insets(0, 0, 5, 5);
		gbc_report_date2.gridx = 5;
		gbc_report_date2.gridy = 1;
		top.add(report_date2, gbc_report_date2);
		report_date2.setColumns(10);
		
		//TODO 签证单查询条件
		qianzheng_num_label = new JLabel("\u7B7E\u8BC1\u5355\u53F7");
		GridBagConstraints gbc_qianzheng_num_label = new GridBagConstraints();
		gbc_qianzheng_num_label.anchor = GridBagConstraints.EAST;
		gbc_qianzheng_num_label.insets = new Insets(0, 0, 5, 5);
		gbc_qianzheng_num_label.gridx = 0;
		gbc_qianzheng_num_label.gridy = 2;
		top.add(qianzheng_num_label, gbc_qianzheng_num_label);
		
		qianzheng_num = new JTextField();
		GridBagConstraints gbc_qianzheng_num = new GridBagConstraints();
		gbc_qianzheng_num.fill = GridBagConstraints.HORIZONTAL;
		gbc_qianzheng_num.insets = new Insets(0, 0, 5, 5);
		gbc_qianzheng_num.gridx = 1;
		gbc_qianzheng_num.gridy = 2;
		top.add(qianzheng_num, gbc_qianzheng_num);
		qianzheng_num.setColumns(10);
		
		qianzheng_date1_label = new JLabel("\u7B7E\u56DE\u65E5\u671F \u4ECE");
		GridBagConstraints gbc_qianzheng_date1_label = new GridBagConstraints();
		gbc_qianzheng_date1_label.anchor = GridBagConstraints.EAST;
		gbc_qianzheng_date1_label.insets = new Insets(0, 0, 5, 5);
		gbc_qianzheng_date1_label.gridx = 2;
		gbc_qianzheng_date1_label.gridy = 2;
		top.add(qianzheng_date1_label, gbc_qianzheng_date1_label);
		
		qianzheng_date1 = new JTextField();
		GridBagConstraints gbc_qianzheng_date1 = new GridBagConstraints();
		gbc_qianzheng_date1.fill = GridBagConstraints.HORIZONTAL;
		gbc_qianzheng_date1.insets = new Insets(0, 0, 5, 5);
		gbc_qianzheng_date1.gridx = 3;
		gbc_qianzheng_date1.gridy = 2;
		top.add(qianzheng_date1, gbc_qianzheng_date1);
		qianzheng_date1.setColumns(10);
		
		qianzheng_date2_label = new JLabel("\u81F3");
		GridBagConstraints gbc_qianzheng_date2_label = new GridBagConstraints();
		gbc_qianzheng_date2_label.insets = new Insets(0, 0, 5, 5);
		gbc_qianzheng_date2_label.gridx = 4;
		gbc_qianzheng_date2_label.gridy = 2;
		top.add(qianzheng_date2_label, gbc_qianzheng_date2_label);
		
		qianzheng_date2 = new JTextField();
		GridBagConstraints gbc_qianzheng_date2 = new GridBagConstraints();
		gbc_qianzheng_date2.fill = GridBagConstraints.HORIZONTAL;
		gbc_qianzheng_date2.insets = new Insets(0, 0, 5, 5);
		gbc_qianzheng_date2.gridx = 5;
		gbc_qianzheng_date2.gridy = 2;
		top.add(qianzheng_date2, gbc_qianzheng_date2);
		qianzheng_date2.setColumns(10);
		
		//TODO 结算查询条件
		settle_num_label = new JLabel("\u7ED3\u7B97\u7F16\u53F7");
		GridBagConstraints gbc_settle_num_label = new GridBagConstraints();
		gbc_settle_num_label.anchor = GridBagConstraints.EAST;
		gbc_settle_num_label.insets = new Insets(0, 0, 5, 5);
		gbc_settle_num_label.gridx = 0;
		gbc_settle_num_label.gridy = 3;
		top.add(settle_num_label, gbc_settle_num_label);
		
		settle_num = new JTextField();
		GridBagConstraints gbc_settle_num = new GridBagConstraints();
		gbc_settle_num.fill = GridBagConstraints.HORIZONTAL;
		gbc_settle_num.insets = new Insets(0, 0, 5, 5);
		gbc_settle_num.gridx = 1;
		gbc_settle_num.gridy = 3;
		top.add(settle_num, gbc_settle_num);
		settle_num.setColumns(10);
		
		settle_date1_label = new JLabel("\u7ED3\u7B97\u65E5\u671F \u4ECE");
		GridBagConstraints gbc_settle_date1_label = new GridBagConstraints();
		gbc_settle_date1_label.anchor = GridBagConstraints.EAST;
		gbc_settle_date1_label.insets = new Insets(0, 0, 5, 5);
		gbc_settle_date1_label.gridx = 2;
		gbc_settle_date1_label.gridy = 3;
		top.add(settle_date1_label, gbc_settle_date1_label);
		
		settle_date1 = new JTextField();
		GridBagConstraints gbc_settle_date1 = new GridBagConstraints();
		gbc_settle_date1.fill = GridBagConstraints.HORIZONTAL;
		gbc_settle_date1.insets = new Insets(0, 0, 5, 5);
		gbc_settle_date1.gridx = 3;
		gbc_settle_date1.gridy = 3;
		top.add(settle_date1, gbc_settle_date1);
		settle_date1.setColumns(10);
		
		settle_date2_label = new JLabel("\u81F3");
		GridBagConstraints gbc_settle_date2_label = new GridBagConstraints();
		gbc_settle_date2_label.insets = new Insets(0, 0, 5, 5);
		gbc_settle_date2_label.gridx = 4;
		gbc_settle_date2_label.gridy = 3;
		top.add(settle_date2_label, gbc_settle_date2_label);
		
		settle_date2 = new JTextField();
		GridBagConstraints gbc_settle_date2 = new GridBagConstraints();
		gbc_settle_date2.fill = GridBagConstraints.HORIZONTAL;
		gbc_settle_date2.insets = new Insets(0, 0, 5, 5);
		gbc_settle_date2.gridx = 5;
		gbc_settle_date2.gridy = 3;
		top.add(settle_date2, gbc_settle_date2);
		settle_date2.setColumns(10);
		
		//TODO 发票查询条件
		invoice_num_label = new JLabel("\u53D1\u7968\u53F7");
		GridBagConstraints gbc_invoice_num_label = new GridBagConstraints();
		gbc_invoice_num_label.anchor = GridBagConstraints.EAST;
		gbc_invoice_num_label.insets = new Insets(0, 0, 5, 5);
		gbc_invoice_num_label.gridx = 0;
		gbc_invoice_num_label.gridy = 4;
		top.add(invoice_num_label, gbc_invoice_num_label);
		
		invoice_num = new JTextField();
		GridBagConstraints gbc_invoice_num = new GridBagConstraints();
		gbc_invoice_num.fill = GridBagConstraints.HORIZONTAL;
		gbc_invoice_num.insets = new Insets(0, 0, 5, 5);
		gbc_invoice_num.gridx = 1;
		gbc_invoice_num.gridy = 4;
		top.add(invoice_num, gbc_invoice_num);
		invoice_num.setColumns(10);
		
		invoice_date1_label = new JLabel("\u5F00\u7968\u65E5\u671F \u4ECE");
		GridBagConstraints gbc_invoice_date1_label = new GridBagConstraints();
		gbc_invoice_date1_label.anchor = GridBagConstraints.EAST;
		gbc_invoice_date1_label.insets = new Insets(0, 0, 5, 5);
		gbc_invoice_date1_label.gridx = 2;
		gbc_invoice_date1_label.gridy = 4;
		top.add(invoice_date1_label, gbc_invoice_date1_label);
		
		invoice_date1 = new JTextField();
		GridBagConstraints gbc_invoice_date1 = new GridBagConstraints();
		gbc_invoice_date1.fill = GridBagConstraints.HORIZONTAL;
		gbc_invoice_date1.insets = new Insets(0, 0, 5, 5);
		gbc_invoice_date1.gridx = 3;
		gbc_invoice_date1.gridy = 4;
		top.add(invoice_date1, gbc_invoice_date1);
		invoice_date1.setColumns(10);
		
		invoice_date2_label = new JLabel("\u81F3");
		GridBagConstraints gbc_invoice_date2_label = new GridBagConstraints();
		gbc_invoice_date2_label.insets = new Insets(0, 0, 5, 5);
		gbc_invoice_date2_label.gridx = 4;
		gbc_invoice_date2_label.gridy = 4;
		top.add(invoice_date2_label, gbc_invoice_date2_label);
		
		invoice_date2 = new JTextField();
		GridBagConstraints gbc_invoice_date2 = new GridBagConstraints();
		gbc_invoice_date2.fill = GridBagConstraints.HORIZONTAL;
		gbc_invoice_date2.insets = new Insets(0, 0, 5, 5);
		gbc_invoice_date2.gridx = 5;
		gbc_invoice_date2.gridy = 4;
		top.add(invoice_date2, gbc_invoice_date2);
		invoice_date2.setColumns(10);
		
		//TODO 收款查询条件
		collected_date1_label = new JLabel("\u6536\u6B3E\u65E5\u671F \u4ECE");
		GridBagConstraints gbc_collected_date1_label = new GridBagConstraints();
		gbc_collected_date1_label.anchor = GridBagConstraints.EAST;
		gbc_collected_date1_label.insets = new Insets(0, 0, 0, 5);
		gbc_collected_date1_label.gridx = 2;
		gbc_collected_date1_label.gridy = 5;
		top.add(collected_date1_label, gbc_collected_date1_label);
		
		collected_date1 = new JTextField();
		GridBagConstraints gbc_collected_date1 = new GridBagConstraints();
		gbc_collected_date1.fill = GridBagConstraints.HORIZONTAL;
		gbc_collected_date1.insets = new Insets(0, 0, 0, 5);
		gbc_collected_date1.gridx = 3;
		gbc_collected_date1.gridy = 5;
		top.add(collected_date1, gbc_collected_date1);
		collected_date1.setColumns(10);
		
		collected_date2_label = new JLabel("\u81F3");
		GridBagConstraints gbc_collected_date2_label = new GridBagConstraints();
		gbc_collected_date2_label.insets = new Insets(0, 0, 0, 5);
		gbc_collected_date2_label.gridx = 4;
		gbc_collected_date2_label.gridy = 5;
		top.add(collected_date2_label, gbc_collected_date2_label);
		
		collected_date2 = new JTextField();
		GridBagConstraints gbc_collected_date2 = new GridBagConstraints();
		gbc_collected_date2.fill = GridBagConstraints.HORIZONTAL;
		gbc_collected_date2.insets = new Insets(0, 0, 0, 5);
		gbc_collected_date2.gridx = 5;
		gbc_collected_date2.gridy = 5;
		top.add(collected_date2, gbc_collected_date2);
		collected_date2.setColumns(10);
		
		bottom = new JPanel();
		add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		label4 = new JLabel("                    ");
		bottom.add(label4);
		
		//TODO 查询按钮
		searchJB = new JButton("\u67E5\u8BE2");
		searchJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			    
			}
		});
		bottom.add(searchJB);
		
		label_5 = new JLabel("            ");
		bottom.add(label_5);
		
		//TODO 修改按钮
		modifyJB = new JButton("\u4FEE\u6539");
		modifyJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modify();	
			}
		});
		bottom.add(modifyJB);
		
		label_6 = new JLabel("              ");
		bottom.add(label_6);
		
		//删除按钮
		deleteJB = new JButton("删除");
		deleteJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		bottom.add(deleteJB);
		
		label_4 = new JLabel("              ");
		bottom.add(label_4);
		
		renewJB = new JButton("\u5237\u65B0");
		renewJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				renew();
			}
		});
		bottom.add(renewJB);
		
		

	}
	
	
	
	
	
	private void repainth(ResultSet rs) {
		// TODO Auto-generated method stub
		if (jsp != null) this.remove(jsp);
		DBTableModel = new DBTableModel(rs);
		DBTableModel.isCountAll(true);
		jt = new JTable(DBTableModel);
		jsp = new JScrollPane(jt);
		jt.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                //Color color = Color.decode((String)table.getValueAt(row, 1));
            	if(row%2==0){
            	//这里可以手动修改漂亮适合的颜色，采用RGB模式，可以去网上进行颜色RGB查询方式得到RGB值
            	
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if(!isSelected){Color color =new Color(242,242,242);comp.setBackground(color);}
                return comp;
                }
            	//这个是底色，就让他是白色就可以	
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if(!isSelected){Color color = Color.white;comp.setBackground(color);}
                return comp;
            }
            
        });

		//设置列不可随容器组件大小变化自动调整宽度
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		DefaultTableColumnModel dcm = (DefaultTableColumnModel)jt.getColumnModel(); 
	    dcm.getColumn(0).setMinWidth(0); 
	    dcm.getColumn(0).setMaxWidth(0); 	
		add(jsp, BorderLayout.CENTER);
		this.revalidate();
		
	}
	
	private void  delete(){

		try {
			int sr=jt.getSelectedRow();
			rs.absolute(sr+1);
			int op = JOptionPane.showConfirmDialog(this, "确认为指令单号为："+rs.getString(2) +" 删除报告编号为 "+ rs.getString(3)+" 的报告?", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(op == JOptionPane.CANCEL_OPTION || op == JOptionPane.CLOSED_OPTION ) return ;
			rs.deleteRow();
			search();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	//TODO 查询动作
	private void search(){
		String seStr[] = new String[15];
		String sql = "select  re_id, cmd_num 指令单号, report_num 报告编号, report_date 报告日期, qianzheng_num 签证单号, qianzheng_date 签证单签回日期, person_send 送报人, settle_num 结算编号, settle_date 结算日期, settle_money 结算额, invoice_num 发票号, invoice_money 开票额, invoice_date 开票日期, collected_amount 收款额, collected_date 收款日期,  report_beizhu 备注  from report_table where bm_name= '" + group +"'";
		int i = 0;
		
		//0处理指令单号
		seStr[0] = cb0.getText().trim().equals("") ? null : cb0.getText().trim() ;
		if(seStr[0] != null){
			sql += " and  cmd_num like '"+ seStr[0] +"%' " ;
			i++;
		}
		//1 处理报告编号
		seStr[1] = report_num.getText().trim().equals("") ? null : report_num.getText().trim() ;
		if(seStr[1] != null){
			sql += " and  report_num like '"+ seStr[1] +"%' " ;
			i++;
		}
		
		//2处理报告日期起始
		seStr[2] = report_date1.getText().trim().equals("") ? null : report_date1.getText().trim() ;
		if(seStr[2] != null){
			sql += " and  report_date >=" + seStr[2];
			i++;
		}
		//3处理报告日期截止
		seStr[3] = report_date2.getText().trim().equals("") ? null : report_date2.getText().trim() ;
		if(seStr[3] != null){
			sql += " and  report_date <=" + seStr[3];
			i++;
		}
		//4处理签证单号
		seStr[4] = qianzheng_num.getText().trim().equals("") ? null : qianzheng_num.getText().trim() ;
		if(seStr[4] != null){
			sql += " and  qianzheng_num like '"+ seStr[4] +"%' " ;
			i++;
		}
		//5处理签回日期起始
		seStr[5] = qianzheng_date1.getText().trim().equals("") ? null : qianzheng_date1.getText().trim() ;
		if(seStr[5] != null){
			sql += " and  qianzheng_date >=" + seStr[5];
			i++;
		}
		//6处理签回日期截止
		seStr[6] = qianzheng_date2.getText().trim().equals("") ? null : qianzheng_date2.getText().trim() ;
		if(seStr[6] != null){
			sql += " and  qianzheng_date <=" + seStr[6];
			i++;
		}
		//7处理结算编号
		seStr[7] = settle_num.getText().trim().equals("") ? null : settle_num.getText().trim() ;
		if(seStr[7] != null){
			sql += " and  settle_num like '"+ seStr[7] +"%' " ;
			i++;
		}
		//8处理结算日期起始
		seStr[8] = settle_date1.getText().trim().equals("") ? null : settle_date1.getText().trim() ;
		if(seStr[8] != null){
			sql += " and  settle_date >=" + seStr[8];
			i++;
		}
		//9处理结算日期截止
		seStr[9] = settle_date2.getText().trim().equals("") ? null : settle_date2.getText().trim() ;
		if(seStr[9] != null){
			
			sql += " and  settle_date <=" + seStr[9];
			i++;
		}
		//10处理发票号
		seStr[10] = invoice_num.getText().trim().equals("") ? null : invoice_num.getText().trim() ;
		if(seStr[10] != null){
			
			sql += " and  invoice_num like '"+ seStr[10] +"%' " ;
			i++;
		}
		//11处理开票日期起始
		seStr[11] = invoice_date1.getText().trim().equals("") ? null : invoice_date1.getText().trim() ;
		if(seStr[11] != null){
		
			sql += " and  invoice_date >=" + seStr[11];
			i++;
		}
		//12处理开票日期起始
		seStr[12] = invoice_date2.getText().trim().equals("") ? null : invoice_date2.getText().trim() ;
		if(seStr[12] != null){
			
			sql += " and  invoice_date <=" + seStr[12];
			i++;
		}
		//13处理收款日期起始
		seStr[13] = collected_date1.getText().trim().equals("") ? null : collected_date1.getText().trim() ;
		if(seStr[13] != null){
		
			sql += " and  collected_date >=" + seStr[13];
			i++;
		}
		//14处理收款日期截止
		seStr[14] = collected_date2.getText().trim().equals("") ? null : collected_date2.getText().trim() ;
		if(seStr[14] != null){
		
			sql += " and  collected_date <=" + seStr[14];
			i++;
		}
		
		sql+=" group by re_id with rollup;";
		System.out.println(sql);
		if(i == 0){JOptionPane.showMessageDialog(null, "请输入查询条件", "失败", JOptionPane.ERROR_MESSAGE); return ;}
		

		rs = sm.submit_search(sql);
		repainth(rs);
	}
	
	
	
	
	private void modify(){	
			int rowindex=jt.getSelectedRow();
			int rowcount = jt.getSelectedRowCount() ;
			if(rowcount == 0){JOptionPane.showMessageDialog(null, "选择需要修改的行", "失败", JOptionPane.ERROR_MESSAGE); return ;}
			if(rowcount > 1){JOptionPane.showMessageDialog(null, "只能选择一行", "失败", JOptionPane.ERROR_MESSAGE); return ;}
			//jt.remove(i);
			String str=jt.getValueAt(rowindex, 0).toString();//取得re_id
			String sql = "select * from report_table where re_id='" + str + "'" ;
			System.out.println(sql);
			
			new SeReAmount(this ,sql );
		
	}
	
	
	@SuppressWarnings("unchecked")
	private void renew(){
		//TODO 指令单号输入下拉框
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
		gbc_cb0.insets = new Insets(0, 0, 5, 5);
		gbc_cb0.gridx = 1;
		gbc_cb0.gridy = 0;
		top.add(cb0, gbc_cb0);
		

		
		//TODO 委托单位下拉选择框
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
		gbc_cb1.insets = new Insets(0, 0, 5, 5);
		gbc_cb1.gridx = 3;
		gbc_cb1.gridy = 0;
		top.add(cb1, gbc_cb1);
		
		
		//TODO工程名称下拉选择框
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
		gbc_cb2.insets = new Insets(0, 0, 5, 5);
		gbc_cb2.gridx = 5;
		gbc_cb2.gridy = 0;
		top.add(cb2, gbc_cb2);
		
		updateUI();
		
	}
	
	
	
	
	
	
	private class SeReAmount extends JDialog{

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
		
		private JButton xiugai;
		private JButton quxiao;
		private JPanel bottom;
		private ResultSet ReRs;
		
		private String sql;
		
		SeReAmount(JPanel jp ,String sqll ){
			super( (JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, jp) , "修改报告表", true);
			this.sql = sqll;
			initSeRePane();
			ReRs = sm.submit_search(sql);
			initData();
			pack();
			setVisible(true);
		}
		
		private void initSeRePane(){
			
			mid_right = new JPanel();
			mid_right.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u62A5\u544A\u5DE5\u4F5C\u91CF", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GREEN));
			getContentPane().add(mid_right);
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
			GridBagConstraints gbc_x_ci = new GridBagConstraints();
			gbc_x_ci.insets = new Insets(0, 0, 5, 0);
			gbc_x_ci.fill = GridBagConstraints.HORIZONTAL;
			gbc_x_ci.gridx = 5;
			gbc_x_ci.gridy = 1;
			mid_right.add(x_ci, gbc_x_ci);
			x_ci.setColumns(10);
			
			
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
			
			//底部按钮
			bottom = new JPanel();
			GridBagConstraints gbc_bottom = new GridBagConstraints();
			gbc_bottom.anchor = GridBagConstraints.CENTER;
			gbc_bottom.insets = new Insets(0, 0, 5, 0);
			gbc_bottom.gridx = 0;
			gbc_bottom.gridy = 20;
			gbc_bottom.fill = GridBagConstraints.HORIZONTAL;
			gbc_bottom.gridwidth = 5;
			mid_right.add(bottom, gbc_bottom);
			bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JLabel label = new JLabel("                    ");
			bottom.add(label);
			
			xiugai = new JButton("修改");
			xiugai.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					xiugai();
				}
			});
			bottom.add(xiugai);
			
			JLabel label_1 = new JLabel("        ");
			bottom.add(label_1);
			
			quxiao = new JButton("取消");
			quxiao.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					quxiao();					
				}
			});
			bottom.add(quxiao);
			
			JLabel label_2 = new JLabel("      ");
			bottom.add(label_2);
			
			
			
			
			
		}
		
		
		private void initData(){
			//ResultSet ReRs = sm.submit_search(sql);
			try {
				ReRs.next();
				x_zhang.setText(ReRs.getString(16)==null? "" : ReRs.getString(16));
				x_ci.setText(ReRs.getString(17)==null? "" : ReRs.getString(17));
				y_zhang.setText(ReRs.getString(18)==null? "" : ReRs.getString(18));
				y_ci.setText(ReRs.getString(19)==null? "" : ReRs.getString(19));
				chaosheng_mi.setText(ReRs.getString(20)==null? "" : ReRs.getString(20));
				chaosheng_dao.setText(ReRs.getString(21)==null? "" : ReRs.getString(21));
				chaosheng_ci.setText(ReRs.getString(22)==null? "" : ReRs.getString(22));
				cifen_mi.setText(ReRs.getString(23)==null? "" : ReRs.getString(23));
				cifen_dao.setText(ReRs.getString(24)==null? "" : ReRs.getString(24));
				cifen_ci.setText(ReRs.getString(25)==null? "" : ReRs.getString(25));
				shentou_mi.setText(ReRs.getString(26)==null? "" : ReRs.getString(26));
				shentou_dao.setText(ReRs.getString(27)==null? "" : ReRs.getString(27));
				shentou_ci.setText(ReRs.getString(28)==null? "" : ReRs.getString(28));
				cehou_dian.setText(ReRs.getString(29)==null? "" : ReRs.getString(29));
				cehou_ci.setText(ReRs.getString(30)==null? "" : ReRs.getString(30));
				biaopei_ri.setText(ReRs.getString(31)==null? "" : ReRs.getString(31));
				guangpuquan_dian.setText(ReRs.getString(32)==null? "" : ReRs.getString(32));
				guangpuquan_ge.setText(ReRs.getString(33)==null? "" : ReRs.getString(33));
				guangpuquan_ci.setText(ReRs.getString(34)==null? "" : ReRs.getString(34)); 
				guangpuban_dian.setText(ReRs.getString(35)==null? "" : ReRs.getString(35));
				guangpuban_ge.setText(ReRs.getString(36)==null? "" : ReRs.getString(36));
				guangpuban_ci.setText(ReRs.getString(37)==null? "" : ReRs.getString(37)); 
				yingdu_dian.setText(ReRs.getString(38)==null? "" : ReRs.getString(38));
				yingdu_ci.setText(ReRs.getString(39)==null? "" : ReRs.getString(39));
				damo_mi.setText(ReRs.getString(40)==null? "" : ReRs.getString(40));
				damo_ci.setText(ReRs.getString(41)==null? "" : ReRs.getString(41));
				tofd_mi.setText(ReRs.getString(42)==null? "" : ReRs.getString(42));
				tofd_ci.setText(ReRs.getString(43)==null? "" : ReRs.getString(43)); 
				lashen_jian.setText(ReRs.getString(44)==null? "" : ReRs.getString(44));
				lashen_ci.setText(ReRs.getString(45)==null? "" : ReRs.getString(45));
				wanqu_jian.setText(ReRs.getString(46)==null? "" : ReRs.getString(46));
				wanqu_ci.setText(ReRs.getString(47)==null? "" : ReRs.getString(47));
				chongji_jian.setText(ReRs.getString(48)==null? "" : ReRs.getString(48));
				chongji_ci.setText(ReRs.getString(49)==null? "" : ReRs.getString(49));
				tie_dian.setText(ReRs.getString(50)==null? "" : ReRs.getString(50));
				tie_ci.setText(ReRs.getString(51)==null? "" : ReRs.getString(51));
				jinxiang_jian.setText(ReRs.getString(52)==null? "" : ReRs.getString(52));
				jinxiang_ci.setText(ReRs.getString(53)==null? "" : ReRs.getString(53));
				diankou_amount.setText(ReRs.getString(54)==null? "" : ReRs.getString(54));
				qita1_amount.setText(ReRs.getString(55)==null? "" : ReRs.getString(55));
				qita2_amount.setText(ReRs.getString(56)==null? "" : ReRs.getString(56));
				qita3_amount.setText(ReRs.getString(57)==null? "" : ReRs.getString(57));
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		
		
		
		private void xiugai(){		
			try {
				int op = JOptionPane.showConfirmDialog(this, "确认为指令单号为：" +ReRs.getString(2) + " 修改报告编号为："+ReRs.getString(3)+" 的报告量？", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(op == JOptionPane.CANCEL_OPTION || op == JOptionPane.CLOSED_OPTION ) return ;
				String data[] = new String[42];
				//0处理x光/张
				data[0] = x_zhang.getText().trim();
				//1处理X光/次
				data[1] = x_ci.getText().trim();
				//2处理y/张
				data[2] = y_zhang.getText().trim();
				//3处理y/次
				data[3] = y_ci.getText().trim();
				//4处理超声/米
				data[4] = chaosheng_mi.getText().trim();
				//5处理超声/道
				data[5] = chaosheng_dao.getText().trim();
				//6处理超声/次	
				data[6] = chaosheng_ci.getText().trim();
				//7处理磁粉/米
				data[7] = cifen_mi.getText().trim();
				//8处理磁粉/道
				data[8] = cifen_dao.getText().trim();
				//9处理磁粉/次	
				data[9] = cifen_ci.getText().trim();		
				//10处理渗透/米
				data[10] = shentou_mi.getText().trim();
				//11处理渗透/道
				data[11] = shentou_dao.getText().trim();
				//12处理渗透/次	
				data[12] = shentou_ci.getText().trim();	
				//13处理测厚/点
				data[13] = cehou_dian.getText().trim();
				//14处理测厚/次
				data[14] = cehou_ci.getText().trim();
				//15处理表面配合/工日
				data[15] = biaopei_ri.getText().trim();
				//16处理全定量/点
				data[16] = guangpuquan_dian.getText().trim();
				//17处理全定量/个元素
				data[17] = guangpuquan_ge.getText().trim();
				//18处理全定量/次
				data[18] = guangpuquan_ci.getText().trim();
				//19处理半定量/点
				data[19] = guangpuban_dian.getText().trim();
				//20处理半定量/个元素
				data[20] = guangpuban_ge.getText().trim();
				//21处理半定量/次
				data[21] = guangpuban_ci.getText().trim();
				//22处理硬度/点
				data[22] = yingdu_dian.getText().trim();
				//23处理硬度/次
				data[23] = yingdu_ci.getText().trim();
				//24处理打磨/米
				data[24] = damo_mi.getText().trim();
				//25处理打磨/次
				data[25] = damo_ci.getText().trim();
				//26处理tofd/米
				data[26] = tofd_mi.getText().trim();
				//27处理tofd/次
				data[27] = tofd_ci.getText().trim();
				//28处理拉伸/件
				data[28] = lashen_jian.getText().trim();
				//29处理拉伸/次
				data[29] = lashen_ci.getText().trim();
				//30处理弯曲/件
				data[30] = wanqu_jian.getText().trim();
				//31处理弯曲/次
				data[31] = wanqu_ci.getText().trim();
				//32处理冲击/件
				data[32] = chongji_jian.getText().trim();
				//33处理冲击/次
				data[33] = chongji_ci.getText().trim();
				//34处理铁素体/点
				data[34] = tie_dian.getText().trim();
				//35处理铁素体/次
				data[35] = tie_ci.getText().trim();
				//36处理金相/件
				data[36] = jinxiang_jian.getText().trim();
				//37处理金相/次
				data[37] = jinxiang_ci.getText().trim();
				//38处理点口、特检院配合、技术指导 数量
				data[38] = diankou_amount.getText().trim();
				//39处理其他项目1数量
				data[39] = qita1_amount.getText().trim();
				//40处理其他项目2数量
				data[40] = qita2_amount.getText().trim();
				//41处理其他项目3数量
				data[41] = qita3_amount.getText().trim();
				
				for(int i=0;i<42;i++){
					if(data[i].equals("") ) ReRs.updateNull(i+16);else ReRs.updateString(i+16,data[i]);
				}
				
				ReRs.updateRow();
				System.out.println("update over");
				JOptionPane.showMessageDialog(null, "修改成功", "成功", JOptionPane.PLAIN_MESSAGE);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		private void quxiao(){
			dispose(); 
		}
		
	}
		
}