package com.acainfo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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
			con = conn();
			String sql = "select num,id,name,email,phone,addr,hagnyeno,del_yn,auth,medate from member where del_yn='N'";
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
			con = conn();
			con.setAutoCommit(false);
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
				System.out.println("[ insertMember(2) pass 성공 ]");
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
			dbClose(pstmt2);
			dbClose(null, pstmt, con);
		}
		return false;
	}

	public ArrayList<MemberDto> selectMemList(int pAuth) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDto> list = null;
		try {
			con = conn();
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

	public ArrayList<MemberDto> selectNoneDpmMember() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDto> list = null;
		try {
			con = conn();
			String sql = "select m.num,m.name from member m,dpm_member d where m.num=d.num(+) and d.denum is null and m.auth=10 and m.del_yn='N'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			list = new ArrayList<MemberDto>();
			while (rs.next()) {
				int num = rs.getInt("num");
				String name = rs.getString("name");
				list.add(new MemberDto(num, name));
			}

			System.out.println("[ selectNoneDpmMember 성공 ]");
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			dbClose(rs, pstmt, con);
		}
	}

	public ArrayList<MemberDto> selectDpmMemList(int denum) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDto> list = null;
		try {
			con = conn();
			String sql = "select m.num,m.name from member m,dpm_member d where m.num=d.num(+) and d.denum=? and m.del_yn='N'";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, denum);
			rs = pstmt.executeQuery();

			list = new ArrayList<MemberDto>();
			while (rs.next()) {
				int num = rs.getInt("num");
				String name = rs.getString("name");
				list.add(new MemberDto(num, name));
			}

			System.out.println("[ selectNoneDpmMember 성공 ]");
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			dbClose(rs, pstmt, con);
		}
	}

	public ArrayList<MemberDto> selectMemDpm(int denum) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDto> list = null;
		try {
			con = conn();
			String sql = "select m.num,m.id,m.name,m.email,m.phone,m.addr,m.hagnyeno,m.del_yn,m.auth,m.medate from member m, dpm_member d where m.num = d.num and denum=? and del_yn='N'";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, denum);
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
			System.out.println("[ selectMemDpm 성공]");
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbClose(rs, pstmt, con);
		}
		return null;
	}

	public boolean deleteMem(int num) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			con = conn();
			con.setAutoCommit(false);
			String sql = "update member set del_yn='Y' where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			int n = pstmt.executeUpdate();
			if (n > 0) {
				System.out.println("[ deleteMem member 성공 ]");
			}

			String sql2 = "update pass set del_yn='Y' where num=?";
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, num);
			int n2 = pstmt2.executeUpdate();
			if (n2 > 0) {
				System.out.println("[ deleteMem(2) pass 성공 ]");
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
}
