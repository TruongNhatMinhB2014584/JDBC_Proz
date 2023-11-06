import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Userauthentication {
    public boolean authenticateUser(String username, String password) {
        String jdbcURL = "jdbc:mysql://localhost:3306/Hospital";
        String dbUser = "root";
        String dbPassword = "root";

        try {
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            String sqlQuery = "SELECT * FROM users WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            
            // Nếu có bất kỳ dòng nào khớp với username
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                // So sánh password lấy từ cơ sở dữ liệu với password được cung cấp
                if (password.equals(storedPassword)) {
                    return true; // Đăng nhập thành công
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false; // Nếu không có kết quả khớp hoặc xảy ra lỗi, từ chối truy cập
    }
}



