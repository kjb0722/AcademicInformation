package com.acainfo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
	private MemberInfo pnlMemberInfo;
	private KPanel pnlMenu;
	private DpmMemberView pnlDepartment;
	private LecAppView pnlLecture;
	private GradeView pnlGrade;
	private GradeMgtView pnlGradeMgt;
	private LecMgtView pnlLectureMgt;
	private KPanel pnlAdmin;

	private KLabel lblTitle;
	private KLabel lblStatus;

	private KButton btnLogout;
	private KButton btnClose;
	private KButton btnMemberMgt;
	private KButton btnDpmMgt;
	private KButton btnDpmMemMgt;

	private JTabbedPane tab;

	public MainView(Controller controller) {
		this.controller = controller;
		memberDtoInit();

		panelInit();

		northInit();

		tabInit();

		adminInit();

		listenerInit();

		init();
	}

	private void memberDtoInit() {
		memberDto = new MemberDto(-1, "", "", "", "", "", -1, "N", -1, null);
	}

	private void listenerInit() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object target = e.getSource();
				if (target == btnLogout) {
					reloadLogin();
				} else if (target == btnClose) {
					int result = JOptionPane.showConfirmDialog(MainView.this, "프로그램을 종료하시겠습니까?", "[종료]",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						System.exit(0);
					}
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

		pnlMemberInfo = new MemberInfo(controller, this);

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

		if (memberDto != null) {
			pnlMemberInfo.setMemInfo(memberDto);
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

	public void setLblStatus(String text) {
		lblStatus.setText("[ " + text + " ]");
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
