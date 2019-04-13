package MainFrame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.*;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SQLManager {
	private static final String user=Password.user;
	private static final String password=Password.password;
	private Properties props;
	private String driver;
	private String ip;
	private String port;
	private String db;
	private String url;
	private Connection conn;
	private PreparedStatement pstmt; // ���̱�
	private PreparedStatement pstmt2;// ϴƬ�����
	private PreparedStatement pstmt3;// ���������
	private PreparedStatement pstmt4;// �����
	private PreparedStatement pstmt5;// ��ͬ��
	private PreparedStatement pstmt6;// ���ڵ��۱�
	private PreparedStatement pstmt7;// ���ڵ��۱�


	private String search_str[] = { "select cmd_num from project_table;", // 0
			"select c_co from project_table where cmd_num =?;", // 1
			"select pro_name from project_table where cmd_num =?;", // 2
			"select c_co from project_table group by c_co;", // 3
			"select pro_name from project_table group by pro_name;", // 4
			"select pro_name from project_table where c_co =?;", // 5
			"select c_co from project_table where pro_name =?;", // 6
			"select cmd_num from project_table where c_co =? and pro_name =?;", // 7
			"select pai_workperson from pai_workers_table;", // 8
			"select xi_workperson from xi_workers_table;", // 9

	};

	private String update_id[] = {

	};
	private Statement stmt;
	private Statement stmt2;

	public SQLManager() {
		while(init());
	}
	private boolean init(){
		props = new Properties();
		try {
			props.load(new FileInputStream("mysql.ini"));
			driver = props.getProperty("driver");
			ip = props.getProperty("url");
			port = props.getProperty("port");
			db =  props.getProperty("dbname");
			url = "jdbc:mysql://" + ip + ":" + port +"/" +db;
			System.out.println(url);
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
			System.out.println("��һ�����ӳɹ�");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "���ݿ�����ʧ�ܣ��������", "����ʧ��", JOptionPane.ERROR_MESSAGE);
			NetDialog di =  new  NetDialog();
			while(di.getReturn());
			return true;
		
			
		}
		return false;
	}

	// TODO ʧЧʱ����
	public int reconnect() {
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("���»������");
			return 1;
		} catch (SQLException e) {
			// ����ʧ�ܣ���������
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "���ݿ�����ʧ�ܣ��������", "����ʧ��", JOptionPane.ERROR_MESSAGE);
			return 0;
		}
	}

	public Connection getConn() {
		return conn;
	}

	// TODO ���빤�̱�
	public int insertPro(String[] str) {
		try {
			pstmt = conn.prepareStatement("insert into  project_table values(null,?,?,?,?,?,?,?,?,?,?);");
			System.out.println("statment1�����ɹ�");
		} catch (SQLException e) {
			// ������ʧ��������
			e.printStackTrace();
			reconnect();
			try {
				pstmt = conn.prepareStatement("insert into  project_table values(null,?,?,?,?,?,?,?,?,?,?);");
			} catch (SQLException f) {
				f.printStackTrace();
				JOptionPane.showMessageDialog(null, "���ݿ�����ʧ�ܣ��������", "����ʧ��", JOptionPane.ERROR_MESSAGE);
				return 0;
			}

		}
		// catch������ִ��֮�����
		try {
			for (int i = 0; i < str.length; i++) {
				if (!str[i].equals(""))
					pstmt.setString(i + 1, str[i]);
				else
					pstmt.setNull(i + 1, java.sql.Types.VARCHAR);
			}
			pstmt.executeUpdate();
			System.out.println("statment1ִ�гɹ�");
			JOptionPane.showMessageDialog(null, "�����ɹ�", "�ɹ�", JOptionPane.PLAIN_MESSAGE);
			return 1;
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "��д��Ϣ���󣬿���������ָ������ظ�", "�������", JOptionPane.ERROR_MESSAGE);
			return 0;
		} finally {
			// �ر����
			try {
				if (!pstmt.isClosed()) {
					try {
						pstmt.close();
						System.out.println("statment1�رճɹ�");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pstmt = null;
		}

	}

	// TODO ��ѯ ��������ڴ��������б�,Ҫ�������,typenum 0 model, 1 ArrayList 2 String
	public Object search(int search_id, int typenum, String[] statment_option) {
		try (PreparedStatement pstmt = conn.prepareStatement(search_str[search_id]);

		) {
			// ��statment�ֽ��preparedstatment�ĸ����ʺţ�����ֵ
			System.out.println("statment2�����ɹ�");
			for (int i = 0; i < statment_option.length; i++) {
				pstmt.setString(i + 1, statment_option[i]);
			}
			ResultSet rs = pstmt.executeQuery();
			System.out.println("�õ�resultset2");
			System.out.println(Thread.currentThread().getStackTrace()[3]);

			// �����Ҫ����ArrayList�򴫳�
			if (typenum == 1) {
				ArrayList<String> result = new ArrayList<>();
				while (rs.next()) {
					result.add(rs.getString(1));
				}
				System.out.println("����ArrayList2");
				return result;
				// �����Ҫ����model���򴫳�
			}
			if (typenum == 0) {
				DefaultComboBoxModel<String> result = new DefaultComboBoxModel<String>();
				while (rs.next()) {
					result.addElement(rs.getString(1));
				}
				System.out.println("����model2");
				return result;
			}
			// typenum = 2,����1��String
			if (typenum == 2) {
				String result;
				while (rs.next()) {
					result = rs.getString(1);
					System.out.println("����String3");
					return result;
				}
				System.out.println("û��ѯ��String2");
				return null;
			}
			return null;
		} catch (SQLException e) {
			// ������쳣,����
			e.printStackTrace();
			reconnect();
			JOptionPane.showMessageDialog(null, "�����������ӣ��ٴγ��Ի����´����", "��ѯʧ��", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

	// TODO ��ѯ,���ô������,��������typenum 0 model, 1 ArrayList 2 String
	public Object search(int search_id, int typenum) {
		try (PreparedStatement pstmt = conn.prepareStatement(search_str[search_id]);) {
			// ��statment�ֽ��preparedstatment�ĸ����ʺţ�����ֵ
			System.out.println("statment3�����ɹ�");
			ResultSet rs = pstmt.executeQuery();
			System.out.println("�õ�resultset3");
			System.out.println(Thread.currentThread().getStackTrace()[2]);
			// �����Ҫ����ArrayList�򴫳�
			if (typenum == 1) {
				ArrayList<String> result = new ArrayList<>();
				while (rs.next()) {
					result.add(rs.getString(1));
				}
				System.out.println("����ArrayList3");
				return result;
				// �����Ҫ����model���򴫳�
			}
			if (typenum == 0) {
				DefaultComboBoxModel<String> result = new DefaultComboBoxModel<String>();
				while (rs.next()) {
					result.addElement(rs.getString(1));
				}
				System.out.println("����model3");
				return result;
			}
			// typenum = 2,����1��String
			if (typenum == 2) {
				String result;
				while (rs.next()) {
					result = rs.getString(1);
					System.out.println("����String3");
					return result;
				}
				System.out.println("û��ѯ��String3");
				return null;
			}
			return null;
		} catch (SQLException e) {
			// ������쳣,����
			e.printStackTrace();
			reconnect();
			JOptionPane.showMessageDialog(null, "�����������ӣ��ٴγ��Ի����´����", "��ѯʧ��", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

//	// TODO ����ϴƬ��
//	public int insert_xipian_table(String[] str) {
//		try {
//			pstmt2 = conn.prepareStatement("insert into xipian_table values(null,?,?,?,?,?,?,?,?,?);");
//			System.out.println("statment_xipian�����ɹ�");
//		} catch (SQLException e) {
//			// ������ʧ��������
//			e.printStackTrace();
//			reconnect();
//			try {
//				pstmt2 = conn.prepareStatement("insert into xipian_table values(null,?,?,?,?,?,?,?,?,?);");
//			} catch (SQLException f) {
//				f.printStackTrace();
//				JOptionPane.showMessageDialog(null, "���ݿ�����ʧ�ܣ��޷�����ϴƬ��", "����ʧ��", JOptionPane.ERROR_MESSAGE);
//				return 0;
//			}
//
//		}
//		// catch������ִ��֮�����
//		try {
//			for (int i = 0; i < str.length; i++) {
//				if (!str[i].equals(""))
//					pstmt2.setString(i + 1, str[i]);
//				else
//					pstmt2.setNull(i + 1, java.sql.Types.VARCHAR);
//			}
//			pstmt2.executeUpdate();
//			System.out.println("����ϴƬִ�гɹ�");
//			JOptionPane.showMessageDialog(null, "����ɹ�", "�ɹ�", JOptionPane.PLAIN_MESSAGE);
//			return 1;
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//			JOptionPane.showMessageDialog(null, "��д��Ϣ������ָ��Ÿ���ϴƬ�˹������������", "����ʧ��", JOptionPane.ERROR_MESSAGE);
//			return 0;
//		} finally {
//			// �ر����
//			try {
//				if (!pstmt2.isClosed()) {
//					try {
//						pstmt2.close();
//						System.out.println("ϴƬ���رճɹ�");
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			pstmt2 = null;
//		}
//
//	}

	// TODO ���빤�������
	public int insert_workload_records_table(String[] str) {
		try {
			pstmt3 = conn.prepareStatement(
					"insert into workload_records_table values(null,now(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			System.out.println("statment_paipian�����ɹ�");
		} catch (SQLException e) {
			// ������ʧ��������
			e.printStackTrace();
			reconnect();
			try {
				pstmt3 = conn.prepareStatement(
						"insert into workload_records_table values(null,now(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			} catch (SQLException f) {
				f.printStackTrace();
				JOptionPane.showMessageDialog(null, "���ݿ�����ʧ�ܣ��޷����빤�˹�����", "����ʧ��", JOptionPane.ERROR_MESSAGE);
				return 0;
			}

		}
		// catch������ִ��֮�����
		try {
			for (int i = 0; i < str.length; i++) {
				if (!str[i].equals(""))
					pstmt3.setString(i + 1, str[i]);
				else
					pstmt3.setNull(i + 1, java.sql.Types.VARCHAR);
			}
			pstmt3.executeUpdate();
			System.out.println("���빤�˹�����ִ�гɹ�");
			JOptionPane.showMessageDialog(null, "����ɹ�", "�ɹ�", JOptionPane.PLAIN_MESSAGE);
			return 1;
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "��д��Ϣ������ָ��Ÿ�����Ƭ�˹������������", "����ʧ��", JOptionPane.ERROR_MESSAGE);
			return 0;
		} finally {
			// �ر����
			try {
				if (!pstmt3.isClosed()) {
					try {
						pstmt3.close();
						System.out.println("������������رճɹ�");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pstmt3 = null;
		}

	}

	// TODO ���뱨���
	public int insert_report_table(String[] str) {
		try {
			pstmt4 = conn.prepareStatement(
					"insert into report_table values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			System.out.println("statment_report�����ɹ�");
		} catch (SQLException e) {
			// ������ʧ��������
			e.printStackTrace();
			reconnect();
			try {
				pstmt4 = conn.prepareStatement(
						"insert into report_table values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			} catch (SQLException f) {
				f.printStackTrace();
				JOptionPane.showMessageDialog(null, "���ݿ�����ʧ�ܣ��޷����뱨���", "����ʧ��", JOptionPane.ERROR_MESSAGE);
				return 0;
			}

		}
		// catch������ִ��֮�����
		try {
			for (int i = 0; i < str.length; i++) {
				if (!str[i].equals(""))
					pstmt4.setString(i + 1, str[i]);
				else
					pstmt4.setNull(i + 1, java.sql.Types.VARCHAR);
			}
			pstmt4.executeUpdate();
			System.out.println("���뱨��ִ�гɹ�");
			JOptionPane.showMessageDialog(null, "����ɹ�", "�ɹ�", JOptionPane.PLAIN_MESSAGE);
			return 1;
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "��д��Ϣ�����ñ������Ѵ���", "����ʧ��", JOptionPane.ERROR_MESSAGE);
			return 0;
		} finally {
			// �ر����
			try {
				if (!pstmt4.isClosed()) {
					try {
						pstmt4.close();
						System.out.println("�������رճɹ�");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pstmt4 = null;
		}

	}

	// TODO �����ͬ��
	public int insert_hetong_table(String[] str) {
		try {
			pstmt5 = conn.prepareStatement("insert into hetong_table values(null,?,?,?,?,?,?);");
			System.out.println("statment_ht�����ɹ�");
		} catch (SQLException e) {
			// ������ʧ��������
			e.printStackTrace();
			reconnect();
			try {
				pstmt5 = conn.prepareStatement("insert into hetong_table values(null,?,?,?,?,?,?);");
			} catch (SQLException f) {
				f.printStackTrace();
				JOptionPane.showMessageDialog(null, "���ݿ�����ʧ�ܣ��޷������ͬ��", "����ʧ��", JOptionPane.ERROR_MESSAGE);
				return 0;
			}

		}
		// catch������ִ��֮�����
		try {
			// �����ڶ����Ƕ������ļ������Գ��ȼ�2
			for (int i = 0; i < str.length - 2; i++) {
				if (!str[i].equals(""))
					pstmt5.setString(i + 1, str[i]);
				else
					pstmt5.setNull(i + 1, java.sql.Types.VARCHAR);
			}
			// ��mediumBlob�Ĵ���
			if (!str[4].equals("")) {
				File f = new File(str[4]);
				try {
					InputStream is = new FileInputStream(f);
					pstmt5.setBinaryStream(5, is, f.length());
				} catch (FileNotFoundException e) {

					e.printStackTrace();
				}

			} else
				pstmt5.setNull(5, java.sql.Types.BLOB);
			
			pstmt5.setString(6, str[5]);

			pstmt5.executeUpdate();
			System.out.println("�����ִͬ�гɹ�");
			JOptionPane.showMessageDialog(null, "����ɹ�", "�ɹ�", JOptionPane.PLAIN_MESSAGE);
			return 1;
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "��д��Ϣ�����ú�ͬ����Ѵ���", "����ʧ��", JOptionPane.ERROR_MESSAGE);
			return 0;
		} finally {
			// �ر����
			try {
				if (!pstmt5.isClosed()) {
					try {
						pstmt5.close();
						System.out.println("��ͬ���رճɹ�");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pstmt5 = null;
		}

	}
	
	
	// TODO ������ڵ��۱�
	public int insert_duinei_price_table(String[] str) {
		try {
			pstmt6 = conn.prepareStatement(
					"insert into duinei_price_table values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			System.out.println("statment_NP�����ɹ�");
		} catch (SQLException e) {
			// ������ʧ��������
			e.printStackTrace();
			reconnect();
			try {
				pstmt6 = conn.prepareStatement(
						"insert into duinei_price_table values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			} catch (SQLException f) {
				f.printStackTrace();
				JOptionPane.showMessageDialog(null, "���ݿ�����ʧ�ܣ��޷������㹤�ʵ���", "����ʧ��", JOptionPane.ERROR_MESSAGE);
				return 0;
			}

		}
		// catch������ִ��֮�����
		try {
			for (int i = 0; i < str.length; i++) {
				if (!str[i].equals(""))
					pstmt6.setString(i + 1, str[i]);
				else
					pstmt6.setNull(i + 1, java.sql.Types.VARCHAR);
			}
			pstmt6.executeUpdate();
			System.out.println("�㹤�ʵ��۴����ɹ�");
			JOptionPane.showMessageDialog(null, "�����ɹ�", "�ɹ�", JOptionPane.PLAIN_MESSAGE);
			return 1;
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "��д��Ϣ����", "����ʧ��", JOptionPane.ERROR_MESSAGE);
			return 0;
		} finally {
			// �ر����
			try {
				if (!pstmt6.isClosed()) {
					try {
						pstmt6.close();
						System.out.println("�㹤�ʵ��۹رճɹ�");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pstmt6 = null;
		}

	}
	
	
	// TODO ������ⵥ�۱�
	public int insert_duiwai_price_table(String[] str) {
		try {
			pstmt7 = conn.prepareStatement(
					"insert into duiwai_price_table values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			System.out.println("statment_WP�����ɹ�");
		} catch (SQLException e) {
			// ������ʧ��������
			e.printStackTrace();
			reconnect();
			try {
				pstmt7 = conn.prepareStatement(
						"insert into duiwai_price_table values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			} catch (SQLException f) {
				f.printStackTrace();
				JOptionPane.showMessageDialog(null, "���ݿ�����ʧ�ܣ��޷��������ֵ����", "����ʧ��", JOptionPane.ERROR_MESSAGE);
				return 0;
			}

		}
		// catch������ִ��֮�����
		try {
			for (int i = 0; i < str.length; i++) {
				if (!str[i].equals(""))
					pstmt7.setString(i + 1, str[i]);
				else
					pstmt7.setNull(i + 1, java.sql.Types.VARCHAR);
			}
			pstmt7.executeUpdate();
			System.out.println("���ֵ���۴����ɹ�");
			JOptionPane.showMessageDialog(null, "�����ɹ�", "�ɹ�", JOptionPane.PLAIN_MESSAGE);
			return 1;
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "��д��Ϣ����", "����ʧ��", JOptionPane.ERROR_MESSAGE);
			return 0;
		} finally {
			// �ر����
			try {
				if (!pstmt7.isClosed()) {
					try {
						pstmt7.close();
						System.out.println("���ֵ���۹رճɹ�");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pstmt7 = null;
		}

	}
	
	

	// TODO ���´������ı�
	public int update_table(int update_num, String[] str) {
		try (PreparedStatement pstmt = conn.prepareStatement(update_id[update_num]);) {
			System.out.println("statment_update�����ɹ�");
			for (int i = 0; i < str.length; i++) {
				pstmt.setString(i + 1, str[i]);
			}
			int affect_row = pstmt.executeUpdate();
			return affect_row;

		} catch (SQLException e) {
			// ������쳣,����
			e.printStackTrace();
			reconnect();
			JOptionPane.showMessageDialog(null, "�����������ӣ��ٴγ��Ի����´����", "����ʧ��", JOptionPane.ERROR_MESSAGE);
			return -1;
		}
	}

	// �����Ĳ�����䷽��
	public int submit_insert(String sql) {
		try {
			stmt = conn.createStatement();
			int i = stmt.executeUpdate(sql);
			return i;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "���ݿ�����ʧ�ܣ��������", "����ʧ��,��˲�ѯʧ��", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			reconnect();
		}
		return 0;
	}

	// �����Ĳ�ѯ��䷽��
	public ResultSet submit_search(String sql) {
		try {
			// PreparedStatement pstm =
			// conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			System.out.println(sql);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "������ѯ������", "����", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			reconnect();
		}
		return null;

	}
	
	
	private class NetDialog extends JDialog {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final JPanel contentPanel = new JPanel();
		private JTextField address;
		private Properties props;
		private JTextField port;
		private Boolean state =true;

		/**
		 * Create the dialog.
		 */
		public NetDialog() {
			initFrame();
			initData();		
		}

		private void initFrame(){
			setResizable(false);
			setBounds(100, 100, 450, 300);
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			GridBagLayout gbl_contentPanel = new GridBagLayout();
			gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
			gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
			gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			contentPanel.setLayout(gbl_contentPanel);
			
			JLabel addressLabel = new JLabel("\u670D\u52A1\u5668\u5730\u5740");
			GridBagConstraints gbc_addressLabel = new GridBagConstraints();
			gbc_addressLabel.insets = new Insets(10, 5, 5, 5);
			gbc_addressLabel.anchor = GridBagConstraints.EAST;
			gbc_addressLabel.gridx = 0;
			gbc_addressLabel.gridy = 0;
			contentPanel.add(addressLabel, gbc_addressLabel);
		
		
			address = new JTextField();
			GridBagConstraints gbc_address = new GridBagConstraints();
			gbc_address.insets = new Insets(10, 0, 5, 20);
			gbc_address.fill = GridBagConstraints.HORIZONTAL;
			gbc_address.gridx = 1;
			gbc_address.gridy = 0;
			contentPanel.add(address, gbc_address);
			address.setColumns(30);
			
			JLabel port_label = new JLabel("\u7AEF\u53E3\u53F7");
			GridBagConstraints gbc_port_label = new GridBagConstraints();
			gbc_port_label.anchor = GridBagConstraints.EAST;
			gbc_port_label.insets = new Insets(10, 5, 5, 5);
			gbc_port_label.gridx = 0;
			gbc_port_label.gridy = 1;
			contentPanel.add(port_label, gbc_port_label);
			
			port = new JTextField();
			GridBagConstraints gbc_port = new GridBagConstraints();
			gbc_port.insets = new Insets(10, 0, 5, 20);
			gbc_port.fill = GridBagConstraints.HORIZONTAL;
			gbc_port.gridx = 1;
			gbc_port.gridy = 1;
			contentPanel.add(port, gbc_port);
			port.setColumns(30);
		
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton okButton = new JButton("\u4FEE\u6539");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					props = new Properties();
					try {
						props.load(new FileInputStream("mysql.ini"));
						props.setProperty("url" , address.getText().trim());
						props.setProperty("port" , port.getText().trim());
						props.store(new FileOutputStream("mysql.ini"), "modify the data"); 
						JOptionPane.showMessageDialog(null, "�޸ĳɹ�", "�ɹ�", JOptionPane.INFORMATION_MESSAGE);
						state=false;
						dispose();
					} catch (FileNotFoundException e1) {
						System.out.println("�Ҳ��� mysql.ini �ļ�");
						JOptionPane.showMessageDialog(null, "�Ҳ��� mysql.ini �ļ�", "�޸�ʧ��", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (IOException e1) {
						System.out.println("�ļ����ش���");
						JOptionPane.showMessageDialog(null, "�򲻿� mysql.ini �ļ�", "�޸�ʧ��", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		
		
			JButton cancelButton = new JButton("\u53D6\u6D88");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
			
			setVisible(true);
		}
		
		
		private void initData(){
			props = new Properties();
			try {
				props.load(new FileInputStream("mysql.ini"));
				String ul = props.getProperty("url");
				String pot = props.getProperty("port");
				address.setText(ul);
				port.setText(pot);

			} catch (FileNotFoundException e) {
				System.out.println("�Ҳ��� mysql.ini �ļ�");
				JOptionPane.showMessageDialog(null, "�Ҳ��� mysql.ini �ļ�", "�޸�ʧ��", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("�ļ����ش���");
				JOptionPane.showMessageDialog(null, "�򲻿� mysql.ini �ļ�", "�޸�ʧ��", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		private Boolean getReturn(){
			return state;
		}
		
	}
	
	
	

}