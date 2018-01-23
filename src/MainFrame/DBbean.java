package MainFrame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

public class DBbean{
	
	private String group;// ����ȫ�ֱ���
	private String host;
	private String user=Password.user;
	private String password=Password.password;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private Properties props;
	private String driver;
	private String ip;
	private String port;
	private String db;
	private String url;
	private String date1;
	private String date2;
	private String dateyuechu;
	private PreparedStatement pstm;
	private String output_liushui;
	private String output_gongren;

	public DBbean(String group) {
		this.group = group;
		output_gongren =" select '',"
				+" t1.workperson,"
				+" t1.cmd_num,"
				+" sum(case when t1.x_pci is not null then 0 else ifnull(t1.x_pzhang,0) end) ��ͨx����,"
				+" t2.x_pzhang_price ��ͨx���ż۸�,"
				+" sum(case when t1.x_pci is not null then 0 else ifnull(t1.x_pzhang,0) end)*t2.x_pzhang_price x������ֵ,"
				+" sum(case when t1.x_pci is not null then ifnull(t1.x_pzhang,0) else 0  end)  x���Ű��ε���Ƭ������,"
				+" sum(case when t1.x_pci is not null then 1 else 0 end) һ����ȥ������,"
				+" group_concat(distinct ifnull(t1.x_pci,'')) �����������ͳ����,"
				+" sum(ifnull(t1.x_pci,0)) �ε���ֵ,"
				+" sum(case when t1.y_pci is not null then 0 else ifnull(t1.y_pzhang,0) end) ��ͨY��,"
				+" t2.y_pzhang_price ��ͨY�ż۸�,"
				+" sum(case when t1.y_pci is not null then 0 else ifnull(t1.y_pzhang,0) end)*t2.y_pzhang_price Y����ֵ,"
				+" sum(case when t1.y_pci is not null then ifnull(t1.y_pzhang,0) else 0  end)  Y�Ű��ε���Ƭ������,"
				+" sum(case when t1.y_pci is not null then 1 else 0 end) һ����ȥ������,"
				+" group_concat(distinct ifnull(t1.y_pci,'')) �����������ͳ����,"
				+" sum(ifnull(t1.y_pci,0)) �ε���ֵ,"
				+" sum(case when t1.chaosheng_pci is not null then 0 else ifnull(t1.chaosheng_pmi,0) end) ��ͨ������,"
				+" t2.chaosheng_pmi_price ��ͨ�����׼۸�,"
				+" sum(case when t1.chaosheng_pci is not null then 0 else ifnull(t1.chaosheng_pmi,0) end)*t2.chaosheng_pmi_price ��������ֵ,"
				+" sum(case when t1.chaosheng_pci is not null then 0 else ifnull(t1.chaosheng_pdao,0) end) ��ͨ������,"
				+" t2.chaosheng_pdao_price ��ͨ�������۸�,"
				+" sum(case when t1.chaosheng_pci is not null then 0 else ifnull(t1.chaosheng_pdao,0) end)*t2.chaosheng_pdao_price ��������ֵ,"
				+" concat"
				+" ("
				+" '�����ף�',ifnull(sum(case when t1.chaosheng_pci is not null then ifnull(t1.chaosheng_pmi,0) else 0  end),0),"
				+" ',',"
				+" '���ε���',ifnull(sum(case when t1.chaosheng_pci is not null then ifnull(t1.chaosheng_pdao,0) else 0  end),0)"
				+" ),"
				+" sum(case when t1.chaosheng_pci is not null then 1 else 0 end) һ����ȥ������,"
				+" group_concat(distinct ifnull(t1.chaosheng_pci,'')) �����������ͳ����,"
				+" sum(ifnull(t1.chaosheng_pci,0)) �ε���ֵ,"
				+" sum(case when t1.cifen_pci is not null then 0 else ifnull(t1.cifen_pmi,0) end) ��ͨ�ŷ���,"
				+" t2.cifen_pmi_price ��ͨ�ŷ��׼۸�,"
				+" sum(case when t1.cifen_pci is not null then 0 else ifnull(t1.cifen_pmi,0) end)*t2.cifen_pmi_price �ŷ�����ֵ,"
				+" sum(case when t1.cifen_pci is not null then 0 else ifnull(t1.cifen_pdao,0) end) ��ͨ�ŷ۵�,"
				+" t2.cifen_pdao_price ��ͨ�ŷ۵��۸�,"
				+" sum(case when t1.cifen_pci is not null then 0 else ifnull(t1.cifen_pdao,0) end)*t2.cifen_pdao_price �ŷ۵���ֵ,"
				+" concat"
				+" ("
				+" '�����ף�',ifnull(sum(case when t1.cifen_pci is not null then ifnull(t1.cifen_pmi,0) else 0  end),0),"
				+" ',',"
				+" '���ε���',ifnull(sum(case when t1.cifen_pci is not null then ifnull(t1.cifen_pdao,0) else 0  end),0)"
				+" ),"
				+" sum(case when t1.cifen_pci is not null then 1 else 0 end) һ����ȥ������,"
				+" group_concat(distinct ifnull(t1.cifen_pci,'')) �����������ͳ����,"
				+" sum(ifnull(t1.cifen_pci,0)) �ε���ֵ,"
				+" sum(case when t1.shentou_pci is not null then 0 else ifnull(t1.shentou_pmi,0) end) ��ͨ��͸��,"
				+" t2.shentou_pmi_price ��ͨ��͸�׼۸�,"
				+" sum(case when t1.shentou_pci is not null then 0 else ifnull(t1.shentou_pmi,0) end)*t2.shentou_pmi_price ��͸����ֵ,"
				+" sum(case when t1.shentou_pci is not null then 0 else ifnull(t1.shentou_pdao,0) end) ��ͨ��͸��,"
				+" t2.shentou_pdao_price ��ͨ��͸���۸�,"
				+" sum(case when t1.shentou_pci is not null then 0 else ifnull(t1.shentou_pdao,0) end)*t2.shentou_pdao_price ��͸����ֵ,"
				+" concat"
				+" ("
				+" '�����ף�',ifnull(sum(case when t1.shentou_pci is not null then ifnull(t1.shentou_pmi,0) else 0  end),0),"
				+" ',',"
				+" '���ε���',ifnull(sum(case when t1.shentou_pci is not null then ifnull(t1.shentou_pdao,0) else 0  end),0)"
				+" ),	"
				+" sum(case when t1.shentou_pci is not null then 1 else 0 end) һ����ȥ������,"
				+" group_concat(distinct ifnull(t1.shentou_pci,'')) �����������ͳ����,"
				+" sum(ifnull(t1.shentou_pci,0)) �ε���ֵ,"
				+" sum(case when t1.cehou_pci is not null then 0 else ifnull(t1.cehou_pdian,0) end) ��ͨ����,"
				+" t2.cehou_pdian_price ��ͨ����۸�,"
				+" sum(case when t1.cehou_pci is not null then 0 else ifnull(t1.cehou_pdian,0) end)*t2.cehou_pdian_price ������ֵ,"
				+" sum(case when t1.cehou_pci is not null then ifnull(t1.cehou_pdian,0) else 0  end)  ���㰴�ε���Ƭ������,"
				+" sum(case when t1.cehou_pci is not null then 1 else 0 end) һ����ȥ������,"
				+" group_concat(distinct ifnull(t1.cehou_pci,'')) �����������ͳ����,"
				+" sum(ifnull(t1.cehou_pci,0)) �ε���ֵ,"
				+" sum(ifnull(t1.biaopei_pri,0)) ��ͨ������Ϲ���,"
				+" t2.biaopei_pri_price ��ͨ������Ϲ��ռ۸�,"
				+" sum(ifnull(t1.biaopei_pri,0))*t2.biaopei_pri_price ������Ϲ�����ֵ,"
				+" sum(case when t1.guangpuquan_pci is not null then 0 else ifnull(t1.guangpuquan_pdian,0) end) ��ͨȫ������,"
				+" t2.guangpuquan_pdian_price ��ͨȫ������۸�,"
				+" sum(case when t1.guangpuquan_pci is not null then 0 else ifnull(t1.guangpuquan_pdian,0) end)*t2.guangpuquan_pdian_price ȫ��������ֵ,"
				+" sum(case when t1.guangpuquan_pci is not null then 0 else ifnull(t1.guangpuquan_pge,0) end) ��ͨȫ������Ԫ��,"
				+" t2.guangpuquan_pge_price ��ͨȫ������Ԫ�ؼ۸�,"
				+" sum(case when t1.guangpuquan_pci is not null then 0 else ifnull(t1.guangpuquan_pge,0) end)*t2.guangpuquan_pge_price ȫ������Ԫ����ֵ,"
				+" concat"
				+" ("
				+" '����ȫ�㣺',ifnull(sum(case when t1.guangpuquan_pci is not null then ifnull(t1.guangpuquan_pdian,0) else 0  end),0),"
				+" ',',"
				+" '����ȫ����',ifnull(sum(case when t1.guangpuquan_pci is not null then ifnull(t1.guangpuquan_pge,0) else 0  end),0)"
				+" ),"
				+" sum(case when t1.guangpuquan_pci is not null then 1 else 0 end) һ����ȥ������,"
				+" group_concat(distinct ifnull(t1.guangpuquan_pci,'')) �����������ͳ����,"
				+" sum(ifnull(t1.guangpuquan_pci,0)) �ε���ֵ,"
				+" sum(case when t1.guangpuban_pci is not null then 0 else ifnull(t1.guangpuban_pdian,0) end) ��ͨ�붨����,"
				+" t2.guangpuban_pdian_price ��ͨ�붨����۸�,"
				+" sum(case when t1.guangpuban_pci is not null then 0 else ifnull(t1.guangpuban_pdian,0) end)*t2.guangpuban_pdian_price �붨������ֵ,"
				+" sum(case when t1.guangpuban_pci is not null then 0 else ifnull(t1.guangpuban_pge,0) end) ��ͨ�붨����Ԫ��,"
				+" t2.guangpuban_pge_price ��ͨ�붨����Ԫ�ؼ۸�,"
				+" sum(case when t1.guangpuban_pci is not null then 0 else ifnull(t1.guangpuban_pge,0) end)*t2.guangpuban_pge_price �붨����Ԫ����ֵ,"
				+" concat"
				+" ("
				+" '���ΰ�㣺',ifnull(sum(case when t1.guangpuban_pci is not null then ifnull(t1.guangpuban_pdian,0) else 0  end),0),"
				+" ',',"
				+" '���ΰ����',ifnull(sum(case when t1.guangpuban_pci is not null then ifnull(t1.guangpuban_pge,0) else 0  end),0)"
				+" ),"
				+" sum(case when t1.guangpuban_pci is not null then 1 else 0 end) һ����ȥ������,"
				+" group_concat(distinct ifnull(t1.guangpuban_pci,'')) �����������ͳ����,"
				+" sum(ifnull(t1.guangpuban_pci,0)) �ε���ֵ,"
				+" sum(case when t1.yingdu_pci is not null then 0 else ifnull(t1.yingdu_pdian,0) end) ��ͨӲ�ȵ�,"
				+" t2.yingdu_pdian_price ��ͨӲ�ȵ�۸�,"
				+" sum(case when t1.yingdu_pci is not null then 0 else ifnull(t1.yingdu_pdian,0) end)*t2.yingdu_pdian_price Ӳ�ȵ���ֵ,"
				+" sum(case when t1.yingdu_pci is not null then ifnull(t1.yingdu_pdian,0) else 0  end)  Ӳ�ȵ㰴�ε���Ƭ������,"
				+" sum(case when t1.yingdu_pci is not null then 1 else 0 end) һ����ȥ������,"
				+" group_concat(distinct ifnull(t1.yingdu_pci,'')) �����������ͳ����,"
				+" sum(ifnull(t1.yingdu_pci,0)) �ε���ֵ,"
				+" sum(case when t1.damo_pci is not null then 0 else ifnull(t1.damo_pmi,0) end) ��ͨ��ĥ��,"
				+" t2.damo_pmi_price ��ͨ��ĥ�׼۸�,"
				+" sum(case when t1.damo_pci is not null then 0 else ifnull(t1.damo_pmi,0) end)*t2.damo_pmi_price ��ĥ����ֵ,"
				+" sum(case when t1.damo_pci is not null then ifnull(t1.damo_pmi,0) else 0  end)  ��ĥ�װ��ε���Ƭ������,"
				+" sum(case when t1.damo_pci is not null then 1 else 0 end) һ����ȥ������,"
				+" group_concat(distinct ifnull(t1.damo_pci,'')) �����������ͳ����,"
				+" sum(ifnull(t1.damo_pci,0)) �ε���ֵ,"
				+" sum(case when t1.tofd_pci is not null then 0 else ifnull(t1.tofd_pmi,0) end) ��ͨTOFD��,"
				+" t2.tofd_pmi_price ��ͨTOFD�׼۸�,"
				+" sum(case when t1.tofd_pci is not null then 0 else ifnull(t1.tofd_pmi,0) end)*t2.tofd_pmi_price TOFD����ֵ,"
				+" sum(case when t1.tofd_pci is not null then ifnull(t1.tofd_pmi,0) else 0  end)  TOFD�װ��ε���Ƭ������,"
				+" sum(case when t1.tofd_pci is not null then 1 else 0 end) һ����ȥ������,"
				+" group_concat(distinct ifnull(t1.tofd_pci,'')) �����������ͳ����,"
				+" sum(ifnull(t1.tofd_pci,0)) �ε���ֵ,"
				+" sum(case when t1.lashen_pci is not null then 0 else ifnull(t1.lashen_pjian,0) end) ��ͨ�����,"
				+" t2.lashen_pjian_price ��ͨ������۸�,"
				+" sum(case when t1.lashen_pci is not null then 0 else ifnull(t1.lashen_pjian,0) end)*t2.lashen_pjian_price �������ֵ,"
				+" sum(case when t1.lashen_pci is not null then ifnull(t1.lashen_pjian,0) else 0  end)  ��������ε���Ƭ������,"
				+" sum(case when t1.lashen_pci is not null then 1 else 0 end) һ����ȥ������,"
				+" group_concat(distinct ifnull(t1.lashen_pci,'')) �����������ͳ����,"
				+" sum(ifnull(t1.lashen_pci,0)) �ε���ֵ,"
				+" sum(case when t1.wanqu_pci is not null then 0 else ifnull(t1.wanqu_pjian,0) end) ��ͨ������,"
				+" t2.wanqu_pjian_price ��ͨ�������۸�,"
				+" sum(case when t1.wanqu_pci is not null then 0 else ifnull(t1.wanqu_pjian,0) end)*t2.wanqu_pjian_price ��������ֵ,"
				+" sum(case when t1.wanqu_pci is not null then ifnull(t1.wanqu_pjian,0) else 0  end)  ���������ε���Ƭ������,"
				+" sum(case when t1.wanqu_pci is not null then 1 else 0 end) һ����ȥ������,"
				+" group_concat(distinct ifnull(t1.wanqu_pci,'')) �����������ͳ����,"
				+" sum(ifnull(t1.wanqu_pci,0)) �ε���ֵ,"
				+" sum(case when t1.chongji_pci is not null then 0 else ifnull(t1.chongji_pjian,0) end) ��ͨ�����,"
				+" t2.chongji_pjian_price ��ͨ������۸�,"
				+" sum(case when t1.chongji_pci is not null then 0 else ifnull(t1.chongji_pjian,0) end)*t2.chongji_pjian_price �������ֵ,"
				+" sum(case when t1.chongji_pci is not null then ifnull(t1.chongji_pjian,0) else 0  end)  ��������ε���Ƭ������,"
				+" sum(case when t1.chongji_pci is not null then 1 else 0 end) һ����ȥ������,"
				+" group_concat(distinct ifnull(t1.chongji_pci,'')) �����������ͳ����,"
				+" sum(ifnull(t1.chongji_pci,0)) �ε���ֵ,"
				+" sum(case when t1.tie_pci is not null then 0 else ifnull(t1.tie_pdian,0) end) ��ͨ�������,"
				+" t2.tie_pdian_price ��ͨ�������۸�,"
				+" sum(case when t1.tie_pci is not null then 0 else ifnull(t1.tie_pdian,0) end)*t2.tie_pdian_price ���������ֵ,"
				+" sum(case when t1.tie_pci is not null then ifnull(t1.tie_pdian,0) else 0  end)  ������㰴�ε���Ƭ������,"
				+" sum(case when t1.tie_pci is not null then 1 else 0 end) һ����ȥ������,"
				+" group_concat(distinct ifnull(t1.tie_pci,'')) �����������ͳ����,"
				+" sum(ifnull(t1.tie_pci,0)) �ε���ֵ,"
				+" sum(case when t1.jinxiang_pci is not null then 0 else ifnull(t1.jinxiang_pjian,0) end) ��ͨ�����,"
				+" t2.jinxiang_pjian_price ��ͨ������۸�,"
				+" sum(case when t1.jinxiang_pci is not null then 0 else ifnull(t1.jinxiang_pjian,0) end)*t2.jinxiang_pjian_price �������ֵ,"
				+" sum(case when t1.jinxiang_pci is not null then ifnull(t1.jinxiang_pjian,0) else 0  end)  ��������ε���Ƭ������,"
				+" sum(case when t1.jinxiang_pci is not null then 1 else 0 end) һ����ȥ������,"
				+" group_concat(distinct ifnull(t1.jinxiang_pci,'')) �����������ͳ����,"
				+" sum(ifnull(t1.jinxiang_pci,0)) �ε���ֵ,"
				+" sum(ifnull(t1.diankou_pamount,0)) ��ͨ��ڡ��ؼ�Ժ��ϡ�����ָ������,"
				+" t2.diankou_pamount_price ��ͨ��ڡ��ؼ�Ժ��ϡ�����ָ�������۸�,"
				+" sum(ifnull(t1.diankou_pamount,0))*t2.diankou_pamount_price ����ؼ�Ժ��ϼ���ָ��������ֵ,"
				+" sum(ifnull(t1.qita1_pamount,0)),"
				+" t2.qita1_pamount_price,"
				+" sum(ifnull(t1.qita1_pamount,0))*t2.qita1_pamount_price,"
				+" sum(ifnull(t1.qita2_pamount,0)),"
				+" t2.qita2_pamount_price,"
				+" sum(ifnull(t1.qita2_pamount,0))*t2.qita2_pamount_price,"
				+" sum(ifnull(t1.qita3_pamount,0)),"
				+" t2.qita3_pamount_price,"
				+" sum(ifnull(t1.qita3_pamount,0))*t2.qita3_pamount_price"
				+" ,''"
				+" ,''"
				+" ,''"
				+" ,''"
				+" ,''"
				+" ,''"
				+" ,''"
				+" ,''"
				+" ,''"
				+" ,''"
				+" ,group_concat(distinct ifnull(t1.shiwu_beizhu,''))"
				+" ,''"
				+" from"
				+" workload_records_table t1 left join duinei_price_table t2 on"
				+" t1.cmd_num=t2.cmd_num where t1.workdate>=? and t1.workdate<=? and t1.bm_name='"+group+"'"
				+" group by t1.workperson,t1.cmd_num;";
		output_liushui = "select "
				+ " T2.person1,T2.amount1,T2.person2,T2.amount2,T2.person3,T2.amount3,T2.workdate,T2.workperson,T3.cmd_num, T3.c_co,T3.pro_name,"
				+ " T2.x_pzhang, T2.x_pfei, T2.y_pzhang, T2.y_pfei, T2.chaosheng_pmi, T2.chaosheng_pdao,T2.cifen_pmi, T2.cifen_pdao,T2.shentou_pmi, T2.shentou_pdao,T2.cehou_pdian, T2.biaopei_pri, T2.guangpuquan_pdian, T2.guangpuquan_pge,T2.guangpuban_pdian, T2.guangpuban_pge, T2.yingdu_pdian, T2.damo_pmi,T2.tofd_pmi, T2.lashen_pjian,T2.wanqu_pjian,T2.chongji_pjian, T2.tie_pdian,T2.jinxiang_pjian,T2.diankou_pamount, qita1_pamount, T2.qita2_pamount, T2.qita3_pamount, T2.shiwu_beizhu"
				+ " from workload_records_table T2,project_table T3"
				+ " where T3.cmd_num=T2.cmd_num and T2.workdate>=? and T2.workdate<=? and T2.bm_name='" + group + "' order by T2.workdate;";

		props = new Properties();
		try {
			props.load(new FileInputStream("mysql.ini"));
			driver = props.getProperty("driver");
			ip = props.getProperty("url");
			port = props.getProperty("port");
			db =  props.getProperty("dbname");
			url = "jdbc:mysql://" + ip + ":" + port +"/" +db;
			Class.forName(driver);

		} catch (FileNotFoundException e) {
			System.out.println("�Ҳ��� mysql.ini �ļ�");
			JOptionPane.showMessageDialog(null, "�Ҳ��� mysql.ini �ļ�", "����ʧ��", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("�ļ����ش���");
			JOptionPane.showMessageDialog(null, "�򲻿� mysql.ini �ļ�", "����ʧ��", JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException e) {
			System.out.println("û�ҵ�������");
		}

		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("��һ�����ӳɹ�dbbean");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "���ݿ�����ʧ�ܣ��������", "����ʧ��", JOptionPane.ERROR_MESSAGE);
		}

	}

	public ResultSet submit(String kinds, String date1, String date2) throws SQLException {
		ResultSet rs = null;
		if (kinds.equals("��ˮ��")) {
			System.out.println(output_liushui);
			pstm = conn.prepareStatement(output_liushui);
			pstm.setObject(1, date1);
			pstm.setObject(2, date2);
			rs = pstm.executeQuery();
		} else if (kinds.equals("��Ա����������")) {
			System.out.println(output_gongren);
			pstm = conn.prepareStatement(output_gongren);
			pstm.setObject(1, date1);
			pstm.setObject(2, date2);
			rs = pstm.executeQuery();
		} else if (kinds.equals("�����")) {
			String str = date2;
			char[] c = str.toCharArray();
			c[6] = '0';
			c[7] = '1';
			String dateyuechu = String.valueOf(c).toString();
			System.out.println(String.valueOf(c).toString());
			stmt = conn.createStatement();
			System.out.println(this.getSql(date1, date2, dateyuechu));
			rs = stmt.executeQuery(this.getSql(date1, date2, dateyuechu));
			// pstm=conn.prepareStatement(output_baogao);
		} else if(kinds.equals("�����(��׼)")){
			String str = date2;
			char[] c = str.toCharArray();
			c[6] = '0';
			c[7] = '1';
			String dateyuechu = String.valueOf(c).toString();
			System.out.println(String.valueOf(c).toString());
			stmt = conn.createStatement();
			System.out.println(this.getSql_biaozhun(date1, date2, dateyuechu));
			rs = stmt.executeQuery(this.getSql_biaozhun(date1, date2, dateyuechu));
		}
		return rs;

	}

	
	private String getSql(String date1, String date2, String dateyuechu) {
		String sql = " select "
				+" group_concat(distinct  ifnull(T2.ht_num,'')),group_concat(distinct  ifnull(T2.ht_price,'')),T4.cmd_num,T3.cmd_date,T3.pro_name,T3.c_co,T3.c_contact,T4.cmd_num,'',T3.d_contact,T3.start_time,T3.end_time,"
				+" T_yue.x_pzhang,"
				+" T_nian.x_pzhang,"
				+" T_yue.y_pzhang,"
				+" T_nian.y_pzhang,"
				+" T_yue.chaosheng_pmi,"
				+" T_nian.chaosheng_pmi,"
				+" T_yue.chaosheng_pdao,"
				+" T_nian.chaosheng_pdao,"
				+" T_yue.cifen_pmi,"
				+" T_nian.cifen_pmi,"
				+" T_yue.cifen_pdao,"
				+" T_nian.cifen_pdao,"
				+" T_yue.shentou_pmi,"
				+" T_nian.shentou_pmi,"
				+" T_yue.shentou_pdao,"
				+" T_nian.shentou_pdao,"
				+" T_yue.cehou_pdian,"
				+" T_nian.cehou_pdian,"
				+" T_yue.biaopei_pri,"
				+" T_nian.biaopei_pri,"
				+" T_yue.guangpuquan_pdian,"
				+" T_nian.guangpuquan_pdian,"
				+" T_yue.guangpuquan_pge,"
				+" T_nian.guangpuquan_pge,"
				+" T_yue.guangpuban_pdian,"
				+" T_nian.guangpuban_pdian,"
				+" T_yue.guangpuban_pge,"
				+" T_nian.guangpuban_pge,"
				+" T_yue.yingdu_pdian,"
				+" T_nian.yingdu_pdian,"
				+" T_yue.damo_pmi,"
				+" T_nian.damo_pmi,"
				+" T_yue.tofd_pmi,"
				+" T_nian.tofd_pmi,"
				+" T_yue.lashen_pjian,"
				+" T_nian.lashen_pjian,"
				+" T_yue.wanqu_pjian,"
				+" T_nian.wanqu_pjian,"
				+" T_yue.chongji_pjian,"
				+" T_nian.chongji_pjian,"
				+" T_yue.tie_pdian,"
				+" T_nian.tie_pdian,"
				+" T_yue.jinxiang_pjian,"
				+" T_nian.jinxiang_pjian,"
				+" T_yue.diankou_pamount,"
				+" T_nian.diankou_pamount,"
				+" t8.yue,t8.nian,'',group_concat(distinct ifnull(report_num,'')),group_concat(distinct ifnull(report_date,'')),group_concat(distinct ifnull(qianzheng_date,'')),group_concat(distinct ifnull(settle_num,'')),group_concat(distinct ifnull(settle_date,'')),'','',"
				+" sum(ifnull(invoice_money,0)),group_concat(distinct ifnull(invoice_num,'')),group_concat(distinct ifnull(invoice_date,'')),'',sum(ifnull(collected_amount,0)),group_concat(distinct ifnull(collected_date,'')) "
				+" from "
				+" workload_records_table T4 left join report_table T1 on T1.cmd_num=T4.cmd_num left join hetong_table T2 on T4.cmd_num=T2.cmd_num left join project_table T3 on T3.cmd_num=T4.cmd_num left join "
				+" ("
				+" select t.cmd_num cmd_num,t3.yue yue,t.nian nian from"
				+" ("
				+" select"
				+" t1.cmd_num ,"
				+" ifnull(sum(case when t1.x_pci is not null then 0 else ifnull(t1.x_pzhang,0) end)*t2.x_pzhang_price ,0)"
				+" +sum(ifnull(t1.x_pci,0)) "
				+" +ifnull(sum(case when t1.y_pci is not null then 0 else ifnull(t1.y_pzhang,0) end)*t2.y_pzhang_price ,0)"
				+" +sum(ifnull(t1.y_pci,0)) "
				+" +ifnull(sum(case when t1.chaosheng_pci is not null then 0 else ifnull(t1.chaosheng_pmi,0) end)*t2.chaosheng_pmi_price ,0)"
				+" +sum(ifnull(t1.chaosheng_pci,0)) "
				+" +ifnull(sum(case when t1.chaosheng_pci is not null then 0 else ifnull(t1.chaosheng_pdao,0) end)*t2.chaosheng_pdao_price ,0)"
				+" +sum(ifnull(t1.chaosheng_pci,0)) "
				+" +ifnull(sum(case when t1.cifen_pci is not null then 0 else ifnull(t1.cifen_pmi,0) end)*t2.cifen_pmi_price ,0)"
				+" +sum(ifnull(t1.cifen_pci,0)) "
				+" +ifnull(sum(case when t1.cifen_pci is not null then 0 else ifnull(t1.cifen_pdao,0) end)*t2.cifen_pdao_price ,0)"
				+" +sum(ifnull(t1.cifen_pci,0)) "
				+" +ifnull(sum(case when t1.shentou_pci is not null then 0 else ifnull(t1.shentou_pmi,0) end)*t2.shentou_pmi_price ,0)"
				+" +sum(ifnull(t1.shentou_pci,0)) "
				+" +ifnull(sum(case when t1.shentou_pci is not null then 0 else ifnull(t1.shentou_pdao,0) end)*t2.shentou_pdao_price ,0)"
				+" +sum(ifnull(t1.shentou_pci,0)) "
				+" +ifnull(sum(case when t1.cehou_pci is not null then 0 else ifnull(t1.cehou_pdian,0) end)*t2.cehou_pdian_price ,0)"
				+" +sum(ifnull(t1.cehou_pci,0)) "
				+" +ifnull(sum(ifnull(t1.biaopei_pri,0))*t2.biaopei_pri_price ,0) "
				+" +ifnull(sum(case when t1.guangpuquan_pci is not null then 0 else ifnull(t1.guangpuquan_pdian,0) end)*t2.guangpuquan_pdian_price ,0)"
				+" +sum(ifnull(t1.guangpuquan_pci,0)) "
				+" +ifnull(sum(case when t1.guangpuquan_pci is not null then 0 else ifnull(t1.guangpuquan_pge,0) end)*t2.guangpuquan_pge_price ,0)"
				+" +sum(ifnull(t1.guangpuquan_pci,0)) "
				+" +ifnull(sum(case when t1.guangpuban_pci is not null then 0 else ifnull(t1.guangpuban_pdian,0) end)*t2.guangpuban_pdian_price ,0)"
				+" +sum(ifnull(t1.guangpuban_pci,0)) "
				+" +ifnull(sum(case when t1.guangpuban_pci is not null then 0 else ifnull(t1.guangpuban_pge,0) end)*t2.guangpuban_pge_price ,0)"
				+" +sum(ifnull(t1.guangpuban_pci,0)) "
				+" +ifnull(sum(case when t1.yingdu_pci is not null then 0 else ifnull(t1.yingdu_pdian,0) end)*t2.yingdu_pdian_price ,0)"
				+" +sum(ifnull(t1.yingdu_pci,0)) "
				+" +ifnull(sum(case when t1.damo_pci is not null then 0 else ifnull(t1.damo_pmi,0) end)*t2.damo_pmi_price ,0)"
				+" +sum(ifnull(t1.damo_pci,0)) "
				+" +ifnull(sum(case when t1.tofd_pci is not null then 0 else ifnull(t1.tofd_pmi,0) end)*t2.tofd_pmi_price ,0)"
				+" +sum(ifnull(t1.tofd_pci,0)) "
				+" +ifnull(sum(case when t1.lashen_pci is not null then 0 else ifnull(t1.lashen_pjian,0) end)*t2.lashen_pjian_price ,0)"
				+" +sum(ifnull(t1.lashen_pci,0)) "
				+" +ifnull(sum(case when t1.wanqu_pci is not null then 0 else ifnull(t1.wanqu_pjian,0) end)*t2.wanqu_pjian_price ,0)"
				+" +sum(ifnull(t1.wanqu_pci,0)) "
				+" +ifnull(sum(case when t1.chongji_pci is not null then 0 else ifnull(t1.chongji_pjian,0) end)*t2.chongji_pjian_price ,0)"
				+" +sum(ifnull(t1.chongji_pci,0)) "
				+" +ifnull(sum(case when t1.tie_pci is not null then 0 else ifnull(t1.tie_pdian,0) end)*t2.tie_pdian_price ,0)"
				+" +sum(ifnull(t1.tie_pci,0)) "
				+" +ifnull(sum(case when t1.jinxiang_pci is not null then 0 else ifnull(t1.jinxiang_pjian,0) end)*t2.jinxiang_pjian_price ,0)"
				+" +sum(ifnull(t1.jinxiang_pci,0)) "
				+" +ifnull(sum(ifnull(t1.diankou_pamount,0))*t2.diankou_pamount_price,0) "
				+ " +ifnull(sum(ifnull(t1.qita1_pamount,0))*t2.qita1_pamount_price,0)"
				+ " +ifnull(sum(ifnull(t1.qita2_pamount,0))*t2.qita2_pamount_price,0)"
				+ " +ifnull(sum(ifnull(t1.qita3_pamount,0))*t2.qita3_pamount_price,0) nian"
				+" "
				+" from workload_records_table t1,duiwai_price_table t2 "
				+" where t1.cmd_num=t2.cmd_num and t1.workdate>="+date1+" and t1.workdate<="+date2+" and t1.bm_name='"+group+"'"
				+" group by t1.cmd_num) t left join "
				+" ("
				+" select"
				+" t1.cmd_num ,"
				+" ifnull(sum(case when t1.x_pci is not null then 0 else ifnull(t1.x_pzhang,0) end)*t2.x_pzhang_price ,0)"
				+" +sum(ifnull(t1.x_pci,0)) "
				+" +ifnull(sum(case when t1.y_pci is not null then 0 else ifnull(t1.y_pzhang,0) end)*t2.y_pzhang_price ,0)"
				+" +sum(ifnull(t1.y_pci,0)) "
				+" +ifnull(sum(case when t1.chaosheng_pci is not null then 0 else ifnull(t1.chaosheng_pmi,0) end)*t2.chaosheng_pmi_price ,0)"
				+" +sum(ifnull(t1.chaosheng_pci,0)) "
				+" +ifnull(sum(case when t1.chaosheng_pci is not null then 0 else ifnull(t1.chaosheng_pdao,0) end)*t2.chaosheng_pdao_price ,0)"
				+" +sum(ifnull(t1.chaosheng_pci,0)) "
				+" +ifnull(sum(case when t1.cifen_pci is not null then 0 else ifnull(t1.cifen_pmi,0) end)*t2.cifen_pmi_price ,0)"
				+" +sum(ifnull(t1.cifen_pci,0)) "
				+" +ifnull(sum(case when t1.cifen_pci is not null then 0 else ifnull(t1.cifen_pdao,0) end)*t2.cifen_pdao_price ,0)"
				+" +sum(ifnull(t1.cifen_pci,0)) "
				+" +ifnull(sum(case when t1.shentou_pci is not null then 0 else ifnull(t1.shentou_pmi,0) end)*t2.shentou_pmi_price ,0)"
				+" +sum(ifnull(t1.shentou_pci,0)) "
				+" +ifnull(sum(case when t1.shentou_pci is not null then 0 else ifnull(t1.shentou_pdao,0) end)*t2.shentou_pdao_price ,0)"
				+" +sum(ifnull(t1.shentou_pci,0)) "
				+" +ifnull(sum(case when t1.cehou_pci is not null then 0 else ifnull(t1.cehou_pdian,0) end)*t2.cehou_pdian_price ,0)"
				+" +sum(ifnull(t1.cehou_pci,0)) "
				+" +ifnull(sum(ifnull(t1.biaopei_pri,0))*t2.biaopei_pri_price ,0) "
				+" +ifnull(sum(case when t1.guangpuquan_pci is not null then 0 else ifnull(t1.guangpuquan_pdian,0) end)*t2.guangpuquan_pdian_price ,0)"
				+" +sum(ifnull(t1.guangpuquan_pci,0)) "
				+" +ifnull(sum(case when t1.guangpuquan_pci is not null then 0 else ifnull(t1.guangpuquan_pge,0) end)*t2.guangpuquan_pge_price ,0)"
				+" +sum(ifnull(t1.guangpuquan_pci,0)) "
				+" +ifnull(sum(case when t1.guangpuban_pci is not null then 0 else ifnull(t1.guangpuban_pdian,0) end)*t2.guangpuban_pdian_price ,0)"
				+" +sum(ifnull(t1.guangpuban_pci,0)) "
				+" +ifnull(sum(case when t1.guangpuban_pci is not null then 0 else ifnull(t1.guangpuban_pge,0) end)*t2.guangpuban_pge_price ,0)"
				+" +sum(ifnull(t1.guangpuban_pci,0)) "
				+" +ifnull(sum(case when t1.yingdu_pci is not null then 0 else ifnull(t1.yingdu_pdian,0) end)*t2.yingdu_pdian_price ,0)"
				+" +sum(ifnull(t1.yingdu_pci,0)) "
				+" +ifnull(sum(case when t1.damo_pci is not null then 0 else ifnull(t1.damo_pmi,0) end)*t2.damo_pmi_price ,0)"
				+" +sum(ifnull(t1.damo_pci,0)) "
				+" +ifnull(sum(case when t1.tofd_pci is not null then 0 else ifnull(t1.tofd_pmi,0) end)*t2.tofd_pmi_price ,0)"
				+" +sum(ifnull(t1.tofd_pci,0)) "
				+" +ifnull(sum(case when t1.lashen_pci is not null then 0 else ifnull(t1.lashen_pjian,0) end)*t2.lashen_pjian_price ,0)"
				+" +sum(ifnull(t1.lashen_pci,0)) "
				+" +ifnull(sum(case when t1.wanqu_pci is not null then 0 else ifnull(t1.wanqu_pjian,0) end)*t2.wanqu_pjian_price ,0)"
				+" +sum(ifnull(t1.wanqu_pci,0)) "
				+" +ifnull(sum(case when t1.chongji_pci is not null then 0 else ifnull(t1.chongji_pjian,0) end)*t2.chongji_pjian_price ,0)"
				+" +sum(ifnull(t1.chongji_pci,0)) "
				+" +ifnull(sum(case when t1.tie_pci is not null then 0 else ifnull(t1.tie_pdian,0) end)*t2.tie_pdian_price ,0)"
				+" +sum(ifnull(t1.tie_pci,0)) "
				+" +ifnull(sum(case when t1.jinxiang_pci is not null then 0 else ifnull(t1.jinxiang_pjian,0) end)*t2.jinxiang_pjian_price ,0)"
				+" +sum(ifnull(t1.jinxiang_pci,0)) "
				+" +ifnull(sum(ifnull(t1.diankou_pamount,0))*t2.diankou_pamount_price,0) "
				+ " +ifnull(sum(ifnull(t1.qita1_pamount,0))*t2.qita1_pamount_price,0)"
				+ " +ifnull(sum(ifnull(t1.qita2_pamount,0))*t2.qita2_pamount_price,0) "
				+ " +ifnull(sum(ifnull(t1.qita3_pamount,0))*t2.qita3_pamount_price,0) yue"
				+" from workload_records_table t1,duiwai_price_table t2 "
				+" where t1.cmd_num=t2.cmd_num and t1.workdate>="+dateyuechu+" and t1.workdate<="+date2+" and t1.bm_name='"+group+"'"
				+" group by t1.cmd_num) t3 on t3.cmd_num=t.cmd_num"
				+" "
				+" ) t8 on t4.cmd_num=t8.cmd_num left join "
				+" ("
				+" SELECT "
				+ "t.cmd_num cmd_num,"
				+" sum(ifnull(T.x_pzhang,0)) x_pzhang,"
				+" sum(ifnull(T.y_pzhang,0)) y_pzhang,"
				+" sum(ifnull(T.chaosheng_pmi,0)) chaosheng_pmi,"
				+" sum(ifnull(T.chaosheng_pdao,0)) chaosheng_pdao,"
				+" sum(ifnull(T.cifen_pmi,0)) cifen_pmi,"
				+" sum(ifnull(T.cifen_pdao,0)) cifen_pdao,"
				+" sum(ifnull(T.shentou_pmi,0)) shentou_pmi,"
				+" sum(ifnull(T.shentou_pdao,0)) shentou_pdao,"
				+" sum(ifnull(T.cehou_pdian,0)) cehou_pdian,"
				+" sum(ifnull(T.biaopei_pri,0)) biaopei_pri,"
				+" sum(ifnull(T.guangpuquan_pdian,0)) guangpuquan_pdian,"
				+" sum(ifnull(T.guangpuquan_pge,0)) guangpuquan_pge,"
				+" sum(ifnull(T.guangpuban_pdian,0)) guangpuban_pdian,"
				+" sum(ifnull(T.guangpuban_pge,0)) guangpuban_pge,"
				+" sum(ifnull(T.yingdu_pdian,0)) yingdu_pdian,"
				+" sum(ifnull(T.damo_pmi,0)) damo_pmi,"
				+" sum(ifnull(T.tofd_pmi,0)) tofd_pmi,"
				+" sum(ifnull(T.lashen_pjian,0)) lashen_pjian,"
				+" sum(ifnull(T.wanqu_pjian,0)) wanqu_pjian,"
				+" sum(ifnull(T.chongji_pjian,0)) chongji_pjian,"
				+" sum(ifnull(T.tie_pdian,0)) tie_pdian,"
				+" sum(ifnull(T.jinxiang_pjian,0)) jinxiang_pjian,"
				+" sum(ifnull(T.diankou_pamount,0)) diankou_pamount"
				+" from workload_records_table T where T.workdate>="+date1+" and T.workdate<="+date2+" and T.bm_name='"+group+"' group by t.cmd_num) T_nian on t4.cmd_num=T_nian.cmd_num left join "
				+" ("
				+" SELECT "
				+" t.cmd_num cmd_num,"
				+" sum(ifnull(T.x_pzhang,0)) x_pzhang,"
				+" sum(ifnull(T.y_pzhang,0)) y_pzhang,"
				+" sum(ifnull(T.chaosheng_pmi,0)) chaosheng_pmi,"
				+" sum(ifnull(T.chaosheng_pdao,0)) chaosheng_pdao,"
				+" sum(ifnull(T.cifen_pmi,0)) cifen_pmi,"
				+" sum(ifnull(T.cifen_pdao,0)) cifen_pdao,"
				+" sum(ifnull(T.shentou_pmi,0)) shentou_pmi,"
				+" sum(ifnull(T.shentou_pdao,0)) shentou_pdao,"
				+" sum(ifnull(T.cehou_pdian,0)) cehou_pdian,"
				+" sum(ifnull(T.biaopei_pri,0)) biaopei_pri,"
				+" sum(ifnull(T.guangpuquan_pdian,0)) guangpuquan_pdian,"
				+" sum(ifnull(T.guangpuquan_pge,0)) guangpuquan_pge,"
				+" sum(ifnull(T.guangpuban_pdian,0)) guangpuban_pdian,"
				+" sum(ifnull(T.guangpuban_pge,0)) guangpuban_pge,"
				+" sum(ifnull(T.yingdu_pdian,0)) yingdu_pdian,"
				+" sum(ifnull(T.damo_pmi,0)) damo_pmi,"
				+" sum(ifnull(T.tofd_pmi,0)) tofd_pmi,"
				+" sum(ifnull(T.lashen_pjian,0)) lashen_pjian,"
				+" sum(ifnull(T.wanqu_pjian,0)) wanqu_pjian,"
				+" sum(ifnull(T.chongji_pjian,0)) chongji_pjian,"
				+" sum(ifnull(T.tie_pdian,0)) tie_pdian,"
				+" sum(ifnull(T.jinxiang_pjian,0)) jinxiang_pjian,"
				+" sum(ifnull(T.diankou_pamount,0)) diankou_pamount"
				+" from workload_records_table T where T.workdate>="+dateyuechu+" and T.workdate<="+date2+" and T.bm_name='"+group+"' group by t.cmd_num) T_yue on T_nian.cmd_num=T_yue.cmd_num"
				+" where "
				+" T4.cmd_num in (select T7.cmd_num from workload_records_table T7 where T7.workdate>="+date1+" and T7.workdate<="+date2+" and T7.bm_name='"+group+"') "
				+" group by T4.cmd_num;"
				+" ";

		return sql;

	}
	//�������Ʊ��sql
	private String getSql_biaozhun(String date1, String date2, String dateyuechu) {
		String strend= date2;
		StringBuffer sb = new StringBuffer(strend);
		String temstr = sb.substring( 0, 4);
		int temInt = Integer.parseInt(temstr) -1 ;
		StringBuffer sbst = new StringBuffer();
		sbst.append(String.valueOf(temInt)).append("0101");
		System.out.println(sbst);
		StringBuffer sbend = new StringBuffer();
		sbend.append(String.valueOf(temInt)).append("1231");
		System.out.println(sbend);
		
		String datequnian1 = sbst.toString();
		String datequnian2 = sbend.toString();
		String sql = " select "
				+" group_concat(distinct  ifnull(T2.ht_num,'')),group_concat(distinct  ifnull(T2.ht_price,'')),T4.cmd_num,T3.cmd_date,T3.pro_name,T3.c_co,T3.c_contact,T4.cmd_num,'',T3.d_contact,T3.start_time,T3.end_time,"
				+" ifnull(T_yue.x_pzhang,0)+ifnull(T_yue.y_pzhang,0),"
				+" ifnull(T_nian.x_pzhang,0)+ifnull(T_nian.y_pzhang,0),"
				+" ifnull(T_yue.chaosheng_pmi,0)+ifnull(T_yue.chaosheng_pdao,0),"
				+" ifnull(T_nian.chaosheng_pmi,0)+ifnull(T_nian.chaosheng_pdao,0),"
				+" ifnull(T_yue.cifen_pmi,0)+ifnull(T_yue.cifen_pdao,0),"
				+" ifnull(T_nian.cifen_pmi,0)+ifnull(T_nian.cifen_pdao,0),"
				+" ifnull(T_yue.shentou_pmi,0)+ifnull(T_yue.shentou_pdao,0),"
				+" ifnull(T_nian.shentou_pmi,0)+ifnull(T_nian.shentou_pdao,0),"
				+" ifnull(T_yue.tofd_pmi,0),"
				+" ifnull(T_nian.tofd_pmi,0),"
				+" ifnull(T_yue.yingdu_pdian,0),"
				+" ifnull(T_nian.yingdu_pdian,0),"
				+" ifnull(T_yue.cehou_pdian,0),"
				+" ifnull(T_nian.cehou_pdian,0),"
				+" ifnull(T_yue.guangpuban_pdian,0)+ifnull(T_yue.guangpuban_pge,0),"
				+" ifnull(T_nian.guangpuban_pdian,0)+ifnull(T_nian.guangpuban_pge,0),"
				+" ifnull(T_yue.damo_pmi,0),"
				+" ifnull(T_nian.damo_pmi,0),"
				+" ifnull(T_yue.jinxiang_pjian,0),"
				+" ifnull(T_nian.jinxiang_pjian,0),"
				+" ifnull(T_yue.guangpuquan_pdian,0)+ifnull(T_yue.guangpuquan_pge,0),"
				+" ifnull(T_nian.guangpuquan_pdian,0)+ifnull(T_nian.guangpuquan_pge,0),"
				+" ifnull(T_yue.lashen_pjian,0)+ifnull(T_yue.wanqu_pjian,0)+ifnull(T_yue.chongji_pjian,0)+ifnull(T_yue.tie_pdian,0),"
				+" ifnull(T_nian.lashen_pjian,0)+ifnull(T_nian.wanqu_pjian,0)+ifnull(T_nian.chongji_pjian,0)+ifnull(T_nian.tie_pdian,0),"
				+" ifnull(t8.yue,0),ifnull(t8.nian,0),'',ifnull(T_qunian.nian,0),group_concat(distinct ifnull(report_num,'')),group_concat(distinct ifnull(report_date,'')),group_concat(distinct ifnull(qianzheng_date,'')),group_concat(distinct ifnull(settle_num,'')),group_concat(distinct ifnull(settle_date,'')),'','',"
				+" sum(distinct t5.invoice_money),group_concat(distinct ifnull(invoice_num,'')),group_concat(distinct ifnull(invoice_date,'')),'',sum(distinct t5.collected_amount),group_concat(distinct ifnull(collected_date,'')),'',group_concat(distinct ifnull(T1.report_beizhu,'')),'' "
				+" from "
				+" workload_records_table T4 left join report_table T1 on T1.cmd_num=T4.cmd_num left join hetong_table T2 on T4.cmd_num=T2.cmd_num left join project_table T3 on T3.cmd_num=T4.cmd_num left join (select t.cmd_num,sum(ifnull(invoice_money,0)) invoice_money,sum(ifnull(collected_amount,0)) collected_amount from report_table t group by t.cmd_num) t5 on t4.cmd_num=t5.cmd_num left join"
				+" ("
				+" select t.cmd_num cmd_num,t3.yue yue,t.nian nian from"
				+" ("
				+" select"
				+" t1.cmd_num ,"
				+" ifnull(sum(case when t1.x_wci is not null then 0 else ifnull(t1.x_pzhang,0) end)*t2.x_pzhang_price ,0)"
				+" +sum(ifnull(t1.x_wci,0)) "
				+" +ifnull(sum(case when t1.y_wci is not null then 0 else ifnull(t1.y_pzhang,0) end)*t2.y_pzhang_price ,0)"
				+" +sum(ifnull(t1.y_wci,0)) "
				+" +ifnull(sum(case when t1.chaosheng_wci is not null then 0 else ifnull(t1.chaosheng_pmi,0) end)*t2.chaosheng_pmi_price ,0)"
				+" +sum(ifnull(t1.chaosheng_wci,0)) "
				+" +ifnull(sum(case when t1.chaosheng_wci is not null then 0 else ifnull(t1.chaosheng_pdao,0) end)*t2.chaosheng_pdao_price ,0)"
				
				+" +ifnull(sum(case when t1.cifen_wci is not null then 0 else ifnull(t1.cifen_pmi,0) end)*t2.cifen_pmi_price ,0)"
				+" +sum(ifnull(t1.cifen_wci,0)) "
				+" +ifnull(sum(case when t1.cifen_wci is not null then 0 else ifnull(t1.cifen_pdao,0) end)*t2.cifen_pdao_price ,0)"
			
				+" +ifnull(sum(case when t1.shentou_wci is not null then 0 else ifnull(t1.shentou_pmi,0) end)*t2.shentou_pmi_price ,0)"
			
				+" +ifnull(sum(case when t1.shentou_wci is not null then 0 else ifnull(t1.shentou_pdao,0) end)*t2.shentou_pdao_price ,0)"
				+" +sum(ifnull(t1.shentou_wci,0)) "
				+" +ifnull(sum(case when t1.cehou_wci is not null then 0 else ifnull(t1.cehou_pdian,0) end)*t2.cehou_pdian_price ,0)"
				+" +sum(ifnull(t1.cehou_wci,0)) "
				+" +ifnull(sum(ifnull(t1.biaopei_pri,0))*t2.biaopei_pri_price ,0) "
				+" +ifnull(sum(case when t1.guangpuquan_wci is not null then 0 else ifnull(t1.guangpuquan_pdian,0) end)*t2.guangpuquan_pdian_price ,0)"
				+" +sum(ifnull(t1.guangpuquan_wci,0)) "
				+" +ifnull(sum(case when t1.guangpuquan_wci is not null then 0 else ifnull(t1.guangpuquan_pge,0) end)*t2.guangpuquan_pge_price ,0)"
			
				+" +ifnull(sum(case when t1.guangpuban_wci is not null then 0 else ifnull(t1.guangpuban_pdian,0) end)*t2.guangpuban_pdian_price ,0)"
				+" +sum(ifnull(t1.guangpuban_wci,0)) "
				+" +ifnull(sum(case when t1.guangpuban_wci is not null then 0 else ifnull(t1.guangpuban_pge,0) end)*t2.guangpuban_pge_price ,0)"
			
				+" +ifnull(sum(case when t1.yingdu_wci is not null then 0 else ifnull(t1.yingdu_pdian,0) end)*t2.yingdu_pdian_price ,0)"
				+" +sum(ifnull(t1.yingdu_wci,0)) "
				+" +ifnull(sum(case when t1.damo_wci is not null then 0 else ifnull(t1.damo_pmi,0) end)*t2.damo_pmi_price ,0)"
				+" +sum(ifnull(t1.damo_wci,0)) "
				+" +ifnull(sum(case when t1.tofd_wci is not null then 0 else ifnull(t1.tofd_pmi,0) end)*t2.tofd_pmi_price ,0)"
				+" +sum(ifnull(t1.tofd_wci,0)) "
				+" +ifnull(sum(case when t1.lashen_wci is not null then 0 else ifnull(t1.lashen_pjian,0) end)*t2.lashen_pjian_price ,0)"
				+" +sum(ifnull(t1.lashen_wci,0)) "
				+" +ifnull(sum(case when t1.wanqu_wci is not null then 0 else ifnull(t1.wanqu_pjian,0) end)*t2.wanqu_pjian_price ,0)"
				+" +sum(ifnull(t1.wanqu_wci,0)) "
				+" +ifnull(sum(case when t1.chongji_wci is not null then 0 else ifnull(t1.chongji_pjian,0) end)*t2.chongji_pjian_price ,0)"
				+" +sum(ifnull(t1.chongji_wci,0)) "
				+" +ifnull(sum(case when t1.tie_wci is not null then 0 else ifnull(t1.tie_pdian,0) end)*t2.tie_pdian_price ,0)"
				+" +sum(ifnull(t1.tie_wci,0)) "
				+" +ifnull(sum(case when t1.jinxiang_wci is not null then 0 else ifnull(t1.jinxiang_pjian,0) end)*t2.jinxiang_pjian_price ,0)"
				+" +sum(ifnull(t1.jinxiang_wci,0)) "
				+" +ifnull(sum(ifnull(t1.diankou_pamount,0))*t2.diankou_pamount_price,0) "
				+ " +ifnull(sum(ifnull(t1.qita1_pamount,0))*t2.qita1_pamount_price,0)"
				+ " +ifnull(sum(ifnull(t1.qita2_pamount,0))*t2.qita2_pamount_price,0)"
				+ " +ifnull(sum(ifnull(t1.qita3_pamount,0))*t2.qita3_pamount_price,0) nian"
				+" "
				+" from workload_records_table t1 left join duiwai_price_table t2 on t1.cmd_num=t2.cmd_num"
				+" where t1.workdate>="+date1+" and t1.workdate<="+date2+" and t1.bm_name='"+group+"'"
				+" group by t1.cmd_num) t left join "
				+" ("
				+" select"
				+" t1.cmd_num ,"
				+" ifnull(sum(case when t1.x_wci is not null then 0 else ifnull(t1.x_pzhang,0) end)*t2.x_pzhang_price ,0)"
				+" +sum(ifnull(t1.x_wci,0)) "
				+" +ifnull(sum(case when t1.y_wci is not null then 0 else ifnull(t1.y_pzhang,0) end)*t2.y_pzhang_price ,0)"
				+" +sum(ifnull(t1.y_wci,0)) "
				+" +ifnull(sum(case when t1.chaosheng_wci is not null then 0 else ifnull(t1.chaosheng_pmi,0) end)*t2.chaosheng_pmi_price ,0)"
				+" +sum(ifnull(t1.chaosheng_wci,0)) "
				+" +ifnull(sum(case when t1.chaosheng_wci is not null then 0 else ifnull(t1.chaosheng_pdao,0) end)*t2.chaosheng_pdao_price ,0)"
			
				+" +ifnull(sum(case when t1.cifen_wci is not null then 0 else ifnull(t1.cifen_pmi,0) end)*t2.cifen_pmi_price ,0)"
				+" +sum(ifnull(t1.cifen_wci,0)) "
				+" +ifnull(sum(case when t1.cifen_wci is not null then 0 else ifnull(t1.cifen_pdao,0) end)*t2.cifen_pdao_price ,0)"
	
				+" +ifnull(sum(case when t1.shentou_wci is not null then 0 else ifnull(t1.shentou_pmi,0) end)*t2.shentou_pmi_price ,0)"
				+" +sum(ifnull(t1.shentou_wci,0)) "
				+" +ifnull(sum(case when t1.shentou_wci is not null then 0 else ifnull(t1.shentou_pdao,0) end)*t2.shentou_pdao_price ,0)"
			
				+" +ifnull(sum(case when t1.cehou_wci is not null then 0 else ifnull(t1.cehou_pdian,0) end)*t2.cehou_pdian_price ,0)"
				+" +sum(ifnull(t1.cehou_wci,0)) "
				+" +ifnull(sum(ifnull(t1.biaopei_pri,0))*t2.biaopei_pri_price ,0) "
				+" +ifnull(sum(case when t1.guangpuquan_wci is not null then 0 else ifnull(t1.guangpuquan_pdian,0) end)*t2.guangpuquan_pdian_price ,0)"
				+" +sum(ifnull(t1.guangpuquan_wci,0)) "
				+" +ifnull(sum(case when t1.guangpuquan_wci is not null then 0 else ifnull(t1.guangpuquan_pge,0) end)*t2.guangpuquan_pge_price ,0)"
		
				+" +ifnull(sum(case when t1.guangpuban_wci is not null then 0 else ifnull(t1.guangpuban_pdian,0) end)*t2.guangpuban_pdian_price ,0)"
			
				+" +ifnull(sum(case when t1.guangpuban_wci is not null then 0 else ifnull(t1.guangpuban_pge,0) end)*t2.guangpuban_pge_price ,0)"
				+" +sum(ifnull(t1.guangpuban_wci,0)) "
				+" +ifnull(sum(case when t1.yingdu_wci is not null then 0 else ifnull(t1.yingdu_pdian,0) end)*t2.yingdu_pdian_price ,0)"
				+" +sum(ifnull(t1.yingdu_wci,0)) "
				+" +ifnull(sum(case when t1.damo_wci is not null then 0 else ifnull(t1.damo_pmi,0) end)*t2.damo_pmi_price ,0)"
				+" +sum(ifnull(t1.damo_wci,0)) "
				+" +ifnull(sum(case when t1.tofd_wci is not null then 0 else ifnull(t1.tofd_pmi,0) end)*t2.tofd_pmi_price ,0)"
				+" +sum(ifnull(t1.tofd_wci,0)) "
				+" +ifnull(sum(case when t1.lashen_wci is not null then 0 else ifnull(t1.lashen_pjian,0) end)*t2.lashen_pjian_price ,0)"
				+" +sum(ifnull(t1.lashen_wci,0)) "
				+" +ifnull(sum(case when t1.wanqu_wci is not null then 0 else ifnull(t1.wanqu_pjian,0) end)*t2.wanqu_pjian_price ,0)"
				+" +sum(ifnull(t1.wanqu_wci,0)) "
				+" +ifnull(sum(case when t1.chongji_wci is not null then 0 else ifnull(t1.chongji_pjian,0) end)*t2.chongji_pjian_price ,0)"
				+" +sum(ifnull(t1.chongji_wci,0)) "
				+" +ifnull(sum(case when t1.tie_wci is not null then 0 else ifnull(t1.tie_pdian,0) end)*t2.tie_pdian_price ,0)"
				+" +sum(ifnull(t1.tie_wci,0)) "
				+" +ifnull(sum(case when t1.jinxiang_wci is not null then 0 else ifnull(t1.jinxiang_pjian,0) end)*t2.jinxiang_pjian_price ,0)"
				+" +sum(ifnull(t1.jinxiang_wci,0)) "
				+" +ifnull(sum(ifnull(t1.diankou_pamount,0))*t2.diankou_pamount_price,0) "
				+ " +ifnull(sum(ifnull(t1.qita1_pamount,0))*t2.qita1_pamount_price,0)"
				+ " +ifnull(sum(ifnull(t1.qita2_pamount,0))*t2.qita2_pamount_price,0) "
				+ " +ifnull(sum(ifnull(t1.qita3_pamount,0))*t2.qita3_pamount_price,0) yue"
				+" from workload_records_table t1 left join duiwai_price_table t2 on t1.cmd_num=t2.cmd_num"
				+" where t1.workdate>="+dateyuechu+" and t1.workdate<="+date2+" and t1.bm_name='"+group+"'"
				+" group by t1.cmd_num) t3 on t3.cmd_num=t.cmd_num"
				+" "
				+" ) t8 on t4.cmd_num=t8.cmd_num left join ("
				
						+" select"
						+" t1.cmd_num ,"
						+" ifnull(sum(case when t1.x_wci is not null then 0 else ifnull(t1.x_pzhang,0) end)*t2.x_pzhang_price ,0)"
						+" +sum(ifnull(t1.x_wci,0)) "
						+" +ifnull(sum(case when t1.y_wci is not null then 0 else ifnull(t1.y_pzhang,0) end)*t2.y_pzhang_price ,0)"
						+" +sum(ifnull(t1.y_wci,0)) "
						+" +ifnull(sum(case when t1.chaosheng_wci is not null then 0 else ifnull(t1.chaosheng_pmi,0) end)*t2.chaosheng_pmi_price ,0)"
				
						+" +ifnull(sum(case when t1.chaosheng_wci is not null then 0 else ifnull(t1.chaosheng_pdao,0) end)*t2.chaosheng_pdao_price ,0)"
						+" +sum(ifnull(t1.chaosheng_wci,0)) "
						+" +ifnull(sum(case when t1.cifen_wci is not null then 0 else ifnull(t1.cifen_pmi,0) end)*t2.cifen_pmi_price ,0)"
				
						+" +ifnull(sum(case when t1.cifen_wci is not null then 0 else ifnull(t1.cifen_pdao,0) end)*t2.cifen_pdao_price ,0)"
						+" +sum(ifnull(t1.cifen_wci,0)) "
						+" +ifnull(sum(case when t1.shentou_wci is not null then 0 else ifnull(t1.shentou_pmi,0) end)*t2.shentou_pmi_price ,0)"
					
						+" +ifnull(sum(case when t1.shentou_wci is not null then 0 else ifnull(t1.shentou_pdao,0) end)*t2.shentou_pdao_price ,0)"
						+" +sum(ifnull(t1.shentou_wci,0)) "
						+" +ifnull(sum(case when t1.cehou_wci is not null then 0 else ifnull(t1.cehou_pdian,0) end)*t2.cehou_pdian_price ,0)"
						+" +sum(ifnull(t1.cehou_wci,0)) "
						+" +ifnull(sum(ifnull(t1.biaopei_pri,0))*t2.biaopei_pri_price ,0) "
						+" +ifnull(sum(case when t1.guangpuquan_wci is not null then 0 else ifnull(t1.guangpuquan_pdian,0) end)*t2.guangpuquan_pdian_price ,0)"
					
						+" +ifnull(sum(case when t1.guangpuquan_wci is not null then 0 else ifnull(t1.guangpuquan_pge,0) end)*t2.guangpuquan_pge_price ,0)"
						+" +sum(ifnull(t1.guangpuquan_wci,0)) "
						+" +ifnull(sum(case when t1.guangpuban_wci is not null then 0 else ifnull(t1.guangpuban_pdian,0) end)*t2.guangpuban_pdian_price ,0)"
						+" +sum(ifnull(t1.guangpuban_wci,0)) "
						+" +ifnull(sum(case when t1.guangpuban_wci is not null then 0 else ifnull(t1.guangpuban_pge,0) end)*t2.guangpuban_pge_price ,0)"
				
						+" +ifnull(sum(case when t1.yingdu_wci is not null then 0 else ifnull(t1.yingdu_pdian,0) end)*t2.yingdu_pdian_price ,0)"
						+" +sum(ifnull(t1.yingdu_wci,0)) "
						+" +ifnull(sum(case when t1.damo_wci is not null then 0 else ifnull(t1.damo_pmi,0) end)*t2.damo_pmi_price ,0)"
						+" +sum(ifnull(t1.damo_wci,0)) "
						+" +ifnull(sum(case when t1.tofd_wci is not null then 0 else ifnull(t1.tofd_pmi,0) end)*t2.tofd_pmi_price ,0)"
						+" +sum(ifnull(t1.tofd_wci,0)) "
						+" +ifnull(sum(case when t1.lashen_wci is not null then 0 else ifnull(t1.lashen_pjian,0) end)*t2.lashen_pjian_price ,0)"
						+" +sum(ifnull(t1.lashen_wci,0)) "
						+" +ifnull(sum(case when t1.wanqu_wci is not null then 0 else ifnull(t1.wanqu_pjian,0) end)*t2.wanqu_pjian_price ,0)"
						+" +sum(ifnull(t1.wanqu_wci,0)) "
						+" +ifnull(sum(case when t1.chongji_wci is not null then 0 else ifnull(t1.chongji_pjian,0) end)*t2.chongji_pjian_price ,0)"
						+" +sum(ifnull(t1.chongji_wci,0)) "
						+" +ifnull(sum(case when t1.tie_wci is not null then 0 else ifnull(t1.tie_pdian,0) end)*t2.tie_pdian_price ,0)"
						+" +sum(ifnull(t1.tie_wci,0)) "
						+" +ifnull(sum(case when t1.jinxiang_wci is not null then 0 else ifnull(t1.jinxiang_pjian,0) end)*t2.jinxiang_pjian_price ,0)"
						+" +sum(ifnull(t1.jinxiang_wci,0)) "
						+" +ifnull(sum(ifnull(t1.diankou_pamount,0))*t2.diankou_pamount_price,0) "
						+ " +ifnull(sum(ifnull(t1.qita1_pamount,0))*t2.qita1_pamount_price,0)"
						+ " +ifnull(sum(ifnull(t1.qita2_pamount,0))*t2.qita2_pamount_price,0)"
						+ " +ifnull(sum(ifnull(t1.qita3_pamount,0))*t2.qita3_pamount_price,0) nian"
						+" "
						+" from workload_records_table t1 left join duiwai_price_table t2 on t1.cmd_num=t2.cmd_num"
						+" where t1.workdate>="+datequnian1+" and t1.workdate<="+datequnian2+" and t1.bm_name='"+group+"'"
						+" group by t1.cmd_num) T_qunian on T_qunian.cmd_num=T4.cmd_num left join "
				
				+" ("
				+" SELECT "
				+ "t.cmd_num cmd_num,"
				+" sum(ifnull(T.x_pzhang,0)) x_pzhang,"
				+" sum(ifnull(T.y_pzhang,0)) y_pzhang,"
				+" sum(ifnull(T.chaosheng_pmi,0)) chaosheng_pmi,"
				+" sum(ifnull(T.chaosheng_pdao,0)) chaosheng_pdao,"
				+" sum(ifnull(T.cifen_pmi,0)) cifen_pmi,"
				+" sum(ifnull(T.cifen_pdao,0)) cifen_pdao,"
				+" sum(ifnull(T.shentou_pmi,0)) shentou_pmi,"
				+" sum(ifnull(T.shentou_pdao,0)) shentou_pdao,"
				+" sum(ifnull(T.cehou_pdian,0)) cehou_pdian,"
				+" sum(ifnull(T.biaopei_pri,0)) biaopei_pri,"
				+" sum(ifnull(T.guangpuquan_pdian,0)) guangpuquan_pdian,"
				+" sum(ifnull(T.guangpuquan_pge,0)) guangpuquan_pge,"
				+" sum(ifnull(T.guangpuban_pdian,0)) guangpuban_pdian,"
				+" sum(ifnull(T.guangpuban_pge,0)) guangpuban_pge,"
				+" sum(ifnull(T.yingdu_pdian,0)) yingdu_pdian,"
				+" sum(ifnull(T.damo_pmi,0)) damo_pmi,"
				+" sum(ifnull(T.tofd_pmi,0)) tofd_pmi,"
				+" sum(ifnull(T.lashen_pjian,0)) lashen_pjian,"
				+" sum(ifnull(T.wanqu_pjian,0)) wanqu_pjian,"
				+" sum(ifnull(T.chongji_pjian,0)) chongji_pjian,"
				+" sum(ifnull(T.tie_pdian,0)) tie_pdian,"
				+" sum(ifnull(T.jinxiang_pjian,0)) jinxiang_pjian,"
				+" sum(ifnull(T.diankou_pamount,0)) diankou_pamount"
				+" from workload_records_table T where T.workdate>="+date1+" and T.workdate<="+date2+" and T.bm_name='"+group+"' group by t.cmd_num) T_nian on t4.cmd_num=T_nian.cmd_num left join "
				+" ("
				+" SELECT "
				+" t.cmd_num cmd_num,"
				+" sum(ifnull(T.x_pzhang,0)) x_pzhang,"
				+" sum(ifnull(T.y_pzhang,0)) y_pzhang,"
				+" sum(ifnull(T.chaosheng_pmi,0)) chaosheng_pmi,"
				+" sum(ifnull(T.chaosheng_pdao,0)) chaosheng_pdao,"
				+" sum(ifnull(T.cifen_pmi,0)) cifen_pmi,"
				+" sum(ifnull(T.cifen_pdao,0)) cifen_pdao,"
				+" sum(ifnull(T.shentou_pmi,0)) shentou_pmi,"
				+" sum(ifnull(T.shentou_pdao,0)) shentou_pdao,"
				+" sum(ifnull(T.cehou_pdian,0)) cehou_pdian,"
				+" sum(ifnull(T.biaopei_pri,0)) biaopei_pri,"
				+" sum(ifnull(T.guangpuquan_pdian,0)) guangpuquan_pdian,"
				+" sum(ifnull(T.guangpuquan_pge,0)) guangpuquan_pge,"
				+" sum(ifnull(T.guangpuban_pdian,0)) guangpuban_pdian,"
				+" sum(ifnull(T.guangpuban_pge,0)) guangpuban_pge,"
				+" sum(ifnull(T.yingdu_pdian,0)) yingdu_pdian,"
				+" sum(ifnull(T.damo_pmi,0)) damo_pmi,"
				+" sum(ifnull(T.tofd_pmi,0)) tofd_pmi,"
				+" sum(ifnull(T.lashen_pjian,0)) lashen_pjian,"
				+" sum(ifnull(T.wanqu_pjian,0)) wanqu_pjian,"
				+" sum(ifnull(T.chongji_pjian,0)) chongji_pjian,"
				+" sum(ifnull(T.tie_pdian,0)) tie_pdian,"
				+" sum(ifnull(T.jinxiang_pjian,0)) jinxiang_pjian,"
				+" sum(ifnull(T.diankou_pamount,0)) diankou_pamount"
				+" from workload_records_table T where T.workdate>="+dateyuechu+" and T.workdate<="+date2+" and T.bm_name='"+group+"' group by t.cmd_num) T_yue on T_nian.cmd_num=T_yue.cmd_num"
				+" where "
				+" T4.cmd_num in (select T7.cmd_num from workload_records_table T7 where T7.workdate>="+date1+" and T7.workdate<="+date2+" and T7.bm_name='"+group+"') "
				+" group by T4.cmd_num;"
				+" ";

		return sql;
	}
	public void disconnected() throws Exception {
		if (rs != null) {
			rs.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

}
