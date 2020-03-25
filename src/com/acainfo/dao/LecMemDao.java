package com.acainfo.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.acainfo.dto.LecMemDto;

public class LecMemDao extends Dao {
	public boolean insertLecMem(LecMemDto dto) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			con = conn();
			con.setAutoCommit(false);
			String sql = "insert into lec_member values(lm_lmnum_seq.nextval,?,?,sysdate,'N')";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getLeNum());
			pstmt.setInt(2, dto.getNum());
			int n = pstmt.executeUpdate();
			if (n > 0) {
				System.out.println("[ insertLecMem lec_member 성공]");
			}

			String sql2 = "insert into grade values(?,?,null,null,'N',sysdate)";
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, dto.getLeNum());
			pstmt2.setInt(2, dto.getNum());
			int n2 = pstmt2.executeUpdate();
			if (n2 > 0) {
				System.out.println("[ insertLecMem(2) grade 성공]");
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
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return false;
	}

	public boolean deleteLecMem(LecMemDto dto) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			con = conn();
			con.setAutoCommit(false);
			String sql = "update lec_member set del_yn='Y' where lenum=? and num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getLeNum());
			pstmt.setInt(2, dto.getNum());
			int n = pstmt.executeUpdate();
			if (n > 0) {
				System.out.println("[ deleteLecMem lec_member 성공]");
			}

			String sql2 = "update grade set del_yn='Y' where lenum=? and num=?";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, dto.getLeNum());
			pstmt2.setInt(2, dto.getNum());
			int n2 = pstmt.executeUpdate();
			if (n2 > 0) {
				System.out.println("[ deleteLecMem(2) grade 성공]");
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
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return false;
	}

}
