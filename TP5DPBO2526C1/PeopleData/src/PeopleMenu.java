import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PeopleMenu extends JFrame {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JTextField idField;
    private JTextField namaField;
    private JTextField tahunLahirField;
    private JComboBox<String> kategoriComboBox;
    private JComboBox<String> kotaComboBox;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JButton deleteButton;
    private JTable peopleTable;

    private Database database;
    private int selectedIndex = -1;

    public static void main(String[] args) {
        PeopleMenu menu = new PeopleMenu();
        menu.setSize(900, 600);
        menu.setLocationRelativeTo(null);
        menu.setContentPane(menu.mainPanel);
        menu.getContentPane().setBackground(Color.WHITE);
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public PeopleMenu() {
        database = new Database();
        peopleTable.setModel(setTable());

        titleLabel.setText("Data Orang");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        idField.setBorder(BorderFactory.createTitledBorder("ID"));
        namaField.setBorder(BorderFactory.createTitledBorder("Nama"));
        tahunLahirField.setBorder(BorderFactory.createTitledBorder("Tahun Lahir"));

        String[] kategoriData = {"Category", "Mahasiswa", "Dosen", "Staf", "Alumni"};
        kategoriComboBox.setModel(new DefaultComboBoxModel<>(kategoriData));

        String[] kotaData = {"CITY", "Bandung", "Jakarta", "Surabaya", "Yogyakarta"};
        kotaComboBox.setModel(new DefaultComboBoxModel<>(kotaData));

        clearForm();

        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Apakah Anda yakin ingin menghapus data ini?",
                        "Konfirmasi Hapus",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    deleteData();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        peopleTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedIndex = peopleTable.getSelectedRow();
                if (selectedIndex == -1) return;

                String curId = peopleTable.getModel().getValueAt(selectedIndex, 0).toString();
                String curNama = peopleTable.getModel().getValueAt(selectedIndex, 1).toString();
                String curTahun = peopleTable.getModel().getValueAt(selectedIndex, 2).toString();
                String curKategori = peopleTable.getModel().getValueAt(selectedIndex, 3).toString();
                String curKota = peopleTable.getModel().getValueAt(selectedIndex, 4).toString();

                idField.setText(curId);
                namaField.setText(curNama);
                tahunLahirField.setText(curTahun);
                kategoriComboBox.setSelectedItem(curKategori);
                kotaComboBox.setSelectedItem(curKota);

                idField.setEditable(false);
                addUpdateButton.setText("Update");
                deleteButton.setVisible(true);
            }
        });
    }

    public final DefaultTableModel setTable() {
        Object[] cols = {"ID", "Nama", "Tahun Lahir", "Kategori", "Kota"};
        DefaultTableModel tmp = new DefaultTableModel(null, cols) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try {
            ResultSet resultSet = database.selectQuery("SELECT * FROM person");
            while (resultSet.next()) {
                Object[] row = new Object[5];
                row[0] = resultSet.getString("id");
                row[1] = resultSet.getString("nama");
                row[2] = resultSet.getString("tahun_lahir");
                row[3] = resultSet.getString("kategori");
                row[4] = resultSet.getString("kota");
                tmp.addRow(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tmp;
    }

    private String esc(String s) {
        return s.replace("'", "''");
    }

    private boolean isFormIncomplete() {
        return idField.getText().trim().isEmpty()
                || namaField.getText().trim().isEmpty()
                || tahunLahirField.getText().trim().isEmpty()
                || kategoriComboBox.getSelectedIndex() == 0
                || kotaComboBox.getSelectedIndex() == 0;
    }

    public void insertData() {
        if (isFormIncomplete()) {
            JOptionPane.showMessageDialog(null, "Semua kolom harus diisi!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String id = idField.getText().trim();
            String nama = namaField.getText().trim();
            int tahun = Integer.parseInt(tahunLahirField.getText().trim());
            String kategori = kategoriComboBox.getSelectedItem().toString();
            String kota = kotaComboBox.getSelectedItem().toString();

            ResultSet check = database.selectQuery(
                    "SELECT id FROM person WHERE id = '" + esc(id) + "'");
            if (check.next()) {
                JOptionPane.showMessageDialog(null, "ID sudah digunakan!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String sqlQuery = "INSERT INTO person (id, nama, tahun_lahir, kategori, kota) VALUES ('"
                    + esc(id) + "', '" + esc(nama) + "', " + tahun + ", '"
                    + esc(kategori) + "', '" + esc(kota) + "')";
            database.insertUpdateDeleteQuery(sqlQuery);

            peopleTable.setModel(setTable());
            clearForm();

            System.out.println("Insert berhasil");
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Tahun lahir harus berupa angka!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void updateData() {
        if (isFormIncomplete()) {
            JOptionPane.showMessageDialog(null, "Semua kolom harus diisi!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String id = idField.getText().trim();
            String nama = namaField.getText().trim();
            int tahun = Integer.parseInt(tahunLahirField.getText().trim());
            String kategori = kategoriComboBox.getSelectedItem().toString();
            String kota = kotaComboBox.getSelectedItem().toString();

            String sqlQuery = "UPDATE person SET nama = '" + esc(nama)
                    + "', tahun_lahir = " + tahun
                    + ", kategori = '" + esc(kategori)
                    + "', kota = '" + esc(kota)
                    + "' WHERE id = '" + esc(id) + "'";
            database.insertUpdateDeleteQuery(sqlQuery);

            peopleTable.setModel(setTable());
            clearForm();

            System.out.println("Update berhasil");
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Tahun lahir harus berupa angka!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteData() {
        String id = idField.getText().trim();
        database.insertUpdateDeleteQuery("DELETE FROM person WHERE id = '" + esc(id) + "'");

        peopleTable.setModel(setTable());
        clearForm();

        System.out.println("Delete berhasil");
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
    }

    public void clearForm() {
        idField.setText("");
        namaField.setText("");
        tahunLahirField.setText("");
        kategoriComboBox.setSelectedIndex(0);
        kotaComboBox.setSelectedIndex(0);
        idField.setEditable(true);
        addUpdateButton.setText("Add");
        deleteButton.setVisible(false);
        selectedIndex = -1;
        peopleTable.clearSelection();
    }
}