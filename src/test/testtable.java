package test;

import java.awt.Color;
import java.awt.Component;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import MainFrame.SQLManager;


public class testtable extends JFrame{

	private ResultSet rs;
	private SQLManager SQLManager=new SQLManager();
	private JTable jt;
	private ResultSetTable ResultSetTable;
	public testtable(){
		//如果需要添加合计的场合，与不合计场合的区别是在其后添加group by sw_id with rollup;sw_id这个位置是一条数据的内部id
		String sql="SELECT t.sw_id,t.cmd_num,t.cmd_num,t.x_pzhang,t.cmd_num,t.cmd_num,t.cmd_num FROM mingfeng_project.workload_records_table t group by sw_id with rollup;";
		rs=SQLManager.submit_search(sql);
		ResultSetTable=new ResultSetTable(rs);
		//在需要添加合计的场合必须必须必须调用这个方法使之为true
		ResultSetTable.isCountAll(true);
		jt=new JTable(ResultSetTable);
		//以下一条语句是添加颜色功能，只要加上去就会出现颜色
		jt.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                //Color color = Color.decode((String)table.getValueAt(row, 1));
            	if(row%2==0){
            	//这里可以手动修改漂亮适合的颜色，采用RGB模式，可以去网上进行颜色RGB查询方式得到RGB值
            	Color color =new Color(51,143,204);
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                comp.setBackground(color);
                return comp;
                }
            	//这个是底色，就让他是白色就可以
            	Color color = Color.white;
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                comp.setBackground(color);
                return comp;
            }
            
        });
        
		this.add(new JScrollPane(jt));
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public static void main(String agrs[]){
		new testtable();
	}

}
class ResultSetTable extends AbstractTableModel
{
	/**
	 * 
	 */
	private boolean isCountall=false;//是否进行合计
	private static final long serialVersionUID = 1L;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	// 构造器，初始化rs和rsmd两个属性
	public ResultSetTable(ResultSet aResultSet)
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
	public String getColumnLabel(int c)
	{
		try
		{
			return rsmd.getColumnName(c + 1);
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
					System.out.println("这是合计行，r+1="+(r+1));
					if(c==1){
						System.out.println("r和c 分别为：:"+r+"和"+c);
						System.out.println("c=1,输出合计");
						return "合计";
					}else if(c>1){
						System.out.println("进入c>1");
						BigDecimal sum=new BigDecimal("0.0");
							for(int j=0;j<getRowCount()-1;j++){
								rs.absolute(j + 1);
								//return rs.getObject(c + 1);
								System.out.println("rs定位到"+(j+1)+"行");
								System.out.println("开始对sum进行累加");
								System.out.println(rs.getObject(c+1).toString());
								sum=sum.add(new BigDecimal(rs.getObject(c+1).toString()));
								System.out.println("输出"+c+j+"："+sum.toString());
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
			System.out.println("rs.getRowCount() =" + rs.getRow());
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
			rs.updateObject(column + 1 , aValue);
			// 提交修改
			rs.updateRow();
			// 触发单元格的修改事件
			fireTableCellUpdated(row, column);
		}
		catch (SQLException evt)
		{
			evt.printStackTrace();
		}
	}
	public void isCountAll(boolean a){
		this.isCountall=a;
	}
}

