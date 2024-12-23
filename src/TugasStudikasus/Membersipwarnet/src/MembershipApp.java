import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MembershipApp extends JFrame {
    private JTextField txtName, txtEmail, txtPhone, txtMembershipDate, txtStatus;
    private JTable table;
    private DefaultTableModel model;

    public MembershipApp() {
        // Setup Frame
        setTitle("Membership Warnet");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        formPanel.add(new JLabel("Name:"));
        txtName = new JTextField();
        formPanel.add(txtName);

        formPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);

        formPanel.add(new JLabel("Phone:"));
        txtPhone = new JTextField();
        formPanel.add(txtPhone);

        formPanel.add(new JLabel("Membership Date (YYYY-MM-DD):"));
        txtMembershipDate = new JTextField();
        formPanel.add(txtMembershipDate);

        formPanel.add(new JLabel("Status:"));
        txtStatus = new JTextField();
        formPanel.add(txtStatus);

        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnRefresh = new JButton("Refresh");

        formPanel.add(btnAdd);
        formPanel.add(btnUpdate);
        formPanel.add(btnDelete);
        formPanel.add(btnRefresh);

        add(formPanel, BorderLayout.NORTH);

        // Table Panel
        model = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Phone", "Membership Date", "Status"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Load Data
        loadData();

        // Button Actions
        btnAdd.addActionListener(e -> addMember());
        btnUpdate.addActionListener(e -> updateMember());
        btnDelete.addActionListener(e -> deleteMember());
        btnRefresh.addActionListener(e -> loadData());
    }

    private void loadData() {
        model.setRowCount(0);
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM members")) {
            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("membership_date"),
                        resultSet.getString("status")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addMember() {
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String membershipDate = txtMembershipDate.getText();
        String status = txtStatus.getText();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || membershipDate.isEmpty() || status.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled out.");
            return;
        }

        String sql = "INSERT INTO members (name, email, phone, membership_date, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, membershipDate);
            preparedStatement.setString(5, status);
            preparedStatement.executeUpdate();
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateMember() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to update.");
            return;
        }

        int id = (int) model.getValueAt(selectedRow, 0);
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String membershipDate = txtMembershipDate.getText();
        String status = txtStatus.getText();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || membershipDate.isEmpty() || status.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled out.");
            return;
        }

        String sql = "UPDATE members SET name = ?, email = ?, phone = ?, membership_date = ?, status = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, membershipDate);
            preparedStatement.setString(5, status);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteMember() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            return;
        }

        int id = (int) model.getValueAt(selectedRow, 0);

        String sql = "DELETE FROM members WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            String url = "jdbc:mysql://localhost:3306/membershipwarnet";
            return DriverManager.getConnection(url, "root", "");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MembershipApp().setVisible(true));
    }
}