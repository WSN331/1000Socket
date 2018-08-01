package shit.db.execute;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import shit.db.exception.ShitDBExecuteException;

public class ShitDBExecuteSQLQuery extends ShitDBExecuteSQL<ResultSet> {


	@Override
	public ResultSet execute(Connection execConn, String sql, List<Serializable> params) throws ShitDBExecuteException {
		PreparedStatement stmt = prepareStatement(execConn, sql, params);
		try {
			return stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ShitDBExecuteException(e);
		}
	}

}
