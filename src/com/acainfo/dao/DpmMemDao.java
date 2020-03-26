package com.acainfo.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.acainfo.dto.DpmMemDto;

public class DpmMemDao extends Dao {

	public boolean delInsDpmMem(ArrayList<DpmMemDto> pArrayDto, int pDeNum) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			con = conn();
			con.setAutoCommit(false);
			String sql = "delete from dpm_member where denum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pDeNum);
			int n = pstmt.executeUpdate();
			System.out.println("[ delInsDpmMem " + n + "건 삭제 성공 ]");

			int n2 = 0;
			for (DpmMemDto dto : pArrayDto) {
				int deNum = dto.getDenum();
				int num = dto.getNum();
				String sql2 = "insert into dpm_member values(?,?)";
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setInt(1, deNum);
				pstmt2.setInt(2, num);
				n2 = n2 + pstmt2.executeUpdate();
			}
			con.commit();
			System.out.println("[ delInsDpmMem(2) " + n2 + "건 삽입 성공 ]");
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}
		} finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			dbClose(null, pstmt, con);
			dbClose(pstmt2);
		}
		return false;
	}

	public boolean deleteDpmMemNum(int pNum) {
		PreparedStatement pstmt = null;
		try {
			con = conn();
			String sql = "delete from dpm_member where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pNum);
			int n = pstmt.executeUpdate();
			System.out.println("[ delInsDpmMem " + n + "건 삭제 성공 ]");
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbClose(null, pstmt, con);
		}
		return false;
	}
}
