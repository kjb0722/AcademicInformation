package com.acainfo.controller;

import java.util.ArrayList;

import com.acainfo.dao.DpmDao;
import com.acainfo.dao.MemberDao;
import com.acainfo.dao.PassDao;
import com.acainfo.dto.DpmDto;
import com.acainfo.dto.MemberDto;
import com.acainfo.dto.PassDto;
import com.acainfo.view.MainView;

public class Controller {
	PassDao passDao;
	MemberDao memberDao;
	DpmDao dpmDao;

	public void main() {
		daoInit();
		mainLoad();
	}

	private void daoInit() {
		passDao = new PassDao();
		memberDao = new MemberDao();
		dpmDao = new DpmDao();
	}

	private void mainLoad() {
		MainView mainView = new MainView(this);
		mainView.loginLoad();
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
}
