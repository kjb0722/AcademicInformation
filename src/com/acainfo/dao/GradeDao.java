package com.acainfo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.acainfo.dto.GradeDto;

public class GradeDao extends Dao {
	public ArrayList<GradeDto> selectMemGrade(int pNum) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = conn();
			String sql = "select grnum,lenum,num,score,rank,del_yn,grdate from grade where num=? and del_yn='N'";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pNum);
			rs = pstmt.executeQuery();

			ArrayList<GradeDto> list = new ArrayList<GradeDto>();
			while (rs.next()) {
				int grNum = rs.getInt("grnum");
				int leNum = rs.getInt("lenum");
				int num = rs.getInt("num");
				int score = rs.getInt("score");
				String rank = rs.getString("rank");
				String del_yn = rs.getString("del_yn");
				Date grdate = rs.getDate("grdate");
				list.add(new GradeDto(grNum, leNum, num, score, rank, del_yn, grdate));
			}
			System.out.println("[ selectMemGrade 성공 ]");
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbClose(rs, pstmt, con);
		}
		return null;
	}

	public ArrayList<GradeDto> selectMemGradeMgt(int pNum) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = conn();
			String sql = "select grnum,lenum,num,score,rank,del_yn,grdate from grade where lenum in(select lenum from lecture where num=?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pNum);
			rs = pstmt.executeQuery();

			ArrayList<GradeDto> list = new ArrayList<GradeDto>();
			while (rs.next()) {
				int grNum = rs.getInt("grNum");
				int leNum = rs.getInt("lenum");
				int num = rs.getInt("num");
				int score = rs.getInt("score");
				String rank = rs.getString("rank");
				String del_yn = rs.getString("del_yn");
				Date grdate = rs.getDate("grdate");
				list.add(new GradeDto(grNum, leNum, num, score, rank, del_yn, grdate));
			}
			System.out.println("[ selectMemGradeMgt 성공 ]");
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbClose(rs, pstmt, con);
		}
		return null;
	}

	public boolean updateGrade(GradeDto pDto) {
		PreparedStatement pstmt = null;
		try {
			con = conn();
			String sql = "update grade set score=?,rank=?,grdate=sysdate where grnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pDto.getScore());
			pstmt.setString(2, pDto.getRank());
			pstmt.setInt(3, pDto.getGrNum());
			int n = pstmt.executeUpdate();
			if (n > 0) {
				System.out.println("[ updateGrade 성공 ]");
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbClose(null, pstmt, con);
		}
		return false;
	}

	public boolean deleteGradeNum(int pNum) {
		PreparedStatement pstmt = null;
		try {
			con = conn();
			String sql = "update grade set del_yn='Y' where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pNum);
			int n = pstmt.executeUpdate();
			if (n > 0) {
				System.out.println("[ deleteGradeNum 성공 ]");
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
