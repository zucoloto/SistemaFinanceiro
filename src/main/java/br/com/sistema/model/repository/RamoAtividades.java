package br.com.sistema.model.repository;

import java.util.List;

import br.com.sistema.model.entity.RamoAtividade;

public interface RamoAtividades {

	public RamoAtividade salvar(RamoAtividade ramoAtividade);
	
	public void excluir(RamoAtividade ramoAtividade);
	
	public List<RamoAtividade> listarTodos();

	public RamoAtividade buscarPorCodigo(Integer codigo);

}
