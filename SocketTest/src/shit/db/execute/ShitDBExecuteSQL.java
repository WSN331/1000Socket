package shit.db.execute;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import shit.db.connection.ShitDBConnection;
import shit.db.exception.ShitDBConnectException;
import shit.db.exception.ShitDBExecuteException;

/**
 * 基本数据库操作执行体
 * 
 * @author GongTengPangYi
 *
 * @param <T>
 *            操作返回
 */
public abstract class ShitDBExecuteSQL<T> {

	/**
	 * 执行带占位符数据库操作
	 * 
	 * @param sql
	 *            占位符
	 * @param params
	 *            占位符参数
	 * @return 处理的返回结果
	 * @throws ShitDBExecuteException
	 *             执行错误
	 */
	public abstract T execute(Connection execConn, String sql, List<Serializable> params) throws ShitDBExecuteException;

	/**
	 * 生成PreparedStatement
	 * 
	 * @param sql
	 *            SQL
	 * @param params
	 *            占位符数值
	 * @return PreparedStatement
	 * @throws ShitDBExecuteException
	 *             执行出错
	 */
	protected PreparedStatement prepareStatement(Connection executeConn, String sql, List<Serializable> params) throws ShitDBExecuteException {
		try {
			PreparedStatement stmt = null;
			if (executeConn == null) {
				throw new ShitDBExecuteException("数据源错误");
			}
			stmt = executeConn.prepareStatement(sql);
			for (int i = 0; i < params.size(); i++) {
				// 遍历绑定占位符参数
				stmt.setObject(i + 1, params.get(i));
			}
			return stmt;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ShitDBExecuteException("参数绑定中发生错误");
		}

	}
}
