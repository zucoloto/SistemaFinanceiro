package br.com.sistema.controller.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sistema.controller.message.FacesUtil;
import br.com.sistema.model.entity.RamoAtividade;
import br.com.sistema.model.repository.RamoAtividades;
import br.com.sistema.model.service.GestaoRamoAtividades;
import br.com.sistema.model.util.Repositorios;

@ManagedBean
@ViewScoped
public class RamoAtividadeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Repositorios repositorios = new Repositorios();
	private RamoAtividade atividade = new RamoAtividade();
	private RamoAtividade atividadeSelecionado;
	private List<RamoAtividade> atividades = new ArrayList<RamoAtividade>();

	@PostConstruct
	public void init() {
		RamoAtividades gestaoAtividades = this.repositorios.getRamoAtividades();
		this.atividades = gestaoAtividades.listarTodos();
	}

	public void salvar() {
		GestaoRamoAtividades gestaoRamoAtividades = new GestaoRamoAtividades(
				this.repositorios.getRamoAtividades());
		gestaoRamoAtividades.salvar(this.atividade);
		this.atividade = new RamoAtividade();
		FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO,
				"Registro salvo com sucesso!");
	}

	public void excluir() {
		GestaoRamoAtividades gestaoRamoAtividades = new GestaoRamoAtividades(
				this.repositorios.getRamoAtividades());
		gestaoRamoAtividades.excluir(this.atividadeSelecionado);
		this.init();
		FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO,
				"Registro excluído com sucesso!");
	}
	
	public boolean isEditando() {
		return this.atividade.getCodigo() != null;
	}

	public RamoAtividade getAtividade() {
		return atividade;
	}

	public void setAtividade(RamoAtividade atividade)
			throws CloneNotSupportedException {
		this.atividade = atividade;
		if (this.atividade == null) {
			this.atividade = new RamoAtividade();
		} else {
			this.atividade = (RamoAtividade) atividade.clone();
		}
	}

	public RamoAtividade getAtividadeSelecionado() {
		return atividadeSelecionado;
	}

	public void setAtividadeSelecionado(RamoAtividade atividadeSelecionado) {
		this.atividadeSelecionado = atividadeSelecionado;
	}

	public List<RamoAtividade> getAtividades() {
		return atividades;
	}

}
