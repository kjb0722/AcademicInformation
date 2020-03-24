package com.acainfo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.acainfo.controller.DbUtil;
import com.acainfo.dto.DpmDto;

public class DpmDao extends Dao {
	public ArrayList<DpmDto> selectDpmList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<DpmDto> list = null;
		try {
			con = DbUtil.conn();
			String sql = "select denum,name,del_yn from department where del_yn='N'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			list = new ArrayList<DpmDto>();
			while (rs.next()) {
				int denum = rs.getInt("denum");
				String name = rs.getString("name");
				String del_yn = rs.getString("del_yn");
				list.add(new DpmDto(denum, name, del_yn));
			}

			System.out.println("[ selectDpmList 성공 ]");
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			DbUtil.dbClose(rs, pstmt, con);
		}
	}

	public boolean insertDpm(String dpmName) {
		PreparedStatement pstmt = null;
		try {
			con = conn();
			String sql = "insert into department values(department_denum_seq.nextval,?,'N')";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dpmName);
			int n = pstmt.executeUpdate();
			if (n > 0) {
				con.commit();
				System.out.println("[ insertDpm 성공 ]");
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}
		} finally {
			dbClose(null, pstmt, con);
		}
		return false;
	}

	public boolean deleteDpm(int deNum) {
		PreparedStatement pstmt = null;
		try {
			con = conn();
			String sql = "update department set del_yn='Y' where denum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, deNum);
			int n = pstmt.executeUpdate();
			if (n > 0) {
				System.out.println("[ deleteDpm 성공 ]");
				con.commit();
				return true;
			}
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}
			System.out.println(e.getMessage());
		} finally {
			dbClose(null, pstmt, con);
		}
		return false;
	}

}
