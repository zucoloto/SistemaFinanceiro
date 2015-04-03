package br.com.sistema.model.service;

import br.com.sistema.model.entity.Pessoa;
import br.com.sistema.model.repository.Pessoas;

public class GestaoPessoas {

	private Pessoas pessoas;

	public GestaoPessoas(Pessoas pessoas) {
		this.pessoas = pessoas;
	}

	public void salvar(Pessoa pessoa) {
		this.pessoas.salvar(pessoa);
	}

	public void excluir(Pessoa pessoas) {
		this.pessoas.excluir(pessoas);
	}
}
