package com.spice.bonus.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spice.bonus.dao.BonusNewDao;
import com.spice.bonus.request.AddUserRequest;
import com.spice.bonus.request.ChangePassword;
import com.spice.bonus.request.DeleteUser;
import com.spice.bonus.request.DownloadRequest;
import com.spice.bonus.request.EditUserRequest;
import com.spice.bonus.request.GateWayDailyMisRequest;
import com.spice.bonus.request.GateWayHourlyRequest;
import com.spice.bonus.request.LoginRequest;
import com.spice.bonus.request.MenuHitsRequest;
import com.spice.bonus.request.UploadRequest;
import com.spice.bonus.request.ViewRequest;
import com.spice.bonus.response.LoginResponse;
import com.spice.bonus.response.ResponseObj;
import com.spice.bonus.response.ResponseObjJSON;
import com.spice.bonus.utility.UtilityData;

@Component
@Service
public class BonusNewService {
	@Autowired
	private BonusNewDao bonusdao;

	@Autowired
	private UtilityData util;
	long diffDays = 0;
	long expiryDiff = 0;

	// Save the uploaded file to this folder
	//private static String UPLOADED_FOLDER = "/home/ussdaps/retailermaster/uploadedFiles/";
	private static String UPLOADED_FOLDER = "/home/ussdaps/ussd_log/retailermaster/uploadedFiles/";

	public ResponseObj misLogin(LoginRequest loginReq) throws Exception {
		List<LoginResponse> login = bonusdao.misLogin(loginReq);
		boolean checkList = login.isEmpty();
		if (checkList == true) {
			return new ResponseObj(null, "success", "Wrong Username or password", 100);

		} else {
			for (LoginResponse data : login) {
				if (data.getUser_type().equalsIgnoreCase("admin")) {
					return new ResponseObj(login);
				} else {
					String[] lastLogin = data.getLastLogin().split(" ");
					String[] expiry = data.getExpiry_date_time().split(" ");
					String currDt = util.getCurrentDate();

					LocalDate d1 = LocalDate.parse(lastLogin[0], DateTimeFormatter.ISO_LOCAL_DATE);
					LocalDate d2 = LocalDate.parse(currDt, DateTimeFormatter.ISO_LOCAL_DATE);
					Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
					diffDays = diff.toDays();

					LocalDate d3 = LocalDate.parse(expiry[0], DateTimeFormatter.ISO_LOCAL_DATE);
					LocalDate d4 = LocalDate.parse(currDt, DateTimeFormatter.ISO_LOCAL_DATE);
					Duration expdiff = Duration.between(d3.atStartOfDay(), d2.atStartOfDay());
					expiryDiff = expdiff.toDays();

					if (expiryDiff > 0) {
						bonusdao.updateStatus(loginReq, "disabled");
						System.out.println("User:" + loginReq.getUserName() + " Password has been Expired");
						return new ResponseObj(null, "success", "Disabled", 100);
					} else if (diffDays <= 25) {
						bonusdao.updateStatus(loginReq, "active");
						System.out.println("User:" + loginReq.getUserName() + ", your account is active");
						return new ResponseObj(login);
					} else if (diffDays <= 30) {
						bonusdao.updateStatus(loginReq, "inactive");
						System.out.println("User:" + loginReq.getUserName() + ", you have logined before 50 days");
						return new ResponseObj(null, "success", "Inactive", 100);
					}
				}
			}
		}
		return new ResponseObj(null, "success", "Disabled", 100);
	}

	public ResponseObj addUser(AddUserRequest uReq) throws Exception {
		return new ResponseObj(bonusdao.addUser(uReq, util.getDateTime(), util.getPlusDate(), "active"));
	}

	public ResponseObj updateUser(EditUserRequest uReq) throws Exception {
		return new ResponseObj(bonusdao.editUser(uReq));
	}

	public ResponseObj deleteUser(DeleteUser uReq) throws Exception {
		return new ResponseObj(bonusdao.deleteUser(uReq));
	}

	public ResponseObj changePassword(ChangePassword uReq) throws Exception {
		return new ResponseObj(bonusdao.changePassword(uReq, util.getPlusDate()));
	}

	public ResponseObj circle() throws Exception {
		return new ResponseObj(bonusdao.circle());
	}

	public ResponseObj userList() throws Exception {
		return new ResponseObj(bonusdao.userList());
	}
	
	public ResponseObj view444Upss(String circleId) throws Exception {
		return new ResponseObj(bonusdao.view444Upss(circleId));
	}
	
	public ResponseObj view444Recc(String circleId) throws Exception {
		return new ResponseObj(bonusdao.view444Recc(circleId));
	}
	
	public ResponseObj view444In(String circleId) throws Exception {
		return new ResponseObj(bonusdao.view444In(circleId));
	}
	
	public ResponseObj view(ViewRequest req) throws Exception {
		return new ResponseObj(bonusdao.view(req));
	}

	public ResponseObj download(DownloadRequest req) throws Exception {
		return new ResponseObj(bonusdao.download(req));
	}

	public ResponseObj upload(MultipartFile file,String circleId,String retailerType) throws Exception {
		String fileName = null;

		try {
			if (file == null || file.isEmpty()) {
				System.out.println("File is empty or not selected by user");
			} else {
				fileName = saveUploadedFiles(Arrays.asList(file));
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception in file uploading");
		}

		return new ResponseObj(bonusdao.upload(UPLOADED_FOLDER+fileName,circleId,retailerType));
	}

	private String saveUploadedFiles(List<MultipartFile> files) throws IOException {
		String uplodedFile = null;
		for (MultipartFile file : files) {

			if (file.isEmpty()) {
				continue; // next pls
			}

			String extension = util.getFileExtension(file.getOriginalFilename());
			String fileName = util.getFileName(file.getOriginalFilename());
			uplodedFile = fileName + "." + extension;
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + fileName + "." + extension);
			Files.write(path, bytes);
		}
		return uplodedFile;
	}

}