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
			String sql = "select lenum,num,score,rank,del_yn,grdate from grade where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pNum);
			rs = pstmt.executeQuery();

			ArrayList<GradeDto> list = new ArrayList<GradeDto>();
			while (rs.next()) {
				int leNum = rs.getInt("lenum");
				int num = rs.getInt("num");
				int score = rs.getInt("score");
				String rank = rs.getString("rank");
				String del_yn = rs.getString("del_yn");
				Date grdate = rs.getDate("grdate");
				list.add(new GradeDto(leNum, num, score, rank, del_yn, grdate));
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
			String sql = "select lenum,num,score,rank,del_yn,grdate from grade where lenum in(select lenum from lecture where num=?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pNum);
			rs = pstmt.executeQuery();

			ArrayList<GradeDto> list = new ArrayList<GradeDto>();
			while (rs.next()) {
				int leNum = rs.getInt("lenum");
				int num = rs.getInt("num");
				int score = rs.getInt("score");
				String rank = rs.getString("rank");
				String del_yn = rs.getString("del_yn");
				Date grdate = rs.getDate("grdate");
				list.add(new GradeDto(leNum, num, score, rank, del_yn, grdate));
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

}
