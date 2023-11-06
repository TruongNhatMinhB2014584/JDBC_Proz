import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class UserReg {
    public boolean authenticateReg(String username, String password, String sdt) {
    String jdbcURL = "jdbc:mysql://localhost:3306/Hospital";
    String dbUser = "root";
    String dbPassword = "root";
    
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
        connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

        // Kiểm tra xem username đã tồn tại trong cơ sở dữ liệu hay chưa
        String checkUserQuery = "SELECT * FROM users WHERE username = ?";
        preparedStatement = connection.prepareStatement(checkUserQuery);
        preparedStatement.setString(1, username);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            // Nếu username đã tồn tại, trả về false
            return false;
        } else {
            // Nếu username chưa tồn tại, thực hiện lệnh SQL để thêm người dùng mới
            String insertUserQuery = "INSERT INTO users (username, password, sdt) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertUserQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, sdt);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                // Đăng ký thành công, trả về true
                return true;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Đảm bảo đóng kết nối và tài nguyên
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Nếu có lỗi xảy ra hoặc không thêm dữ liệu thành công, trả về false
    return false;
    }
    
    public boolean validatePhoneNumber(String phoneNumber) {
    // Loại bỏ các ký tự không phải là số
    phoneNumber = phoneNumber.replaceAll("[^0-9]", "");
    
    // Kiểm tra xem số điện thoại có đúng 10 chữ số không
    return phoneNumber.length() == 10;
    }

}
