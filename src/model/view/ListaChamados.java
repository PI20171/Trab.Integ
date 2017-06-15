package model.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.chamado.DadosChamado;
import model.dao.DadosSQL;

/*Classe que cria a primeira tela, onde será exibido a tabela de chamados*/

public class ListaChamados extends JFrame {

	/*Elementos do swing*/
	
	private JPanel painelFundo;
	private JPanel painelBotoes;
	private JTable tabela;
	private JScrollPane barraRolagem;
	private JButton btInserir;
	private JButton btEditar;
	static DefaultTableModel modelo = new DefaultTableModel();

	/*Metodo que inicializa tela*/
	
	public ListaChamados() {
		super("DadosChamados");
		criaJTable();
		criaJanela();
	}

	/*Metodo que cria janela*/
	
	public void criaJanela() {
		btInserir = new JButton("Inserir");
		btEditar = new JButton("Editar");
		painelBotoes = new JPanel();
		barraRolagem = new JScrollPane(tabela);
		painelFundo = new JPanel();
		painelFundo.setLayout(new BorderLayout());
		painelFundo.add(BorderLayout.CENTER, barraRolagem);
		painelBotoes.add(btInserir);
		painelBotoes.add(btEditar);
		painelFundo.add(BorderLayout.SOUTH, painelBotoes);

		getContentPane().add(painelFundo);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 320);
		setVisible(true);
		btInserir.addActionListener(new BtInserirListener());
		btEditar.addActionListener(new BtEditarListener());
		
	}

	/*Metodo que cria tabela dentro da janela*/
	
	private void criaJTable() {
		tabela = new JTable(modelo);
		modelo.addColumn("OS");
		modelo.addColumn("NOME");
		modelo.addColumn("STATUS");
		tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
		pesquisar(modelo);
	}

	/*Metodo que realiza varredura e atualiza tabela*/
	
	public static void pesquisar(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		DadosSQL dao = new DadosSQL();

		for (DadosChamado c : dao.getChamados()) {
			modelo.addRow(new Object[] { c.getOs(), c.getNome(), c.getStatos() });
		}
	}

	/*Listener que irá invocar a classe Layout*/
	
	private class BtInserirListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Layout.m = 0;
			Layout ic = new Layout();
			ic.setVisible(true);
		}
	}
	
	/*Listener que irá invocar a classe Layout com os dados ateriores para modificar*/
	private class BtEditarListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			int linhaSelecionada = -1;
			linhaSelecionada = tabela.getSelectedRow();
			if (linhaSelecionada >= 0) {
				int OsTab = (int) tabela.getValueAt(linhaSelecionada, 0);
						
				Layout.m = 1;
				Layout.ID =  (int) tabela.getValueAt(linhaSelecionada, 0);
				Layout ic = new Layout();
				

				ic.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "É necesário selecionar uma linha.");
			}
		}
	}

	public static void main(String[] args) {
		ListaChamados lc = new ListaChamados();
		lc.setVisible(true);
	}
}