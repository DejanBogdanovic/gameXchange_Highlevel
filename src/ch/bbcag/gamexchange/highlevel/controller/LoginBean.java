package ch.bbcag.gamexchange.highlevel.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import ch.bbcag.gamexchange.highlevel.model.User;

@ManagedBean
@RequestScoped
public class LoginBean {
	private User user;
	
	
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sb;

	public LoginBean() {
		user = new User();
	}

	public String login() {
		String message;
		if (user.getUserForLogin(user.getUserEmail(), user.getUserPassword())) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			session.setAttribute("user", user);
			return "dashboard.xhtml?faces-redirect=true";
		} else {
			message = "Failed to sign in. Please check your input";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			return "login.xhtml?faces-redirect=true";
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setSb(SessionBean sb) {
		this.sb = sb;
	}
	
}