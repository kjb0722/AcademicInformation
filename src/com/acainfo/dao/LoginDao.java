package com.acainfo.dao;

import com.acainfo.controller.DbConnect;
import com.acainfo.dto.LoginDto;

import java.sql.Connection;

public class LoginDao {
    Connection con = null;

    public void login(LoginDto dto) {
        con = DbConnect.conn();

    }
}
