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
	private String group;// 传到这里的组别
	private DBbean db;
	private String output;// 输出路径
	private ResultSet rs;// 持有结果集合
	private String kinds;
	private String date1;
	private String date2;
	private WritableWorkbook workbook;// excel集成类

	// 构造函数，初始化时候得到DBbean返回的结果集
	// 测试用的一个构造函数
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
		if (kinds.equals("流水表")) {
			try {
				this.write_output_daily_head(output, date1, date2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("流水表的sql出问题");
				e.printStackTrace();
			}
		} else if (kinds.equals("人员工作工作量")) {
			this.write_output_wages_head(output, date1, date2);
		} else if (kinds.equals("报告表")) {
			this.output_report(output, date1, date2);
		} else if (kinds.equals("洗片工作量表")) {
			this.output_xipian(output, date1, date2);
		} else if (kinds.equals("报告表(标准)")) {
			// 在本地复制一份标准的
			// CopyFileUtil.copyFile("报告表_标准.xls", output, true);
			this.output_report_biaozhun(output, date1, date2);
		}
	}

	// 第五个表
	private void output_report_biaozhun(String output, String date1, String date2) {

		try {
			try {
				// 获取元
				Workbook wb = Workbook.getWorkbook(new File("报告表_标准.xls"));
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
			JOptionPane.showMessageDialog(null, "输出成功" + output, "成功", JOptionPane.PLAIN_MESSAGE);
		} catch (IOException | SQLException | WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 第五个表，可以进行添加检测方法的方法
	private void after_output_jiancefangfa(WritableSheet sheet, int head, int body) {

		int row = sheet.getRows();
		int colend = sheet.getColumns();// 得到横向上的列数
		System.out.println("表有几行：" + row);
		System.out.println("表有几列：" + colend);
		// 遍历行
		for (int i = 1; i < row; i++) {
			String jiancefangfa = "";
			// 遍历横向上的值
			for (int j = 1; j < colend + 1; j++) {
				// 遍历得到item用作添加
				String item = sheet.getCell(j - 1, 3).getContents().toString();
				// System.out.println("第三栏的值遍历--------------------------------:"+item);
				String leiji = sheet.getCell(j, 4).getContents().toString();
				// System.out.println("第四栏的值遍历--------------------------------:"+leiji);
				// System.out.println(""+sheet.getCell(j,
				// 0).getContents().toString());

				if (leiji.equals("累计")) {
					// 当行进到第3行开始
					if (i > 4) {
						String s = sheet.getCell(j, i).getContents().toString();
						System.out.println("总计上的值是：--------------------------------------" + s);
						// 如果累计列和当月列都是0.00的情况下
						System.out.println("这个死后，i,j的值" + sheet.getCell(j - 1, i).getContents().toString() + "::"
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

	// 第四个帐票
	private void output_xipian(String output2, String date12, String date22) {
		// TODO Auto-generated method stub
		try {
			SQLManager = new SQLManager();
			System.out.println("开始生成excel");
			workbook = Workbook.createWorkbook(new File(output));
			WritableSheet sheet = workbook.createSheet("工人工作表", 0);
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
			System.out.println("关闭");
			JOptionPane.showMessageDialog(null, "输出成功" + output, "成功", JOptionPane.PLAIN_MESSAGE);

		} catch (IOException | WriteException e) {
			// TODO Auto-generated catch block
			System.out.println("第四个出问题");
			e.printStackTrace();
		}
	}

	// 提供书写map的方法
	private void writeMap(WritableSheet sheet, Map map) throws RowsExceededException, WriteException {
		// TODO Auto-generated method stub
		int i = 1;
		sheet.addCell(new Label(0, 0, "洗片人"));
		sheet.addCell(new Label(1, 0, "数量"));
		for (Object key : map.keySet()) {
			String person = key.toString();
			String value = map.get(key).toString();
			double d=Double.parseDouble(value);
			System.out.println("person+value" + person + "————" + value);
			jxl.write.Number number = new jxl.write.Number(1,i, d); // 将数字3.14159
			// 放到单元格D5
			sheet.addCell(number);
			sheet.addCell(new Label(0, i, person));
			//sheet.addCell(new Label(1, i, value));
			i = i + 1;
		}
	}

	// 集成三个map的方法
	private Map getAllMap(Map map1, Map map2, Map map3) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap();
		Set hs = new HashSet();
		Set hs1 = map1.keySet();
		Set hs2 = map2.keySet();
		Set hs3 = map3.keySet();
		// 将set并成一个set
		boolean i = hs.addAll(hs1);
		i = hs.addAll(hs2);
		i = hs.addAll(hs2);
		// 遍历set
		for (Object str : hs) {
			System.out.println("进入hs循环");
			String person = str.toString();// 得到人物
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

	// 将rs放入map的方法
	private Map getMap(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		rs.last();
		int row = rs.getRow();
		System.out.println("这个时候row:" + row);
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
		 * String[][] s = { { "这里是标题", "", "", "", "", "", "", "", "", "", "",
		 * "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
		 * "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
		 * "", "", "", "", "", "", "", "", "" }, { "洗片1", "废片1", "洗片2", "废片2",
		 * "洗片3", "废片3", "工作时间", "工作人员", "工程编号", "委托单位", "项目名称", "射线", "", "",
		 * "", "超声", "", "", "磁粉", "", "", "渗透", "", "", "测厚", "", "配合", "全定量",
		 * "", "", "半定量", "", "", "硬度", "", "打磨", "", "TOFD", "", "拉伸", "",
		 * "弯曲", "", "冲击", "", "铁素体", "", "金相", "", "点口", "其他1", "其他2", "其他3",
		 * "备注" } ,
		 * {"","","","","","","","","","","","X光/张","x光/次","Y光/张","Y光/次","米","道"
		 * ,"次","米","道","次","米","道","次","点","测厚/次","工日","点","个元素","次","点","个元素",
		 * "次","点","次","米","次","米","次","件","次","件","次","件","次","点","次","件","次",
		 * "","","","",""}};
		 * 
		 */try {
			workbook = Workbook.createWorkbook(new File(output));
			WritableSheet sheet = workbook.createSheet("流水表", 0);
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
			// this.writehead(sheet, s);// 调用本地书写标题的方法
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
			 * 得到将要操作的文件 // 建表语句，添加sheet,后端数字是插入sheet的位置 WritableSheet sheet =
			 * workbook.createSheet("日常流水表", 0); WritableSheet sheet2 =
			 * workbook.createSheet("表3", 2); /* 建立表头（ABCDE，12345，内容）/
			 * 之后必须调用,add完成对单元格的操作
			 */
		// Label label0 = new Label(0, 0, "字段1");

		/*
		 * mergeCells(a,b,c,d) 单元格合并函数 a 单元格的列号 b 单元格的行号 c 从单元格[a,b]起，向下合并的列数 d
		 * 从单元格[a,b]起，向下合并的行数/、 合并必须是空值才可以合并
		 */
		// sheet.mergeCells(0, 0, 2, 0);
		// 关闭对象，释放资源,必须存在以下两个

	}

	// 个人工作量，平时的流水作业
	private void write_output_daily_head(String output, String date1, String date2) throws SQLException {
		String[][] s = {
				{ "个人流水表" + date1 + "至" + date2, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
						"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
						"", "", "", "", "", "", "", "", "", "" },
				{ "洗片人1", "数量1", "洗片人2", "数量2", "洗片人3", "数量3", "工作时间", "工作人员", "工程编号", "委托单位", "项目名称", "射线", "射线", "射线",
						"射线", "超声", "超声", "磁粉", "磁粉", "渗透", "渗透", "测厚", "配合", "全定量", "全定量", "半定量", "半定量", "硬度", "打磨",
						"TOFD", "拉伸", "弯曲", "冲击", "铁素体", "金相", "点口、特检院配合、技术指导 数量", "其他1", "其他2", "其他3", "备注", "", "",
						"", "", "", "", "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "", "", "", "", "", "X光/张", "x光废片", "Y光/张", "Y光废片", "米", "道", "米", "道", "米",
						"道", "点", "工日", "点", "个元素", "点", "个元素", "点", "米", "米", "件", "件", "件", "点", "件", "", "", "", "",
						"", "", "", "", "", "", "", "", "", "", "", "", "", "", } };
		try {
			System.out.println("开始生成excel");
			workbook = Workbook.createWorkbook(new File(output));
			WritableSheet sheet = workbook.createSheet("流水表", 0);

			ArrayList<String> al = new ArrayList<String>();
			String[] str = new String[] { "射线", "超声", "磁粉", "渗透", "测厚", "配合", "全定量", "半定量", "硬度", "打磨", "TOFD", "拉伸",
					"弯曲", "冲击", "铁素体", "金相", "点口、特检院配合、技术指导 数量", "其他1", "其他2", "其他3", "洗片人2", "数量2", "洗片人3", "数量3" };
			for (int i = 0; i < str.length; i++) {
				al.add(str[i]);
			}
			;// 初始化al的步骤
			this.writehead(sheet, s);// 调用本地书写标题的方法
			rs = db.submit(kinds, date1, date2);
			this.writeExcel(rs, sheet, 3);
			this.afteroutput(sheet, al, 1, 3);
			this.mergeall(sheet, 1);
			workbook.write();
			workbook.close();
			System.out.println("关闭");
			JOptionPane.showMessageDialog(null, "输出成功" + output, "成功", JOptionPane.PLAIN_MESSAGE);

		} catch (IOException | WriteException e) {
			// TODO Auto-generated catch block
			System.out.println("流水表try出问题");
			e.printStackTrace();
		}

	}

	// 工人的干活数目
	private void write_output_wages_head(String output, String date1, String date2) throws IOException {
		String[][] s = { { "序号", "作业人员", "指令单单号", "x光/张", "x光/张", "x光/张", "x光/次", "x光/次", "x光/次", "x光/次", "Y/张", "Y/张",
				"Y/张", "Y/次", "Y/次", "Y/次", "Y/次", "超声/米", "超声/米", "超声/米", "超声/道", "超声/道", "超声/道", "超声/次", "超声/次",
				"超声/次", "超声/次", "磁粉/米", "磁粉/米", "磁粉/米", "磁粉/道", "磁粉/道", "磁粉/道", "磁粉/次", "磁粉/次", "磁粉/次", "磁粉/次", "渗透/米",
				"渗透/米", "渗透/米", "渗透/道", "渗透/道", "渗透/道", "渗透/次", "渗透/次", "渗透/次", "渗透/次", "测厚/点", "测厚/点", "测厚/点", "测厚/次",
				"测厚/次", "测厚/次", "测厚/次", "表面配合/工日", "表面配合/工日", "表面配合/工日", "全定量/点", "全定量/点", "全定量/点", "全定量/个元素",
				"全定量/个元素", "全定量/个元素", "全定量/次", "全定量/次", "全定量/次", "全定量/次", "半定量/点", "半定量/点", "半定量/点", "半定量/个元素", "半定量/个元素",
				"半定量/个元素", "半定量/次", "半定量/次", "半定量/次", "半定量/次", "硬度/点", "硬度/点", "硬度/点", "硬度/次", "硬度/次", "硬度/次", "硬度/次",
				"打磨/米", "打磨/米", "打磨/米", "打磨/次", "打磨/次", "打磨/次", "打磨/次", "TOFD/米", "TOFD/米", "TOFD/米", "TOFD/次",
				"TOFD/次", "TOFD/次", "TOFD/次", "拉伸/件", "拉伸/件", "拉伸/件", "拉伸/次", "拉伸/次", "拉伸/次", "拉伸/次", "弯曲/件", "弯曲/件",
				"弯曲/件", "弯曲/次", "弯曲/次", "弯曲/次", "弯曲/次", "冲击/件", "冲击/件", "冲击/件", "冲击/次", "冲击/次", "冲击/次", "冲击/次", "铁素体/点",
				"铁素体/点", "铁素体/点", "铁素体/次", "铁素体/次", "铁素体/次", "铁素体/次", "金相/件", "金相/件", "金相/件", "金相/次", "金相/次", "金相/次", "金相/次",
				"点口、特检院配合、技术指导 数量", "点口、特检院配合、技术指导 数量", "点口、特检院配合、技术指导 数量", "其他项目1", "其他项目1", "其他项目1", "其他项目2", "其他项目2",
				"其他项目2", "其他项目3", "其他项目3", "其他项目3", "计件工资合计（元）", "开车（天）", "监护（天）", "开车及监护（元）", "总计应发工资（元）", "考试费补贴（元）",
				"已发工资（元）", "公司借款（元）", "应扣除总金额（元）", "剩余未发工资金额  （元）", "备注", },
				{ "", "", "", "数量", "单价(元)", "小计(元)", "总数量(张)", "总次数", "单价(元)", "小计(元)", "数量", "单价(元)", "小计(元)",
						"总数量(张)", "总次数", "单价(元)", "小计(元)", "数量", "单价(元)", "小计(元)", "数量", "单价(元)", "小计(元)", "总数量(张)",
						"总次数", "单价(元)", "小计(元)", "数量", "单价(元)", "小计(元)", "数量", "单价(元)", "小计(元)", "总数量(张)", "总次数",
						"单价(元)", "小计(元)", "数量", "单价(元)", "小计(元)", "数量", "单价(元)", "小计(元)", "总数量(张)", "总次数", "单价(元)",
						"小计(元)", "数量", "单价(元)", "小计(元)", "总数量(张)", "总次数", "单价(元)", "小计(元)", "数量", "单价(元)", "小计(元)",
						"数量", "单价(元)", "小计(元)", "数量", "单价(元)", "小计(元)", "数量", "", "单价(元)", "小计(元)", "数量", "单价(元)",
						"小计(元)", "数量", "单价(元)", "小计(元)", "总数量(张)", "总次数", "单价(元)", "小计(元)", "数量", "单价(元)", "小计(元)",
						"总数量(张)", "总次数", "单价(元)", "小计(元)", "数量", "单价(元)", "小计(元)", "总数量(张)", "总次数", "单价(元)", "小计(元)",
						"数量", "单价(元)", "小计(元)", "总数量(张)", "总次数", "单价(元)", "小计(元)", "数量", "单价(元)", "小计(元)", "总数量(张)",
						"总次数", "单价(元)", "小计(元)", "数量", "单价(元)", "小计(元)", "总数量(张)", "总次数", "单价(元)", "小计(元)", "数量",
						"单价(元)", "小计(元)", "总数量(张)", "总次数", "单价(元)", "小计(元)", "数量", "单价(元)", "小计(元)", "总数量(张)", "总次数",
						"单价(元)", "小计(元)", "数量", "单价(元)", "小计(元)", "总数量(张)", "总次数", "单价(元)", "小计(元)", "数量", "单价(元)",
						"小计(元)", "数量", "单价(元)", "小计(元)", "数量", "单价(元)", "小计(元)", "数量", "单价(元)", "小计(元)", "", "", "", "",
						"", "", "", "", "", "", "", }, };
		try {
			workbook = Workbook.createWorkbook(new File(output));
			WritableSheet sheet = workbook.createSheet("工人工作统计表", 0);

			this.writehead(sheet, s);// 调用本地书写标题的方法
			rs = db.submit(kinds, date1, date2);
			this.writeExcel(rs, sheet, 2);
			this.afteroutput_wage(sheet, 0, 2);
			this.afteroutput_wage_add(sheet, 0, 0);
			this.mergeall(sheet, 0);
			workbook.write();
			workbook.close();
			JOptionPane.showMessageDialog(null, "输出成功" + output, "成功", JOptionPane.PLAIN_MESSAGE);
		} catch (IOException | WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("sql出了问题");
		}
	}

	private void output_report(String output, String date1, String date2) {

		String[][] s = {
				{ "合同编号", "合同价", "指令单编号", "下达日期", "工程名称", "委托单位", "联系人", "工程编号", "检测方法", "项目负责人", "开工日期", "完工日期",
						"完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量",
						"完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量",
						"完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量",
						"完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量",
						"完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量", "完成工作量", "本年产值（元）", "本年产值（元）", "上年产值未结算额", "报告情况",
						"报告情况", "签证单日期", "结算情况", "结算情况", "上年度结款金额(元)", "本年度结款金额(元)", "开票额", "发票号", "开票日期", "未开票额",
						"收款额", "收款日期", "收款累计", "备注", "未签单", "未结算签单", "报告工作量", },
				{ "", "", "", "", "", "", "", "", "", "", "", "", "x光/张", "x光/张", "Y/张", "Y/张", "超声/米", "超声/米", "超声/道",
						"超声/道", "磁粉/米", "磁粉/米", "磁粉/道", "磁粉/道", "渗透/米", "渗透/米", "渗透/道", "渗透/道", "测厚/点", "测厚/点",
						"表面配合/工日", "表面配合/工日", "全定量/点", "全定量/点", "全定量/个元素", "全定量/个元素", "半定量/点", "半定量/点", "半定量/个元素",
						"半定量/个元素", "硬度/点", "硬度/点", "打磨/米", "打磨/米", "TOFD/米", "TOFD/米", "拉伸/件", "拉伸/件", "弯曲/件", "弯曲/件",
						"冲击/件", "冲击/件", "铁素体/点", "铁素体/点", "金相/件", "金相/件", "点口、特检院配合、技术指导 数量", "点口、特检院配合、技术指导 数量", "",
						"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", },
				{ "", "", "", "", "", "", "", "", "", "", "", "", "当月", "累计", "当月", "累计", "当月", "累计", "当月", "累计", "当月",
						"累计", "当月", "累计", "当月", "累计", "当月", "累计", "当月", "累计", "当月", "累计", "当月", "累计", "当月", "累计", "当月",
						"累计", "当月", "累计", "当月", "累计", "当月", "累计", "当月", "累计", "当月", "累计", "当月", "累计", "当月", "累计", "当月",
						"累计", "当月", "累计", "当月", "累计", "当月", "累计", "", "报告编号", "报告日期", "", "结算单号", "结算单日期", "", "", "",
						"", "", "", "", "", "", "", "", "", "", },

		};
		try {
			workbook = Workbook.createWorkbook(new File(output));
			WritableSheet sheet = workbook.createSheet("报告表", 0);

			ArrayList al = new ArrayList();
			String[] str = new String[] { "x光/张", "x光/次", "Y/张", "Y/次", "超声/米", "超声/道", "超声/次", "磁粉/米", "磁粉/道", "磁粉/次",
					"测厚/点", "测厚/次", "表面配合/工日", "全定量/点", "全定量/个元素", "全定量/次", "半定量/点", "半定量/个元素", "半定量/次", "硬度/点", "硬度/次",
					"打磨/米", "打磨/次", "TOFD/米", "TOFD/次", "拉伸/件", "拉伸/次", "弯曲/件", "弯曲/次", "冲击/件", "冲击/次", "铁素体/点",
					"铁素体/次", "金相/件", "金相/次", "点口、特检院配合、技术指导 数量", "渗透/米", "渗透/次", "渗透/道" };
			for (int i = 0; i < str.length; i++) {
				al.add(str[i]);
			}
			;// 初始化al的步骤

			this.writehead(sheet, s);// 调用本地书写标题的方法
			rs = db.submit(kinds, date1, date2);
			this.writeExcel(rs, sheet, 3);
			this.afteroutput_report(sheet, al, 1, 3);
			this.mergeall(sheet, 0);
			// this.mergeall(sheet, 1);
			workbook.write();
			workbook.close();
			JOptionPane.showMessageDialog(null, "输出成功" + output, "成功", JOptionPane.PLAIN_MESSAGE);
		} catch (IOException | WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("sql出了问题");
		}
	}

	// 提供一个稍微简单化的基础方法
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

	// 书写rs的方法
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
						jxl.write.Number number = new jxl.write.Number(i - 1, row + index, d); // 将数字3.14159
																								// 放到单元格D5
						sheet.addCell(number);
					} else
						sheet.addCell(new Label(i - 1, row + index, rs.getObject(i).toString()));
				}

			}
			index++;
		}

	}

	// 第一个帐票的后处理方法，扫描列，如果列上没有值则进行删除,第一个参数是扫描段，第二个参数是从哪里开始进行判断是否为空0/1
	private void afteroutput(WritableSheet sheet, ArrayList<String> al, int head, int body) {
		int colend = sheet.getColumns();
		for (int i = 0; i < colend; i++) {
			System.out.println("此时colend:" + colend);
			String item = sheet.getCell(i, head).getContents();// 得到扫描头部cell值
			// System.out.println(i);
			System.out.println("取得头内容：" + i + head + ":" + item);
			System.out.println("判断是否al里面有值" + al.contains(item));
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

	// 第一个帐票的判断该列的方法，从指定行开始是否为空,第一个参数是从哪行开始进行判断，第二个参数是从第几列开始进行判断，,都是0开始计数的情况下
	private boolean isEmpty(WritableSheet sheet, int body, int col) {
		int rowend = sheet.getRows();
		// System.out.println("rowend:"+rowend);
		for (int i = body; i < rowend; i++) {
			System.out.println("" + col + i + "的值:" + sheet.getCell(col, i).getContents());
			if (!sheet.getCell(col, i).getContents().equals("")
					&& !sheet.getCell(col, i).getContents().toString().equals("0")
					&& !sheet.getCell(col, i).getContents().toString().equals("0.00")
					&& !sheet.getCell(col, i).getContents().toString().equals("按次米：0.00,按次道：0.00")
					&& !sheet.getCell(col, i).getContents().toString().equals("按次全点：0.00,按次全个：0.00")
					&& !sheet.getCell(col, i).getContents().toString().equals("按次半点：0.00,按次半个：0.00")) {
				System.out.println("" + col + i + "有值");
				return false;
			}

		}
		return true;
	}

	// 第二个帐票处理的后处理方法，扫描指定列，进行删除空列
	private void afteroutput_wage(WritableSheet sheet, int head, int body) {
		ArrayList<String> al = new ArrayList<String>();
		String[] str1 = new String[] { "x光/张", "Y/张", "超声/米", "超声/道", "磁粉/米", "磁粉/道", "测厚/点", "表面配合/工日", "全定量/点",
				"全定量/个元素", "半定量/点", "半定量/个元素", "硬度/点", "打磨/米", "TOFD/米", "拉伸/件", "弯曲/件", "冲击/件", "铁素体/点", "金相/件",
				"点口、特检院配合、技术指导 数量", "渗透/米", "渗透/道", "其他项目1", "其他项目2", "其他项目3" };
		for (int i = 0; i < str1.length; i++) {
			al.add(str1[i]);
		}
		;// 初始化al的步骤
		ArrayList<String> al2 = new ArrayList<String>();
		String[] str2 = new String[] { "x光/次", "Y/次", "超声/次", "磁粉/米", "磁粉/次", "测厚/次", "全定量/次", "半定量/次", "打磨/次",
				"TOFD/次", "拉伸/次", "弯曲/次", "冲击/次", "铁素体/次", "金相/次", "渗透/次", "硬度/次" };
		for (int i = 0; i < str2.length; i++) {
			al2.add(str2[i]);
		}
		;// 初始化al的步骤

		int colend = sheet.getColumns();
		for (int i = 0; i < colend; i++) {
			System.out.println("此时colend:" + colend);
			String item = sheet.getCell(i, head).getContents();// 得到扫描头部cell值
			// System.out.println(i);
			System.out.println("取得头内容：" + i + "," + head + ":" + item);

			if (al.contains(item)) {
				System.out.println("判断:是否al里面有" + item);
				if (isEmpty(sheet, body, i)) {
					System.out.println("" + item + "列是空的");
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

	// 第二个帐票的合计处理方法
	private void afteroutput_wage_add(WritableSheet sheet, int head, int body) {
		BigDecimal bg = new BigDecimal("0.00");
		// 定义总值

		System.out.println("------------------新建的bg:" + bg.toString());
		int add = 0;// 插入总值的位置
		int row = sheet.getRows();
		int colend = sheet.getColumns();// 得到横向上的列数
		System.out.println("表有几行：" + row);
		System.out.println("表有几列：" + colend);
		// 遍历行
		for (int i = 1; i < row; i++) {
			// 遍历横向上的值
			bg = new BigDecimal("0.00");
			for (int j = 1; j < colend + 1; j++) {
				// 得到是小计的
				String xiaoji = sheet.getCell(j, 1).getContents().toString();
				System.out.println("" + sheet.getCell(j, 0).getContents().toString());
				if (sheet.getCell(j, 0).getContents().toString().equals("计件工资合计（元）")) {
					add = j;
				}
				if (xiaoji.equals("小计(元)")) {
					// 得到小计（元）上的值
					// 当行进到第3行开始
					if (i > 1) {
						String s = sheet.getCell(j, i).getContents().toString();
						System.out.println("小计上的值是：--------------------------------------" + s);
						// 如果空的情况下
						if (s.equals("")) {
							System.out.println("------------------------被判定为空");
							bg = bg.add(new BigDecimal("0.00"));
						} else if (!s.equals("")) {
							System.out.println("------------------------被判定不为空");
							bg = bg.add(new BigDecimal(s));
							System.out.println(
									"这个时候总值已经是：--------------------------------------------------" + bg.toString());
						}
					}
				}

				// System.out.println(i+","+j+"的内容是:"+s);
			}
			// 一行扫描结束后，对某一个地方进行赋值
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

	// 第三个后处理方法
	private void afteroutput_report(WritableSheet sheet, ArrayList<String> al, int head, int body) {
		int colend = sheet.getColumns();
		for (int i = 0; i < colend; i++) {
			System.out.println("此时colend:" + colend);
			String item = sheet.getCell(i, head).getContents();// 得到扫描头部cell值
			// System.out.println(i);
			System.out.println("取得头内容：" + i + head + ":" + item);
			System.out.println("判断是否al里面有值" + al.contains(item));
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

	// 第三个帐票的判断方法
	private boolean isEmpty_3(WritableSheet sheet, int body, int col, String item) {
		boolean isempty = true;
		int rowend = sheet.getRows();
		// System.out.println("rowend:"+rowend);
		for (int i = body; i < rowend + 2; i++) {
			System.out.println("" + col + "，" + i + "的值:" + sheet.getCell(col, i).getContents());
			System.out.println("" + col + "," + i + "的值:" + sheet.getCell(col + 1, i).getContents());
			if (!sheet.getCell(col, i).getContents().toString().equals("")) {
				System.out.println("" + col + i + "有值");
				isempty = false;
			}
			if (!sheet.getCell(col + 1, i).getContents().toString().equals("")) {
				System.out.println("" + col + i + "有值");
				isempty = false;
			}
			if (!sheet.getCell(col, i).getContents().toString().equals("0.00")) {
				System.out.println("" + col + i + "有值");
				isempty = false;
			}
			if (!sheet.getCell(col + 1, i).getContents().toString().equals("0.00")) {
				System.out.println("" + col + i + "有值");
				isempty = false;
			}
			/*
			 * if(isempty==false){ String exist=sheet.getCell(9,
			 * i).getContents().toString(); System.out.println(
			 * "--------------------------------------------------------："+i+
			 * item); }
			 */

		}
		return isempty;
	}

	// 后处理，扫描表头进行合并,指定第几列
	private void mergeall(WritableSheet sheet, int row) throws RowsExceededException, WriteException {
		// Sheet sheet1=sheet;
		int i = 0;// 主指针
		int j = 0;// 复指针
		for (int j1 = 0; j1 < sheet.getColumns() + 1; j1++) {
			// 如果碰到了不一致的值
			System.out.println("j1的值" + j1);
			System.out.println("i的值" + i);
			if (!(sheet.getCell(i, row).getContents().equals(sheet.getCell(j1, row).getContents()))) {
				sheet.mergeCells(i, row, j1 - 1, row);
				i = j1;
			}
		}
	}

	public static void main(String[] agrs) {
		try {
			new ExcelBean("test流水.xls", "流水表", "20161010", "20161010", "bumen1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// new ExcelBean("测试1.xls","20161010","20161010");

	}

}
