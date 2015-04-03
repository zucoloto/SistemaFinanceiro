package br.com.sistema.controller.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sistema.controller.message.FacesUtil;
import br.com.sistema.model.entity.Lancamento;
import br.com.sistema.model.repository.Lancamentos;
import br.com.sistema.model.service.GestaoLancamentos;
import br.com.sistema.model.service.exception.RegraNegocioException;
import br.com.sistema.model.util.Repositorios;

@ManagedBean
@ViewScoped
public class ConsultaLancamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Repositorios repositorios = new Repositorios();
	private Lancamento lancamento = new Lancamento();
	private Lancamento lancamentoSelecionado;
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();

	@PostConstruct
	public void init() {
		Lancamentos lancamentos = this.repositorios.getLancamentos();
		this.lancamentos = lancamentos.listarTodos();
	}

	public void excluir() {
		GestaoLancamentos gestaoLancamentos = new GestaoLancamentos(
				this.repositorios.getLancamentos());
		try {
			gestaoLancamentos.excluir(this.lancamentoSelecionado);

			this.init();

			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO,
					"Registro excluído com sucesso!");
		} catch (RegraNegocioException e) {
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR,
					e.getMessage());
		}
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public Lancamento getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

}
