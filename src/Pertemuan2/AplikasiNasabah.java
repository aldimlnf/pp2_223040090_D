package pertemuan2;

import java.awt.event.*;
import javax.swing.*;

public class AplikasiNasabah extends JFrame {

	private JTextField textNama, textHp;
	private JRadioButton radioJk1, radioJk2;
	private JTextArea txtOutput;
	private JList<String> listTabungan;
	private JSpinner spinnerTransaksi, spinnerTglLahir;
	private JPasswordField passwordField, konfirmasiPassword;

	public AplikasiNasabah() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 600);
		this.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem resetMenuItem = new JMenuItem("Reset");
		JMenuItem exitMenuItem = new JMenuItem("Exit");

		resetMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textNama.setText("");
				textHp.setText("");
				radioJk1.setSelected(true);
				txtOutput.setText("");
				listTabungan.clearSelection();
				spinnerTransaksi.setValue(1);
				passwordField.setText("");
				konfirmasiPassword.setText("");
			}
		});

		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		menu.add(resetMenuItem);
		menu.add(exitMenuItem);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);

		JLabel labelnama = new JLabel("Nama : ");
		labelnama.setBounds(15, 40, 100, 20);
		textNama = new JTextField();
		textNama.setBounds(120, 40, 350, 30);

		JLabel labelHp = new JLabel("Nomor HP : ");
		labelHp.setBounds(15, 80, 100, 20);
		textHp = new JTextField();
		textHp.setBounds(120, 80, 350, 30);

		JLabel labelJk = new JLabel("Jenis Kelamin:");
		labelJk.setBounds(15, 120, 100, 20);
		radioJk1 = new JRadioButton("Laki-Laki", true);
		radioJk1.setBounds(120, 120, 100, 20);
		radioJk2 = new JRadioButton("Perempuan");
		radioJk2.setBounds(220, 120, 100, 20);
		ButtonGroup bg = new ButtonGroup();
		bg.add(radioJk1);
		bg.add(radioJk2);

		// JList untuk memilih jenis tabungan
		JLabel labelTabungan = new JLabel("Jenis Tabungan:");
		labelTabungan.setBounds(15, 160, 100, 20);
		String[] jenisTabungan = {"Tabungan Pelajar", "Tabungan Deposito", "Tabungan Pensiun", "Tabungan Bisnis"};
		listTabungan = new JList<>(jenisTabungan);
		listTabungan.setBounds(120, 160, 150, 70);
		listTabungan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Spinner untuk frekuensi transaksi
		JLabel labelTransaksi = new JLabel("Frekuensi Transaksi:");
		labelTransaksi.setBounds(15, 240, 150, 20);
		spinnerTransaksi = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		spinnerTransaksi.setBounds(170, 240, 100, 30);

		// Password dan Konfirmasi Password
		JLabel labelPassword = new JLabel("Password:");
		labelPassword.setBounds(15, 280, 100, 20);
		passwordField = new JPasswordField();
		passwordField.setBounds(120, 280, 350, 30);

		JLabel labelKonfirmasi = new JLabel("Konfirmasi Password:");
		labelKonfirmasi.setBounds(15, 320, 150, 20);
		konfirmasiPassword = new JPasswordField();
		konfirmasiPassword.setBounds(170, 320, 300, 30);

		// Spinner untuk tanggal lahir
		JLabel labelTglLahir = new JLabel("Tanggal Lahir:");
		labelTglLahir.setBounds(15, 360, 100, 20);
		spinnerTglLahir = new JSpinner(new SpinnerDateModel());
		spinnerTglLahir.setBounds(120, 360, 150, 30);
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerTglLahir, "dd-MM-yyyy");
		spinnerTglLahir.setEditor(editor);

		//Tombol Simpan
		JButton button = new JButton("Simpan");
		button.setBounds(15, 400, 100, 40);

		// TextArea untuk output
		txtOutput = new JTextArea("");
		txtOutput.setBounds(15, 450, 450, 200);
		txtOutput.setEditable(false);

		// Action listener untuk tombol Simpan
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtOutput.setText("");

				String nama = textNama.getText();
				String Hp = textHp.getText();

				String jenisKelamin = radioJk1.isSelected() ? radioJk1.getText() : radioJk2.getText();

				String tabungan = listTabungan.getSelectedValue() != null ? listTabungan.getSelectedValue() : "Belum Memilih";

				int transaksi = (int) spinnerTransaksi.getValue();

				String password = new String(passwordField.getPassword());
				String konfirmasi = new String(konfirmasiPassword.getPassword());
				String passwordCocok = password.equals(konfirmasi) ? "Password Cocok" : "Password Tidak Cocok";

				java.util.Date tglLahir = (java.util.Date) spinnerTglLahir.getValue();
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
				String tanggalLahir = sdf.format(tglLahir);

				txtOutput.append("Nama          : " + nama + "\n");
				txtOutput.append("Nomor HP      : " + Hp + "\n");
				txtOutput.append("Jenis Kelamin : " + jenisKelamin + "\n");
				txtOutput.append("Tabungan      : " + tabungan + "\n");
				txtOutput.append("Frekuensi Transaksi : " + transaksi + "\n");
				txtOutput.append("Tanggal Lahir : " + tanggalLahir + "\n");
				txtOutput.append(passwordCocok + "\n");
				txtOutput.append("===============================");
			}
		});

		this.add(labelnama);
		this.add(textNama);
		this.add(labelHp);
		this.add(textHp);
		this.add(labelJk);
		this.add(radioJk1);
		this.add(radioJk2);
		this.add(labelTabungan);
		this.add(listTabungan);
		this.add(labelTransaksi);
		this.add(spinnerTransaksi);
		this.add(labelPassword);
		this.add(passwordField);
		this.add(labelKonfirmasi);
		this.add(konfirmasiPassword);
		this.add(labelTglLahir);
		this.add(spinnerTglLahir);
		this.add(button);
		this.add(txtOutput);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AplikasiNasabah A = new AplikasiNasabah();
				A.setVisible(true);
			}
		});
	}
}