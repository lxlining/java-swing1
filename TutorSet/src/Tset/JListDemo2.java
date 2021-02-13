package Tset;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.ScrollPane;
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

public class JListDemo2 extends JFrame {

	Container contentPane;
	JList jList1, jList2, jList3;
	private JPanel panel;

	int m_id;
	int teacher_id;
	String teacher_name;
	String s_name;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JLabel lblNewLabel;
	private JButton btnNewButton;

	public JListDemo2() {

		contentPane = this.getContentPane();

		String[] fruit = new String[100];

		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(25, 10, 332, 51);
		getContentPane().add(panel);
		panel.setLayout(null);

		btnNewButton_1 = new JButton("\u4FEE\u6539\u4FE1\u606F");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String string = "20200101    aaa    2020第一学期   1";
				// string=jList3.getSelectedValue().toString();
				teacher_id = Integer.valueOf(string.substring(0, 8));
				teacher_name = string.substring(12).substring(0, 3).trim();
				s_name = string.substring(27).trim();

				System.out.println(teacher_name);
				System.out.println(s_name);
				System.out.println(teacher_id);
			}
		});
		btnNewButton_1.setBounds(227, 8, 95, 27);
		panel.add(btnNewButton_1);

		btnNewButton_2 = new JButton("\u5220\u9664\u4FE1\u606F");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnNewButton_2.setBounds(0, 6, 95, 31);
		panel.add(btnNewButton_2);

		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setBounds(30, 80, 326, 265);
		getContentPane().add(jScrollPane);

		try {
			// 注册驱动
			String className = "com.mysql.cj.jdbc.Driver";
			Class.forName(className);

			// 与数据库建立连接
			String dbUserName = "xm1";
			String dbPassword = "xm1+test";
			String url = "jdbc:mysql://49.232.213.160:3306/xm1?serverTimezone=Asia/Shanghai";
			Connection con = DriverManager.getConnection(url, dbUserName, dbPassword);
			ResultSet rSet;
			// 创建SQL语句
			PreparedStatement pstm = con.prepareStatement(
					"select * from z_testu t join z_teacher_adviser ta on t.teacher_id=ta.teacher_id join tb_semester ts on ts.semester_id=ta.semester_id and guide_adviser_affirm=?");
			// 执行SQL，处理结果
			pstm.setInt(1, 0);
			rSet = pstm.executeQuery();
			// 关闭连接，释放资源
			int i = 1;

			fruit[0] = "姓名" + "    " + "工号" + "    " + "学期";
			while (rSet.next()) {
				fruit[i] = rSet.getString("teacher_name") + "    " + rSet.getString("teacher_id") + "    "
						+ rSet.getString("semester_name");
				i++;
				// lblNewLabel_2.setText(rSet.getString("uname"));
			}

			pstm.close();
			con.close();
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}

		jList3 = new JList(fruit);
		jList3.setBounds(30, 78, 324, 265);
		// getContentPane().add(jList3);
		jList3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList3.setVisibleRowCount(3);

		jScrollPane.setViewportView(jList3);

		btnNewButton = new JButton("\u8FD4\u56DE\u9996\u9875");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		btnNewButton.setBounds(262, 355, 95, 31);
		getContentPane().add(btnNewButton);

		this.setTitle("JListDemo2");
		this.setSize(398, 440);
		// pack();
		this.setLocation(400, 300);
		this.setVisible(true);
	}

	public int s_id;

	public JListDemo2(int id) {

		m_id = id;// 教学秘书id
		contentPane = this.getContentPane();

		String[] fruit = new String[100];
		try {
			// 注册驱动
			String className = "com.mysql.cj.jdbc.Driver";
			Class.forName(className);

			// 与数据库建立连接
			String dbUserName = "xm1";
			String dbPassword = "xm1+test";
			String url = "jdbc:mysql://49.232.213.160:3306/xm1?serverTimezone=Asia/Shanghai";
			Connection con = DriverManager.getConnection(url, dbUserName, dbPassword);
			ResultSet rSet;
			// 创建SQL语句
			PreparedStatement pstm = con.prepareStatement(
					"select * from tb_jk1_teacher1 t join tb_jk1_instructor1 ta on t.teacher_id=ta.teacher_id join tb_jk1_semester1 ts on ts.semester_id=ta.semester_id");
			// 执行SQL，处理结果

			rSet = pstm.executeQuery();
			// 关闭连接，释放资源

			System.out.print(rSet);
			int i = 1;
			fruit[0] = "工号" + "    " + "姓名" + "        " + "学期" + "        " + "能否修改(0可以)";
			while (rSet.next()) {
				if (rSet.getString("teacher_name").length() == 2) {
					fruit[i] = rSet.getString("teacher_id") + "    " + rSet.getString("teacher_name") + "     "
							+ rSet.getString("semester_name") + "  " + rSet.getString("inst_confirm");
					System.out.println(fruit[i]);
				} else {
					fruit[i] = rSet.getString("teacher_id") + "    " + rSet.getString("teacher_name") + "    "
							+ rSet.getString("semester_name") + "  " + rSet.getString("inst_confirm");
					System.out.println(fruit[i]);
				}

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

		btnNewButton_1 = new JButton("\u4FEE\u6539\u4FE1\u606F");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String string;
				int sure;
				if (jList3.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "必须选中某位老师", "alert", JOptionPane.ERROR_MESSAGE);
					JListDemo2 jd1 = new JListDemo2(m_id);
					jd1.setVisible(true);
					JListDemo2.this.setVisible(false);
				}
				string = jList3.getSelectedValue().toString();
				teacher_id = Integer.valueOf(string.substring(0, 5).trim());
				teacher_name = string.substring(9).substring(0, 3).trim();
				s_name = string.substring(16).substring(0, 9).trim();
				sure = Integer.valueOf(string.substring(27).substring(0).trim());

				System.out.println(0);
				System.out.println(string);
				System.out.println(111);
				System.out.println(s_name);
				System.out.print(sure);

				if (sure > 0) {
					JOptionPane.showMessageDialog(null, "不可操作", "alert", JOptionPane.ERROR_MESSAGE);
					JListDemo2 j2 = new JListDemo2(3001);
					j2.setVisible(true);
					JListDemo2.this.dispose();
				} else {
					Update u = new Update(teacher_id, teacher_name, s_name);
					u.setVisible(true);

					JListDemo2.this.dispose();

				}

			}
		});
		btnNewButton_1.setBounds(227, 8, 95, 27);
		panel.add(btnNewButton_1);

		btnNewButton_2 = new JButton("\u5220\u9664\u4FE1\u606F");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String string;
				int sure;
				if (jList3.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "必须选中某位老师", "alert", JOptionPane.ERROR_MESSAGE);
					JListDemo2 jd1 = new JListDemo2(m_id);
					jd1.setVisible(true);
					JListDemo2.this.setVisible(false);
				}
				string = jList3.getSelectedValue().toString();
				teacher_id = Integer.valueOf(string.substring(0, 6).trim());
				teacher_name = string.substring(9).substring(0, 3).trim();
				s_name = string.substring(16).substring(0, 10).trim();
				sure = Integer.valueOf(string.substring(27).substring(0).trim());

				
				  if(sure>0) {
					  JOptionPane.showMessageDialog(null, "不可操作", "alert",JOptionPane.ERROR_MESSAGE);
					  JListDemo2 j2=new JListDemo2(3001);
				      j2.setVisible(true); 
				      JListDemo2.this.setVisible(false);
				  }else {
				 
				try {
					// 注册驱动
					String className = "com.mysql.cj.jdbc.Driver";
					Class.forName(className);

					// 与数据库建立连接
					String dbUserName = "xm1";
					String dbPassword = "xm1+test";
					String url = "jdbc:mysql://49.232.213.160:3306/xm1?serverTimezone=Asia/Shanghai";
					Connection con = DriverManager.getConnection(url, dbUserName, dbPassword);
					ResultSet rSet;
					PreparedStatement pstm1 = con
							.prepareStatement("select * from tb_jk1_semester1 where semester_name=?");
					// 执行SQL，处理结果
					pstm1.setString(1, s_name);

					rSet = pstm1.executeQuery();

					if (rSet.next()) {
						s_id = Integer.valueOf(rSet.getString("semester_id"));
					}
					// 关闭连接，释放资源
					pstm1.close();

					PreparedStatement pstm = con
							.prepareStatement("delete from tb_jk1_instructor1 where teacher_id=? and semester_id=?");
					// 执行SQL，处理结果
					pstm.setInt(1, teacher_id);
					pstm.setInt(2, s_id);
					pstm.executeUpdate();
					// 关闭连接，释放资源
					pstm.close();

					JOptionPane.showMessageDialog(null, "删除成功！", "info", JOptionPane.ERROR_MESSAGE);

					con.close();
				
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
				int s = 202001;
				JListDemo2 jd1 = new JListDemo2(s);
				jd1.setVisible(true);
				JListDemo2.this.dispose();
			}
		 }
		});
		btnNewButton_2.setBounds(0, 6, 95, 31);
		panel.add(btnNewButton_2);

		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setBounds(30, 80, 326, 265);
		getContentPane().add(jScrollPane);

		jList3 = new JList(fruit);
		jList3.setBounds(30, 78, 324, 265);
		// getContentPane().add(jList3);
		jList3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList3.setVisibleRowCount(3);

		jScrollPane.setViewportView(jList3);

		btnNewButton = new JButton("\u8FD4\u56DE\u9996\u9875");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JListDemo1 jd1 = new JListDemo1(m_id);
				jd1.setVisible(true);
				JListDemo2.this.setVisible(false);
			}
		});
		btnNewButton.setBounds(262, 355, 95, 31);
		getContentPane().add(btnNewButton);

		this.setTitle("JListDemo");
		this.setSize(467, 444);
		// pack();
		this.setLocation(400, 300);
		this.setVisible(true);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JListDemo2 test = new JListDemo2();
					test.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}