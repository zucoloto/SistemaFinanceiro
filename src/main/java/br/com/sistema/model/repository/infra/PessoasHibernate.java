package br.com.sistema.model.repository.infra;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.sistema.model.entity.Pessoa;
import br.com.sistema.model.repository.Pessoas;

public class PessoasHibernate implements Pessoas {

	private Session session;

	public PessoasHibernate(Session session) {
		this.session = session;
	}

	@Override
	public Pessoa salvar(Pessoa pessoa) {
		return (Pessoa) this.session.merge(pessoa);
	}

	@Override
	public void excluir(Pessoa pessoa) {
		this.session.delete(pessoa);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pessoa> listarTodos() {
		return this.session.createCriteria(Pessoa.class)
				.addOrder(Order.asc("nome")).list();
	}

	@Override
	public Pessoa buscarPorCodigo(Integer codigo) {
		return (Pessoa) this.session.get(Pessoa.class, codigo);
	}

}
