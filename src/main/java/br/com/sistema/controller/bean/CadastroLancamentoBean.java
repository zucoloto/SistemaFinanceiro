package br.com.sistema.controller.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import br.com.sistema.controller.message.FacesUtil;
import br.com.sistema.model.entity.Lancamento;
import br.com.sistema.model.entity.Pessoa;
import br.com.sistema.model.entity.TipoLancamento;
import br.com.sistema.model.repository.Pessoas;
import br.com.sistema.model.service.GestaoLancamentos;
import br.com.sistema.model.service.exception.RegraNegocioException;
import br.com.sistema.model.util.Repositorios;

@ManagedBean
@ViewScoped
public class CadastroLancamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Repositorios repositorios = new Repositorios();
	private Lancamento lancamento = new Lancamento();
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();

	@PostConstruct
	public void init() {
		Pessoas pessoas = this.repositorios.getPessoas();
		this.pessoas = pessoas.listarTodos();
	}

	public void salvar() {
		GestaoLancamentos gestaoLancamentos = new GestaoLancamentos(
				this.repositorios.getLancamentos());
		try {
			gestaoLancamentos.salvar(this.lancamento);
			this.lancamento = new Lancamento();
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO,
					"Registro salvo com sucesso!");
		} catch (RegraNegocioException e) {
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR,
					e.getMessage());
		}
	}

	public boolean isEditando() {
		return this.lancamento.getCodigo() != null;
	}

	public void lancamentoPagoModificado(ValueChangeEvent event) {
		this.lancamento.setPago((Boolean) event.getNewValue());
		this.lancamento.setDataPagamento(null);
		FacesContext.getCurrentInstance().renderResponse();
	}

	public TipoLancamento[] getTipoLancamento() {
		return TipoLancamento.values();
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) throws CloneNotSupportedException {
		this.lancamento = lancamento;
		if (this.lancamento == null){
			this.lancamento = new Lancamento();
		} else {
			this.lancamento = (Lancamento) lancamento.clone();
		}
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

}
