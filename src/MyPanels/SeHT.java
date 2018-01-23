package MyPanels;

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

@SuppressWarnings("serial")
public class SeHT extends JPanel {
	

	/**
	 * 查合同表
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
	 private JButton getPDFJB;
	 private JLabel label_6;
	 private JButton deleteJB;
	 private JLabel ht_num_label;
	 private JTextField ht_num;
	 private JLabel label;
	 private JButton renewJB;
	
	
	
	public SeHT(SQLManager s, String group) {
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
		gbl_top.rowHeights = new int[]{0, 0, 0};
		gbl_top.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_top.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
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
		
		//TODO 合同编号
		ht_num_label = new JLabel("\u5408\u540C\u7F16\u53F7");
		GridBagConstraints gbc_ht_num_label = new GridBagConstraints();
		gbc_ht_num_label.anchor = GridBagConstraints.EAST;
		gbc_ht_num_label.insets = new Insets(0, 0, 0, 5);
		gbc_ht_num_label.gridx = 0;
		gbc_ht_num_label.gridy = 1;
		top.add(ht_num_label, gbc_ht_num_label);
		
		ht_num = new JTextField();
		GridBagConstraints gbc_ht_num = new GridBagConstraints();
		gbc_ht_num.insets = new Insets(0, 0, 0, 5);
		gbc_ht_num.fill = GridBagConstraints.HORIZONTAL;
		gbc_ht_num.gridx = 1;
		gbc_ht_num.gridy = 1;
		top.add(ht_num, gbc_ht_num);
		ht_num.setColumns(10);
		
		
		
		
		
		
		
		
		
		
		
		
		
		//TODO 底部容器
		bottom = new JPanel();
		add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		label4 = new JLabel("        ");
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
		
		//删除按钮
		deleteJB = new JButton("删除");
		deleteJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		bottom.add(deleteJB);	
	
		
		label_6 = new JLabel("            ");
		bottom.add(label_6);
		

		//TODO 修改PDF
		getPDFJB = new JButton("\u67E5\u770B\u5408\u540C\u6587\u4EF6");
		getPDFJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPDF();	
			}
		});
		bottom.add(getPDFJB);
		
		label = new JLabel("             ");
		bottom.add(label);
		
		renewJB = new JButton("\u5237\u65B0");
		renewJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				renew();
			}
		});
		bottom.add(renewJB);
	}
	
	
	
	
	//TODO 得到中间table
	private void repainth(ResultSet rs) {
		// TODO 得到中间table
		if (jsp != null) this.remove(jsp);
		DBTableModel = new DBTableModel(rs);
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
	    //dcm.getColumn(0).setMinWidth(0); 
	    //dcm.getColumn(0).setMaxWidth(0); 	
		add(jsp, BorderLayout.CENTER);
		this.revalidate();	
	}
	
	
	
	
	
	//TODO 查询操作
	private void search(){
		String seStr[] = new String[15];
		String sql = "select  ht_id, cmd_num 指令单号, ht_num 合同编号, ht_price 合同价   from hetong_table where bm_name= '" + group +"'";
		int i = 0;
		//0处理指令单号
		seStr[0] = cb0.getText().trim().equals("") ? null : cb0.getText().trim() ;
		if(seStr[0] != null){
			sql += " and cmd_num like '"+ seStr[0] +"%' " ;
			i++;
		}
		//1 处理合同编号
		seStr[1] = ht_num.getText().trim().equals("") ? null : ht_num.getText().trim() ;
		if(seStr[1] != null){
			sql += " and  ht_num like '"+ seStr[1] +"%' " ;
			i++;
		}
		//后处理
		sql+=" ;";
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
			int op = JOptionPane.showConfirmDialog(this, "确认删除合同编号为 "+ rs.getString(3) +" 的合同？", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(op == JOptionPane.CANCEL_OPTION || op == JOptionPane.CLOSED_OPTION ) return ;
			rs.deleteRow();
			search();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//文件管理按钮
	private void getPDF(){
		int rowindex=jt.getSelectedRow();
		int rowcount = jt.getSelectedRowCount() ;
		if(rowcount == 0){JOptionPane.showMessageDialog(null, "选择需要修改的行", "失败", JOptionPane.ERROR_MESSAGE); return ;}
		if(rowcount > 1){JOptionPane.showMessageDialog(null, "只能选择一行", "失败", JOptionPane.ERROR_MESSAGE); return ;}
		String str=jt.getValueAt(rowindex, 0).toString();//取得ht_id 
		String sql = "select ht_id, ht_file_name , ht_file from hetong_table where ht_id='" + str + "'" ;
		System.out.println(sql);
		
		new FileMan(this ,sql,str );
		
	}
	
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
		
		updateUI();
		
	}
	
	
	
	
	private class FileMan extends JDialog {

		private String sql;
		private ResultSet ReRs;
		private final JPanel contentPanel = new JPanel();
		private JTextField textField;
		private JButton downloadJB;
		private JButton modifyJB;
		private JButton deleteJB;
		private String  id_num ;//合同id

		/**
		 * Create the dialog.
		 */
		FileMan(JPanel jp ,String sqll,String id_numm ) {
			super( (JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, jp) , "管理合同文件", true);
			this.sql = sqll;
			this.id_num = id_numm ;
			initHTFile();
			ReRs = sm.submit_search(sql);
			initData();
			pack();
			setVisible(true);
			

		}
		
		private void initHTFile(){
			setBounds(100, 100, 450, 300);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			GridBagLayout gbl_contentPanel = new GridBagLayout();
			gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
			gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
			gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			contentPanel.setLayout(gbl_contentPanel);
			
			// TODO 文件信息文本框
			textField = new JTextField();
			textField.setEditable(false);
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.gridwidth = 3;
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.gridx = 0;
			gbc_textField.gridy = 0;
			contentPanel.add(textField, gbc_textField);
			textField.setColumns(50);
			
			//下载按钮
			downloadJB = new JButton("\u4E0B\u8F7D");
			downloadJB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					downFile();
				}
			});
			GridBagConstraints gbc_downloadJB = new GridBagConstraints();
			gbc_downloadJB.insets = new Insets(0, 0, 0, 5);
			gbc_downloadJB.gridx = 0;
			gbc_downloadJB.gridy = 1;
			contentPanel.add(downloadJB, gbc_downloadJB);
			
			//修改按钮
			modifyJB = new JButton("\u4FEE\u6539");
			modifyJB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modifyFile();
				}
			});
			GridBagConstraints gbc_modifyJB = new GridBagConstraints();
			gbc_modifyJB.insets = new Insets(0, 0, 0, 5);
			gbc_modifyJB.gridx = 1;
			gbc_modifyJB.gridy = 1;
			contentPanel.add(modifyJB, gbc_modifyJB);
			
			//删除按钮
			deleteJB = new JButton("\u5220\u9664");
			deleteJB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteFile();
				}
			});
			GridBagConstraints gbc_deleteJB = new GridBagConstraints();
			gbc_deleteJB.gridx = 2;
			gbc_deleteJB.gridy = 1;
			contentPanel.add(deleteJB, gbc_deleteJB);
			
			
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		}
		
		
		private void initData(){
			try {
				ReRs.next();
				textField.setText(ReRs.getString(2)==null? "" : ReRs.getString(2));
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		//TODO 下载文件
		private void downFile(){

			try {
				if(ReRs.getBlob(3)==null) {JOptionPane.showMessageDialog(null, "从未上传过文件", "失败", JOptionPane.ERROR_MESSAGE); return ;}

				//文件选择器，选择输出文件夹
				JFileChooser jfc=new JFileChooser();
				jfc.setMultiSelectionEnabled(false);
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int jfcr=jfc.showDialog(this,"保存");
				if(jfcr==JFileChooser.APPROVE_OPTION){
					Blob fileBlob = ReRs.getBlob(3);
					String filename = ReRs.getString(2);
					File jfcf=jfc.getSelectedFile();//获取选择文件的具体地址
					String jfcp = jfcf.getPath();
					String outputName = jfcp + "\\" + filename;
					System.out.println(outputName);
					File outputFile=new File(outputName);
				    if(outputFile.exists()) outputFile.delete();
					try {  
				        	outputFile.createNewFile();  
				        	byte[] Buffer = new byte[4096*5];
				        	FileOutputStream outputStream = new FileOutputStream(outputFile);
				        	InputStream iStream =fileBlob.getBinaryStream();
				            int size=0;  
				            while((size=iStream.read(Buffer))!=-1)  
				            {  
				                System.out.println(size);  
				                outputStream.write(Buffer,0,size);  
				            } 	
				            outputStream.close();
				            JOptionPane.showMessageDialog(null, "已下载", "成功", JOptionPane.PLAIN_MESSAGE);
				        } catch (Exception e) {  
				            e.printStackTrace();  
				        }
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		//TODO 修改文件
		private void modifyFile(){
			JFileChooser jfc=new JFileChooser();
			jfc.setMultiSelectionEnabled(false);
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int jfcr=jfc.showDialog(this,"提交");
			if(jfcr==JFileChooser.APPROVE_OPTION){
				File jfcf=jfc.getSelectedFile();//获取对话框的具体地址
				String jfcp = jfcf.getPath();
				System.out.println(jfcp);
				textField.setText(jfcp);
				System.out.println(textField.getText().trim().substring(textField.getText().lastIndexOf('\\')+1));
				if(!textField.getText().equals("")){
					String updatesql =  "update hetong_table set ht_file=? ,ht_file_name=? where ht_id=? ";
					try (PreparedStatement pstmt = sm.getConn().prepareStatement(updatesql);
						 	
							){
						File f = new File(textField.getText());
						InputStream is = new FileInputStream(f);
						pstmt.setBinaryStream(1, is, f.length());
						pstmt.setString(2, textField.getText().trim().substring(textField.getText().lastIndexOf('\\')+1));
						pstmt.setString(3,id_num);
						pstmt.executeUpdate();
						ReRs.refreshRow() ;
						JOptionPane.showMessageDialog(null, "修改成功", "成功", JOptionPane.PLAIN_MESSAGE);
					}catch (Exception e) {
						// 如果有异常,重连
						e.printStackTrace();
						sm.reconnect();
						JOptionPane.showMessageDialog(null, "请检查网络连接，再次尝试或重新打开软件", "更新失败", JOptionPane.ERROR_MESSAGE);

					}
				}else {JOptionPane.showMessageDialog(null, "请选择文件修改", "失败", JOptionPane.ERROR_MESSAGE); return ;}
				
			}
		}
		
		
		private void deleteFile(){
			try {
				int op = JOptionPane.showConfirmDialog(this, "确认删除该文件？", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(op == JOptionPane.CANCEL_OPTION || op == JOptionPane.CLOSED_OPTION ) return ;
				if(ReRs.getBlob(3)==null) {JOptionPane.showMessageDialog(null, "从未上传过文件", "失败", JOptionPane.ERROR_MESSAGE); return ;}
				ReRs.updateNull(2);
				ReRs.updateNull(3);
				textField.setText("");
				ReRs.updateRow();
				ReRs.refreshRow() ;
				JOptionPane.showMessageDialog(null, "删除成功", "成功", JOptionPane.PLAIN_MESSAGE);
				
			}  catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		

	}
	
	
	
	
	
	
}





