package com.acainfo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.acainfo.dto.DpmDto;

public class DpmDao extends Dao {
	public ArrayList<DpmDto> selectDpmList(int pNum) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<DpmDto> list = null;
		try {
			con = conn();
			if (pNum == -1) {
				String sql = "select denum,name,del_yn from department where del_yn='N'";
				pstmt = con.prepareStatement(sql);
			} else {
				String sql = "select denum,name,del_yn from department where denum in(select denum from dpm_member where num=?) and del_yn='N'";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, pNum);
			}
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
			dbClose(rs, pstmt, con);
		}
	}

	public boolean insertDpm(String pDpmName) {
		PreparedStatement pstmt = null;
		try {
			con = conn();
			String sql = "insert into department values(department_denum_seq.nextval,?,'N')";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pDpmName);
			int n = pstmt.executeUpdate();
			if (n > 0) {
				System.out.println("[ insertDpm 성공 ]");
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbClose(null, pstmt, con);
		}
		return false;
	}

	public boolean deleteDpm(int pDeNum) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			con = conn();
			con.setAutoCommit(false);
			String sql2 = "delete from dpm_member where denum=?";
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, pDeNum);
			int n2 = pstmt2.executeUpdate();
			if (n2 > 0) {
				System.out.println("[ deleteDpm dpm_member 성공 ]");
			}

			String sql = "update department set del_yn='Y' where denum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pDeNum);
			int n = pstmt.executeUpdate();
			if (n > 0) {
				System.out.println("[ deleteDpm(2) department 성공 ]");
				con.commit();
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
			dbClose(pstmt2);
			dbClose(null, pstmt, con);
		}
		return false;
	}

	public boolean deleteDpmNum(int pNum) {
		PreparedStatement pstmt = null;
		try {
			con = conn();
			String sql = "update department set del_yn='Y' where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pNum);
			int n = pstmt.executeUpdate();
			if (n > 0) {
				System.out.println("[ deleteDpmNum 성공 ]");
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbClose(null, pstmt, con);
		}
		return false;
	}
}
