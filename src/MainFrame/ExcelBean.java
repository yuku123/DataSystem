package MainFrame;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.CellFormat;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelBean {
	private SQLManager SQLManager = null;
	private String group;// ������������
	private DBbean db;
	private String output;// ���·��
	private ResultSet rs;// ���н������
	private String kinds;
	private String date1;
	private String date2;
	private WritableWorkbook workbook;// excel������

	// ���캯������ʼ��ʱ��õ�DBbean���صĽ����
	// �����õ�һ�����캯��
	public ExcelBean(String output, String date1, String date2) {
		// this.testwrite(output);

	}

	public ExcelBean(String output, String kinds, String date1, String date2, String group)
			throws SQLException, IOException {
		this.output = output;
		this.kinds = kinds;
		this.date1 = date1;
		this.date2 = date2;
		this.group = group;
		this.db = new DBbean(group);
		this.submit();
	}

	private void submit() throws IOException {
		if (kinds.equals("��ˮ��")) {
			try {
				this.write_output_daily_head(output, date1, date2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("��ˮ���sql������");
				e.printStackTrace();
			}
		} else if (kinds.equals("��Ա����������")) {
			this.write_output_wages_head(output, date1, date2);
		} else if (kinds.equals("�����")) {
			this.output_report(output, date1, date2);
		} else if (kinds.equals("ϴƬ��������")) {
			this.output_xipian(output, date1, date2);
		} else if (kinds.equals("�����(��׼)")) {
			// �ڱ��ظ���һ�ݱ�׼��
			// CopyFileUtil.copyFile("�����_��׼.xls", output, true);
			this.output_report_biaozhun(output, date1, date2);
		}
	}

	// �������
	private void output_report_biaozhun(String output, String date1, String date2) {

		try {
			try {
				// ��ȡԪ
				Workbook wb = Workbook.getWorkbook(new File("�����_��׼.xls"));
				WorkbookSettings aa = new WorkbookSettings();
				workbook = wb.createWorkbook(new File(output), wb);
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WritableSheet sheet = workbook.getSheet(0);
			rs = db.submit(kinds, date1, date2);
			this.writeExcel(rs, sheet, 5);
			this.after_output_jiancefangfa(sheet, 1, 2);
			workbook.write();
			workbook.close();
			JOptionPane.showMessageDialog(null, "����ɹ�" + output, "�ɹ�", JOptionPane.PLAIN_MESSAGE);
		} catch (IOException | SQLException | WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ����������Խ�����Ӽ�ⷽ���ķ���
	private void after_output_jiancefangfa(WritableSheet sheet, int head, int body) {

		int row = sheet.getRows();
		int colend = sheet.getColumns();// �õ������ϵ�����
		System.out.println("���м��У�" + row);
		System.out.println("���м��У�" + colend);
		// ������
		for (int i = 1; i < row; i++) {
			String jiancefangfa = "";
			// ���������ϵ�ֵ
			for (int j = 1; j < colend + 1; j++) {
				// �����õ�item�������
				String item = sheet.getCell(j - 1, 3).getContents().toString();
				// System.out.println("��������ֵ����--------------------------------:"+item);
				String leiji = sheet.getCell(j, 4).getContents().toString();
				// System.out.println("��������ֵ����--------------------------------:"+leiji);
				// System.out.println(""+sheet.getCell(j,
				// 0).getContents().toString());

				if (leiji.equals("�ۼ�")) {
					// ���н�����3�п�ʼ
					if (i > 4) {
						String s = sheet.getCell(j, i).getContents().toString();
						System.out.println("�ܼ��ϵ�ֵ�ǣ�--------------------------------------" + s);
						// ����ۼ��к͵����ж���0.00�������
						System.out.println("�������i,j��ֵ" + sheet.getCell(j - 1, i).getContents().toString() + "::"
								+ sheet.getCell(j, i).getContents().toString());
						//System.out.println("!sheet.getCell(j, i).getContents().toString().equals(0.0)"+!sheet.getCell(j, i).getContents().toString().equals("0.0"));
						//System.out.println("!sheet.getCell(j, i).getContents().toString().equals(0.0)"+!sheet.getCell(j, i).getContents().toString().equals("0.0"));
						//System.out.println("!sheet.getCell(j, i).getContents().toString().equals()"+!sheet.getCell(j, i).getContents().toString().equals(""));
						//System.out.println("!sheet.getCell(j, i).getContents().toString().equals()"+!sheet.getCell(j, i).getContents().toString().equals(""));
						
						if (!(sheet.getCell(j, i).getContents().toString().equals("0")&& sheet.getCell(j - 1, i).getContents().toString().equals("0"))) {

							if(!item.equals("")){
								jiancefangfa = jiancefangfa + "," + item;
							}
							
							System.out.println("-------------------------------------" + jiancefangfa);
						}
					}
				}
			}
			try {
				if(i>4){
					jiancefangfa=jiancefangfa.substring(1); 
					sheet.addCell(new Label(8, i, jiancefangfa));
				}
				
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// ���ĸ���Ʊ
	private void output_xipian(String output2, String date12, String date22) {
		// TODO Auto-generated method stub
		try {
			SQLManager = new SQLManager();
			System.out.println("��ʼ����excel");
			workbook = Workbook.createWorkbook(new File(output));
			WritableSheet sheet = workbook.createSheet("���˹�����", 0);
			String sql1 = "SELECT t.person1 person,sum(t.amount1) amount FROM workload_records_table t where t.workdate>="
					+ date1 + " and t.workdate<=" + date2 + " and t.bm_name='" + group + "' group by t.person1";
			String sql2 = "SELECT t.person2 person,sum(t.amount2) amount FROM workload_records_table t where t.workdate>="
					+ date1 + " and t.workdate<=" + date2 + " and t.bm_name='" + group + "' group by t.person2";
			String sql3 = "SELECT t.person3 person,sum(t.amount3) amount FROM workload_records_table t where t.workdate>="
					+ date1 + " and t.workdate<=" + date2 + " and t.bm_name='" + group + "' group by t.person3 ";

			ResultSet rs1 = SQLManager.submit_search(sql1);
			ResultSet rs2 = SQLManager.submit_search(sql2);
			ResultSet rs3 = SQLManager.submit_search(sql3);

			Map map1 = null;
			Map map2 = null;
			Map map3 = null;
			try {
				map1 = this.getMap(rs1);
				map2 = this.getMap(rs2);
				map3 = this.getMap(rs3);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Map map = this.getAllMap(map1, map2, map3);
			this.writeMap(sheet, map);
			workbook.write();
			workbook.close();
			System.out.println("�ر�");
			JOptionPane.showMessageDialog(null, "����ɹ�" + output, "�ɹ�", JOptionPane.PLAIN_MESSAGE);

		} catch (IOException | WriteException e) {
			// TODO Auto-generated catch block
			System.out.println("���ĸ�������");
			e.printStackTrace();
		}
	}

	// �ṩ��дmap�ķ���
	private void writeMap(WritableSheet sheet, Map map) throws RowsExceededException, WriteException {
		// TODO Auto-generated method stub
		int i = 1;
		sheet.addCell(new Label(0, 0, "ϴƬ��"));
		sheet.addCell(new Label(1, 0, "����"));
		for (Object key : map.keySet()) {
			String person = key.toString();
			String value = map.get(key).toString();
			double d=Double.parseDouble(value);
			System.out.println("person+value" + person + "��������" + value);
			jxl.write.Number number = new jxl.write.Number(1,i, d); // ������3.14159
			// �ŵ���Ԫ��D5
			sheet.addCell(number);
			sheet.addCell(new Label(0, i, person));
			//sheet.addCell(new Label(1, i, value));
			i = i + 1;
		}
	}

	// ��������map�ķ���
	private Map getAllMap(Map map1, Map map2, Map map3) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap();
		Set hs = new HashSet();
		Set hs1 = map1.keySet();
		Set hs2 = map2.keySet();
		Set hs3 = map3.keySet();
		// ��set����һ��set
		boolean i = hs.addAll(hs1);
		i = hs.addAll(hs2);
		i = hs.addAll(hs2);
		// ����set
		for (Object str : hs) {
			System.out.println("����hsѭ��");
			String person = str.toString();// �õ�����
			System.out.println(str.toString());
			BigDecimal b1 = new BigDecimal("0.00");

			if (map1.containsKey(person)) {
				// System.out.println(map1.get(person).toString());
				b1 = b1.add(new BigDecimal(map1.get(person).toString()));
				// b1=b1.add(new BigDecimal( map1.get(person).toString()));
			}
			if (map2.containsKey(person)) {
				b1 = b1.add(new BigDecimal(map2.get(person).toString()));
				// System.out.println(map2.get(person).toString());
			}
			if (map3.containsKey(person)) {
				b1 = b1.add(new BigDecimal(map3.get(person).toString()));
				// System.out.println(map3.get(person).toString());
			}
			map.put(person, b1.toString());

		}
		System.out.println(map);
		return map;
	}

	// ��rs����map�ķ���
	private Map getMap(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		rs.last();
		int row = rs.getRow();
		System.out.println("���ʱ��row:" + row);
		for (int i = 1; i < row + 1; i++) {
			rs.absolute(i);
			if (rs.getObject(1) != (Object) null) {
				System.out.println(rs.getObject(1).toString() + ":" + rs.getObject(2).toString());
				map.put(rs.getObject(1).toString(), rs.getObject(2).toString());
			}
		}
		// System.out.println(map);
		return map;
	}

	private void testwrite(String output) {
		/*
		 * String[][] s = { { "�����Ǳ���", "", "", "", "", "", "", "", "", "", "",
		 * "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
		 * "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
		 * "", "", "", "", "", "", "", "", "" }, { "ϴƬ1", "��Ƭ1", "ϴƬ2", "��Ƭ2",
		 * "ϴƬ3", "��Ƭ3", "����ʱ��", "������Ա", "���̱��", "ί�е�λ", "��Ŀ����", "����", "", "",
		 * "", "����", "", "", "�ŷ�", "", "", "��͸", "", "", "���", "", "���", "ȫ����",
		 * "", "", "�붨��", "", "", "Ӳ��", "", "��ĥ", "", "TOFD", "", "����", "",
		 * "����", "", "���", "", "������", "", "����", "", "���", "����1", "����2", "����3",
		 * "��ע" } ,
		 * {"","","","","","","","","","","","X��/��","x��/��","Y��/��","Y��/��","��","��"
		 * ,"��","��","��","��","��","��","��","��","���/��","����","��","��Ԫ��","��","��","��Ԫ��",
		 * "��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��",
		 * "","","","",""}};
		 * 
		 */try {
			workbook = Workbook.createWorkbook(new File(output));
			WritableSheet sheet = workbook.createSheet("��ˮ��", 0);
			sheet.addCell(new Label(0, 0, "a"));
			sheet.addCell(new Label(0, 5, "00"));
			sheet.addCell(new Label(1, 0, "a"));

			sheet.addCell(new Label(2, 0, "a"));
			sheet.addCell(new Label(2, 5, "00"));

			sheet.addCell(new Label(3, 0, "b"));
			sheet.addCell(new Label(4, 0, "b"));
			sheet.addCell(new Label(4, 3, "b"));
			sheet.addCell(new Label(5, 0, "b"));

			// System.out.println(isEmpty(sheet,1,2));
			ArrayList al = new ArrayList();
			al.add("a");
			al.add("b");
			this.afteroutput(sheet, al, 0, 1);
			// this.mergeall(sheet, 0);
			// this.writehead(sheet, s);// ���ñ�����д����ķ���
			// sheet.removeColumn(2);
			// sheet.mergeCells(0,1,1,0);
			workbook.write();
			workbook.close();
		} catch (IOException | WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*
			 *
			 *
			 * try { workbook = Workbook.createWorkbook(new File(output));//
			 * �õ���Ҫ�������ļ� // ������䣬���sheet,��������ǲ���sheet��λ�� WritableSheet sheet =
			 * workbook.createSheet("�ճ���ˮ��", 0); WritableSheet sheet2 =
			 * workbook.createSheet("��3", 2); /* ������ͷ��ABCDE��12345�����ݣ�/
			 * ֮��������,add��ɶԵ�Ԫ��Ĳ���
			 */
		// Label label0 = new Label(0, 0, "�ֶ�1");

		/*
		 * mergeCells(a,b,c,d) ��Ԫ��ϲ����� a ��Ԫ����к� b ��Ԫ����к� c �ӵ�Ԫ��[a,b]�����ºϲ������� d
		 * �ӵ�Ԫ��[a,b]�����ºϲ�������/�� �ϲ������ǿ�ֵ�ſ��Ժϲ�
		 */
		// sheet.mergeCells(0, 0, 2, 0);
		// �رն����ͷ���Դ,���������������

	}

	// ���˹�������ƽʱ����ˮ��ҵ
	private void write_output_daily_head(String output, String date1, String date2) throws SQLException {
		String[][] s = {
				{ "������ˮ��" + date1 + "��" + date2, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
						"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
						"", "", "", "", "", "", "", "", "", "" },
				{ "ϴƬ��1", "����1", "ϴƬ��2", "����2", "ϴƬ��3", "����3", "����ʱ��", "������Ա", "���̱��", "ί�е�λ", "��Ŀ����", "����", "����", "����",
						"����", "����", "����", "�ŷ�", "�ŷ�", "��͸", "��͸", "���", "���", "ȫ����", "ȫ����", "�붨��", "�붨��", "Ӳ��", "��ĥ",
						"TOFD", "����", "����", "���", "������", "����", "��ڡ��ؼ�Ժ��ϡ�����ָ�� ����", "����1", "����2", "����3", "��ע", "", "",
						"", "", "", "", "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "", "", "", "", "", "X��/��", "x���Ƭ", "Y��/��", "Y���Ƭ", "��", "��", "��", "��", "��",
						"��", "��", "����", "��", "��Ԫ��", "��", "��Ԫ��", "��", "��", "��", "��", "��", "��", "��", "��", "", "", "", "",
						"", "", "", "", "", "", "", "", "", "", "", "", "", "", } };
		try {
			System.out.println("��ʼ����excel");
			workbook = Workbook.createWorkbook(new File(output));
			WritableSheet sheet = workbook.createSheet("��ˮ��", 0);

			ArrayList<String> al = new ArrayList<String>();
			String[] str = new String[] { "����", "����", "�ŷ�", "��͸", "���", "���", "ȫ����", "�붨��", "Ӳ��", "��ĥ", "TOFD", "����",
					"����", "���", "������", "����", "��ڡ��ؼ�Ժ��ϡ�����ָ�� ����", "����1", "����2", "����3", "ϴƬ��2", "����2", "ϴƬ��3", "����3" };
			for (int i = 0; i < str.length; i++) {
				al.add(str[i]);
			}
			;// ��ʼ��al�Ĳ���
			this.writehead(sheet, s);// ���ñ�����д����ķ���
			rs = db.submit(kinds, date1, date2);
			this.writeExcel(rs, sheet, 3);
			this.afteroutput(sheet, al, 1, 3);
			this.mergeall(sheet, 1);
			workbook.write();
			workbook.close();
			System.out.println("�ر�");
			JOptionPane.showMessageDialog(null, "����ɹ�" + output, "�ɹ�", JOptionPane.PLAIN_MESSAGE);

		} catch (IOException | WriteException e) {
			// TODO Auto-generated catch block
			System.out.println("��ˮ��try������");
			e.printStackTrace();
		}

	}

	// ���˵ĸɻ���Ŀ
	private void write_output_wages_head(String output, String date1, String date2) throws IOException {
		String[][] s = { { "���", "��ҵ��Ա", "ָ�����", "x��/��", "x��/��", "x��/��", "x��/��", "x��/��", "x��/��", "x��/��", "Y/��", "Y/��",
				"Y/��", "Y/��", "Y/��", "Y/��", "Y/��", "����/��", "����/��", "����/��", "����/��", "����/��", "����/��", "����/��", "����/��",
				"����/��", "����/��", "�ŷ�/��", "�ŷ�/��", "�ŷ�/��", "�ŷ�/��", "�ŷ�/��", "�ŷ�/��", "�ŷ�/��", "�ŷ�/��", "�ŷ�/��", "�ŷ�/��", "��͸/��",
				"��͸/��", "��͸/��", "��͸/��", "��͸/��", "��͸/��", "��͸/��", "��͸/��", "��͸/��", "��͸/��", "���/��", "���/��", "���/��", "���/��",
				"���/��", "���/��", "���/��", "�������/����", "�������/����", "�������/����", "ȫ����/��", "ȫ����/��", "ȫ����/��", "ȫ����/��Ԫ��",
				"ȫ����/��Ԫ��", "ȫ����/��Ԫ��", "ȫ����/��", "ȫ����/��", "ȫ����/��", "ȫ����/��", "�붨��/��", "�붨��/��", "�붨��/��", "�붨��/��Ԫ��", "�붨��/��Ԫ��",
				"�붨��/��Ԫ��", "�붨��/��", "�붨��/��", "�붨��/��", "�붨��/��", "Ӳ��/��", "Ӳ��/��", "Ӳ��/��", "Ӳ��/��", "Ӳ��/��", "Ӳ��/��", "Ӳ��/��",
				"��ĥ/��", "��ĥ/��", "��ĥ/��", "��ĥ/��", "��ĥ/��", "��ĥ/��", "��ĥ/��", "TOFD/��", "TOFD/��", "TOFD/��", "TOFD/��",
				"TOFD/��", "TOFD/��", "TOFD/��", "����/��", "����/��", "����/��", "����/��", "����/��", "����/��", "����/��", "����/��", "����/��",
				"����/��", "����/��", "����/��", "����/��", "����/��", "���/��", "���/��", "���/��", "���/��", "���/��", "���/��", "���/��", "������/��",
				"������/��", "������/��", "������/��", "������/��", "������/��", "������/��", "����/��", "����/��", "����/��", "����/��", "����/��", "����/��", "����/��",
				"��ڡ��ؼ�Ժ��ϡ�����ָ�� ����", "��ڡ��ؼ�Ժ��ϡ�����ָ�� ����", "��ڡ��ؼ�Ժ��ϡ�����ָ�� ����", "������Ŀ1", "������Ŀ1", "������Ŀ1", "������Ŀ2", "������Ŀ2",
				"������Ŀ2", "������Ŀ3", "������Ŀ3", "������Ŀ3", "�Ƽ����ʺϼƣ�Ԫ��", "�������죩", "�໤���죩", "�������໤��Ԫ��", "�ܼ�Ӧ�����ʣ�Ԫ��", "���ԷѲ�����Ԫ��",
				"�ѷ����ʣ�Ԫ��", "��˾��Ԫ��", "Ӧ�۳��ܽ�Ԫ��", "ʣ��δ�����ʽ��  ��Ԫ��", "��ע", },
				{ "", "", "", "����", "����(Ԫ)", "С��(Ԫ)", "������(��)", "�ܴ���", "����(Ԫ)", "С��(Ԫ)", "����", "����(Ԫ)", "С��(Ԫ)",
						"������(��)", "�ܴ���", "����(Ԫ)", "С��(Ԫ)", "����", "����(Ԫ)", "С��(Ԫ)", "����", "����(Ԫ)", "С��(Ԫ)", "������(��)",
						"�ܴ���", "����(Ԫ)", "С��(Ԫ)", "����", "����(Ԫ)", "С��(Ԫ)", "����", "����(Ԫ)", "С��(Ԫ)", "������(��)", "�ܴ���",
						"����(Ԫ)", "С��(Ԫ)", "����", "����(Ԫ)", "С��(Ԫ)", "����", "����(Ԫ)", "С��(Ԫ)", "������(��)", "�ܴ���", "����(Ԫ)",
						"С��(Ԫ)", "����", "����(Ԫ)", "С��(Ԫ)", "������(��)", "�ܴ���", "����(Ԫ)", "С��(Ԫ)", "����", "����(Ԫ)", "С��(Ԫ)",
						"����", "����(Ԫ)", "С��(Ԫ)", "����", "����(Ԫ)", "С��(Ԫ)", "����", "", "����(Ԫ)", "С��(Ԫ)", "����", "����(Ԫ)",
						"С��(Ԫ)", "����", "����(Ԫ)", "С��(Ԫ)", "������(��)", "�ܴ���", "����(Ԫ)", "С��(Ԫ)", "����", "����(Ԫ)", "С��(Ԫ)",
						"������(��)", "�ܴ���", "����(Ԫ)", "С��(Ԫ)", "����", "����(Ԫ)", "С��(Ԫ)", "������(��)", "�ܴ���", "����(Ԫ)", "С��(Ԫ)",
						"����", "����(Ԫ)", "С��(Ԫ)", "������(��)", "�ܴ���", "����(Ԫ)", "С��(Ԫ)", "����", "����(Ԫ)", "С��(Ԫ)", "������(��)",
						"�ܴ���", "����(Ԫ)", "С��(Ԫ)", "����", "����(Ԫ)", "С��(Ԫ)", "������(��)", "�ܴ���", "����(Ԫ)", "С��(Ԫ)", "����",
						"����(Ԫ)", "С��(Ԫ)", "������(��)", "�ܴ���", "����(Ԫ)", "С��(Ԫ)", "����", "����(Ԫ)", "С��(Ԫ)", "������(��)", "�ܴ���",
						"����(Ԫ)", "С��(Ԫ)", "����", "����(Ԫ)", "С��(Ԫ)", "������(��)", "�ܴ���", "����(Ԫ)", "С��(Ԫ)", "����", "����(Ԫ)",
						"С��(Ԫ)", "����", "����(Ԫ)", "С��(Ԫ)", "����", "����(Ԫ)", "С��(Ԫ)", "����", "����(Ԫ)", "С��(Ԫ)", "", "", "", "",
						"", "", "", "", "", "", "", }, };
		try {
			workbook = Workbook.createWorkbook(new File(output));
			WritableSheet sheet = workbook.createSheet("���˹���ͳ�Ʊ�", 0);

			this.writehead(sheet, s);// ���ñ�����д����ķ���
			rs = db.submit(kinds, date1, date2);
			this.writeExcel(rs, sheet, 2);
			this.afteroutput_wage(sheet, 0, 2);
			this.afteroutput_wage_add(sheet, 0, 0);
			this.mergeall(sheet, 0);
			workbook.write();
			workbook.close();
			JOptionPane.showMessageDialog(null, "����ɹ�" + output, "�ɹ�", JOptionPane.PLAIN_MESSAGE);
		} catch (IOException | WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("sql��������");
		}
	}

	private void output_report(String output, String date1, String date2) {

		String[][] s = {
				{ "��ͬ���", "��ͬ��", "ָ����", "�´�����", "��������", "ί�е�λ", "��ϵ��", "���̱��", "��ⷽ��", "��Ŀ������", "��������", "�깤����",
						"��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����",
						"��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����",
						"��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����",
						"��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����",
						"��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "��ɹ�����", "�����ֵ��Ԫ��", "�����ֵ��Ԫ��", "�����ֵδ�����", "�������",
						"�������", "ǩ֤������", "�������", "�������", "����Ƚ����(Ԫ)", "����Ƚ����(Ԫ)", "��Ʊ��", "��Ʊ��", "��Ʊ����", "δ��Ʊ��",
						"�տ��", "�տ�����", "�տ��ۼ�", "��ע", "δǩ��", "δ����ǩ��", "���湤����", },
				{ "", "", "", "", "", "", "", "", "", "", "", "", "x��/��", "x��/��", "Y/��", "Y/��", "����/��", "����/��", "����/��",
						"����/��", "�ŷ�/��", "�ŷ�/��", "�ŷ�/��", "�ŷ�/��", "��͸/��", "��͸/��", "��͸/��", "��͸/��", "���/��", "���/��",
						"�������/����", "�������/����", "ȫ����/��", "ȫ����/��", "ȫ����/��Ԫ��", "ȫ����/��Ԫ��", "�붨��/��", "�붨��/��", "�붨��/��Ԫ��",
						"�붨��/��Ԫ��", "Ӳ��/��", "Ӳ��/��", "��ĥ/��", "��ĥ/��", "TOFD/��", "TOFD/��", "����/��", "����/��", "����/��", "����/��",
						"���/��", "���/��", "������/��", "������/��", "����/��", "����/��", "��ڡ��ؼ�Ժ��ϡ�����ָ�� ����", "��ڡ��ؼ�Ժ��ϡ�����ָ�� ����", "",
						"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", },
				{ "", "", "", "", "", "", "", "", "", "", "", "", "����", "�ۼ�", "����", "�ۼ�", "����", "�ۼ�", "����", "�ۼ�", "����",
						"�ۼ�", "����", "�ۼ�", "����", "�ۼ�", "����", "�ۼ�", "����", "�ۼ�", "����", "�ۼ�", "����", "�ۼ�", "����", "�ۼ�", "����",
						"�ۼ�", "����", "�ۼ�", "����", "�ۼ�", "����", "�ۼ�", "����", "�ۼ�", "����", "�ۼ�", "����", "�ۼ�", "����", "�ۼ�", "����",
						"�ۼ�", "����", "�ۼ�", "����", "�ۼ�", "����", "�ۼ�", "", "������", "��������", "", "���㵥��", "���㵥����", "", "", "",
						"", "", "", "", "", "", "", "", "", "", },

		};
		try {
			workbook = Workbook.createWorkbook(new File(output));
			WritableSheet sheet = workbook.createSheet("�����", 0);

			ArrayList al = new ArrayList();
			String[] str = new String[] { "x��/��", "x��/��", "Y/��", "Y/��", "����/��", "����/��", "����/��", "�ŷ�/��", "�ŷ�/��", "�ŷ�/��",
					"���/��", "���/��", "�������/����", "ȫ����/��", "ȫ����/��Ԫ��", "ȫ����/��", "�붨��/��", "�붨��/��Ԫ��", "�붨��/��", "Ӳ��/��", "Ӳ��/��",
					"��ĥ/��", "��ĥ/��", "TOFD/��", "TOFD/��", "����/��", "����/��", "����/��", "����/��", "���/��", "���/��", "������/��",
					"������/��", "����/��", "����/��", "��ڡ��ؼ�Ժ��ϡ�����ָ�� ����", "��͸/��", "��͸/��", "��͸/��" };
			for (int i = 0; i < str.length; i++) {
				al.add(str[i]);
			}
			;// ��ʼ��al�Ĳ���

			this.writehead(sheet, s);// ���ñ�����д����ķ���
			rs = db.submit(kinds, date1, date2);
			this.writeExcel(rs, sheet, 3);
			this.afteroutput_report(sheet, al, 1, 3);
			this.mergeall(sheet, 0);
			// this.mergeall(sheet, 1);
			workbook.write();
			workbook.close();
			JOptionPane.showMessageDialog(null, "����ɹ�" + output, "�ɹ�", JOptionPane.PLAIN_MESSAGE);
		} catch (IOException | WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("sql��������");
		}
	}

	// �ṩһ����΢�򵥻��Ļ�������
	private void writehead(WritableSheet sheet, int colum, String[] s) throws RowsExceededException, WriteException {
		for (int i = 0; i < s.length; i++) {
			sheet.addCell(new Label(i, colum, s[i]));
		}

	}

	private void writehead(WritableSheet sheet, String[][] s) throws RowsExceededException, WriteException {
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[i].length; j++) {
				sheet.addCell(new Label(j, i, s[i][j]));
			}

		}

	}

	// ��дrs�ķ���
	private void writeExcel(ResultSet rs, WritableSheet sheet, int row)
			throws SQLException, RowsExceededException, WriteException {
		int col = rs.getMetaData().getColumnCount();
		int index = 0;
		while (rs.next()) {
			for (int i = 1; i < col; i++) {
				if (rs.getObject(i) == null) {
					sheet.addCell(new Label(i - 1, row + index, ""));
				} else if (rs.getObject(i) != null) {
					if (rs.getObject(i).getClass().getName().equals("java.math.BigDecimal")) {
						Double d = ((java.math.BigDecimal) rs.getObject(i)).doubleValue();
						jxl.write.Number number = new jxl.write.Number(i - 1, row + index, d); // ������3.14159
																								// �ŵ���Ԫ��D5
						sheet.addCell(number);
					} else
						sheet.addCell(new Label(i - 1, row + index, rs.getObject(i).toString()));
				}

			}
			index++;
		}

	}

	// ��һ����Ʊ�ĺ�������ɨ���У��������û��ֵ�����ɾ��,��һ��������ɨ��Σ��ڶ��������Ǵ����￪ʼ�����ж��Ƿ�Ϊ��0/1
	private void afteroutput(WritableSheet sheet, ArrayList<String> al, int head, int body) {
		int colend = sheet.getColumns();
		for (int i = 0; i < colend; i++) {
			System.out.println("��ʱcolend:" + colend);
			String item = sheet.getCell(i, head).getContents();// �õ�ɨ��ͷ��cellֵ
			// System.out.println(i);
			System.out.println("ȡ��ͷ���ݣ�" + i + head + ":" + item);
			System.out.println("�ж��Ƿ�al������ֵ" + al.contains(item));
			if (al.contains(item)) {
				if (isEmpty(sheet, body, i)) {
					sheet.removeColumn(i);
					i = -1;
					colend = sheet.getColumns();
					continue;
				}
			}
		}
	}

	// ��һ����Ʊ���жϸ��еķ�������ָ���п�ʼ�Ƿ�Ϊ��,��һ�������Ǵ����п�ʼ�����жϣ��ڶ��������Ǵӵڼ��п�ʼ�����жϣ�,����0��ʼ�����������
	private boolean isEmpty(WritableSheet sheet, int body, int col) {
		int rowend = sheet.getRows();
		// System.out.println("rowend:"+rowend);
		for (int i = body; i < rowend; i++) {
			System.out.println("" + col + i + "��ֵ:" + sheet.getCell(col, i).getContents());
			if (!sheet.getCell(col, i).getContents().equals("")
					&& !sheet.getCell(col, i).getContents().toString().equals("0")
					&& !sheet.getCell(col, i).getContents().toString().equals("0.00")
					&& !sheet.getCell(col, i).getContents().toString().equals("�����ף�0.00,���ε���0.00")
					&& !sheet.getCell(col, i).getContents().toString().equals("����ȫ�㣺0.00,����ȫ����0.00")
					&& !sheet.getCell(col, i).getContents().toString().equals("���ΰ�㣺0.00,���ΰ����0.00")) {
				System.out.println("" + col + i + "��ֵ");
				return false;
			}

		}
		return true;
	}

	// �ڶ�����Ʊ����ĺ�������ɨ��ָ���У�����ɾ������
	private void afteroutput_wage(WritableSheet sheet, int head, int body) {
		ArrayList<String> al = new ArrayList<String>();
		String[] str1 = new String[] { "x��/��", "Y/��", "����/��", "����/��", "�ŷ�/��", "�ŷ�/��", "���/��", "�������/����", "ȫ����/��",
				"ȫ����/��Ԫ��", "�붨��/��", "�붨��/��Ԫ��", "Ӳ��/��", "��ĥ/��", "TOFD/��", "����/��", "����/��", "���/��", "������/��", "����/��",
				"��ڡ��ؼ�Ժ��ϡ�����ָ�� ����", "��͸/��", "��͸/��", "������Ŀ1", "������Ŀ2", "������Ŀ3" };
		for (int i = 0; i < str1.length; i++) {
			al.add(str1[i]);
		}
		;// ��ʼ��al�Ĳ���
		ArrayList<String> al2 = new ArrayList<String>();
		String[] str2 = new String[] { "x��/��", "Y/��", "����/��", "�ŷ�/��", "�ŷ�/��", "���/��", "ȫ����/��", "�붨��/��", "��ĥ/��",
				"TOFD/��", "����/��", "����/��", "���/��", "������/��", "����/��", "��͸/��", "Ӳ��/��" };
		for (int i = 0; i < str2.length; i++) {
			al2.add(str2[i]);
		}
		;// ��ʼ��al�Ĳ���

		int colend = sheet.getColumns();
		for (int i = 0; i < colend; i++) {
			System.out.println("��ʱcolend:" + colend);
			String item = sheet.getCell(i, head).getContents();// �õ�ɨ��ͷ��cellֵ
			// System.out.println(i);
			System.out.println("ȡ��ͷ���ݣ�" + i + "," + head + ":" + item);

			if (al.contains(item)) {
				System.out.println("�ж�:�Ƿ�al������" + item);
				if (isEmpty(sheet, body, i)) {
					System.out.println("" + item + "���ǿյ�");
					sheet.removeColumn(i);
					sheet.removeColumn(i);
					sheet.removeColumn(i);
					i = -1;
					colend = sheet.getColumns();
					continue;
				}
				i = i + 2;
			} else if (al2.contains(item)) {
				if (isEmpty(sheet, body, i)) {
					sheet.removeColumn(i);
					sheet.removeColumn(i);
					sheet.removeColumn(i);
					sheet.removeColumn(i);
					i = -1;
					colend = sheet.getColumns();
					continue;
				}
				i = i + 3;
			}
		}
	}

	// �ڶ�����Ʊ�ĺϼƴ�����
	private void afteroutput_wage_add(WritableSheet sheet, int head, int body) {
		BigDecimal bg = new BigDecimal("0.00");
		// ������ֵ

		System.out.println("------------------�½���bg:" + bg.toString());
		int add = 0;// ������ֵ��λ��
		int row = sheet.getRows();
		int colend = sheet.getColumns();// �õ������ϵ�����
		System.out.println("���м��У�" + row);
		System.out.println("���м��У�" + colend);
		// ������
		for (int i = 1; i < row; i++) {
			// ���������ϵ�ֵ
			bg = new BigDecimal("0.00");
			for (int j = 1; j < colend + 1; j++) {
				// �õ���С�Ƶ�
				String xiaoji = sheet.getCell(j, 1).getContents().toString();
				System.out.println("" + sheet.getCell(j, 0).getContents().toString());
				if (sheet.getCell(j, 0).getContents().toString().equals("�Ƽ����ʺϼƣ�Ԫ��")) {
					add = j;
				}
				if (xiaoji.equals("С��(Ԫ)")) {
					// �õ�С�ƣ�Ԫ���ϵ�ֵ
					// ���н�����3�п�ʼ
					if (i > 1) {
						String s = sheet.getCell(j, i).getContents().toString();
						System.out.println("С���ϵ�ֵ�ǣ�--------------------------------------" + s);
						// ����յ������
						if (s.equals("")) {
							System.out.println("------------------------���ж�Ϊ��");
							bg = bg.add(new BigDecimal("0.00"));
						} else if (!s.equals("")) {
							System.out.println("------------------------���ж���Ϊ��");
							bg = bg.add(new BigDecimal(s));
							System.out.println(
									"���ʱ����ֵ�Ѿ��ǣ�--------------------------------------------------" + bg.toString());
						}
					}
				}

				// System.out.println(i+","+j+"��������:"+s);
			}
			// һ��ɨ������󣬶�ĳһ���ط����и�ֵ
			try {
				double d=bg.doubleValue();
				jxl.write.Number number = new jxl.write.Number(add,i, d);
				sheet.addCell(number);
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	// ������������
	private void afteroutput_report(WritableSheet sheet, ArrayList<String> al, int head, int body) {
		int colend = sheet.getColumns();
		for (int i = 0; i < colend; i++) {
			System.out.println("��ʱcolend:" + colend);
			String item = sheet.getCell(i, head).getContents();// �õ�ɨ��ͷ��cellֵ
			// System.out.println(i);
			System.out.println("ȡ��ͷ���ݣ�" + i + head + ":" + item);
			System.out.println("�ж��Ƿ�al������ֵ" + al.contains(item));
			if (al.contains(item)) {
				if (isEmpty_3(sheet, body, i, item)) {
					sheet.removeColumn(i);
					sheet.removeColumn(i);
					i = -1;
					colend = sheet.getColumns();
					continue;
				}
				i = i + 1;
			}
		}
	}

	// ��������Ʊ���жϷ���
	private boolean isEmpty_3(WritableSheet sheet, int body, int col, String item) {
		boolean isempty = true;
		int rowend = sheet.getRows();
		// System.out.println("rowend:"+rowend);
		for (int i = body; i < rowend + 2; i++) {
			System.out.println("" + col + "��" + i + "��ֵ:" + sheet.getCell(col, i).getContents());
			System.out.println("" + col + "," + i + "��ֵ:" + sheet.getCell(col + 1, i).getContents());
			if (!sheet.getCell(col, i).getContents().toString().equals("")) {
				System.out.println("" + col + i + "��ֵ");
				isempty = false;
			}
			if (!sheet.getCell(col + 1, i).getContents().toString().equals("")) {
				System.out.println("" + col + i + "��ֵ");
				isempty = false;
			}
			if (!sheet.getCell(col, i).getContents().toString().equals("0.00")) {
				System.out.println("" + col + i + "��ֵ");
				isempty = false;
			}
			if (!sheet.getCell(col + 1, i).getContents().toString().equals("0.00")) {
				System.out.println("" + col + i + "��ֵ");
				isempty = false;
			}
			/*
			 * if(isempty==false){ String exist=sheet.getCell(9,
			 * i).getContents().toString(); System.out.println(
			 * "--------------------------------------------------------��"+i+
			 * item); }
			 */

		}
		return isempty;
	}

	// ����ɨ���ͷ���кϲ�,ָ���ڼ���
	private void mergeall(WritableSheet sheet, int row) throws RowsExceededException, WriteException {
		// Sheet sheet1=sheet;
		int i = 0;// ��ָ��
		int j = 0;// ��ָ��
		for (int j1 = 0; j1 < sheet.getColumns() + 1; j1++) {
			// ��������˲�һ�µ�ֵ
			System.out.println("j1��ֵ" + j1);
			System.out.println("i��ֵ" + i);
			if (!(sheet.getCell(i, row).getContents().equals(sheet.getCell(j1, row).getContents()))) {
				sheet.mergeCells(i, row, j1 - 1, row);
				i = j1;
			}
		}
	}

	public static void main(String[] agrs) {
		try {
			new ExcelBean("test��ˮ.xls", "��ˮ��", "20161010", "20161010", "bumen1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// new ExcelBean("����1.xls","20161010","20161010");

	}

}
