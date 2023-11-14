import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class search extends JFrame {
    private JTable jTableSearchResults;
    private DefaultTableModel searchModel;

    public search(DefaultTableModel searchModel) {
        this.searchModel = searchModel;
        initComponents();
    }

    private void initComponents() {
        jTableSearchResults = new JTable(searchModel);
        JScrollPane jScrollPane = new JScrollPane(jTableSearchResults);

        JButton editButton = new JButton("Sửa");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editButtonActionPerformed();
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(editButton))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jScrollPane, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(editButton))
        );
        pack();
        setLocationRelativeTo(null);
    }

   private void editButtonActionPerformed() {
    int selectedRow = jTableSearchResults.getSelectedRow();
    if (selectedRow != -1) {
        String maBenhNhan = (String) jTableSearchResults.getValueAt(selectedRow, 0);
        String hoTen = (String) jTableSearchResults.getValueAt(selectedRow, 1);
        String gioiTinh = (String) jTableSearchResults.getValueAt(selectedRow, 2);
        String ngaySinh = (String) jTableSearchResults.getValueAt(selectedRow, 3);
        String diaChi = (String) jTableSearchResults.getValueAt(selectedRow, 4);
        String sdt = (String) jTableSearchResults.getValueAt(selectedRow, 5);
        String baoHiemYTe = (String) jTableSearchResults.getValueAt(selectedRow, 6);

        // Gọi hàm sửa thông tin bệnh nhân
        editBenhNhan(maBenhNhan, hoTen, gioiTinh, ngaySinh, diaChi, sdt, baoHiemYTe);

        // Cập nhật dữ liệu trong model và hiển thị lên table
        searchModel.setValueAt(hoTen, selectedRow, 1);
        searchModel.setValueAt(gioiTinh, selectedRow, 2);
        searchModel.setValueAt(ngaySinh, selectedRow, 3);
        searchModel.setValueAt(diaChi, selectedRow, 4);
        searchModel.setValueAt(sdt, selectedRow, 5);
        searchModel.setValueAt(baoHiemYTe, selectedRow, 6);
    }
}


    private void editBenhNhan(String maBenhNhan, String hoTen, String gioiTinh, String ngaySinh, String diaChi, String sdt, String baoHiemYTe) {
        try (Connection connection = connect()) {
            if (connection == null) {
                return;
            }

            String query = "UPDATE BenhNhan SET HoTen = ?, GioiTinh = ?, NgaySinh = ?, DiaChi = ?, SDT = ?, BaoHiemYTe = ? WHERE MaBenhNhan = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, hoTen);
                preparedStatement.setString(2, gioiTinh);
                preparedStatement.setString(3, ngaySinh);
                preparedStatement.setString(4, diaChi);
                preparedStatement.setString(5, sdt);
                preparedStatement.setString(6, baoHiemYTe);
                preparedStatement.setString(7, maBenhNhan);
                preparedStatement.executeUpdate();
              int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                 JOptionPane.showMessageDialog(this, "Sửa thành công");
            } else {
                 JOptionPane.showMessageDialog(this, "Sửa thất bại");
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     private static Connection connect() {
        try {
            String jdbcURL = "jdbc:mysql://localhost:3306/Hospital";
            String username = "root";
            String password = "root";
            return DriverManager.getConnection(jdbcURL, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
