package com.acainfo.component;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class KTable extends JTable {
	public KTable() {
		setFont(new Font("맑은 고딕", Font.BOLD, 14));
		setDefaultEditor(Object.class, null);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
}
