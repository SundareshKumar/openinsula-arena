package org.openinsula.arena.validator;

public class Person {

	@Cpf
	private String cpf;

	@Cnpj
	private String cnpj;

	@CpfOrCnpj
	private String cpfOrCnpj;

	@PisPasep
	private String pisPasep;

	@DateFormat
	private String dateFormat;

	@BacenPais
	private String paisString;

	@BacenPais
	private Integer paisInteger;

	@IbgeMunicipio
	private String municipioString;

	@IbgeMunicipio
	private Integer municipioInteger;

	@IbgeUf
	private Integer uf;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}

	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}

	public String getPisPasep() {
		return pisPasep;
	}

	public void setPisPasep(String pisPasep) {
		this.pisPasep = pisPasep;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getPaisString() {
		return paisString;
	}

	public void setPaisString(String paisString) {
		this.paisString = paisString;
	}

	public Integer getPaisInteger() {
		return paisInteger;
	}

	public void setPaisInteger(Integer paisInteger) {
		this.paisInteger = paisInteger;
	}

	public String getMunicipioString() {
		return municipioString;
	}

	public void setMunicipioString(String municipioString) {
		this.municipioString = municipioString;
	}

	public Integer getMunicipioInteger() {
		return municipioInteger;
	}

	public void setMunicipioInteger(Integer municipioInteger) {
		this.municipioInteger = municipioInteger;
	}

	public Integer getUf() {
		return uf;
	}

	public void setUf(Integer uf) {
		this.uf = uf;
	}

}
