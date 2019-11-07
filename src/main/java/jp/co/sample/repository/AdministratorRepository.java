package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;


/**
 *administratorsテーブル操作のリポジトリクラス.
 * @author hiraokayuri
 *
 */


@Repository
public class AdministratorRepository {
	/**
	 * ResultSetオブジェクトからAdministratorオブジェクトに変換するためのクラス実装.
	 */
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
		Administrator administrator = new Administrator();

		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));
		return administrator;

	};
	
	
	/**
	 * 管理者情報を登録する.
	 * @param administrator
	 */
	public void insert(Administrator administrator) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);

		String insertsql = "insert into administrators (name,mail_address,password)values(:name,:mailAddress,:password)";
		template.update(insertsql, param);

	}
	
	
	/**
	 *メールアドレスとパスワードから管理者情報を取得する.
	 * @param mailAddress
	 * @param password
	 * @return Administrator
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {

		String selectSql = "select id,name,mail_address,password from administrators where mail_address =:mailAddress and password =:password";
		SqlParameterSource param = new MapSqlParameterSource().addValue("password", password).addValue("mailAddress",mailAddress);

		try {
			return template.queryForObject(selectSql, param, ADMINISTRATOR_ROW_MAPPER);

		} catch (Exception e) {

			return null;

		}
		
		

	}

}
