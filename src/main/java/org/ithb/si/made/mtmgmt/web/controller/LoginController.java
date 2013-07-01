/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.ithb.si.made.mtmgmt.core.persistence.dao.UserDao;
import org.ithb.si.made.mtmgmt.core.persistence.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Uyeee
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private UserDao userDao;

	public LoginController() {
		System.out.println("LoginController!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showLogin(final Model model) {
		final LoginForm loginForm = new LoginForm();
		model.addAttribute("loginForm", loginForm);
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doLogin(final Model model, final HttpSession session, @Valid final LoginForm loginForm, final BindingResult bindingResult) {
		LOG.debug("doLogin loginForm:[{}]", loginForm);

		final UserEntity dbUserEntity = userDao.findByLoginId(loginForm.getLoginId());
		if (dbUserEntity != null) {
			LOG.debug("Found dbUserEntity:[{}]", dbUserEntity);
			if (dbUserEntity.getPasswordHash().equals(loginForm.getPassword())) {
				LOG.debug("Password match for loginForm:[{}], dbUserEntity:[{}]", loginForm, dbUserEntity);
				session.setAttribute("userId", dbUserEntity.getId());
				if (dbUserEntity.getAccessRole() == UserEntity.AccessRole.SUPERVISOR) {
					return "supervisor/home";
				} else if (dbUserEntity.getAccessRole() == UserEntity.AccessRole.TECHNICIAN) {
					return "technician/home";
				} else {
					return "admin/home";
				}
			}
		}

		bindingResult.reject("login.invalid");
		return "login";
	}

	public static class LoginForm {

		@NotNull
		@NotEmpty
		private String loginId;
		@NotNull
		@NotEmpty
		private String password;

		public LoginForm() {
		}

		public String getLoginId() {
			return loginId;
		}

		public void setLoginId(String loginId) {
			this.loginId = loginId;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		@Override
		public String toString() {
			return "LoginForm{" + "loginId=" + loginId + ", password=" + password + '}';
		}
	}
}
