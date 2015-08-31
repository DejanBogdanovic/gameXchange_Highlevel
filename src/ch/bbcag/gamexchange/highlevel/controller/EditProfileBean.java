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
	
	public EditProfileBean() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		user = (User) session.getAttribute("user");
	}
	
	public String edit() {
		if(user.updateUser(user)) {
			return "dashboard.xhtm?faces-redirect=true";
		} else {	
			String message = "An error occurred. Please check your input and try it again."
					+ user.getName() + user.getLastname() + user.getUserName() + user.getUserEmail() + user.getUserPassword() + user.getUserPasswordRepeat()
					+ user.getDomicile() + user.getPostcode() + user.getCountry();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			return "editProfile.xhtml?faces-redirect=true";
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}