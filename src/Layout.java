import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class Layout extends JFrame {

	private JPanel contentPane;
	public JLabel numeroOS;
	public JTextField nome;
	public JTextField matricula;
	public JTextField setor;
	public JTextField dataHora;
	JTextArea descricao;
	JTextArea Feedback;
	JButton botaoEnviar;
	JButton botaoCancelar;
	JComboBox<String> motivo;
	JComboBox<String> status;
	
	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public Layout() {
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

		numeroOS = new JLabel();
		numeroOS.setBounds(111, 20, 70, 20);
		contentPane.add(numeroOS);
		

		nome = new JTextField();
		nome.setBounds(112, 127, 185, 20);
		contentPane.add(nome);
		nome.setColumns(10);

		matricula = new JTextField();
		matricula.setBounds(112, 102, 86, 20);
		contentPane.add(matricula);
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

		descricao = new JTextArea();
		descricao.setBounds(48, 209, 477, 127);
		contentPane.add(descricao);

		setor = new JTextField();
		setor.setBounds(373, 127, 152, 20);
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

		dataHora = new JTextField();
		dataHora.setBounds(396, 344, 86, 20);
		contentPane.add(dataHora);
		dataHora.setColumns(10);

		Feedback = new JTextArea();
		Feedback.setBounds(48, 413, 477, 110);
		contentPane.add(Feedback);

		botaoEnviar = new JButton("Enviar");
		botaoEnviar.addActionListener(new BtEnviar());
		botaoEnviar.setBounds(340, 547, 89, 23);
		contentPane.add(botaoEnviar);

		botaoCancelar = new JButton("Cancelar");
		botaoCancelar.addActionListener(new BtCancelar());
		botaoCancelar.setBounds(436, 547, 89, 23);
		contentPane.add(botaoCancelar);
	}

	private class BtEnviar implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			DadosChamado d = new DadosChamado();
			
			d.setMotivo(motivo.getSelectedItem());
			d.setMatricula(matricula.getText());
			d.setNome(nome.getText());
			d.setSetor(setor.getText());
			d.setDetalhamento(descricao.getText());
			d.setStatos(status.getSelectedItem());
			d.setFeedback(Feedback.getText());

			DadosSQL dao = new DadosSQL();
			dao.inserir(d);

			setVisible(false);

		}
	}
	
	private class BtCancelar implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {

			
			
		}
	}
}
