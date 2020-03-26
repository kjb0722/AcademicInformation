package com.acainfo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.acainfo.component.KButton;
import com.acainfo.component.KDialog;
import com.acainfo.component.KLabel;
import com.acainfo.component.KNumField;
import com.acainfo.component.ValueItem;
import com.acainfo.controller.Controller;
import com.acainfo.dto.GradeDto;

public class GradeInputView extends KDialog {
	private GradeDto gradeDto;
	private KButton btnSave;
	private KButton btnClose;

	private KLabel lblLeNum;
	private KLabel lblNum;
	private KLabel lblScore;
	private KLabel lblRank;

	private KNumField txtLeNum;
	private KNumField txtNum;
	private KNumField txtScore;

	private JComboBox<String> cboRank;

	private String rankItem[] = { "A", "B", "C", "D", "F" };

	public GradeInputView(Controller controller, GradeDto gradeDto) {
		super(controller);
		this.gradeDto = gradeDto;

		lblInit();

		txtInit();

		cboInit();

		btnInit();

		valueInit();

		init();
	}

	private void valueInit() {
		int leNum = gradeDto.getLeNum();
		int num = gradeDto.getNum();
		int score = gradeDto.getScore();
		String rank = gradeDto.getRank();
		txtLeNum.setText(Integer.toString(leNum));
		txtNum.setText(Integer.toString(num));
		txtScore.setText(Integer.toString(score));

		cboRank.setSelectedItem(rank);
	}

	private void cboInit() {
		cboRank = new JComboBox<String>();
		cboRank.setBounds(120, 140, 100, 20);
		add(cboRank);

		for (int i = 0; i < rankItem.length; i++) {
			cboRank.addItem(rankItem[i]);
		}
	}

	private void txtInit() {
		txtLeNum = new KNumField();
		txtLeNum.setBounds(120, 20, 100, 20);
		txtLeNum.setEditable(false);
		add(txtLeNum);

		txtNum = new KNumField();
		txtNum.setBounds(120, 60, 100, 20);
		txtNum.setEditable(false);
		add(txtNum);

		txtScore = new KNumField();
		txtScore.setBounds(120, 100, 100, 20);
		add(txtScore);
	}

	private void lblInit() {
		lblLeNum = new KLabel("점수 번호");
		lblLeNum.setLocation(20, 20);
		add(lblLeNum);

		lblNum = new KLabel("학생 번호");
		lblNum.setLocation(20, 60);
		add(lblNum);

		lblScore = new KLabel("점수");
		lblScore.setLocation(20, 100);
		add(lblScore);

		lblRank = new KLabel("등급");
		lblRank.setLocation(20, 140);
		add(lblRank);
	}

	private void btnInit() {
		btnSave = new KButton("저장");
		btnSave.setLocation(20, 200);
		add(btnSave);

		btnClose = new KButton("닫기");
		btnClose.setLocation(130, 200);
		add(btnClose);

		btnListener();
	}

	private void btnListener() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object target = e.getSource();
				if (target == btnSave) {
					gradeSave();
				} else if (target == btnClose) {
					dispose();
				}
			}
		};
		btnSave.addActionListener(listener);
		btnClose.addActionListener(listener);
	}

	private void gradeSave() {
		if (txtLeNum.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "성적 번호를 입력하세요.");
			return;
		}
		if (txtNum.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "학생 번호를 입력하세요.");
			return;
		}
		if (txtScore.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "점수를 입력하세요.");
			return;
		}
		if (cboRank.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "등급을 입력하세요.");
			return;
		}

		int leNum = Integer.parseInt(txtLeNum.getText());
		int num = Integer.parseInt(txtNum.getText());
		int score = Integer.parseInt(txtScore.getText());
		String rank = (String) cboRank.getSelectedItem();
		System.out.println(rank);
		GradeDto dto = new GradeDto(gradeDto.getGrNum(), leNum, num, score, rank, "N", null);
		if (controller.updateGrade(dto)) {
			JOptionPane.showMessageDialog(this, "[ 점수 저장 완료 ]");
			dispose();
		}
	}

	private void init() {
		setTitle("[ 성적 입력 ]");
		setSize(250, 250);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
