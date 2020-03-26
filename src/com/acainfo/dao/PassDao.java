package com.acainfo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.acainfo.dto.PassDto;

public class PassDao extends Dao {
	public boolean selectLogin(PassDto pDto) {
		con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = conn();
			String sql = "select id from pass where id=? and pass=? and del_yn='N'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pDto.getId());
			pstmt.setString(2, pDto.getPw());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("[ selectLogin 성공 ]");
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
