package com.acainfo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.acainfo.controller.DbUtil;
import com.acainfo.dto.MemberDto;

public class MemberDao extends Dao {
	public MemberDto selectMemInfo(String pId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = conn();
			String sql = "select num,id,name,email,phone,addr,hagnyeno,del_yn,auth,medate from member where id=? and del_yn='N'";
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
				System.out.println("[ selectMemInfo 성공 ]");
				return new MemberDto(num, id, name, email, phone, addr, hagnyeno, del_yn, auth, medate);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbClose(rs, pstmt, con);
		}
		return null;
	}

	public ArrayList<MemberDto> selectMemListAll() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDto> list = null;
		try {
			con = DbUtil.conn();
			String sql = "select num,id,name,email,phone,addr,hagnyeno,del_yn,auth,medate from member";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			list = new ArrayList<MemberDto>();
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
				list.add(new MemberDto(num, id, name, email, phone, addr, hagnyeno, del_yn, auth, medate));
			}
			System.out.println("[ selectMemListAll 성공]");
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbClose(rs, pstmt, con);
		}
		return null;
	}

	public boolean insertMember(MemberDto dto, String pass) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			con = DbUtil.conn();
			String sql = "insert into member values(member_num_seq.nextval,?,?,?,?,?,?,'N',?,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getPhone());
			pstmt.setString(5, dto.getAddr());
			pstmt.setInt(6, dto.getHagnyeno());
			pstmt.setInt(7, dto.getAuth());
			int n = pstmt.executeUpdate();
			if (n > 0) {
				System.out.println("[ insertMember member 성공 ]");
			}

			String sql2 = "insert into pass values(member_num_seq.currval,?,?,'N')";
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setString(1, dto.getId());
			pstmt2.setString(2, pass);
			int n2 = pstmt2.executeUpdate();
			if (n2 > 0) {
				System.out.println("[ insertMember pass 성공 ]");
				con.commit();
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				con.rollback();
				return false;
			} catch (SQLException e1) {
				System.out.println(e.getMessage());
			}
		} finally {
			DbUtil.dbClose(pstmt);
			DbUtil.dbClose(con);
		}
		return false;
	}

	public ArrayList<MemberDto> selectMemList(int pAuth) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDto> list = null;
		try {
			con = DbUtil.conn();
			String sql = "select num,id,name,email,phone,addr,hagnyeno,del_yn,auth,medate from member where auth=? and del_yn='N'";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pAuth);
			rs = pstmt.executeQuery();

			list = new ArrayList<MemberDto>();
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
				list.add(new MemberDto(num, id, name, email, phone, addr, hagnyeno, del_yn, auth, medate));
			}
			System.out.println("[ selectMemList 성공]");
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbClose(rs, pstmt, con);
		}
		return null;
	}
}
