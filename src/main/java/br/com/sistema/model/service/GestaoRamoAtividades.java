package br.com.sistema.model.service;

import br.com.sistema.model.entity.RamoAtividade;
import br.com.sistema.model.repository.RamoAtividades;

public class GestaoRamoAtividades {

	private RamoAtividades ramoAtividades;

	public GestaoRamoAtividades(RamoAtividades ramoAtividades) {
		this.ramoAtividades = ramoAtividades;
	}

	public void salvar(RamoAtividade ramoAtividade) {
		this.ramoAtividades.salvar(ramoAtividade);
	}

	public void excluir(RamoAtividade ramoAtividade) {
		this.ramoAtividades.excluir(ramoAtividade);
	}
}
