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
		//�����Ҫ��ӺϼƵĳ��ϣ��벻�ϼƳ��ϵ���������������group by sw_id with rollup;sw_id���λ����һ�����ݵ��ڲ�id
		String sql="SELECT t.sw_id,t.cmd_num,t.cmd_num,t.x_pzhang,t.cmd_num,t.cmd_num,t.cmd_num FROM mingfeng_project.workload_records_table t group by sw_id with rollup;";
		rs=SQLManager.submit_search(sql);
		ResultSetTable=new ResultSetTable(rs);
		//����Ҫ��ӺϼƵĳ��ϱ�������������������ʹ֮Ϊtrue
		ResultSetTable.isCountAll(true);
		jt=new JTable(ResultSetTable);
		//����һ������������ɫ���ܣ�ֻҪ����ȥ�ͻ������ɫ
		jt.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                //Color color = Color.decode((String)table.getValueAt(row, 1));
            	if(row%2==0){
            	//��������ֶ��޸�Ư���ʺϵ���ɫ������RGBģʽ������ȥ���Ͻ�����ɫRGB��ѯ��ʽ�õ�RGBֵ
            	Color color =new Color(51,143,204);
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                comp.setBackground(color);
                return comp;
                }
            	//����ǵ�ɫ���������ǰ�ɫ�Ϳ���
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
	private boolean isCountall=false;//�Ƿ���кϼ�
	private static final long serialVersionUID = 1L;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	// ����������ʼ��rs��rsmd��������
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
	// ��дgetColumnName����������Ϊ��TableModel��������
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
					System.out.println("���Ǻϼ��У�r+1="+(r+1));
					if(c==1){
						System.out.println("r��c �ֱ�Ϊ��:"+r+"��"+c);
						System.out.println("c=1,����ϼ�");
						return "�ϼ�";
					}else if(c>1){
						System.out.println("����c>1");
						BigDecimal sum=new BigDecimal("0.0");
							for(int j=0;j<getRowCount()-1;j++){
								rs.absolute(j + 1);
								//return rs.getObject(c + 1);
								System.out.println("rs��λ��"+(j+1)+"��");
								System.out.println("��ʼ��sum�����ۼ�");
								System.out.println(rs.getObject(c+1).toString());
								sum=sum.add(new BigDecimal(rs.getObject(c+1).toString()));
								System.out.println("���"+c+j+"��"+sum.toString());
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
			System.out.println("rs.getRowCount() =" + rs.getRow());
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
			rs.updateObject(column + 1 , aValue);
			// �ύ�޸�
			rs.updateRow();
			// ������Ԫ����޸��¼�
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

