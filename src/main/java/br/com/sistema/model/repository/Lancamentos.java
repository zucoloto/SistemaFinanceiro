package br.com.sistema.model.repository;

import java.util.List;

import br.com.sistema.model.entity.Lancamento;

public interface Lancamentos {

	public Lancamento salvar(Lancamento lancamento);

	public void excluir(Lancamento lancamento);

	public List<Lancamento> listarTodos();

	public Lancamento buscarPorCodigo(Integer codigo);

	public Lancamento comDadosIguais(Lancamento lancamento);

}
