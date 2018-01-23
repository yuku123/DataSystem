package MyPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;

import MainFrame.DBTableModel;
import MainFrame.Feature;
import MainFrame.SQLManager;

public class SearchItem extends JPanel implements Feature, ActionListener {
	private String group;//全局变量的代值
	// 创建很多面板提供构造支持
	private JPanel jp_tiaojian = new JPanel();
	private JPanel jp_jiben = new JPanel();
	private JPanel jp_xiangxi = new JPanel();//提供详细条件的承装面板
	private JPanel jp_xiangxi_xiangmu = new JPanel();
	private JPanel jp_xiangxi_baogao = new JPanel();
	private JPanel jp_xiangxi_gongren = new JPanel();
	private JPanel jp_result =new JPanel();//提供结果table的存放面板
	private JScrollPane jsp;
	private JTable jt;

	///////////////////////////////////////////////////////////////////////////////// 基本条件的原件
	// 三个选择，进行对项目的筛选
	private JRadioButton jrd1 = new JRadioButton("已结束");
	private JRadioButton jrd2 = new JRadioButton("未结束");
	private JRadioButton jrd3 = new JRadioButton("全部");
	private ButtonGroup bg = new ButtonGroup();
	// 创建下拉菜单
	private JComboBox<String> jcb = new JComboBox<String>(new String[] { "项目相关", "报告相关", "工人相关" });
	// 创建两个按钮
	private JButton jb1 = new JButton("搜索");
	private JButton jb2 = new JButton("搜索");
	///////////////////////////////////////////////////////////////////////////////// 项目相关元素定义
	private JLabel jl_xiangmu_zhilingdanhao = new JLabel("指令单号");
	private JTextField jtf_xiangmu_zhilingdanhao = new JTextField(10);
	private JTextField[] jtf;
	private DBTableModel DBTableModel;//提供JTable支持的一个辅助类
	private SQLManager SQLManager;//提供数据库服务
	

	public SearchItem(SQLManager SQLManager, String group) {
		this.group=group;
		this.SQLManager=SQLManager;
		this.initialComponents();
		this.initialComponentsConstruct();
		this.initialComponentsPattern();
		this.initialListener();
	}

	@Override
	public void initialComponents() {
		// 完成对JRadio的拼装

		bg.add(jrd1);
		bg.add(jrd2);
		bg.add(jrd3);
	}

	@Override
	public void initialComponentsConstruct() {
		///////// 基本条件的JPanel
		jp_tiaojian.setLayout(new BorderLayout());
		jp_tiaojian.add(jp_jiben, BorderLayout.NORTH);
		jp_tiaojian.add(jp_xiangxi, BorderLayout.CENTER);
		jp_jiben.add(jrd1);
		jp_jiben.add(jrd2);
		jp_jiben.add(jrd3);// 添加三个Jradio
		jp_jiben.add(jcb);// 添加下拉框
		jp_jiben.add(jb1);
		// 详细条件—项目相关
		String[] str = new String[] { "指令单号  ", "委托单位  ", "工程名称  ", " 开工时间>", "<结束时间    " };
		jtf = new JTextField[str.length];

		JPanel jp_jtf = new JPanel();
		JPanel jp_label = new JPanel();
		for (int i = 0; i < str.length; i++) {
			jtf[i] = new JTextField(5);
			jp_jtf.add(jtf[i]);
		} //
		for (int i = 0; i < str.length; i++) {
			jp_label.add(new JLabel(str[i]));
		}

		jp_xiangxi_xiangmu.setLayout(new BorderLayout());
		jp_xiangxi_xiangmu.add(jp_label, BorderLayout.NORTH);
		jp_xiangxi_xiangmu.add(jp_jtf, BorderLayout.SOUTH);

		jp_xiangxi.add(jp_xiangxi_xiangmu);//
		//////////////////////////////////////////////////////////////////详细条件--工人
		
		this.setLayout(new BorderLayout());
		this.add(jp_tiaojian,BorderLayout.NORTH);
		//this.add(jsp, BorderLayout.CENTER);
	}

	@Override
	public void initialComponentsPattern() {
		jrd3.setSelected(true);
		jp_jiben.setBorder(BorderFactory.createTitledBorder("基本条件"));
		jp_xiangxi.setBorder(BorderFactory.createTitledBorder("详细条件"));
	}

	@Override
	public void initialListener() {
		// 对不同的panel进行添加
		jcb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String kinds = jcb.getSelectedItem().toString();
				if (kinds.equals("项目相关")) {
					System.out.println("点了项目相关");
					jp_xiangxi.removeAll();
					jp_xiangxi.add(jp_xiangxi_xiangmu);

				} else if (kinds.equals("报告相关")) {
					jp_xiangxi.removeAll();
					jp_xiangxi.add(jp_xiangxi_baogao);
				} else if (kinds.equals("工人相关")) {
					jp_xiangxi.removeAll();
					jp_xiangxi.add(jp_xiangxi_gongren);
				}
				updateUI();
			}
		});
		jb1.addActionListener(new ActionListener() {
			String kinds = jcb.getSelectedItem().toString();
			String cmd_num;
			String c_co;
			String pro_name;
			String start_time;
			String end_time;
			//String c_co=jtf[1].getText().trim().equals("")?jtf[1].getText().trim():"true";
			//String pro_name=jtf[2].getText().trim().equals("")?"true":jtf[1].getText().trim();
			//String start_time=jtf[3].getText().trim().equals("")?"true":jtf[1].getText().trim();
			//String end_time=jtf[4].getText().trim().equals("")?"true":jtf[1].getText().trim();
			

			@Override
			public void actionPerformed(ActionEvent e) {
				//得到各种设置的值参加sql的拼装
				cmd_num=jtf[0].getText().trim().equals("")?"true":" T.cmd_num='"+jtf[0].getText().trim()+"'";
				c_co=jtf[1].getText().trim().equals("")?" AND true":" AND T.c_co='"+jtf[1].getText().trim()+"'";
				pro_name=jtf[2].getText().trim().equals("")?" AND true":" AND T.pro_name='"+jtf[2].getText().trim()+"'";
				start_time=jtf[3].getText().trim().equals("")?" AND true":" AND T.start_time>='"+jtf[3].getText().trim()+"'";
				end_time=jtf[4].getText().trim().equals("")?" AND true":" AND T.end_time<='"+jtf[4].getText().trim()+"'";
				
				//如果单击搜索时，选择的是项目相关
				if(kinds.equals("项目相关")){
					System.out.println(cmd_num);
					System.out.println(c_co);
					System.out.println(pro_name);
					System.out.println(start_time);
					System.out.println(end_time);
					String sql=null;
					//如果选择了已经结束的前提下，而且详细里面有值的情况下
					if (jrd1.isSelected()) {
						end_time=" AND T.end_time<=now()";
						sql = "SELECT T.in_id 内部标识,T.cmd_num 指令单编号,T.c_co 委托单位,T.pro_name 工程名称,T.cmd_date 指令单下达日期,T.c_contact 委托单位联系人,T.c_phone 委托单位联系人电话号码,T.d_contact 本部门联系人,T.start_time 开工时间 ,T.end_time 结束时间 FROM project_table T where "
								+cmd_num+c_co+pro_name+start_time+end_time+" and T.bm_name='"+group+"'";
						System.out.println(sql);
					}
					if(jrd2.isSelected()){
						//如果选择还没有结束，则取出值应该是"";
						end_time=" AND T.end_time is null";
						sql = "SELECT T.in_id 内部标识,T.cmd_num 指令单编号,T.c_co 委托单位,T.pro_name 工程名称,T.cmd_date 指令单下达日期,T.c_contact 委托单位联系人,T.c_phone 委托单位联系人电话号码,T.d_contact 本部门联系人,T.start_time 开工时间 ,T.end_time 结束时间 FROM project_table T where "
								+cmd_num+c_co+pro_name+start_time+end_time +" and T.bm_name='"+group+"'";
						System.out.println(sql);
					}
					if(jrd3.isSelected()){
						//选择全部的情况下可以出现的结果
						sql = "SELECT T.in_id 内部标识,T.cmd_num 指令单编号,T.c_co 委托单位,T.pro_name 工程名称,T.cmd_date 指令单下达日期,T.c_contact 委托单位联系人,T.c_phone 委托单位联系人电话号码,T.d_contact 本部门联系人,T.start_time 开工时间 ,T.end_time 结束时间 FROM project_table T where "
								+cmd_num+c_co+pro_name+start_time+end_time+" and T.bm_name='"+group+"'";
					}
					System.out.println(sql);
					ResultSet rs=SQLManager.submit_search(sql);
					repainth(rs);
				}
				if(kinds.equals("工人相关")){
					
				}
				if(kinds.equals("")){}
				
				
			}
		});

	}
	private void repainth(ResultSet rs) {
		// TODO Auto-generated method stub
		System.out.println("外面"+(jsp != null));
		if (jsp != null) {
			this.remove(jsp);
			System.out.println("里面"+(jsp != null));
		}
		DBTableModel= new DBTableModel(rs);
		jt = new JTable(DBTableModel);
		jsp = new JScrollPane(jt);
		
		jt.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                //Color color = Color.decode((String)table.getValueAt(row, 1));
            	if(row%2==0){
            	//这里可以手动修改漂亮适合的颜色，采用RGB模式，可以去网上进行颜色RGB查询方式得到RGB值
            	
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if( (!isSelected)&&(!hasFocus) ){Color color =new Color(242,242,242);comp.setBackground(color);}
                return comp;
                }
            	//这个是底色，就让他是白色就可以	
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if((!isSelected)&&(!hasFocus)){Color color = Color.white;comp.setBackground(color);}
                return comp;
            }
            
        });
		
		//jps.add(jsp);
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		DefaultTableColumnModel dcm = (DefaultTableColumnModel)jt.getColumnModel(); 
	    dcm.getColumn(0).setMinWidth(0); 
	    dcm.getColumn(0).setMaxWidth(0); 	
	    this.add(jsp,BorderLayout.CENTER);
		this.revalidate();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setSize(500, 500);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(new SearchItem(new SQLManager(),"bumen1"));
		jf.setVisible(true);

	}

}
