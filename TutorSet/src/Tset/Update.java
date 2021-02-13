package Tset;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JToggleButton;
import javax.swing.JTextArea;

public class Update extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private ButtonGroup group;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private TextArea textArea;

	/**
	 * Launch the application.
	 */
	public int t_id;
	public String t_nameString;
	private JTextField textField_1;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update frame = new Update();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public Update(int id,String name,String s_name) {
		t_id=id;//教师id
		t_nameString=name;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(115, 27, 150, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField.setText(String.valueOf(t_id));
		
		
		
		 String[] fruit= new String[100]; 
	      try {
				// 注册驱动
				String className = "com.mysql.cj.jdbc.Driver";
				Class.forName(className);

				// 与数据库建立连接
				String dbUserName = "xm1";
				String dbPassword = "xm1+test";
				String url = 
					"jdbc:mysql://49.232.213.160:3306/xm1?serverTimezone=Asia/Shanghai";
				Connection con = DriverManager.
					getConnection(url, dbUserName, dbPassword);
				ResultSet rSet;
				// 创建SQL语句
				PreparedStatement pstm = con.
					prepareStatement("select * from tb_jk1_semester1");
				// 执行SQL，处理结果
				
				rSet=pstm.executeQuery();
				// 关闭连接，释放资源
				int i=0;
				
				while (rSet.next()) {
					
						fruit[i]=rSet.getString("semester_id")+"  "+rSet.getString("semester_name");
					i++;
				}
				pstm.close();
				con.close();
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
		
	      JComboBox comboBox = new JComboBox(fruit);
			comboBox.setBounds(115, 94, 150, 23);
			contentPane.add(comboBox);
			//comboBox.setSelectedItem(s_name);
			
			JTextArea textArea = new JTextArea();
			 textArea.setLineWrap(true);
			 textArea.setWrapStyleWord(true);
			 textArea.setBounds(118, 203, 310, 99);
			 contentPane.add(textArea);
			 
			 JPanel panel = new JPanel();
				panel.setBounds(115, 131, 79, 52);
				contentPane.add(panel);
				panel.setLayout(null);
		
			 JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("\u90AE\u7BB1");
				rdbtnNewRadioButton_1.setSelected(true);
				rdbtnNewRadioButton_1.setBounds(6, 23, 49, 23);
				panel.add(rdbtnNewRadioButton_1);
				
				JRadioButton rdbtnNewRadioButton = new JRadioButton("\u77ED\u4FE1");
				rdbtnNewRadioButton.setSelected(true);
				rdbtnNewRadioButton.setBounds(6, 6, 49, 23);
				panel.add(rdbtnNewRadioButton);
				
		 
		JLabel lblNewLabel = new JLabel("\u59D3\u540D");
		lblNewLabel.setBounds(20, 30, 58, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5B66\u671F");
		lblNewLabel_1.setBounds(20, 98, 58, 15);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("\u4FEE\u6539");
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int id1,id2;
				String strsString=comboBox.getSelectedItem().toString();
				//btnNewButton.setText(strsString.substring(0, 1));
				String id1String=strsString.substring(0, 1).trim();
				id1=Integer.valueOf(id1String.trim());
				id2=1;
				
				try {
						// 注册驱动
						String className = "com.mysql.cj.jdbc.Driver";
						Class.forName(className);

						// 与数据库建立连接
						String dbUserName = "xm1";
						String dbPassword = "xm1+test";
						String url = 
							"jdbc:mysql://49.232.213.160:3306/xm1?serverTimezone=Asia/Shanghai";
						Connection con = DriverManager.
							getConnection(url, dbUserName, dbPassword);
						
						ResultSet rSet;
						// 创建SQL语句
						PreparedStatement pstm1 = con.
							prepareStatement("select * from tb_jk1_semester1 where semester_name=?");
						// 执行SQL，处理结果
						pstm1.setString(1, s_name);
						rSet=pstm1.executeQuery();
						// 关闭连接，释放资源
						int i=0;
						
						while (rSet.next()) {
							
								i=Integer.valueOf(rSet.getString("semester_id"));
						}
						pstm1.close();
						
						System.out.print(i);
							PreparedStatement pstm = con.
									prepareStatement("update tb_jk1_instructor1 set semester_id=? where teacher_id=?");
								// 执行SQL，处理结果
								pstm.setInt(1, id1);
								pstm.setInt(2, t_id);
								
								pstm.executeUpdate();
								// 关闭连接，释放资源
								pstm.close();
								
								JOptionPane.showMessageDialog(null, "修改成功！", "info", JOptionPane.ERROR_MESSAGE); 
						
							con.close();
							
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
					}
				
				int s=202001;
				JListDemo2 jd1=new JListDemo2(s);
				jd1.setVisible(true);
				Update.this.dispose();
			}
		});
		btnNewButton.setBounds(115, 341, 97, 23);
		contentPane.add(btnNewButton);
		
		
		
		JLabel lblNewLabel_1_1 = new JLabel("\u901A\u77E5");
		lblNewLabel_1_1.setBounds(20, 153, 58, 15);
		contentPane.add(lblNewLabel_1_1);
		
		
		
		 ButtonGroup group=new ButtonGroup();
		 //group.setBounds(105,130,130,50);
		 group.add(rdbtnNewRadioButton);
		 group.add(rdbtnNewRadioButton_1);
		 
		 JLabel lblNewLabel_2 = new JLabel("\u5DE5\u53F7");
		 lblNewLabel_2.setBounds(20, 65, 58, 15);
		 contentPane.add(lblNewLabel_2);
		 
		 textField_1 = new JTextField();
		 textField_1.setColumns(10);
		 textField_1.setBounds(115, 63, 150, 21);
		 contentPane.add(textField_1);
		 textField_1.setText(t_nameString);
		 textField.setEditable(false); 
		 textField_1.setEditable(false); 
		
		 JLabel lblNewLabel_1_1_1 = new JLabel("\u901A\u77E5\u5185\u5BB9");
		 lblNewLabel_1_1_1.setBounds(20, 223, 58, 15);
		 contentPane.add(lblNewLabel_1_1_1);
		 
		 JButton btnNewButton_1 = new JButton("\u8FD4\u56DE");
		 btnNewButton_1.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		JListDemo2 jd1=new JListDemo2(3001);
      			jd1.setVisible(true);
      			Update.this.dispose();
		 	}
		 });
		 btnNewButton_1.setBounds(228, 341, 97, 23);
		 contentPane.add(btnNewButton_1);
	}
	
	public Update() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(115, 27, 150, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		
		
		 String[] fruit= new String[100]; 
	      try {
				// 注册驱动
				String className = "com.mysql.cj.jdbc.Driver";
				Class.forName(className);

				// 与数据库建立连接
				String dbUserName = "xm1";
				String dbPassword = "xm1+test";
				String url = 
					"jdbc:mysql://49.232.213.160:3306/xm1?serverTimezone=Asia/Shanghai";
				Connection con = DriverManager.
					getConnection(url, dbUserName, dbPassword);
				ResultSet rSet;
				// 创建SQL语句
				PreparedStatement pstm = con.
					prepareStatement("select * from tb_jk1_semester1");
				// 执行SQL，处理结果
				
				rSet=pstm.executeQuery();
				// 关闭连接，释放资源
				int i=0;
				
				while (rSet.next()) {
					
						fruit[i]=rSet.getString("semester_name");
					i++;
					//lblNewLabel_2.setText(rSet.getString("uname"));
				}
				pstm.close();
				con.close();
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
		
	      JComboBox comboBox = new JComboBox(fruit);
			comboBox.setBounds(115, 94, 150, 23);
			contentPane.add(comboBox);
			
			 JTextArea textArea = new JTextArea();
			 textArea.setLineWrap(true);
			 textArea.setWrapStyleWord(true);
			 textArea.setBounds(118, 203, 310, 99);
			 contentPane.add(textArea);
			 
			 JPanel panel = new JPanel();
				panel.setBounds(115, 131, 79, 52);
				contentPane.add(panel);
				panel.setLayout(null);
		
			 JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("\u90AE\u7BB1");
				rdbtnNewRadioButton_1.setSelected(true);
				rdbtnNewRadioButton_1.setBounds(6, 23, 49, 23);
				panel.add(rdbtnNewRadioButton_1);
				
				JRadioButton rdbtnNewRadioButton = new JRadioButton("\u77ED\u4FE1");
				rdbtnNewRadioButton.setSelected(true);
				rdbtnNewRadioButton.setBounds(6, 6, 49, 23);
				panel.add(rdbtnNewRadioButton);
		
		JLabel lblNewLabel = new JLabel("\u59D3\u540D");
		lblNewLabel.setBounds(20, 30, 58, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5B66\u671F");
		lblNewLabel_1.setBounds(20, 98, 58, 15);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("\u4FEE\u6539");
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int id1,id2;
				String strsString=comboBox.getSelectedItem().toString();
				btnNewButton.setText(strsString.substring(0, 1));
				String id1String=strsString.substring(0, 1);
				id1=Integer.valueOf(id1String);
				id2=1;
			      
			}
		});
		btnNewButton.setBounds(115, 341, 97, 23);
		contentPane.add(btnNewButton);
		
		
		
		JLabel lblNewLabel_1_1 = new JLabel("\u901A\u77E5");
		lblNewLabel_1_1.setBounds(20, 153, 58, 15);
		contentPane.add(lblNewLabel_1_1);
		
		
		 ButtonGroup group=new ButtonGroup();
		 //group.setBounds(105,130,130,50);
		 group.add(rdbtnNewRadioButton);
		 group.add(rdbtnNewRadioButton_1);
		 
		 JLabel lblNewLabel_2 = new JLabel("\u5DE5\u53F7");
		 lblNewLabel_2.setBounds(20, 65, 58, 15);
		 contentPane.add(lblNewLabel_2);
		 
		 textField_1 = new JTextField();
		 textField_1.setColumns(10);
		 textField_1.setBounds(115, 63, 150, 21);
		 contentPane.add(textField_1);
		 
		 textField.setEditable(false); 
		 textField_1.setEditable(false); 
		 
		
		 
		 JLabel lblNewLabel_1_1_1 = new JLabel("\u901A\u77E5\u5185\u5BB9");
		 lblNewLabel_1_1_1.setBounds(20, 223, 58, 15);
		 contentPane.add(lblNewLabel_1_1_1);
		 
		 JButton btnNewButton_1 = new JButton("\u8FD4\u56DE");
		 btnNewButton_1.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 	}
		 });
		 btnNewButton_1.setBounds(228, 341, 97, 23);
		 contentPane.add(btnNewButton_1);
	}
}
