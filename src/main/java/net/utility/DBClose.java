package net.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class DBClose {	

	public DBClose() {
		System.out.println("---DBClose() °´Ã¼ »ý¼ºµÊ.....");
	}

	public void close(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
		}
	}// end

	public void close(Connection con, PreparedStatement pstmt) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
		}

		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
		}
	}// end

	public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
		}

		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
		}

		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
		}
	}// end

}// class end
