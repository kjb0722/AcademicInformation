package com.acainfo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.acainfo.component.KButton;
import com.acainfo.component.KLabel;
import com.acainfo.component.KPanel;
import com.acainfo.controller.Controller;
import com.acainfo.dto.MemberDto;

public class MainView extends JFrame {
	private Controller controller;

	public static MemberDto memberDto;

	private KPanel pnlNorth;
	private KPanel pnlTitle;
	private KPanel pnlMemberInfo;
	private KPanel pnlMenu;
	private DpmMemberView pnlDepartment;
	private LecAppView pnlLecture;
	private GradeView pnlGrade;
	private GradeMgtView pnlGradeMgt;
	private LecMgtView pnlLectureMgt;
	private KPanel pnlAdmin;

	private KButton btnLogout;
	private KButton btnClose;
	private KButton btnMemberMgt;
	private KButton btnDpmMgt;
	private KButton btnDpmMemMgt;

	private KLabel lblTitle;
	private KLabel lblId;
	private KLabel lblNum;
	private KLabel lblHagnyeno;
	private KLabel lblName;
	private KLabel lblEmail;
	private KLabel lblPhone;
	private KLabel lblAddr;
	private KLabel lblAuth;
	private KLabel lblStatus;

	private JTextField txtId;
	private JTextField txtNum;
	private JTextField txtHagnyeno;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtAddr;
	private JTextField txtAuth;

	private JTabbedPane tab;

	public MainView(Controller controller) {
		this.controller = controller;
		memberDtoInit();

		panelInit();

		northInit();

		tabInit();

		memberInfoInit();

		adminInit();

		listenerInit();

		init();
	}

	private void memberDtoInit() {
		memberDto = new MemberDto(-1, "", "", "", "", "", -1, "N", -1, null);
	}

	private void memberInfoInit() {
		lblId = new KLabel("아이디 : ");
		lblId.setBounds(50, 30, 100, 30);
		pnlMemberInfo.add(lblId);

		txtId = new JTextField();
		txtId.setBounds(120, 35, 100, 25);
		pnlMemberInfo.add(txtId);

		lblNum = new KLabel("학번 : ");
		lblNum.setBounds(50, 70, 100, 30);
		pnlMemberInfo.add(lblNum);

		txtNum = new JTextField();
		txtNum.setBounds(120, 75, 100, 25);
		txtNum.setEditable(false);
		pnlMemberInfo.add(txtNum);

		lblHagnyeno = new KLabel("학년 : ");
		lblHagnyeno.setBounds(50, 110, 100, 30);
		pnlMemberInfo.add(lblHagnyeno);

		txtHagnyeno = new JTextField();
		txtHagnyeno.setBounds(120, 115, 100, 25);
		txtHagnyeno.setEditable(false);
		pnlMemberInfo.add(txtHagnyeno);

		lblName = new KLabel("이름 : ");
		lblName.setBounds(50, 150, 100, 30);
		pnlMemberInfo.add(lblName);

		txtName = new JTextField();
		txtName.setBounds(120, 155, 100, 25);
		pnlMemberInfo.add(txtName);

		lblEmail = new KLabel("이메일 : ");
		lblEmail.setBounds(50, 190, 100, 30);
		pnlMemberInfo.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(120, 195, 170, 25);
		pnlMemberInfo.add(txtEmail);

		lblPhone = new KLabel("휴대폰 번호 : ");
		lblPhone.setBounds(50, 230, 100, 30);
		pnlMemberInfo.add(lblPhone);

		txtPhone = new JTextField();
		txtPhone.setBounds(150, 235, 100, 25);
		pnlMemberInfo.add(txtPhone);

		lblAddr = new KLabel("주소 : ");
		lblAddr.setBounds(50, 270, 100, 30);
		pnlMemberInfo.add(lblAddr);

		txtAddr = new JTextField();
		txtAddr.setBounds(120, 275, 300, 25);
		pnlMemberInfo.add(txtAddr);

		lblAuth = new KLabel("구분 : ");
		lblAuth.setBounds(50, 310, 100, 30);
		pnlMemberInfo.add(lblAuth);

		txtAuth = new JTextField();
		txtAuth.setBounds(120, 315, 100, 25);
		txtAuth.setEditable(false);
		pnlMemberInfo.add(txtAuth);
	}

	private void listenerInit() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object target = e.getSource();
				if (target == btnLogout) {
					reloadLogin();
				} else if (target == btnClose) {
					System.exit(0);
				}
			}
		};

		btnLogout.addActionListener(listener);
		btnClose.addActionListener(listener);
	}

	public void reloadLogin() {
		memberDto = null;
		loginLoad();
	}

	private void gradeMgtLoad() {
		pnlGradeMgt.selectGradeMgt();
	}

	private void gradeLoad() {
		pnlGrade.selectGrade();
	}

	private void lecMgtLoad() {
		pnlLectureMgt.selectLec();
	}

	private void lecLoad() {
		pnlLecture.selectLecAll();
	}

	private void dpmLoad() {
		pnlDepartment.selectDpmList();
		pnlDepartment.selectMemMemDpm();
	}

	private void northInit() {
		lblTitle = new KLabel("[ 학사 정보 관리 ]");
		lblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		pnlTitle.add(lblTitle);

		lblStatus = new KLabel();
		lblStatus.setForeground(Color.red);
		pnlMenu.add(lblStatus);

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

		pnlMemberInfo = new KPanel();

		pnlDepartment = new DpmMemberView(controller);

		pnlLecture = new LecAppView(controller);

		pnlGrade = new GradeView(controller);

		pnlGradeMgt = new GradeMgtView(controller);

		pnlLectureMgt = new LecMgtView(controller);

		pnlAdmin = new KPanel();
	}

	private void tabInit() {
		tab = new JTabbedPane();
		tab.addTab("아이디 정보", pnlMemberInfo);
		tab.addTab("학과 정보", pnlDepartment);
		tab.addTab("수강 신청", pnlLecture);
		tab.addTab("성적", pnlGrade);
		add(tab, BorderLayout.CENTER);

		tabListener();
	}

	private void tabListener() {
		tab.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				switch (tab.getSelectedIndex()) {
				case 0:
					break;
				case 1:
					dpmLoad();
					break;
				case 2:
					lecLoad();
					break;
				case 3:
					gradeLoad();
					break;
				case 4:
					gradeMgtLoad();
					break;
				case 5:
					lecMgtLoad();
					break;
				case 6:
					break;
				}
			}
		});
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
		MainView.memberDto = loginView.getMemberDto();

		setMemInfo(memberDto);

		if (memberDto != null) {
			lblStatus.setText("[ " + memberDto.getName() + " ]");
			removeTabTitle("성적 관리");
			removeTabTitle("강의 관리");
			removeTabTitle("관리자");

			if (memberDto.getAuth() == 50) {
				addTabProf();
			} else if (memberDto.getAuth() == 99) {
				addTabProf();
				addTabAdmin();
			}

			tab.setSelectedIndex(0);
		}
	}

	private void setMemInfo(MemberDto memberDto) {
		txtId.setText(memberDto.getId());
		txtNum.setText(Integer.toString(memberDto.getNum()));
		txtHagnyeno.setText(Integer.toString(memberDto.getHagnyeno()));
		txtName.setText(memberDto.getName());
		txtEmail.setText(memberDto.getEmail());
		txtPhone.setText(memberDto.getPhone());
		txtAddr.setText(memberDto.getAddr());

		String auth = "";
		if (memberDto.getAuth() == 10) {
			auth = "학생";
		} else if (memberDto.getAuth() == 50) {
			auth = "교수";
		} else if (memberDto.getAuth() == 99) {
			auth = "관리자";
		}
		txtAuth.setText(auth);
	}

	private void addTabProf() {
		tab.addTab("성적 관리", pnlGradeMgt);
		tab.addTab("강의 관리", pnlLectureMgt);
	}

	private void addTabAdmin() {
		tab.addTab("관리자", pnlAdmin);
	}

	private void removeTabTitle(String title) {
		for (int i = 0; i < tab.getTabCount(); i++) {
			if (tab.getTitleAt(i).equals(title)) {
				tab.remove(i);
			}
		}
	}

	private void adminInit() {
		btnMemberMgt = new KButton("회원 관리");
		btnMemberMgt.setLocation(40, 40);
		pnlAdmin.add(btnMemberMgt);

		btnDpmMgt = new KButton("학과 관리");
		btnDpmMgt.setLocation(40, 100);
		pnlAdmin.add(btnDpmMgt);

		btnDpmMemMgt = new KButton("학과 회원 관리");
		btnDpmMemMgt.setLocation(40, 160);
		btnDpmMemMgt.setBounds(40, 160, 130, 30);
		pnlAdmin.add(btnDpmMemMgt);

		adminListener();
	}

	private void adminListener() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object target = e.getSource();
				if (target == btnMemberMgt) {
					new MemberMgtView(controller);
				} else if (target == btnDpmMgt) {
					new DpmMgtView(controller);
				} else if (target == btnDpmMemMgt) {
					new DpmMemMgtView(controller);
				}
			}
		};

		btnMemberMgt.addActionListener(listener);
		btnDpmMgt.addActionListener(listener);
		btnDpmMemMgt.addActionListener(listener);
	}
}
