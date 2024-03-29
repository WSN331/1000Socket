package shit.db.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import shit.db.connection.ShitDBConnection;
import shit.db.exception.ShitDBConfigureException;
import shit.db.exception.ShitDBJDBCException;
import shit.db.exception.ShitDBTranslateException;
import shit.db.exception.ShitDBWrongControlException;
import shit.db.query.ShitDBPager;
import shit.db.query.ShitDBQuery;
import shit.db.query.ShitDBQueryBasic;

/**
 * 单纯依靠jdbc本身来实现的session，即不存在java缓存
 * 
 * @author GongTengPangYi
 *
 */
public class ShitDBSessionJDBC extends ShitDBSessionBasic {

	public ShitDBSessionJDBC(ShitDBConnection conn) {
		super(conn);
	}

	public ShitDBSessionJDBC(ShitDBConnection conn, boolean showSql) {
		super(conn, showSql);
	}

	@Override
	public List<? extends Serializable> query(Class<? extends Serializable> clazz, String shitQL,
			Map<String, Serializable> params, ShitDBPager pager) throws ShitDBJDBCException,
					ShitDBWrongControlException, ShitDBConfigureException, ShitDBTranslateException {
		Connection execConn = getExecConn();
		ShitDBQuery query = new ShitDBQueryBasic(execConn, clazz, shitQL, pager, params);
		query.setShowSql(showSql);
		List<Serializable> list = query.query();
		query = null;
		closeConn(execConn);
		return list;
	}

//	@Override
//	public ShitDBQuery query(Class<? extends Serializable> clazz) {
//		return new ShitDBQueryBasic(conn, clazz);
//	}

}
