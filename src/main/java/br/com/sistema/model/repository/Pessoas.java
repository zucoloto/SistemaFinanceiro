package br.com.sistema.model.repository;

import java.util.List;

import br.com.sistema.model.entity.Pessoa;

public interface Pessoas {

	public Pessoa salvar(Pessoa pessoa);

	public void excluir(Pessoa pessoa);

	public List<Pessoa> listarTodos();

	public Pessoa buscarPorCodigo(Integer codigo);

}
