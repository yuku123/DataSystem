package MainFrame;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;



/*
 * table的一个MODEL，可以自己满足修改字段，同步数据库功能，提供给其他查询面板，修改面板使用/
 */

public class DBTableModel extends AbstractTableModel{
	private boolean isCountall;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	// 构造器，初始化rs和rsmd两个属性
	public DBTableModel(ResultSet aResultSet)
	{
		rs = aResultSet;
		try
		{
			rsmd = rs.getMetaData();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	// 重写getColumnName方法，用于为该TableModel设置列名
	public String getColumnName(int c)
	{
		try
		{
			return rsmd.getColumnLabel(c + 1);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return "";
		}
	}
	// 重写getColumnCount方法，用于设置该TableModel的列数
	public int getColumnCount()
	{
		try
		{
			return rsmd.getColumnCount();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	// 重写getValueAt方法，用于设置该TableModel指定单元格的值
	public Object getValueAt(int r, int c)
	{
		try
		{
			if(this.isCountall==true){
				if(r+1==getRowCount()){
					if(c==1){
						System.out.println("ccc:"+r+c);
						return "合计";
					}else if(c>1){
						System.out.println("进入c>1");
						BigDecimal sum=new BigDecimal("0.0");
							for(int j=0;j<getRowCount()-1;j++){
								rs.absolute(j + 1);
								//return rs.getObject(c + 1);
								System.out.println("开始对sum进行累加");
								System.out.println(rs.getObject(c+1)==(Object)null);
								if(rs.getObject(c+1)==(Object)null){
									sum=sum.add(new BigDecimal("0.0"));
									System.out.println("输出"+c+j+"："+sum.toString());
								}else if(!(rs.getObject(c+1)==(Object)null)){
									sum=sum.add(new BigDecimal(rs.getObject(c+1).toString()));
									System.out.println("输出"+c+j+"："+sum.toString());
								}
								
							}
							System.out.println("return sum.toString():"+sum.toString());
							return sum.toString();
						}
				}
				rs.absolute(r + 1);
				return rs.getObject(c + 1);
			}else if(this.isCountall==false){
				rs.absolute(r + 1);
				return rs.getObject(c + 1);
			}
			return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	// 重写getColumnCount方法，用于设置该TableModel的行数
	public int getRowCount()
	{
		try
		{
			rs.last();
			return rs.getRow();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	// 重写isCellEditable返回true，让每个单元格可编辑
	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
		return true;
	}
	// 重写setValueAt()方法，当用户编辑单元格时，将会触发该方法
	public void setValueAt(Object aValue , int row,int column)
	{
		try
		{
			// 结果集定位到对应的行数
			rs.absolute(row + 1);
			// 修改单元格多对应的值
			if(aValue.toString().equals("")){
				rs.updateNull(column + 1);
			}else rs.updateObject(column + 1 , aValue);
			
			// 提交修改
			rs.updateRow();
			// 触发单元格的修改事件
			fireTableCellUpdated(row, column);
			System.out.println("修改成功");
		}
		catch (SQLException evt)
		{
			evt.printStackTrace();
		}
	}
	public void isCountAll(boolean a){
		this.isCountall=a;
	}
	
	public ResultSet returnResultSet(){
		return rs;
	}
}
