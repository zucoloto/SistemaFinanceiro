package br.com.sistema.model.util;

import java.io.Serializable;

import org.hibernate.Session;

import br.com.sistema.controller.message.FacesUtil;
import br.com.sistema.model.repository.Lancamentos;
import br.com.sistema.model.repository.Pessoas;
import br.com.sistema.model.repository.RamoAtividades;
import br.com.sistema.model.repository.infra.LancamentosHibernate;
import br.com.sistema.model.repository.infra.PessoasHibernate;
import br.com.sistema.model.repository.infra.RamoAtividadesHibernate;

public class Repositorios implements Serializable {

	private static final long serialVersionUID = 1L;

	private Session getSession() {
		return (Session) FacesUtil.getRequestAtrribute("session");
	}

	public Pessoas getPessoas() {
		return new PessoasHibernate(this.getSession());
	}

	public Lancamentos getLancamentos() {
		return new LancamentosHibernate(this.getSession());
	}
	
	public RamoAtividades getRamoAtividades() {
		return new RamoAtividadesHibernate(this.getSession());
	}

}
