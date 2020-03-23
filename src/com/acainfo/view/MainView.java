package com.acainfo.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.acainfo.component.KButton;
import com.acainfo.component.KLabel;
import com.acainfo.component.KPanel;
import com.acainfo.controller.Controller;
import com.acainfo.dto.MemberDto;

public class MainView extends JFrame {
	private Controller controller;
	
	private MemberDto memberDto;

	private KPanel pnlNorth;
	private KPanel pnlTitle;
	private KPanel pnlUserInfo;
	private KPanel pnlMenu;
	private KPanel pnlDepartment;
	private KPanel pnlClassReg;
	private KPanel pnlGrade;

	private KButton btnLogout;
	private KButton btnClose;

	private KLabel lblTitle;
	private KLabel lblId;
	private KLabel lblNum;
	private KLabel lblGradeYear;
	private KLabel lblName;
	private KLabel lblEmail;
	private KLabel lblPhone;

	private JTextField txtId;
	private JTextField txtNum;
	private JTextField txtGradeYear;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtPhone;

	private JTabbedPane tab;

	private ActionListener menuListener;

	public MainView(Controller controller) {
		this.controller = controller;

		panelInit();

		northInit();
		
		tabInit();

		userInfoInit();
		
		listenerInit();

		init();
	}

	private void userInfoInit() {
		lblId = new KLabel("아이디 : ");
		lblId.setBounds(50, 30, 100, 30);
		pnlUserInfo.add(lblId);
		
		txtId = new JTextField();
		txtId.setBounds(120, 35, 100, 25);
		pnlUserInfo.add(txtId);

		lblNum = new KLabel("학번 : ");
		lblNum.setBounds(50, 70, 100, 30);
		pnlUserInfo.add(lblNum);
		
		txtNum = new JTextField();
		txtNum.setBounds(120, 75, 100, 25);
		pnlUserInfo.add(txtNum);

		lblGradeYear = new KLabel("학년 : ");
		lblGradeYear.setBounds(50, 110, 100, 30);
		pnlUserInfo.add(lblGradeYear);
		
		txtGradeYear = new JTextField();
		txtGradeYear.setBounds(120, 115, 100, 25);
		pnlUserInfo.add(txtGradeYear);

		lblName = new KLabel("이름 : ");
		lblName.setBounds(50, 150, 100, 30);
		pnlUserInfo.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(120, 155, 100, 25);
		pnlUserInfo.add(txtName);

		lblEmail = new KLabel("이메일 : ");
		lblEmail.setBounds(50, 190, 100, 30);
		pnlUserInfo.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(120, 195, 100, 25);
		pnlUserInfo.add(txtEmail);

		lblPhone = new KLabel("휴대폰 번호 : ");
		lblPhone.setBounds(50, 230, 100, 30);
		pnlUserInfo.add(lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(150, 235, 100, 25);
		pnlUserInfo.add(txtPhone);
	}

	private void listenerInit() {
		menuListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object target = e.getSource();
				if (target == btnLogout) {

				} else if (target == btnClose) {
					System.exit(0);
				}
			}
		};

		btnLogout.addActionListener(menuListener);
		btnClose.addActionListener(menuListener);
	}

	private void northInit() {
		lblTitle = new KLabel("[ 학사 정보 관리 ]");
		lblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		pnlTitle.add(lblTitle);

		btnLogout = new KButton("로그아웃");
		pnlMenu.add(btnLogout);

		btnClose = new KButton("종료");
		pnlMenu.add(btnClose);
	}

	private void panelInit() {
		pnlNorth = new KPanel();
		pnlNorth.setLayout(new GridLayout(1, 2));
		add(pnlNorth, BorderLayout.NORTH);

		pnlTitle = new KPanel();
		pnlTitle.setLayout(new FlowLayout());
		pnlNorth.add(pnlTitle);

		pnlMenu = new KPanel();
		pnlMenu.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlNorth.add(pnlMenu);

		pnlUserInfo = new KPanel();

		pnlDepartment = new KPanel();

		pnlClassReg = new KPanel();

		pnlGrade = new KPanel();
	}

	private void tabInit() {
		tab = new JTabbedPane();
		tab.addTab("아이디 정보", pnlUserInfo);
		tab.addTab("학과 정보", pnlDepartment);
		tab.addTab("수강 신청", pnlClassReg);
		tab.addTab("성적 확인", pnlGrade);
		add(tab, BorderLayout.CENTER);
	}

	private void init() {
		setSize(600, 600);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void loginLoad() {
		LoginView loginView = new LoginView(controller);
		memberDto = loginView.getMemberDto();
	}
}
