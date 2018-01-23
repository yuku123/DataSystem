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

import javax.swing.ButtonGroup;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.*;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class InNP extends JPanel {
	private String group;//全局变量的代值
	private SQLManager sm;//获取数据库管理

	//顶部容器
	private JPanel top = new JPanel();
	private MyComboBox_TextFieldPane cb0;  //指令单号下拉选择框
	private JComboBox<String> cb1= new JComboBox<>(); //委托单位
	private JComboBox<String> cb2 = new JComboBox<>();//工程名称
	
    public static final Pattern pattern = Pattern.compile("(\\d+)|(\\d+\\.\\d+)");
    public static final Pattern pattern2 = Pattern.compile("\\d\\d\\d\\d\\d\\d\\d\\d");

    //下部容器
    private JTextField x_pzhang;
    private JTextField y_pzhang;
    private JTextField chaosheng_pmi;
    private JTextField chaosheng_pdao;
    private JTextField cifen_pmi;
    private JTextField cifen_pdao;
    private JTextField shentou_pmi;
    private JTextField shentou_pdao;
    private JTextField cehou_pdian;
    private JTextField biaopei_pri;
    private JTextField guangpuquan_pdian;
    private JTextField guangpuquan_pge;
    private JTextField guangpuban_pdian;
    private JTextField guangpuban_pge;
    private JTextField yingdu_pdian;
    private JTextField damo_pmi;
    private JTextField tofd_pmi;
    private JTextField lashen_pjian;
    private JTextField wanqu_pjian;
    private JTextField chongji_pjian;
    private JTextField tie_pdian;
    private JTextField jinxiang_pjian;
    private JTextField diankou_pamount;
    private JTextField qita1_pamount;
    private JTextField qita2_pamount;
    private JTextField qita3_pamount;
    private JTextArea duinei_beizhu ;
 
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("unchecked")
	public InNP(SQLManager s, String group) {
		this.group=group;
		this.sm=s;
		//总布局BorderLayout
		setLayout(new BorderLayout(0, 0));
		
		
		//TODO 顶部容器
		top.setBorder(new TitledBorder(null, "\u9009\u62E9\u6307\u4EE4\u5355\u53F7\u6216\u8005\u5DE5\u7A0B\u540D\u79F0\u548C\u59D4\u6258\u5355\u4F4D", TitledBorder.LEFT, TitledBorder.TOP, null, Color.RED));
		add(top, BorderLayout.NORTH);
		GridBagLayout gbl_top = new GridBagLayout();
		gbl_top.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_top.rowHeights = new int[]{0, 0};
		gbl_top.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_top.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		top.setLayout(gbl_top);
		
		//指令单号label
		JLabel label0 = new JLabel("\u6307\u4EE4\u5355\u53F7");
		label0.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_label0 = new GridBagConstraints();
		gbc_label0.insets = new Insets(0, 0, 0, 5);
		gbc_label0.gridx = 0;
		gbc_label0.gridy = 0;
		top.add(label0, gbc_label0);
		
		//委托单位label
		JLabel label1 = new JLabel("\u59D4\u6258\u5355\u4F4D");
		GridBagConstraints gbc_label1 = new GridBagConstraints();
		gbc_label1.insets = new Insets(0, 0, 0, 5);
		gbc_label1.gridx = 2;
		gbc_label1.gridy = 0;
		top.add(label1, gbc_label1);
		
		//项目名称label
		JLabel label2 = new JLabel("\u5DE5\u7A0B\u540D\u79F0");
		GridBagConstraints gbc_label2 = new GridBagConstraints();
		gbc_label2.insets = new Insets(0, 0, 0, 5);
		gbc_label2.gridx = 4;
		gbc_label2.gridy = 0;
		top.add(label2, gbc_label2);
		
		//空白label
		JLabel lblNewLabel_2 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.weighty = 5.0;
		gbc_lblNewLabel_2.weightx = 5.0;
		gbc_lblNewLabel_2.ipady = 1;
		gbc_lblNewLabel_2.ipadx = 1;
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.gridx = 6;
		gbc_lblNewLabel_2.gridy = 0;
		top.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		//指令单号
		cb0 = new MyComboBox_TextFieldPane((ArrayList<String>)sm.search(0, 1),10);
		GridBagConstraints gbc_cb0 = new GridBagConstraints();
		gbc_cb0.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb0.weighty = 10.0;
		gbc_cb0.weightx = 10.0;
		gbc_cb0.insets = new Insets(0, 0, 0, 5);
		gbc_cb0.gridx = 1;
		gbc_cb0.gridy = 0;
		top.add(cb0, gbc_cb0);
		cb0.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e) {
				// TODO 失去焦点向委托单位和工程名称里查询插入
				cb1.setModel( (DefaultComboBoxModel<String>)sm.search(3, 0));
				cb1.setSelectedItem((String)sm.search(1,2,new String[]{cb0.getText()}));
				cb2.setModel( (DefaultComboBoxModel<String>)sm.search(4, 0));
				cb2.setSelectedItem((String)sm.search(2,2,new String[]{cb0.getText()}));
			}

		});
		
		//委托单位
		cb1.setModel( (DefaultComboBoxModel<String>)sm.search(3, 0));
		cb1.setMaximumRowCount(15);
		cb1.setSelectedIndex(-1);
		GridBagConstraints gbc_cb1 = new GridBagConstraints();
		gbc_cb1.weighty = 10.0;
		gbc_cb1.weightx = 10.0;
		gbc_cb1.insets = new Insets(0, 0, 0, 5);
		gbc_cb1.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb1.gridx = 3;
		gbc_cb1.gridy = 0;
		top.add(cb1, gbc_cb1);
		cb1.addFocusListener(new FocusAdapter(){
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
		
		
		//项目名称
		cb2.setModel( (DefaultComboBoxModel<String>)sm.search(4, 0));
		cb2.setMaximumRowCount(15);
		cb2.setSelectedIndex(-1);
		GridBagConstraints gbc_cb2 = new GridBagConstraints();
		gbc_cb2.weighty = 10.0;
		gbc_cb2.weightx = 10.0;
		gbc_cb2.insets = new Insets(0, 0, 0, 5);
		gbc_cb2.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb2.gridx = 5;
		gbc_cb2.gridy = 0;
		top.add(cb2, gbc_cb2);
		cb2.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e) {
				// 筛选指令单号
				if((String)cb1.getSelectedItem() != "" && cb1.getSelectedItem()!=""){
				cb0.setText((String)sm.search(7,2,new String[]{(String)cb1.getSelectedItem(),(String)cb2.getSelectedItem()}));
				}			
	     	}		
		});		
		
		//中间可滚动容器
		JScrollPane middle = new JScrollPane();
		add(middle, BorderLayout.CENTER);	
			
		//中间容器
		JPanel mid_pane = new JPanel();
		mid_pane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u5DE5\u8D44\u7ED3\u7B97\u5355\u4EF7", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		middle.setViewportView(mid_pane);
		GridBagLayout gbl_mid_pane = new GridBagLayout();
		gbl_mid_pane.columnWidths = new int[]{85, 27, 0, 0, 0, 0, 0, 0, 0};
		gbl_mid_pane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mid_pane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_mid_pane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		mid_pane.setLayout(gbl_mid_pane);
		
		//x射线按张算的输入框
		JLabel x_label = new JLabel("X\u5149\uFF1A\u6309\u5F20");
		GridBagConstraints gbc_x_label = new GridBagConstraints();
		gbc_x_label.anchor = GridBagConstraints.EAST;
		gbc_x_label.insets = new Insets(0, 0, 5, 5);
		gbc_x_label.gridx = 0;
		gbc_x_label.gridy = 1;
		mid_pane.add(x_label, gbc_x_label);
		
		
		x_pzhang = new JTextField();
		GridBagConstraints gbc_x_pzhang = new GridBagConstraints();
		gbc_x_pzhang.anchor = GridBagConstraints.EAST;
		gbc_x_pzhang.insets = new Insets(0, 0, 5, 5);
		gbc_x_pzhang.gridx = 1;
		gbc_x_pzhang.gridy = 1;
		mid_pane.add(x_pzhang, gbc_x_pzhang);
		x_pzhang.setColumns(6);

		
		JLabel x_pzhang_label = new JLabel("\u5143");
		GridBagConstraints gbc_x_pzhang_label = new GridBagConstraints();
		gbc_x_pzhang_label.anchor = GridBagConstraints.WEST;
		gbc_x_pzhang_label.insets = new Insets(0, 0, 5, 5);
		gbc_x_pzhang_label.gridx = 2;
		gbc_x_pzhang_label.gridy = 1;
		mid_pane.add(x_pzhang_label, gbc_x_pzhang_label);
		

		
		JLabel y_label = new JLabel("\u03B3\u5C04\u7EBF\uFF1A\u6309\u5F20");
		GridBagConstraints gbc_y_label = new GridBagConstraints();
		gbc_y_label.anchor = GridBagConstraints.EAST;
		gbc_y_label.insets = new Insets(0, 0, 5, 5);
		gbc_y_label.gridx = 0;
		gbc_y_label.gridy = 2;
		mid_pane.add(y_label, gbc_y_label);
		
		//伽马射线按张算
		y_pzhang = new JTextField();
		GridBagConstraints gbc_y_pzhang = new GridBagConstraints();
		gbc_y_pzhang.anchor = GridBagConstraints.EAST;
		gbc_y_pzhang.insets = new Insets(0, 0, 5, 5);
		gbc_y_pzhang.gridx = 1;
		gbc_y_pzhang.gridy = 2;
		mid_pane.add(y_pzhang, gbc_y_pzhang);
		y_pzhang.setColumns(6);

		
		JLabel y_pzhang_label = new JLabel("\u5143");
		GridBagConstraints gbc_y_pzhang_label = new GridBagConstraints();
		gbc_y_pzhang_label.anchor = GridBagConstraints.WEST;
		gbc_y_pzhang_label.insets = new Insets(0, 0, 5, 5);
		gbc_y_pzhang_label.gridx = 2;
		gbc_y_pzhang_label.gridy = 2;
		mid_pane.add(y_pzhang_label, gbc_y_pzhang_label);
		
		JLabel chaosheng_pdao_label1 = new JLabel("\u6309\u9053");
		GridBagConstraints gbc_chaosheng_pdao_label1 = new GridBagConstraints();
		gbc_chaosheng_pdao_label1.insets = new Insets(0, 0, 5, 5);
		gbc_chaosheng_pdao_label1.anchor = GridBagConstraints.EAST;
		gbc_chaosheng_pdao_label1.gridx = 3;
		gbc_chaosheng_pdao_label1.gridy = 3;
		mid_pane.add(chaosheng_pdao_label1, gbc_chaosheng_pdao_label1);
		
		JLabel cifen_pdao_label1 = new JLabel("\u6309\u9053");
		GridBagConstraints gbc_cifen_pdao_label1 = new GridBagConstraints();
		gbc_cifen_pdao_label1.insets = new Insets(0, 0, 5, 5);
		gbc_cifen_pdao_label1.anchor = GridBagConstraints.EAST;
		gbc_cifen_pdao_label1.gridx = 3;
		gbc_cifen_pdao_label1.gridy = 4;
		mid_pane.add(cifen_pdao_label1, gbc_cifen_pdao_label1);
		
		JLabel shentou_pdao_label1 = new JLabel("\u6309\u9053");
		GridBagConstraints gbc_shentou_pdao_label1 = new GridBagConstraints();
		gbc_shentou_pdao_label1.insets = new Insets(0, 0, 5, 5);
		gbc_shentou_pdao_label1.anchor = GridBagConstraints.EAST;
		gbc_shentou_pdao_label1.gridx = 3;
		gbc_shentou_pdao_label1.gridy = 5;
		mid_pane.add(shentou_pdao_label1, gbc_shentou_pdao_label1);
		
		JLabel guangpuquan_pge_label1 = new JLabel("\u6BCF\u4E2A\u5143\u7D20");
		GridBagConstraints gbc_guangpuquan_pge_label1 = new GridBagConstraints();
		gbc_guangpuquan_pge_label1.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuquan_pge_label1.anchor = GridBagConstraints.EAST;
		gbc_guangpuquan_pge_label1.gridx = 3;
		gbc_guangpuquan_pge_label1.gridy = 8;
		mid_pane.add(guangpuquan_pge_label1, gbc_guangpuquan_pge_label1);
		

		
		
		JLabel guangpuquan_pge_label = new JLabel("\u5143");
		GridBagConstraints gbc_guangpuquan_pge_label = new GridBagConstraints();
		gbc_guangpuquan_pge_label.anchor = GridBagConstraints.WEST;
		gbc_guangpuquan_pge_label.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuquan_pge_label.gridx = 5;
		gbc_guangpuquan_pge_label.gridy = 8;
		mid_pane.add(guangpuquan_pge_label, gbc_guangpuquan_pge_label);
		
		
		JLabel biaopei_pri_label = new JLabel("\u5143");
		GridBagConstraints gbc_biaopei_pri_label = new GridBagConstraints();
		gbc_biaopei_pri_label.anchor = GridBagConstraints.WEST;
		gbc_biaopei_pri_label.insets = new Insets(0, 0, 5, 5);
		gbc_biaopei_pri_label.gridx = 2;
		gbc_biaopei_pri_label.gridy = 7;
		mid_pane.add(biaopei_pri_label, gbc_biaopei_pri_label);
		
		JLabel cehou_pdian_label = new JLabel("\u5143");
		GridBagConstraints gbc_cehou_pdian_label = new GridBagConstraints();
		gbc_cehou_pdian_label.anchor = GridBagConstraints.WEST;
		gbc_cehou_pdian_label.insets = new Insets(0, 0, 5, 5);
		gbc_cehou_pdian_label.gridx = 2;
		gbc_cehou_pdian_label.gridy = 6;
		mid_pane.add(cehou_pdian_label, gbc_cehou_pdian_label);
		
		JLabel shentou_pdao_label = new JLabel("\u5143");
		GridBagConstraints gbc_shentou_pdao_label = new GridBagConstraints();
		gbc_shentou_pdao_label.anchor = GridBagConstraints.WEST;
		gbc_shentou_pdao_label.insets = new Insets(0, 0, 5, 5);
		gbc_shentou_pdao_label.gridx = 5;
		gbc_shentou_pdao_label.gridy = 5;
		mid_pane.add(shentou_pdao_label, gbc_shentou_pdao_label);
		
		JLabel shentou_pmi_label = new JLabel("\u5143");
		GridBagConstraints gbc_shentou_pmi_label = new GridBagConstraints();
		gbc_shentou_pmi_label.anchor = GridBagConstraints.WEST;
		gbc_shentou_pmi_label.insets = new Insets(0, 0, 5, 5);
		gbc_shentou_pmi_label.gridx = 2;
		gbc_shentou_pmi_label.gridy = 5;
		mid_pane.add(shentou_pmi_label, gbc_shentou_pmi_label);
		
		JLabel cifen_pdao_label = new JLabel("\u5143");
		GridBagConstraints gbc_cifen_pdao_label = new GridBagConstraints();
		gbc_cifen_pdao_label.anchor = GridBagConstraints.WEST;
		gbc_cifen_pdao_label.insets = new Insets(0, 0, 5, 5);
		gbc_cifen_pdao_label.gridx = 5;
		gbc_cifen_pdao_label.gridy = 4;
		mid_pane.add(cifen_pdao_label, gbc_cifen_pdao_label);
		
		JLabel cifen_pmi_label = new JLabel("\u5143");
		GridBagConstraints gbc_cifen_pmi_label = new GridBagConstraints();
		gbc_cifen_pmi_label.anchor = GridBagConstraints.WEST;
		gbc_cifen_pmi_label.insets = new Insets(0, 0, 5, 5);
		gbc_cifen_pmi_label.gridx = 2;
		gbc_cifen_pmi_label.gridy = 4;
		mid_pane.add(cifen_pmi_label, gbc_cifen_pmi_label);
		
		JLabel chaosheng_label = new JLabel("\u8D85\u58F0\uFF1A\u6309\u7C73");
		GridBagConstraints gbc_chaosheng_label = new GridBagConstraints();
		gbc_chaosheng_label.anchor = GridBagConstraints.EAST;
		gbc_chaosheng_label.insets = new Insets(0, 0, 5, 5);
		gbc_chaosheng_label.gridx = 0;
		gbc_chaosheng_label.gridy = 3;
		mid_pane.add(chaosheng_label, gbc_chaosheng_label);
		
		chaosheng_pmi = new JTextField();
		GridBagConstraints gbc_chaosheng_pmi = new GridBagConstraints();
		gbc_chaosheng_pmi.anchor = GridBagConstraints.EAST;
		gbc_chaosheng_pmi.insets = new Insets(0, 0, 5, 5);
		gbc_chaosheng_pmi.gridx = 1;
		gbc_chaosheng_pmi.gridy = 3;
		mid_pane.add(chaosheng_pmi, gbc_chaosheng_pmi);
		chaosheng_pmi.setColumns(6);
		
		JLabel chaosheng_pmi_label = new JLabel("\u5143");
		GridBagConstraints gbc_chaosheng_pmi_label = new GridBagConstraints();
		gbc_chaosheng_pmi_label.anchor = GridBagConstraints.WEST;
		gbc_chaosheng_pmi_label.insets = new Insets(0, 0, 5, 5);
		gbc_chaosheng_pmi_label.gridx = 2;
		gbc_chaosheng_pmi_label.gridy = 3;
		mid_pane.add(chaosheng_pmi_label, gbc_chaosheng_pmi_label);
		
		chaosheng_pdao = new JTextField();
		GridBagConstraints gbc_chaosheng_pdao = new GridBagConstraints();
		gbc_chaosheng_pdao.anchor = GridBagConstraints.EAST;
		gbc_chaosheng_pdao.insets = new Insets(0, 0, 5, 5);
		gbc_chaosheng_pdao.gridx = 4;
		gbc_chaosheng_pdao.gridy = 3;
		mid_pane.add(chaosheng_pdao, gbc_chaosheng_pdao);
		chaosheng_pdao.setColumns(6);
		
		JLabel chaosheng_pdao_label = new JLabel("\u5143");
		GridBagConstraints gbc_chaosheng_pdao_label = new GridBagConstraints();
		gbc_chaosheng_pdao_label.anchor = GridBagConstraints.WEST;
		gbc_chaosheng_pdao_label.insets = new Insets(0, 0, 5, 5);
		gbc_chaosheng_pdao_label.gridx = 5;
		gbc_chaosheng_pdao_label.gridy = 3;
		mid_pane.add(chaosheng_pdao_label, gbc_chaosheng_pdao_label);
		
		JLabel cifen_label = new JLabel("\u78C1\u7C89\uFF1A\u6309\u7C73");
		GridBagConstraints gbc_cifen_label = new GridBagConstraints();
		gbc_cifen_label.anchor = GridBagConstraints.EAST;
		gbc_cifen_label.insets = new Insets(0, 0, 5, 5);
		gbc_cifen_label.gridx = 0;
		gbc_cifen_label.gridy = 4;
		mid_pane.add(cifen_label, gbc_cifen_label);
		
		cifen_pmi = new JTextField();
		GridBagConstraints gbc_cifen_pmi = new GridBagConstraints();
		gbc_cifen_pmi.anchor = GridBagConstraints.EAST;
		gbc_cifen_pmi.insets = new Insets(0, 0, 5, 5);
		gbc_cifen_pmi.gridx = 1;
		gbc_cifen_pmi.gridy = 4;
		mid_pane.add(cifen_pmi, gbc_cifen_pmi);
		cifen_pmi.setColumns(6);
		
		cifen_pdao = new JTextField();
		GridBagConstraints gbc_cifen_pdao = new GridBagConstraints();
		gbc_cifen_pdao.anchor = GridBagConstraints.EAST;
		gbc_cifen_pdao.insets = new Insets(0, 0, 5, 5);
		gbc_cifen_pdao.gridx = 4;
		gbc_cifen_pdao.gridy = 4;
		mid_pane.add(cifen_pdao, gbc_cifen_pdao);
		cifen_pdao.setColumns(6);
		
		JLabel shentou_label = new JLabel("\u6E17\u900F\uFF1A\u6309\u7C73");
		GridBagConstraints gbc_shentou_label = new GridBagConstraints();
		gbc_shentou_label.anchor = GridBagConstraints.EAST;
		gbc_shentou_label.insets = new Insets(0, 0, 5, 5);
		gbc_shentou_label.gridx = 0;
		gbc_shentou_label.gridy = 5;
		mid_pane.add(shentou_label, gbc_shentou_label);
		
		shentou_pmi = new JTextField();
		GridBagConstraints gbc_shentou_pmi = new GridBagConstraints();
		gbc_shentou_pmi.anchor = GridBagConstraints.EAST;
		gbc_shentou_pmi.insets = new Insets(0, 0, 5, 5);
		gbc_shentou_pmi.gridx = 1;
		gbc_shentou_pmi.gridy = 5;
		mid_pane.add(shentou_pmi, gbc_shentou_pmi);
		shentou_pmi.setColumns(6);
		
		shentou_pdao = new JTextField();
		GridBagConstraints gbc_shentou_pdao = new GridBagConstraints();
		gbc_shentou_pdao.anchor = GridBagConstraints.EAST;
		gbc_shentou_pdao.insets = new Insets(0, 0, 5, 5);
		gbc_shentou_pdao.gridx = 4;
		gbc_shentou_pdao.gridy = 5;
		mid_pane.add(shentou_pdao, gbc_shentou_pdao);
		shentou_pdao.setColumns(6);
		
		JLabel cehou = new JLabel("\u6D4B\u539A\uFF1A\u6309\u70B9");
		GridBagConstraints gbc_cehou = new GridBagConstraints();
		gbc_cehou.anchor = GridBagConstraints.EAST;
		gbc_cehou.insets = new Insets(0, 0, 5, 5);
		gbc_cehou.gridx = 0;
		gbc_cehou.gridy = 6;
		mid_pane.add(cehou, gbc_cehou);
		
		cehou_pdian = new JTextField();
		GridBagConstraints gbc_cehou_pdian = new GridBagConstraints();
		gbc_cehou_pdian.anchor = GridBagConstraints.EAST;
		gbc_cehou_pdian.insets = new Insets(0, 0, 5, 5);
		gbc_cehou_pdian.gridx = 1;
		gbc_cehou_pdian.gridy = 6;
		mid_pane.add(cehou_pdian, gbc_cehou_pdian);
		cehou_pdian.setColumns(6);
		
		JLabel biaopei_label = new JLabel("\u8868\u9762\u914D\u5408\u6BCF\u5DE5\u65E5");
		GridBagConstraints gbc_biaopei_label = new GridBagConstraints();
		gbc_biaopei_label.anchor = GridBagConstraints.EAST;
		gbc_biaopei_label.insets = new Insets(0, 0, 5, 5);
		gbc_biaopei_label.gridx = 0;
		gbc_biaopei_label.gridy = 7;
		mid_pane.add(biaopei_label, gbc_biaopei_label);
		
		biaopei_pri = new JTextField();
		GridBagConstraints gbc_biaopei_pri = new GridBagConstraints();
		gbc_biaopei_pri.anchor = GridBagConstraints.EAST;
		gbc_biaopei_pri.insets = new Insets(0, 0, 5, 5);
		gbc_biaopei_pri.gridx = 1;
		gbc_biaopei_pri.gridy = 7;
		mid_pane.add(biaopei_pri, gbc_biaopei_pri);
		biaopei_pri.setColumns(6);
		
		JLabel guangpuquan_label = new JLabel("\u5168\u5B9A\u91CF\uFF1A\u6309\u70B9");
		GridBagConstraints gbc_guangpuquan_label = new GridBagConstraints();
		gbc_guangpuquan_label.anchor = GridBagConstraints.EAST;
		gbc_guangpuquan_label.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuquan_label.gridx = 0;
		gbc_guangpuquan_label.gridy = 8;
		mid_pane.add(guangpuquan_label, gbc_guangpuquan_label);
		
		guangpuquan_pdian = new JTextField();
		GridBagConstraints gbc_guangpuquan_pdian = new GridBagConstraints();
		gbc_guangpuquan_pdian.anchor = GridBagConstraints.EAST;
		gbc_guangpuquan_pdian.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuquan_pdian.gridx = 1;
		gbc_guangpuquan_pdian.gridy = 8;
		mid_pane.add(guangpuquan_pdian, gbc_guangpuquan_pdian);
		guangpuquan_pdian.setColumns(6);
		
		JLabel guangpuquan_pdian_label = new JLabel("\u5143");
		GridBagConstraints gbc_guangpuquan_pdian_label = new GridBagConstraints();
		gbc_guangpuquan_pdian_label.anchor = GridBagConstraints.WEST;
		gbc_guangpuquan_pdian_label.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuquan_pdian_label.gridx = 2;
		gbc_guangpuquan_pdian_label.gridy = 8;
		mid_pane.add(guangpuquan_pdian_label, gbc_guangpuquan_pdian_label);
		
		guangpuquan_pge = new JTextField();
		GridBagConstraints gbc_guangpuquan_pge = new GridBagConstraints();
		gbc_guangpuquan_pge.anchor = GridBagConstraints.EAST;
		gbc_guangpuquan_pge.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuquan_pge.gridx = 4;
		gbc_guangpuquan_pge.gridy = 8;
		mid_pane.add(guangpuquan_pge, gbc_guangpuquan_pge);
		guangpuquan_pge.setColumns(6);
		

		
		JLabel guangpuban_label = new JLabel("\u534A\u5B9A\u91CF\uFF1A\u6309\u70B9");
		GridBagConstraints gbc_guangpuban_label = new GridBagConstraints();
		gbc_guangpuban_label.anchor = GridBagConstraints.EAST;
		gbc_guangpuban_label.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuban_label.gridx = 0;
		gbc_guangpuban_label.gridy = 9;
		mid_pane.add(guangpuban_label, gbc_guangpuban_label);
		
		guangpuban_pdian = new JTextField();
		GridBagConstraints gbc_guangpuban_pdian = new GridBagConstraints();
		gbc_guangpuban_pdian.anchor = GridBagConstraints.EAST;
		gbc_guangpuban_pdian.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuban_pdian.gridx = 1;
		gbc_guangpuban_pdian.gridy = 9;
		mid_pane.add(guangpuban_pdian, gbc_guangpuban_pdian);
		guangpuban_pdian.setColumns(6);
		
		JLabel guangpuban_pdian_label = new JLabel("\u5143");
		GridBagConstraints gbc_guangpuban_pdian_label = new GridBagConstraints();
		gbc_guangpuban_pdian_label.anchor = GridBagConstraints.WEST;
		gbc_guangpuban_pdian_label.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuban_pdian_label.gridx = 2;
		gbc_guangpuban_pdian_label.gridy = 9;
		mid_pane.add(guangpuban_pdian_label, gbc_guangpuban_pdian_label);
		
		JLabel guangpuban_pge_label1 = new JLabel("\u6BCF\u4E2A\u5143\u7D20");
		GridBagConstraints gbc_guangpuban_pge_label1 = new GridBagConstraints();
		gbc_guangpuban_pge_label1.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuban_pge_label1.anchor = GridBagConstraints.EAST;
		gbc_guangpuban_pge_label1.gridx = 3;
		gbc_guangpuban_pge_label1.gridy = 9;
		mid_pane.add(guangpuban_pge_label1, gbc_guangpuban_pge_label1);
		
		guangpuban_pge = new JTextField();
		GridBagConstraints gbc_guangpuban_pge = new GridBagConstraints();
		gbc_guangpuban_pge.anchor = GridBagConstraints.EAST;
		gbc_guangpuban_pge.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuban_pge.gridx = 4;
		gbc_guangpuban_pge.gridy = 9;
		mid_pane.add(guangpuban_pge, gbc_guangpuban_pge);
		guangpuban_pge.setColumns(6);
		
		JLabel guangpuban_pge_label = new JLabel("\u5143");
		GridBagConstraints gbc_guangpuban_pge_label = new GridBagConstraints();
		gbc_guangpuban_pge_label.anchor = GridBagConstraints.WEST;
		gbc_guangpuban_pge_label.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuban_pge_label.gridx = 5;
		gbc_guangpuban_pge_label.gridy = 9;
		mid_pane.add(guangpuban_pge_label, gbc_guangpuban_pge_label);
		

		
		JLabel yingdu_label = new JLabel("\u786C\u5EA6\uFF1A\u6309\u70B9");
		GridBagConstraints gbc_yingdu_label = new GridBagConstraints();
		gbc_yingdu_label.anchor = GridBagConstraints.EAST;
		gbc_yingdu_label.insets = new Insets(0, 0, 5, 5);
		gbc_yingdu_label.gridx = 0;
		gbc_yingdu_label.gridy = 10;
		mid_pane.add(yingdu_label, gbc_yingdu_label);
		
		yingdu_pdian = new JTextField();
		GridBagConstraints gbc_yingdu_pdian = new GridBagConstraints();
		gbc_yingdu_pdian.anchor = GridBagConstraints.EAST;
		gbc_yingdu_pdian.insets = new Insets(0, 0, 5, 5);
		gbc_yingdu_pdian.gridx = 1;
		gbc_yingdu_pdian.gridy = 10;
		mid_pane.add(yingdu_pdian, gbc_yingdu_pdian);
		yingdu_pdian.setColumns(6);
		
		JLabel yingdu_pdian_label = new JLabel("\u5143");
		GridBagConstraints gbc_yingdu_pdian_label = new GridBagConstraints();
		gbc_yingdu_pdian_label.anchor = GridBagConstraints.WEST;
		gbc_yingdu_pdian_label.insets = new Insets(0, 0, 5, 5);
		gbc_yingdu_pdian_label.gridx = 2;
		gbc_yingdu_pdian_label.gridy = 10;
		mid_pane.add(yingdu_pdian_label, gbc_yingdu_pdian_label);
		

		
		JLabel damo_label = new JLabel("\u6253\u78E8\uFF1A\u6309\u7C73");
		GridBagConstraints gbc_damo_label = new GridBagConstraints();
		gbc_damo_label.anchor = GridBagConstraints.EAST;
		gbc_damo_label.insets = new Insets(0, 0, 5, 5);
		gbc_damo_label.gridx = 0;
		gbc_damo_label.gridy = 11;
		mid_pane.add(damo_label, gbc_damo_label);
		
		damo_pmi = new JTextField();
		GridBagConstraints gbc_damo_pmi = new GridBagConstraints();
		gbc_damo_pmi.anchor = GridBagConstraints.EAST;
		gbc_damo_pmi.insets = new Insets(0, 0, 5, 5);
		gbc_damo_pmi.gridx = 1;
		gbc_damo_pmi.gridy = 11;
		mid_pane.add(damo_pmi, gbc_damo_pmi);
		damo_pmi.setColumns(6);
		
		JLabel damo_pmi_label = new JLabel("\u5143");
		GridBagConstraints gbc_damo_pmi_label = new GridBagConstraints();
		gbc_damo_pmi_label.anchor = GridBagConstraints.WEST;
		gbc_damo_pmi_label.insets = new Insets(0, 0, 5, 5);
		gbc_damo_pmi_label.gridx = 2;
		gbc_damo_pmi_label.gridy = 11;
		mid_pane.add(damo_pmi_label, gbc_damo_pmi_label);
		
		JLabel label = new JLabel("\u5907\u6CE8");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridheight = 5;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 5;
		gbc_label.gridy = 11;
		mid_pane.add(label, gbc_label);
		
		JPanel duinei_beizhu_panel = new JPanel();
		duinei_beizhu_panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_duinei_beizhu_panel = new GridBagConstraints();
		gbc_duinei_beizhu_panel.gridheight = 8;
		gbc_duinei_beizhu_panel.insets = new Insets(0, 0, 5, 5);
		gbc_duinei_beizhu_panel.fill = GridBagConstraints.BOTH;
		gbc_duinei_beizhu_panel.gridx = 6;
		gbc_duinei_beizhu_panel.gridy = 11;
		mid_pane.add(duinei_beizhu_panel, gbc_duinei_beizhu_panel);
		duinei_beizhu_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		duinei_beizhu = new JTextArea();
		duinei_beizhu.setRows(8);
		duinei_beizhu.setLineWrap(true);
		duinei_beizhu.setColumns(20);
		duinei_beizhu_panel.add(duinei_beizhu);
		

		
		JLabel tofd_label = new JLabel("TOFD\uFF1A\u6309\u7C73");
		GridBagConstraints gbc_tofd_label = new GridBagConstraints();
		gbc_tofd_label.anchor = GridBagConstraints.EAST;
		gbc_tofd_label.insets = new Insets(0, 0, 5, 5);
		gbc_tofd_label.gridx = 0;
		gbc_tofd_label.gridy = 12;
		mid_pane.add(tofd_label, gbc_tofd_label);
		
		tofd_pmi = new JTextField();
		GridBagConstraints gbc_tofd_pmi = new GridBagConstraints();
		gbc_tofd_pmi.anchor = GridBagConstraints.EAST;
		gbc_tofd_pmi.insets = new Insets(0, 0, 5, 5);
		gbc_tofd_pmi.gridx = 1;
		gbc_tofd_pmi.gridy = 12;
		mid_pane.add(tofd_pmi, gbc_tofd_pmi);
		tofd_pmi.setColumns(6);
		
		JLabel tofd_pmi_label = new JLabel("\u5143");
		GridBagConstraints gbc_tofd_pmi_label = new GridBagConstraints();
		gbc_tofd_pmi_label.anchor = GridBagConstraints.WEST;
		gbc_tofd_pmi_label.insets = new Insets(0, 0, 5, 5);
		gbc_tofd_pmi_label.gridx = 2;
		gbc_tofd_pmi_label.gridy = 12;
		mid_pane.add(tofd_pmi_label, gbc_tofd_pmi_label);
		

		
		JLabel lashen_label = new JLabel("\u62C9\u4F38\uFF1A\u6309\u4EF6");
		GridBagConstraints gbc_lashen_label = new GridBagConstraints();
		gbc_lashen_label.anchor = GridBagConstraints.EAST;
		gbc_lashen_label.insets = new Insets(0, 0, 5, 5);
		gbc_lashen_label.gridx = 0;
		gbc_lashen_label.gridy = 13;
		mid_pane.add(lashen_label, gbc_lashen_label);
		
		lashen_pjian = new JTextField();
		GridBagConstraints gbc_lashen_pjian = new GridBagConstraints();
		gbc_lashen_pjian.anchor = GridBagConstraints.EAST;
		gbc_lashen_pjian.insets = new Insets(0, 0, 5, 5);
		gbc_lashen_pjian.gridx = 1;
		gbc_lashen_pjian.gridy = 13;
		mid_pane.add(lashen_pjian, gbc_lashen_pjian);
		lashen_pjian.setColumns(6);
		
		JLabel lashen_pjian_label = new JLabel("\u5143");
		GridBagConstraints gbc_lashen_pjian_label = new GridBagConstraints();
		gbc_lashen_pjian_label.anchor = GridBagConstraints.WEST;
		gbc_lashen_pjian_label.insets = new Insets(0, 0, 5, 5);
		gbc_lashen_pjian_label.gridx = 2;
		gbc_lashen_pjian_label.gridy = 13;
		mid_pane.add(lashen_pjian_label, gbc_lashen_pjian_label);
		

		
		JLabel wanqu_label = new JLabel("\u5F2F\u66F2\uFF1A\u6309\u4EF6");
		GridBagConstraints gbc_wanqu_label = new GridBagConstraints();
		gbc_wanqu_label.anchor = GridBagConstraints.EAST;
		gbc_wanqu_label.insets = new Insets(0, 0, 5, 5);
		gbc_wanqu_label.gridx = 0;
		gbc_wanqu_label.gridy = 14;
		mid_pane.add(wanqu_label, gbc_wanqu_label);
		
		wanqu_pjian = new JTextField();
		GridBagConstraints gbc_wanqu_pjian = new GridBagConstraints();
		gbc_wanqu_pjian.anchor = GridBagConstraints.EAST;
		gbc_wanqu_pjian.insets = new Insets(0, 0, 5, 5);
		gbc_wanqu_pjian.gridx = 1;
		gbc_wanqu_pjian.gridy = 14;
		mid_pane.add(wanqu_pjian, gbc_wanqu_pjian);
		wanqu_pjian.setColumns(6);
		
		JLabel wanqu_pjian_label = new JLabel("\u5143");
		GridBagConstraints gbc_wanqu_pjian_label = new GridBagConstraints();
		gbc_wanqu_pjian_label.anchor = GridBagConstraints.WEST;
		gbc_wanqu_pjian_label.insets = new Insets(0, 0, 5, 5);
		gbc_wanqu_pjian_label.gridx = 2;
		gbc_wanqu_pjian_label.gridy = 14;
		mid_pane.add(wanqu_pjian_label, gbc_wanqu_pjian_label);
		

		
		JLabel chongji_label = new JLabel("\u51B2\u51FB\uFF1A\u6309\u4EF6");
		GridBagConstraints gbc_chongji_label = new GridBagConstraints();
		gbc_chongji_label.anchor = GridBagConstraints.EAST;
		gbc_chongji_label.insets = new Insets(0, 0, 5, 5);
		gbc_chongji_label.gridx = 0;
		gbc_chongji_label.gridy = 15;
		mid_pane.add(chongji_label, gbc_chongji_label);
		
		chongji_pjian = new JTextField();
		GridBagConstraints gbc_chongji_pjian = new GridBagConstraints();
		gbc_chongji_pjian.anchor = GridBagConstraints.EAST;
		gbc_chongji_pjian.insets = new Insets(0, 0, 5, 5);
		gbc_chongji_pjian.gridx = 1;
		gbc_chongji_pjian.gridy = 15;
		mid_pane.add(chongji_pjian, gbc_chongji_pjian);
		chongji_pjian.setColumns(6);
		
		JLabel chongji_pjian_label = new JLabel("\u5143");
		GridBagConstraints gbc_chongji_pjian_label = new GridBagConstraints();
		gbc_chongji_pjian_label.anchor = GridBagConstraints.WEST;
		gbc_chongji_pjian_label.insets = new Insets(0, 0, 5, 5);
		gbc_chongji_pjian_label.gridx = 2;
		gbc_chongji_pjian_label.gridy = 15;
		mid_pane.add(chongji_pjian_label, gbc_chongji_pjian_label);
		

		
		JLabel tie_label = new JLabel("\u94C1\u7D20\u4F53\uFF1A\u6309\u70B9");
		GridBagConstraints gbc_tie_label = new GridBagConstraints();
		gbc_tie_label.anchor = GridBagConstraints.EAST;
		gbc_tie_label.insets = new Insets(0, 0, 5, 5);
		gbc_tie_label.gridx = 0;
		gbc_tie_label.gridy = 16;
		mid_pane.add(tie_label, gbc_tie_label);
		
		tie_pdian = new JTextField();
		GridBagConstraints gbc_tie_pdian = new GridBagConstraints();
		gbc_tie_pdian.anchor = GridBagConstraints.EAST;
		gbc_tie_pdian.insets = new Insets(0, 0, 5, 5);
		gbc_tie_pdian.gridx = 1;
		gbc_tie_pdian.gridy = 16;
		mid_pane.add(tie_pdian, gbc_tie_pdian);
		tie_pdian.setColumns(6);
		
		JLabel tie_pdian_label = new JLabel("\u5143");
		GridBagConstraints gbc_tie_pdian_label = new GridBagConstraints();
		gbc_tie_pdian_label.anchor = GridBagConstraints.WEST;
		gbc_tie_pdian_label.insets = new Insets(0, 0, 5, 5);
		gbc_tie_pdian_label.gridx = 2;
		gbc_tie_pdian_label.gridy = 16;
		mid_pane.add(tie_pdian_label, gbc_tie_pdian_label);
		

		
		JLabel jinxiang_label = new JLabel("\u91D1\u76F8\uFF1A\u6309\u4EF6");
		GridBagConstraints gbc_jinxiang_label = new GridBagConstraints();
		gbc_jinxiang_label.anchor = GridBagConstraints.EAST;
		gbc_jinxiang_label.insets = new Insets(0, 0, 5, 5);
		gbc_jinxiang_label.gridx = 0;
		gbc_jinxiang_label.gridy = 17;
		mid_pane.add(jinxiang_label, gbc_jinxiang_label);
		
		jinxiang_pjian = new JTextField();
		GridBagConstraints gbc_jinxiang_pjian = new GridBagConstraints();
		gbc_jinxiang_pjian.anchor = GridBagConstraints.EAST;
		gbc_jinxiang_pjian.insets = new Insets(0, 0, 5, 5);
		gbc_jinxiang_pjian.gridx = 1;
		gbc_jinxiang_pjian.gridy = 17;
		mid_pane.add(jinxiang_pjian, gbc_jinxiang_pjian);
		jinxiang_pjian.setColumns(6);
		
		JLabel jinxiang_pjian_label = new JLabel("\u5143");
		GridBagConstraints gbc_jinxiang_pjian_label = new GridBagConstraints();
		gbc_jinxiang_pjian_label.anchor = GridBagConstraints.WEST;
		gbc_jinxiang_pjian_label.insets = new Insets(0, 0, 5, 5);
		gbc_jinxiang_pjian_label.gridx = 2;
		gbc_jinxiang_pjian_label.gridy = 17;
		mid_pane.add(jinxiang_pjian_label, gbc_jinxiang_pjian_label);
		

		
		JLabel diankou_label = new JLabel("\u70B9\u53E3/\u7279\u914D\u6BCF\u5929");
		GridBagConstraints gbc_diankou_label = new GridBagConstraints();
		gbc_diankou_label.anchor = GridBagConstraints.EAST;
		gbc_diankou_label.insets = new Insets(0, 0, 5, 5);
		gbc_diankou_label.gridx = 0;
		gbc_diankou_label.gridy = 18;
		mid_pane.add(diankou_label, gbc_diankou_label);
		
		diankou_pamount = new JTextField();
		GridBagConstraints gbc_diankou_pamount = new GridBagConstraints();
		gbc_diankou_pamount.anchor = GridBagConstraints.EAST;
		gbc_diankou_pamount.insets = new Insets(0, 0, 5, 5);
		gbc_diankou_pamount.gridx = 1;
		gbc_diankou_pamount.gridy = 18;
		mid_pane.add(diankou_pamount, gbc_diankou_pamount);
		diankou_pamount.setColumns(6);
		
		JLabel diankou_pamount_label = new JLabel("\u5143");
		GridBagConstraints gbc_diankou_pamount_label = new GridBagConstraints();
		gbc_diankou_pamount_label.anchor = GridBagConstraints.WEST;
		gbc_diankou_pamount_label.insets = new Insets(0, 0, 5, 5);
		gbc_diankou_pamount_label.gridx = 2;
		gbc_diankou_pamount_label.gridy = 18;
		mid_pane.add(diankou_pamount_label, gbc_diankou_pamount_label);
		
		JLabel qita1_label = new JLabel("\u5176\u4ED61");
		qita1_label.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_qita1_label = new GridBagConstraints();
		gbc_qita1_label.anchor = GridBagConstraints.EAST;
		gbc_qita1_label.insets = new Insets(0, 0, 5, 5);
		gbc_qita1_label.gridx = 0;
		gbc_qita1_label.gridy = 19;
		mid_pane.add(qita1_label, gbc_qita1_label);
		
		qita1_pamount = new JTextField();
		GridBagConstraints gbc_qita1_pamount = new GridBagConstraints();
		gbc_qita1_pamount.anchor = GridBagConstraints.EAST;
		gbc_qita1_pamount.insets = new Insets(0, 0, 5, 5);
		gbc_qita1_pamount.gridx = 1;
		gbc_qita1_pamount.gridy = 19;
		mid_pane.add(qita1_pamount, gbc_qita1_pamount);
		qita1_pamount.setColumns(6);
		
		JLabel qita2_label = new JLabel("\u5176\u4ED62");
		GridBagConstraints gbc_qita2_label = new GridBagConstraints();
		gbc_qita2_label.anchor = GridBagConstraints.EAST;
		gbc_qita2_label.insets = new Insets(0, 0, 5, 5);
		gbc_qita2_label.gridx = 2;
		gbc_qita2_label.gridy = 19;
		mid_pane.add(qita2_label, gbc_qita2_label);
		
		qita2_pamount = new JTextField();
		GridBagConstraints gbc_qita2_pamount = new GridBagConstraints();
		gbc_qita2_pamount.anchor = GridBagConstraints.WEST;
		gbc_qita2_pamount.insets = new Insets(0, 0, 5, 5);
		gbc_qita2_pamount.gridx = 3;
		gbc_qita2_pamount.gridy = 19;
		mid_pane.add(qita2_pamount, gbc_qita2_pamount);
		qita2_pamount.setColumns(6);
		
		JLabel qita3_label = new JLabel("\u5176\u4ED63");
		GridBagConstraints gbc_qita3_label = new GridBagConstraints();
		gbc_qita3_label.anchor = GridBagConstraints.EAST;
		gbc_qita3_label.insets = new Insets(0, 0, 5, 5);
		gbc_qita3_label.gridx = 4;
		gbc_qita3_label.gridy = 19;
		mid_pane.add(qita3_label, gbc_qita3_label);
		
		qita3_pamount = new JTextField();
		GridBagConstraints gbc_qita3_pamount = new GridBagConstraints();
		gbc_qita3_pamount.anchor = GridBagConstraints.WEST;
		gbc_qita3_pamount.insets = new Insets(0, 0, 5, 5);
		gbc_qita3_pamount.gridx = 5;
		gbc_qita3_pamount.gridy = 19;
		mid_pane.add(qita3_pamount, gbc_qita3_pamount);
		qita3_pamount.setColumns(6);
		
		
		
		JPanel bottom = new JPanel();
		add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel bottom_space1 = new JLabel("                    ");
		bottom.add(bottom_space1);
	
		//TODO 插入对内单价表id
		JButton dataJB = new JButton("提交工资结算单价");
		bottom.add(dataJB);
		dataJB.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			// 提交工作人员工作量到数据库
			insetToNP();

			}
			
		});
				
			
		JLabel bottom_space2 = new JLabel("                   ");
		bottom.add(bottom_space2);
		
		JButton resetJB = new JButton("\u91CD\u7F6E");
		bottom.add(resetJB);
		resetJB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 重置按钮
				renewAction();
				x_pzhang.setText("");y_pzhang.setText(""); chaosheng_pmi.setText(""); chaosheng_pdao.setText(""); cifen_pmi.setText(""); cifen_pdao.setText("");  shentou_pmi.setText(""); shentou_pdao.setText("");  cehou_pdian.setText("");  biaopei_pri.setText(""); guangpuquan_pdian.setText(""); guangpuquan_pge.setText("");  guangpuban_pdian.setText(""); guangpuban_pge.setText("");  yingdu_pdian.setText("");  damo_pmi.setText("");  tofd_pmi.setText("");  lashen_pjian.setText("");  wanqu_pjian.setText(""); chongji_pjian.setText("");  tie_pdian.setText("");  jinxiang_pjian.setText("");  diankou_pamount.setText(""); qita1_pamount.setText(""); qita2_pamount.setText(""); qita3_pamount.setText(""); duinei_beizhu.setText("");

			}
			
		});
		
		JLabel bottom_space3 = new JLabel("              ");
		bottom.add(bottom_space3);
		
        //TODO 刷新按钮
		JButton renewJB = new JButton("\u5237\u65B0");
		renewJB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 重新获取下拉栏的内容的重置按钮
				renewAction();			
			}

		});
		bottom.add(renewJB);
		
	}
	
	@SuppressWarnings("unchecked")
	private void renewAction(){
		//指令单号
		top.remove(cb0);
		cb0 = new MyComboBox_TextFieldPane((ArrayList<String>)sm.search(0, 1),10);
		GridBagConstraints gbc_cb0 = new GridBagConstraints();
		gbc_cb0.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb0.weighty = 10.0;
		gbc_cb0.weightx = 10.0;
		gbc_cb0.insets = new Insets(0, 0, 0, 5);
		gbc_cb0.gridx = 1;
		gbc_cb0.gridy = 0;
		top.add(cb0, gbc_cb0);
		cb0.addFocusListener(new FocusAdapter(){
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO 失去焦点向委托单位和工程名称里查询插入	
				cb1.setModel( (DefaultComboBoxModel<String>)sm.search(3, 0));
				cb1.setSelectedItem((String)sm.search(1,2,new String[]{cb0.getText()}));
				cb2.setModel( (DefaultComboBoxModel<String>)sm.search(4, 0));
				cb2.setSelectedItem((String)sm.search(2,2,new String[]{cb0.getText()}));
			}

		});
		
		//委托单位
		top.remove(cb1);
		cb1.setModel( (DefaultComboBoxModel<String>)sm.search(3, 0));
		cb1.setMaximumRowCount(15);
		cb1.setSelectedIndex(-1);
		GridBagConstraints gbc_cb1 = new GridBagConstraints();
		gbc_cb1.weighty = 10.0;
		gbc_cb1.weightx = 10.0;
		gbc_cb1.insets = new Insets(0, 0, 0, 5);
		gbc_cb1.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb1.gridx = 3;
		gbc_cb1.gridy = 0;
		top.add(cb1, gbc_cb1);
		cb1.addFocusListener(new FocusAdapter(){
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
		
		
		//项目名称
		top.remove(cb2);
		cb2.setModel( (DefaultComboBoxModel<String>)sm.search(4, 0));
		cb2.setMaximumRowCount(15);
		cb2.setSelectedIndex(-1);
		GridBagConstraints gbc_cb2 = new GridBagConstraints();
		gbc_cb2.weighty = 10.0;
		gbc_cb2.weightx = 10.0;
		gbc_cb2.insets = new Insets(0, 0, 0, 5);
		gbc_cb2.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb2.gridx = 5;
		gbc_cb2.gridy = 0;
		top.add(cb2, gbc_cb2);
		cb2.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e) {
				// 筛选指令单号
				if((String)cb1.getSelectedItem() != "" && cb1.getSelectedItem()!=""){
				cb0.setText((String)sm.search(7,2,new String[]{(String)cb1.getSelectedItem(),(String)cb2.getSelectedItem()}));
				}			
	     	}		
		});		
		
		updateUI();
	}
	
	private void insetToNP(){
		int op = JOptionPane.showConfirmDialog(this, "确认添加算工资单价？", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(op == JOptionPane.CANCEL_OPTION || op == JOptionPane.CLOSED_OPTION ) return ;
		String[] data = new String[29];
		//0处理指令单号
		data[0] = cb0.getText().trim();
		if(data[0].equals("")) {JOptionPane.showMessageDialog(null, "请输入指令单号", "失败", JOptionPane.ERROR_MESSAGE);return ;}
		//1处理x光/张
		data[1] = x_pzhang.getText().trim();
		//2处理y/张
		data[2] = y_pzhang.getText().trim();
		//3处理超声/米
		data[3] = chaosheng_pmi.getText().trim();
		//4处理超声/道
		data[4] = chaosheng_pdao.getText().trim();
		//5处理磁粉/米
		data[5] = cifen_pmi.getText().trim();
		//6处理磁粉/道
		data[6] = cifen_pdao.getText().trim();	
		//7处理渗透/米
		data[7] = shentou_pmi.getText().trim();
		//8处理渗透/道
		data[8] = shentou_pdao.getText().trim();
		//9处理测厚/点
		data[9] = cehou_pdian.getText().trim();
		//10处理表面配合/工日
		data[10] = biaopei_pri.getText().trim();
		//11处理全定量/点
		data[11] = guangpuquan_pdian.getText().trim();
		//12处理全定量/个元素
		data[12] = guangpuquan_pge.getText().trim();
		//13处理半定量/点
		data[13] = guangpuban_pdian.getText().trim();
		//14处理半定量/个元素
		data[14] = guangpuban_pge.getText().trim();
		//15处理硬度/点
		data[15] = yingdu_pdian.getText().trim();
		//16处理打磨/米
		data[16] = damo_pmi.getText().trim();
		//17处理tofd/米
		data[17] = tofd_pmi.getText().trim();
		//18处理拉伸/件
		data[18] = lashen_pjian.getText().trim();
		//19处理弯曲/件
		data[19] = wanqu_pjian.getText().trim();
		//20处理冲击/件
		data[20] = chongji_pjian.getText().trim();
		//21处理铁素体/点
		data[21] = tie_pdian.getText().trim();
		//22处理金相/件
		data[22] = jinxiang_pjian.getText().trim();
		//23处理点口、特检院配合、技术指导 数量
		data[23] = diankou_pamount.getText().trim();
		//24处理其他项目1数量
		data[24] = qita1_pamount.getText().trim();
		//25处理其他项目2数量
		data[25] = qita2_pamount.getText().trim();
		//26处理其他项目3数量
		data[26] = qita3_pamount.getText().trim();
		//27处理备注
		data[27] = duinei_beizhu.getText().trim();
		//28部门
		data[28] = group;
		
		//插入数据库
		sm.insert_duinei_price_table(data);
		
	}

}
