import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.DefaultListModel;

public class GerenciadorDeTarefas {
    private JFrame janela;
    private DefaultListModel<String> listaTarefasModel;
    private JList<String> listaTarefas;
    private JTextField campoTarefa;
    private JComboBox<String> campoCategoria;

    public GerenciadorDeTarefas() {
        // Inicialização da janela
        janela = new JFrame("Gerenciamento de Tarefas");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(400, 300);

        // Modelo para a lista de tarefas
        listaTarefasModel = new DefaultListModel<>();
        listaTarefas = new JList<>(listaTarefasModel);
        JScrollPane scrollPane = new JScrollPane(listaTarefas);
        janela.add(scrollPane, BorderLayout.CENTER);

        // Campo de texto para adicionar tarefas
        campoTarefa = new JTextField();
        janela.add(campoTarefa, BorderLayout.NORTH);

        // Painel de botões
        JPanel botoesPanel = new JPanel();

        // Botão "Adicionar"
        JButton botaoAdicionar = new JButton("Adicionar");
        botaoAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarTarefa();
            }
        });
        botoesPanel.add(botaoAdicionar);

        // Botão "Remover"
        JButton botaoRemover = new JButton("Remover");
        botaoRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excluirTarefa();
            }
        });
        botoesPanel.add(botaoRemover);

        janela.add(botoesPanel, BorderLayout.SOUTH);

        // Campo de seleção (ComboBox) para categorias
        String[] categorias = {"Trabalho", "Estudo", "Lazer", "Outros"};
        campoCategoria = new JComboBox<>(categorias);
        janela.add(campoCategoria, BorderLayout.EAST);

        // Tratamento de evento de clique duplo para remover tarefas
        listaTarefas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    excluirTarefa();
                }
            }
        });

        // Tornar a janela visível
        janela.setVisible(true);
    }

    // Adicionar uma tarefa à lista
    private void adicionarTarefa() {
        String tarefa = campoTarefa.getText().trim();
        if (!tarefa.isEmpty()) {
            String categoria = (String) campoCategoria.getSelectedItem();
            listaTarefasModel.addElement(tarefa + " - " + categoria);
            campoTarefa.setText("");
        }
    }

    // Excluir a tarefa selecionada
    private void excluirTarefa() {
        int selectedIndex = listaTarefas.getSelectedIndex();
        if (selectedIndex != -1) {
            listaTarefasModel.remove(selectedIndex);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GerenciadorDeTarefas());
    }
}
