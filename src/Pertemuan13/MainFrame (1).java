package srcmodul13;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MainFrame extends JFrame {
    private JTable table;
    private JTextField nimField;
    private JTextField namaField;
    private JTextField nilaiField;
    private JButton addButton;
    private JButton generateButton;
    private JProgressBar progressBar;
    private MahasiswaTableModel tableModel;

    public MainFrame() {
        // Set up frame
        setTitle("Data Mahasiswa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        // Initialize components
        tableModel = new MahasiswaTableModel();
        table = new JTable(tableModel);
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        // Create input panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        namaField = new JTextField(20);
        nimField = new JTextField(20);
        nilaiField = new JTextField(20);
        addButton = new JButton("Tambah");
        generateButton = new JButton("Generate 100 Data");

        inputPanel.add(new JLabel("Nama:"));
        inputPanel.add(namaField);
        inputPanel.add(new JLabel("NIM:"));
        inputPanel.add(nimField);
        inputPanel.add(new JLabel("Nilai:"));
        inputPanel.add(nilaiField);
        inputPanel.add(addButton);
        inputPanel.add(generateButton);

        // Layout
        setLayout(new BorderLayout(10, 10));
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(progressBar, BorderLayout.SOUTH);

        // Add button action
        addButton.addActionListener(e -> {
            if (!namaField.getText().isEmpty() && 
                !nimField.getText().isEmpty() && 
                !nilaiField.getText().isEmpty()) {
                
                Mahasiswa mhs = new Mahasiswa(
                    namaField.getText(),
                    nimField.getText(),
                    nilaiField.getText()
                );
                tableModel.add(mhs);
                clearFields();
            }
        });

        // Generate button action with SwingWorker
        generateButton.addActionListener(e -> {
            generateButton.setEnabled(false);
            addButton.setEnabled(false);
            progressBar.setValue(0);

            SwingWorker<Void, Integer> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    for (int i = 0; i < 100; i++) {
                        Thread.sleep(100); // Simulate processing time
                        String num = String.format("%03d", i + 1);
                        Mahasiswa mhs = new Mahasiswa(
                            "Mahasiswa " + num,
                            "2024" + num,
                            String.valueOf(70 + (int)(Math.random() * 30))
                        );
                        tableModel.add(mhs);
                        publish(i + 1);
                    }
                    return null;
                }

                @Override
                protected void process(List<Integer> chunks) {
                    progressBar.setValue(chunks.get(chunks.size() - 1));
                }

                @Override
                protected void done() {
                    generateButton.setEnabled(true);
                    addButton.setEnabled(true);
                    JOptionPane.showMessageDialog(MainFrame.this, 
                        "Berhasil generate 100 data!", 
                        "Sukses", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
            };

            worker.execute();
        });
    }

    private void clearFields() {
        namaField.setText("");
        nimField.setText("");
        nilaiField.setText("");
        namaField.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}