package com.acainfo.controller;

import java.util.ArrayList;

import com.acainfo.dao.DpmDao;
import com.acainfo.dao.DpmMemDao;
import com.acainfo.dao.GradeDao;
import com.acainfo.dao.LecMemDao;
import com.acainfo.dao.LectureDao;
import com.acainfo.dao.MemberDao;
import com.acainfo.dao.PassDao;
import com.acainfo.dto.DpmDto;
import com.acainfo.dto.DpmMemDto;
import com.acainfo.dto.GradeDto;
import com.acainfo.dto.LecMemDto;
import com.acainfo.dto.LectureDto;
import com.acainfo.dto.MemberDto;
import com.acainfo.dto.PassDto;
import com.acainfo.view.MainView;

public class Controller {
	PassDao passDao;
	MemberDao memberDao;
	DpmDao dpmDao;
	DpmMemDao dpmMemDao;
	LectureDao lectureDao;
	LecMemDao lecMemDao;
	GradeDao gradeDao;

	public void main() {
		daoInit();
		mainLoad();
	}

	private void daoInit() {
		passDao = new PassDao();
		memberDao = new MemberDao();
		dpmDao = new DpmDao();
		dpmMemDao = new DpmMemDao();
		lectureDao = new LectureDao();
		lecMemDao = new LecMemDao();
		gradeDao = new GradeDao();
	}

	private void mainLoad() {
		MainView mainView = new MainView(this);
		mainView.reloadLogin();
	}

	public boolean selectLogin(PassDto dto) {
		passDao = new PassDao();
		return passDao.selectLogin(dto);
	}

	public MemberDto selectMemInfo(PassDto dto) {
		memberDao = new MemberDao();
		return memberDao.selectMemInfo(dto.getId());
	}

	public ArrayList<MemberDto> selectMemListAll() {
		memberDao = new MemberDao();
		return memberDao.selectMemListAll();
	}

	public boolean insertMember(MemberDto dto, String pass) {
		memberDao = new MemberDao();
		return memberDao.insertMember(dto, pass);
	}

	public ArrayList<MemberDto> selectMemList(int auth) {
		memberDao = new MemberDao();
		return memberDao.selectMemList(auth);
	}

	public ArrayList<DpmDto> selectDpmList() {
		dpmDao = new DpmDao();
		return dpmDao.selectDpmList();
	}

	public boolean insertDpm(String dpmName) {
		return dpmDao.insertDpm(dpmName);
	}

	public boolean deleteDpm(int deNum) {
		return dpmDao.deleteDpm(deNum);
	}

	public ArrayList<MemberDto> selectNoneDpmMember() {
		return memberDao.selectNoneDpmMember();
	}

	public ArrayList<MemberDto> selectDpmMemList(int denum) {
		return memberDao.selectDpmMemList(denum);
	}

	public boolean delInsDpmMem(ArrayList<DpmMemDto> arrayDto, int deNum) {
		return dpmMemDao.delInsDpmMem(arrayDto, deNum);
	}

	public ArrayList<MemberDto> selectMemDpm(int denum) {
		return memberDao.selectMemDpm(denum);
	}

	public ArrayList<LectureDto> selectLecAll(int num) {
		return lectureDao.selectLecYN(num);
	}

	public boolean insertLecMem(LecMemDto dto) {
		return lecMemDao.insertLecMem(dto);
	}

	public boolean deleteLecMem(LecMemDto dto) {
		return lecMemDao.deleteLecMem(dto);
	}

	public boolean insertLec(LectureDto dto) {
		return lectureDao.insertLec(dto);
	}

	public ArrayList<LectureDto> selectLec() {
		return lectureDao.selectLec();
	}

	public boolean deleteLec(int lenum) {
		return lectureDao.deleteLec(lenum);
	}

	public ArrayList<GradeDto> selectMemGrade(int num) {
		return gradeDao.selectMemGrade(num);
	}
}
