package com.spice.bonus.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UploadRequest {

	private String retailerType;
	private String circleId;

	private MultipartFile fileName;
}
