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

public class Set extends JFrame {

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
					Set frame = new Set();
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
	
	public Set(int id,String name) {
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
		
		textField.setText(name);
		
		
		
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
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int id1,id2;
				String strsString=comboBox.getSelectedItem().toString();
				btnNewButton.setText(strsString.substring(0, 1).trim());
				String id1String=strsString.substring(0, 1).trim();
				id1=Integer.valueOf(id1String);
				id2=Integer.valueOf(textField_1.getText().trim());
				
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
						
						if(rdbtnNewRadioButton.isSelected()) {
							
							PreparedStatement pstm = con.
									prepareStatement("insert into tb_jk1_instructor1(semester_id,teacher_id,message,content) values( ?,?,?,?)");
								// 执行SQL，处理结果
								pstm.setInt(1, id1);
								pstm.setInt(2, id2);
								pstm.setInt(3, 1);
								pstm.setString(4, textArea.getText());
								
								pstm.executeUpdate();
								// 关闭连接，释放资源
								pstm.close();
								
								
								JOptionPane.showMessageDialog(null, "添加成功！", "info", JOptionPane.ERROR_MESSAGE); 
						}else if(rdbtnNewRadioButton_1.isSelected()){
							PreparedStatement pstm = con.
									prepareStatement("insert into tb_jk1_instructor1(semester_id,teacher_id,email,content) values( ?,?,?,?)");
								// 执行SQL，处理结果
								pstm.setInt(1, id1);
								pstm.setInt(2, id2);
								pstm.setInt(3, 1);
								pstm.setString(4, textArea.getText());
								
								pstm.executeUpdate();
								// 关闭连接，释放资源
								pstm.close();
								JOptionPane.showMessageDialog(null, "添加成功！", "info", JOptionPane.ERROR_MESSAGE); 
						}else {
							JOptionPane.showMessageDialog(null, "error！", "error", JOptionPane.ERROR_MESSAGE); 
						}
							
							con.close();
							
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
					}
				
				int s=3001;
				JListDemo1 jd1=new JListDemo1(s);
				jd1.setVisible(true);
				Set.this.dispose();
			}
		});
		btnNewButton.setBounds(115, 341, 97, 23);
		contentPane.add(btnNewButton);
		
		
		
		JLabel lblNewLabel_1_1 = new JLabel("\u901A\u77E5");
		lblNewLabel_1_1.setBounds(20, 153, 58, 15);
		contentPane.add(lblNewLabel_1_1);
		
		 ButtonGroup group=new ButtonGroup();
		 group.add(rdbtnNewRadioButton);
		 group.add(rdbtnNewRadioButton_1);
		 
		 JLabel lblNewLabel_2 = new JLabel("\u5DE5\u53F7");
		 lblNewLabel_2.setBounds(20, 65, 58, 15);
		 contentPane.add(lblNewLabel_2);
		 
		 textField_1 = new JTextField();
		 textField_1.setColumns(10);
		 textField_1.setBounds(115, 63, 150, 21);
		 contentPane.add(textField_1);
		 textField_1.setText(String.valueOf(id));
		 textField.setEditable(false); 
		 textField_1.setEditable(false); 
		 
		 JLabel lblNewLabel_1_1_1 = new JLabel("\u901A\u77E5\u5185\u5BB9");
		 lblNewLabel_1_1_1.setBounds(20, 223, 58, 15);
		 contentPane.add(lblNewLabel_1_1_1);
		 
		 JButton btnNewButton_1 = new JButton("\u8FD4\u56DE");
		 btnNewButton_1.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		JListDemo1 jd1=new JListDemo1(1);
      			jd1.setVisible(true);
      			Set.this.dispose();
		 	}
		 });
		 btnNewButton_1.setBounds(246, 341, 97, 23);
		 contentPane.add(btnNewButton_1);
	}
	
	public Set() {
		
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
					prepareStatement("select * from tb_semester");
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
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		
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
		 btnNewButton_1.setBounds(246, 341, 97, 23);
		 contentPane.add(btnNewButton_1);
	}
}
