import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class transaction {

    public int createPrescription(String maDonThuoc, String maHoaDon, String maThuoc, int soLuong, double gia) {
        Connection connection = null;
        try {
            connection = connect();

            // Bắt đầu giao dịch
            connection.setAutoCommit(false);

            // Thực hiện thêm đơn thuốc vào CSDL và nhận về ID của đơn thuốc mới
            int newPrescriptionId = addPrescriptionToDatabase(connection, maHoaDon, maDonThuoc, maThuoc, soLuong, gia);

            // Commit transaction nếu mọi thứ thành công
            connection.commit();

            return newPrescriptionId;
        } catch (SQLException e) {
            // Rollback transaction nếu có lỗi
            rollback(connection);
            e.printStackTrace();
            return -1; // Hoặc mã lỗi khác nếu cần
        } finally {
            // Đóng kết nối sau khi sử dụng
            closeConnection(connection);
        }
    }

   private int addPrescriptionToDatabase(Connection connection, String maHoaDon, String maDonThuoc, String maThuoc, int soLuong, double gia) throws SQLException {
    int newPrescriptionId = -1;

    // Check if the specified MaThuoc exists in the Thuoc table
    if (isMaThuocValid(connection, maThuoc)) {
        String insertPrescriptionSQL = "INSERT INTO DonThuoc (MaDonThuoc, SoLuong, Gia, MaHoaDon, MaThuoc) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertPrescriptionSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, maDonThuoc);
            preparedStatement.setInt(2, soLuong);
            preparedStatement.setDouble(3, gia);
            preparedStatement.setString(4, maHoaDon);
            preparedStatement.setString(5, maThuoc);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                newPrescriptionId = getGeneratedKey(preparedStatement);
            }
        }
    } else {
        System.out.println("Invalid MaThuoc. Please check the Thuoc table.");
    }

    return newPrescriptionId;
}

private boolean isMaThuocValid(Connection connection, String maThuoc) throws SQLException {
    String checkMaThuocSQL = "SELECT COUNT(*) FROM Thuoc WHERE MaThuoc = ?";

    try (PreparedStatement preparedStatement = connection.prepareStatement(checkMaThuocSQL)) {
        preparedStatement.setString(1, maThuoc);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        }
    }

    return false;
}

    private void updateQuantity(Connection connection, String maThuoc, int soLuong) throws SQLException {
        String updateQuantitySQL = "UPDATE Thuoc SET SoLuong = SoLuong - ? WHERE MaThuoc = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuantitySQL)) {
            preparedStatement.setInt(1, soLuong);
            preparedStatement.setString(2, maThuoc);

            preparedStatement.executeUpdate();
        }
    }

    private int getGeneratedKey(PreparedStatement preparedStatement) throws SQLException {
        int newId = -1;
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            newId = generatedKeys.getInt(1);
        }
        return newId;
    }

    // Các phương thức hỗ trợ khác

    private Connection connect() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/Hospital";
        String username = "root";
        String password = "root";
        return DriverManager.getConnection(jdbcURL, username, password);
    }

    private void rollback(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
