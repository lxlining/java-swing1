

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * �Զ���� MySQL ���ݿ�����ࡣ������Ϻ�ʹ�� close() �ر��������ӡ�<br>
 * ע1����Ҫ�ڡ���Ŀ\���ԡ�����������ļ�<br>
 * ע2������ʵ����Ҫ����� sql ���ã������Զ���ӣ�<br>
 * ���磺import="java.sql.ResultSet"
 * 
 * @author THS
 */
public class MySQL {
	// Ĭ�����ݿ���Ϣ
	String IP = "49.232.213.160", DB = "xm1";
	String dbUserName = "xm1", dbPassword = "xm1+test";

	/**
	 * ���ݿ����ӳɹ���ʶ��true ��ʾ���ӳɹ�
	 */
	public boolean connectionOK = false;
	/**
	 * ���ݿ�����
	 */
	public Connection con = null;
	Statement sm = null;
	PreparedStatement prsm = null;

	// 1.ע�� MySQL ������ʹ�þ�̬����鷽ʽ��ִֻ��һ��
	static {
		final String className = "com.mysql.cj.jdbc.Driver";
		try {
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ʹ��Ĭ�����ݿ���Ϣ
	 */
	public MySQL() {
		con = getConnection();
	}

	/**
	 * ʹ��ָ�������ݿ���Ϣ
	 * 
	 * @param IP         ���ݿ�IP��ַ
	 * @param DB         ���ݿ���
	 * @param dbUserName ���ݿ��¼�û���
	 * @param dbPassword ���ݿ��¼����
	 */
	public MySQL(String IP, String DB, String dbUserName, String dbPassword) {
		this.IP = IP;
		this.DB = DB;
		this.dbUserName = dbUserName;
		this.dbPassword = dbPassword;
		con = getConnection();
	}

	/**
	 * �������ݿ����ӣ�connectionOK = true ��ʾ���ӳɹ�
	 * 
	 * @return ���ݿ�����
	 */
	Connection getConnection() {
		String url = "jdbc:mysql://" + IP + ":3306/" 
			+ DB + "?serverTimezone=Asia/Shanghai";
		Connection reCon = null;
		try {
			reCon = DriverManager.getConnection(url, dbUserName, dbPassword);
			if (reCon != null) {
				connectionOK = true;
				sm = reCon.createStatement();
			} else {
				connectionOK = false;
			}
		} catch (SQLException e) {
			connectionOK = false;
			reCon = null;
			sm = null;
			e.printStackTrace();
		}
		return reCon;
	}

	/**
	 * �رն����ͷ����ݿ�����
	 */
	public void close() {
		try {
			if (sm != null) {
				sm.close();
				sm = null;
			}
			if (prsm != null) {
				prsm.close();
				prsm = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ʹ�� Statement ִ��һ�䲻��Ҫ����ֵ�� SQL ��䣨insert��update��delete �ȣ�
	 * 
	 * @param sqlStr Ҫִ�е� SQL ���
	 * @return true ��ʾִ�гɹ�
	 */
	public boolean execute(String sqlStr) {
		if (connectionOK) {
			try {
				sm.execute(sqlStr);
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	/**
	 * ʹ�� Statement ִ��һ���з���ֵ�� SQL ��䣨insert��update��delete �ȣ�
	 * 
	 * @param sqlStr Ҫִ�е� SQL ���
	 * @return ����SQL���Ӱ��ļ�¼������-1 ��ʾִ��ʧ��
	 */
	public int executeUpdate(String sqlStr) {
		if (connectionOK) {
			try {
				return sm.executeUpdate(sqlStr);
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		}
		return -1;
	}

	/**
	 * ʹ�� Statement ִ��һ�䷵��ֵ����Ϊ ResultSet �� SQL ���
	 * 
	 * @param sqlStr Ҫִ�е� SQL ���
	 * @return ���� ResultSet ��¼����null ��ʾִ��ʧ��
	 */
	public ResultSet executeQuery(String sqlStr) {
		if (connectionOK) {
			try {
				return sm.executeQuery(sqlStr);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	/**
	 * ʹ�� PreparedStatement ִ��һ�� SQL ���
	 * <p>
	 * ʾ���� <br/>
	 * Object[][] obj = { { "ls", 85 }, { "ww", 90 } }; <br/>
	 * String sqlString = "insert into cjb(name,cj) values (?,?)"; <br/>
	 * MySQL sql = new MySQL(); <br/>
	 * sql.executeBatch(sqlString, obj); <br/>
	 * sql.close();
	 * 
	 * @param sqlStr Ҫִ�е� SQL ���
	 * @param obj    ��������ά����
	 * @return һά���飬��ʾӰ��ļ�¼���������� null ��ʾִ��ʧ��
	 */
	public int[] executeBatch(String sqlStr, Object[][] obj) {
		int[] y = null;
		if (connectionOK) {
			try {
				prsm = con.prepareStatement(sqlStr);
				prsm.clearBatch();
				for (int i = 0; i < obj.length; i++) {
					for (int j = 0; j < obj[i].length; j++) {
						if (obj[i][j] instanceof Integer) {
							prsm.setInt(j + 1, (int) obj[i][j]);
						} else if (obj[i][j] instanceof Long) {
							prsm.setLong(j + 1, (long) obj[i][j]);
						} else if (obj[i][j] instanceof Short) {
							prsm.setShort(j + 1, (short) obj[i][j]);
						} else if (obj[i][j] instanceof Boolean) {
							prsm.setBoolean(j + 1, (boolean) obj[i][j]);
						} else if (obj[i][j] instanceof Byte) {
							prsm.setByte(j + 1, (byte) obj[i][j]);
						} else if (obj[i][j] instanceof Double) {
							prsm.setDouble(j + 1, (double) obj[i][j]);
						} else if (obj[i][j] instanceof Float) {
							prsm.setFloat(j + 1, (float) obj[i][j]);
						} else if (obj[i][j] instanceof String) {
							prsm.setString(j + 1, (String) obj[i][j]);
						} else {
							System.out.println("unknown type");
						}
					}
					prsm.addBatch();
				}
				// ����Ӱ��ļ�¼����
				y = prsm.executeBatch();
			} catch (Exception e) {
				y = null;
			}
		}
		return y;
	}

}
