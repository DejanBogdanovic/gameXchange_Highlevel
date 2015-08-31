package ch.bbcag.gamexchange.highlevel.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import ch.bbcag.gamexchange.highlevel.model.User;

@ManagedBean
@RequestScoped
public class RegisterBean {
	private User user;
	
	public RegisterBean() {
		user = new User();
	}
	
	public String register() {
		String message;
		String newId = user.insertUser(user);
		if(newId == null || newId.equals("0")) {
			message = "Failed to sign in. Please check your input";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			return "register.xhtml?faces-redirect=true";
		} else {
			message = "Register successfully";
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			session.setAttribute("user", user);
			return "index.xhtml?faces-redirect=true";
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}