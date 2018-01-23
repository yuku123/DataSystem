package MainFrame;
import MyPanels.*;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
public class MainFrame extends JFrame{
	private String group;//����ȫ�ֱ���
	private String name;//�û���
	private String password;//����

	//����ѧԺ��ŵ�����
	String coll_id;
	//�������ĸ����ڵ�
	private DefaultMutableTreeNode dmtnRoot=
	        new DefaultMutableTreeNode(new MyNode("����ѡ��","0"));
	private DefaultMutableTreeNode dmtn1=
	        new DefaultMutableTreeNode(new MyNode("ϵͳѡ��","1"));
	private DefaultMutableTreeNode dmtn2=
	        new DefaultMutableTreeNode(new MyNode("��������","2"));
	private DefaultMutableTreeNode dmtn3=
	        new DefaultMutableTreeNode(new MyNode("�޸Ĳ�ѯ","3"));
	private DefaultMutableTreeNode dmtn4=
	        new DefaultMutableTreeNode(new MyNode("�����Ʊ","4"));
	private DefaultMutableTreeNode dmtn11=
	        new DefaultMutableTreeNode(new MyNode("�˳�","11"));
	private DefaultMutableTreeNode dmtn13=
	        new DefaultMutableTreeNode(new MyNode("�����޸�","13"));
	private DefaultMutableTreeNode dmtn21=
	        new DefaultMutableTreeNode(new MyNode("������Ŀ","21"));
	private DefaultMutableTreeNode dmtn22=
	        new DefaultMutableTreeNode(new MyNode("�ճ���ˮ��","22"));
	private DefaultMutableTreeNode dmtn23=
	        new DefaultMutableTreeNode(new MyNode("��Ա����","23"));
	private DefaultMutableTreeNode dmtn24=
	        new DefaultMutableTreeNode(new MyNode("�����","24"));
	private DefaultMutableTreeNode dmtn25=
	        new DefaultMutableTreeNode(new MyNode("��ͬ","25"));
	private DefaultMutableTreeNode dmtn26=
	        new DefaultMutableTreeNode(new MyNode("���ʽ��㵥��","26"));
	private DefaultMutableTreeNode dmtn27=
	        new DefaultMutableTreeNode(new MyNode("��ֵ���㵥��","27"));
	private DefaultMutableTreeNode dmtn31=
	        new DefaultMutableTreeNode(new MyNode("��Ŀ","31"));
	private DefaultMutableTreeNode dmtn32=
	        new DefaultMutableTreeNode(new MyNode("�ճ���ˮ��","32"));
	private DefaultMutableTreeNode dmtn33=
	        new DefaultMutableTreeNode(new MyNode("��Ա����","33"));
	private DefaultMutableTreeNode dmtn34=
	        new DefaultMutableTreeNode(new MyNode("�����","34"));
	private DefaultMutableTreeNode dmtn35=
	        new DefaultMutableTreeNode(new MyNode("��ͬ����","35"));
	private DefaultMutableTreeNode dmtn36=
	        new DefaultMutableTreeNode(new MyNode("ϴƬ��ѯ","36"));
	private DefaultMutableTreeNode dmtn37=
	        new DefaultMutableTreeNode(new MyNode("���ʽ��㵥�۲�ѯ","37"));
	private DefaultMutableTreeNode dmtn38=
	        new DefaultMutableTreeNode(new MyNode("��ֵ���㵥�۲�ѯ","38"));
	private DefaultMutableTreeNode dmtn41=
			new DefaultMutableTreeNode(new MyNode("������","41"));
	
	//�������ڵ�
	private DefaultTreeModel dtm=new DefaultTreeModel(dmtnRoot);
	//������״�б�ؼ�
	private JTree jt=new JTree(dtm);
	//������߹�������
	private JScrollPane jspz=new JScrollPane(jt);
	//�������
	private JPanel jpy=new JPanel();
	//�����ָ��
	private JSplitPane jsp1=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jspz,jpy);
	//���ݿ�����ã��ڹ��캯���оͽ��е�һ��������
	private SQLManager SQLManager;
	
	/*
	 * /��������ģ������(������佫�ں����ģ��Ŀ�����������һ��ӣ�
	 */
	private ProCrePane ProCrePane;//��Ŀ����
	private InsertDataPane InsertDataPane;//�ճ���ˮ��ҵ
	private InRePan InRePan;//��������
	private InsertPerson InsertPerson;//��Ա����
	private OutputPanel OutputPanel;//�����Ʊ
	private SeRe sere;//�鱨���
	private SeWR sewr;//����ˮ��
	private InHTPane inht ;//�����ͬ
	private SeHT seht ;//�����ͬ
	private SearchItem SearchItem;//��ѯ-��Ŀ
	private ChangePassword ChangePassword;//�޸�����
	private SeXi sexi;//ϴƬ��ѯ
	private InNP innp;//���ʽ��㵥������
	private SeNP senp;//���ʽ��㵥�۲�ѯ
	private InWP inwp;//���ʽ��㵥������
	private SeWP sewp;//���ʽ��㵥�۲�ѯ
	/*private insert_daily insert_daily;
	private insert_worker insert_worker;
	private insert_report insert_report;


	private modify_item modify_item;
	private modify_daily modify_daily;
	private modify_worker modify_worker;
	private modify_report modify_report;
	
	private output_personal output_personal;
	private output_daily output_daily;
	private output_item output_item;
	private output_wages output_wages;
	*/

	//������Ƭ��������
	CardLayout cl;
	//���캯��
	public MainFrame()
	{
		//��ʼ�����ݿ������
		SQLManager=new SQLManager();
		//��ʼ����״�б�ؼ�
		this.initialTree();
		//����������ģ�����
		this.initialPanel();
		//Ϊ�ڵ�ע�������
		this.addListener();
		//��ʼ�����
		this.initialJpy();
		//��ʼ��������
		this.initialFrame();
	}
	public MainFrame(SQLManager SQLManager,String group,String name,String password)
	{
		this.group=group;
		this.name=name;
		this.password=password;
		//��ʼ�����ݿ������
		this.SQLManager=SQLManager;
		//��ʼ����״�б�ؼ�
		this.initialTree();
		//����������ģ�����
		this.initialPanel();
		//Ϊ�ڵ�ע�������
		this.addListener();
		//��ʼ�����
		this.initialJpy();
		//��ʼ��������
		this.initialFrame();
	}
	
	public void initialPanel()
	{//��ʼ��������ģ��
	     //��ʼ�������ں����ģ��Ŀ�����������һ���
	    /* 
	     *welcome=new Welcome("�ɼ�����ϵͳ")
	     */
		
		setIconImage(Toolkit.getDefaultToolkit().createImage("Testing.png"));
		ProCrePane=new ProCrePane(SQLManager,group);
		InsertDataPane=new InsertDataPane(SQLManager,group);
		InRePan = new InRePan(SQLManager,group);
		OutputPanel=new OutputPanel(group);
		InsertPerson=new InsertPerson(SQLManager,group);
		sere = new SeRe(SQLManager,group);
		sewr = new SeWR(SQLManager,group);
		inht = new InHTPane(SQLManager,group);
		seht = new SeHT(SQLManager,group);
		sexi = new SeXi(SQLManager,group);
		SearchItem=new SearchItem(SQLManager,group);
		ChangePassword=new ChangePassword(SQLManager,group,name,password);
		innp = new InNP(SQLManager,group);
		senp = new SeNP(SQLManager,group);
		inwp = new InWP(SQLManager,group);
		sewp = new SeWP(SQLManager,group);
	}
	//��ʼ����״�б�ؼ��ķ���
	public void initialTree()
	{
		dmtnRoot.add(dmtn1);
		dmtnRoot.add(dmtn2);
		dmtnRoot.add(dmtn3);
		dmtnRoot.add(dmtn4);
		dmtn1.add(dmtn11);
		dmtn1.add(dmtn13);
		dmtn2.add(dmtn21);
		dmtn2.add(dmtn22);
		dmtn2.add(dmtn23);
		dmtn2.add(dmtn24);
		dmtn2.add(dmtn25);
		dmtn2.add(dmtn26);
		dmtn2.add(dmtn27);
		dmtn3.add(dmtn31);
		dmtn3.add(dmtn32);
		dmtn3.add(dmtn33);
		dmtn3.add(dmtn34);
		dmtn3.add(dmtn35);
		dmtn3.add(dmtn36);
		dmtn3.add(dmtn37);
		dmtn3.add(dmtn38);
		dmtn4.add(dmtn41);
	}
	public void initialJpy()
	{//��������ģ����ӵ������
	    //���������Ϊ��Ƭ����
		jpy.setLayout(new CardLayout());
		cl=(CardLayout)jpy.getLayout();
		
		/*
		 * ������ģ�齫�ں����ģ��Ŀ�����������һ���
		*/
		jpy.add(ProCrePane, "ProCrePane");
		jpy.add(InsertDataPane,"InsertDataPane");
		jpy.add(InRePan,"InRePan");
		jpy.add(OutputPanel,"OutputPanel");
		jpy.add(InsertPerson,"InsertPerson");
		jpy.add(sere,"SearchReport");
		jpy.add(sewr,"SearchWorkload");
		jpy.add(inht,"InsertHT");
		jpy.add(seht,"SearchHT");
		jpy.add(SearchItem, "SearchItem");
		jpy.add(sexi, "SearchXi");
		jpy.add(ChangePassword, "ChangePassword");
		jpy.add(innp, "InsertNeiPrice");
		jpy.add(senp, "SearchNeiPrice");
		jpy.add(inwp, "InsertWaiPrice");
		jpy.add(sewp, "SearchWaiPrice");
	}
	public void initialFrame()
	{   //���ô���ı��⡢��С����ɼ���
		this.add(jsp1);
		jsp1.setDividerLocation(200);
		jsp1.setDividerSize(4);
		//Image image=new ImageIcon("ico.gif").getImage();  
		//this.setIconImage(image);
		this.setTitle("1.0�汾��ҵ�����ݿ�д�����");
		Dimension screenSize = Toolkit.
		             getDefaultToolkit().getScreenSize();
		int centerX=screenSize.width/2;
		int centerY=screenSize.height/2;
		int w=900;//��������
		int h=650;//������߶�
		//���ô����������Ļ����
		this.setBounds(centerX-w/2,centerY-h/2-30,w,h);
		this.setVisible(true);
		//����ȫ��
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//���ùر��˳�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void addListener()
	{
		this.addComponentListener(new ComponentAdapter(){
			@Override public void componentResized(ComponentEvent e){
			    // write you code here
				jsp1.setDividerLocation(0.18);
				
			}});
		
		
		
		//Ϊ��״�б�ؼ�ע������¼�������
		jt.addMouseListener(
	           new MouseAdapter()
	           {
	           	  public void mouseClicked(MouseEvent e)
	           	  { 
	           	      DefaultMutableTreeNode dmtntemp=(DefaultMutableTreeNode)jt.getLastSelectedPathComponent();
					  MyNode mynode=(MyNode)dmtntemp.getUserObject();
					  String id=mynode.getId();
					  //����idֵ��ʾ��ͬ�Ŀ�Ƭ
					  if(id.equals("0"))
					  {//��ӭҳ��
					        //cl.show(jpy,"welcome");
					  }
	           	      else if(id.equals("11"))
	           	      {//�˳�ϵͳ
	           	            int i=JOptionPane.showConfirmDialog(jpy,
	           	                 "��ȷ��Ҫ�˳���ϵͳ��","ѯ��",
	           	                  JOptionPane.YES_NO_OPTION,
	           	                   JOptionPane.QUESTION_MESSAGE);
	           	      		if(i==0)
	           	      		{
	           	      			System.exit(0);
	           	      		}
	           	      }
	           	       else if(id.equals("13")){
	           	    	  cl.show(jpy,"ChangePassword");
	           	      }
	           	      else if(id.equals("22")){
	           	    	  cl.show(jpy,"InsertDataPane");
	           	      }
	           	      else if(id.equals("21")){
	           	    	  cl.show(jpy, "ProCrePane");
	           	      }
	           	      else if(id.equals("23")){
	           	    	  cl.show(jpy, "InsertPerson");
	           	      }
	           	      else if(id.equals("24")){
	           	    	  cl.show(jpy, "InRePan");
	           	      }else if(id.equals("31")){
	           	    	  cl.show(jpy, "SearchItem");
	           	      }else if(id.equals("41")){
	           	    	  cl.show(jpy, "OutputPanel");
	           	      }else if(id.equals("34")){
	           	    	  cl.show(jpy, "SearchReport");
	           	      }else if(id.equals("32")){
	           	    	  cl.show(jpy, "SearchWorkload");
	           	      }else if(id.equals("25")){
	           	    	  cl.show(jpy, "InsertHT");
	           	      }else if(id.equals("35")){
	           	    	  cl.show(jpy, "SearchHT");
	           	      }else if(id.equals("33")){
	           	    	  cl.show(jpy, "InsertPerson");
	           	      }else if(id.equals("36")){
	           	    	  cl.show(jpy, "SearchXi");
	           	      }else if(id.equals("26")){
	           	    	  cl.show(jpy, "InsertNeiPrice");
	           	      }else if(id.equals("37")){
	           	    	  cl.show(jpy, "SearchNeiPrice");
	           	      }else if(id.equals("27")){
	           	    	  cl.show(jpy, "InsertWaiPrice");
	           	      }else if(id.equals("38")){
	           	    	  cl.show(jpy, "SearchWaiPrice");
	           	      }
	              }
	           }
		                       );
		//��չ���ڵ�������������Ϊ1
		jt.setToggleClickCount(1);
	}
	//�Զ���ĳ�ʼ�����ڵ�����ݶ������
	class MyNode
	{
		private String values;
		private String id;
		public MyNode(String values,String id)
		{
			this.values=values;
			this.id=id;
		}
		public String toString()
		{
			return this.values;
		}
		public String getId()
		{
			return this.id;
		}
	}
	public static void main(String args[])	
	{
		new MainFrame();
	}

}
