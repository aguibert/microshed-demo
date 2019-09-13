package org.aguibert.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class TCDemoIT {
    
    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>()
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");
    
    @Test
    public void testWithPostgreSQL() throws Exception {
        try (Connection conn = postgres.createConnection("")){
            conn.prepareStatement("CREATE TABLE IF NOT EXISTS people (id bigint, name text, age integer)")
                .execute();
            conn.prepareStatement("INSERT INTO people VALUES (1, 'Andy', 26)").execute();

            ResultSet rs = conn.prepareStatement("SELECT * FROM people").executeQuery();
            rs.next();
            assertEquals("Andy", rs.getString(2));
        }
    }
}

