package MyPanels;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;

import MainFrame.DBTableModel;
import MainFrame.SQLManager;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.FlowLayout;

import javax.swing.JScrollPane;

import java.awt.event.*;



@SuppressWarnings("serial")
public class SeWR extends JPanel {
	

	/**
	 * 查流水表
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
	 private JLabel workpersonlabel ;
	 private JComboBox<String> workperson ;
	 private JLabel workdate_label1;
	 private JTextField workdate1;
	 private JLabel workdate_label2;
	 private JTextField workdate2;
	 
	 //中部
	 private ResultSet rs;
	 private JScrollPane jsp;
	 private DBTableModel DBTableModel;
	 private JTable jt;
	 
	 
	 
		//TODO 底部容器
	 private JPanel bottom;
	 private JLabel label4;
	 private JButton searchJB;
	 private JLabel label_5;
	 private JButton modifyJB;
	 private JLabel label_6;
	 private JButton deleteJB;
	 
	 public static final Pattern pattern = Pattern.compile("(\\d+)|(\\d+\\.\\d+)");
	 public static final Pattern pattern2 = Pattern.compile("\\d\\d\\d\\d\\d\\d\\d\\d");
	 private JButton renewJB;
	 private JLabel label_4;
	 private JComboBox itemCB;
	 private JComboBox calCB;
	 private JTextField calTF;
	
	public SeWR(SQLManager s, String group) {
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
		gbl_top.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_top.rowHeights = new int[]{0, 0, 0, 0};
		gbl_top.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
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
		gbc_cb0.gridwidth = 2;
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
		gbc_label1.gridx = 3;
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
		gbc_cb1.gridx = 4;
		gbc_cb1.gridy = 0;
		top.add(cb1, gbc_cb1);
		
		//TODO 工程名称label
		label2 = new JLabel("\u5DE5\u7A0B\u540D\u79F0");
		GridBagConstraints gbc_label2 = new GridBagConstraints();
		gbc_label2.insets = new Insets(0, 0, 5, 5);
		gbc_label2.gridx = 5;
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
		gbc_cb2.gridx = 6;
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
		gbc_label_3.gridx = 7;
		gbc_label_3.gridy = 0;
		top.add(label_3, gbc_label_3);
		
		//TODO 工作人员信息			
		workpersonlabel = new JLabel("\u5DE5\u4F5C\u4EBA\u5458");
		GridBagConstraints gbc_workpersonlabel = new GridBagConstraints();
		gbc_workpersonlabel.insets = new Insets(0, 0, 5, 5);
		gbc_workpersonlabel.anchor = GridBagConstraints.EAST;
		gbc_workpersonlabel.gridx = 0;
		gbc_workpersonlabel.gridy = 1;
		top.add(workpersonlabel, gbc_workpersonlabel);
		
		workperson = new JComboBox<String>();
		workperson.setModel( (DefaultComboBoxModel<String>)sm.search(8, 0));
		workperson.setMaximumRowCount(15);
		workperson.setSelectedIndex(-1);
		workperson.setEditable(true);
		workperson.setMaximumRowCount(10);
		GridBagConstraints gbc_workperson = new GridBagConstraints();
		gbc_workperson.gridwidth = 2;
		gbc_workperson.fill = GridBagConstraints.HORIZONTAL;
		gbc_workperson.anchor = GridBagConstraints.WEST;
		gbc_workperson.insets = new Insets(0, 0, 5, 5);
		gbc_workperson.gridx = 1;
		gbc_workperson.gridy = 1;
		top.add(workperson, gbc_workperson);
		workperson.setFocusable(true);
		
		//TODO 工作日期
		workdate_label1 = new JLabel("\u5DE5\u4F5C\u65E5\u671F \u4ECE");
		GridBagConstraints gbc_workdate_label1 = new GridBagConstraints();
		gbc_workdate_label1.anchor = GridBagConstraints.EAST;
		gbc_workdate_label1.insets = new Insets(0, 0, 5, 5);
		gbc_workdate_label1.gridx = 3;
		gbc_workdate_label1.gridy = 1;
		top.add(workdate_label1, gbc_workdate_label1);
		
		workdate1 = new JTextField();
		GridBagConstraints gbc_workdate1 = new GridBagConstraints();
		gbc_workdate1.insets = new Insets(0, 0, 5, 5);
		gbc_workdate1.fill = GridBagConstraints.HORIZONTAL;
		gbc_workdate1.gridx = 4;
		gbc_workdate1.gridy = 1;
		top.add(workdate1, gbc_workdate1);
		workdate1.setColumns(10);
		
		workdate_label2 = new JLabel("\u81F3");
		GridBagConstraints gbc_workdate_label2 = new GridBagConstraints();
		gbc_workdate_label2.insets = new Insets(0, 0, 5, 5);
		gbc_workdate_label2.gridx = 5;
		gbc_workdate_label2.gridy = 1;
		top.add(workdate_label2, gbc_workdate_label2);
		
		workdate2 = new JTextField();
		GridBagConstraints gbc_workdate2 = new GridBagConstraints();
		gbc_workdate2.insets = new Insets(0, 0, 5, 5);
		gbc_workdate2.fill = GridBagConstraints.HORIZONTAL;
		gbc_workdate2.gridx = 6;
		gbc_workdate2.gridy = 1;
		top.add(workdate2, gbc_workdate2);
		workdate2.setColumns(10);
		
		itemCB = new JComboBox();
		itemCB.addItem((Object)"");
		itemCB.addItem((Object)"x");
		itemCB.addItem((Object)"y");
		itemCB.addItem((Object)"x+y");
		itemCB.setSelectedIndex(0);
		GridBagConstraints gbc_itemCB = new GridBagConstraints();
		gbc_itemCB.insets = new Insets(0, 0, 0, 5);
		gbc_itemCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_itemCB.gridx = 0;
		gbc_itemCB.gridy = 2;
		top.add(itemCB, gbc_itemCB);
		
		calCB = new JComboBox();
		calCB.addItem((Object)"=");
		calCB.addItem((Object)">=");
		calCB.addItem((Object)"<=");
		calCB.addItem((Object)">");
		calCB.addItem((Object)"<");
		itemCB.setSelectedIndex(0);
		GridBagConstraints gbc_calCB = new GridBagConstraints();
		gbc_calCB.insets = new Insets(0, 0, 0, 5);
		gbc_calCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_calCB.gridx = 1;
		gbc_calCB.gridy = 2;
		top.add(calCB, gbc_calCB);
		
		calTF = new JTextField();
		GridBagConstraints gbc_calTF = new GridBagConstraints();
		gbc_calTF.insets = new Insets(0, 0, 0, 5);
		gbc_calTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_calTF.gridx = 2;
		gbc_calTF.gridy = 2;
		top.add(calTF, gbc_calTF);
		calTF.setColumns(10);
		
		
		//TODO 底部容器
		bottom = new JPanel();
		add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		label4 = new JLabel("          ");
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
		
		label_6 = new JLabel("      ");
		bottom.add(label_6);
		
		//TODO 删除按钮
		deleteJB = new JButton("删除");
		deleteJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		bottom.add(deleteJB);	
		
		label_4 = new JLabel("    ");
		bottom.add(label_4);
		
		renewJB = new JButton("\u5237\u65B0");
		renewJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				renew();
			}
		});
		bottom.add(renewJB);
	}
	
	
	
	//TODO 得到中间table
	private void repainth(  final ResultSet rs) {
		// TODO Auto-generated method stub
		if (jsp != null) this.remove(jsp);
		DBTableModel = new DBTableModel(rs);
		DBTableModel.isCountAll(true);
		jt = new JTable(DBTableModel){
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex){
				
				if(jt.getColumnName(columnIndex).equals("x光/张") ){
					System.out.println(jt.getColumnName(columnIndex));
					return false;
				}else if(jt.getColumnName(columnIndex).equals("y光/张")){
					System.out.println(jt.getColumnName(columnIndex));
					return false;
				}else{
					return true;
				}

			}
		};
		jsp = new JScrollPane(jt);
		jt.getModel().addTableModelListener(new TableModelListener(){
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO 更新表内内容
				validate();
				updateUI();
			}
		});
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
		//不可修改
		
		DefaultTableColumnModel dcm = (DefaultTableColumnModel)jt.getColumnModel(); 
	    dcm.getColumn(0).setMinWidth(0); 
	    dcm.getColumn(0).setMaxWidth(0); 	
		add(jsp, BorderLayout.CENTER);
		this.revalidate();	
		
	}
	
	
	
	
	
	
	
	
	private void search(){
		System.out.println("查询");
		String seStr[] = new String[15];
		String sql = "select  sw_id, cmd_num '指令单号'   , workperson '工作人员' , workdate '工作日期' , x_pzhang 'x光/张',x_pfei 'x光废片'  , y_pzhang 'y光/张', y_pfei 'y光废片' , chaosheng_pmi '超声/米' , chaosheng_pdao '超声/道'  , cifen_pmi '磁粉/米' , cifen_pdao '磁粉/道 ' , shentou_pmi '渗透/米' , shentou_pdao '渗透/道 ' , cehou_pdian '测厚/点'  , biaopei_pri '表面配合/工日' , guangpuquan_pdian '全定量/点' , guangpuquan_pge '全定量/个元素' , guangpuban_pdian '半定量/点' , guangpuban_pge '半定量/个元素 ' , yingdu_pdian '硬度/点'  , damo_pmi '打磨/米'  , tofd_pmi 'TOFD/米'  , lashen_pjian '拉伸/件 ' , wanqu_pjian '弯曲/件'  , chongji_pjian '冲击/件 ' , tie_pdian '铁素体/点' , jinxiang_pjian '金相/件 ' , diankou_pamount '点口/特检院配合/技术指导 数量' , qita1_pamount '其他1数量'  , qita2_pamount '其他2数量' , qita3_pamount '其他3数量' , shiwu_beizhu '备注' , x_pci '工资单价x光/次', x_wci '产值单价x光/次', y_pci '工资单价γ/次 ', y_wci '产值单价γ/次 ', chaosheng_pci '工资单价超声/次', chaosheng_wci '产值单价超声/次', cifen_pci '工资单价磁粉/次', cifen_wci '产值单价磁粉/次', shentou_pci '工资单价渗透/次', shentou_wci '产值单价渗透/次', cehou_pci '工资单价测厚/次', cehou_wci '产值单价测厚/次', guangpuquan_pci '工资单价全定量/次 ', guangpuquan_wci '产值单价全定量/次 ', guangpuban_pci '工资单价半定量/次', guangpuban_wci '产值单价半定量/次', yingdu_pci '工资单价硬度/次', yingdu_wci '产值单价硬度/次', damo_pci '工资单价打磨/次', damo_wci '产值单价打磨/次', tofd_pci '工资单价TOFD/次', tofd_wci '产值单价TOFD/次', lashen_pci '工资单价拉伸/次', lashen_wci '产值单价拉伸/次', wanqu_pci '工资单价弯曲/次', wanqu_wci '产值单价弯曲/次', chongji_pci '工资单价冲击/次', chongji_wci '产值单价冲击/次', tie_pci '工资单价铁素体/次 ', tie_wci '产值单价铁素体/次 ', jinxiang_pci '工资单价金相/次', jinxiang_wci '产值单价金相/次', jilu_num '记录编号' from workload_records_table where bm_name= '" + group +"'";
		
		int i = 0;
		
		//0处理指令单号
		seStr[0] = cb0.getText().trim().equals("") ? null : cb0.getText().trim() ;
		if(seStr[0] != null){
			sql += " and  cmd_num like '"+ seStr[0] +"%' " ;
			i++;
		}
		//1 处理工作人员
		if(workperson.getSelectedIndex()==-1 && workperson.getSelectedItem()==null) seStr[1]=null;
		else seStr[1] = workperson.getSelectedItem().toString().trim().equals("") ? null : workperson.getSelectedItem().toString().trim() ;
		if(seStr[1] != null){
			
			sql += " and  workperson like '"+ seStr[1] +"%'"  ;
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
		//4处理大于小于条件
		seStr[4] = itemCB.getSelectedItem().toString();
		if(!seStr[4].equals("")){
			if(seStr[4].equals("x")) seStr[4] = " ifnull(x_pzhang,0)";
			else if(seStr[4].equals("y")) seStr[4] = " ifnull(y_pzhang,0)";
			else if(seStr[4].equals("x+y")) seStr[4] = " ifnull(x_pzhang,0)+ifnull(y_pzhang,0)";
			sql += " and " + seStr[4] + calCB.getSelectedItem().toString()  
					+ (calTF.getText().trim().equals("") ? "0" : calTF.getText().trim());
		}else seStr[4]=null;
		
		
		sql+=" group by sw_id with rollup;";
		System.out.println(sql);
		if(i == 0){JOptionPane.showMessageDialog(null, "请输入查询条件", "失败", JOptionPane.ERROR_MESSAGE); return ;}
		
		rs = sm.submit_search(sql);
		repainth(rs);
		
		
		
	}
	
   //TODO 删除按钮
	private void delete(){
		try {
			int sr=jt.getSelectedRow();
			rs.absolute(sr+1);
			int op = JOptionPane.showConfirmDialog(this, "确认删除指令单号为： "+ rs.getString(2) +" 工作人员为 "+ rs.getString(3)+" 在 "+ rs.getString(4)+ "的工作量？此操作会删除该流水中洗片人的工作量", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(op == JOptionPane.CANCEL_OPTION || op == JOptionPane.CLOSED_OPTION ) return ;
			rs.deleteRow();
			search();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//修改按钮
	private void modify(){	
		int rowindex=jt.getSelectedRow();
		int rowcount = jt.getSelectedRowCount() ;
		if(rowcount == 0){JOptionPane.showMessageDialog(null, "选择需要修改的行", "失败", JOptionPane.ERROR_MESSAGE); return ;}
		if(rowcount > 1){JOptionPane.showMessageDialog(null, "只能选择一行", "失败", JOptionPane.ERROR_MESSAGE); return ;}	
		String str=jt.getValueAt(rowindex, 0).toString();//取得sw_id
		String sql = "select * from workload_records_table where sw_id='" + str + "'" ;
		System.out.println(sql);
		new SeReAmount(this ,sql );
}

	@SuppressWarnings("unchecked")
	private void renew(){
		

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
		gbc_cb0.gridwidth = 2;
		gbc_cb0.weighty = 10.0;
		gbc_cb0.weightx = 10.0;
		gbc_cb0.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb0.insets = new Insets(0, 0, 5, 5);
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
		gbc_cb1.insets = new Insets(0, 0, 5, 5);
		gbc_cb1.gridx = 4;
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
		gbc_cb2.insets = new Insets(0, 0, 5, 5);
		gbc_cb2.gridx = 6;
		gbc_cb2.gridy = 0;
		top.add(cb2, gbc_cb2);
		
		
		//工作人员
		top.remove(workperson);
		workperson = new JComboBox<String>();
		workperson.setModel( (DefaultComboBoxModel<String>)sm.search(8, 0));
		workperson.setMaximumRowCount(15);
		workperson.setSelectedIndex(-1);
		workperson.setEditable(true);
		workperson.setMaximumRowCount(10);
		GridBagConstraints gbc_workperson = new GridBagConstraints();
		gbc_workperson.gridwidth = 2;
		gbc_workperson.fill = GridBagConstraints.HORIZONTAL;
		gbc_workperson.anchor = GridBagConstraints.WEST;
		gbc_workperson.insets = new Insets(0, 0, 5, 5);
		gbc_workperson.gridx = 1;
		gbc_workperson.gridy = 1;
		top.add(workperson, gbc_workperson);
		workperson.setFocusable(true);
		
		updateUI();

	}
	
	
	
	
	
	private class SeReAmount extends JDialog{

		//中上容器
		private JPanel mid_left_top = new JPanel();
		private JTextField workdate = new JTextField();
		private JComboBox<String> workperson = new JComboBox<String>();//工作人员Combobox
		private JLabel workperson_info = new JLabel("");   //显示工作人员下拉框的输入状态
		private JTextField xipian1= new JTextField();
		private JTextField xipian2= new JTextField();
		private JTextField xipian3= new JTextField();
		private JComboBox<String> xipianren1 = new JComboBox<>();//洗片人1的人名选择
		private JComboBox<String> xipianren2 = new JComboBox<>();//洗片人2的人名选择
		private JComboBox<String> xipianren3 = new JComboBox<>();//洗片人3的人名选择
	    private JTextArea shiwu_beizhu = new JTextArea();
	    private JTextField jilu_num;
	   
	    private JTextField xipian_total = new JTextField();
	 //   private JTextField paipian_total = new JTextField();
	   


	    
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
		
		
		//底部容器
		private JButton xiugai;
		private JButton quxiao;
		private JPanel bottom;
		private ResultSet ReRs;
		
		private String sql;
		
		SeReAmount(JPanel jp ,String sqll ){
			super( (JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, jp) , "修改工人工作量", true);
			this.sql = sqll;
			initSeRePane();
			ReRs = sm.submit_search(sql);
			initData();
			pack();
			setVisible(true);
		}
		
		private void initSeRePane(){
			
			//总布局BorderLayout
			setLayout(new BorderLayout(0, 0));
			
			//中间可滚动容器
			JScrollPane middle = new JScrollPane();
			add(middle, BorderLayout.CENTER);
			
			//在滚动容器中添加BoxLayout的middle_Pane,用来放置左右两个容器
			JPanel middle_pane = new JPanel();
			middle.setViewportView(middle_pane);
			middle_pane.setLayout(new BoxLayout(middle_pane, BoxLayout.X_AXIS));
			
			JPanel mid_left = new JPanel();
			middle_pane.add(mid_left);
			mid_left.setLayout(new BoxLayout(mid_left, BoxLayout.Y_AXIS));
			
			//TODO 中间左边的容器
			mid_left.add(mid_left_top);
			GridBagLayout gbl_mid_left_top = new GridBagLayout();
			gbl_mid_left_top.columnWidths = new int[]{0, 64, 31, 24, 0};
			gbl_mid_left_top.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_mid_left_top.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_mid_left_top.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			mid_left_top.setLayout(gbl_mid_left_top);
			
	//TODO 工作人员信息		
			//workpersonlabel是拍片工人的标签
			 JLabel workpersonlabel = new JLabel("\u5DE5\u4F5C\u4EBA\u5458");
			GridBagConstraints gbc_workpersonlabel = new GridBagConstraints();
			gbc_workpersonlabel.insets = new Insets(0, 0, 5, 5);
			gbc_workpersonlabel.anchor = GridBagConstraints.EAST;
			gbc_workpersonlabel.gridx = 0;
			gbc_workpersonlabel.gridy = 0;
			mid_left_top.add(workpersonlabel, gbc_workpersonlabel);
			
			//workperson是拍片工人的名字的下拉框，可输入
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
					// 寻找有无匹配值
					if((  (String)workperson.getSelectedItem() != "" )&&( workperson.getSelectedIndex()>-1)  ){
						System.out.println("有这个值");
						workperson_info.setText("");
					}
					else{
						workperson.setSelectedIndex(-1);
						//JOptionPane.showMessageDialog(null, "未找到该射线人员，请在人员管理中添加", "查无此人", JOptionPane.ERROR_MESSAGE);
						workperson_info.setText("查无此人");
						System.out.println("没这个值");
					}				
				}		
			});
			
			
			//workperon的信息label workperson_info ,显示输入信息
			GridBagConstraints gbc_workperson_info = new GridBagConstraints();
			gbc_workperson_info.insets = new Insets(0, 0, 5, 5);
			gbc_workperson_info.gridx = 2;
			gbc_workperson_info.gridy = 0;
			workperson_info.setFont(new Font("汉仪长仿宋体", Font.BOLD, 12));
			workperson_info.setForeground(Color.RED);
			mid_left_top.add(workperson_info, gbc_workperson_info);
			
			
			//工作日期
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
			
			

			
			
			// TODO xipian1 ,xipian2,xipian3 是3个洗片工人的工作量，xipianren1,2,3是名字下拉框			
		    // 洗片人1combobox xipianren1记录洗片人的信息
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
			
			
	        //洗片人1的工作量,有输入时把工作量算到统计里
			GridBagConstraints gbc_xipian1 = new GridBagConstraints();
			gbc_xipian1.anchor = GridBagConstraints.WEST;
			gbc_xipian1.insets = new Insets(0, 0, 5, 0);
			gbc_xipian1.fill = GridBagConstraints.HORIZONTAL;
			gbc_xipian1.gridx = 3;
			gbc_xipian1.gridy = 2;
			mid_left_top.add(xipian1, gbc_xipian1);
			xipian1.setColumns(5);

			
			
			//洗片人2的人名选择
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
			

			GridBagConstraints gbc_xipian2 = new GridBagConstraints();
			gbc_xipian2.anchor = GridBagConstraints.WEST;
			gbc_xipian2.insets = new Insets(0, 0, 5, 0);
			gbc_xipian2.fill = GridBagConstraints.HORIZONTAL;
			gbc_xipian2.gridx = 3;
			gbc_xipian2.gridy = 3;
			mid_left_top.add(xipian2, gbc_xipian2);
			xipian2.setColumns(5);



			//洗片人3的人名选择
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
			

			GridBagConstraints gbc_xipian3 = new GridBagConstraints();
			gbc_xipian3.anchor = GridBagConstraints.WEST;
			gbc_xipian3.insets = new Insets(0, 0, 5, 0);
			gbc_xipian3.fill = GridBagConstraints.HORIZONTAL;
			gbc_xipian3.gridx = 3;
			gbc_xipian3.gridy = 4;
			mid_left_top.add(xipian3, gbc_xipian3);
			xipian3.setColumns(5);
			
			//TODO 记录编号
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

			
			//TODO 事务备注
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
			
			//事物备注的Textfield
			shiwu_beizhu_panel.add(shiwu_beizhu);
			shiwu_beizhu.setLineWrap(true);
			shiwu_beizhu.setColumns(20);
			shiwu_beizhu.setRows(5);

			
			//左边容器的空白部分，用来控制拉伸
			JLabel space_left = new JLabel("");
			GridBagConstraints gbc_space_left = new GridBagConstraints();
			gbc_space_left.anchor = GridBagConstraints.WEST;
			gbc_space_left.weighty = 100.0;
			gbc_space_left.weightx = 100.0;
			gbc_space_left.gridx = 3;
			gbc_space_left.gridy = 7;
			mid_left_top.add(space_left, gbc_space_left);
			

			//TODO 中左下容器
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
			
	        //洗片总量
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
			
//			JLabel paipian_total_label = new JLabel("\u8BB0\u5F55\u62CD\u7247\u603B\u91CF");
//			GridBagConstraints gbc_paipian_total_label = new GridBagConstraints();
//			gbc_paipian_total_label.insets = new Insets(0, 0, 5, 5);
//			gbc_paipian_total_label.gridx = 0;
//			gbc_paipian_total_label.gridy = 1;
//			mid_left_bottom.add(paipian_total_label, gbc_paipian_total_label);
//			
//	        //拍片总量，只计x射线和Y射线
//			paipian_total.setEditable(false);
//			GridBagConstraints gbc_paipian_total = new GridBagConstraints();
//			gbc_paipian_total.anchor = GridBagConstraints.WEST;
//			gbc_paipian_total.insets = new Insets(0, 0, 5, 5);
//			gbc_paipian_total.gridx = 1;
//			gbc_paipian_total.gridy = 1;
//			mid_left_bottom.add(paipian_total, gbc_paipian_total);
//			paipian_total.setColumns(10);
			
			JLabel mid_left_bottom_space = new JLabel("");
			GridBagConstraints gbc_mid_left_bottom_space = new GridBagConstraints();
			gbc_mid_left_bottom_space.weighty = 100.0;
			gbc_mid_left_bottom_space.weightx = 100.0;
			gbc_mid_left_bottom_space.fill = GridBagConstraints.BOTH;
			gbc_mid_left_bottom_space.gridx = 2;
			gbc_mid_left_bottom_space.gridy = 2;
			mid_left_bottom.add(mid_left_bottom_space, gbc_mid_left_bottom_space);
			
			
			
			
			//中间容器右半部分
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
			
			JLabel duineianci_label1 = new JLabel("工资按次算单价");
			GridBagConstraints gbc_duineianci_label1 = new GridBagConstraints();
			gbc_duineianci_label1.insets = new Insets(0, 0, 5, 5);
			gbc_duineianci_label1.gridx = 6;
			gbc_duineianci_label1.gridy = 0;
			mid_right.add(duineianci_label1, gbc_duineianci_label1);
			
			JLabel duiwaidanjia_label1 = new JLabel("产值按次算单价");
			GridBagConstraints gbc_duiwaidanjia_label1 = new GridBagConstraints();
			gbc_duiwaidanjia_label1.insets = new Insets(0, 0, 5, 5);
			gbc_duiwaidanjia_label1.gridx = 7;
			gbc_duiwaidanjia_label1.gridy = 0;
			mid_right.add(duiwaidanjia_label1, gbc_duiwaidanjia_label1);
			
			//x射线按张算的输入框
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
			
			//伽马射线按张算
			y_pzhang = new JTextField();
			GridBagConstraints gbc_y_pzhang = new GridBagConstraints();
			gbc_y_pzhang.anchor = GridBagConstraints.EAST;
			gbc_y_pzhang.insets = new Insets(0, 0, 5, 5);
			gbc_y_pzhang.gridx = 1;
			gbc_y_pzhang.gridy = 2;
			mid_right.add(y_pzhang, gbc_y_pzhang);
			y_pzhang.setColumns(3);

			
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
			
			cifen_pdao = new JTextField();
			GridBagConstraints gbc_cifen_pdao = new GridBagConstraints();
			gbc_cifen_pdao.anchor = GridBagConstraints.EAST;
			gbc_cifen_pdao.insets = new Insets(0, 0, 5, 5);
			gbc_cifen_pdao.gridx = 3;
			gbc_cifen_pdao.gridy = 4;
			mid_right.add(cifen_pdao, gbc_cifen_pdao);
			cifen_pdao.setColumns(3);
			
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
			
			shentou_pdao = new JTextField();
			GridBagConstraints gbc_shentou_pdao = new GridBagConstraints();
			gbc_shentou_pdao.anchor = GridBagConstraints.EAST;
			gbc_shentou_pdao.insets = new Insets(0, 0, 5, 5);
			gbc_shentou_pdao.gridx = 3;
			gbc_shentou_pdao.gridy = 5;
			mid_right.add(shentou_pdao, gbc_shentou_pdao);
			shentou_pdao.setColumns(3);
			
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
			
			//底部按钮
			bottom = new JPanel();
			GridBagConstraints gbc_bottom = new GridBagConstraints();
			gbc_bottom.anchor = GridBagConstraints.CENTER;
			gbc_bottom.insets = new Insets(0, 0, 5, 0);
			gbc_bottom.gridx = 0;
			gbc_bottom.gridy = 19;
			gbc_bottom.fill = GridBagConstraints.HORIZONTAL;
			gbc_bottom.gridwidth = 5;
			add(bottom,BorderLayout.SOUTH);
			bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JLabel label = new JLabel("                    ");
			bottom.add(label);
			
			//TODO 底部修改按钮
			xiugai = new JButton("修改");
			xiugai.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					xiugai();
				}
			});
			bottom.add(xiugai);
			
			JLabel label_1 = new JLabel("        ");
			bottom.add(label_1);
			
			//TODO 底部取消按钮
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
			
			try {
				ReRs.next();			
				workperson.setSelectedItem(ReRs.getString(4));
				workdate.setText(ReRs.getString(5)==null? "" : ReRs.getString(5));
				x_pzhang.setText(ReRs.getString(6)==null? "" : ReRs.getString(6));
				x_pfei.setText(ReRs.getString(7)==null? "" : ReRs.getString(7));
				y_pzhang.setText(ReRs.getString(8)==null? "" : ReRs.getString(8));
				y_pfei.setText(ReRs.getString(9)==null? "" : ReRs.getString(9));
				chaosheng_pmi.setText(ReRs.getString(10)==null? "" : ReRs.getString(10));
				chaosheng_pdao.setText(ReRs.getString(11)==null? "" : ReRs.getString(11));
				cifen_pmi.setText(ReRs.getString(12)==null? "" : ReRs.getString(12));
				cifen_pdao.setText(ReRs.getString(13)==null? "" : ReRs.getString(13));
				shentou_pmi.setText(ReRs.getString(14)==null? "" : ReRs.getString(14));
				shentou_pdao.setText(ReRs.getString(15)==null? "" : ReRs.getString(15));
				cehou_pdian.setText(ReRs.getString(16)==null? "" : ReRs.getString(16));
				biaopei_pri.setText(ReRs.getString(17)==null? "" : ReRs.getString(17));
				guangpuquan_pdian.setText(ReRs.getString(18)==null? "" : ReRs.getString(18));
				guangpuquan_pge.setText(ReRs.getString(19)==null? "" : ReRs.getString(19));
				guangpuban_pdian.setText(ReRs.getString(20)==null? "" : ReRs.getString(20));
				guangpuban_pge.setText(ReRs.getString(21)==null? "" : ReRs.getString(21));
				yingdu_pdian.setText(ReRs.getString(22)==null? "" : ReRs.getString(22));
				damo_pmi.setText(ReRs.getString(23)==null? "" : ReRs.getString(23));
				tofd_pmi.setText(ReRs.getString(24)==null? "" : ReRs.getString(24));
				lashen_pjian.setText(ReRs.getString(25)==null? "" : ReRs.getString(25));
				wanqu_pjian.setText(ReRs.getString(26)==null? "" : ReRs.getString(26));
				chongji_pjian.setText(ReRs.getString(27)==null? "" : ReRs.getString(27));
				tie_pdian.setText(ReRs.getString(28)==null? "" : ReRs.getString(28));
				jinxiang_pjian.setText(ReRs.getString(29)==null? "" : ReRs.getString(29));
				diankou_pamount.setText(ReRs.getString(30)==null? "" : ReRs.getString(30));
				qita1_pamount.setText(ReRs.getString(31)==null? "" : ReRs.getString(31));
				qita2_pamount.setText(ReRs.getString(32)==null? "" : ReRs.getString(32));
				qita3_pamount.setText(ReRs.getString(33)==null? "" : ReRs.getString(33));
				shiwu_beizhu.setText(ReRs.getString(34)==null? "" : ReRs.getString(34));
				x_pci.setText(ReRs.getString(35)==null? "" : ReRs.getString(35));
				x_wci.setText(ReRs.getString(36)==null? "" : ReRs.getString(36));
				y_pci.setText(ReRs.getString(37)==null? "" : ReRs.getString(37));
				y_wci.setText(ReRs.getString(38)==null? "" : ReRs.getString(38));
				chaosheng_pci.setText(ReRs.getString(39)==null? "" : ReRs.getString(39));
				chaosheng_wci.setText(ReRs.getString(40)==null? "" : ReRs.getString(40));
				cifen_pci.setText(ReRs.getString(41)==null? "" : ReRs.getString(41));
				cifen_wci.setText(ReRs.getString(42)==null? "" : ReRs.getString(42));
				shentou_pci.setText(ReRs.getString(43)==null? "" : ReRs.getString(43));
				shentou_wci.setText(ReRs.getString(44)==null? "" : ReRs.getString(44));
				cehou_pci.setText(ReRs.getString(45)==null? "" : ReRs.getString(45)); 
				cehou_wci.setText(ReRs.getString(46)==null? "" : ReRs.getString(46)); 
				guangpuquan_pci.setText(ReRs.getString(47)==null? "" : ReRs.getString(47));
				guangpuquan_wci.setText(ReRs.getString(48)==null? "" : ReRs.getString(48));
				guangpuban_pci.setText(ReRs.getString(49)==null? "" : ReRs.getString(49));
				guangpuban_wci.setText(ReRs.getString(50)==null? "" : ReRs.getString(50));
				yingdu_pci.setText(ReRs.getString(51)==null? "" : ReRs.getString(51));
				yingdu_wci.setText(ReRs.getString(52)==null? "" : ReRs.getString(52));
				damo_pci.setText(ReRs.getString(53)==null? "" : ReRs.getString(53));
				damo_wci.setText(ReRs.getString(54)==null? "" : ReRs.getString(54));
				tofd_pci.setText(ReRs.getString(55)==null? "" : ReRs.getString(55));
				tofd_wci.setText(ReRs.getString(56)==null? "" : ReRs.getString(56));
				lashen_pci.setText(ReRs.getString(57)==null? "" : ReRs.getString(57));
				lashen_wci.setText(ReRs.getString(58)==null? "" : ReRs.getString(58));
				wanqu_pci.setText(ReRs.getString(59)==null? "" : ReRs.getString(59));
				wanqu_wci.setText(ReRs.getString(60)==null? "" : ReRs.getString(60));
				chongji_pci.setText(ReRs.getString(61)==null? "" : ReRs.getString(61));
				chongji_wci.setText(ReRs.getString(62)==null? "" : ReRs.getString(62));
				tie_pci.setText(ReRs.getString(63)==null? "" : ReRs.getString(63));
				tie_wci.setText(ReRs.getString(64)==null? "" : ReRs.getString(64));
				jinxiang_pci.setText(ReRs.getString(65)==null? "" : ReRs.getString(65));
				jinxiang_wci.setText(ReRs.getString(66)==null? "" : ReRs.getString(66));
				
				xipianren1.setSelectedItem(ReRs.getString(67));
				xipian1.setText(ReRs.getString(68)==null? "" : ReRs.getString(68));
				xipianren2.setSelectedItem(ReRs.getString(69));
				xipian2.setText(ReRs.getString(70)==null? "" : ReRs.getString(70));
				xipianren3.setSelectedItem(ReRs.getString(71));
				xipian3.setText(ReRs.getString(72)==null? "" : ReRs.getString(72));
				
				jilu_num.setText(ReRs.getString(73)==null? "" : ReRs.getString(73));
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		
		
		
		private int xiugai(){		
			try {
				
				String[] outputpaipian = new String[71];
				//计算拍片量与洗片量相加是否相等
				if(caculate_equal() == 0 ) {JOptionPane.showMessageDialog(null, "射线量与拍片量不相等或存在非法字符", "失败", JOptionPane.ERROR_MESSAGE);return 1;}
				
				//1处理工作人员，不为空
				outputpaipian[1] = ((String)workperson.getSelectedItem()) ==null? "":(String)workperson.getSelectedItem();
				if(outputpaipian[1] == "" ) {JOptionPane.showMessageDialog(null, "请输入工作人员", "失败", JOptionPane.ERROR_MESSAGE);return 2;}
				//2处理工作日期，不能为空，进行格式检查。如果为空或者格式不匹配
				outputpaipian[2] = workdate.getText().trim();
				if(outputpaipian[2].equals("") ) {JOptionPane.showMessageDialog(null, "工作时间不能为空且格式应为，如：20160101", "失败", JOptionPane.ERROR_MESSAGE);return 3;}	
				//3处理x光/张
				outputpaipian[3] = x_pzhang.getText().trim();
				//4处理x光废片
				outputpaipian[4] = x_pfei.getText().trim();
				//5处理y/张
				outputpaipian[5] = y_pzhang.getText().trim();
				//6处理y/废片
				outputpaipian[6] = y_pfei.getText().trim();
				//7处理超声/米
				outputpaipian[7] = chaosheng_pmi.getText().trim();
				//8处理超声/道
				outputpaipian[8] = chaosheng_pdao.getText().trim();
				//9处理磁粉/米
				outputpaipian[9] = cifen_pmi.getText().trim();
				//10处理磁粉/道
				outputpaipian[10] = cifen_pdao.getText().trim();	
				//11处理渗透/米
				outputpaipian[11] = shentou_pmi.getText().trim();
				//12处理渗透/道
				outputpaipian[12] = shentou_pdao.getText().trim();
				//13处理测厚/点
				outputpaipian[13] = cehou_pdian.getText().trim();
				//14处理表面配合/工日
				outputpaipian[14] = biaopei_pri.getText().trim();
				//15处理全定量/点
				outputpaipian[15] = guangpuquan_pdian.getText().trim();
				//16处理全定量/个元素
				outputpaipian[16] = guangpuquan_pge.getText().trim();
				//17处理半定量/点
				outputpaipian[17] = guangpuban_pdian.getText().trim();
				//18处理半定量/个元素
				outputpaipian[18] = guangpuban_pge.getText().trim();
				//19处理硬度/点
				outputpaipian[19] = yingdu_pdian.getText().trim();
				//20处理打磨/米
				outputpaipian[20] = damo_pmi.getText().trim();
				//21处理tofd/米
				outputpaipian[21] = tofd_pmi.getText().trim();
				//22处理拉伸/件
				outputpaipian[22] = lashen_pjian.getText().trim();
				//23处理弯曲/件
				outputpaipian[23] = wanqu_pjian.getText().trim();
				//24处理冲击/件
				outputpaipian[24] = chongji_pjian.getText().trim();
				//25处理铁素体/点
				outputpaipian[25] = tie_pdian.getText().trim();
				//26处理金相/件
				outputpaipian[26] = jinxiang_pjian.getText().trim();
				//27处理点口、特检院配合、技术指导 数量
				outputpaipian[27] = diankou_pamount.getText().trim();
				//28处理其他项目1数量
				outputpaipian[28] = qita1_pamount.getText().trim();
				//29处理其他项目2数量
				outputpaipian[29] = qita2_pamount.getText().trim();
				//30处理其他项目3数量
				outputpaipian[30] = qita3_pamount.getText().trim();
				//31处理备注
				outputpaipian[31] = shiwu_beizhu.getText().trim();
				//32处理x/次内
				outputpaipian[32] = x_pci.getText().trim();
				//33处理x/次外
				outputpaipian[33] = x_wci.getText().trim();
				//34处理y/次内
				outputpaipian[34] = y_pci.getText().trim();
				//35处理y/次外
				outputpaipian[35] = y_wci.getText().trim();
				//36处理超声/次内
				outputpaipian[36] = chaosheng_pci.getText().trim();
				//37处理超声/次外
				outputpaipian[37] = chaosheng_wci.getText().trim();
				//38处理磁粉/次内
				outputpaipian[38] = cifen_pci.getText().trim();
				//39处理磁粉/次外
				outputpaipian[39] = cifen_wci.getText().trim();
				//40处理渗透/次内
				outputpaipian[40] = shentou_pci.getText().trim();
				//41处理渗透/次外
				outputpaipian[41] = shentou_wci.getText().trim();
				//42处理测厚/次内
				outputpaipian[42] = cehou_pci.getText().trim();
				//43处理测厚/次外
				outputpaipian[43] = cehou_wci.getText().trim();
				//44处理光谱全/次内
				outputpaipian[44] = guangpuquan_pci.getText().trim();
				//45处理光谱全/次外
				outputpaipian[45] = guangpuquan_wci.getText().trim();
				//46处理光谱半/次内
				outputpaipian[46] = guangpuban_pci.getText().trim();
				//47处理光谱半/次外
				outputpaipian[47] = guangpuban_wci.getText().trim();
				//48处理硬度/次内
				outputpaipian[48] = yingdu_pci.getText().trim();
				//49处理硬度/次外
				outputpaipian[49] = yingdu_wci.getText().trim();
				//50处理打磨/次内
				outputpaipian[50] = damo_pci.getText().trim();
				//51处理打磨/次外
				outputpaipian[51] = damo_wci.getText().trim();
				//52处理tofd/次内
				outputpaipian[52] = tofd_pci.getText().trim();
				//53处理tofd/次外
				outputpaipian[53] = tofd_wci.getText().trim();
				//54处理拉伸/次内
				outputpaipian[54] = lashen_pci.getText().trim();
				//55处理拉伸/次外
				outputpaipian[55] = lashen_wci.getText().trim();
				//56处理弯曲/次内
				outputpaipian[56] = wanqu_pci.getText().trim();
				//57处理弯曲/次外
				outputpaipian[57] = wanqu_wci.getText().trim();
				//58处理冲击/次内
				outputpaipian[58] = chongji_pci.getText().trim();
				//59处理冲击/次外
				outputpaipian[59] = chongji_wci.getText().trim();
				//60处理铁素体/次内
				outputpaipian[60] = tie_pci.getText().trim();
				//61处理铁素体/次外
				outputpaipian[61] = tie_wci.getText().trim();
				//62处理金相/次内
				outputpaipian[62] = jinxiang_pci.getText().trim();
				//63处理金相/次外
				outputpaipian[63] = jinxiang_wci.getText().trim();
		        //64处理洗片人1，为null输出""，不为null输出洗片人
				outputpaipian[64] = ((String)xipianren1.getSelectedItem()) ==null? "":(String)xipianren1.getSelectedItem();
				//65处理洗片人1的工作量，如果洗片人1选择了则不能为空或不匹配格式，如果没选择洗片人1则不能填数字
				outputpaipian[65] = xipian1.getText().trim();
				if(   (outputpaipian[64] != "") && (outputpaipian[65].equals("") || (!pattern.matcher(outputpaipian[65]).matches()))) {JOptionPane.showMessageDialog(null, "洗片人1的工作量未输入，或格式错误", "失败", JOptionPane.ERROR_MESSAGE);return 3;}
				if( (outputpaipian[64] == "") && (! outputpaipian[65].equals("")) ) {JOptionPane.showMessageDialog(null, "若不选择洗片人1，则对应洗片量不应填数字", "失败", JOptionPane.ERROR_MESSAGE);return 4;}
			    //66处理洗片人2，为null输出""，不为null输出洗片人
				outputpaipian[66] = ((String)xipianren2.getSelectedItem()) ==null? "":(String)xipianren2.getSelectedItem();
				//67处理洗片人2的工作量，如果洗片人2选择了则不能为空或不匹配格式，如果没选择洗片人2则不能填数字
				outputpaipian[67] = xipian2.getText().trim();
				if(   (outputpaipian[66] != "") && (outputpaipian[67].equals("") || (!pattern.matcher(outputpaipian[67]).matches()))) {JOptionPane.showMessageDialog(null, "洗片人2的工作量未输入，或格式错误", "失败", JOptionPane.ERROR_MESSAGE);return 7;}
				if( (outputpaipian[66] == "") && (! outputpaipian[67].equals("")) ) {JOptionPane.showMessageDialog(null, "若不选择洗片人2，则对应洗片量不应填数字", "失败", JOptionPane.ERROR_MESSAGE);return 8;}
			    //68处理洗片人3，为null输出""，不为null输出洗片人
				outputpaipian[68] = ((String)xipianren3.getSelectedItem()) ==null? "":(String)xipianren3.getSelectedItem();
				//69处理洗片人3的工作量，如果洗片人3选择了则不能为空或不匹配格式，如果没选择洗片人3则不能填数字
				outputpaipian[69] = xipian3.getText().trim();
				if(   (outputpaipian[68] != "") && (outputpaipian[69].equals("") || (!pattern.matcher(outputpaipian[69]).matches()))) {JOptionPane.showMessageDialog(null, "洗片人3的工作量未输入，或格式错误", "失败", JOptionPane.ERROR_MESSAGE);return 11;}
				if( (outputpaipian[68] == "") && (! outputpaipian[69].equals("")) ) {JOptionPane.showMessageDialog(null, "若不选择洗片人3，则对应洗片量不应填数字", "失败", JOptionPane.ERROR_MESSAGE);return 12;}
				//70记录编号
				outputpaipian[70] = jilu_num.getText().trim();
				
//				//0处理工作人员，不为空
//				outputpaipian[0] = ((String)workperson.getSelectedItem()) ==null? "":(String)workperson.getSelectedItem();
//				if(outputpaipian[0] == "" ) {JOptionPane.showMessageDialog(null, "请输入工作人员", "失败", JOptionPane.ERROR_MESSAGE);return ;}
//				//1处理工作日期，不能为空，进行格式检查。如果为空或者格式不匹配
//				outputpaipian[1] = workdate.getText().trim();
//				if(outputpaipian[1].equals("") || (!pattern2.matcher(outputpaipian[1]).matches()) ) {JOptionPane.showMessageDialog(null, "工作时间不能为空且格式应为，如：20160101", "失败", JOptionPane.ERROR_MESSAGE);return ;}	
//				
//				//计算拍片量与洗片量相加是否相等
//				if(caculate_equal() == 0 ) {JOptionPane.showMessageDialog(null, "射线量与拍片量不相等或存在非法字符", "失败", JOptionPane.ERROR_MESSAGE);return ;}
//				
//				//0处理x光/张
//				data[0] = x_pzhang.getText().trim().equals("") ? "0" : x_pzhang.getText().trim() ;
//				//1处理x光废片
//				data[1] = x_pfei.getText().trim().equals("") ? "0" : x_pfei.getText().trim() ;
//				//2处理y/张
//				data[2] = y_pzhang.getText().trim().equals("") ? "0" : y_pzhang.getText().trim() ;
//				//3处理y废片
//				data[3] = y_pfei.getText().trim().equals("") ? "0" : y_pfei.getText().trim() ;
//				//4处理超声/米
//				data[4] = chaosheng_pmi.getText().trim();
//				//5处理超声/道
//				data[5] = chaosheng_pdao.getText().trim();
//				//6处理磁粉/米
//				data[6] = cifen_pmi.getText().trim();
//				//7处理磁粉/道
//				data[7] = cifen_pdao.getText().trim();
//				//8处理渗透/米
//				data[8] = shentou_pmi.getText().trim();
//				//9处理渗透/道
//				data[9] = shentou_pdao.getText().trim();
//				//10处理测厚/点
//				data[10] = cehou_pdian.getText().trim();
//				//11处理表面配合/工日
//				data[11] = biaopei_pri.getText().trim();
//				//12处理全定量/点
//				data[12] = guangpuquan_pdian.getText().trim();
//				//13处理全定量/个元素
//				data[13] = guangpuquan_pge.getText().trim();
//				//14处理半定量/点
//				data[14] = guangpuban_pdian.getText().trim();
//				//15处理半定量/个元素
//				data[15] = guangpuban_pge.getText().trim();
//				//16处理硬度/点
//				data[16] = yingdu_pdian.getText().trim();
//				//17处理打磨/米
//				data[17] = damo_pmi.getText().trim();
//				//18处理tofd/米
//				data[18] = tofd_pmi.getText().trim();
//				//19处理拉伸/件
//				data[19] = lashen_pjian.getText().trim();
//				//20处理弯曲/件
//				data[20] = wanqu_pjian.getText().trim();
//				//21处理冲击/件
//				data[21] = chongji_pjian.getText().trim();
//				//22处理铁素体/点
//				data[22] = tie_pdian.getText().trim();
//				//23处理金相/件
//				data[23] = jinxiang_pjian.getText().trim();
//				//24处理点口、特检院配合、技术指导 数量
//				data[24] = diankou_pamount.getText().trim();
//				//25处理其他项目1数量
//				data[25] = qita1_pamount.getText().trim();
//				//26处理其他项目2数量
//				data[26] = qita2_pamount.getText().trim();
//				//27处理其他项目3数量
//				data[27] = qita3_pamount.getText().trim();
//				//28处理X光/次
//				data[28] = x_pci.getText().trim();
//				//29处理X光/次
//				data[29] = x_wci.getText().trim();
//				//30处理y/次
//				data[30] = y_pci.getText().trim();
//				//31处理y/次
//				data[31] = y_wci.getText().trim();
//				//32处理超声/次	
//				data[32] = chaosheng_pci.getText().trim();
//				//33处理超声/次	
//				data[33] = chaosheng_wci.getText().trim();
//				//34处理磁粉/次	
//				data[34] = cifen_pci.getText().trim();	
//				//35处理磁粉/次	
//				data[35] = cifen_wci.getText().trim();	
//				//36处理渗透/次	
//				data[36] = shentou_pci.getText().trim();
//				//37处理渗透/次	
//				data[37] = shentou_wci.getText().trim();		
//				//38处理测厚/次
//				data[38] = cehou_pci.getText().trim();			
//				//39处理测厚/次
//				data[39] = cehou_wci.getText().trim();	
//				//40处理全定量/次
//				data[40] = guangpuquan_pci.getText().trim();
//				//41处理全定量/次
//				data[41] = guangpuquan_wci.getText().trim();
//				//42处理半定量/次
//				data[42] = guangpuban_pci.getText().trim();
//				//43处理半定量/次
//				data[43] = guangpuban_wci.getText().trim();
//				//44处理硬度/次
//				data[44] = yingdu_pci.getText().trim();
//				//45处理硬度/次
//				data[45] = yingdu_wci.getText().trim();
//				//46处理打磨/次
//				data[46] = damo_pci.getText().trim();
//				//47处理打磨/次
//				data[47] = damo_wci.getText().trim();
//				//48处理tofd/次
//				data[48] = tofd_pci.getText().trim();
//				//49处理tofd/次
//				data[49] = tofd_wci.getText().trim();
//				//50处理拉伸/次
//				data[50] = lashen_pci.getText().trim();
//				//51处理拉伸/次
//				data[51] = lashen_wci.getText().trim();
//				//52处理弯曲/次
//				data[52] = wanqu_pci.getText().trim();
//				//53处理弯曲/次
//				data[53] = wanqu_wci.getText().trim();
//				//54处理冲击/次
//				data[54] = chongji_pci.getText().trim();
//				//55处理冲击/次
//				data[55] = chongji_wci.getText().trim();
//				//56处理铁素体/次
//				data[56] = tie_pci.getText().trim();
//				//57处理铁素体/次
//				data[57] = tie_wci.getText().trim();
//				//58处理金相/次
//				data[58] = jinxiang_pci.getText().trim();
//				//59处理金相/次
//				data[59] = jinxiang_wci.getText().trim();
//				//60处理洗片人1，为null输出""，不为null输出洗片人
//				data[60] = ((String)xipianren1.getSelectedItem()) ==null? "":(String)xipianren1.getSelectedItem();
//				//61处理洗片人1的工作量，如果洗片人1选择了则不能为空或不匹配格式，如果没选择洗片人1则不能填数字
//				data[61] = xipian1.getText().trim();
//				if(   (data[60] != "") && (data[61].equals("") || (!pattern.matcher(data[61]).matches()))) {JOptionPane.showMessageDialog(null, "洗片人1的工作量未输入，或格式错误", "失败", JOptionPane.ERROR_MESSAGE);return ;}
//				if( (data[60] == "") && (! data[61].equals("")) ) {JOptionPane.showMessageDialog(null, "若不选择洗片人1，则对应洗片量不应填数字", "失败", JOptionPane.ERROR_MESSAGE);return ;}
//			    //62处理洗片人2，为null输出""，不为null输出洗片人
//				data[62] = ((String)xipianren2.getSelectedItem()) ==null? "":(String)xipianren2.getSelectedItem();
//				//63处理洗片人2的工作量，如果洗片人2选择了则不能为空或不匹配格式，如果没选择洗片人2则不能填数字
//				data[63] = xipian2.getText().trim();
//				if(   (data[62] != "") && (data[63].equals("") || (!pattern.matcher(data[63]).matches()))) {JOptionPane.showMessageDialog(null, "洗片人2的工作量未输入，或格式错误", "失败", JOptionPane.ERROR_MESSAGE);return ;}
//				if( (data[62] == "") && (! data[63].equals("")) ) {JOptionPane.showMessageDialog(null, "若不选择洗片人2，则对应洗片量不应填数字", "失败", JOptionPane.ERROR_MESSAGE);return ;}
//			    //64处理洗片人3，为null输出""，不为null输出洗片人
//				data[64] = ((String)xipianren3.getSelectedItem()) ==null? "":(String)xipianren3.getSelectedItem();
//				//65处理洗片人3的工作量，如果洗片人3选择了则不能为空或不匹配格式，如果没选择洗片人3则不能填数字
//				data[65] = xipian3.getText().trim();
//				if(   (data[64] != "") && (data[65].equals("") || (!pattern.matcher(data[65]).matches()))) {JOptionPane.showMessageDialog(null, "洗片人3的工作量未输入，或格式错误", "失败", JOptionPane.ERROR_MESSAGE);return ;}
//				if( (data[64] == "") && (! data[65].equals("")) ) {JOptionPane.showMessageDialog(null, "若不选择洗片人3，则对应洗片量不应填数字", "失败", JOptionPane.ERROR_MESSAGE);return ;}
//				//66记录编号
//				data[66] = jilu_num.getText().trim();
				
				int op = JOptionPane.showConfirmDialog(this, "确认为指令单号为： "+ ReRs.getString(3) +" 工作人员为 "+ ReRs.getString(4)+" 在 "+ ReRs.getString(5)+ "的工作量修改流水？", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(op == JOptionPane.CANCEL_OPTION || op == JOptionPane.CLOSED_OPTION ) return 1;
				
				for(int i=1;i<71;i++){
					if(outputpaipian[i].equals("") ) ReRs.updateNull(i+3);else ReRs.updateString(i+3,outputpaipian[i]);
				}

				
				ReRs.updateRow();
				search();

				System.out.println("update over");
				JOptionPane.showMessageDialog(null, "修改成功", "成功", JOptionPane.PLAIN_MESSAGE);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}
		
		
		private void quxiao(){
			dispose(); 
		}
		
		private int caculate_equal(){
			if( !pattern.matcher(x_pzhang.getText()).matches()  && (!x_pzhang.getText().equals("")) ) return 0 ;
			if( !pattern.matcher(y_pzhang.getText()).matches()  && (!y_pzhang.getText().equals("")) ) return 0 ;
			if( !pattern.matcher(xipian1.getText()).matches() && (!xipian1.getText().equals("")) ) return 0 ;
			if( !pattern.matcher(xipian2.getText()).matches() && (!xipian2.getText().equals("")) ) return 0 ;
			if( !pattern.matcher(xipian3.getText()).matches() && (!xipian3.getText().equals("")) ) return 0 ;
			
			
			BigDecimal bd1 = new BigDecimal(pattern.matcher(x_pzhang.getText()).matches()?x_pzhang.getText():"0.00");
			BigDecimal bd2 = new BigDecimal(pattern.matcher(y_pzhang.getText()).matches()?y_pzhang.getText():"0.00");
			
			BigDecimal bd3 = new BigDecimal(pattern.matcher(xipian1.getText()).matches()?xipian1.getText():"0.00");
			BigDecimal bd4 = new BigDecimal(pattern.matcher(xipian2.getText()).matches()?xipian2.getText():"0.00");
			BigDecimal bd5 = new BigDecimal(pattern.matcher(xipian3.getText()).matches()?xipian3.getText():"0.00");
			
			if(  bd1.add(bd2).equals(bd3.add(bd4).add(bd5))   ) return 1;
			else return 0 ;
		}
		
		private void caculate_xipian() {
			 if(jilu_num.getText().trim().equals("")){JOptionPane.showMessageDialog(null, "请先输入记录编号", "失败", JOptionPane.ERROR_MESSAGE);return ;}
			 String sql = "select sum(x_pzhang)+sum(y_pzhang) from workload_records_table where jilu_num='" + jilu_num.getText().trim() + "'";
			 ResultSet Caculaters =  sm.submit_search(sql) ;
			 try {
				Caculaters.next();
				xipian_total.setText(Caculaters.getString(1));
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	
	
	
}
