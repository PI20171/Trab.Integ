package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import connection.Conexao;
import model.chamado.DadosChamado;

/*Classe que envia comandos para o Banco de Dados*/

public class DadosSQL {

	/*Comandos para o Banco de Dados*/
	private final String INSERT = "INSERT INTO DADOSCHAMADO (NOME, MATRICULA, SETOR,MOTIVO,DESCRICAO,STATOS,FEEDBACK) VALUES (?,?,?,?,?,?,?)";
	private final String UPDATE = "UPDATE DADOSCHAMADO SET NOME=? , MATRICULA=? , SETOR=? , MOTIVO=? , DESCRICAO=? , STATOS=? , FEEDBACK=? WHERE OS=?";
	private final String LIST = "SELECT * FROM DADOSCHAMADO";
	private final String LISTBYID = "SELECT * FROM DADOSCHAMADO D WHERE D.OS=?";

	
	/*Classe que realiza a inserção no banco de dados*/
	public void inserir(DadosChamado dadosfuncionario) {
		if (dadosfuncionario != null) {
			Connection conn = null;
			try {
				conn = Conexao.getConexao();
				PreparedStatement pstm;
				pstm = conn.prepareStatement(INSERT);

				pstm.setString(1, dadosfuncionario.getNome());
				pstm.setString(2, dadosfuncionario.getMatricula());
				pstm.setString(3, dadosfuncionario.getSetor());
				pstm.setString(4, dadosfuncionario.getMotivo());
				pstm.setString(5, dadosfuncionario.getDescricao());
				pstm.setString(6, dadosfuncionario.getStatos());
				pstm.setString(7, dadosfuncionario.getFeedback());

				pstm.execute();
				JOptionPane.showMessageDialog(null, "Contato cadastrado com sucesso");
				Conexao.fechaConexao(conn, pstm);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao inserir contato no banco de" + "dados " + e.getMessage());
			}
		} else {
			System.out.println("O contato enviado por parâmetro está vazio");
		}
	}

	/*Classe que realiza substituição de dados no banco de dados*/
	public void atualizar(DadosChamado dadosfuncionario) {
		if (dadosfuncionario != null) {
			Connection conn = null;
			try {
				conn = Conexao.getConexao();
				PreparedStatement pstm;
				pstm = conn.prepareStatement(UPDATE);

				pstm.setString(1, dadosfuncionario.getNome());
				pstm.setString(2, dadosfuncionario.getMatricula());
				pstm.setString(3, dadosfuncionario.getSetor());
				pstm.setString(4, dadosfuncionario.getMotivo());
				pstm.setString(5, dadosfuncionario.getDescricao());
				pstm.setString(6, dadosfuncionario.getStatos());
				pstm.setString(7, dadosfuncionario.getFeedback());
				System.out.println("===== " + dadosfuncionario.getOs());
				pstm.setInt(8, dadosfuncionario.getOs());

				pstm.execute();
				JOptionPane.showMessageDialog(null, "Contato alterado com sucesso");
				Conexao.fechaConexao(conn);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Erro ao atualizar contato no banco de" + "dados " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "O contato enviado por parâmetro está vazio");
		}

	}

	/*Classe que realiza a busca no banco de dados para a apresentação do JTable*/
	public List<DadosChamado> getChamados() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<DadosChamado> contatos = new ArrayList<>();
		try {
			conn = Conexao.getConexao();
			pstm = conn.prepareStatement(LIST);
			rs = pstm.executeQuery();
			while (rs.next()) {
				DadosChamado contato = new DadosChamado();

				contato.setOs(rs.getInt("os"));
				contato.setNome(rs.getString("nome"));
				contato.setStatos(rs.getObject("statos"));

				contatos.add(contato);
			}
			Conexao.fechaConexao(conn, pstm, rs);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar chamados " + e.getMessage());
		}
		return contatos;
	}

	/*Classe que realiza a busca no banco de dados para a recuperação de dados do chamado*/
	public DadosChamado getChamadosById(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		DadosChamado contato = new DadosChamado();
		try {
			conn = Conexao.getConexao();
			pstm = conn.prepareStatement(LISTBYID);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			while (rs.next()) {

				contato.setOs(rs.getInt("os"));
				contato.setNome(rs.getString("nome"));
				contato.setMatricula(rs.getString("matricula"));
				contato.setSetor(rs.getString("setor"));
				contato.setMotivo(rs.getString("motivo"));
				contato.setDescricao(rs.getString("descricao"));
				contato.setStatos(rs.getString("statos"));
				contato.setFeedback(rs.getString("feedback"));
			}
			Conexao.fechaConexao(conn, pstm, rs);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar contatos" + e.getMessage());
		}
		return contato;
	}

}
