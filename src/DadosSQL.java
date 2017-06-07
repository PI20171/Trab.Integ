import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DadosSQL {

	private final String INSERT = "INSERT INTO DADOSCHAMADO (NOME, MATRICULA, SETOR,MOTIVO,DETALHAMENTO,STATOS,FEEDBACK) VALUES (?,?,?,?,?,?,?)";
	private final String UPDATE = "UPDATE CONTATO SET STATOS=?, FEEDBACK=? WHERE OS=?";
	private final String LIST = "SELECT * FROM DADOSCHAMADO";
	private final String LISTBYID = "SELECT * FROM  WHERE OS=?";

	public void inserir(DadosChamado dadosfuncionario) {
		if (dadosfuncionario != null) {
			Connection conn = null;
			try {
				conn = Conexao.getConexao();
				PreparedStatement pstm;
				pstm = conn.prepareStatement(INSERT);

				pstm.setString(1, dadosfuncionario.getNome());
				pstm.setString(2, dadosfuncionario.getSetor());
				pstm.setString(3, dadosfuncionario.getMatricula());
				pstm.setString(4, dadosfuncionario.getMotivo());
				pstm.setString(5, dadosfuncionario.getDetalhamento());
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

	public void atualizar(DadosChamado dadosfuncionario) {
		if (dadosfuncionario != null) {
			Connection conn = null;
			try {
				conn = Conexao.getConexao();
				PreparedStatement pstm;
				pstm = conn.prepareStatement(UPDATE);

				pstm.setString(6, dadosfuncionario.getStatos());
				pstm.setString(7, dadosfuncionario.getFeedback());

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
				// contato.setMatricula(rs.getString("matricula"));
				// contato.setSetor(rs.getString("setor"));
				// contato.setMotivo(rs.getInt("motivo"));
				// contato.setDetalhamento(rs.getString("descrição"));
				contato.setStatos(rs.getObject("statos"));
				// contato.setFeedback(rs.getString("feedback"));

				contatos.add(contato);
			}
			Conexao.fechaConexao(conn, pstm, rs);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar chamados " + e.getMessage());
		}
		return contatos;
	}

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
				contato.setMotivo(rs.getInt("motivo"));
				contato.setDetalhamento(rs.getString("descrição"));
				contato.setStatos(rs.getString("status"));
				contato.setFeedback(rs.getString("feedback"));
			}
			Conexao.fechaConexao(conn, pstm, rs);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar contatos" + e.getMessage());
		}
		return contato;
	}

}
