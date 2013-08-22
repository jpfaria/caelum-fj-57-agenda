package br.com.caelum.cadastro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Prova implements Serializable {

	private static final long serialVersionUID = 402350841640010647L;

	private String data;
	private String materia;
	private String descricao;
	private List<String> topicos = new ArrayList<String>();
	
	public Prova(String data, String materia) {
		this.data = data;
		this.materia = materia;
	}
	
	@Override
	public String toString() {
		return materia + " - " + data;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<String> getTopicos() {
		return topicos;
	}

	public void setTopicos(List<String> topicos) {
		this.topicos = topicos;
	}

}