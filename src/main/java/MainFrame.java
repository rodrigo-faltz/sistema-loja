package src.main.java;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import javax.swing.text.MaskFormatter;
import javax.swing.table.DefaultTableModel;

//Mainframe do projeto
//Também não tem boas práticas

public class MainFrame extends JFrame {
    private JButton inserirButton;
    private JButton alterarButton;
    private JButton deletarButton;
    private JButton listarButton;
    private JButton buscarButton; 
    private JButton resumoButton;


    

    public MainFrame() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR")); // Configuração para o formato brasileiro (vírgula como separador decimal)
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        


        setTitle("Gestão de Clientes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        inserirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MaskFormatter cpfFormatter = new MaskFormatter("###.###.###-##");
                    JFormattedTextField cpfField = new JFormattedTextField(cpfFormatter);
                    cpfField.setBounds(10, 20, 255, 25);
                    
                    // Mostra uma mensagem com o campo formatado para CPF
                    JOptionPane.showMessageDialog(MainFrame.this, cpfField, "Digite o CPF para Inserir", JOptionPane.PLAIN_MESSAGE);
                    
                    // Após o usuário digitar o CPF, obtém o valor do campo formatado
                    String cpf = cpfField.getText().replaceAll("[^0-9]", ""); // Remove caracteres não numéricos
        
                    if (!cpf.isEmpty()) {
                        inserirCliente(cpf);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        

        alterarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MaskFormatter cpfFormatter = new MaskFormatter("###.###.###-##");
                    JFormattedTextField cpfField = new JFormattedTextField(cpfFormatter);
                    cpfField.setBounds(10, 20, 255, 25);
                    
                    // Mostra uma mensagem com o campo formatado para CPF
                    JOptionPane.showMessageDialog(MainFrame.this, cpfField, "Digite o CPF para Alterar", JOptionPane.PLAIN_MESSAGE);
                    
                    // Após o usuário digitar o CPF, obtém o valor do campo formatado
                    String cpf = cpfField.getText().replaceAll("[^0-9]", ""); // Remove caracteres não numéricos
        
                    if (!cpf.isEmpty()) {
                        alterarCliente(cpf);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        

        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MaskFormatter cpfFormatter = new MaskFormatter("###.###.###-##");
                    JFormattedTextField cpfField = new JFormattedTextField(cpfFormatter);
                    cpfField.setBounds(10, 20, 255, 25);
                    
                    // Mostra uma mensagem com o campo formatado para CPF
                    JOptionPane.showMessageDialog(MainFrame.this, cpfField, "Digite o CPF para Deletar", JOptionPane.PLAIN_MESSAGE);
                    
                    // Após o usuário digitar o CPF, obtém o valor do campo formatado
                    String cpf = cpfField.getText().replaceAll("[^0-9]", ""); // Remove caracteres não numéricos
        
                    if (!cpf.isEmpty()) {
                        deletarCliente(cpf);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        

        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarClientes();
            }
        });

        // Listener para o botão de buscar por CPF
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MaskFormatter cpfFormatter = new MaskFormatter("###.###.###-##");
                    JFormattedTextField cpfField = new JFormattedTextField(cpfFormatter);
                    cpfField.setBounds(10, 20, 255, 25);
                    
                    // Mostra uma mensagem com o campo formatado para CPF
                    JOptionPane.showMessageDialog(MainFrame.this, cpfField, "Buscar por CPF", JOptionPane.PLAIN_MESSAGE);
                    
                    // Após o usuário digitar o CPF, obtém o valor do campo formatado
                    String cpf = cpfField.getText().replaceAll("[^0-9]", ""); // Remove caracteres não numéricos
        
                    if (!cpf.isEmpty()) {
                        buscarClientePorCpf(cpf);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        resumoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarResumoEscolas();
            }
        });
        

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);
    
        inserirButton = new JButton("Inserir");
        inserirButton.setBounds(10, 50, 255, 25);
        panel.add(inserirButton);
    
        alterarButton = new JButton("Alterar");
        alterarButton.setBounds(10, 80, 255, 25);
        panel.add(alterarButton);
    
        deletarButton = new JButton("Deletar");
        deletarButton.setBounds(10, 110, 255, 25);
        panel.add(deletarButton);
    
        listarButton = new JButton("Listar");
        listarButton.setBounds(10, 140, 255, 25);
        panel.add(listarButton);
    
        buscarButton = new JButton("Buscar por CPF");
        buscarButton.setBounds(10, 170, 255, 25);
        panel.add(buscarButton);
        
        resumoButton = new JButton("Mostrar Resumo");
        resumoButton.setBounds(10, 200, 255, 25);
        panel.add(resumoButton);
    }

    private void buscarClientePorCpf(String cpf) {
        try {
            String query = "SELECT * FROM clientes24 WHERE cpf = ?";
            try (Connection conn = Database.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, cpf);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String nome = rs.getString("nome");
                        String telefone = rs.getString("telefone");
                        String escola = rs.getString("escola");
                        double valor = rs.getDouble("valor");
                        JOptionPane.showMessageDialog(this,
                                "Nome: " + nome + "\nCPF: " + cpf + "\nTelefone: " + telefone +"\nEscola: "+escola+"\nValor: "+valor,
                                "Informações do Cliente",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Cliente com CPF " + cpf + " não encontrado.",
                                "Cliente não encontrado",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erro ao buscar cliente por CPF: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void inserirCliente(String cpf) {
        try {
            if (!Cliente.isValidCPF(cpf)) {
                JOptionPane.showMessageDialog(this, "CPF inválido!");
                return;
            }
    
            if (Database.cpfExists(cpf)) {
                JOptionPane.showMessageDialog(this, "CPF já existe!");
                return;
            }
    
            String nome = JOptionPane.showInputDialog("Digite o nome:");
    
            JFormattedTextField telefoneField;
            try {
                telefoneField = criarCampoTelefoneFormatado();
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this,
                        "Erro ao criar campo de telefone formatado: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            JOptionPane.showMessageDialog(this, telefoneField, "Digite o telefone", JOptionPane.PLAIN_MESSAGE);
            String telefone = telefoneField.getText().replaceAll("[^0-9]", "");
    
            String escola = JOptionPane.showInputDialog("Digite a escola:");
            String escolaFormat = escola.toUpperCase();
    
            double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor:" + "\nUtilize '.' para número com vírgula"));
    
            String query = "INSERT INTO clientes24 (nome, cpf, telefone, escola, valor) VALUES (?, ?, ?, ?, ?)";
            try (Connection conn = Database.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, nome);
                stmt.setString(2, cpf);
                stmt.setString(3, telefone);
                stmt.setString(4, escolaFormat);
                stmt.setDouble(5, valor);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Cliente inserido com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erro ao inserir cliente: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private JFormattedTextField criarCampoTelefoneFormatado() throws ParseException {
        MaskFormatter telefoneFormatter = new MaskFormatter("(##) #####-####");
        JFormattedTextField telefoneField = new JFormattedTextField(telefoneFormatter);
        telefoneField.setBounds(10, 20, 255, 25);
        return telefoneField;
    }
    


    private void alterarCliente(String cpf) {
        try {
            if (!Cliente.isValidCPF(cpf)) {
                JOptionPane.showMessageDialog(this, "CPF inválido!");
                return;
            }
    
            if (!Database.cpfExists(cpf)) {
                JOptionPane.showMessageDialog(this, "CPF não encontrado!");
                return;
            }
    
            JFormattedTextField telefoneField;
            try {
                telefoneField = criarCampoTelefoneFormatado();
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this,
                        "Erro ao criar campo de telefone formatado: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            String nome = JOptionPane.showInputDialog("Digite o novo nome:");
            JOptionPane.showMessageDialog(this, telefoneField, "Digite o telefone", JOptionPane.PLAIN_MESSAGE);
            String telefone = telefoneField.getText().replaceAll("[^0-9]", "");
    
            String escola = JOptionPane.showInputDialog("Digite a nova escola:");
            String escolaFormat = escola.toUpperCase();
    
            double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o novo valor:" + "\nUtilize '.' para número com vírgula"));
    
            String query = "UPDATE clientes24 SET nome = ?, telefone = ?, escola = ?, valor = ? WHERE cpf = ?";
            try (Connection conn = Database.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, nome);
                stmt.setString(2, telefone);
                stmt.setString(3, escolaFormat);
                stmt.setDouble(4, valor);
                stmt.setString(5, cpf);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Cliente alterado com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erro ao alterar cliente: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    

    private void deletarCliente(String cpf) {
        try {
            if (!Database.cpfExists(cpf)) {
                JOptionPane.showMessageDialog(this, "CPF não encontrado!");
                return;
            }

            String query = "DELETE FROM clientes24 WHERE cpf = ?";
            try (Connection conn = Database.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, cpf);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Cliente deletado com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erro ao deletar cliente: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarClientes() {
        try {
            List<Cliente> clientes = Database.getAllClientes();
            if (clientes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Não há clientes cadastrados.");
            } else {
                // Abrir uma nova janela para listar os clientes
                ListagemClientesFrame listagemFrame = new ListagemClientesFrame(clientes);
                listagemFrame.setVisible(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erro ao listar clientes: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarResumoEscolas() {
        String query = "SELECT escola, COUNT(*) AS quantidade, SUM(valor) AS total_valor FROM clientes24 GROUP BY escola";
    
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
    
            DefaultTableModel model = new DefaultTableModel(new Object[]{"Escola", "Quantidade de Clientes", "Total Gasto"}, 0);
            while (rs.next()) {
                String escola = rs.getString("escola");
                int quantidade = rs.getInt("quantidade");
                double totalValor = rs.getDouble("total_valor");
    
                model.addRow(new Object[]{escola, quantidade, totalValor});
            }
    
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            table.setFillsViewportHeight(true);
    
            JFrame frame = new JFrame("Resumo por Escola");
            frame.setSize(600, 400);
            frame.add(scrollPane);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
    
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao obter resumo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame();
            }
        });
    }
}
