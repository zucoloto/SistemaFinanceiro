package br.com.sistema.controller.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sistema.controller.message.FacesUtil;
import br.com.sistema.model.entity.Pessoa;
import br.com.sistema.model.repository.Pessoas;
import br.com.sistema.model.service.GestaoPessoas;
import br.com.sistema.model.util.Repositorios;

@ManagedBean
@ViewScoped
public class ConsultaPessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Repositorios repositorios = new Repositorios();
	private Pessoa pessoaSelecionado;
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();

	@PostConstruct
	public void init() {
		Pessoas pessoas = this.repositorios.getPessoas();
		this.pessoas = pessoas.listarTodos();
	}

	public void excluir() {
		GestaoPessoas gestaoPessoas = new GestaoPessoas(
				this.repositorios.getPessoas());
		gestaoPessoas.excluir(this.pessoaSelecionado);
		this.init();
		FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO,
				"Registro excluído com sucesso!");
	}

	public Pessoa getPessoaSelecionado() {
		return pessoaSelecionado;
	}

	public void setPessoaSelecionado(Pessoa pessoaSelecionado) {
		this.pessoaSelecionado = pessoaSelecionado;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

}
