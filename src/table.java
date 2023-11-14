import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Vector;
import javax.swing.*;

public class table{
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

    public static void loadData1(DefaultTableModel model) {
        try {
            Connection connection = connect();
            if (connection == null) {
                return;
            }

            String sqlQuery = "SELECT * FROM BacSi";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            model.setColumnIdentifiers(new Object[]{"Mã bác sĩ", "Họ tên", "Giới tính", "Học vị", "Chuyên môn", "Mã khoa"});
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("MaBacSi"));
                row.add(resultSet.getString("HoTen"));
                row.add(resultSet.getString("GioiTinh"));
                row.add(resultSet.getString("HocVi"));
                row.add(resultSet.getString("ChuyenMon"));
                row.add(resultSet.getString("MaKhoa"));
                model.addRow(row);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public static void loadData2(DefaultTableModel model) {
        try {
            Connection connection = connect();
            if (connection == null) {
                return;
            }

            String sqlQuery = "SELECT * FROM BenhNhan";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            model.setColumnIdentifiers(new Object[]{"Mã bệnh nhân", "Họ tên", "Giới tính", "Ngày sinh", "Địa chỉ", "SĐT", "Bảo hiểm y tế"});
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("MaBenhNhan"));
                row.add(resultSet.getString("HoTen"));
                row.add(resultSet.getString("GioiTinh"));
                row.add(resultSet.getString("NgaySinh"));
                row.add(resultSet.getString("DiaChi"));
                row.add(resultSet.getString("SDT"));
                row.add(resultSet.getString("BaoHiemYTe"));
                model.addRow(row);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     public static void loadData3(DefaultTableModel model) {
        try {
            Connection connection = connect();
            if (connection == null) {
                return;
            }

            String sqlQuery = "SELECT * FROM donthuoc";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            model.setColumnIdentifiers(new Object[]{"Mã đơn thuốc", "số lượng", "Giá", "Mã hóa đơn", "Mã thuốc"});
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("MaDonThuoc"));
                row.add(resultSet.getString("SoLuong"));
                row.add(resultSet.getString("Gia"));
                row.add(resultSet.getString("MaHoaDon"));
                row.add(resultSet.getString("MaThuoc"));
                model.addRow(row);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
      public static void loadData4(DefaultTableModel model) {
        try {
            Connection connection = connect();
            if (connection == null) {
                return;
            }

            String sqlQuery = "SELECT * FROM thuoc";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            model.setColumnIdentifiers(new Object[]{"Mã thuốc", "Tên thuốc", "Số lượng", "Giá thuốc", "Ngày sản xuất", "Hạn sử dụng", "Mã loại thuốc"});
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("MaThuoc"));
                row.add(resultSet.getString("TenThuoc"));
                row.add(resultSet.getString("SoLuong"));
                row.add(resultSet.getString("GiaThuoc"));
                row.add(resultSet.getString("NgaySanXuat"));
                row.add(resultSet.getString("HanSuDung"));
                row.add(resultSet.getString("MaLoaiThuoc"));
                model.addRow(row);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
       public static void loadData5(DefaultTableModel model) {
        try {
            Connection connection = connect();
            if (connection == null) {
                return;
            }

            String sqlQuery = "SELECT * FROM loaithuoc";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            model.setColumnIdentifiers(new Object[]{"Mã Loại thuốc", "Tên Loại thuốc"});
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("MaLoaiThuoc"));
                row.add(resultSet.getString("TenLoaiThuoc"));

                model.addRow(row);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
       public static void loadData6(DefaultTableModel model) {
        try {
            Connection connection = connect();
            if (connection == null) {
                return;
            }

            String sqlQuery = "SELECT * FROM Khoa";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            model.setColumnIdentifiers(new Object[]{"Mã Khoa", "Tên Khoa", "Mô tả"});
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("MaKhoa"));
                row.add(resultSet.getString("TenKhoa"));
                row.add(resultSet.getString("Mota"));
                model.addRow(row);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
       public static void loadData7(DefaultTableModel model) {
        try {
            Connection connection = connect();
            if (connection == null) {
                return;
            }

            String sqlQuery = "SELECT * FROM Hoadon";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            model.setColumnIdentifiers(new Object[]{"Mã Hóa Đơn", "Tiền tạm ứng", "Phi phát sinh", "Tiền khám", "Mã Bệnh Nhân"});
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("MaHoaDon"));
                row.add(resultSet.getString("TienTamUng"));
                row.add(resultSet.getString("PhiPhatSinh"));
                row.add(resultSet.getString("TienKham"));
                row.add(resultSet.getString("MaBenhNhan"));
                model.addRow(row);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
       public static void loadData8(DefaultTableModel model) {
        try {
            Connection connection = connect();
            if (connection == null) {
                return;
            }

            String sqlQuery = "SELECT * FROM hosobenhan";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            model.setColumnIdentifiers(new Object[]{"Mã Bệnh Án", "Triệu chứng", "Phương pháp điều trị", "Kết quả điều trị", "Mô tả", "Mã bệnh nhân", "Mã bác sĩ", "Mã đơn thuốc"});
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("MaBenhAn"));
                row.add(resultSet.getString("TrieuChung"));
                row.add(resultSet.getString("PhuongPhapDieuTri"));
                row.add(resultSet.getString("KetQuaDieuTri"));
                row.add(resultSet.getString("MoTa"));
                row.add(resultSet.getString("MaBenhNhan"));
                row.add(resultSet.getString("MaBacSi"));
                row.add(resultSet.getString("MaDonThuoc"));
                model.addRow(row);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
public static void addBS(String maBacSi, String hoTen, String gioiTinh, String hocVi, String chuyenMon, String maKhoa) {
    try (Connection connection = connect()) {
        if (connection == null) {
            return;
        }

        String query = "CALL addBS(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, maBacSi);
            preparedStatement.setString(2, hoTen);
            preparedStatement.setString(3, gioiTinh);
            preparedStatement.setString(4, hocVi);
            preparedStatement.setString(5, chuyenMon);
            preparedStatement.setString(6, maKhoa);

            boolean hasResults = preparedStatement.execute();

            // Hiển thị thông báo ngay tại đây (Nếu cần)
            JOptionPane.showMessageDialog(null, "Thêm bác sĩ thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Thêm bác sĩ thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
    }
}


public static void addBN(String MaBenhNhan, String hoTen, String gioiTinh, String ngaySinh, String diaChi, String sdt, String baoHiemYTe) {
    try (Connection connection = connect()) {
        if (connection == null) {
            return;
        }

        String query = "CALL addBN(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, MaBenhNhan);
            preparedStatement.setString(2, hoTen);
            preparedStatement.setString(3, gioiTinh);
            preparedStatement.setString(4, ngaySinh);
            preparedStatement.setString(5, diaChi);
            preparedStatement.setString(6, sdt);
            preparedStatement.setString(7, baoHiemYTe);

            boolean hasResults = preparedStatement.execute();

            // Hiển thị thông báo ngay tại đây (Nếu cần)
            JOptionPane.showMessageDialog(null, "Thêm bệnh nhân thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Thêm bệnh nhân thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
    }
}

public static void addThuoc(String maThuoc, String tenThuoc, int soLuong, double giaThuoc, String ngaySanXuat, String hanSuDung, String maLoaiThuoc) {
    try (Connection connection = connect()) {
        if (connection == null) {
            return;
        }

        String query = "CALL addThuoc(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, maThuoc);
            preparedStatement.setString(2, tenThuoc);
            preparedStatement.setInt(3, soLuong);
            preparedStatement.setDouble(4, giaThuoc);
            preparedStatement.setString(5, ngaySanXuat);
            preparedStatement.setString(6, hanSuDung);
            preparedStatement.setString(7, maLoaiThuoc);

            boolean hasResults = preparedStatement.execute();

            // Hiển thị thông báo ngay tại đây (Nếu cần)
            JOptionPane.showMessageDialog(null, "Thêm thuốc thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Thêm thuốc thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
    }
}


public static void addLoaiThuoc(String maLoaiThuoc, String tenLoaiThuoc) {
    try (Connection connection = connect()) {
        if (connection == null) {
            return;
        }

        String query = "INSERT INTO loaithuoc (MaLoaiThuoc, TenLoaiThuoc) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, maLoaiThuoc);
            preparedStatement.setString(2, tenLoaiThuoc);

            int affectedRows = preparedStatement.executeUpdate();

            // Hiển thị thông báo ngay tại đây
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Thêm loại thuốc thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Thêm loại thuốc thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Thêm loại thuốc thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
    }
}

public static void addKhoa(String maKhoa, String tenKhoa, String moTa) {
    try (Connection connection = connect()) {
        if (connection == null) {
            return;
        }

        String query = "INSERT INTO khoa (MaKhoa, TenKhoa, MoTa) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, maKhoa);
            preparedStatement.setString(2, tenKhoa);
            preparedStatement.setString(3, moTa);

            int affectedRows = preparedStatement.executeUpdate();

            // Hiển thị thông báo ngay tại đây
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Thêm khoa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Thêm khoa thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Thêm khoa thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
    }
}

public static void addDT(String maDonThuoc, int soLuong, double gia, String maHoaDon, String maThuoc) {
    try (Connection connection = connect()) {
        if (connection == null) {
            return;
        }

        String query = "CALL addDonThuoc(?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, maDonThuoc);
            preparedStatement.setInt(2, soLuong);
            preparedStatement.setDouble(3, gia);
            preparedStatement.setString(4, maHoaDon);
            preparedStatement.setString(5, maThuoc);

            boolean hasResults = preparedStatement.execute();

            // Hiển thị thông báo ngay tại đây (Nếu cần)
            JOptionPane.showMessageDialog(null, "Thêm đơn thuốc thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Thêm đơn thuốc thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
    }
}

public static void addHD(String maHoaDon, double tienTamUng, double phiPhatSinh, double tienKham, String maBenhNhan) {
    try (Connection connection = connect()) {
        if (connection == null) {
            return;
        }

        String query = "CALL addHoaDon(?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, maHoaDon);
            preparedStatement.setDouble(2, tienTamUng);
            preparedStatement.setDouble(3, phiPhatSinh);
            preparedStatement.setDouble(4, tienKham);
            preparedStatement.setString(5, maBenhNhan);

            boolean hasResults = preparedStatement.execute();

            // Hiển thị thông báo ngay tại đây (Nếu cần)
            JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Thêm hóa đơn thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
    }
}

public static void addBA(String maBenhAn, String trieuChung, String phuongPhapDieuTri, String ketQuaDieuTri, String moTa, String maBenhNhan, String maBacSi, String maDonThuoc) {
    try (Connection connection = connect()) {
        if (connection == null) {
            return;
        }

        String query = "CALL addHoSoBenhAn(?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, maBenhAn);
            preparedStatement.setString(2, trieuChung);
            preparedStatement.setString(3, phuongPhapDieuTri);
            preparedStatement.setString(4, ketQuaDieuTri);
            preparedStatement.setString(5, moTa);
            preparedStatement.setString(6, maBenhNhan);
            preparedStatement.setString(7, maBacSi);
            preparedStatement.setString(8, maDonThuoc);

            boolean hasResults = preparedStatement.execute();

            // Hiển thị thông báo ngay tại đây (Nếu cần)
            JOptionPane.showMessageDialog(null, "Thêm hồ sơ bệnh án thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Thêm hồ sơ bệnh án thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
    }
}


public static void findBS(DefaultTableModel model, String maBacSi) {
    try {
        Connection connection = connect();
        if (connection == null) {
            return;
        }

        String sqlQuery = "SELECT * FROM BacSi WHERE MaBacSi = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, maBacSi);

            ResultSet resultSet = preparedStatement.executeQuery();

            model.setColumnIdentifiers(new Object[]{"Mã bác sĩ", "Họ tên", "Giới tính", "Học vị", "Chuyên môn", "Mã khoa"});
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("MaBacSi"));
                row.add(resultSet.getString("HoTen"));
                row.add(resultSet.getString("GioiTinh"));
                row.add(resultSet.getString("HocVi"));
                row.add(resultSet.getString("ChuyenMon"));
                row.add(resultSet.getString("MaKhoa"));
                model.addRow(row);
            }

            resultSet.close();
        }

        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    
     public static void findBN(DefaultTableModel model, String maBenhNhan) {
    try {
        Connection connection = connect();
        if (connection == null) {
            return;
        }

        String sqlQuery = "SELECT * FROM BenhNhan WHERE MaBenhNhan = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1,maBenhNhan);

            ResultSet resultSet = preparedStatement.executeQuery();

            model.setColumnIdentifiers(new Object[]{"Mã bệnh nhân", "Họ tên", "Giới tính", "Ngày sinh", "Địa chỉ", "SĐT", "Bảo hiểm y tế"});
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("MaBenhNhan"));
                row.add(resultSet.getString("HoTen"));
                row.add(resultSet.getString("GioiTinh"));
                row.add(resultSet.getString("NgaySinh"));
                row.add(resultSet.getString("DiaChi"));
                row.add(resultSet.getString("SDT"));
                row.add(resultSet.getString("BaoHiemYTe"));
                model.addRow(row);
            }

            resultSet.close();
        }

        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
     
     public static void findDT(DefaultTableModel model, String maDonThuoc) {
    try {
        Connection connection = connect();
        if (connection == null) {
            return;
        }

        String sqlQuery = "SELECT * FROM donthuoc WHERE MaDonThuoc = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, maDonThuoc);

            ResultSet resultSet = preparedStatement.executeQuery();

            model.setColumnIdentifiers(new Object[]{"Mã đơn thuốc", "số lượng", "Giá", "Mã hóa đơn", "Mã thuốc"});
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("MaDonThuoc"));
                row.add(resultSet.getString("SoLuong"));
                row.add(resultSet.getString("Gia"));
                row.add(resultSet.getString("MaHoaDon"));
                row.add(resultSet.getString("MaThuoc"));
                model.addRow(row);
            }

            resultSet.close();
        }

        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
     
     public static void findT(DefaultTableModel model, String maThuoc) {
    try {
        Connection connection = connect();
        if (connection == null) {
            return;
        }

        String sqlQuery = "SELECT * FROM thuoc WHERE MaThuoc = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, maThuoc);

            ResultSet resultSet = preparedStatement.executeQuery();

            model.setColumnIdentifiers(new Object[]{"Mã thuốc", "Tên thuốc", "Số lượng", "Giá thuốc", "Ngày sản xuất", "Hạn sử dụng", "Mã loại thuốc"});
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("MaThuoc"));
                row.add(resultSet.getString("TenThuoc"));
                row.add(resultSet.getString("SoLuong"));
                row.add(resultSet.getString("GiaThuoc"));
                row.add(resultSet.getString("NgaySanXuat"));
                row.add(resultSet.getString("HanSuDung"));
                row.add(resultSet.getString("MaLoaiThuoc"));
                model.addRow(row);
            }

            resultSet.close();
        }

        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    public static void findHD(DefaultTableModel model, String maHoaDon) {
    try {
        Connection connection = connect();
        if (connection == null) {
            return;
        }

        String sqlQuery = "SELECT * FROM Hoadon WHERE MaHoaDon = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, maHoaDon);

            ResultSet resultSet = preparedStatement.executeQuery();

            model.setColumnIdentifiers(new Object[]{"Mã Hóa Đơn", "Tiền tạm ứng", "Phi phát sinh", "Tiền khám", "Mã Bệnh Nhân"});
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("MaHoaDon"));
                row.add(resultSet.getString("TienTamUng"));
                row.add(resultSet.getString("PhiPhatSinh"));
                row.add(resultSet.getString("TienKham"));
                row.add(resultSet.getString("MaBenhNhan"));
                model.addRow(row);
            }

            resultSet.close();
        }

        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
       public static void findBA(DefaultTableModel model, String maBenhAn) {
    try {
        Connection connection = connect();
        if (connection == null) {
            return;
        }

        String sqlQuery = "SELECT * FROM hosobenhan WHERE MaBenhAn = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1,maBenhAn);

            ResultSet resultSet = preparedStatement.executeQuery();

            model.setColumnIdentifiers(new Object[]{"Mã Bệnh Án", "Triệu chứng", "Phương pháp điều trị", "Kết quả điều trị", "Mô tả", "Mã bệnh nhân", "Mã bác sĩ", "Mã đơn thuốc"});
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("MaBenhAn"));
                row.add(resultSet.getString("TrieuChung"));
                row.add(resultSet.getString("PhuongPhapDieuTri"));
                row.add(resultSet.getString("KetQuaDieuTri"));
                row.add(resultSet.getString("MoTa"));
                row.add(resultSet.getString("MaBenhNhan"));
                row.add(resultSet.getString("MaBacSi"));
                row.add(resultSet.getString("MaDonThuoc"));
                model.addRow(row);
            }

            resultSet.close();
        }

        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    
}
       
       public static void deleteBN(String maBenhNhan) {
    try {
        Connection connection = connect();
        if (connection == null) {
            return;
        }

        String sqlQuery = "DELETE FROM benhnhan WHERE MaBenhNhan = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, maBenhNhan);
            preparedStatement.executeUpdate();
        }

        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
       
         public static void deleteBS(String maBacSi) {
    try {
        Connection connection = connect();
        if (connection == null) {
            return;
        }

        String sqlQuery = "DELETE FROM bacsi WHERE MaBacSi = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, maBacSi);
            preparedStatement.executeUpdate();
        }

        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
         
            public static void deleteT(String thuoc) {
    try {
        Connection connection = connect();
        if (connection == null) {
            return;
        }

        String sqlQuery = "DELETE FROM thuoc WHERE Mathuoc = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, thuoc);
            preparedStatement.executeUpdate();
        }

        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
            
}
