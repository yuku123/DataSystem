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

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

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
	private PreparedStatement pstmt; // 工程表
	private PreparedStatement pstmt2;// 洗片事务表
	private PreparedStatement pstmt3;// 工人事务表
	private PreparedStatement pstmt4;// 报告表
	private PreparedStatement pstmt5;// 合同表
	private PreparedStatement pstmt6;// 对内单价表
	private PreparedStatement pstmt7;// 对内单价表


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
			System.out.println("找不到 mysql.ini 文件");
			JOptionPane.showMessageDialog(null, "找不到 mysql.ini 文件", "连接失败", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("文件加载错误");
			JOptionPane.showMessageDialog(null, "打不开 mysql.ini 文件", "连接失败", JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException e) {
			System.out.println("没找到驱动类");
		}

		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("第一次连接成功");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "数据库连接失败，检查连接", "连接失败", JOptionPane.ERROR_MESSAGE);
			NetDialog di =  new  NetDialog();
			while(di.getReturn());
			return true;
		
			
		}
		return false;
	}

	// TODO 失效时重连
	public int reconnect() {
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("重新获得连接");
			return 1;
		} catch (SQLException e) {
			// 重连失败，弹出错误
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "数据库连接失败，检查连接", "连接失败", JOptionPane.ERROR_MESSAGE);
			return 0;
		}
	}

	public Connection getConn() {
		return conn;
	}

	// TODO 插入工程表
	public int insertPro(String[] str) {
		try {
			pstmt = conn.prepareStatement("insert into  project_table values(null,?,?,?,?,?,?,?,?,?,?);");
			System.out.println("statment1创建成功");
		} catch (SQLException e) {
			// 获得语句失败则重连
			e.printStackTrace();
			reconnect();
			try {
				pstmt = conn.prepareStatement("insert into  project_table values(null,?,?,?,?,?,?,?,?,?,?);");
			} catch (SQLException f) {
				f.printStackTrace();
				JOptionPane.showMessageDialog(null, "数据库连接失败，检查连接", "连接失败", JOptionPane.ERROR_MESSAGE);
				return 0;
			}

		}
		// catch结束，执行之后语句
		try {
			for (int i = 0; i < str.length; i++) {
				if (!str[i].equals(""))
					pstmt.setString(i + 1, str[i]);
				else
					pstmt.setNull(i + 1, java.sql.Types.VARCHAR);
			}
			pstmt.executeUpdate();
			System.out.println("statment1执行成功");
			JOptionPane.showMessageDialog(null, "创建成功", "成功", JOptionPane.PLAIN_MESSAGE);
			return 1;
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "填写信息错误，可能与已有指令单号有重复", "插入错误", JOptionPane.ERROR_MESSAGE);
			return 0;
		} finally {
			// 关闭语句
			try {
				if (!pstmt.isClosed()) {
					try {
						pstmt.close();
						System.out.println("statment1关闭成功");
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

	// TODO 查询 多语句用于创建下拉列表,要传入参数,typenum 0 model, 1 ArrayList 2 String
	public Object search(int search_id, int typenum, String[] statment_option) {
		try (PreparedStatement pstmt = conn.prepareStatement(search_str[search_id]);

		) {
			// 把statment分解成preparedstatment的各个问号，并赋值
			System.out.println("statment2创建成功");
			for (int i = 0; i < statment_option.length; i++) {
				pstmt.setString(i + 1, statment_option[i]);
			}
			ResultSet rs = pstmt.executeQuery();
			System.out.println("得到resultset2");
			System.out.println(Thread.currentThread().getStackTrace()[3]);

			// 如果需要传出ArrayList则传出
			if (typenum == 1) {
				ArrayList<String> result = new ArrayList<>();
				while (rs.next()) {
					result.add(rs.getString(1));
				}
				System.out.println("返回ArrayList2");
				return result;
				// 如果需要传出model，则传出
			}
			if (typenum == 0) {
				DefaultComboBoxModel<String> result = new DefaultComboBoxModel<String>();
				while (rs.next()) {
					result.addElement(rs.getString(1));
				}
				System.out.println("返回model2");
				return result;
			}
			// typenum = 2,传出1个String
			if (typenum == 2) {
				String result;
				while (rs.next()) {
					result = rs.getString(1);
					System.out.println("返回String3");
					return result;
				}
				System.out.println("没查询到String2");
				return null;
			}
			return null;
		} catch (SQLException e) {
			// 如果有异常,重连
			e.printStackTrace();
			reconnect();
			JOptionPane.showMessageDialog(null, "请检查网络连接，再次尝试或重新打开软件", "查询失败", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

	// TODO 查询,不用传入参数,返回类型typenum 0 model, 1 ArrayList 2 String
	public Object search(int search_id, int typenum) {
		try (PreparedStatement pstmt = conn.prepareStatement(search_str[search_id]);) {
			// 把statment分解成preparedstatment的各个问号，并赋值
			System.out.println("statment3创建成功");
			ResultSet rs = pstmt.executeQuery();
			System.out.println("得到resultset3");
			System.out.println(Thread.currentThread().getStackTrace()[2]);
			// 如果需要传出ArrayList则传出
			if (typenum == 1) {
				ArrayList<String> result = new ArrayList<>();
				while (rs.next()) {
					result.add(rs.getString(1));
				}
				System.out.println("返回ArrayList3");
				return result;
				// 如果需要传出model，则传出
			}
			if (typenum == 0) {
				DefaultComboBoxModel<String> result = new DefaultComboBoxModel<String>();
				while (rs.next()) {
					result.addElement(rs.getString(1));
				}
				System.out.println("返回model3");
				return result;
			}
			// typenum = 2,传出1个String
			if (typenum == 2) {
				String result;
				while (rs.next()) {
					result = rs.getString(1);
					System.out.println("返回String3");
					return result;
				}
				System.out.println("没查询到String3");
				return null;
			}
			return null;
		} catch (SQLException e) {
			// 如果有异常,重连
			e.printStackTrace();
			reconnect();
			JOptionPane.showMessageDialog(null, "请检查网络连接，再次尝试或重新打开软件", "查询失败", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

//	// TODO 插入洗片表
//	public int insert_xipian_table(String[] str) {
//		try {
//			pstmt2 = conn.prepareStatement("insert into xipian_table values(null,?,?,?,?,?,?,?,?,?);");
//			System.out.println("statment_xipian创建成功");
//		} catch (SQLException e) {
//			// 获得语句失败则重连
//			e.printStackTrace();
//			reconnect();
//			try {
//				pstmt2 = conn.prepareStatement("insert into xipian_table values(null,?,?,?,?,?,?,?,?,?);");
//			} catch (SQLException f) {
//				f.printStackTrace();
//				JOptionPane.showMessageDialog(null, "数据库连接失败，无法插入洗片表", "连接失败", JOptionPane.ERROR_MESSAGE);
//				return 0;
//			}
//
//		}
//		// catch结束，执行之后语句
//		try {
//			for (int i = 0; i < str.length; i++) {
//				if (!str[i].equals(""))
//					pstmt2.setString(i + 1, str[i]);
//				else
//					pstmt2.setNull(i + 1, java.sql.Types.VARCHAR);
//			}
//			pstmt2.executeUpdate();
//			System.out.println("插入洗片执行成功");
//			JOptionPane.showMessageDialog(null, "插入成功", "成功", JOptionPane.PLAIN_MESSAGE);
//			return 1;
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//			JOptionPane.showMessageDialog(null, "填写信息错误或该指令单号该日洗片人工作量已输入过", "插入失败", JOptionPane.ERROR_MESSAGE);
//			return 0;
//		} finally {
//			// 关闭语句
//			try {
//				if (!pstmt2.isClosed()) {
//					try {
//						pstmt2.close();
//						System.out.println("洗片语句关闭成功");
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

	// TODO 插入工人事物表
	public int insert_workload_records_table(String[] str) {
		try {
			pstmt3 = conn.prepareStatement(
					"insert into workload_records_table values(null,now(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			System.out.println("statment_paipian创建成功");
		} catch (SQLException e) {
			// 获得语句失败则重连
			e.printStackTrace();
			reconnect();
			try {
				pstmt3 = conn.prepareStatement(
						"insert into workload_records_table values(null,now(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			} catch (SQLException f) {
				f.printStackTrace();
				JOptionPane.showMessageDialog(null, "数据库连接失败，无法插入工人工作量", "连接失败", JOptionPane.ERROR_MESSAGE);
				return 0;
			}

		}
		// catch结束，执行之后语句
		try {
			for (int i = 0; i < str.length; i++) {
				if (!str[i].equals(""))
					pstmt3.setString(i + 1, str[i]);
				else
					pstmt3.setNull(i + 1, java.sql.Types.VARCHAR);
			}
			pstmt3.executeUpdate();
			System.out.println("插入工人工作量执行成功");
			JOptionPane.showMessageDialog(null, "插入成功", "成功", JOptionPane.PLAIN_MESSAGE);
			return 1;
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "填写信息错误或该指令单号该日拍片人工作量已输入过", "插入失败", JOptionPane.ERROR_MESSAGE);
			return 0;
		} finally {
			// 关闭语句
			try {
				if (!pstmt3.isClosed()) {
					try {
						pstmt3.close();
						System.out.println("工人事务表语句关闭成功");
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

	// TODO 插入报告表
	public int insert_report_table(String[] str) {
		try {
			pstmt4 = conn.prepareStatement(
					"insert into report_table values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			System.out.println("statment_report创建成功");
		} catch (SQLException e) {
			// 获得语句失败则重连
			e.printStackTrace();
			reconnect();
			try {
				pstmt4 = conn.prepareStatement(
						"insert into report_table values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			} catch (SQLException f) {
				f.printStackTrace();
				JOptionPane.showMessageDialog(null, "数据库连接失败，无法插入报告表", "连接失败", JOptionPane.ERROR_MESSAGE);
				return 0;
			}

		}
		// catch结束，执行之后语句
		try {
			for (int i = 0; i < str.length; i++) {
				if (!str[i].equals(""))
					pstmt4.setString(i + 1, str[i]);
				else
					pstmt4.setNull(i + 1, java.sql.Types.VARCHAR);
			}
			pstmt4.executeUpdate();
			System.out.println("插入报告执行成功");
			JOptionPane.showMessageDialog(null, "插入成功", "成功", JOptionPane.PLAIN_MESSAGE);
			return 1;
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "填写信息错误或该报告编号已存在", "插入失败", JOptionPane.ERROR_MESSAGE);
			return 0;
		} finally {
			// 关闭语句
			try {
				if (!pstmt4.isClosed()) {
					try {
						pstmt4.close();
						System.out.println("报告语句关闭成功");
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

	// TODO 插入合同表
	public int insert_hetong_table(String[] str) {
		try {
			pstmt5 = conn.prepareStatement("insert into hetong_table values(null,?,?,?,?,?,?);");
			System.out.println("statment_ht创建成功");
		} catch (SQLException e) {
			// 获得语句失败则重连
			e.printStackTrace();
			reconnect();
			try {
				pstmt5 = conn.prepareStatement("insert into hetong_table values(null,?,?,?,?,?,?);");
			} catch (SQLException f) {
				f.printStackTrace();
				JOptionPane.showMessageDialog(null, "数据库连接失败，无法插入合同表", "连接失败", JOptionPane.ERROR_MESSAGE);
				return 0;
			}

		}
		// catch结束，执行之后语句
		try {
			// 倒数第二个是二进制文件，所以长度减2
			for (int i = 0; i < str.length - 2; i++) {
				if (!str[i].equals(""))
					pstmt5.setString(i + 1, str[i]);
				else
					pstmt5.setNull(i + 1, java.sql.Types.VARCHAR);
			}
			// 对mediumBlob的处理
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
			System.out.println("插入合同执行成功");
			JOptionPane.showMessageDialog(null, "插入成功", "成功", JOptionPane.PLAIN_MESSAGE);
			return 1;
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "填写信息错误或该合同编号已存在", "插入失败", JOptionPane.ERROR_MESSAGE);
			return 0;
		} finally {
			// 关闭语句
			try {
				if (!pstmt5.isClosed()) {
					try {
						pstmt5.close();
						System.out.println("合同语句关闭成功");
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
	
	
	// TODO 插入对内单价表
	public int insert_duinei_price_table(String[] str) {
		try {
			pstmt6 = conn.prepareStatement(
					"insert into duinei_price_table values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			System.out.println("statment_NP创建成功");
		} catch (SQLException e) {
			// 获得语句失败则重连
			e.printStackTrace();
			reconnect();
			try {
				pstmt6 = conn.prepareStatement(
						"insert into duinei_price_table values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			} catch (SQLException f) {
				f.printStackTrace();
				JOptionPane.showMessageDialog(null, "数据库连接失败，无法创建算工资单价", "连接失败", JOptionPane.ERROR_MESSAGE);
				return 0;
			}

		}
		// catch结束，执行之后语句
		try {
			for (int i = 0; i < str.length; i++) {
				if (!str[i].equals(""))
					pstmt6.setString(i + 1, str[i]);
				else
					pstmt6.setNull(i + 1, java.sql.Types.VARCHAR);
			}
			pstmt6.executeUpdate();
			System.out.println("算工资单价创建成功");
			JOptionPane.showMessageDialog(null, "创建成功", "成功", JOptionPane.PLAIN_MESSAGE);
			return 1;
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "填写信息错误", "插入失败", JOptionPane.ERROR_MESSAGE);
			return 0;
		} finally {
			// 关闭语句
			try {
				if (!pstmt6.isClosed()) {
					try {
						pstmt6.close();
						System.out.println("算工资单价关闭成功");
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
	
	
	// TODO 插入对外单价表
	public int insert_duiwai_price_table(String[] str) {
		try {
			pstmt7 = conn.prepareStatement(
					"insert into duiwai_price_table values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			System.out.println("statment_WP创建成功");
		} catch (SQLException e) {
			// 获得语句失败则重连
			e.printStackTrace();
			reconnect();
			try {
				pstmt7 = conn.prepareStatement(
						"insert into duiwai_price_table values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			} catch (SQLException f) {
				f.printStackTrace();
				JOptionPane.showMessageDialog(null, "数据库连接失败，无法创建算产值单价", "连接失败", JOptionPane.ERROR_MESSAGE);
				return 0;
			}

		}
		// catch结束，执行之后语句
		try {
			for (int i = 0; i < str.length; i++) {
				if (!str[i].equals(""))
					pstmt7.setString(i + 1, str[i]);
				else
					pstmt7.setNull(i + 1, java.sql.Types.VARCHAR);
			}
			pstmt7.executeUpdate();
			System.out.println("算产值单价创建成功");
			JOptionPane.showMessageDialog(null, "创建成功", "成功", JOptionPane.PLAIN_MESSAGE);
			return 1;
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "填写信息错误", "插入失败", JOptionPane.ERROR_MESSAGE);
			return 0;
		} finally {
			// 关闭语句
			try {
				if (!pstmt7.isClosed()) {
					try {
						pstmt7.close();
						System.out.println("算产值单价关闭成功");
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
	
	

	// TODO 更新带参数的表
	public int update_table(int update_num, String[] str) {
		try (PreparedStatement pstmt = conn.prepareStatement(update_id[update_num]);) {
			System.out.println("statment_update创建成功");
			for (int i = 0; i < str.length; i++) {
				pstmt.setString(i + 1, str[i]);
			}
			int affect_row = pstmt.executeUpdate();
			return affect_row;

		} catch (SQLException e) {
			// 如果有异常,重连
			e.printStackTrace();
			reconnect();
			JOptionPane.showMessageDialog(null, "请检查网络连接，再次尝试或重新打开软件", "更新失败", JOptionPane.ERROR_MESSAGE);
			return -1;
		}
	}

	// 单独的插入语句方法
	public int submit_insert(String sql) {
		try {
			stmt = conn.createStatement();
			int i = stmt.executeUpdate(sql);
			return i;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "数据库连接失败，检查连接", "连接失败,因此查询失败", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			reconnect();
		}
		return 0;
	}

	// 单独的查询语句方法
	public ResultSet submit_search(String sql) {
		try {
			// PreparedStatement pstm =
			// conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			System.out.println(sql);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "发生查询语句错误", "错误", JOptionPane.ERROR_MESSAGE);
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
						JOptionPane.showMessageDialog(null, "修改成功", "成功", JOptionPane.INFORMATION_MESSAGE);
						state=false;
						dispose();
					} catch (FileNotFoundException e1) {
						System.out.println("找不到 mysql.ini 文件");
						JOptionPane.showMessageDialog(null, "找不到 mysql.ini 文件", "修改失败", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (IOException e1) {
						System.out.println("文件加载错误");
						JOptionPane.showMessageDialog(null, "打不开 mysql.ini 文件", "修改失败", JOptionPane.ERROR_MESSAGE);
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
				System.out.println("找不到 mysql.ini 文件");
				JOptionPane.showMessageDialog(null, "找不到 mysql.ini 文件", "修改失败", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("文件加载错误");
				JOptionPane.showMessageDialog(null, "打不开 mysql.ini 文件", "修改失败", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		private Boolean getReturn(){
			return state;
		}
		
	}
	
	
	

}