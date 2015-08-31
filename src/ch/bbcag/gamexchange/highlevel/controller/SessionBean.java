package ch.bbcag.gamexchange.highlevel.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import ch.bbcag.gamexchange.highlevel.model.User;

@ManagedBean
@SessionScoped
public class SessionBean {
	
	private HttpSession session;
	
	public boolean isLoggedIn() {
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("user") != null;
	}
	
	public String logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "index.xhtml?faces-redirect=true";
	}

	public User getUser() {
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return (User) session.getAttribute("user");
	}

}