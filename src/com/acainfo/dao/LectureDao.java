package com.acainfo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.acainfo.dto.LectureDto;

public class LectureDao extends Dao {

	public ArrayList<LectureDto> selectLecYN(int pNum) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = conn();
			String sql = "select l.lenum,(select name from member where l.num=num) 교수,l.name,nvl((select 'Y' from lec_member where l.lenum=lenum and num=? and del_yn='N'),'N') 수강여부 from lecture l where del_yn = 'N'";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pNum);
			rs = pstmt.executeQuery();

			ArrayList<LectureDto> list = new ArrayList<LectureDto>();
			while (rs.next()) {
				int leNum = rs.getInt("lenum");
				String lec_mem = rs.getString("교수");
				String name = rs.getString("name");
				String lec_yn = rs.getString("수강여부");
				LectureDto dto = new LectureDto(leNum, -1, name, "N");
				dto.setLec_mem(lec_mem);
				dto.setLec_yn(lec_yn);
				list.add(dto);
			}
			System.out.println("[ selectLecAll 성공 ]");
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbClose(rs, pstmt, con);
		}
		return null;
	}

	public boolean insertLec(LectureDto pDto) {
		PreparedStatement pstmt = null;
		try {
			con = conn();
			String sql = "insert into lecture values(lec_lenum_seq.nextval,?,?,'N')";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pDto.getNum());
			pstmt.setString(2, pDto.getName());
			int n = pstmt.executeUpdate();
			if (n > 0) {
				System.out.println("[ insertLec 성공 ]");
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbClose(null, pstmt, con);
		}
		return false;
	}

	public ArrayList<LectureDto> selectLec(int pNum) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = conn();
			String sql = "select l.lenum,l.name,l.num,(select name from member where num=l.num) 교수 from lecture l where num = ? and del_yn='N'";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pNum);
			rs = pstmt.executeQuery();

			ArrayList<LectureDto> list = new ArrayList<LectureDto>();
			while (rs.next()) {
				int leNum = rs.getInt("lenum");
				String name = rs.getString("name");
				int num = rs.getInt("num");
				String lec_mem = rs.getString("교수");
				LectureDto dto = new LectureDto(leNum, num, name, "N");
				dto.setLec_mem(lec_mem);
				list.add(dto);
			}
			System.out.println("[ selectLec 성공 ]");
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbClose(rs, pstmt, con);
		}
		return null;
	}

	public boolean deleteLec(int pLenum) {
		PreparedStatement pstmt = null;
		try {
			con = conn();
			String sql = "update lecture set del_yn='Y' where lenum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pLenum);
			int n = pstmt.executeUpdate();
			if (n > 0) {
				System.out.println("[ deleteLec 성공 ]");
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbClose(null, pstmt, con);
		}
		return false;
	}

	public boolean deleteLecNum(int pNum) {
		PreparedStatement pstmt = null;
		try {
			con = conn();
			String sql = "update lecture set del_yn='Y' where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pNum);
			int n = pstmt.executeUpdate();
			if (n > 0) {
				System.out.println("[ deleteLecNum 성공 ]");
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
