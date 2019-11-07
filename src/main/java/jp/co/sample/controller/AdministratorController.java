package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratoService;

/**
 * 管理者関連機能の処理の制御を行うコントローラー
 * 
 * @author hiraokayuri
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private AdministratoService administratorService;
	@Autowired
	private HttpSession session;

	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {

		return new InsertAdministratorForm();

	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}

	/**
	 * 管理者情報を挿入する
	 * 
	 * @param form
	 * @return ダブルサブミット処理：コントローラー
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		return "redirect:/";

	}

	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();

	}

	@RequestMapping("/")
	public String toLogin() {

		return "administrator/login";
	}

	/**
	 * 管理者情報にログインする.
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {
		Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());

		if (administrator == null) {

			model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です。");
			return "administrator/login";
		} else {

			session.setAttribute("administratorName", administrator.getName());
			return "forward:/employee/showList";

		}

	}

	/**
	 * ログアウトする.
	 * 
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";

	}

}
