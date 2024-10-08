package src.main.java;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class Database {
    private static final String URL = "****";
    private static final String USER = "****";
    private static final String PASSWORD = "****";

    static {
        try {
            // Carrega o driver do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean cpfExists(String cpf) throws SQLException {
        String query = "SELECT COUNT(*) FROM clientes24 WHERE cpf = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public static void inserirCliente(String cpf, String nome, String telefone, String escola, double valor) throws SQLException {
        String query = "INSERT INTO clientes24 (cpf, nome, telefone, escola, valor) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cpf);
            stmt.setString(2, nome);
            stmt.setString(3, telefone);
            stmt.setString(4, escola);
            stmt.setDouble(5, valor);
            stmt.executeUpdate();
        }
    }

    
    

    public static void alterarCliente(String cpf, String novoNome, String novoTelefone, String novaEscola, double novoValor) throws SQLException {
        String query = "UPDATE clientes24 SET nome = ?, telefone = ?, escola = ?, valor =? WHERE cpf = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, novoNome);
            stmt.setString(2, novoTelefone);
            stmt.setString(3, novaEscola);
            stmt.setDouble(4, novoValor);
            stmt.setString(5, cpf);
            stmt.executeUpdate();
        }
    }

    public static void deletarCliente(String cpf) throws SQLException {
        String query = "DELETE FROM clientes24 WHERE cpf = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        }
    }

    public static Cliente buscarClientePorCpf(String cpf) throws SQLException {
        String query = "SELECT * FROM clientes24 WHERE cpf = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String telefone = rs.getString("telefone");
                    String escola = rs.getString("escola");
                    double valor = rs.getDouble("valor");
                    return new Cliente(cpf, nome, telefone, escola, valor);
                }
            }
        }
        return null;
    }

    public static List<Cliente> getAllClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM clientes24 ORDER BY nome";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String cpf = rs.getString("cpf");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String escola = rs.getString("escola");
                double valor = rs.getDouble("valor");
                clientes.add(new Cliente(cpf, nome, telefone,escola,valor));
            }
        }
        return clientes;
    }

    
}
