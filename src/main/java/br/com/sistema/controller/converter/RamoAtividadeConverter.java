package br.com.sistema.controller.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.sistema.model.entity.RamoAtividade;
import br.com.sistema.model.repository.RamoAtividades;
import br.com.sistema.model.util.Repositorios;

@FacesConverter(forClass = RamoAtividade.class)
public class RamoAtividadeConverter implements Converter {

	private Repositorios repositorios = new Repositorios();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		RamoAtividade retorno = null;
		RamoAtividades atividades = this.repositorios.getRamoAtividades();

		if (value != null && !value.equals("")) {
			retorno = atividades.buscarPorCodigo(new Integer(value));

			if (retorno == null) {
				String descricaoErro = "Registro não existe.";
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, descricaoErro,
						descricaoErro);
				throw new ConverterException(message);
			}
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Integer codigo = ((RamoAtividade) value).getCodigo();
			return codigo == null ? "" : codigo.toString();
		}

		return null;
	}

}
