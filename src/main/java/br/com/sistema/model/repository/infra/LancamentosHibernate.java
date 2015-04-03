package br.com.sistema.model.repository.infra;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.sistema.model.entity.Lancamento;
import br.com.sistema.model.repository.Lancamentos;

public class LancamentosHibernate implements Lancamentos {

	private Session session;

	public LancamentosHibernate(Session session) {
		this.session = session;
	}

	@Override
	public Lancamento salvar(Lancamento lancamento) {
		return (Lancamento) session.merge(lancamento);
	}

	@Override
	public void excluir(Lancamento lancamento) {
		this.session.delete(lancamento);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lancamento> listarTodos() {
		return this.session.createCriteria(Lancamento.class).list();
	}

	@Override
	public Lancamento buscarPorCodigo(Integer codigo) {
		return (Lancamento) this.session.get(Lancamento.class, codigo);
	}

	@Override
	public Lancamento comDadosIguais(Lancamento lancamento) {
		return (Lancamento) this.session
				.createCriteria(Lancamento.class)
				.add(Restrictions.eq("tipo", lancamento.getTipo()))
				.add(Restrictions.eq("pessoa", lancamento.getPessoa()))
				.add(Restrictions.ilike("descricao", lancamento.getDescricao()))
				.add(Restrictions.eq("valor", lancamento.getValor()))
				.add(Restrictions.eq("dataVencimento",
						lancamento.getDataVencimento())).uniqueResult();
	}

}
