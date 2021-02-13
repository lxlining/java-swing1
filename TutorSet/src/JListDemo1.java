
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;



import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class JListDemo1 extends JFrame{ 
    
    Container contentPane; 
    JList jList1,jList2,jList3; 
    private JPanel panel;
    private JButton btnNewButton;
    
    int m_id;
    int teacher_admin_id;
    String teacher_name;
    private JButton btnNewButton_1;
    private JButton btnNewButton_2;
    private JLabel lblNewLabel;
     
   public JListDemo1(){ 
        
      contentPane = this.getContentPane();
 
		
      String[] fruit= new String[100]; 
      
      getContentPane().setLayout(null);
      
      panel = new JPanel();
      panel.setBounds(25, 10, 332, 51);
      getContentPane().add(panel);
      panel.setLayout(null);
      
      btnNewButton_1 = new JButton("\u4FEE\u6539\u4FE1\u606F");
      btnNewButton_1.addMouseListener(new MouseAdapter() {
      	@Override
      	public void mouseClicked(MouseEvent e) {
      		
      	}
      });
      btnNewButton_1.setBounds(203, 8, 95, 27);
      panel.add(btnNewButton_1);
      
      btnNewButton_2 = new JButton("\u5220\u9664\u4FE1\u606F");
      btnNewButton_2.addMouseListener(new MouseAdapter() {
      	@Override
      	public void mouseClicked(MouseEvent e) {
      	}
      });
      btnNewButton_2.setBounds(23, 6, 95, 31);
      panel.add(btnNewButton_2);
      JScrollPane jScrollPane = new JScrollPane();
      jScrollPane.setBounds(30, 80, 326, 265);
      getContentPane().add(jScrollPane);
      

      jList3=new JList(fruit);
      jList3.setBounds(30, 78, 324, 265);
      getContentPane().add(jList3);
      jList3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
      jList3.setVisibleRowCount(3); 
      
      btnNewButton = new JButton("\u8BBE\u7F6E\u6559\u5E08");
      btnNewButton.setBounds(128, 371, 101, 31);
      getContentPane().add(btnNewButton);
      btnNewButton.addMouseListener(new MouseAdapter() {
      	@Override
      	public void mouseClicked(MouseEvent e) {
      		
      	}
      });
      
      this.setTitle("JListDemo1"); 
      this.setSize(398,475); 
      this.setLocation(400, 300); 
      this.setVisible(true); 
   } 
 
   public JListDemo1(int id){ 
       
	      m_id=id;//教学秘书id
	      contentPane = this.getContentPane();
	 
			
	      String[] fruit= new String[100]; 
	      try {
				// 注册驱动
				String className = "com.mysql.cj.jdbc.Driver";
				Class.forName(className);

				// 与数据库建立连接
				String dbUserName = "root";
				String dbPassword = "000000";
				String url = 
					"jdbc:mysql://localhost:3306/jk1_1?serverTimezone=UTC";
				Connection con = DriverManager.
					getConnection(url, dbUserName, dbPassword);
				ResultSet rSet;
				// 创建SQL语句
				PreparedStatement pstm = con.
					prepareStatement("select * from tb_jk1_teacher1");
				// 执行SQL，处理结果
				
				rSet=pstm.executeQuery();
				// 关闭连接，释放资源
				int i=1;
				fruit[0]="工号"+"        "+"姓名";
				while (rSet.next()) {
					
						fruit[i]=rSet.getString("teacher_id")+"    "+rSet.getString("teacher_name");
					i++;
				}
				pstm.close();
				con.close();
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
	      getContentPane().setLayout(null);
	      
	      panel = new JPanel();
	      panel.setBounds(25, 10, 332, 51);
	      getContentPane().add(panel);
	      panel.setLayout(null);
	      
	      btnNewButton = new JButton("\u8BBE\u7F6E\u6559\u5E08");
	      btnNewButton.addMouseListener(new MouseAdapter() {
	      	@Override
	      	public void mouseClicked(MouseEvent e) {
	      		String string;
	      		if(jList3.isSelectionEmpty()) {
	      			JOptionPane.showMessageDialog(null, "必须选中某位老师", "alert", JOptionPane.ERROR_MESSAGE); 
	      			JListDemo1 jd1=new JListDemo1(m_id);
	      			jd1.setVisible(true);
	      			JListDemo1.this.setVisible(false);
	      		}
	      		string=jList3.getSelectedValue().toString();
	      		teacher_admin_id=Integer.valueOf(string.substring(0, 6).trim());
	      		teacher_name=string.substring(9).trim();
	      		
	      		
	      		Set s=new Set(teacher_admin_id, teacher_name);
	      		s.setVisible(true);
	      		
	      		JListDemo1.this.dispose();
	      	}
	      });
	      btnNewButton.setBounds(127, 360, 101, 31);
	      //panel.add(btnNewButton);
	      contentPane.add(btnNewButton);
	      
	      btnNewButton_1 = new JButton("\u4FEE\u6539\u4FE1\u606F");
	      btnNewButton_1.addMouseListener(new MouseAdapter() {
	      	@Override
	      	public void mouseClicked(MouseEvent e) {
	      		JListDemo2 s=new JListDemo2(id);
	      		s.setVisible(true);
	      		
	      		JListDemo1.this.dispose();
	      	}
	      });
	      btnNewButton_1.setBounds(203, 8, 95, 27);
	      panel.add(btnNewButton_1);
	      
	      btnNewButton_2 = new JButton("\u5220\u9664\u4FE1\u606F");
	      btnNewButton_2.addMouseListener(new MouseAdapter() {
	      	@Override
	      	public void mouseClicked(MouseEvent e) {
	      		JListDemo2 s=new JListDemo2(id);
	      		s.setVisible(true);
	      		
	      		JListDemo1.this.setVisible(false);
	      		JListDemo1.this.dispose();
	      	}
	      });
	      btnNewButton_2.setBounds(23, 6, 95, 31);
	      panel.add(btnNewButton_2);
	      
	      JScrollPane jScrollPane = new JScrollPane();
	      jScrollPane.setBounds(30, 80, 326, 265);
	      getContentPane().add(jScrollPane);
	      
	      jList3=new JList(fruit);
	      jList3.setBounds(30, 78, 324, 265);
	      jScrollPane.setViewportView(jList3);
	      jList3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
	      jList3.setVisibleRowCount(3); 
	     
	      this.setTitle("JListDemo"); 
	      this.setSize(398,475); 
	      this.setLocation(400, 300); 
	      this.setVisible(true); 
	   } 
   
 
   public static void main(String[] args){ 
      
      EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JListDemo1 test=new JListDemo1(); 
					test.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
} 