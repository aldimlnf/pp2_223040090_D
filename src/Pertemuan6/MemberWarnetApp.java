package Pertemuan6;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MemberWarnetApp extends JFrame {
    private JTextField idField, nameField, usernameField, phoneField;
    private JTable memberTable;
    private MemberTableModel memberTableModel;

    public MemberWarnetApp() {
        setTitle("Biodata Member Warnet");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setJMenuBar(createMenuBar());
        add(createMemberForm(), BorderLayout.NORTH);
        memberTableModel = new MemberTableModel();
        memberTable = new JTable(memberTableModel);
        add(new JScrollPane(memberTable), BorderLayout.CENTER);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        
        JMenuItem item1 = new JMenuItem("Input Member");
        item1.addActionListener(e -> clearForm());
        menu.add(item1);
        
        JMenuItem item2 = new JMenuItem("Keluar");
        item2.addActionListener(e -> System.exit(0));
        menu.add(item2);
        
        menuBar.add(menu);
        return menuBar;
    }

    private JPanel createMemberForm() {
        JPanel panel = new JPanel(new GridLayout(5, 2));

        panel.add(new JLabel("ID Member:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Nama Lengkap:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Nomor Telepon:"));
        phoneField = new JTextField();
        panel.add(phoneField);

        JButton addButton = new JButton("Tambah Member");
        addButton.addActionListener(new AddMemberAction());
        panel.add(addButton);

        return panel;
    }

    private void clearForm() {
        idField.setText("");
        nameField.setText("");
        usernameField.setText("");
        phoneField.setText("");
    }

    private class AddMemberAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = idField.getText();
            String name = nameField.getText();
            String username = usernameField.getText();
            String phone = phoneField.getText();

            if (!id.isEmpty() && !name.isEmpty() && !username.isEmpty() && !phone.isEmpty()) {
                memberTableModel.addMember(new Member(id, name, username, phone));
                clearForm();
            } else {
                JOptionPane.showMessageDialog(MemberWarnetApp.this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static class Member {
        private String id;
        private String name;
        private String username;
        private String phone;

        public Member(String id, String name, String username, String phone) {
            this.id = id;
            this.name = name;
            this.username = username;
            this.phone = phone;
        }

        public String getId() { return id; }
        public String getName() { return name; }
        public String getUsername() { return username; }
        public String getPhone() { return phone; }
    }

    private static class MemberTableModel extends AbstractTableModel {
        private final String[] columnNames = {"ID Member", "Nama Lengkap", "Username", "Nomor Telepon"};
        private final List<Member> members = new ArrayList<>();

        @Override
        public int getRowCount() {
            return members.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Member member = members.get(rowIndex);
            switch (columnIndex) {
                case 0: return member.getId();
                case 1: return member.getName();
                case 2: return member.getUsername();
                case 3: return member.getPhone();
                default: return null;
            }
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        public void addMember(Member member) {
            members.add(member);
            fireTableRowsInserted(members.size() - 1, members.size() - 1);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MemberWarnetApp app = new MemberWarnetApp();
            app.setVisible(true);
        });
    }
}
