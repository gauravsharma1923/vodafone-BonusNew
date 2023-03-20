package com.spice.bonus.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spice.bonus.request.AddUserRequest;
import com.spice.bonus.request.ChangePassword;
import com.spice.bonus.request.DeleteUser;
import com.spice.bonus.request.DownloadRequest;
import com.spice.bonus.request.EditUserRequest;
import com.spice.bonus.request.LoginRequest;
import com.spice.bonus.request.ViewRequest;
import com.spice.bonus.response.ResponseObj;
import com.spice.bonus.service.BonusNewService;

@RestController
@RequestMapping("/api/bonus")
public class BonusNewController {
	@Autowired
	private BonusNewService service;

	@PostMapping(value = "/login", consumes = "application/json")
	public ResponseObj misLogin(@RequestBody LoginRequest loginReq)
			throws Exception {
		return service.misLogin(loginReq);
	}
	
	@PostMapping(value = "/addUser", consumes = "application/json", produces = "application/json")
	public ResponseObj addUser(@RequestBody AddUserRequest userReq)
			throws Exception {
		return service.addUser(userReq);
	}
	
	@PostMapping(value = "/editUser", consumes = "application/json", produces = "application/json")
	public ResponseObj updateUser(@RequestBody EditUserRequest userReq)
			throws Exception {
		return service.updateUser(userReq);
	}
	
	@PostMapping(value = "/deleteUser", consumes = "application/json", produces = "application/json")
	public ResponseObj deleteUser(@RequestBody DeleteUser userReq)
			throws Exception {
		return service.deleteUser(userReq);
	}
	
	@PostMapping(value = "/changePassword", consumes = "application/json", produces = "application/json")
	public ResponseObj changePassword(@RequestBody ChangePassword userReq)
			throws Exception {
		return service.changePassword(userReq);
	}
	
	@GetMapping(value = "/circle")
	public ResponseObj circle() throws Exception {
		return service.circle();
	}
	
	@GetMapping(value = "/userList")
	public ResponseObj userList() throws Exception {
		return service.userList();
	}
	
	@PostMapping(value = "/view444upss")
	public ResponseObj view444Upss(@RequestParam("circleId") String circleId) throws Exception {
		return service.view444Upss(circleId);
	}
	
	@PostMapping(value = "/view444recc")
	public ResponseObj view444Recc(@RequestParam("circleId") String circleId) throws Exception {
		return service.view444Recc(circleId);
	}
	
	@PostMapping(value = "/view444in")
	public ResponseObj view444In(@RequestParam("circleId") String circleId) throws Exception {
		return service.view444In(circleId);
	}
	
	@PostMapping(value = "/view",consumes = "application/json")
	public ResponseObj view(@RequestBody ViewRequest req) throws Exception {
		return service.view(req);
	}
	
	@PostMapping(value = "/download",consumes = "application/json")
	public ResponseObj download(@RequestBody DownloadRequest req) throws Exception {
		return service.download(req);
	}
	
	
	@PostMapping(value = "/upload",consumes = "multipart/form-data",produces="application/json")
	public ResponseObj upload(@RequestPart("file") MultipartFile file,@RequestParam("circleId") String circleId,@RequestParam("retailerType") String retailerType ,HttpServletRequest request) throws Exception {
		//System.out.println("body:::"+getBody(request));
		return service.upload(file,circleId,retailerType);
	}
	
	public static String getBody(HttpServletRequest request) throws IOException {	
	    String body = null;
	    StringBuilder stringBuilder = new StringBuilder();
	    BufferedReader bufferedReader = null;

	    try {
	        InputStream inputStream = request.getInputStream();
	        if (inputStream != null) {
	            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	            char[] charBuffer = new char[128];
	            int bytesRead = -1;
	            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	                stringBuilder.append(charBuffer, 0, bytesRead);
	            }
	        } else {
	            stringBuilder.append("");
	        }
	    } catch (IOException ex) {
	        throw ex;
	    } finally {
	        if (bufferedReader != null) {
	            try {
	                bufferedReader.close();
	            } catch (IOException ex) {
	                throw ex;
	            }
	        }
	    }

	    body = stringBuilder.toString();
	    return body;
	}
}