package com.acainfo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.acainfo.dto.MemberDto;

public class MemberDao extends Dao {
	public MemberDto getMemberInfo(String pId) {
		con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = conn();
			String sql = "select num,id,name,email,phone,addr,hagnyeno,del_yn,auth,medate from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int num = rs.getInt("num");
				String id = rs.getString("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String addr = rs.getString("addr");
				int hagnyeno = rs.getInt("hagnyeno");
				String del_yn = rs.getString("del_yn");
				int auth = rs.getInt("auth");
				Date medate = rs.getDate("medate");
				return new MemberDto(num, id, name, email, phone, addr, hagnyeno, del_yn, auth, medate);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbClose(rs, pstmt, con);
		}
		return null;
	}
}
