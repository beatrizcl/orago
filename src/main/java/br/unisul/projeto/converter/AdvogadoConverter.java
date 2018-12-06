package br.unisul.projeto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unisul.projeto.dao.AdvogadoDao;
import br.unisul.projeto.domain.Advogado;

@FacesConverter("advogadoConverter")
public class AdvogadoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) return null;
		return new AdvogadoDao().listar(Integer.valueOf(value)).get(0);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((Advogado)value).getCd().toString();
	}

}

