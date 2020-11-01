package entity;

public class Telefone {

	private int idTelefone;
	private String tipo;
	private String ddd;
	private String numero;
	private int fkPessoaTelefone;

	public Telefone() {
		super();
	}

	public int getIdTelefone() {
		return idTelefone;
	}

	public void setIdTelefone(int idTelefone) {
		this.idTelefone = idTelefone;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public int getFkPessoaTelefone() {
		return fkPessoaTelefone;
	}

	public void setFkPessoaTelefone(int fkPessoaTelefone) {
		this.fkPessoaTelefone = fkPessoaTelefone;
	}

	@Override
	public String toString() {
		return "[idTelefone=" + idTelefone + ", tipo=" + tipo + ", ddd=" + ddd + ", numero=" + numero
				+ ", fkPessoaTelefone=" + fkPessoaTelefone + "]";
	}

}
