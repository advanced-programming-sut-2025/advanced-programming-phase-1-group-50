package com.stardew.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DatabaseManager {
    private static DatabaseManager instance;
    private final HikariDataSource dataSource;


    private DatabaseManager(String dbPath) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlite:" + dbPath);
        config.setMaximumPoolSize(3);
        config.setAutoCommit(true);

        this.dataSource = new HikariDataSource(config);
    }

    public static synchronized void initialize(String dbPath) {
        if (instance == null) {
            instance = new DatabaseManager(dbPath);
        }
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) throw new IllegalStateException("DatabaseManager not initialized");
        return instance;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }

}
