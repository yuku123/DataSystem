package MainFrame;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;



/*
 * table��һ��MODEL�������Լ������޸��ֶΣ�ͬ�����ݿ⹦�ܣ��ṩ��������ѯ��壬�޸����ʹ��/
 */

public class DBTableModel extends AbstractTableModel{
	private boolean isCountall;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	// ����������ʼ��rs��rsmd��������
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
	// ��дgetColumnName����������Ϊ��TableModel��������
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
	// ��дgetColumnCount�������������ø�TableModel������
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
	// ��дgetValueAt�������������ø�TableModelָ����Ԫ���ֵ
	public Object getValueAt(int r, int c)
	{
		try
		{
			if(this.isCountall==true){
				if(r+1==getRowCount()){
					if(c==1){
						System.out.println("ccc:"+r+c);
						return "�ϼ�";
					}else if(c>1){
						System.out.println("����c>1");
						BigDecimal sum=new BigDecimal("0.0");
							for(int j=0;j<getRowCount()-1;j++){
								rs.absolute(j + 1);
								//return rs.getObject(c + 1);
								System.out.println("��ʼ��sum�����ۼ�");
								System.out.println(rs.getObject(c+1)==(Object)null);
								if(rs.getObject(c+1)==(Object)null){
									sum=sum.add(new BigDecimal("0.0"));
									System.out.println("���"+c+j+"��"+sum.toString());
								}else if(!(rs.getObject(c+1)==(Object)null)){
									sum=sum.add(new BigDecimal(rs.getObject(c+1).toString()));
									System.out.println("���"+c+j+"��"+sum.toString());
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
	// ��дgetColumnCount�������������ø�TableModel������
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
	// ��дisCellEditable����true����ÿ����Ԫ��ɱ༭
	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
		return true;
	}
	// ��дsetValueAt()���������û��༭��Ԫ��ʱ�����ᴥ���÷���
	public void setValueAt(Object aValue , int row,int column)
	{
		try
		{
			// �������λ����Ӧ������
			rs.absolute(row + 1);
			// �޸ĵ�Ԫ����Ӧ��ֵ
			if(aValue.toString().equals("")){
				rs.updateNull(column + 1);
			}else rs.updateObject(column + 1 , aValue);
			
			// �ύ�޸�
			rs.updateRow();
			// ������Ԫ����޸��¼�
			fireTableCellUpdated(row, column);
			System.out.println("�޸ĳɹ�");
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
