package com.acainfo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.acainfo.dto.PassDto;

public class PassDao extends Dao {
	public boolean login(PassDto dto) {
		con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = conn();
			String sql = "select id from pass where id=? and pass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbClose(rs, pstmt, con);
		}
		return false;
	}
}
