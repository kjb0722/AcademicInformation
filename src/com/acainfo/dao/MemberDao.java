package com.acainfo.dao;

import com.acainfo.controller.DbUtil;
import com.acainfo.dto.MemberDto;

public class MemberDao extends Dao {
	public MemberDto getMemberInfo(String id, String pw) {
		con = DbUtil.conn();
		return null;
	}
}
