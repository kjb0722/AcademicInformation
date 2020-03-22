package com.acainfo.controller;

import com.acainfo.dao.LoginDao;
import com.acainfo.dto.LoginDto;
import com.acainfo.view.LoginView;
import com.acainfo.view.MainView;

import java.sql.Connection;

public class Controller {
    Connection con = null;
    public void main(){
        mainLoad();
    }
    private void mainLoad(){
        MainView mainView = new MainView(this);
        mainView.loginLoad();
    }
    public void login(LoginDto dto){
        LoginDao dao = new LoginDao();
        dao.login(dto);
    }
}
