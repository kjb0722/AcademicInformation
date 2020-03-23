package com.acainfo.controller;

import com.acainfo.dao.PassDao;
import com.acainfo.dto.PassDto;
import com.acainfo.view.LoginView;
import com.acainfo.view.MainView;

import java.sql.Connection;

public class Controller {
	Connection con = null;

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
}
