package Pertemuan2;

import java.awt.event.*;
import javax.swing.*;

public class AplikasiBiodataTeman extends JFrame {

	public AplikasiBiodataTeman() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel labelnama = new JLabel("Nama : ");
		labelnama.setBounds(15, 40, 350, 10);
		JTextField textNama = new JTextField();
		textNama.setBounds(15, 60, 350, 30);

		JLabel labelHp = new JLabel("Nomor HP : ");
		labelHp.setBounds(15, 100, 350, 10);
		JTextField textHp = new JTextField();
		textHp.setBounds(15, 130, 350, 30);

		JLabel labelJk = new JLabel("Jenis Kelamin:");
		labelJk.setBounds(15, 190, 350, 10);
		JRadioButton radioJk1 = new JRadioButton("Laki-Laki", true);
		radioJk1.setBounds(15, 220, 350, 30);
		JRadioButton radioJk2 = new JRadioButton("Perempuan");
		radioJk2.setBounds(15, 250, 350, 30);

		ButtonGroup bg = new ButtonGroup();
		bg.add(radioJk1);
		bg.add(radioJk2);

		JCheckBox wnCheckBox = new JCheckBox("Warga Negara Asing");
		wnCheckBox.setBounds(15, 280, 350, 30);

		JButton button = new JButton("Simpan");
		button.setBounds(15, 310, 100, 40);

		JTextArea txtOutput = new JTextArea("");
		txtOutput.setBounds(15, 360, 350, 100);
		txtOutput.setEditable(false);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtOutput.setText("");

				String nama = textNama.getText();
				String Hp = textHp.getText();

				String jenisKelamin = "";
				if (radioJk1.isSelected()) {
					jenisKelamin = radioJk1.getText();
				} else if (radioJk2.isSelected()) {
					jenisKelamin = radioJk2.getText();
				}

				String WN = wnCheckBox.isSelected() ? "Ya" : "Tidak";

				txtOutput.append("Nama          : " + nama + "\n");
				txtOutput.append("Nomor HP      : " + Hp + "\n");
				txtOutput.append("Jenis Kelamin : " + jenisKelamin + "\n");
				txtOutput.append("WNA           : " + WN + "\n");
				txtOutput.append("===============================");
			}
		});

		this.add(labelnama);
		this.add(textNama);
		this.add(labelHp);
		this.add(textHp);
		this.add(button);
		this.add(labelJk);
		this.add(radioJk1);
		this.add(radioJk2);
		this.add(wnCheckBox);
		this.add(txtOutput);

		this.setSize(400, 500);
		this.setLayout(null);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AplikasiBiodataTeman A = new AplikasiBiodataTeman();
				A.setVisible(true);
			}
		});
	}
}