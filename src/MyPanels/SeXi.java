package MyPanels;

import javax.swing.JPanel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

public class SeXi extends JPanel {

	/**
	 * 查询洗片
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
	 private JLabel xipianrenlabel ;
	 private JComboBox<String> xipianren ;
	 private JLabel workdate_label1;
	 private JTextField workdate1;
	 private JLabel workdate_label2;
	 private JTextField workdate2;
	 private JLabel workperson_label;
	 private JComboBox<String> workperson;//工作人员Combobox
	 private JLabel jilu_num_label;
	 private JTextField jilu_num;
	 
	 //中部
	 private ResultSet rs;
	 private JScrollPane jsp;
	 private DBTableModel DBTableModel;
	 private JTable jt;
	
		//TODO 底部容器
	 private JPanel bottom;
	 private JLabel label4;
	 private JButton searchJB;
	 private JLabel label_6;
	 private JButton deleteJB;
	 private JLabel label;
	 private JButton renewJB;

	
	
	public SeXi(SQLManager s, String group) {
		this.group=group;
		this.sm=s;
		setLayout(new BorderLayout(0, 0));
		init();

	}

	
	@SuppressWarnings("unchecked")
	private void init(){
		// TODO 顶部容器
		top = new JPanel();
		top.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u67E5\u8BE2\u6761\u4EF6", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 0, 0)));
		add(top, BorderLayout.NORTH);
		GridBagLayout gbl_top = new GridBagLayout();
		gbl_top.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_top.rowHeights = new int[]{0, 0, 0, 0};
		gbl_top.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_top.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		top.setLayout(gbl_top);
		
		// TODO 指令单号
		label0 = new JLabel("\u6307\u4EE4\u5355\u53F7");
		label0.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_label0 = new GridBagConstraints();
		gbc_label0.anchor = GridBagConstraints.EAST;
		gbc_label0.insets = new Insets(0, 0, 5, 5);
		gbc_label0.gridx = 0;
		gbc_label0.gridy = 0;
		top.add(label0, gbc_label0);
		
		
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
		
		//TODO 委托单位
		label1 = new JLabel("\u59D4\u6258\u5355\u4F4D");
		GridBagConstraints gbc_label1 = new GridBagConstraints();
		gbc_label1.anchor = GridBagConstraints.EAST;
		gbc_label1.insets = new Insets(0, 0, 5, 5);
		gbc_label1.gridx = 2;
		gbc_label1.gridy = 0;
		top.add(label1, gbc_label1);
		
		
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
		
		//TODO 工作人员信息			
		xipianrenlabel = new JLabel("洗片人");
		GridBagConstraints gbc_xipianrenlabel = new GridBagConstraints();
		gbc_xipianrenlabel.insets = new Insets(0, 0, 5, 5);
		gbc_xipianrenlabel.anchor = GridBagConstraints.EAST;
		gbc_xipianrenlabel.gridx = 0;
		gbc_xipianrenlabel.gridy = 1;
		top.add(xipianrenlabel, gbc_xipianrenlabel);
		
		xipianren = new JComboBox<String>();
		xipianren.setModel( (DefaultComboBoxModel<String>)sm.search(9, 0));
		xipianren.setMaximumRowCount(15);
		xipianren.setSelectedIndex(-1);
		xipianren.setEditable(true);
		GridBagConstraints gbc_xipianren = new GridBagConstraints();
		gbc_xipianren.fill = GridBagConstraints.HORIZONTAL;
		gbc_xipianren.anchor = GridBagConstraints.WEST;
		gbc_xipianren.insets = new Insets(0, 0, 5, 5);
		gbc_xipianren.gridx = 1;
		gbc_xipianren.gridy = 1;
		top.add(xipianren, gbc_xipianren);
		xipianren.setFocusable(true);
		
		
		
		
		//TODO 工作日期
		workdate_label1 = new JLabel("\u5DE5\u4F5C\u65E5\u671F \u4ECE");
		GridBagConstraints gbc_workdate_label1 = new GridBagConstraints();
		gbc_workdate_label1.anchor = GridBagConstraints.EAST;
		gbc_workdate_label1.insets = new Insets(0, 0, 5, 5);
		gbc_workdate_label1.gridx = 2;
		gbc_workdate_label1.gridy = 1;
		top.add(workdate_label1, gbc_workdate_label1);
		
		workdate1 = new JTextField();
		GridBagConstraints gbc_workdate1 = new GridBagConstraints();
		gbc_workdate1.insets = new Insets(0, 0, 5, 5);
		gbc_workdate1.fill = GridBagConstraints.HORIZONTAL;
		gbc_workdate1.gridx = 3;
		gbc_workdate1.gridy = 1;
		top.add(workdate1, gbc_workdate1);
		workdate1.setColumns(10);
		
		workdate_label2 = new JLabel("\u81F3");
		GridBagConstraints gbc_workdate_label2 = new GridBagConstraints();
		gbc_workdate_label2.insets = new Insets(0, 0, 5, 5);
		gbc_workdate_label2.gridx = 4;
		gbc_workdate_label2.gridy = 1;
		top.add(workdate_label2, gbc_workdate_label2);
		
		workdate2 = new JTextField();
		GridBagConstraints gbc_workdate2 = new GridBagConstraints();
		gbc_workdate2.insets = new Insets(0, 0, 5, 5);
		gbc_workdate2.fill = GridBagConstraints.HORIZONTAL;
		gbc_workdate2.gridx = 5;
		gbc_workdate2.gridy = 1;
		top.add(workdate2, gbc_workdate2);
		workdate2.setColumns(10);
		
		workperson_label = new JLabel("\u5DE5\u4F5C\u4EBA\u5458");
		GridBagConstraints gbc_workperson_label = new GridBagConstraints();
		gbc_workperson_label.anchor = GridBagConstraints.EAST;
		gbc_workperson_label.insets = new Insets(0, 0, 0, 5);
		gbc_workperson_label.gridx = 0;
		gbc_workperson_label.gridy = 2;
		top.add(workperson_label, gbc_workperson_label);
		
		workperson = new JComboBox<String>();
		workperson.setModel( (DefaultComboBoxModel<String>)sm.search(8, 0));
		workperson.setMaximumRowCount(15);
		workperson.setSelectedIndex(-1);
		workperson.setFocusable(true);
		GridBagConstraints gbc_workperson = new GridBagConstraints();
		gbc_workperson.insets = new Insets(0, 0, 0, 5);
		gbc_workperson.fill = GridBagConstraints.HORIZONTAL;
		gbc_workperson.gridx = 1;
		gbc_workperson.gridy = 2;
		top.add(workperson, gbc_workperson);

		
		jilu_num_label = new JLabel("\u8BB0\u5F55\u7F16\u53F7");
		GridBagConstraints gbc_jilu_num_label = new GridBagConstraints();
		gbc_jilu_num_label.anchor = GridBagConstraints.EAST;
		gbc_jilu_num_label.insets = new Insets(0, 0, 0, 5);
		gbc_jilu_num_label.gridx = 2;
		gbc_jilu_num_label.gridy = 2;
		top.add(jilu_num_label, gbc_jilu_num_label);
		
		jilu_num = new JTextField();
		GridBagConstraints gbc_jilu_num = new GridBagConstraints();
		gbc_jilu_num.insets = new Insets(0, 0, 0, 5);
		gbc_jilu_num.fill = GridBagConstraints.HORIZONTAL;
		gbc_jilu_num.gridx = 3;
		gbc_jilu_num.gridy = 2;
		top.add(jilu_num, gbc_jilu_num);
		jilu_num.setColumns(10);
		
		
		
		
		
		//TODO 底部容器
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
		
		label = new JLabel("                                     ");
		bottom.add(label);
		
		renewJB = new JButton("\u5237\u65B0");
		renewJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				renew();
			}
		});
		bottom.add(renewJB);
		
//		label_6 = new JLabel("              ");
//		bottom.add(label_6);
//		
//		//TODO 删除按钮
//		deleteJB = new JButton("删除");
//		deleteJB.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				delete();
//			}
//		});
//		bottom.add(deleteJB);	
	
		
	}
	
	
	//TODO 得到中间table
	private void repainth(ResultSet rs) {
		// TODO Auto-generated method stub
		if (jsp != null) this.remove(jsp);
		DBTableModel = new DBTableModel(rs);
		DBTableModel.isCountAll(true);
		jt = new JTable(DBTableModel){
			@Override
			public boolean isCellEditable(int row,int col){ 
				   return false;
				} 
		};
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
	    //dcm.getColumn(0).setMinWidth(0); 
	    //dcm.getColumn(0).setMaxWidth(0); 	
		add(jsp, BorderLayout.CENTER);
		this.revalidate();	
	}
	
	
	private void search(){
		System.out.println("查询");
		String seStr[] = new String[15];
		String sql = "select  sw_id, workperson '工作人员', cmd_num '指令单号'   , workdate '工作日期' , person1 '洗片人1' ,amount1 '洗片人1工作量', person2 '洗片人2' ,amount2 '洗片人2工作量', person3 '洗片人3',amount3 '洗片人3工作量' ,jilu_num '记录编号'   from workload_records_table where bm_name= '" + group +"'";
		int i = 0;
		//0处理指令单号
		seStr[0] = cb0.getText().trim().equals("") ? null : cb0.getText().trim() ;
		if(seStr[0] != null){
			sql += " and  cmd_num like '"+ seStr[0] +"%' " ;
			i++;
		}
		//1 处理洗片人
		if(xipianren.getSelectedIndex()==-1 && xipianren.getSelectedItem()==null) seStr[1]=null;
		else seStr[1] = xipianren.getSelectedItem().toString().trim().equals("") ? null : xipianren.getSelectedItem().toString().trim() ;
		if(seStr[1] != null){
			sql += " and ( person1= '"+ seStr[1] +"' or person2= '"+ seStr[1] +"' or person3= '"+ seStr[1]  +"' ) " ;
			i++;
		}
		
		//2处理工作日期起始
		seStr[2] = workdate1.getText().trim().equals("") ? null : workdate1.getText().trim() ;
		if(seStr[2] != null){
			sql += " and  workdate >=" + seStr[2];
			i++;
		}
		//3处理工作日期截止
		seStr[3] = workdate2.getText().trim().equals("") ? null : workdate2.getText().trim() ;
		if(seStr[3] != null){
			sql += " and  workdate <=" + seStr[3];
			i++;
		}
		
		//4 处理工作人员
		if(workperson.getSelectedIndex()==-1 && workperson.getSelectedItem()==null) seStr[4]=null;
		else seStr[4] = workperson.getSelectedItem().toString().trim().equals("") ? null : workperson.getSelectedItem().toString().trim() ;
		if(seStr[4] != null){
			
			sql += " and  workperson= '"+ seStr[4] +"'"  ;
			i++;
		}
		
		//5处理记录编号
		seStr[5] = jilu_num.getText().trim().equals("") ? null : jilu_num.getText().trim() ;
		if(seStr[5] != null){
			sql += " and  jilu_num like '"+ seStr[5] +"%' " ;
			i++;
		}
		
		
		
		sql+="  group by sw_id with rollup;";
		System.out.println(sql);
		if(i == 0){JOptionPane.showMessageDialog(null, "请输入查询条件", "失败", JOptionPane.ERROR_MESSAGE); return ;}
		
		rs = sm.submit_search(sql);
		repainth(rs);
	}
	
	
//	private void delete(){
//		try {
//			int sr=jt.getSelectedRow();
//			rs.absolute(sr+1);
//			rs.deleteRow();
//			search();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
	
	
	@SuppressWarnings("unchecked")
	private void renew(){
		
		// TODO 指令单号
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
		
		//TODO 委托单位
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
		
		
		//TODO 工作人员信息			
		top.remove(xipianren);
		xipianren = new JComboBox<String>();
		xipianren.setModel( (DefaultComboBoxModel<String>)sm.search(9, 0));
		xipianren.setMaximumRowCount(15);
		xipianren.setSelectedIndex(-1);
		xipianren.setEditable(true);
		GridBagConstraints gbc_xipianren = new GridBagConstraints();
		gbc_xipianren.fill = GridBagConstraints.HORIZONTAL;
		gbc_xipianren.anchor = GridBagConstraints.WEST;
		gbc_xipianren.insets = new Insets(0, 0, 5, 5);
		gbc_xipianren.gridx = 1;
		gbc_xipianren.gridy = 1;
		top.add(xipianren, gbc_xipianren);
		xipianren.setFocusable(true);
		
		
		//工作人员
		top.remove(workperson);
		workperson = new JComboBox<String>();
		workperson.setModel( (DefaultComboBoxModel<String>)sm.search(8, 0));
		workperson.setMaximumRowCount(15);
		workperson.setSelectedIndex(-1);
		workperson.setFocusable(true);
		GridBagConstraints gbc_workperson = new GridBagConstraints();
		gbc_workperson.insets = new Insets(0, 0, 0, 5);
		gbc_workperson.fill = GridBagConstraints.HORIZONTAL;
		gbc_workperson.gridx = 1;
		gbc_workperson.gridy = 2;
		top.add(workperson, gbc_workperson);
		
		updateUI();
		
	}
	
	
	
	
}
