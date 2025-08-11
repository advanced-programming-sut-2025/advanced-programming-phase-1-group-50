package com.stardew.repository;

import com.stardew.model.gameApp.SecurityQuestion;
import com.stardew.model.userInfo.Gender;
import com.stardew.model.userInfo.User;
import org.sqlite.SQLiteErrorCode;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static UserDAO instance;
    private final DataSource dataSource;

    private UserDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO(DatabaseManager.getInstance().getDataSource());
        }
        return instance;
    }

    public boolean insertUser(User user) throws SQLException {
        String sql = "INSERT INTO users(username, password_hash, nickname, email, gender, security_question, security_answer) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPasswordHash());
            preparedStatement.setString(3, user.getNickname());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getGender().toString());
            preparedStatement.setString(6, user.getSecurityQuestion().getQuestion());
            preparedStatement.setString(7, user.getSecurityQuestion().getAnswer());
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException ex) {
            if (ex.getErrorCode() == SQLiteErrorCode.SQLITE_CONSTRAINT.code) {
                return false;
            }
            throw ex;
        }
    }

    public boolean checkIfUserExists(String username) throws SQLException {
        String sql = "SELECT 1 FROM users WHERE username = ? LIMIT 1";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }
        }
        return false;
    }

    public User findByUsername(String username) throws SQLException {
        String sql = "SELECT username, password_hash, nickname, email, gender, security_question, security_answer FROM users WHERE username = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                        resultSet.getString("username"),
                        resultSet.getString("password_hash"),
                        resultSet.getString("nickname"),
                        resultSet.getString("email"),
                        Gender.valueOf(resultSet.getString("gender")),
                        new SecurityQuestion(
                            resultSet.getString("security_question"),
                            resultSet.getString("security_answer"))
                    );
                }
            }
        }
        return null;
    }

    public void updatePasswordHash(String username, String newPasswordHash) throws SQLException {
        String sql = "UPDATE users SET password_hash = ? WHERE username = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newPasswordHash);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        }
    }

    public void updateUsername(String username, String newUsername) throws SQLException {
        String sql = "UPDATE users SET username = ? WHERE username = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newUsername);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        }
    }

    public void updateEmail(String username, String newEmail) throws SQLException {
        String sql = "UPDATE users SET email = ? WHERE username = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newEmail);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        }
    }

    public void updateNickname(String username, String newNickname) throws SQLException {
        String sql = "UPDATE users SET nickname = ? WHERE username = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newNickname);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        }
    }
}
