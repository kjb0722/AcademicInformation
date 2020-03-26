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

	/////////////////////////////////
	// return
	// -1 : 아이디 중복
	// 0 : 실패
	// 1 : 완료
	/////////////////////////////////
	public int insertMember(MemberDto pDto, String pPass) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		try {
			con = conn();
			String sql3 = "select id from member where id=?";
			pstmt3 = con.prepareStatement(sql3);
			pstmt3.setString(1, pDto.getId());
			rs = pstmt3.executeQuery();
			if (rs.next()) {
				System.out.println("[ insertMember 아이디 중복 ]");
				return -1;
			}

			con.setAutoCommit(false);
			String sql = "insert into member values(member_num_seq.nextval,?,?,?,?,?,?,'N',?,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pDto.getId());
			pstmt.setString(2, pDto.getName());
			pstmt.setString(3, pDto.getEmail());
			pstmt.setString(4, pDto.getPhone());
			pstmt.setString(5, pDto.getAddr());
			pstmt.setInt(6, pDto.getHagnyeno());
			pstmt.setInt(7, pDto.getAuth());
			int n = pstmt.executeUpdate();
			if (n > 0) {
				System.out.println("[ insertMember member 성공 ]");
			}

			String sql2 = "insert into pass values(member_num_seq.currval,?,?,'N')";
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setString(1, pDto.getId());
			pstmt2.setString(2, pPass);
			int n2 = pstmt2.executeUpdate();
			if (n2 > 0) {
				System.out.println("[ insertMember(2) pass 성공 ]");
				con.commit();
				return 1;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				con.rollback();
				return -1;
			} catch (SQLException e1) {
				System.out.println(e.getMessage());
			}
		} finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			dbClose(pstmt2);
			dbClose(null, pstmt, con);
		}
		return -1;
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

	public ArrayList<MemberDto> selectDpmMemList(int pDeNum) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDto> list = null;
		try {
			con = conn();
			String sql = "select m.num,m.name from member m,dpm_member d where m.num=d.num(+) and d.denum=? and m.del_yn='N'";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pDeNum);
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

	public ArrayList<MemberDto> selectMemDpm(int pDeNum) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDto> list = null;
		try {
			con = conn();
			String sql = "select m.num,m.id,m.name,m.email,m.phone,m.addr,m.hagnyeno,m.del_yn,m.auth,m.medate from member m, dpm_member d where m.num = d.num and denum=? and del_yn='N'";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pDeNum);
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

	public boolean deleteMem(int pNum) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			con = conn();
			con.setAutoCommit(false);

			LectureDao lecDao = new LectureDao();
			LecMemDao lecMemDao = new LecMemDao();
			DpmDao dpmDao = new DpmDao();
			DpmMemDao dpmMemDao = new DpmMemDao();
			GradeDao gradeDao = new GradeDao();

			lecDao.deleteLecNum(pNum);
			lecMemDao.deleteLecMemNum(pNum);
			dpmDao.deleteDpmNum(pNum);
			dpmMemDao.deleteDpmMemNum(pNum);
			gradeDao.selectMemGrade(pNum);

			String sql = "update member set del_yn='Y' where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pNum);
			int n = pstmt.executeUpdate();
			if (n > 0) {
				System.out.println("[ deleteMem member 성공 ]");
			}

			String sql2 = "update pass set del_yn='Y' where num=?";
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, pNum);
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

	public boolean updateMemInfo(MemberDto pDto) {
		PreparedStatement pstmt = null;
		try {
			con = conn();
			String sql = "update member set id=?,name=?,email=?,phone=?,addr=? where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pDto.getId());
			pstmt.setString(2, pDto.getName());
			pstmt.setString(3, pDto.getEmail());
			pstmt.setString(4, pDto.getPhone());
			pstmt.setString(5, pDto.getAddr());
			pstmt.setInt(6, pDto.getNum());
			int n = pstmt.executeUpdate();
			if (n > 0) {
				System.out.println("[ updateMemInfo 성공 ]");
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
