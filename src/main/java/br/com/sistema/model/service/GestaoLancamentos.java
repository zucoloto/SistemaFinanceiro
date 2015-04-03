package br.com.sistema.model.service;

import br.com.sistema.model.entity.Lancamento;
import br.com.sistema.model.repository.Lancamentos;
import br.com.sistema.model.service.exception.RegraNegocioException;

public class GestaoLancamentos {

	private Lancamentos lancamentos;

	public GestaoLancamentos(Lancamentos lancamentos) {
		this.lancamentos = lancamentos;
	}

	public void salvar(Lancamento lancamento) throws RegraNegocioException {
		if (existeLancamentoSemelhante(lancamento)) {
			throw new RegraNegocioException(
					"Já existe uma lancamento igual a este.");
		}
		this.lancamentos.salvar(lancamento);
	}

	public boolean existeLancamentoSemelhante(Lancamento lancamento) {
		Lancamento lancamentoSemelhante = this.lancamentos
				.comDadosIguais(lancamento);
		return (lancamentoSemelhante != null && !lancamentoSemelhante
				.equals(lancamento));
	}

	public void excluir(Lancamento lancamento) throws RegraNegocioException {
		if (lancamento.isPago()) {
			throw new RegraNegocioException(
					"Lançamento já foi pago e não pode ser excluído!");
		}
		this.lancamentos.excluir(lancamento);
	}
}
