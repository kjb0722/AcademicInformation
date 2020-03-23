package com.acainfo.controller;

import com.acainfo.dao.MemberDao;
import com.acainfo.dao.PassDao;
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

	public boolean login(PassDto dto) {
		PassDao dao = new PassDao();
		return dao.login(dto);
	}

	public MemberDto memberInfo(PassDto dto) {
		MemberDao dao = new MemberDao();
		return dao.getMemberInfo(dto.getId());
	}
}
