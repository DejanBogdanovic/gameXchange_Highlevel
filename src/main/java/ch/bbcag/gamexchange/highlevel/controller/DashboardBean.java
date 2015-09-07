package ch.bbcag.gamexchange.highlevel.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import ch.bbcag.gamexchange.highlevel.model.User;

@ManagedBean
@RequestScoped
public class DashboardBean {
	private User user;
	private HttpSession session;
	
	public DashboardBean() {
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		user = (User) session.getAttribute("user");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
