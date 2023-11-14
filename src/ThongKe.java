import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.CallableStatement;
import java.sql.Types;


public class ThongKe extends JFrame {
    private JTextField startDateField;
    private JTextField endDateField;


    public ThongKe() {
        // Xây dựng GUI
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        JLabel startDateLabel = new JLabel("Ngày bắt đầu (yyyy-MM-dd):");
        startDateField = new JTextField(10);
        JLabel endDateLabel = new JLabel("Ngày kết thúc (yyyy-MM-dd):");
        endDateField = new JTextField(10);

        JButton statisticButton = new JButton("Thống kê thu nhập");
        JButton countButton = new JButton("Đếm hóa đơn");

        panel.add(startDateLabel);
        panel.add(startDateField);
        panel.add(endDateLabel);
        panel.add(endDateField);
        panel.add(statisticButton);
        panel.add(countButton);

        setLocationRelativeTo(null);
        statisticButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performStatistic();
            }
        });

        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performCount();
            }
        });

        getContentPane().add(panel);
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
private static double tongThuNhap(String startDate, String endDate) {
    try (Connection connection = connect()) {
        if (connection == null) {
            return -1.0; // Giá trị âm có thể được sử dụng để biểu thị lỗi kết nối
        }

        String query = "SELECT tongThuNhap(?, ?) AS totalAmount";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, startDate);
            preparedStatement.setString(2, endDate);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("totalAmount");
            } else {
                return -1.0; // Hoặc giá trị mặc định khác nếu không có dữ liệu trả về
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return -1.0; // Giá trị âm có thể được sử dụng để biểu thị lỗi kết nối hoặc lỗi thực hiện thống kê tổng thu nhập
    }
}


private static int countHoaDon(String startDate, String endDate) {
    try (Connection connection = connect()) {
        if (connection == null) {
            return -1; // Giá trị âm có thể được sử dụng để biểu thị lỗi kết nối
        }

        String query = "SELECT CountHoaDon(?, ?) AS totalHoaDon";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, startDate);
            preparedStatement.setString(2, endDate);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("totalHoaDon");
            } else {
                return -1; // Hoặc giá trị mặc định khác nếu không có dữ liệu trả về
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return -1; // Giá trị âm có thể được sử dụng để biểu thị lỗi kết nối
    }
}


    private void performStatistic() {
        // Lấy ngày bắt đầu và ngày kết thúc từ GUI
        String startDateString = startDateField.getText();
        String endDateString = endDateField.getText();

        // Gọi hàm thống kê từ lớp DatabaseFunctions và hiển thị kết quả
        double result = tongThuNhap(startDateString, endDateString);
        JOptionPane.showMessageDialog(this, result, "Kết quả thống kê", JOptionPane.INFORMATION_MESSAGE);
    }

    private void performCount() {
        // Lấy ngày bắt đầu và ngày kết thúc từ GUI
        String startDateString = startDateField.getText();
        String endDateString = endDateField.getText();

        try {
            // Chuyển đổi chuỗi thành đối tượng Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(startDateString);
            Date endDate = dateFormat.parse(endDateString);

            // Gọi hàm đếm từ lớp DatabaseFunctions và hiển thị kết quả
            int count = countHoaDon(startDateString, endDateString);
            JOptionPane.showMessageDialog(this, "Số lượng hóa đơn trong khoảng thời gian: " + count,
                    "Kết quả đếm", JOptionPane.INFORMATION_MESSAGE);
        } catch (ParseException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ngày không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ThongKe().setVisible(true);
            }
        });
    }
}
