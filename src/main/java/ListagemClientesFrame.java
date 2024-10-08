package src.main.java;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListagemClientesFrame extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    // Cria a tabela de listagem de clientes

    public ListagemClientesFrame(List<Cliente> clientes) {
        setTitle("Listagem de Clientes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"Nome", "CPF", "Telefone", "Escola", "Valor"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Adicionar cada cliente à tabela
        for (Cliente cliente : clientes) {
            String[] rowData = {cliente.getNome(), cliente.getCpf(), cliente.getTelefone(), cliente.getEscola(), Double.toString(cliente.getValor())};
            tableModel.addRow(rowData);
        }
    }
}
