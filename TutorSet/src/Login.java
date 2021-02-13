import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Tset.JListDemo1;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel_2;
	public Login frame;
	private JPasswordField passwordField;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 373, 299);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel.setBounds(67, 74, 58, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6  \u7801\uFF1A");
		lblNewLabel_1.setBounds(67, 119, 58, 15);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(146, 71, 143, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBounds(146, 116, 143, 21);
		contentPane.add(passwordField);
		
		textField.setText("3001");
		//textField_1.setText(String.valueOf(202001));
		passwordField.setText("123");
		
		
		JButton btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
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
					
					//select * from tb_jk1_user1 where userid=? and password=?---一表查询
					PreparedStatement pstm = con.
						prepareStatement("select * from tb_jk1_manager1 where manager_id=? and manager_password=?");
					// 执行SQL，处理结果
					String sidString=textField.getText();
					int t_id=Integer.valueOf(sidString);
					pstm.setInt(1, Integer.valueOf(sidString));
					pstm.setString(2, passwordField.getText());
					
					rSet=pstm.executeQuery();
					// 关闭连接，释放资源
					
					if(!rSet.next()) {
						JOptionPane.showMessageDialog(null, "用户名或密码错误", "alert", JOptionPane.ERROR_MESSAGE); 
					}else {
						//打开一个新的页面
						JListDemo1 jListDemo1=new JListDemo1(t_id);
						jListDemo1.setVisible(true);
						Login.this.setVisible(false);
						
					}
					
					pstm.close();
					con.close();
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
			}

		});
		btnNewButton.setBounds(67, 188, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u53D6\u6D88");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText("");
				passwordField.setText("");
			}
		});
		btnNewButton_1.setBounds(192, 188, 97, 23);
		contentPane.add(btnNewButton_1);
		
		
		
		
	}
}
