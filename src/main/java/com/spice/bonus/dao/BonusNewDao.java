package com.spice.bonus.dao;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.spice.bonus.request.AddUserRequest;
import com.spice.bonus.request.ChangePassword;
import com.spice.bonus.request.DeleteUser;
import com.spice.bonus.request.DownloadRequest;
import com.spice.bonus.request.EditUserRequest;
import com.spice.bonus.request.LoginRequest;
import com.spice.bonus.request.UploadRequest;
import com.spice.bonus.request.ViewRequest;
import com.spice.bonus.response.CircleResponse;
import com.spice.bonus.response.DownloadResponse;
import com.spice.bonus.response.LoginResponse;
import com.spice.bonus.response.ResponseObj;
import com.spice.bonus.response.UserResponse;
import com.spice.bonus.response.View444InResponse;
import com.spice.bonus.response.View444ReccResponse;
import com.spice.bonus.response.View444UpssResponse;
import com.spice.bonus.response.ViewResponse;
import com.spice.bonus.utility.UtilityData;

@Transactional
@Repository
public class BonusNewDao {
	private static final Logger LOGGER = LogManager.getLogger(BonusNewDao.class);

	@Value("${spring.datasource.url}")
	private String jdbcUrl;

	@Value("${spring.datasource.username}")
	private String jdbcUserName;

	@Value("${spring.datasource.password}")
	private String jdbcPassword;

	private JdbcTemplate jdbcTemplateObject;

	@Autowired
	public void setDataSource(DataSource ds) {
		this.jdbcTemplateObject = new JdbcTemplate(ds);
	}

	@Autowired
	private UtilityData util;

	public List<LoginResponse> misLogin(LoginRequest request) {
		String sql = "";

		sql = " select user_id,password,user_type,circle_id,expiry_date_time,last_login from tbl_user_mis_new where  user_id=? and password=?";
		LOGGER.info(sql);

		return jdbcTemplateObject.query(sql, new Object[] { request.getUserName(), request.getPassword() },
				(rs, rowNum) -> new LoginResponse(rs.getString("user_id"), rs.getString("password"),
						rs.getString("user_type"), rs.getString("circle_id"), rs.getString("expiry_date_time"),
						rs.getString("last_login")));

	}

	public int updateStatus(LoginRequest request, String status) {
		String sql = "";
		if (status.equals("active")) {
			sql = "update tbl_user_mis_new set status = ?, last_login = ? where user_id = ? and password = ?";
			return jdbcTemplateObject.update(sql, status, util.getDateTime(), request.getUserName(),
					request.getPassword());
		} else {
			sql = "update tbl_user_mis_new set status = ? where user_id = ? and password = ?";
			return jdbcTemplateObject.update(sql, status, request.getUserName(), request.getPassword());
		}
	}

	public ResponseObj addUser(AddUserRequest userReq, String currentDateTime, String expiryD, String status) {
		String sql = "";
		sql = "insert into tbl_user_mis_new(user_id,password,user_type,circle_id,create_date_time,expiry_date_time,sam_id,workorder_id,email_id,msisdn,last_login,status) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object obj = jdbcTemplateObject.update(sql, userReq.getUser_id(), userReq.getPassword(), userReq.getUser_type(),
				userReq.getCircle_id(), currentDateTime, expiryD, userReq.getSam_id(), userReq.getWorkorder_id(),
				userReq.getEmail_id(), userReq.getMsisdn(), currentDateTime, status);
		return new ResponseObj(obj);
	}

	public ResponseObj editUser(EditUserRequest userReq) {
		String sql = "";
		String status = "active";
		sql = "update tbl_user_mis_new set password = ?,user_type=?, circle_id = ?, create_date_time = ?, expiry_date_time = ?, sam_id = ?, workorder_id = ?, email_id = ?, msisdn =?, last_login = ?, status = ? where user_id = ?";
		Object obj = jdbcTemplateObject.update(sql, userReq.getPassword(), userReq.getUserType(), userReq.getCircleId(),
				userReq.getCreateDate(), userReq.getExpiryDate(), userReq.getSamId(), userReq.getWorkorderId(),
				userReq.getEmailId(), userReq.getMsisdn(), userReq.getLastLoginDate(), status, userReq.getUserId());
		return new ResponseObj(obj);
	}

	public ResponseObj deleteUser(DeleteUser req) {

		String sqlQuery = "delete  from tbl_user_mis_new where user_id=?";

		Object obj = jdbcTemplateObject.update(sqlQuery, req.getUserId());

		return new ResponseObj(obj);
	}

	public ResponseObj changePassword(ChangePassword req, String plusDate) {
		String sqlSelect = "";
		String sqlQuery ="";
		Object obj = "";

		sqlSelect = "SELECT password from tbl_user_mis_new  where user_id = ?";
		sqlQuery = "update tbl_user_mis_new set password = ? , expiry_date_time = ? where user_id = ? and password = ?";
		
		String oldpaswd = jdbcTemplateObject.queryForObject(sqlSelect,new Object[] {req.getUserId()},String.class);
		if(oldpaswd.equals(req.getOldPassword())) {
			obj = jdbcTemplateObject.update(sqlQuery, req.getNewPassword(), plusDate, req.getUserId(),
					req.getOldPassword());
		}else {
			return new ResponseObj(null,"Failure","Password not matched",100);
		}
		
		return new ResponseObj(obj);
	}

	public List<CircleResponse> circle() {

		String sql = "select decription,circle_name,circle_id from tbl_circle_list";
		// return jdbcTemplateObject.queryForList(sql, String.class);

		return jdbcTemplateObject.query(sql, new Object[] {},
				(rs, rowNum) -> new CircleResponse(rs.getString("decription"), rs.getString("circle_name"),
						rs.getString("circle_id")));
	}

	public List<UserResponse> userList() {
		String sql = "";
		sql = "select * from tbl_user_mis_new";

		return jdbcTemplateObject.query(sql, new Object[] {},
				(rs, rowNum) -> new UserResponse(rs.getString("user_id"), rs.getString("password"),
						rs.getString("user_type"), rs.getString("circle_id"), rs.getString("create_date_time"),
						rs.getString("expiry_date_time"), rs.getString("sam_id"), rs.getString("workorder_id"),
						rs.getString("email_id"), rs.getString("msisdn"), rs.getString("last_login")));

	}
	
	public List<View444UpssResponse> view444Upss(String circleId) {
		String sql = "";
		if(circleId.equalsIgnoreCase("pan")|| circleId.equalsIgnoreCase("corp")) {
			sql = "select * from 444Bonus";
			return jdbcTemplateObject.query(sql, new Object[] {},
					(rs, rowNum) -> new View444UpssResponse(rs.getString("circle_id"),rs.getString("rank_order"),rs.getString("offer_name"),rs.getString("bonus_name"),rs.getString("value"),rs.getString("amount"),rs.getString("threshold"),rs.getString("debit_event_id"),rs.getString("credit_event_id"),rs.getString("validity"),rs.getString("activation_mode"),rs.getString("system_creation_date"),rs.getString("system_update_date"),rs.getString("effective_date"),rs.getString("exp_date"),rs.getString("mdm_id"),rs.getString("brand"),rs.getString("status")));
		}else {
			sql = "select * from 444Bonus where circle_id=?";
			return jdbcTemplateObject.query(sql, new Object[] {circleId},
					(rs, rowNum) -> new View444UpssResponse(rs.getString("circle_id"),rs.getString("rank_order"),rs.getString("offer_name"),rs.getString("bonus_name"),rs.getString("value"),rs.getString("amount"),rs.getString("threshold"),rs.getString("debit_event_id"),rs.getString("credit_event_id"),rs.getString("validity"),rs.getString("activation_mode"),rs.getString("system_creation_date"),rs.getString("system_update_date"),rs.getString("effective_date"),rs.getString("exp_date"),rs.getString("mdm_id"),rs.getString("brand"),rs.getString("status")));
		}
		
	}
	
	public List<View444ReccResponse> view444Recc(String circleId) {
		String sql = "";
		if(circleId.equalsIgnoreCase("pan") || circleId.equalsIgnoreCase("corp")) {
			sql = "select * from 444Bonus where (activation_mode like '%RECC')";
			return jdbcTemplateObject.query(sql, new Object[] {circleId},
					(rs, rowNum) -> new View444ReccResponse(rs.getString("circle_id"),rs.getString("rank_order"),rs.getString("offer_name"),rs.getString("bonus_name"),rs.getString("value"),rs.getString("amount"),rs.getString("threshold"),rs.getString("debit_event_id"),rs.getString("credit_event_id"),rs.getString("validity"),rs.getString("activation_mode"),rs.getString("system_creation_date"),rs.getString("system_update_date"),rs.getString("effective_date"),rs.getString("exp_date"),rs.getString("mdm_id"),rs.getString("brand"),rs.getString("status")));
		}else {
			sql = "select * from 444Bonus where circle_id=? and (activation_mode like '%RECC')";
			return jdbcTemplateObject.query(sql, new Object[] {circleId},
					(rs, rowNum) -> new View444ReccResponse(rs.getString("circle_id"),rs.getString("rank_order"),rs.getString("offer_name"),rs.getString("bonus_name"),rs.getString("value"),rs.getString("amount"),rs.getString("threshold"),rs.getString("debit_event_id"),rs.getString("credit_event_id"),rs.getString("validity"),rs.getString("activation_mode"),rs.getString("system_creation_date"),rs.getString("system_update_date"),rs.getString("effective_date"),rs.getString("exp_date"),rs.getString("mdm_id"),rs.getString("brand"),rs.getString("status")));
		}
		
	}
	
	public List<View444InResponse> view444In(String circleId) {
		String sql = "";
		if(circleId.equalsIgnoreCase("pan") || circleId.equalsIgnoreCase("corp")) {
			sql = "select * from 444Bonus where (activation_mode='UPI' or activation_mode='JAV')";
			return jdbcTemplateObject.query(sql, new Object[] {circleId},
					(rs, rowNum) -> new View444InResponse(rs.getString("circle_id"),rs.getString("rank_order"),rs.getString("offer_name"),rs.getString("bonus_name"),rs.getString("value"),rs.getString("amount"),rs.getString("threshold"),rs.getString("debit_event_id"),rs.getString("credit_event_id"),rs.getString("validity"),rs.getString("activation_mode"),rs.getString("system_creation_date"),rs.getString("system_update_date"),rs.getString("effective_date"),rs.getString("exp_date"),rs.getString("mdm_id"),rs.getString("brand"),rs.getString("status")));
		}else {
			sql = "select * from 444Bonus where circle_id=? and (activation_mode='UPI' or activation_mode='JAV')";
			return jdbcTemplateObject.query(sql, new Object[] {circleId},
					(rs, rowNum) -> new View444InResponse(rs.getString("circle_id"),rs.getString("rank_order"),rs.getString("offer_name"),rs.getString("bonus_name"),rs.getString("value"),rs.getString("amount"),rs.getString("threshold"),rs.getString("debit_event_id"),rs.getString("credit_event_id"),rs.getString("validity"),rs.getString("activation_mode"),rs.getString("system_creation_date"),rs.getString("system_update_date"),rs.getString("effective_date"),rs.getString("exp_date"),rs.getString("mdm_id"),rs.getString("brand"),rs.getString("status")));
		}
		
	}

	public List<ViewResponse> view(ViewRequest req) {
		String sql = "";
		sql = "select user_id,email_id,msisdn,last_login,expiry_date_time from tbl_user_mis_new where circle_id = ?";

		return jdbcTemplateObject.query(sql, new Object[] {req.getCircleId()},
				(rs, rowNum) -> new ViewResponse(rs.getString("user_id"),rs.getString("email_id"), rs.getString("msisdn"), rs.getString("last_login"),rs.getString("expiry_date_time")));

	}
	
	public List<DownloadResponse> download(DownloadRequest req) throws Exception {
		String sql = "";
		sql = "select msisdn,msi_type,circle_id from tbl_list where circle_id=? order by msisdn desc";

		return jdbcTemplateObject.query(sql, new Object[] {req.getCircleId()},
				(rs, rowNum) -> new DownloadResponse(rs.getString("msisdn"),rs.getString("msi_type"),rs.getString("circle_id")));

	}
	
	public ResponseObj upload(String fileName,String circleId,String retailerType) throws Exception {
		String sqlInsert = "";
		String sqlDelete = "";
		String sqlLoad = "";
		
		sqlInsert = "INSERT INTO tbl_list_backup (msisdn, msi_type,circle_id, date_time) SELECT msisdn, msi_type,circle_id, NOW() FROM tbl_list WHERE msi_type=? and circle_id=?";
		sqlDelete = "DELETE from  tbl_list WHERE msi_type=? and circle_id=?";
		sqlLoad = "LOAD DATA LOCAL INFILE '"+fileName+"' INTO TABLE tbl_list LINES TERMINATED BY '\r\n' (msisdn) SET msi_type = '"+retailerType+"' ,circle_id='"+circleId+"'";
		
		jdbcTemplateObject.update(sqlInsert, retailerType,circleId);		
		jdbcTemplateObject.update(sqlDelete,retailerType,circleId );
		jdbcTemplateObject.execute(sqlLoad);
		return null;
	}
}
