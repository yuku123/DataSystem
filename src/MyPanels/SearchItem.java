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
	private String group;//ȫ�ֱ����Ĵ�ֵ
	// �����ܶ�����ṩ����֧��
	private JPanel jp_tiaojian = new JPanel();
	private JPanel jp_jiben = new JPanel();
	private JPanel jp_xiangxi = new JPanel();//�ṩ��ϸ�����ĳ�װ���
	private JPanel jp_xiangxi_xiangmu = new JPanel();
	private JPanel jp_xiangxi_baogao = new JPanel();
	private JPanel jp_xiangxi_gongren = new JPanel();
	private JPanel jp_result =new JPanel();//�ṩ���table�Ĵ�����
	private JScrollPane jsp;
	private JTable jt;

	///////////////////////////////////////////////////////////////////////////////// ����������ԭ��
	// ����ѡ�񣬽��ж���Ŀ��ɸѡ
	private JRadioButton jrd1 = new JRadioButton("�ѽ���");
	private JRadioButton jrd2 = new JRadioButton("δ����");
	private JRadioButton jrd3 = new JRadioButton("ȫ��");
	private ButtonGroup bg = new ButtonGroup();
	// ���������˵�
	private JComboBox<String> jcb = new JComboBox<String>(new String[] { "��Ŀ���", "�������", "�������" });
	// ����������ť
	private JButton jb1 = new JButton("����");
	private JButton jb2 = new JButton("����");
	///////////////////////////////////////////////////////////////////////////////// ��Ŀ���Ԫ�ض���
	private JLabel jl_xiangmu_zhilingdanhao = new JLabel("ָ���");
	private JTextField jtf_xiangmu_zhilingdanhao = new JTextField(10);
	private JTextField[] jtf;
	private DBTableModel DBTableModel;//�ṩJTable֧�ֵ�һ��������
	private SQLManager SQLManager;//�ṩ���ݿ����
	

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
		// ��ɶ�JRadio��ƴװ

		bg.add(jrd1);
		bg.add(jrd2);
		bg.add(jrd3);
	}

	@Override
	public void initialComponentsConstruct() {
		///////// ����������JPanel
		jp_tiaojian.setLayout(new BorderLayout());
		jp_tiaojian.add(jp_jiben, BorderLayout.NORTH);
		jp_tiaojian.add(jp_xiangxi, BorderLayout.CENTER);
		jp_jiben.add(jrd1);
		jp_jiben.add(jrd2);
		jp_jiben.add(jrd3);// �������Jradio
		jp_jiben.add(jcb);// ���������
		jp_jiben.add(jb1);
		// ��ϸ��������Ŀ���
		String[] str = new String[] { "ָ���  ", "ί�е�λ  ", "��������  ", " ����ʱ��>", "<����ʱ��    " };
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
		//////////////////////////////////////////////////////////////////��ϸ����--����
		
		this.setLayout(new BorderLayout());
		this.add(jp_tiaojian,BorderLayout.NORTH);
		//this.add(jsp, BorderLayout.CENTER);
	}

	@Override
	public void initialComponentsPattern() {
		jrd3.setSelected(true);
		jp_jiben.setBorder(BorderFactory.createTitledBorder("��������"));
		jp_xiangxi.setBorder(BorderFactory.createTitledBorder("��ϸ����"));
	}

	@Override
	public void initialListener() {
		// �Բ�ͬ��panel�������
		jcb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String kinds = jcb.getSelectedItem().toString();
				if (kinds.equals("��Ŀ���")) {
					System.out.println("������Ŀ���");
					jp_xiangxi.removeAll();
					jp_xiangxi.add(jp_xiangxi_xiangmu);

				} else if (kinds.equals("�������")) {
					jp_xiangxi.removeAll();
					jp_xiangxi.add(jp_xiangxi_baogao);
				} else if (kinds.equals("�������")) {
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
				//�õ��������õ�ֵ�μ�sql��ƴװ
				cmd_num=jtf[0].getText().trim().equals("")?"true":" T.cmd_num='"+jtf[0].getText().trim()+"'";
				c_co=jtf[1].getText().trim().equals("")?" AND true":" AND T.c_co='"+jtf[1].getText().trim()+"'";
				pro_name=jtf[2].getText().trim().equals("")?" AND true":" AND T.pro_name='"+jtf[2].getText().trim()+"'";
				start_time=jtf[3].getText().trim().equals("")?" AND true":" AND T.start_time>='"+jtf[3].getText().trim()+"'";
				end_time=jtf[4].getText().trim().equals("")?" AND true":" AND T.end_time<='"+jtf[4].getText().trim()+"'";
				
				//�����������ʱ��ѡ�������Ŀ���
				if(kinds.equals("��Ŀ���")){
					System.out.println(cmd_num);
					System.out.println(c_co);
					System.out.println(pro_name);
					System.out.println(start_time);
					System.out.println(end_time);
					String sql=null;
					//���ѡ�����Ѿ�������ǰ���£�������ϸ������ֵ�������
					if (jrd1.isSelected()) {
						end_time=" AND T.end_time<=now()";
						sql = "SELECT T.in_id �ڲ���ʶ,T.cmd_num ָ����,T.c_co ί�е�λ,T.pro_name ��������,T.cmd_date ָ��´�����,T.c_contact ί�е�λ��ϵ��,T.c_phone ί�е�λ��ϵ�˵绰����,T.d_contact ��������ϵ��,T.start_time ����ʱ�� ,T.end_time ����ʱ�� FROM project_table T where "
								+cmd_num+c_co+pro_name+start_time+end_time+" and T.bm_name='"+group+"'";
						System.out.println(sql);
					}
					if(jrd2.isSelected()){
						//���ѡ��û�н�������ȡ��ֵӦ����"";
						end_time=" AND T.end_time is null";
						sql = "SELECT T.in_id �ڲ���ʶ,T.cmd_num ָ����,T.c_co ί�е�λ,T.pro_name ��������,T.cmd_date ָ��´�����,T.c_contact ί�е�λ��ϵ��,T.c_phone ί�е�λ��ϵ�˵绰����,T.d_contact ��������ϵ��,T.start_time ����ʱ�� ,T.end_time ����ʱ�� FROM project_table T where "
								+cmd_num+c_co+pro_name+start_time+end_time +" and T.bm_name='"+group+"'";
						System.out.println(sql);
					}
					if(jrd3.isSelected()){
						//ѡ��ȫ��������¿��Գ��ֵĽ��
						sql = "SELECT T.in_id �ڲ���ʶ,T.cmd_num ָ����,T.c_co ί�е�λ,T.pro_name ��������,T.cmd_date ָ��´�����,T.c_contact ί�е�λ��ϵ��,T.c_phone ί�е�λ��ϵ�˵绰����,T.d_contact ��������ϵ��,T.start_time ����ʱ�� ,T.end_time ����ʱ�� FROM project_table T where "
								+cmd_num+c_co+pro_name+start_time+end_time+" and T.bm_name='"+group+"'";
					}
					System.out.println(sql);
					ResultSet rs=SQLManager.submit_search(sql);
					repainth(rs);
				}
				if(kinds.equals("�������")){
					
				}
				if(kinds.equals("")){}
				
				
			}
		});

	}
	private void repainth(ResultSet rs) {
		// TODO Auto-generated method stub
		System.out.println("����"+(jsp != null));
		if (jsp != null) {
			this.remove(jsp);
			System.out.println("����"+(jsp != null));
		}
		DBTableModel= new DBTableModel(rs);
		jt = new JTable(DBTableModel);
		jsp = new JScrollPane(jt);
		
		jt.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                //Color color = Color.decode((String)table.getValueAt(row, 1));
            	if(row%2==0){
            	//��������ֶ��޸�Ư���ʺϵ���ɫ������RGBģʽ������ȥ���Ͻ�����ɫRGB��ѯ��ʽ�õ�RGBֵ
            	
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if( (!isSelected)&&(!hasFocus) ){Color color =new Color(242,242,242);comp.setBackground(color);}
                return comp;
                }
            	//����ǵ�ɫ���������ǰ�ɫ�Ϳ���	
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
