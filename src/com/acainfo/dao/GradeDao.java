package com.acainfo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.acainfo.dto.GradeDto;

public class GradeDao extends Dao {
	public ArrayList<GradeDto> selectMemGrade(int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = conn();
			String sql = "select lenum,num,score,rank,del_yn,grdate from grade where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			ArrayList<GradeDto> dto = new ArrayList<GradeDto>();
			while (rs.next())
{

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbClose(rs, pstmt, con);
		}
		return null;
	}

}
