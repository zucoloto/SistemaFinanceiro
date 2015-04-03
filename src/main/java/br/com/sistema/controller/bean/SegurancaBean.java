package br.com.sistema.controller.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import br.com.sistema.controller.message.FacesUtil;

@ManagedBean
public class SegurancaBean {

	private String usuario;
	private String senha;

	private HttpServletRequest getRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (HttpServletRequest) context.getExternalContext().getRequest();
	}

	public String logar() {
		try {
			this.getRequest().login(this.usuario, this.senha);
			return "Home?faces-redirect=true";
		} catch (ServletException e) {
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR,
					"Usuário ou senha não confere.");
			return null;
		}
	}

	public String sair() throws ServletException {
		this.getRequest().logout();
		return "Login?faces-redirect=true";
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
