package ch.bbcag.gamexchange.highlevel.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import ch.bbcag.gamexchange.highlevel.model.User;

@ManagedBean
@RequestScoped
public class EditProfileBean {
	
	private User user;
	private HttpSession session;
	
	public EditProfileBean() {
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		user = (User) session.getAttribute("user");		
	}
	
	public String edit() {
		if(user.updateUser(user)) {
			return "dashboard.xhtm?faces-redirect=true";
		} else {	
			String message = "An error occurred. Please try it again later.";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			return "editProfile.xhtml?faces-redirect=true";
		}
	}
	
	public String delete() {
		user.deleteUser(user);
		session.invalidate();
		return "index.xhtml?faces-redirect=true";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}