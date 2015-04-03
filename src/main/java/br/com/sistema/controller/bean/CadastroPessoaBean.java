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
import br.com.sistema.model.entity.Pessoa;
import br.com.sistema.model.entity.RamoAtividade;
import br.com.sistema.model.entity.TipoPessoa;
import br.com.sistema.model.repository.RamoAtividades;
import br.com.sistema.model.service.GestaoPessoas;
import br.com.sistema.model.util.Repositorios;

@ManagedBean
@ViewScoped
public class CadastroPessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Repositorios repositorios = new Repositorios();
	private Pessoa pessoa = new Pessoa();
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	private List<RamoAtividade> atividades = new ArrayList<RamoAtividade>();

	@PostConstruct
	public void init() {
		RamoAtividades ramoAtividades = this.repositorios.getRamoAtividades();
		this.atividades = ramoAtividades.listarTodos();
	}

	public void salvar() {
		GestaoPessoas gestaoPessoas = new GestaoPessoas(
				this.repositorios.getPessoas());
		gestaoPessoas.salvar(this.pessoa);
		this.pessoa = new Pessoa();
		FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO,
				"Registro salvo com sucesso!");
	}

	public boolean isEditando() {
		return this.pessoa.getCodigo() != null;
	}

	public void lancamentoTipoPessoaModificado(ValueChangeEvent event) {
		this.pessoa.setTipo((TipoPessoa) event.getNewValue());
		this.pessoa.setDataNascimento(null);
		this.pessoa.setAtividade(null);
		FacesContext.getCurrentInstance().renderResponse();
	}

	public TipoPessoa[] getTipoPessoa() {
		return TipoPessoa.values();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) throws CloneNotSupportedException {
		this.pessoa = pessoa;
		if (this.pessoa == null) {
			this.pessoa = new Pessoa();
		} else {
			this.pessoa = (Pessoa) pessoa.clone();
		}
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public List<RamoAtividade> getAtividades() {
		return atividades;
	}

}
