

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 自定义的 MySQL 数据库操作类。操作完毕后使用 close() 关闭数据连接。<br>
 * 注1：需要在“项目\属性”中添加驱动文件<br>
 * 注2：根据实际需要，添加 sql 引用（可以自动添加）<br>
 * 例如：import="java.sql.ResultSet"
 * 
 * @author THS
 */
public class MySQL {
	// 默认数据库信息
	String IP = "49.232.213.160", DB = "xm1";
	String dbUserName = "xm1", dbPassword = "xm1+test";

	/**
	 * 数据库连接成功标识：true 表示连接成功
	 */
	public boolean connectionOK = false;
	/**
	 * 数据库连接
	 */
	public Connection con = null;
	Statement sm = null;
	PreparedStatement prsm = null;

	// 1.注册 MySQL 驱动：使用静态代码块方式，只执行一次
	static {
		final String className = "com.mysql.cj.jdbc.Driver";
		try {
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 使用默认数据库信息
	 */
	public MySQL() {
		con = getConnection();
	}

	/**
	 * 使用指定的数据库信息
	 * 
	 * @param IP         数据库IP地址
	 * @param DB         数据库名
	 * @param dbUserName 数据库登录用户名
	 * @param dbPassword 数据库登录密码
	 */
	public MySQL(String IP, String DB, String dbUserName, String dbPassword) {
		this.IP = IP;
		this.DB = DB;
		this.dbUserName = dbUserName;
		this.dbPassword = dbPassword;
		con = getConnection();
	}

	/**
	 * 创建数据库连接：connectionOK = true 表示连接成功
	 * 
	 * @return 数据库连接
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
	 * 关闭对象，释放数据库连接
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
	 * 使用 Statement 执行一句不需要返回值的 SQL 语句（insert、update、delete 等）
	 * 
	 * @param sqlStr 要执行的 SQL 语句
	 * @return true 表示执行成功
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
	 * 使用 Statement 执行一句有返回值的 SQL 语句（insert、update、delete 等）
	 * 
	 * @param sqlStr 要执行的 SQL 语句
	 * @return 返回SQL语句影响的记录条件，-1 表示执行失败
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
	 * 使用 Statement 执行一句返回值类型为 ResultSet 的 SQL 语句
	 * 
	 * @param sqlStr 要执行的 SQL 语句
	 * @return 返回 ResultSet 记录集，null 表示执行失败
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
	 * 使用 PreparedStatement 执行一句 SQL 语句
	 * <p>
	 * 示例： <br/>
	 * Object[][] obj = { { "ls", 85 }, { "ww", 90 } }; <br/>
	 * String sqlString = "insert into cjb(name,cj) values (?,?)"; <br/>
	 * MySQL sql = new MySQL(); <br/>
	 * sql.executeBatch(sqlString, obj); <br/>
	 * sql.close();
	 * 
	 * @param sqlStr 要执行的 SQL 语句
	 * @param obj    参数，二维数组
	 * @return 一维数组，表示影响的记录条数，返回 null 表示执行失败
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
				// 返回影响的记录条数
				y = prsm.executeBatch();
			} catch (Exception e) {
				y = null;
			}
		}
		return y;
	}

}
