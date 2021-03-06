import java.sql.Date;

public class DadosChamado {

	private int os;
	private String nome;
	private String matricula;
	private String setor;
	private Date dataAb;
	private String motivo;
	private String detalhamento;
	private String statos;
	private String feedback;
	
	public int getOs() {
		return os;
	}
	public void setOs(int os) {
		this.os = os;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public Date getDataAb() {
		return dataAb;
	}
	public void setData(Date dataAb) {
		this.dataAb = dataAb;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(Object object) {
		this.motivo = (String) object;
	}
	public String getDetalhamento() {
		return detalhamento;
	}
	public void setDetalhamento(String detalhamento) {
		this.detalhamento = detalhamento;
	}
	public String getStatos() {
		return statos;
	}
	public void setStatos(Object object) {
		this.statos = (String) object;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
}
