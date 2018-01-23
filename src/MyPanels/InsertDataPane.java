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

public class InsertDataPane extends JPanel {
	private String group;//ȫ�ֱ����Ĵ�ֵ
	private SQLManager sm;//��ȡ���ݿ����
	
	private JTextField calTF;


	//��������
	private JPanel top = new JPanel();
	private MyComboBox_TextFieldPane cb0;  //ָ�������ѡ���
	private JComboBox<String> cb1= new JComboBox<>(); //ί�е�λ
	private JComboBox<String> cb2 = new JComboBox<>();//��������
	
	
	//��������
	private JPanel mid_left_top = new JPanel();
	private JTextField workdate = new JTextField();
	private JComboBox<String> workperson = new JComboBox<String>();//������ԱCombobox
	private JLabel workperson_info = new JLabel("");   //��ʾ������Ա�����������״̬
	private JTextField xipian1= new JTextField();
	private JTextField xipian2= new JTextField();
	private JTextField xipian3= new JTextField();
	private JComboBox<String> xipianren1 = new JComboBox<>();//ϴƬ��1������ѡ��
	private JComboBox<String> xipianren2 = new JComboBox<>();//ϴƬ��2������ѡ��
	private JComboBox<String> xipianren3 = new JComboBox<>();//ϴƬ��3������ѡ��
    private JTextArea shiwu_beizhu = new JTextArea();
    private JTextField jilu_num;
   
    private JTextField xipian_total = new JTextField();
 //   private JTextField paipian_total = new JTextField();
   
    public static final Pattern pattern = Pattern.compile("(\\d+)|(\\d+\\.\\d+)");
    public static final Pattern pattern2 = Pattern.compile("\\d\\d\\d\\d\\d\\d\\d\\d");

    
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
    

    private JTextField x_pfei;
    private JTextField y_pfei;
    private JTextField x_pci;
    private JTextField y_pci;
    private JTextField x_wci;
    private JTextField y_wci;
    private JTextField chaosheng_pci;
    private JTextField chaosheng_wci;
    private JTextField cifen_pci;
    private JTextField cifen_wci;
    private JTextField shentou_pci;
    private JTextField shentou_wci;
    private JTextField cehou_pci;
    private JTextField cehou_wci;
    private JTextField guangpuquan_pci;
    private JTextField guangpuquan_wci;
    private JTextField guangpuban_pci;
    private JTextField guangpuban_wci;
    private JTextField yingdu_pci;
    private JTextField yingdu_wci;
    private JTextField damo_pci;
    private JTextField damo_wci;
    private JTextField tofd_pci;
    private JTextField tofd_wci;
    private JTextField lashen_pci;
    private JTextField lashen_wci;
    private JTextField wanqu_pci;
    private JTextField wanqu_wci;
    private JTextField chongji_pci;
    private JTextField chongji_wci;
    private JTextField tie_pci;
    private JTextField tie_wci;
    private JTextField jinxiang_pci;
    private JTextField jinxiang_wci;

	/**
	 * Create the panel.
	 * @param group 
	 */
	@SuppressWarnings("unchecked")
	public InsertDataPane( SQLManager s, String group) {
		this.group=group;
		this.sm=s;
		//�ܲ���BorderLayout
		setLayout(new BorderLayout(0, 0));
		
		//�м�ɹ�������
		JScrollPane middle = new JScrollPane();
		add(middle, BorderLayout.CENTER);
		
		//�ڹ������������BoxLayout��middle_Pane,��������������������
		JPanel middle_pane = new JPanel();
		middle.setViewportView(middle_pane);
		middle_pane.setLayout(new BoxLayout(middle_pane, BoxLayout.X_AXIS));
		
		JPanel mid_left = new JPanel();
		middle_pane.add(mid_left);
		mid_left.setLayout(new BoxLayout(mid_left, BoxLayout.Y_AXIS));
		
		//TODO �м���ߵ�����
		mid_left.add(mid_left_top);
		GridBagLayout gbl_mid_left_top = new GridBagLayout();
		gbl_mid_left_top.columnWidths = new int[]{0, 64, 31, 24, 0};
		gbl_mid_left_top.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mid_left_top.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_mid_left_top.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mid_left_top.setLayout(gbl_mid_left_top);
		
//TODO ������Ա��Ϣ		
		//workpersonlabel����Ƭ���˵ı�ǩ
		 JLabel workpersonlabel = new JLabel("\u5DE5\u4F5C\u4EBA\u5458");
		GridBagConstraints gbc_workpersonlabel = new GridBagConstraints();
		gbc_workpersonlabel.insets = new Insets(0, 0, 5, 5);
		gbc_workpersonlabel.anchor = GridBagConstraints.EAST;
		gbc_workpersonlabel.gridx = 0;
		gbc_workpersonlabel.gridy = 0;
		mid_left_top.add(workpersonlabel, gbc_workpersonlabel);
		
		//workperson����Ƭ���˵����ֵ������򣬿�����
		workperson.setModel( (DefaultComboBoxModel<String>)sm.search(8, 0));
		workperson.setMaximumRowCount(15);
		workperson.setSelectedIndex(-1);
		workpersonlabel.setLabelFor(workperson);
		workperson.setEditable(true);
		workperson.setMaximumRowCount(10);
		GridBagConstraints gbc_workperson = new GridBagConstraints();
		gbc_workperson.fill = GridBagConstraints.HORIZONTAL;
		gbc_workperson.anchor = GridBagConstraints.WEST;
		gbc_workperson.insets = new Insets(0, 0, 5, 5);
		gbc_workperson.gridx = 1;
		gbc_workperson.gridy = 0;
		mid_left_top.add(workperson, gbc_workperson);
		workperson.setFocusable(true);
		workperson.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// Ѱ������ƥ��ֵ
				if((  (String)workperson.getSelectedItem() != "" )&&( workperson.getSelectedIndex()>-1)  ){
					System.out.println("�����ֵ");
					workperson_info.setText("");
				}
				else{
					workperson.setSelectedIndex(-1);
					//JOptionPane.showMessageDialog(null, "δ�ҵ���������Ա��������Ա���������", "���޴���", JOptionPane.ERROR_MESSAGE);
					workperson_info.setText("���޴���");
					System.out.println("û���ֵ");
				}				
			}		
		});
		
		
		//workperon����Ϣlabel workperson_info ,��ʾ������Ϣ
		GridBagConstraints gbc_workperson_info = new GridBagConstraints();
		gbc_workperson_info.insets = new Insets(0, 0, 5, 5);
		gbc_workperson_info.gridx = 2;
		gbc_workperson_info.gridy = 0;
		workperson_info.setFont(new Font("���ǳ�������", Font.BOLD, 12));
		workperson_info.setForeground(Color.RED);
		mid_left_top.add(workperson_info, gbc_workperson_info);
		
		JButton calJB = new JButton("\u8BA1\u7B97\u5668");
		calJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Calculator(calTF);
				calTF=null;
			}
		});
		GridBagConstraints gbc_calJB = new GridBagConstraints();
		gbc_calJB.insets = new Insets(0, 0, 5, 0);
		gbc_calJB.gridx = 3;
		gbc_calJB.gridy = 0;
		mid_left_top.add(calJB, gbc_calJB);
		
		
		//��������
		JLabel workdatelabel = new JLabel("\u5DE5\u4F5C\u65F6\u95F4");
		GridBagConstraints gbc_workdatelabel = new GridBagConstraints();
		gbc_workdatelabel.anchor = GridBagConstraints.EAST;
		gbc_workdatelabel.insets = new Insets(0, 0, 5, 5);
		gbc_workdatelabel.gridx = 0;
		gbc_workdatelabel.gridy = 1;
		mid_left_top.add(workdatelabel, gbc_workdatelabel);
		
		
		workdatelabel.setLabelFor(workdate);
		GridBagConstraints gbc_workdate = new GridBagConstraints();
		gbc_workdate.anchor = GridBagConstraints.WEST;
		gbc_workdate.insets = new Insets(0, 0, 5, 5);
		gbc_workdate.gridx = 1;
		gbc_workdate.gridy = 1;
		mid_left_top.add(workdate, gbc_workdate);
		workdate.setColumns(10);
		
		

		
		
		// TODO xipian1 ,xipian2,xipian3 ��3��ϴƬ���˵Ĺ�������xipianren1,2,3������������			
	    // ϴƬ��1combobox xipianren1��¼ϴƬ�˵���Ϣ
		xipianren1.setModel( (DefaultComboBoxModel<String>)sm.search(9, 0));
		xipianren1.setSelectedIndex(-1);
		xipianren1.setMaximumRowCount(15);
		GridBagConstraints gbc_xipianren1 = new GridBagConstraints();
		gbc_xipianren1.fill = GridBagConstraints.HORIZONTAL;
		gbc_xipianren1.anchor = GridBagConstraints.WEST;
		gbc_xipianren1.insets = new Insets(0, 0, 5, 5);
		gbc_xipianren1.gridx = 1;
		gbc_xipianren1.gridy = 2;
		mid_left_top.add(xipianren1, gbc_xipianren1);
		
		
		JLabel xipianlabel1 = new JLabel("\u6D17\u7247\u4EBA1");
		GridBagConstraints gbc_xipianlabel1 = new GridBagConstraints();
		gbc_xipianlabel1.insets = new Insets(0, 0, 5, 5);
		gbc_xipianlabel1.gridx = 0;
		gbc_xipianlabel1.gridy = 2;
		mid_left_top.add(xipianlabel1, gbc_xipianlabel1);
		xipianlabel1.setLabelFor(xipianren1);	
		
		
		JLabel xipian_amount1 = new JLabel("\u6570\u91CF");
		GridBagConstraints gbc_xipian_amount1 = new GridBagConstraints();
		gbc_xipian_amount1.anchor = GridBagConstraints.EAST;
		gbc_xipian_amount1.insets = new Insets(0, 0, 5, 5);
		gbc_xipian_amount1.gridx = 2;
		gbc_xipian_amount1.gridy = 2;
		mid_left_top.add(xipian_amount1, gbc_xipian_amount1);
		xipian_amount1.setLabelFor(xipian1);
		
		
        //ϴƬ��1�Ĺ�����,������ʱ�ѹ������㵽ͳ����
		GridBagConstraints gbc_xipian1 = new GridBagConstraints();
		gbc_xipian1.anchor = GridBagConstraints.WEST;
		gbc_xipian1.insets = new Insets(0, 0, 5, 0);
		gbc_xipian1.fill = GridBagConstraints.HORIZONTAL;
		gbc_xipian1.gridx = 3;
		gbc_xipian1.gridy = 2;
		xipian1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		mid_left_top.add(xipian1, gbc_xipian1);
		xipian1.setColumns(5);

		
		
		//ϴƬ��2������ѡ��
		xipianren2.setModel( (DefaultComboBoxModel<String>)sm.search(9, 0));
		xipianren2.setSelectedIndex(-1);
		xipianren2.setMaximumRowCount(15);		
		GridBagConstraints gbc_xipianren2 = new GridBagConstraints();
		gbc_xipianren2.fill = GridBagConstraints.HORIZONTAL;
		gbc_xipianren2.anchor = GridBagConstraints.WEST;
		gbc_xipianren2.insets = new Insets(0, 0, 5, 5);
		gbc_xipianren2.gridx = 1;
		gbc_xipianren2.gridy = 3;
		mid_left_top.add(xipianren2, gbc_xipianren2);	
		
		JLabel xipianlabel2 = new JLabel("\u6D17\u7247\u4EBA2");
		GridBagConstraints gbc_xipianlabel2 = new GridBagConstraints();
		gbc_xipianlabel2.insets = new Insets(0, 0, 5, 5);
		gbc_xipianlabel2.gridx = 0;
		gbc_xipianlabel2.gridy = 3;
		mid_left_top.add(xipianlabel2, gbc_xipianlabel2);
		xipianlabel2.setLabelFor(xipianren2);
		

		
		JLabel xipian_amount2 = new JLabel("\u6570\u91CF");
		GridBagConstraints gbc_xipian_amount2 = new GridBagConstraints();
		gbc_xipian_amount2.anchor = GridBagConstraints.EAST;
		gbc_xipian_amount2.insets = new Insets(0, 0, 5, 5);
		gbc_xipian_amount2.gridx = 2;
		gbc_xipian_amount2.gridy = 3;
		mid_left_top.add(xipian_amount2, gbc_xipian_amount2);
		xipian_amount2.setLabelFor(xipian2);
		

		xipian2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		GridBagConstraints gbc_xipian2 = new GridBagConstraints();
		gbc_xipian2.anchor = GridBagConstraints.WEST;
		gbc_xipian2.insets = new Insets(0, 0, 5, 0);
		gbc_xipian2.fill = GridBagConstraints.HORIZONTAL;
		gbc_xipian2.gridx = 3;
		gbc_xipian2.gridy = 3;
		mid_left_top.add(xipian2, gbc_xipian2);
		xipian2.setColumns(5);



		//ϴƬ��3������ѡ��
		xipianren3.setModel( (DefaultComboBoxModel<String>)sm.search(9, 0));
		xipianren3.setSelectedIndex(-1);
		xipianren3.setMaximumRowCount(15);			
		GridBagConstraints gbc_xipianren3 = new GridBagConstraints();
		gbc_xipianren3.fill = GridBagConstraints.HORIZONTAL;
		gbc_xipianren3.anchor = GridBagConstraints.WEST;
		gbc_xipianren3.insets = new Insets(0, 0, 5, 5);
		gbc_xipianren3.gridx = 1;
		gbc_xipianren3.gridy = 4;
		mid_left_top.add(xipianren3, gbc_xipianren3);		
		
		JLabel xipianlabel3 = new JLabel("\u6D17\u7247\u4EBA3");
		GridBagConstraints gbc_xipianlabel3 = new GridBagConstraints();
		gbc_xipianlabel3.insets = new Insets(0, 0, 5, 5);
		gbc_xipianlabel3.gridx = 0;
		gbc_xipianlabel3.gridy = 4;
		mid_left_top.add(xipianlabel3, gbc_xipianlabel3);
		xipianlabel3.setLabelFor(xipianren3);
		

		
		JLabel xipian_amount3 = new JLabel("\u6570\u91CF");
		GridBagConstraints gbc_xipian_amount3 = new GridBagConstraints();
		gbc_xipian_amount3.anchor = GridBagConstraints.EAST;
		gbc_xipian_amount3.insets = new Insets(0, 0, 5, 5);
		gbc_xipian_amount3.gridx = 2;
		gbc_xipian_amount3.gridy = 4;
		mid_left_top.add(xipian_amount3, gbc_xipian_amount3);
		xipian_amount3.setLabelFor(xipian3);
		

		xipian3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		GridBagConstraints gbc_xipian3 = new GridBagConstraints();
		gbc_xipian3.anchor = GridBagConstraints.WEST;
		gbc_xipian3.insets = new Insets(0, 0, 5, 0);
		gbc_xipian3.fill = GridBagConstraints.HORIZONTAL;
		gbc_xipian3.gridx = 3;
		gbc_xipian3.gridy = 4;
		mid_left_top.add(xipian3, gbc_xipian3);
		xipian3.setColumns(5);
		
		//TODO ��¼���
		JLabel jilu_num_label = new JLabel("\u8BB0\u5F55\u7F16\u53F7");
		GridBagConstraints gbc_jilu_num_label = new GridBagConstraints();
		gbc_jilu_num_label.anchor = GridBagConstraints.EAST;
		gbc_jilu_num_label.insets = new Insets(0, 0, 5, 5);
		gbc_jilu_num_label.gridx = 0;
		gbc_jilu_num_label.gridy = 6;
		mid_left_top.add(jilu_num_label, gbc_jilu_num_label);
		
		jilu_num = new JTextField();
		GridBagConstraints gbc_jilu_num = new GridBagConstraints();
		gbc_jilu_num.insets = new Insets(0, 0, 5, 5);
		gbc_jilu_num.fill = GridBagConstraints.HORIZONTAL;
		gbc_jilu_num.gridx = 1;
		gbc_jilu_num.gridy = 6;
		mid_left_top.add(jilu_num, gbc_jilu_num);
		jilu_num.setColumns(10);

		
		//TODO ����ע
		JLabel shiwu_beizhu_label = new JLabel("\u5907\u6CE8");
		GridBagConstraints gbc_shiwu_beizhu_label = new GridBagConstraints();
		gbc_shiwu_beizhu_label.insets = new Insets(0, 0, 0, 5);
		gbc_shiwu_beizhu_label.gridx = 0;
		gbc_shiwu_beizhu_label.gridy = 7;
		mid_left_top.add(shiwu_beizhu_label, gbc_shiwu_beizhu_label);
		
		JPanel shiwu_beizhu_panel = new JPanel();
		GridBagConstraints gbc_shiwu_beizhu_panel = new GridBagConstraints();
		gbc_shiwu_beizhu_panel.gridwidth = 2;
		gbc_shiwu_beizhu_panel.insets = new Insets(0, 0, 0, 5);
		gbc_shiwu_beizhu_panel.gridx = 1;
		gbc_shiwu_beizhu_panel.gridy = 7;
		mid_left_top.add(shiwu_beizhu_panel, gbc_shiwu_beizhu_panel);
		shiwu_beizhu_panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		shiwu_beizhu_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//���ﱸע��Textfield
		shiwu_beizhu_panel.add(shiwu_beizhu);
		shiwu_beizhu.setLineWrap(true);
		shiwu_beizhu.setColumns(20);
		shiwu_beizhu.setRows(5);

		
		//��������Ŀհײ��֣�������������
		JLabel space_left = new JLabel("");
		GridBagConstraints gbc_space_left = new GridBagConstraints();
		gbc_space_left.anchor = GridBagConstraints.WEST;
		gbc_space_left.weighty = 100.0;
		gbc_space_left.weightx = 100.0;
		gbc_space_left.gridx = 3;
		gbc_space_left.gridy = 7;
		mid_left_top.add(space_left, gbc_space_left);
		

		//TODO ����������
		JPanel mid_left_bottom = new JPanel();
		mid_left_bottom.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u7EDF\u8BA1\u6838\u5BF9(\u6839\u636E\u8BB0\u5F55\u7F16\u53F7)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mid_left.add(mid_left_bottom);
		GridBagLayout gbl_mid_left_bottom = new GridBagLayout();
		gbl_mid_left_bottom.columnWidths = new int[]{0, 0, 0, 0};
		gbl_mid_left_bottom.rowHeights = new int[]{0, 0, 0, 0};
		gbl_mid_left_bottom.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_mid_left_bottom.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		mid_left_bottom.setLayout(gbl_mid_left_bottom);
		
		JLabel xipian_total_label = new JLabel("\u8BB0\u5F55\u603B\u91CF");
		GridBagConstraints gbc_xipian_total_label = new GridBagConstraints();
		gbc_xipian_total_label.insets = new Insets(0, 0, 5, 5);
		gbc_xipian_total_label.gridx = 0;
		gbc_xipian_total_label.gridy = 0;
		mid_left_bottom.add(xipian_total_label, gbc_xipian_total_label);
		
        //ϴƬ����
		xipian_total.setEditable(false);
		GridBagConstraints gbc_xipian_total = new GridBagConstraints();
		gbc_xipian_total.insets = new Insets(0, 0, 5, 5);
		gbc_xipian_total.anchor = GridBagConstraints.WEST;
		gbc_xipian_total.gridx = 1;
		gbc_xipian_total.gridy = 0;
		mid_left_bottom.add(xipian_total, gbc_xipian_total);
		xipian_total.setColumns(10);
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				caculate_xipian();
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 0);
		gbc_button.gridx = 2;
		gbc_button.gridy = 0;
		mid_left_bottom.add(button, gbc_button);
		
//		JLabel paipian_total_label = new JLabel("\u8BB0\u5F55\u62CD\u7247\u603B\u91CF");
//		GridBagConstraints gbc_paipian_total_label = new GridBagConstraints();
//		gbc_paipian_total_label.insets = new Insets(0, 0, 5, 5);
//		gbc_paipian_total_label.gridx = 0;
//		gbc_paipian_total_label.gridy = 1;
//		mid_left_bottom.add(paipian_total_label, gbc_paipian_total_label);
//		
//        //��Ƭ������ֻ��x���ߺ�Y����
//		paipian_total.setEditable(false);
//		GridBagConstraints gbc_paipian_total = new GridBagConstraints();
//		gbc_paipian_total.anchor = GridBagConstraints.WEST;
//		gbc_paipian_total.insets = new Insets(0, 0, 5, 5);
//		gbc_paipian_total.gridx = 1;
//		gbc_paipian_total.gridy = 1;
//		mid_left_bottom.add(paipian_total, gbc_paipian_total);
//		paipian_total.setColumns(10);
		
		JLabel mid_left_bottom_space = new JLabel("");
		GridBagConstraints gbc_mid_left_bottom_space = new GridBagConstraints();
		gbc_mid_left_bottom_space.weighty = 100.0;
		gbc_mid_left_bottom_space.weightx = 100.0;
		gbc_mid_left_bottom_space.fill = GridBagConstraints.BOTH;
		gbc_mid_left_bottom_space.gridx = 2;
		gbc_mid_left_bottom_space.gridy = 2;
		mid_left_bottom.add(mid_left_bottom_space, gbc_mid_left_bottom_space);
		
		
		
		
		//�м������Ұ벿��
		JPanel mid_right = new JPanel();
		mid_right.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u68C0\u6D4B\u5DE5\u4F5C\u91CF", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		middle_pane.add(mid_right);
		GridBagLayout gbl_mid_right = new GridBagLayout();
		gbl_mid_right.columnWidths = new int[]{85, 27, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mid_right.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mid_right.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_mid_right.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		mid_right.setLayout(gbl_mid_right);
		
		JPanel split_pane = new JPanel();
		split_pane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_split_pane = new GridBagConstraints();
		gbc_split_pane.gridheight = 19;
		gbc_split_pane.insets = new Insets(0, 0, 5, 5);
		gbc_split_pane.fill = GridBagConstraints.BOTH;
		gbc_split_pane.gridx = 5;
		gbc_split_pane.gridy = 0;
		mid_right.add(split_pane, gbc_split_pane);
		
		JLabel duineianci_label1 = new JLabel("\u5BF9\u5185\u6309\u6B21\u7B97\u5355\u4EF7");
		GridBagConstraints gbc_duineianci_label1 = new GridBagConstraints();
		gbc_duineianci_label1.insets = new Insets(0, 0, 5, 5);
		gbc_duineianci_label1.gridx = 6;
		gbc_duineianci_label1.gridy = 0;
		mid_right.add(duineianci_label1, gbc_duineianci_label1);
		
		JLabel duiwaidanjia_label1 = new JLabel("\u5BF9\u5916\u6309\u6B21\u7B97\u5355\u4EF7");
		GridBagConstraints gbc_duiwaidanjia_label1 = new GridBagConstraints();
		gbc_duiwaidanjia_label1.insets = new Insets(0, 0, 5, 5);
		gbc_duiwaidanjia_label1.gridx = 7;
		gbc_duiwaidanjia_label1.gridy = 0;
		mid_right.add(duiwaidanjia_label1, gbc_duiwaidanjia_label1);
		
		//x���߰�����������
		JLabel x_label = new JLabel("X\u5149");
		GridBagConstraints gbc_x_label = new GridBagConstraints();
		gbc_x_label.anchor = GridBagConstraints.EAST;
		gbc_x_label.insets = new Insets(0, 0, 5, 5);
		gbc_x_label.gridx = 0;
		gbc_x_label.gridy = 1;
		mid_right.add(x_label, gbc_x_label);
		
		
		x_pzhang = new JTextField();
		GridBagConstraints gbc_x_pzhang = new GridBagConstraints();
		gbc_x_pzhang.anchor = GridBagConstraints.EAST;
		gbc_x_pzhang.insets = new Insets(0, 0, 5, 5);
		gbc_x_pzhang.gridx = 1;
		gbc_x_pzhang.gridy = 1;
		mid_right.add(x_pzhang, gbc_x_pzhang);
		x_pzhang.setColumns(3);
		x_pzhang.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});

		
		JLabel x_pzhang_label = new JLabel("\u5F20");
		GridBagConstraints gbc_x_pzhang_label = new GridBagConstraints();
		gbc_x_pzhang_label.anchor = GridBagConstraints.WEST;
		gbc_x_pzhang_label.insets = new Insets(0, 0, 5, 5);
		gbc_x_pzhang_label.gridx = 2;
		gbc_x_pzhang_label.gridy = 1;
		mid_right.add(x_pzhang_label, gbc_x_pzhang_label);
		
		x_pfei = new JTextField();
		GridBagConstraints gbc_x_pfei = new GridBagConstraints();
		gbc_x_pfei.anchor = GridBagConstraints.EAST;
		gbc_x_pfei.insets = new Insets(0, 0, 5, 5);
		gbc_x_pfei.gridx = 3;
		gbc_x_pfei.gridy = 1;
		mid_right.add(x_pfei, gbc_x_pfei);
		x_pfei.setColumns(3);
		x_pfei.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel x_pfei_label = new JLabel("\u5F20\u5E9F\u7247");
		GridBagConstraints gbc_x_pfei_label = new GridBagConstraints();
		gbc_x_pfei_label.anchor = GridBagConstraints.WEST;
		gbc_x_pfei_label.insets = new Insets(0, 0, 5, 5);
		gbc_x_pfei_label.gridx = 4;
		gbc_x_pfei_label.gridy = 1;
		mid_right.add(x_pfei_label, gbc_x_pfei_label);
		
		x_pci = new JTextField();
		x_pci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(x_pci.getText().trim().equals("")) x_pci.setBackground(new Color(255, 255, 255));
				else x_pci.setBackground(new Color(255, 255, 0));
			}
		});
		x_pci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		x_pci.setForeground(new Color(0, 0, 0));
		GridBagConstraints gbc_x_pci = new GridBagConstraints();
		gbc_x_pci.fill = GridBagConstraints.HORIZONTAL;
		gbc_x_pci.insets = new Insets(0, 0, 5, 5);
		gbc_x_pci.gridx = 6;
		gbc_x_pci.gridy = 1;
		mid_right.add(x_pci, gbc_x_pci);
		x_pci.setColumns(10);
		
		x_wci = new JTextField();
		x_wci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(x_wci.getText().trim().equals("")) x_wci.setBackground(new Color(255, 255, 255));
				else x_wci.setBackground(new Color(255, 255, 0));
			}
		});
		x_wci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		GridBagConstraints gbc_x_wci = new GridBagConstraints();
		gbc_x_wci.fill = GridBagConstraints.HORIZONTAL;
		gbc_x_wci.insets = new Insets(0, 0, 5, 5);
		gbc_x_wci.gridx = 7;
		gbc_x_wci.gridy = 1;
		mid_right.add(x_wci, gbc_x_wci);
		x_wci.setColumns(10);
		
		JLabel y_label = new JLabel("\u03B3\u5C04\u7EBF");
		GridBagConstraints gbc_y_label = new GridBagConstraints();
		gbc_y_label.anchor = GridBagConstraints.EAST;
		gbc_y_label.insets = new Insets(0, 0, 5, 5);
		gbc_y_label.gridx = 0;
		gbc_y_label.gridy = 2;
		mid_right.add(y_label, gbc_y_label);
		
		//٤�����߰�����
		y_pzhang = new JTextField();
		GridBagConstraints gbc_y_pzhang = new GridBagConstraints();
		gbc_y_pzhang.anchor = GridBagConstraints.EAST;
		gbc_y_pzhang.insets = new Insets(0, 0, 5, 5);
		gbc_y_pzhang.gridx = 1;
		gbc_y_pzhang.gridy = 2;
		mid_right.add(y_pzhang, gbc_y_pzhang);
		y_pzhang.setColumns(3);
		y_pzhang.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});

		
		JLabel y_pzhang_label = new JLabel("\u5F20");
		GridBagConstraints gbc_y_pzhang_label = new GridBagConstraints();
		gbc_y_pzhang_label.anchor = GridBagConstraints.WEST;
		gbc_y_pzhang_label.insets = new Insets(0, 0, 5, 5);
		gbc_y_pzhang_label.gridx = 2;
		gbc_y_pzhang_label.gridy = 2;
		mid_right.add(y_pzhang_label, gbc_y_pzhang_label);
		
		y_pfei = new JTextField();
		GridBagConstraints gbc_y_pfei = new GridBagConstraints();
		gbc_y_pfei.anchor = GridBagConstraints.EAST;
		gbc_y_pfei.insets = new Insets(0, 0, 5, 5);
		gbc_y_pfei.gridx = 3;
		gbc_y_pfei.gridy = 2;
		mid_right.add(y_pfei, gbc_y_pfei);
		y_pfei.setColumns(3);
		y_pfei.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel y_pfei_label = new JLabel("\u5F20\u5E9F\u7247");
		GridBagConstraints gbc_y_pfei_label = new GridBagConstraints();
		gbc_y_pfei_label.anchor = GridBagConstraints.WEST;
		gbc_y_pfei_label.insets = new Insets(0, 0, 5, 5);
		gbc_y_pfei_label.gridx = 4;
		gbc_y_pfei_label.gridy = 2;
		mid_right.add(y_pfei_label, gbc_y_pfei_label);
		
		y_pci = new JTextField();
		y_pci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(y_pci.getText().trim().equals("")) y_pci.setBackground(new Color(255, 255, 255));
				else y_pci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_y_pci = new GridBagConstraints();
		gbc_y_pci.fill = GridBagConstraints.HORIZONTAL;
		gbc_y_pci.insets = new Insets(0, 0, 5, 5);
		gbc_y_pci.gridx = 6;
		gbc_y_pci.gridy = 2;
		mid_right.add(y_pci, gbc_y_pci);
		y_pci.setColumns(10);
		y_pci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		y_wci = new JTextField();
		y_wci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(y_wci.getText().trim().equals("")) y_wci.setBackground(new Color(255, 255, 255));
				else y_wci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_y_wci = new GridBagConstraints();
		gbc_y_wci.fill = GridBagConstraints.HORIZONTAL;
		gbc_y_wci.insets = new Insets(0, 0, 5, 5);
		gbc_y_wci.gridx = 7;
		gbc_y_wci.gridy = 2;
		mid_right.add(y_wci, gbc_y_wci);
		y_wci.setColumns(10);
		y_wci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		chaosheng_pci = new JTextField();
		chaosheng_pci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(chaosheng_pci.getText().trim().equals("")) chaosheng_pci.setBackground(new Color(255, 255, 255));
				else chaosheng_pci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_chaosheng_pci = new GridBagConstraints();
		gbc_chaosheng_pci.insets = new Insets(0, 0, 5, 5);
		gbc_chaosheng_pci.fill = GridBagConstraints.HORIZONTAL;
		gbc_chaosheng_pci.gridx = 6;
		gbc_chaosheng_pci.gridy = 3;
		mid_right.add(chaosheng_pci, gbc_chaosheng_pci);
		chaosheng_pci.setColumns(10);
		chaosheng_pci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		chaosheng_wci = new JTextField();
		chaosheng_wci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(chaosheng_wci.getText().trim().equals("")) chaosheng_wci.setBackground(new Color(255, 255, 255));
				else chaosheng_wci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_chaosheng_wci = new GridBagConstraints();
		gbc_chaosheng_wci.insets = new Insets(0, 0, 5, 5);
		gbc_chaosheng_wci.fill = GridBagConstraints.HORIZONTAL;
		gbc_chaosheng_wci.gridx = 7;
		gbc_chaosheng_wci.gridy = 3;
		mid_right.add(chaosheng_wci, gbc_chaosheng_wci);
		chaosheng_wci.setColumns(10);
		chaosheng_wci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		cifen_pci = new JTextField();
		cifen_pci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(cifen_pci.getText().trim().equals("")) cifen_pci.setBackground(new Color(255, 255, 255));
				else cifen_pci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_cifen_pci = new GridBagConstraints();
		gbc_cifen_pci.insets = new Insets(0, 0, 5, 5);
		gbc_cifen_pci.fill = GridBagConstraints.HORIZONTAL;
		gbc_cifen_pci.gridx = 6;
		gbc_cifen_pci.gridy = 4;
		mid_right.add(cifen_pci, gbc_cifen_pci);
		cifen_pci.setColumns(10);
		cifen_pci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		cifen_wci = new JTextField();
		cifen_wci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(cifen_wci.getText().trim().equals("")) cifen_wci.setBackground(new Color(255, 255, 255));
				else cifen_wci.setBackground(new Color(255, 255, 0));
			}
		});
		cifen_wci.setText("");
		GridBagConstraints gbc_cifen_wci = new GridBagConstraints();
		gbc_cifen_wci.insets = new Insets(0, 0, 5, 5);
		gbc_cifen_wci.fill = GridBagConstraints.HORIZONTAL;
		gbc_cifen_wci.gridx = 7;
		gbc_cifen_wci.gridy = 4;
		mid_right.add(cifen_wci, gbc_cifen_wci);
		cifen_wci.setColumns(10);
		cifen_wci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		shentou_pci = new JTextField();
		shentou_pci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(shentou_pci.getText().trim().equals("")) shentou_pci.setBackground(new Color(255, 255, 255));
				else shentou_pci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_shentou_pci = new GridBagConstraints();
		gbc_shentou_pci.insets = new Insets(0, 0, 5, 5);
		gbc_shentou_pci.fill = GridBagConstraints.HORIZONTAL;
		gbc_shentou_pci.gridx = 6;
		gbc_shentou_pci.gridy = 5;
		mid_right.add(shentou_pci, gbc_shentou_pci);
		shentou_pci.setColumns(10);
		shentou_pci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		shentou_wci = new JTextField();
		shentou_wci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(shentou_wci.getText().trim().equals("")) shentou_wci.setBackground(new Color(255, 255, 255));
				else shentou_wci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_shentou_wci = new GridBagConstraints();
		gbc_shentou_wci.insets = new Insets(0, 0, 5, 5);
		gbc_shentou_wci.fill = GridBagConstraints.HORIZONTAL;
		gbc_shentou_wci.gridx = 7;
		gbc_shentou_wci.gridy = 5;
		mid_right.add(shentou_wci, gbc_shentou_wci);
		shentou_wci.setColumns(10);
		shentou_wci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		cehou_pci = new JTextField();
		cehou_pci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(cehou_pci.getText().trim().equals("")) cehou_pci.setBackground(new Color(255, 255, 255));
				else cehou_pci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_cehou_pci = new GridBagConstraints();
		gbc_cehou_pci.insets = new Insets(0, 0, 5, 5);
		gbc_cehou_pci.fill = GridBagConstraints.HORIZONTAL;
		gbc_cehou_pci.gridx = 6;
		gbc_cehou_pci.gridy = 6;
		mid_right.add(cehou_pci, gbc_cehou_pci);
		cehou_pci.setColumns(10);
		cehou_pci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		cehou_wci = new JTextField();
		cehou_wci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(cehou_wci.getText().trim().equals("")) cehou_wci.setBackground(new Color(255, 255, 255));
				else cehou_wci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_cehou_wci = new GridBagConstraints();
		gbc_cehou_wci.insets = new Insets(0, 0, 5, 5);
		gbc_cehou_wci.fill = GridBagConstraints.HORIZONTAL;
		gbc_cehou_wci.gridx = 7;
		gbc_cehou_wci.gridy = 6;
		mid_right.add(cehou_wci, gbc_cehou_wci);
		cehou_wci.setColumns(10);
		cehou_wci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		
		JLabel guangpuquan_pge_label = new JLabel("\u4E2A\u5143\u7D20");
		GridBagConstraints gbc_guangpuquan_pge_label = new GridBagConstraints();
		gbc_guangpuquan_pge_label.anchor = GridBagConstraints.WEST;
		gbc_guangpuquan_pge_label.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuquan_pge_label.gridx = 4;
		gbc_guangpuquan_pge_label.gridy = 8;
		mid_right.add(guangpuquan_pge_label, gbc_guangpuquan_pge_label);
		
		
		JLabel biaopei_pri_label = new JLabel("\u5DE5\u65E5");
		GridBagConstraints gbc_biaopei_pri_label = new GridBagConstraints();
		gbc_biaopei_pri_label.anchor = GridBagConstraints.WEST;
		gbc_biaopei_pri_label.insets = new Insets(0, 0, 5, 5);
		gbc_biaopei_pri_label.gridx = 2;
		gbc_biaopei_pri_label.gridy = 7;
		mid_right.add(biaopei_pri_label, gbc_biaopei_pri_label);
		
		JLabel cehou_pdian_label = new JLabel("\u70B9");
		GridBagConstraints gbc_cehou_pdian_label = new GridBagConstraints();
		gbc_cehou_pdian_label.anchor = GridBagConstraints.WEST;
		gbc_cehou_pdian_label.insets = new Insets(0, 0, 5, 5);
		gbc_cehou_pdian_label.gridx = 2;
		gbc_cehou_pdian_label.gridy = 6;
		mid_right.add(cehou_pdian_label, gbc_cehou_pdian_label);
		
		JLabel shentou_pdao_label = new JLabel("\u9053");
		GridBagConstraints gbc_shentou_pdao_label = new GridBagConstraints();
		gbc_shentou_pdao_label.anchor = GridBagConstraints.WEST;
		gbc_shentou_pdao_label.insets = new Insets(0, 0, 5, 5);
		gbc_shentou_pdao_label.gridx = 4;
		gbc_shentou_pdao_label.gridy = 5;
		mid_right.add(shentou_pdao_label, gbc_shentou_pdao_label);
		
		JLabel shentou_pmi_label = new JLabel("\u7C73");
		GridBagConstraints gbc_shentou_pmi_label = new GridBagConstraints();
		gbc_shentou_pmi_label.anchor = GridBagConstraints.WEST;
		gbc_shentou_pmi_label.insets = new Insets(0, 0, 5, 5);
		gbc_shentou_pmi_label.gridx = 2;
		gbc_shentou_pmi_label.gridy = 5;
		mid_right.add(shentou_pmi_label, gbc_shentou_pmi_label);
		
		JLabel cifen_pdao_label = new JLabel("\u9053");
		GridBagConstraints gbc_cifen_pdao_label = new GridBagConstraints();
		gbc_cifen_pdao_label.anchor = GridBagConstraints.WEST;
		gbc_cifen_pdao_label.insets = new Insets(0, 0, 5, 5);
		gbc_cifen_pdao_label.gridx = 4;
		gbc_cifen_pdao_label.gridy = 4;
		mid_right.add(cifen_pdao_label, gbc_cifen_pdao_label);
		
		JLabel cifen_pmi_label = new JLabel("\u7C73");
		GridBagConstraints gbc_cifen_pmi_label = new GridBagConstraints();
		gbc_cifen_pmi_label.anchor = GridBagConstraints.WEST;
		gbc_cifen_pmi_label.insets = new Insets(0, 0, 5, 5);
		gbc_cifen_pmi_label.gridx = 2;
		gbc_cifen_pmi_label.gridy = 4;
		mid_right.add(cifen_pmi_label, gbc_cifen_pmi_label);
		
		JLabel chaosheng_label = new JLabel("\u8D85\u58F0");
		GridBagConstraints gbc_chaosheng_label = new GridBagConstraints();
		gbc_chaosheng_label.anchor = GridBagConstraints.EAST;
		gbc_chaosheng_label.insets = new Insets(0, 0, 5, 5);
		gbc_chaosheng_label.gridx = 0;
		gbc_chaosheng_label.gridy = 3;
		mid_right.add(chaosheng_label, gbc_chaosheng_label);
		
		chaosheng_pmi = new JTextField();
		GridBagConstraints gbc_chaosheng_pmi = new GridBagConstraints();
		gbc_chaosheng_pmi.anchor = GridBagConstraints.EAST;
		gbc_chaosheng_pmi.insets = new Insets(0, 0, 5, 5);
		gbc_chaosheng_pmi.gridx = 1;
		gbc_chaosheng_pmi.gridy = 3;
		mid_right.add(chaosheng_pmi, gbc_chaosheng_pmi);
		chaosheng_pmi.setColumns(3);
		chaosheng_pmi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel chaosheng_pmi_label = new JLabel("\u7C73");
		GridBagConstraints gbc_chaosheng_pmi_label = new GridBagConstraints();
		gbc_chaosheng_pmi_label.anchor = GridBagConstraints.WEST;
		gbc_chaosheng_pmi_label.insets = new Insets(0, 0, 5, 5);
		gbc_chaosheng_pmi_label.gridx = 2;
		gbc_chaosheng_pmi_label.gridy = 3;
		mid_right.add(chaosheng_pmi_label, gbc_chaosheng_pmi_label);
		
		chaosheng_pdao = new JTextField();
		GridBagConstraints gbc_chaosheng_pdao = new GridBagConstraints();
		gbc_chaosheng_pdao.anchor = GridBagConstraints.EAST;
		gbc_chaosheng_pdao.insets = new Insets(0, 0, 5, 5);
		gbc_chaosheng_pdao.gridx = 3;
		gbc_chaosheng_pdao.gridy = 3;
		mid_right.add(chaosheng_pdao, gbc_chaosheng_pdao);
		chaosheng_pdao.setColumns(3);
		chaosheng_pdao.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel chaosheng_pdao_label = new JLabel("\u9053");
		GridBagConstraints gbc_chaosheng_pdao_label = new GridBagConstraints();
		gbc_chaosheng_pdao_label.anchor = GridBagConstraints.WEST;
		gbc_chaosheng_pdao_label.insets = new Insets(0, 0, 5, 5);
		gbc_chaosheng_pdao_label.gridx = 4;
		gbc_chaosheng_pdao_label.gridy = 3;
		mid_right.add(chaosheng_pdao_label, gbc_chaosheng_pdao_label);
		
		JLabel cifen_label = new JLabel("\u78C1\u7C89");
		GridBagConstraints gbc_cifen_label = new GridBagConstraints();
		gbc_cifen_label.anchor = GridBagConstraints.EAST;
		gbc_cifen_label.insets = new Insets(0, 0, 5, 5);
		gbc_cifen_label.gridx = 0;
		gbc_cifen_label.gridy = 4;
		mid_right.add(cifen_label, gbc_cifen_label);
		
		cifen_pmi = new JTextField();
		GridBagConstraints gbc_cifen_pmi = new GridBagConstraints();
		gbc_cifen_pmi.anchor = GridBagConstraints.EAST;
		gbc_cifen_pmi.insets = new Insets(0, 0, 5, 5);
		gbc_cifen_pmi.gridx = 1;
		gbc_cifen_pmi.gridy = 4;
		mid_right.add(cifen_pmi, gbc_cifen_pmi);
		cifen_pmi.setColumns(3);
		cifen_pmi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		cifen_pdao = new JTextField();
		GridBagConstraints gbc_cifen_pdao = new GridBagConstraints();
		gbc_cifen_pdao.anchor = GridBagConstraints.EAST;
		gbc_cifen_pdao.insets = new Insets(0, 0, 5, 5);
		gbc_cifen_pdao.gridx = 3;
		gbc_cifen_pdao.gridy = 4;
		mid_right.add(cifen_pdao, gbc_cifen_pdao);
		cifen_pdao.setColumns(3);
		cifen_pdao.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel shentou_label = new JLabel("\u6E17\u900F");
		GridBagConstraints gbc_shentou_label = new GridBagConstraints();
		gbc_shentou_label.anchor = GridBagConstraints.EAST;
		gbc_shentou_label.insets = new Insets(0, 0, 5, 5);
		gbc_shentou_label.gridx = 0;
		gbc_shentou_label.gridy = 5;
		mid_right.add(shentou_label, gbc_shentou_label);
		
		shentou_pmi = new JTextField();
		GridBagConstraints gbc_shentou_pmi = new GridBagConstraints();
		gbc_shentou_pmi.anchor = GridBagConstraints.EAST;
		gbc_shentou_pmi.insets = new Insets(0, 0, 5, 5);
		gbc_shentou_pmi.gridx = 1;
		gbc_shentou_pmi.gridy = 5;
		mid_right.add(shentou_pmi, gbc_shentou_pmi);
		shentou_pmi.setColumns(3);
		shentou_pmi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		shentou_pdao = new JTextField();
		GridBagConstraints gbc_shentou_pdao = new GridBagConstraints();
		gbc_shentou_pdao.anchor = GridBagConstraints.EAST;
		gbc_shentou_pdao.insets = new Insets(0, 0, 5, 5);
		gbc_shentou_pdao.gridx = 3;
		gbc_shentou_pdao.gridy = 5;
		mid_right.add(shentou_pdao, gbc_shentou_pdao);
		shentou_pdao.setColumns(3);
		shentou_pdao.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel cehou = new JLabel("\u6D4B\u539A");
		GridBagConstraints gbc_cehou = new GridBagConstraints();
		gbc_cehou.anchor = GridBagConstraints.EAST;
		gbc_cehou.insets = new Insets(0, 0, 5, 5);
		gbc_cehou.gridx = 0;
		gbc_cehou.gridy = 6;
		mid_right.add(cehou, gbc_cehou);
		
		cehou_pdian = new JTextField();
		GridBagConstraints gbc_cehou_pdian = new GridBagConstraints();
		gbc_cehou_pdian.anchor = GridBagConstraints.EAST;
		gbc_cehou_pdian.insets = new Insets(0, 0, 5, 5);
		gbc_cehou_pdian.gridx = 1;
		gbc_cehou_pdian.gridy = 6;
		mid_right.add(cehou_pdian, gbc_cehou_pdian);
		cehou_pdian.setColumns(3);
		cehou_pdian.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel biaopei_label = new JLabel("\u8868\u9762\u914D\u5408");
		GridBagConstraints gbc_biaopei_label = new GridBagConstraints();
		gbc_biaopei_label.anchor = GridBagConstraints.EAST;
		gbc_biaopei_label.insets = new Insets(0, 0, 5, 5);
		gbc_biaopei_label.gridx = 0;
		gbc_biaopei_label.gridy = 7;
		mid_right.add(biaopei_label, gbc_biaopei_label);
		
		biaopei_pri = new JTextField();
		GridBagConstraints gbc_biaopei_pri = new GridBagConstraints();
		gbc_biaopei_pri.anchor = GridBagConstraints.EAST;
		gbc_biaopei_pri.insets = new Insets(0, 0, 5, 5);
		gbc_biaopei_pri.gridx = 1;
		gbc_biaopei_pri.gridy = 7;
		mid_right.add(biaopei_pri, gbc_biaopei_pri);
		biaopei_pri.setColumns(3);
		biaopei_pri.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel guangpuquan_label = new JLabel("\u5168\u5B9A\u91CF");
		GridBagConstraints gbc_guangpuquan_label = new GridBagConstraints();
		gbc_guangpuquan_label.anchor = GridBagConstraints.EAST;
		gbc_guangpuquan_label.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuquan_label.gridx = 0;
		gbc_guangpuquan_label.gridy = 8;
		mid_right.add(guangpuquan_label, gbc_guangpuquan_label);
		
		guangpuquan_pdian = new JTextField();
		GridBagConstraints gbc_guangpuquan_pdian = new GridBagConstraints();
		gbc_guangpuquan_pdian.anchor = GridBagConstraints.EAST;
		gbc_guangpuquan_pdian.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuquan_pdian.gridx = 1;
		gbc_guangpuquan_pdian.gridy = 8;
		mid_right.add(guangpuquan_pdian, gbc_guangpuquan_pdian);
		guangpuquan_pdian.setColumns(3);
		guangpuquan_pdian.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel guangpuquan_pdian_label = new JLabel("\u70B9");
		GridBagConstraints gbc_guangpuquan_pdian_label = new GridBagConstraints();
		gbc_guangpuquan_pdian_label.anchor = GridBagConstraints.WEST;
		gbc_guangpuquan_pdian_label.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuquan_pdian_label.gridx = 2;
		gbc_guangpuquan_pdian_label.gridy = 8;
		mid_right.add(guangpuquan_pdian_label, gbc_guangpuquan_pdian_label);
		
		guangpuquan_pge = new JTextField();
		GridBagConstraints gbc_guangpuquan_pge = new GridBagConstraints();
		gbc_guangpuquan_pge.anchor = GridBagConstraints.EAST;
		gbc_guangpuquan_pge.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuquan_pge.gridx = 3;
		gbc_guangpuquan_pge.gridy = 8;
		mid_right.add(guangpuquan_pge, gbc_guangpuquan_pge);
		guangpuquan_pge.setColumns(3);
		guangpuquan_pge.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		guangpuquan_pci = new JTextField();
		guangpuquan_pci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(guangpuquan_pci.getText().trim().equals("")) guangpuquan_pci.setBackground(new Color(255, 255, 255));
				else guangpuquan_pci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_guangpuquan_pci = new GridBagConstraints();
		gbc_guangpuquan_pci.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuquan_pci.fill = GridBagConstraints.HORIZONTAL;
		gbc_guangpuquan_pci.gridx = 6;
		gbc_guangpuquan_pci.gridy = 8;
		mid_right.add(guangpuquan_pci, gbc_guangpuquan_pci);
		guangpuquan_pci.setColumns(10);
		guangpuquan_pci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		guangpuquan_wci = new JTextField();
		guangpuquan_wci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(guangpuquan_wci.getText().trim().equals("")) guangpuquan_wci.setBackground(new Color(255, 255, 255));
				else guangpuquan_wci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_guangpuquan_wci = new GridBagConstraints();
		gbc_guangpuquan_wci.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuquan_wci.fill = GridBagConstraints.HORIZONTAL;
		gbc_guangpuquan_wci.gridx = 7;
		gbc_guangpuquan_wci.gridy = 8;
		mid_right.add(guangpuquan_wci, gbc_guangpuquan_wci);
		guangpuquan_wci.setColumns(10);
		guangpuquan_wci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel guangpuban_label = new JLabel("\u534A\u5B9A\u91CF");
		GridBagConstraints gbc_guangpuban_label = new GridBagConstraints();
		gbc_guangpuban_label.anchor = GridBagConstraints.EAST;
		gbc_guangpuban_label.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuban_label.gridx = 0;
		gbc_guangpuban_label.gridy = 9;
		mid_right.add(guangpuban_label, gbc_guangpuban_label);
		
		guangpuban_pdian = new JTextField();
		GridBagConstraints gbc_guangpuban_pdian = new GridBagConstraints();
		gbc_guangpuban_pdian.anchor = GridBagConstraints.EAST;
		gbc_guangpuban_pdian.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuban_pdian.gridx = 1;
		gbc_guangpuban_pdian.gridy = 9;
		mid_right.add(guangpuban_pdian, gbc_guangpuban_pdian);
		guangpuban_pdian.setColumns(3);
		guangpuban_pdian.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel guangpuban_pdian_label = new JLabel("\u70B9");
		GridBagConstraints gbc_guangpuban_pdian_label = new GridBagConstraints();
		gbc_guangpuban_pdian_label.anchor = GridBagConstraints.WEST;
		gbc_guangpuban_pdian_label.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuban_pdian_label.gridx = 2;
		gbc_guangpuban_pdian_label.gridy = 9;
		mid_right.add(guangpuban_pdian_label, gbc_guangpuban_pdian_label);
		
		guangpuban_pge = new JTextField();
		GridBagConstraints gbc_guangpuban_pge = new GridBagConstraints();
		gbc_guangpuban_pge.anchor = GridBagConstraints.EAST;
		gbc_guangpuban_pge.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuban_pge.gridx = 3;
		gbc_guangpuban_pge.gridy = 9;
		mid_right.add(guangpuban_pge, gbc_guangpuban_pge);
		guangpuban_pge.setColumns(3);
		guangpuban_pge.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel guangpuban_pge_label = new JLabel("\u4E2A\u5143\u7D20");
		GridBagConstraints gbc_guangpuban_pge_label = new GridBagConstraints();
		gbc_guangpuban_pge_label.anchor = GridBagConstraints.WEST;
		gbc_guangpuban_pge_label.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuban_pge_label.gridx = 4;
		gbc_guangpuban_pge_label.gridy = 9;
		mid_right.add(guangpuban_pge_label, gbc_guangpuban_pge_label);
		
		guangpuban_pci = new JTextField();
		guangpuban_pci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(guangpuban_pci.getText().trim().equals("")) guangpuban_pci.setBackground(new Color(255, 255, 255));
				else guangpuban_pci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_guangpuban_pci = new GridBagConstraints();
		gbc_guangpuban_pci.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuban_pci.fill = GridBagConstraints.HORIZONTAL;
		gbc_guangpuban_pci.gridx = 6;
		gbc_guangpuban_pci.gridy = 9;
		mid_right.add(guangpuban_pci, gbc_guangpuban_pci);
		guangpuban_pci.setColumns(10);
		guangpuban_pci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		guangpuban_wci = new JTextField();
		guangpuban_wci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(guangpuban_wci.getText().trim().equals("")) guangpuban_wci.setBackground(new Color(255, 255, 255));
				else guangpuban_wci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_guangpuban_wci = new GridBagConstraints();
		gbc_guangpuban_wci.insets = new Insets(0, 0, 5, 5);
		gbc_guangpuban_wci.fill = GridBagConstraints.HORIZONTAL;
		gbc_guangpuban_wci.gridx = 7;
		gbc_guangpuban_wci.gridy = 9;
		mid_right.add(guangpuban_wci, gbc_guangpuban_wci);
		guangpuban_wci.setColumns(10);
		guangpuban_wci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel yingdu_label = new JLabel("\u786C\u5EA6");
		GridBagConstraints gbc_yingdu_label = new GridBagConstraints();
		gbc_yingdu_label.anchor = GridBagConstraints.EAST;
		gbc_yingdu_label.insets = new Insets(0, 0, 5, 5);
		gbc_yingdu_label.gridx = 0;
		gbc_yingdu_label.gridy = 10;
		mid_right.add(yingdu_label, gbc_yingdu_label);
		
		yingdu_pdian = new JTextField();
		GridBagConstraints gbc_yingdu_pdian = new GridBagConstraints();
		gbc_yingdu_pdian.anchor = GridBagConstraints.EAST;
		gbc_yingdu_pdian.insets = new Insets(0, 0, 5, 5);
		gbc_yingdu_pdian.gridx = 1;
		gbc_yingdu_pdian.gridy = 10;
		mid_right.add(yingdu_pdian, gbc_yingdu_pdian);
		yingdu_pdian.setColumns(3);
		yingdu_pdian.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel yingdu_pdian_label = new JLabel("\u70B9");
		GridBagConstraints gbc_yingdu_pdian_label = new GridBagConstraints();
		gbc_yingdu_pdian_label.anchor = GridBagConstraints.WEST;
		gbc_yingdu_pdian_label.insets = new Insets(0, 0, 5, 5);
		gbc_yingdu_pdian_label.gridx = 2;
		gbc_yingdu_pdian_label.gridy = 10;
		mid_right.add(yingdu_pdian_label, gbc_yingdu_pdian_label);
		
		yingdu_pci = new JTextField();
		yingdu_pci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(yingdu_pci.getText().trim().equals("")) yingdu_pci.setBackground(new Color(255, 255, 255));
				else yingdu_pci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_yingdu_pci = new GridBagConstraints();
		gbc_yingdu_pci.insets = new Insets(0, 0, 5, 5);
		gbc_yingdu_pci.fill = GridBagConstraints.HORIZONTAL;
		gbc_yingdu_pci.gridx = 6;
		gbc_yingdu_pci.gridy = 10;
		mid_right.add(yingdu_pci, gbc_yingdu_pci);
		yingdu_pci.setColumns(10);
		yingdu_pci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		yingdu_wci = new JTextField();
		yingdu_wci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(yingdu_wci.getText().trim().equals("")) yingdu_wci.setBackground(new Color(255, 255, 255));
				else yingdu_wci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_yingdu_wci = new GridBagConstraints();
		gbc_yingdu_wci.insets = new Insets(0, 0, 5, 5);
		gbc_yingdu_wci.fill = GridBagConstraints.HORIZONTAL;
		gbc_yingdu_wci.gridx = 7;
		gbc_yingdu_wci.gridy = 10;
		mid_right.add(yingdu_wci, gbc_yingdu_wci);
		yingdu_wci.setColumns(10);
		yingdu_wci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel damo_label = new JLabel("\u6253\u78E8");
		GridBagConstraints gbc_damo_label = new GridBagConstraints();
		gbc_damo_label.anchor = GridBagConstraints.EAST;
		gbc_damo_label.insets = new Insets(0, 0, 5, 5);
		gbc_damo_label.gridx = 0;
		gbc_damo_label.gridy = 11;
		mid_right.add(damo_label, gbc_damo_label);
		
		damo_pmi = new JTextField();
		GridBagConstraints gbc_damo_pmi = new GridBagConstraints();
		gbc_damo_pmi.anchor = GridBagConstraints.EAST;
		gbc_damo_pmi.insets = new Insets(0, 0, 5, 5);
		gbc_damo_pmi.gridx = 1;
		gbc_damo_pmi.gridy = 11;
		mid_right.add(damo_pmi, gbc_damo_pmi);
		damo_pmi.setColumns(3);
		damo_pmi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel damo_pmi_label = new JLabel("\u7C73");
		GridBagConstraints gbc_damo_pmi_label = new GridBagConstraints();
		gbc_damo_pmi_label.anchor = GridBagConstraints.WEST;
		gbc_damo_pmi_label.insets = new Insets(0, 0, 5, 5);
		gbc_damo_pmi_label.gridx = 2;
		gbc_damo_pmi_label.gridy = 11;
		mid_right.add(damo_pmi_label, gbc_damo_pmi_label);
		
		damo_pci = new JTextField();
		damo_pci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(damo_pci.getText().trim().equals("")) damo_pci.setBackground(new Color(255, 255, 255));
				else damo_pci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_damo_pci = new GridBagConstraints();
		gbc_damo_pci.insets = new Insets(0, 0, 5, 5);
		gbc_damo_pci.fill = GridBagConstraints.HORIZONTAL;
		gbc_damo_pci.gridx = 6;
		gbc_damo_pci.gridy = 11;
		mid_right.add(damo_pci, gbc_damo_pci);
		damo_pci.setColumns(10);
		damo_pci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		damo_wci = new JTextField();
		damo_wci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(damo_wci.getText().trim().equals("")) damo_wci.setBackground(new Color(255, 255, 255));
				else damo_wci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_damo_wci = new GridBagConstraints();
		gbc_damo_wci.insets = new Insets(0, 0, 5, 5);
		gbc_damo_wci.fill = GridBagConstraints.HORIZONTAL;
		gbc_damo_wci.gridx = 7;
		gbc_damo_wci.gridy = 11;
		mid_right.add(damo_wci, gbc_damo_wci);
		damo_wci.setColumns(10);
		damo_wci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel tofd_label = new JLabel("TOFD");
		GridBagConstraints gbc_tofd_label = new GridBagConstraints();
		gbc_tofd_label.anchor = GridBagConstraints.EAST;
		gbc_tofd_label.insets = new Insets(0, 0, 5, 5);
		gbc_tofd_label.gridx = 0;
		gbc_tofd_label.gridy = 12;
		mid_right.add(tofd_label, gbc_tofd_label);
		
		tofd_pmi = new JTextField();
		GridBagConstraints gbc_tofd_pmi = new GridBagConstraints();
		gbc_tofd_pmi.anchor = GridBagConstraints.EAST;
		gbc_tofd_pmi.insets = new Insets(0, 0, 5, 5);
		gbc_tofd_pmi.gridx = 1;
		gbc_tofd_pmi.gridy = 12;
		mid_right.add(tofd_pmi, gbc_tofd_pmi);
		tofd_pmi.setColumns(3);
		tofd_pmi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel tofd_pmi_label = new JLabel("\u7C73");
		GridBagConstraints gbc_tofd_pmi_label = new GridBagConstraints();
		gbc_tofd_pmi_label.anchor = GridBagConstraints.WEST;
		gbc_tofd_pmi_label.insets = new Insets(0, 0, 5, 5);
		gbc_tofd_pmi_label.gridx = 2;
		gbc_tofd_pmi_label.gridy = 12;
		mid_right.add(tofd_pmi_label, gbc_tofd_pmi_label);
		
		tofd_pci = new JTextField();
		tofd_pci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(tofd_pci.getText().trim().equals("")) tofd_pci.setBackground(new Color(255, 255, 255));
				else tofd_pci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_tofd_pci = new GridBagConstraints();
		gbc_tofd_pci.insets = new Insets(0, 0, 5, 5);
		gbc_tofd_pci.fill = GridBagConstraints.HORIZONTAL;
		gbc_tofd_pci.gridx = 6;
		gbc_tofd_pci.gridy = 12;
		mid_right.add(tofd_pci, gbc_tofd_pci);
		tofd_pci.setColumns(10);
		tofd_pci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		tofd_wci = new JTextField();
		tofd_wci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(tofd_wci.getText().trim().equals("")) tofd_wci.setBackground(new Color(255, 255, 255));
				else tofd_wci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_tofd_wci = new GridBagConstraints();
		gbc_tofd_wci.insets = new Insets(0, 0, 5, 5);
		gbc_tofd_wci.fill = GridBagConstraints.HORIZONTAL;
		gbc_tofd_wci.gridx = 7;
		gbc_tofd_wci.gridy = 12;
		mid_right.add(tofd_wci, gbc_tofd_wci);
		tofd_wci.setColumns(10);
		tofd_wci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel lashen_label = new JLabel("\u62C9\u4F38");
		GridBagConstraints gbc_lashen_label = new GridBagConstraints();
		gbc_lashen_label.anchor = GridBagConstraints.EAST;
		gbc_lashen_label.insets = new Insets(0, 0, 5, 5);
		gbc_lashen_label.gridx = 0;
		gbc_lashen_label.gridy = 13;
		mid_right.add(lashen_label, gbc_lashen_label);
		
		lashen_pjian = new JTextField();
		GridBagConstraints gbc_lashen_pjian = new GridBagConstraints();
		gbc_lashen_pjian.anchor = GridBagConstraints.EAST;
		gbc_lashen_pjian.insets = new Insets(0, 0, 5, 5);
		gbc_lashen_pjian.gridx = 1;
		gbc_lashen_pjian.gridy = 13;
		mid_right.add(lashen_pjian, gbc_lashen_pjian);
		lashen_pjian.setColumns(3);
		lashen_pjian.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel lashen_pjian_label = new JLabel("\u4EF6");
		GridBagConstraints gbc_lashen_pjian_label = new GridBagConstraints();
		gbc_lashen_pjian_label.anchor = GridBagConstraints.WEST;
		gbc_lashen_pjian_label.insets = new Insets(0, 0, 5, 5);
		gbc_lashen_pjian_label.gridx = 2;
		gbc_lashen_pjian_label.gridy = 13;
		mid_right.add(lashen_pjian_label, gbc_lashen_pjian_label);
		
		lashen_pci = new JTextField();
		lashen_pci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(lashen_pci.getText().trim().equals("")) lashen_pci.setBackground(new Color(255, 255, 255));
				else lashen_pci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_lashen_pci = new GridBagConstraints();
		gbc_lashen_pci.insets = new Insets(0, 0, 5, 5);
		gbc_lashen_pci.fill = GridBagConstraints.HORIZONTAL;
		gbc_lashen_pci.gridx = 6;
		gbc_lashen_pci.gridy = 13;
		mid_right.add(lashen_pci, gbc_lashen_pci);
		lashen_pci.setColumns(10);
		lashen_pci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		lashen_wci = new JTextField();
		lashen_wci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(lashen_wci.getText().trim().equals("")) lashen_wci.setBackground(new Color(255, 255, 255));
				else lashen_wci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_lashen_wci = new GridBagConstraints();
		gbc_lashen_wci.insets = new Insets(0, 0, 5, 5);
		gbc_lashen_wci.fill = GridBagConstraints.HORIZONTAL;
		gbc_lashen_wci.gridx = 7;
		gbc_lashen_wci.gridy = 13;
		mid_right.add(lashen_wci, gbc_lashen_wci);
		lashen_wci.setColumns(10);
		lashen_wci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel wanqu_label = new JLabel("\u5F2F\u66F2");
		GridBagConstraints gbc_wanqu_label = new GridBagConstraints();
		gbc_wanqu_label.anchor = GridBagConstraints.EAST;
		gbc_wanqu_label.insets = new Insets(0, 0, 5, 5);
		gbc_wanqu_label.gridx = 0;
		gbc_wanqu_label.gridy = 14;
		mid_right.add(wanqu_label, gbc_wanqu_label);
		
		wanqu_pjian = new JTextField();
		GridBagConstraints gbc_wanqu_pjian = new GridBagConstraints();
		gbc_wanqu_pjian.anchor = GridBagConstraints.EAST;
		gbc_wanqu_pjian.insets = new Insets(0, 0, 5, 5);
		gbc_wanqu_pjian.gridx = 1;
		gbc_wanqu_pjian.gridy = 14;
		mid_right.add(wanqu_pjian, gbc_wanqu_pjian);
		wanqu_pjian.setColumns(3);
		wanqu_pjian.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel wanqu_pjian_label = new JLabel("\u4EF6");
		GridBagConstraints gbc_wanqu_pjian_label = new GridBagConstraints();
		gbc_wanqu_pjian_label.anchor = GridBagConstraints.WEST;
		gbc_wanqu_pjian_label.insets = new Insets(0, 0, 5, 5);
		gbc_wanqu_pjian_label.gridx = 2;
		gbc_wanqu_pjian_label.gridy = 14;
		mid_right.add(wanqu_pjian_label, gbc_wanqu_pjian_label);
		
		wanqu_pci = new JTextField();
		wanqu_pci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(wanqu_pci.getText().trim().equals("")) wanqu_pci.setBackground(new Color(255, 255, 255));
				else wanqu_pci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_wanqu_pci = new GridBagConstraints();
		gbc_wanqu_pci.insets = new Insets(0, 0, 5, 5);
		gbc_wanqu_pci.fill = GridBagConstraints.HORIZONTAL;
		gbc_wanqu_pci.gridx = 6;
		gbc_wanqu_pci.gridy = 14;
		mid_right.add(wanqu_pci, gbc_wanqu_pci);
		wanqu_pci.setColumns(10);
		wanqu_pci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		wanqu_wci = new JTextField();
		wanqu_wci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(wanqu_wci.getText().trim().equals("")) wanqu_wci.setBackground(new Color(255, 255, 255));
				else wanqu_wci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_wanqu_wci = new GridBagConstraints();
		gbc_wanqu_wci.insets = new Insets(0, 0, 5, 5);
		gbc_wanqu_wci.fill = GridBagConstraints.HORIZONTAL;
		gbc_wanqu_wci.gridx = 7;
		gbc_wanqu_wci.gridy = 14;
		mid_right.add(wanqu_wci, gbc_wanqu_wci);
		wanqu_wci.setColumns(10);
		wanqu_wci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel chongji_label = new JLabel("\u51B2\u51FB");
		GridBagConstraints gbc_chongji_label = new GridBagConstraints();
		gbc_chongji_label.anchor = GridBagConstraints.EAST;
		gbc_chongji_label.insets = new Insets(0, 0, 5, 5);
		gbc_chongji_label.gridx = 0;
		gbc_chongji_label.gridy = 15;
		mid_right.add(chongji_label, gbc_chongji_label);
		
		chongji_pjian = new JTextField();
		GridBagConstraints gbc_chongji_pjian = new GridBagConstraints();
		gbc_chongji_pjian.anchor = GridBagConstraints.EAST;
		gbc_chongji_pjian.insets = new Insets(0, 0, 5, 5);
		gbc_chongji_pjian.gridx = 1;
		gbc_chongji_pjian.gridy = 15;
		mid_right.add(chongji_pjian, gbc_chongji_pjian);
		chongji_pjian.setColumns(3);
		chongji_pjian.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel chongji_pjian_label = new JLabel("\u4EF6");
		GridBagConstraints gbc_chongji_pjian_label = new GridBagConstraints();
		gbc_chongji_pjian_label.anchor = GridBagConstraints.WEST;
		gbc_chongji_pjian_label.insets = new Insets(0, 0, 5, 5);
		gbc_chongji_pjian_label.gridx = 2;
		gbc_chongji_pjian_label.gridy = 15;
		mid_right.add(chongji_pjian_label, gbc_chongji_pjian_label);
		
		chongji_pci = new JTextField();
		chongji_pci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(chongji_pci.getText().trim().equals("")) chongji_pci.setBackground(new Color(255, 255, 255));
				else chongji_pci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_chongji_pci = new GridBagConstraints();
		gbc_chongji_pci.insets = new Insets(0, 0, 5, 5);
		gbc_chongji_pci.fill = GridBagConstraints.HORIZONTAL;
		gbc_chongji_pci.gridx = 6;
		gbc_chongji_pci.gridy = 15;
		mid_right.add(chongji_pci, gbc_chongji_pci);
		chongji_pci.setColumns(10);
		chongji_pci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		chongji_wci = new JTextField();
		chongji_wci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(chongji_wci.getText().trim().equals("")) chongji_wci.setBackground(new Color(255, 255, 255));
				else chongji_wci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_chongji_wci = new GridBagConstraints();
		gbc_chongji_wci.insets = new Insets(0, 0, 5, 5);
		gbc_chongji_wci.fill = GridBagConstraints.HORIZONTAL;
		gbc_chongji_wci.gridx = 7;
		gbc_chongji_wci.gridy = 15;
		mid_right.add(chongji_wci, gbc_chongji_wci);
		chongji_wci.setColumns(10);
		chongji_wci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel tie_label = new JLabel("\u94C1\u7D20\u4F53");
		GridBagConstraints gbc_tie_label = new GridBagConstraints();
		gbc_tie_label.anchor = GridBagConstraints.EAST;
		gbc_tie_label.insets = new Insets(0, 0, 5, 5);
		gbc_tie_label.gridx = 0;
		gbc_tie_label.gridy = 16;
		mid_right.add(tie_label, gbc_tie_label);
		
		tie_pdian = new JTextField();
		GridBagConstraints gbc_tie_pdian = new GridBagConstraints();
		gbc_tie_pdian.anchor = GridBagConstraints.EAST;
		gbc_tie_pdian.insets = new Insets(0, 0, 5, 5);
		gbc_tie_pdian.gridx = 1;
		gbc_tie_pdian.gridy = 16;
		mid_right.add(tie_pdian, gbc_tie_pdian);
		tie_pdian.setColumns(3);
		tie_pdian.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel tie_pdian_label = new JLabel("\u70B9");
		GridBagConstraints gbc_tie_pdian_label = new GridBagConstraints();
		gbc_tie_pdian_label.anchor = GridBagConstraints.WEST;
		gbc_tie_pdian_label.insets = new Insets(0, 0, 5, 5);
		gbc_tie_pdian_label.gridx = 2;
		gbc_tie_pdian_label.gridy = 16;
		mid_right.add(tie_pdian_label, gbc_tie_pdian_label);
		
		tie_pci = new JTextField();
		tie_pci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(tie_pci.getText().trim().equals("")) tie_pci.setBackground(new Color(255, 255, 255));
				else tie_pci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_tie_pci = new GridBagConstraints();
		gbc_tie_pci.insets = new Insets(0, 0, 5, 5);
		gbc_tie_pci.fill = GridBagConstraints.HORIZONTAL;
		gbc_tie_pci.gridx = 6;
		gbc_tie_pci.gridy = 16;
		mid_right.add(tie_pci, gbc_tie_pci);
		tie_pci.setColumns(10);
		tie_pci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		tie_wci = new JTextField();
		tie_wci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(tie_wci.getText().trim().equals("")) tie_wci.setBackground(new Color(255, 255, 255));
				else tie_wci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_tie_wci = new GridBagConstraints();
		gbc_tie_wci.insets = new Insets(0, 0, 5, 5);
		gbc_tie_wci.fill = GridBagConstraints.HORIZONTAL;
		gbc_tie_wci.gridx = 7;
		gbc_tie_wci.gridy = 16;
		mid_right.add(tie_wci, gbc_tie_wci);
		tie_wci.setColumns(10);
		tie_wci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel jinxiang_label = new JLabel("\u91D1\u76F8");
		GridBagConstraints gbc_jinxiang_label = new GridBagConstraints();
		gbc_jinxiang_label.anchor = GridBagConstraints.EAST;
		gbc_jinxiang_label.insets = new Insets(0, 0, 5, 5);
		gbc_jinxiang_label.gridx = 0;
		gbc_jinxiang_label.gridy = 17;
		mid_right.add(jinxiang_label, gbc_jinxiang_label);
		
		jinxiang_pjian = new JTextField();
		GridBagConstraints gbc_jinxiang_pjian = new GridBagConstraints();
		gbc_jinxiang_pjian.anchor = GridBagConstraints.EAST;
		gbc_jinxiang_pjian.insets = new Insets(0, 0, 5, 5);
		gbc_jinxiang_pjian.gridx = 1;
		gbc_jinxiang_pjian.gridy = 17;
		mid_right.add(jinxiang_pjian, gbc_jinxiang_pjian);
		jinxiang_pjian.setColumns(3);
		jinxiang_pjian.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel jinxiang_pjian_label = new JLabel("\u4EF6");
		GridBagConstraints gbc_jinxiang_pjian_label = new GridBagConstraints();
		gbc_jinxiang_pjian_label.anchor = GridBagConstraints.WEST;
		gbc_jinxiang_pjian_label.insets = new Insets(0, 0, 5, 5);
		gbc_jinxiang_pjian_label.gridx = 2;
		gbc_jinxiang_pjian_label.gridy = 17;
		mid_right.add(jinxiang_pjian_label, gbc_jinxiang_pjian_label);
		
		jinxiang_pci = new JTextField();
		jinxiang_pci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(jinxiang_pci.getText().trim().equals("")) jinxiang_pci.setBackground(new Color(255, 255, 255));
				else jinxiang_pci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_jinxiang_pci = new GridBagConstraints();
		gbc_jinxiang_pci.insets = new Insets(0, 0, 5, 5);
		gbc_jinxiang_pci.fill = GridBagConstraints.HORIZONTAL;
		gbc_jinxiang_pci.gridx = 6;
		gbc_jinxiang_pci.gridy = 17;
		mid_right.add(jinxiang_pci, gbc_jinxiang_pci);
		jinxiang_pci.setColumns(10);
		jinxiang_pci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		jinxiang_wci = new JTextField();
		jinxiang_wci.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(jinxiang_wci.getText().trim().equals("")) jinxiang_wci.setBackground(new Color(255, 255, 255));
				else jinxiang_wci.setBackground(new Color(255, 255, 0));
			}
		});
		GridBagConstraints gbc_jinxiang_wci = new GridBagConstraints();
		gbc_jinxiang_wci.insets = new Insets(0, 0, 5, 5);
		gbc_jinxiang_wci.fill = GridBagConstraints.HORIZONTAL;
		gbc_jinxiang_wci.gridx = 7;
		gbc_jinxiang_wci.gridy = 17;
		mid_right.add(jinxiang_wci, gbc_jinxiang_wci);
		jinxiang_wci.setColumns(10);
		jinxiang_wci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel diankou_label = new JLabel("\u70B9\u53E3/\u7279\u914D");
		GridBagConstraints gbc_diankou_label = new GridBagConstraints();
		gbc_diankou_label.anchor = GridBagConstraints.EAST;
		gbc_diankou_label.insets = new Insets(0, 0, 5, 5);
		gbc_diankou_label.gridx = 0;
		gbc_diankou_label.gridy = 18;
		mid_right.add(diankou_label, gbc_diankou_label);
		
		diankou_pamount = new JTextField();
		GridBagConstraints gbc_diankou_pamount = new GridBagConstraints();
		gbc_diankou_pamount.anchor = GridBagConstraints.EAST;
		gbc_diankou_pamount.insets = new Insets(0, 0, 5, 5);
		gbc_diankou_pamount.gridx = 1;
		gbc_diankou_pamount.gridy = 18;
		mid_right.add(diankou_pamount, gbc_diankou_pamount);
		diankou_pamount.setColumns(3);
		diankou_pamount.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel diankou_pamount_label = new JLabel("\u5929");
		GridBagConstraints gbc_diankou_pamount_label = new GridBagConstraints();
		gbc_diankou_pamount_label.anchor = GridBagConstraints.WEST;
		gbc_diankou_pamount_label.insets = new Insets(0, 0, 5, 5);
		gbc_diankou_pamount_label.gridx = 2;
		gbc_diankou_pamount_label.gridy = 18;
		mid_right.add(diankou_pamount_label, gbc_diankou_pamount_label);
		
		JLabel qita1_label = new JLabel("\u5176\u4ED61");
		qita1_label.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_qita1_label = new GridBagConstraints();
		gbc_qita1_label.anchor = GridBagConstraints.EAST;
		gbc_qita1_label.insets = new Insets(0, 0, 5, 5);
		gbc_qita1_label.gridx = 0;
		gbc_qita1_label.gridy = 19;
		mid_right.add(qita1_label, gbc_qita1_label);
		
		qita1_pamount = new JTextField();
		GridBagConstraints gbc_qita1_pamount = new GridBagConstraints();
		gbc_qita1_pamount.anchor = GridBagConstraints.EAST;
		gbc_qita1_pamount.insets = new Insets(0, 0, 5, 5);
		gbc_qita1_pamount.gridx = 1;
		gbc_qita1_pamount.gridy = 19;
		mid_right.add(qita1_pamount, gbc_qita1_pamount);
		qita1_pamount.setColumns(3);
		qita1_pamount.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel qita2_label = new JLabel("\u5176\u4ED62");
		GridBagConstraints gbc_qita2_label = new GridBagConstraints();
		gbc_qita2_label.anchor = GridBagConstraints.EAST;
		gbc_qita2_label.insets = new Insets(0, 0, 5, 5);
		gbc_qita2_label.gridx = 2;
		gbc_qita2_label.gridy = 19;
		mid_right.add(qita2_label, gbc_qita2_label);
		
		qita2_pamount = new JTextField();
		GridBagConstraints gbc_qita2_pamount = new GridBagConstraints();
		gbc_qita2_pamount.anchor = GridBagConstraints.WEST;
		gbc_qita2_pamount.insets = new Insets(0, 0, 5, 5);
		gbc_qita2_pamount.gridx = 3;
		gbc_qita2_pamount.gridy = 19;
		mid_right.add(qita2_pamount, gbc_qita2_pamount);
		qita2_pamount.setColumns(3);
		qita2_pamount.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		JLabel qita3_label = new JLabel("\u5176\u4ED63");
		GridBagConstraints gbc_qita3_label = new GridBagConstraints();
		gbc_qita3_label.anchor = GridBagConstraints.WEST;
		gbc_qita3_label.insets = new Insets(0, 0, 5, 5);
		gbc_qita3_label.gridx = 4;
		gbc_qita3_label.gridy = 19;
		mid_right.add(qita3_label, gbc_qita3_label);
		
		qita3_pamount = new JTextField();
		GridBagConstraints gbc_qita3_pamount = new GridBagConstraints();
		gbc_qita3_pamount.anchor = GridBagConstraints.WEST;
		gbc_qita3_pamount.insets = new Insets(0, 0, 5, 5);
		gbc_qita3_pamount.gridx = 5;
		gbc_qita3_pamount.gridy = 19;
		mid_right.add(qita3_pamount, gbc_qita3_pamount);
		qita3_pamount.setColumns(3);
		qita3_pamount.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				toField(e);
			}
		});
		
		
		
		
		//TODO ��������
		top.setBorder(new TitledBorder(null, "\u9009\u62E9\u6307\u4EE4\u5355\u53F7\u6216\u8005\u5DE5\u7A0B\u540D\u79F0\u548C\u59D4\u6258\u5355\u4F4D", TitledBorder.LEFT, TitledBorder.TOP, null, Color.RED));
		add(top, BorderLayout.NORTH);
		GridBagLayout gbl_top = new GridBagLayout();
		gbl_top.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_top.rowHeights = new int[]{0, 0};
		gbl_top.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_top.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		top.setLayout(gbl_top);
		
		//ָ���label
		JLabel label0 = new JLabel("\u6307\u4EE4\u5355\u53F7");
		label0.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_label0 = new GridBagConstraints();
		gbc_label0.insets = new Insets(0, 0, 0, 5);
		gbc_label0.gridx = 0;
		gbc_label0.gridy = 0;
		top.add(label0, gbc_label0);
		
		//ί�е�λlabel
		JLabel label1 = new JLabel("\u59D4\u6258\u5355\u4F4D");
		GridBagConstraints gbc_label1 = new GridBagConstraints();
		gbc_label1.insets = new Insets(0, 0, 0, 5);
		gbc_label1.gridx = 2;
		gbc_label1.gridy = 0;
		top.add(label1, gbc_label1);
		
		//��Ŀ����label
		JLabel label2 = new JLabel("\u5DE5\u7A0B\u540D\u79F0");
		GridBagConstraints gbc_label2 = new GridBagConstraints();
		gbc_label2.insets = new Insets(0, 0, 0, 5);
		gbc_label2.gridx = 4;
		gbc_label2.gridy = 0;
		top.add(label2, gbc_label2);
		
		//�հ�label
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
		
		//ָ���
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
				// TODO ʧȥ������ί�е�λ�͹����������ѯ����
				cb1.setModel( (DefaultComboBoxModel<String>)sm.search(3, 0));
				cb1.setSelectedItem((String)sm.search(1,2,new String[]{cb0.getText()}));
				cb2.setModel( (DefaultComboBoxModel<String>)sm.search(4, 0));
				cb2.setSelectedItem((String)sm.search(2,2,new String[]{cb0.getText()}));
			}

		});
		
		//ί�е�λ
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
				// ɸѡ���������Ĺ�������
				if((String)cb1.getSelectedItem() != ""){
				cb2.setModel((DefaultComboBoxModel<String>)sm.search(5, 0,new String[]{(String)cb1.getSelectedItem()}));
				}else{
					cb2.setModel( (DefaultComboBoxModel<String>)sm.search(4, 0));
				}				
			}		
		});
		
		
		//��Ŀ����
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
				// ɸѡָ���
				if((String)cb1.getSelectedItem() != "" && cb1.getSelectedItem()!=""){
				cb0.setText((String)sm.search(7,2,new String[]{(String)cb1.getSelectedItem(),(String)cb2.getSelectedItem()}));
				}			
	     	}		
		});		
		
	
		
		
		JPanel bottom = new JPanel();
		add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel bottom_space1 = new JLabel("                    ");
		bottom.add(bottom_space1);
	
		//TODO ���빤�������ť
		JButton dataJB = new JButton("\u63D2\u5165\u5DE5\u4F5C\u4EBA\u5458\u5DE5\u4F5C\u91CF");
		bottom.add(dataJB);
		dataJB.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			// �ύ������Ա�����������ݿ�
			insetToWorkload();

			}
			
		});
				
			
		JLabel bottom_space2 = new JLabel("                   ");
		bottom.add(bottom_space2);
		
		JButton resetJB = new JButton("\u91CD\u7F6E");
		bottom.add(resetJB);
		resetJB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO ���ð�ť
				renewAction();
				workdate.setText("");x_pzhang.setText(""); x_pci.setText("");x_wci.setText(""); y_pzhang.setText(""); y_pci.setText("");y_wci.setText("");chaosheng_pmi.setText(""); chaosheng_pdao.setText(""); chaosheng_pci.setText(""); chaosheng_wci.setText("");cifen_pmi.setText(""); cifen_pdao.setText(""); cifen_pci.setText("");cifen_wci.setText(""); shentou_pmi.setText(""); shentou_pdao.setText(""); shentou_pci.setText("");shentou_wci.setText(""); cehou_pdian.setText(""); cehou_pci.setText("");cehou_wci.setText(""); biaopei_pri.setText(""); guangpuquan_pdian.setText(""); guangpuquan_pge.setText(""); guangpuquan_pci.setText("");guangpuquan_wci.setText(""); guangpuban_pdian.setText(""); guangpuban_pge.setText(""); guangpuban_pci.setText("");guangpuban_wci.setText(""); yingdu_pdian.setText(""); yingdu_pci.setText("");yingdu_wci.setText(""); damo_pmi.setText(""); damo_pci.setText("");damo_wci.setText(""); tofd_pmi.setText(""); tofd_pci.setText("");tofd_wci.setText(""); lashen_pjian.setText(""); lashen_pci.setText("");lashen_wci.setText(""); wanqu_pjian.setText(""); wanqu_pci.setText("");wanqu_wci.setText("");chongji_pjian.setText(""); chongji_pci.setText("");chongji_wci.setText(""); tie_pdian.setText(""); tie_pci.setText("");tie_wci.setText(""); jinxiang_pjian.setText(""); jinxiang_pci.setText("");jinxiang_wci.setText(""); diankou_pamount.setText(""); qita1_pamount.setText(""); qita2_pamount.setText(""); qita3_pamount.setText(""); shiwu_beizhu.setText("");
				xipian1.setText("");xipian2.setText("");xipian3.setText("");
				xipian_total.setText("");
			}
			
		});
		
		JLabel bottom_space3 = new JLabel("              ");
		bottom.add(bottom_space3);
		
        //TODO ˢ�°�ť
		JButton renewJB = new JButton("\u5237\u65B0");
		renewJB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO ���»�ȡ�����������ݵ����ð�ť
				renewAction();			
			}

		});
		bottom.add(renewJB);

		
		

	}
	
	private void toField(FocusEvent e){
		calTF = (JTextField)e.getComponent();
		
	}
	
	private void caculate_xipian() {
		 if(jilu_num.getText().trim().equals("")){JOptionPane.showMessageDialog(null, "���������¼���", "ʧ��", JOptionPane.ERROR_MESSAGE);return ;}
		 String sql = "select x_pzhang , y_pzhang from workload_records_table where jilu_num='" + jilu_num.getText().trim() + "'";
		 ResultSet Caculaters =  sm.submit_search(sql) ;
		 double caldb =0.0;
		 try {
			while(Caculaters.next()){
				caldb += Caculaters.getDouble(1) + Caculaters.getDouble(2);
			}
			xipian_total.setText(Double.toString(caldb));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	
	private int caculate_equal(){
		if( !pattern.matcher(x_pzhang.getText()).matches()  && (!x_pzhang.getText().equals("")) ) return 0 ;
		if( !pattern.matcher(y_pzhang.getText()).matches()  && (!y_pzhang.getText().equals("")) ) return 0 ;
		if( !pattern.matcher(xipian1.getText()).matches() && (!xipian1.getText().equals("")) ) return 0 ;
		if( !pattern.matcher(xipian2.getText()).matches() && (!xipian2.getText().equals("")) ) return 0 ;
		if( !pattern.matcher(xipian3.getText()).matches() && (!xipian3.getText().equals("")) ) return 0 ;
		
		
		BigDecimal bd1 = new BigDecimal(pattern.matcher(x_pzhang.getText()).matches()?x_pzhang.getText():"0");
		BigDecimal bd2 = new BigDecimal(pattern.matcher(y_pzhang.getText()).matches()?y_pzhang.getText():"0");
		
		BigDecimal bd3 = new BigDecimal(pattern.matcher(xipian1.getText()).matches()?xipian1.getText():"0");
		BigDecimal bd4 = new BigDecimal(pattern.matcher(xipian2.getText()).matches()?xipian2.getText():"0");
		BigDecimal bd5 = new BigDecimal(pattern.matcher(xipian3.getText()).matches()?xipian3.getText():"0");
		
		if(  bd1.add(bd2).equals(bd3.add(bd4).add(bd5))   ) return 1;
		else return 0 ;
	}
	
	
	
	
	//���������
	private int insetToWorkload(){
		int op = JOptionPane.showConfirmDialog(this, "ȷ����Ӹù�������", "ȷ��", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(op == JOptionPane.CANCEL_OPTION || op == JOptionPane.CLOSED_OPTION ) return 1;
		
		String[] outputpaipian = new String[72];
		//������Ƭ����ϴƬ������Ƿ����
		if(caculate_equal() == 0 ) {JOptionPane.showMessageDialog(null, "����������Ƭ������Ȼ���ڷǷ��ַ�", "ʧ��", JOptionPane.ERROR_MESSAGE);return 1;}
		
		//0����ָ��ţ�����Ϊ��
		outputpaipian[0] = cb0.getText().trim();
		if(outputpaipian[0].equals("")) {JOptionPane.showMessageDialog(null, "������ָ���", "ʧ��", JOptionPane.ERROR_MESSAGE);return 1;}
		//1��������Ա����Ϊ��
		outputpaipian[1] = ((String)workperson.getSelectedItem()) ==null? "":(String)workperson.getSelectedItem();
		if(outputpaipian[1] == "" ) {JOptionPane.showMessageDialog(null, "�����빤����Ա", "ʧ��", JOptionPane.ERROR_MESSAGE);return 2;}
		//2���������ڣ�����Ϊ�գ����и�ʽ��顣���Ϊ�ջ��߸�ʽ��ƥ��
		outputpaipian[2] = workdate.getText().trim();
		if(outputpaipian[2].equals("") || (!pattern2.matcher(outputpaipian[2]).matches()) ) {JOptionPane.showMessageDialog(null, "����ʱ�䲻��Ϊ���Ҹ�ʽӦΪ���磺20160101", "ʧ��", JOptionPane.ERROR_MESSAGE);return 3;}	
		//3����x��/��
		outputpaipian[3] = x_pzhang.getText().trim();
		//4����x���Ƭ
		outputpaipian[4] = x_pfei.getText().trim();
		//5����y/��
		outputpaipian[5] = y_pzhang.getText().trim();
		//6����y/��Ƭ
		outputpaipian[6] = y_pfei.getText().trim();
		//7������/��
		outputpaipian[7] = chaosheng_pmi.getText().trim();
		//8������/��
		outputpaipian[8] = chaosheng_pdao.getText().trim();
		//9����ŷ�/��
		outputpaipian[9] = cifen_pmi.getText().trim();
		//10����ŷ�/��
		outputpaipian[10] = cifen_pdao.getText().trim();	
		//11������͸/��
		outputpaipian[11] = shentou_pmi.getText().trim();
		//12������͸/��
		outputpaipian[12] = shentou_pdao.getText().trim();
		//13������/��
		outputpaipian[13] = cehou_pdian.getText().trim();
		//14����������/����
		outputpaipian[14] = biaopei_pri.getText().trim();
		//15����ȫ����/��
		outputpaipian[15] = guangpuquan_pdian.getText().trim();
		//16����ȫ����/��Ԫ��
		outputpaipian[16] = guangpuquan_pge.getText().trim();
		//17����붨��/��
		outputpaipian[17] = guangpuban_pdian.getText().trim();
		//18����붨��/��Ԫ��
		outputpaipian[18] = guangpuban_pge.getText().trim();
		//19����Ӳ��/��
		outputpaipian[19] = yingdu_pdian.getText().trim();
		//20�����ĥ/��
		outputpaipian[20] = damo_pmi.getText().trim();
		//21����tofd/��
		outputpaipian[21] = tofd_pmi.getText().trim();
		//22��������/��
		outputpaipian[22] = lashen_pjian.getText().trim();
		//23��������/��
		outputpaipian[23] = wanqu_pjian.getText().trim();
		//24������/��
		outputpaipian[24] = chongji_pjian.getText().trim();
		//25����������/��
		outputpaipian[25] = tie_pdian.getText().trim();
		//26�������/��
		outputpaipian[26] = jinxiang_pjian.getText().trim();
		//27�����ڡ��ؼ�Ժ��ϡ�����ָ�� ����
		outputpaipian[27] = diankou_pamount.getText().trim();
		//28����������Ŀ1����
		outputpaipian[28] = qita1_pamount.getText().trim();
		//29����������Ŀ2����
		outputpaipian[29] = qita2_pamount.getText().trim();
		//30����������Ŀ3����
		outputpaipian[30] = qita3_pamount.getText().trim();
		//31����ע
		outputpaipian[31] = shiwu_beizhu.getText().trim();
		//32����x/����
		outputpaipian[32] = x_pci.getText().trim();
		//33����x/����
		outputpaipian[33] = x_wci.getText().trim();
		//34����y/����
		outputpaipian[34] = y_pci.getText().trim();
		//35����y/����
		outputpaipian[35] = y_wci.getText().trim();
		//36������/����
		outputpaipian[36] = chaosheng_pci.getText().trim();
		//37������/����
		outputpaipian[37] = chaosheng_wci.getText().trim();
		//38����ŷ�/����
		outputpaipian[38] = cifen_pci.getText().trim();
		//39����ŷ�/����
		outputpaipian[39] = cifen_wci.getText().trim();
		//40������͸/����
		outputpaipian[40] = shentou_pci.getText().trim();
		//41������͸/����
		outputpaipian[41] = shentou_wci.getText().trim();
		//42������/����
		outputpaipian[42] = cehou_pci.getText().trim();
		//43������/����
		outputpaipian[43] = cehou_wci.getText().trim();
		//44�������ȫ/����
		outputpaipian[44] = guangpuquan_pci.getText().trim();
		//45�������ȫ/����
		outputpaipian[45] = guangpuquan_wci.getText().trim();
		//46������װ�/����
		outputpaipian[46] = guangpuban_pci.getText().trim();
		//47������װ�/����
		outputpaipian[47] = guangpuban_wci.getText().trim();
		//48����Ӳ��/����
		outputpaipian[48] = yingdu_pci.getText().trim();
		//49����Ӳ��/����
		outputpaipian[49] = yingdu_wci.getText().trim();
		//50�����ĥ/����
		outputpaipian[50] = damo_pci.getText().trim();
		//51�����ĥ/����
		outputpaipian[51] = damo_wci.getText().trim();
		//52����tofd/����
		outputpaipian[52] = tofd_pci.getText().trim();
		//53����tofd/����
		outputpaipian[53] = tofd_wci.getText().trim();
		//54��������/����
		outputpaipian[54] = lashen_pci.getText().trim();
		//55��������/����
		outputpaipian[55] = lashen_wci.getText().trim();
		//56��������/����
		outputpaipian[56] = wanqu_pci.getText().trim();
		//57��������/����
		outputpaipian[57] = wanqu_wci.getText().trim();
		//58������/����
		outputpaipian[58] = chongji_pci.getText().trim();
		//59������/����
		outputpaipian[59] = chongji_wci.getText().trim();
		//60����������/����
		outputpaipian[60] = tie_pci.getText().trim();
		//61����������/����
		outputpaipian[61] = tie_wci.getText().trim();
		//62�������/����
		outputpaipian[62] = jinxiang_pci.getText().trim();
		//63�������/����
		outputpaipian[63] = jinxiang_wci.getText().trim();
        //64����ϴƬ��1��Ϊnull���""����Ϊnull���ϴƬ��
		outputpaipian[64] = ((String)xipianren1.getSelectedItem()) ==null? "":(String)xipianren1.getSelectedItem();
		//65����ϴƬ��1�Ĺ����������ϴƬ��1ѡ��������Ϊ�ջ�ƥ���ʽ�����ûѡ��ϴƬ��1����������
		outputpaipian[65] = xipian1.getText().trim();
		if(   (outputpaipian[64] != "") && (outputpaipian[65].equals("") || (!pattern.matcher(outputpaipian[65]).matches()))) {JOptionPane.showMessageDialog(null, "ϴƬ��1�Ĺ�����δ���룬���ʽ����", "ʧ��", JOptionPane.ERROR_MESSAGE);return 3;}
		if( (outputpaipian[64] == "") && (! outputpaipian[65].equals("")) ) {JOptionPane.showMessageDialog(null, "����ѡ��ϴƬ��1�����ӦϴƬ����Ӧ������", "ʧ��", JOptionPane.ERROR_MESSAGE);return 4;}
	    //66����ϴƬ��2��Ϊnull���""����Ϊnull���ϴƬ��
		outputpaipian[66] = ((String)xipianren2.getSelectedItem()) ==null? "":(String)xipianren2.getSelectedItem();
		//67����ϴƬ��2�Ĺ����������ϴƬ��2ѡ��������Ϊ�ջ�ƥ���ʽ�����ûѡ��ϴƬ��2����������
		outputpaipian[67] = xipian2.getText().trim();
		if(   (outputpaipian[66] != "") && (outputpaipian[67].equals("") || (!pattern.matcher(outputpaipian[67]).matches()))) {JOptionPane.showMessageDialog(null, "ϴƬ��2�Ĺ�����δ���룬���ʽ����", "ʧ��", JOptionPane.ERROR_MESSAGE);return 7;}
		if( (outputpaipian[66] == "") && (! outputpaipian[67].equals("")) ) {JOptionPane.showMessageDialog(null, "����ѡ��ϴƬ��2�����ӦϴƬ����Ӧ������", "ʧ��", JOptionPane.ERROR_MESSAGE);return 8;}
	    //68����ϴƬ��3��Ϊnull���""����Ϊnull���ϴƬ��
		outputpaipian[68] = ((String)xipianren3.getSelectedItem()) ==null? "":(String)xipianren3.getSelectedItem();
		//69����ϴƬ��3�Ĺ����������ϴƬ��3ѡ��������Ϊ�ջ�ƥ���ʽ�����ûѡ��ϴƬ��3����������
		outputpaipian[69] = xipian3.getText().trim();
		if(   (outputpaipian[68] != "") && (outputpaipian[69].equals("") || (!pattern.matcher(outputpaipian[69]).matches()))) {JOptionPane.showMessageDialog(null, "ϴƬ��3�Ĺ�����δ���룬���ʽ����", "ʧ��", JOptionPane.ERROR_MESSAGE);return 11;}
		if( (outputpaipian[68] == "") && (! outputpaipian[69].equals("")) ) {JOptionPane.showMessageDialog(null, "����ѡ��ϴƬ��3�����ӦϴƬ����Ӧ������", "ʧ��", JOptionPane.ERROR_MESSAGE);return 12;}
		//70��¼���
		outputpaipian[70] = jilu_num.getText().trim();
		//71����
		outputpaipian[71] = group;
		
		//�������ݿ�
		sm.insert_workload_records_table(outputpaipian);
		return 0;

		
	}
	
	@SuppressWarnings("unchecked")
	private void renewAction(){
		//ָ���
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
				// TODO ʧȥ������ί�е�λ�͹����������ѯ����	
				cb1.setModel( (DefaultComboBoxModel<String>)sm.search(3, 0));
				cb1.setSelectedItem((String)sm.search(1,2,new String[]{cb0.getText()}));
				cb2.setModel( (DefaultComboBoxModel<String>)sm.search(4, 0));
				cb2.setSelectedItem((String)sm.search(2,2,new String[]{cb0.getText()}));
			}

		});
		
		//ί�е�λ
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
				// ɸѡ���������Ĺ�������
				if((String)cb1.getSelectedItem() != ""){
				cb2.setModel((DefaultComboBoxModel<String>)sm.search(5, 0,new String[]{(String)cb1.getSelectedItem()}));
				}else{
					cb2.setModel( (DefaultComboBoxModel<String>)sm.search(4, 0));
				}				
			}		
		});
		
		
		//��Ŀ����
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
				// ɸѡָ���
				if((String)cb1.getSelectedItem() != "" && cb1.getSelectedItem()!=""){
				cb0.setText((String)sm.search(7,2,new String[]{(String)cb1.getSelectedItem(),(String)cb2.getSelectedItem()}));
				}			
	     	}		
		});		
		
		//������Ա
		mid_left_top.remove(workperson);
		workperson.setModel( (DefaultComboBoxModel<String>)sm.search(8, 0));
		workperson.setMaximumRowCount(15);
		workperson.setSelectedIndex(-1);
		workperson.setEditable(true);
		workperson.setMaximumRowCount(10);
		GridBagConstraints gbc_workperson = new GridBagConstraints();
		gbc_workperson.fill = GridBagConstraints.HORIZONTAL;
		gbc_workperson.anchor = GridBagConstraints.WEST;
		gbc_workperson.insets = new Insets(0, 0, 5, 5);
		gbc_workperson.gridx = 1;
		gbc_workperson.gridy = 0;
		mid_left_top.add(workperson, gbc_workperson);
		workperson.setFocusable(true);
		workperson.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// Ѱ������ƥ��ֵ
				if((  (String)workperson.getSelectedItem() != "" )&&( workperson.getSelectedIndex()>-1)  ){
					System.out.println("�����ֵ");
					workperson_info.setText("");
				}
				else{
					workperson.setSelectedIndex(-1);
					//JOptionPane.showMessageDialog(null, "δ�ҵ���������Ա��������Ա���������", "���޴���", JOptionPane.ERROR_MESSAGE);
					workperson_info.setText("���޴���");
					System.out.println("û���ֵ");
				}				
			}		
		});

        //ϴƬ��1
		mid_left_top.remove(xipianren1);
		xipianren1.setModel( (DefaultComboBoxModel<String>)sm.search(9, 0));
		xipianren1.setSelectedIndex(-1);
		xipianren1.setMaximumRowCount(15);
		GridBagConstraints gbc_xipianren1 = new GridBagConstraints();
		gbc_xipianren1.fill = GridBagConstraints.HORIZONTAL;
		gbc_xipianren1.anchor = GridBagConstraints.WEST;
		gbc_xipianren1.insets = new Insets(0, 0, 5, 5);
		gbc_xipianren1.gridx = 1;
		gbc_xipianren1.gridy = 2;
		mid_left_top.add(xipianren1, gbc_xipianren1);
		
		//ϴƬ��1
		mid_left_top.remove(xipianren2);
		xipianren2.setModel( (DefaultComboBoxModel<String>)sm.search(9, 0));
		xipianren2.setSelectedIndex(-1);
		xipianren2.setMaximumRowCount(15);		
		GridBagConstraints gbc_xipianren2 = new GridBagConstraints();
		gbc_xipianren2.fill = GridBagConstraints.HORIZONTAL;
		gbc_xipianren2.anchor = GridBagConstraints.WEST;
		gbc_xipianren2.insets = new Insets(0, 0, 5, 5);
		gbc_xipianren2.gridx = 1;
		gbc_xipianren2.gridy = 3;
		mid_left_top.add(xipianren2, gbc_xipianren2);	
		
		//ϴƬ��3
		mid_left_top.remove(xipianren3);
		xipianren3.setModel( (DefaultComboBoxModel<String>)sm.search(9, 0));
		xipianren3.setSelectedIndex(-1);
		xipianren3.setMaximumRowCount(15);			
		GridBagConstraints gbc_xipianren3 = new GridBagConstraints();
		gbc_xipianren3.fill = GridBagConstraints.HORIZONTAL;
		gbc_xipianren3.anchor = GridBagConstraints.WEST;
		gbc_xipianren3.insets = new Insets(0, 0, 5, 5);
		gbc_xipianren3.gridx = 1;
		gbc_xipianren3.gridy = 4;
		mid_left_top.add(xipianren3, gbc_xipianren3);		
		

		updateUI();
	}
	
	
	
}