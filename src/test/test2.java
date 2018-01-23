package test;

public class test2 {
	public static void main(String[] agrs) {

		String[] s = new String[] { "x_pzhang", "x_pci", "y_pzhang", "y_pci", "chaosheng_pmi", "chaosheng_pdao",
				"chaosheng_pci", "cifen_pmi", "cifen_pdao", "cifen_pci", "shentou_pmi", "shentou_pdao", "shentou_pci",
				"cehou_pdian", "cehou_pci", "biaopei_pri", "guangpuquan_pdian", "guangpuquan_pge", "guangpuquan_pci",
				"guangpuban_pdian", "guangpuban_pge", "guangpuban_pci", "yingdu_pdian", "yingdu_pci", "damo_pmi",
				"damo_pci", "tofd_pmi", "tofd_pci", "lashen_pjian", "lashen_pci", "wanqu_pjian", "wanqu_pci",
				"chongji_pjian", "chongji_pci", "tie_pdian", "tie_pci", "jinxiang_pjian", "jinxiang_pci",
				"diankou_pamount" };
		String[] s2 = new String[] { "x_pzhang", "y_pzhang", "chaosheng_pmi", "chaosheng_pdao", "cifen_pmi",
				"cifen_pdao", "shentou_pmi", "shentou_pdao", "cehou_pdian", "biaopei_pri", "guangpuquan_pdian",
				"guangpuquan_pge", "guangpuban_pdian", "guangpuban_pge", "yingdu_pdian", "damo_pmi",
				"tofd_pmi", "lashen_pjian", "wanqu_pjian", "chongji_pjian", "tie_pdian", "jinxiang_pjian",
				"diankou_pamount" };
		for (int i = 0; i < s2.length; i++) {
			// µ±ÔÂ
			// System.out.println("(select sum(ifnull(T."+s[i]+",0)) from
			// workload_records_table T where T.workdate>=\"+datexiugai+\" and
			// T.workdate<=\"+date2+\"),");
			// ÀÛ¼Æ
			// System.out.println("(select sum(ifnull(T."+s[i]+",0)) from
			// workload_records_table T where T.workdate>=\"+date1+\" and
			// T.workdate<=\"+date2+\"),");
				//System.out.println("T_yue." + s2[i] + ",");
				//System.out.println("T_nian." + s2[i] + ",");
			System.out.println("sum(ifnull(T."+s2[i]+",0)) "+s2[i]+",");
			
		}

	}

}
