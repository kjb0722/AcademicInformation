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
			String sql = "select denum,name,del_yn from department";
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

}
