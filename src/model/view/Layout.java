package model.view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.chamado.DadosChamado;
import model.dao.DadosSQL;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*Classe Layout*/

public class Layout extends JFrame {

	/*Elementos do Layout que serão usados fora do construtor*/
	
	private JPanel contentPane;
	public JLabel os;
	public JTextField nome;
	public JTextField matricula;
	public JTextField setor;
	public JTextField dataHora;
	JTextArea descricao;
	JTextArea Feedback;
	JButton botaoEnviar;
	JButton botaoSalvar;
	JButton botaoCancelar;
	JComboBox<String> motivo;
	JComboBox<String> status;
	static Integer m = 0;
	static int ID;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Layout frame = new Layout();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*Metodo construtor de Layout*/
	
	public Layout() {
		
		DadosSQL dao = new DadosSQL();
		DadosChamado d = dao.getChamadosById(ID);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 591, 636);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ordem de Servi\u00E7o");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(340, 11, 185, 28);
		contentPane.add(lblNewLabel);

		JLabel lblNumero = new JLabel("Numero:");
		lblNumero.setBounds(48, 23, 53, 14);
		contentPane.add(lblNumero);

		JLabel lblNewLabel_1 = new JLabel("Motivo:");
		lblNewLabel_1.setBounds(48, 80, 46, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setBounds(48, 105, 54, 14);
		contentPane.add(lblMatricula);

		JLabel lblNewLabel_2 = new JLabel("Nome:");
		lblNewLabel_2.setBounds(48, 130, 38, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Setor:");
		lblNewLabel_3.setBounds(320, 133, 46, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Detalhamento da OS:");
		lblNewLabel_4.setBounds(48, 182, 168, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(48, 347, 46, 14);
		contentPane.add(lblStatus);

		JLabel lblDataEHora = new JLabel("Data e Hora:");
		lblDataEHora.setBounds(320, 347, 84, 14);
		contentPane.add(lblDataEHora);

		JLabel lblFeedback = new JLabel("Feedback:");
		lblFeedback.setBounds(48, 388, 95, 14);
		contentPane.add(lblFeedback);

		os = new JLabel();
		os.setBounds(111, 20, 70, 20);
		contentPane.add(os);
		os.setText("" + d.getOs());

		nome = new JTextField();
		nome.setBounds(112, 127, 185, 20);
		contentPane.add(nome);
		nome.setText(d.getNome());
		nome.setColumns(10);

		matricula = new JTextField();
		matricula.setBounds(112, 102, 86, 20);
		contentPane.add(matricula);
		matricula.setText(d.getMatricula());
		matricula.setColumns(10);

		JList list = new JList();
		list.setBounds(169, 264, 1, 1);
		contentPane.add(list);

		motivo = new JComboBox<String>();
		motivo.setBounds(112, 77, 185, 20);
		contentPane.add(motivo);
		motivo.addItem("Infraestrutura");
		motivo.addItem("Internet");
		motivo.addItem("Software");
		motivo.addItem("ERP");
		motivo.addItem("Periféricos");
		motivo.addItem("Desenvolvimento");
		motivo.addItem("Outros Assuntos");
		motivo.setSelectedItem(d.getMotivo());

		descricao = new JTextArea();
		descricao.setBounds(48, 209, 477, 127);
		descricao.setText(d.getDescricao());
		contentPane.add(descricao);

		setor = new JTextField();
		setor.setBounds(373, 127, 152, 20);
		setor.setText(d.getSetor());
		contentPane.add(setor);
		setor.setColumns(10);

		status = new JComboBox<String>();
		status.setBounds(113, 344, 184, 20);
		contentPane.add(status);
		status.addItem("Aberto");
		status.addItem("Em análise");
		status.addItem("Em atendimento");
		status.addItem("Cancelado");
		status.addItem("Encerrado");
		status.setSelectedItem(d.getStatos());

		dataHora = new JTextField();
		dataHora.setBounds(396, 344, 86, 20);
		contentPane.add(dataHora);
		dataHora.setColumns(10);

		Feedback = new JTextArea();
		Feedback.setBounds(48, 413, 477, 110);
		Feedback.setText(d.getFeedback());
		contentPane.add(Feedback);

		if (m == 1) {
			botaoSalvar = new JButton("Salvar");
			botaoSalvar.addActionListener(new BtSalvar());
			botaoSalvar.setBounds(340, 547, 89, 23);
			contentPane.add(botaoSalvar);
		} else {
			botaoEnviar = new JButton("Enviar");
			botaoEnviar.addActionListener(new BtEnviar());
			botaoEnviar.setBounds(340, 547, 89, 23);
			contentPane.add(botaoEnviar);
		}

		botaoCancelar = new JButton("Cancelar");
		botaoCancelar.addActionListener(new BtCancelar());
		botaoCancelar.setBounds(436, 547, 89, 23);
		contentPane.add(botaoCancelar);
	}

	/*Listener que irá enviar os dados para o Banco de Dados em um novo contato */
	
	private class BtEnviar implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			DadosChamado d = new DadosChamado();

			d.setMotivo(motivo.getSelectedItem());
			d.setMatricula(matricula.getText());
			d.setNome(nome.getText());
			d.setSetor(setor.getText());
			d.setDescricao(descricao.getText());
			d.setStatos(status.getSelectedItem());
			d.setFeedback(Feedback.getText());

			DadosSQL dao = new DadosSQL();
			dao.inserir(d);
			ListaChamados.pesquisar(ListaChamados.modelo);

			setVisible(false);

		}
	}

	/*Listener que irá enviar os dados para o Banco de Dados em um contato existente*/
	
	private class BtSalvar implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			DadosSQL dao = new DadosSQL();
			
			DadosChamado d = new DadosChamado();

			d.setMotivo(motivo.getSelectedItem());
			d.setMatricula(matricula.getText());
			d.setNome(nome.getText());
			d.setSetor(setor.getText());
			d.setDescricao(descricao.getText());
			d.setStatos(status.getSelectedItem());
			d.setFeedback(Feedback.getText());			
			d.setOs(ID);

			
			dao.atualizar(d);
			ListaChamados.pesquisar(ListaChamados.modelo);

			setVisible(false);

		}
	}

	/*Listener que irá fechar Layout*/
	
	private class BtCancelar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			dispose();
			ListaChamados.pesquisar(ListaChamados.modelo);
			
		}
	}
}
