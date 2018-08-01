package shit.db.execute;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import shit.db.connection.ShitDBConnection;
import shit.db.exception.ShitDBExecuteException;

public class ShitDBExecuteSQLNotQuery extends ShitDBExecuteSQL<Void> {

	@Override
	public Void execute(Connection execConn, String sql, List<Serializable> params) throws ShitDBExecuteException {
		PreparedStatement stmt = prepareStatement(execConn, sql, params);
		try {
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ShitDBExecuteException(e);
		}
		return null;
	}

}
