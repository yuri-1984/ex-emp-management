package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/**
 * 従業員関連機能の業務処理を行うサービス
 * 
 * @author hiraokayuri
 *
 */
@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * 従業員情報を全権取得する.
	 * @return
	 */
	public List<Employee> showList() {

		List<Employee> employeeList = employeeRepository.findAll();
		return employeeList;

	}

	/**
	 * 従業員情報を取得する.
	 * 
	 * @param id
	 * @return
	 */
	public Employee showDetail(Integer id) {
		Employee employeeDate = employeeRepository.load(id);
		return employeeDate;

	}
	
	/**
	 * 従業員情報を更新する.
	 * @param employee
	 */
	public void update(Employee employee) {
		employeeRepository.update(employee);
	}

}
