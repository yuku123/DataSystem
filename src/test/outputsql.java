package test;

public class outputsql {
	public static void main(String[] agrs) {

		String[] name1 = new String[] { "x光张", "Y张", "超声米", "超声道", "磁粉米", "磁粉道", "渗透米", "渗透道", "测厚点",
				"表面配合工日", "全定量点", "全定量个元素", "半定量点", "半定量个元素", "硬度点", "打磨米", "TOFD米", "拉伸件", "弯曲件", "冲击件",
				"铁素体点", "金相件", "点口、特检院配合、技术指导 数量" };
		String[] duineidanjia = new String[] { "x_pzhang_price", "y_pzhang_price", "chaosheng_pmi_price",
				"chaosheng_pdao_price", "cifen_pmi_price", "cifen_pdao_price", "shentou_pmi_price",
				"shentou_pdao_price", "cehou_pdian_price", "biaopei_pri_price", "guangpuquan_pdian_price",
				"guangpuquan_pge_price", "guangpuban_pdian_price", "guangpuban_pge_price", "yingdu_pdian_price",
				"damo_pmi_price", "tofd_pmi_price", "lashen_pjian_price", "wanqu_pjian_price", "chongji_pjian_price",
				"tie_pdian_price", "jinxiang_pjian_price", "diankou_pamount_price" };

		String[] ziduan = new String[] { "x_pzhang", "y_pzhang", "chaosheng_pmi", "chaosheng_pdao", "cifen_pmi",
				"cifen_pdao", "shentou_pmi", "shentou_pdao", "cehou_pdian", "biaopei_pri", "guangpuquan_pdian",
				"guangpuquan_pge", "guangpuban_pdian", "guangpuban_pge", "yingdu_pdian", "damo_pmi", "tofd_pmi",
				"lashen_pjian", "wanqu_pjian", "chongji_pjian", "tie_pdian", "jinxiang_pjian", "diankou_pamount" };

		String[] ciduinei = new String[] { "x_pci", "y_pci", "chaosheng_pci", "chaosheng_pci", "cifen_pci", "cifen_pci",
				"shentou_pci", "shentou_pci", "cehou_pci", "", "guangpuquan_pci", "guangpuquan_pci", "guangpuban_pci", "guangpuban_pci", "yingdu_pci",
				"damo_pci", "tofd_pci", "lashen_pci", "wanqu_pci", "chongji_pci", "tie_pci", "jinxiang_pci",""};

		for (int i = 0; i < name1.length; i++) {
			//System.out.println(name1[i] + ":" + duineidanjia[i] + ":" + ziduan[i] + "次字段:::" + ciduinei[i]);
			//System.out.println("sum(case when t1."+ciduinei[i]+" is not null then 0 else ifnull(t1."+ziduan[i]+",0) end) 普通"+name1[i]+",");
			//System.out.println("t2."+duineidanjia[i]+" 普通"+name1[i]+"价格,");
			//System.out.println("sum(case when t1."+ciduinei[i]+" is not null then 0 else ifnull(t1."+ziduan[i]+",0) end)*t2."+duineidanjia[i]+" "+name1[i]+"总值,");
			//System.out.println("sum(case when t1."+ciduinei[i]+" is not null then ifnull(t1."+ziduan[i]+",0) else 0  end)  "+name1[i]+"按次的拍片总数量,");
			//System.out.println("sum(case when t1."+ciduinei[i]+" is not null then 1 else 0 end) 一个月去过几次,");
			//System.out.println("group_concat(distinct ifnull(t1."+ciduinei[i]+",0)) 不进行排异的统计量,");
			//System.out.println("sum(ifnull(t1."+ciduinei[i]+",0)) 次的总值,");
			//System.out.println("#####");
			System.out.println("+ifnull(sum(case when t1."+ciduinei[i]+" is not null then 0 else ifnull(t1."+ziduan[i]+",0) end)*t2."+duineidanjia[i]+" ,0)");
			System.out.println("+sum(ifnull(t1."+ciduinei[i]+",0)) ");
		
		}
	}

}
