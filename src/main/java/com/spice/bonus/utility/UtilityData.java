package com.spice.bonus.utility;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class UtilityData {
	Date currentDate = new Date();
	public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
	
	private final static String salt = "Dbbwjvj$%)GE$5SGr@3VsHYUMas2323E4d57vfBfFSTRU@!DSH(*%FDSdfg13sgfsg";

	public String getMD5(String input) throws NoSuchAlgorithmException {
		input = input + salt;
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(input.getBytes());
		BigInteger number = new BigInteger(1, messageDigest);
		String hashtext = number.toString(16);
		while (hashtext.length() < 32) {
			hashtext = "0" + hashtext;
		}
		return hashtext;
	}

	public static String getMachineIp() {
		try {
			return String.valueOf(InetAddress.getLocalHost());
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static Node insert(String data, Node node, int i) {

		if (node != null) {
			//System.out.println(node.toString());
			if (data.equalsIgnoreCase(node.text) && i == 0) {
				
				return node;
			}
			//System.out.println("--"+node.getNodes());
			for (Node node1 : node.getNodes()) {
				//System.out.println("-->"+node1.text);
				if (node1.text.equalsIgnoreCase(data)) {
					return node1;
				}
			}
			Node node1 = new Node();
			node1.text = data;
			node.getNodes().add(node1);
			return node1;
		} else {
			node = new Node();
			node.text = data;
			return node;
		}
	}
	/*
	 * public String getCurrentDate() {
	 * 
	 * //convert date to calendar Calendar c = Calendar.getInstance();
	 * c.setTime(currentDate);
	 * 
	 * // convert calendar to date Date currentDate = c.getTime(); SimpleDateFormat
	 * sdf = new SimpleDateFormat(DATE_FORMAT_NOW); String finalCurrentdate =
	 * sdf.format(currentDate);
	 * System.out.println("Today Date-->"+finalCurrentdate); return
	 * finalCurrentdate; }
	 */
	
	public String getCurrentDate() {
		
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDate localDate = localDateTime.toLocalDate();
		System.out.println(localDate);
		return localDate.toString();
	}
	
	public String getPlusDate() {
		
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDate localDate = localDateTime.toLocalDate().plusDays(30);
		System.out.println(localDate);
		return localDate.toString();
	}
	
	public String getDateTime() {
		  LocalDateTime dateTime = LocalDateTime.now();
	      System.out.println("Current date-time: "+dateTime);
	      return dateTime.toString();
	}
	
	public static String getFileExtension(String fileName) {
		// String fileName = file.getName();
		String extension = "";
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			extension = fileName.substring(fileName.lastIndexOf(".") + 1);

		return extension;
	}

	public static String getFileName(String fileName) {
		// String fileName = file.getName();
		String extension = "";
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			extension = fileName.substring(0, fileName.lastIndexOf("."));

		return extension;
	}
	
	/*
	 * public static void main (String[] args) { UtilityData obj = new
	 * UtilityData(); obj.getDatTim(); }
	 */
	 
	

	/*
	 * public static void downloadFile(HttpServletRequest request,
	 * HttpServletResponse response, String filePath) throws IOException { File file
	 * = new File(filePath); if (!file.exists()) { String errorMessage =
	 * "Sorry. The file you are looking for does not exist"; OutputStream
	 * outputStream = response.getOutputStream();
	 * outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
	 * outputStream.close(); return; } String mimeType =
	 * URLConnection.guessContentTypeFromName(file.getName()); if (mimeType == null)
	 * { mimeType = "application/csv"; } response.setContentType(mimeType);
	 * response.setHeader("Content-Disposition", String.format("inline; filename=\""
	 * + file.getName() + "\"")); response.setContentLength((int) file.length());
	 * InputStream inputStream = new BufferedInputStream(new
	 * FileInputStream(filePath)); FileCopyUtils.copy(inputStream,
	 * response.getOutputStream()); if (file.exists()) { file.delete(); } }
	 */
}
