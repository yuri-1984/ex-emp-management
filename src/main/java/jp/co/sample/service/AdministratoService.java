package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;



/**
 * 管理者関連機能の業務処理を行うサービスクラス.
 * @author hiraokayuri
 *
 */
@Service
@Transactional
public class AdministratoService {
	
	@Autowired
	private AdministratorRepository adiministratorrepository;
	
	public  void insert (Administrator administrator) {
		
		 adiministratorrepository.insert(administrator);
		
	}
	
	/**
	 * ログインをする業務処理を行うメソッド.
	 * @param mailAddress
	 * @param password
	 * @return　
	 */
	public Administrator login (String mailAddress , String password) {
		return adiministratorrepository.findByMailAddressAndPassword(mailAddress, password);
		
	}

	

}
