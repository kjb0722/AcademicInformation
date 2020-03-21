package com.acainfo.controller;

import com.acainfo.view.LoginView;
import com.acainfo.view.MainView;

public class Controller {
    public Controller(){

    }
    public void main(){
        mainLoad();
    }
    private void mainLoad(){
        MainView mainView = new MainView(this);
        mainView.loginLoad();
    }
}
