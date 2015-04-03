package br.com.sistema.model.repository.infra;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.sistema.model.entity.RamoAtividade;
import br.com.sistema.model.repository.RamoAtividades;

public class RamoAtividadesHibernate implements RamoAtividades {

	private Session session;

	public RamoAtividadesHibernate(Session session) {
		this.session = session;
	}

	@Override
	public RamoAtividade salvar(RamoAtividade ramoAtividade) {
		return (RamoAtividade) this.session.merge(ramoAtividade);
	}

	@Override
	public void excluir(RamoAtividade ramoAtividade) {
		this.session.delete(ramoAtividade);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RamoAtividade> listarTodos() {
		return this.session.createCriteria(RamoAtividade.class)
				.addOrder(Order.asc("descricao")).list();
	}

	@Override
	public RamoAtividade buscarPorCodigo(Integer codigo) {
		return (RamoAtividade) this.session.get(RamoAtividade.class, codigo);
	}

}
