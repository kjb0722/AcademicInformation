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
	public void main() {
		mainLoad();
	}

	private void mainLoad() {
		MainView mainView = new MainView(this);
		mainView.loginLoad();
	}

	public boolean selectLogin(PassDto dto) {
		PassDao dao = new PassDao();
		return dao.selectLogin(dto);
	}

	public MemberDto selectMemInfo(PassDto dto) {
		MemberDao dao = new MemberDao();
		return dao.selectMemInfo(dto.getId());
	}

	public ArrayList<MemberDto> selectMemListAll() {
		MemberDao dao = new MemberDao();
		return dao.selectMemListAll();
	}

	public boolean insertMember(MemberDto dto, String pass) {
		MemberDao dao = new MemberDao();
		return dao.insertMember(dto, pass);
	}

	public ArrayList<MemberDto> selectMemList(int auth) {
		MemberDao dao = new MemberDao();
		return dao.selectMemList(auth);
	}

	public ArrayList<DpmDto> selectDpmList() {
		DpmDao dao = new DpmDao();
		return dao.selectDpmList();
	}
}
