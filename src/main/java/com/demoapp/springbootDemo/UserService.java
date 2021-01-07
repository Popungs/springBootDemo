package com.demoapp.springbootDemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	List<LoginInfo> LiList = new ArrayList<>();

	public boolean loginValid(String uname, String pass) {
		if (LiList.size() == 0) {
			System.out.println("list is empty");
			return false;
		}
		
		for (LoginInfo li : LiList) {
			String liUname = li.getUname();
			String liPass = li.getPassword();
			if (liUname.equals(uname) && liPass.equals(pass)) {
				System.out.println("Found credentials in database. Username : " + uname);
				return true;
			}
		}
		return false;
	}

	public boolean registerValid(String uname, String pass, String email, String city) {
		LoginInfo newLogInfo = new LoginInfo();
		
		if ((!uname.equals(null) && !pass.equals(null) && !email.equals(null) && !city.equals(null))) {
			newLogInfo.setUname(uname);
			newLogInfo.setPassword(pass);
			newLogInfo.setEmail(email);
			newLogInfo.setCity(city);
		} else {
			System.out.println("null input detected");
			return false;
		}
		
		if (LiList.size() > 0) { // size is greater than 1
				for (LoginInfo li : LiList) {
					String liUname = li.getUname();
					String liEmail = li.getEmail();
					System.out.println(liUname + " " + liEmail + " iterating List" );
					if (uname.equals(liUname) || email.equals(liEmail)) {
						return false;
					}
				}
				LiList.add(newLogInfo);
				System.out.println(newLogInfo);
				System.out.println("list has : " + LiList.size() + " credentials");
				return true;
		}  else { // size is 0
				LiList.add(newLogInfo);
				System.out.println(newLogInfo);
				System.out.println("list has : " + LiList.size() + " credentials");
				return true;
		}

	}
	public List<LoginInfo> loadUserInfo() {
		return LiList;
	}
	public boolean findUser(String uname) {
		for (LoginInfo l :LiList) {
			if (l.getUname().equals(uname)) {
				System.out.println("User found! : " + uname + " user email : " +l.getEmail());
				return true;
			}
		}
		return false;
	}

	public boolean updateUser(String uname, String email) {
		for (LoginInfo l :LiList) {
			if (l.getUname().equals(uname)) { // found 
				System.out.println("user found! user email : " + l.getEmail() + "  updating its email...");
				l.setEmail(email);
				System.out.println("User new email : " + l.getEmail() );
				return true;
			}
		}
		return false;
	}
	
	public boolean deleteUser(String uname) {
		if (LiList.size() == 0) {
			System.out.println("list is empty!");
			return false;
		}
		
		for (LoginInfo l :LiList) {
			if (l.getUname().equals(uname)) { // user found
				LiList.remove(l);
				return true;
			}
		}
		return false;
	}
}
