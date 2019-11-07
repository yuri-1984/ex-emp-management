package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * @author hiraokayuri
 *従業員関連機能の処理の制御を行うコントローラー.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 従業員一覧を出力する.
	 * @param model
	 * @return employee/list
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {

		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);
		return "employee/list";
	}

	/**
	 * UpdateEmployeeFormオブジェクトがmodelオブジェクトに自動格納.
	 * 
	 * @return
	 */
	@ModelAttribute
	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		return "employee/detail";
	}
	
	/**
	 * 従業員詳細を更新する.
	 * @param form
	 * @return
	 */
	@RequestMapping("/update")
	public String update(UpdateEmployeeForm form) {
		Employee employee = new Employee();
		employee.setDependentsCounts(Integer.parseInt(form.getDependentsCount()));
		employee.setId(Integer.parseInt(form.getId()));
		employeeService.update(employee);
		return "redirect:/employee/showList";
		
		
		
	}

}
