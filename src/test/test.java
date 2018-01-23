package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import jxl.Cell;
import jxl.CellType;
import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class test {
	public void submit(String output) throws JXLException{
		try {
			InputStream stream = new FileInputStream(output);
			Workbook workbook = Workbook.getWorkbook(stream);
			
			Sheet sheet=workbook.getSheet(0);
			for(int i=0;i<sheet.getRows();i++){
				System.out.print("{");
				for(int j=0;j<sheet.getColumns();j++){
					Cell a2 = sheet.getCell(j,i); //第一个是第几列，第二个值是第几行
				    String rest = a2.getContents();
				    System.out.print("\""+rest+"\",");
				    //if(rest.equals("")){System.out.print("'"+rest+"',");}
				    //System.out.print(""+rest+",");
				}
				System.out.print("},");
				System.out.println("");
			}
		} catch (IOException e){
			e.printStackTrace();}
		}
		
	public static void main(String []agrs) throws JXLException{
	  new test().submit("C:\\Users\\张子房\\Desktop\\test2.xls");
		/*
		Calendar cal = Calendar.getInstance(); 
		SimpleDateFormat datef=new SimpleDateFormat("yyyyMMdd");
		             //当前月的最后一天   
		             cal.set( Calendar.DATE, 1 );
		             cal.roll(Calendar.DATE, - 1 );
		             Date endTime=cal.getTime();
		             String endTime1=datef.format(endTime)+" 23:59:59";
		              //当前月的第一天          
		cal.set(GregorianCalendar.DAY_OF_MONTH, 1); 
		Date beginTime=cal.getTime();
		             //String beginTime1=datef.format(beginTime)+" 00:00:00";
		String beginTime1=datef.format(beginTime);
		             System.out.println(beginTime1);
		             */
		/*
		String str="20161010";
		char[] c=str.toCharArray();
		c[6]='0';
		c[7]='1';
		System.out.println(String.valueOf(c).toString());
		*/
	}
}
