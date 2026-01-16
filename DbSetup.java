import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class DbSetup {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "arpon007");

        try (Connection conn = DriverManager.getConnection(url, props);
                Statement stmt = conn.createStatement()) {

            String[] dbs = { "netflix_auth", "netflix_rating" };

            for (String db : dbs) {
                try {
                    stmt.executeUpdate("CREATE DATABASE " + db);
                    System.out.println("Created database: " + db);
                } catch (Exception e) {
                    if (e.getMessage().contains("already exists")) {
                        System.out.println("Database already exists: " + db);
                    } else {
                        System.err.println("Failed to create " + db + ": " + e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
