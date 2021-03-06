package com.kh.pitapet.member.model.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.pitapet.member.model.dao.MemberMapper;
import com.kh.pitapet.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	

	@Override
	public Member findMemberById(String id) {
		
		return mapper.findMemberById(id);
	}
	
	@Override
	public Member login(String id, String password) {
		Member member = null;

		member = mapper.findMemberById(id);
		
		return member != null && 
				passwordEncoder.matches(password, member.getPassword()) ? member : null;
	}

	@Override
	@Transactional
	public int save(Member member) {
		int result = 0;
		
		if(member.getNo() != 0) {
			result = mapper.updateMember(member);
		} else {
			member.setPassword(passwordEncoder.encode(member.getPassword()));
			
			result = mapper.insertMember(member);
		}
		
		return result;
	}

	@Override
	public Boolean isDuplicateID(String id) {
		
		return mapper.findMemberById(id) != null;	
	}
	
	@Override
	public Boolean isDuplicateEmail(String email) {
		
		return mapper.findMemberByEmail(email) != null;
	}

	@Override
	public int delete(int no) {
		
		return mapper.deleteMember(no);
	}

	@Override
	public Member findId(String name, String email) {
		Member member = null;
		
		member = mapper.findId(name, email);
		
		return member;
	}
	
	@Override
	public void findPw(String id, String email) {
		String temppassword = maketemppassword();
		// ???????????? ???????????? ????????? ???????????? ??????
		sendEmail(email, temppassword);
		
		String encodedtemppassword = passwordEncoder.encode(temppassword);
		
		// ???????????? ??????????????? ????????????
		mapper.updatePw(id, encodedtemppassword);
		
	}
	
	// ????????? 8?????? ????????? ???????????? ??????
	private String maketemppassword() {
		
		Random rand = new Random();

		String temppassword = Integer.toString( rand.nextInt(8) + 1);

		for (int i = 0; i < 7; i++) {
		    temppassword += Integer.toString(rand.nextInt(9));
		}
		
		return temppassword;
	}
	
	// ????????? ?????????
	private void sendEmail(String userEmail, String temppassword) {
		String adminId = "pitapettest@gmail.com";
		String adminPw = "yss2623!@";
		String subject = "???????????? ????????????";
		String body = "???????????? ????????? ??????????????? " + temppassword + " ?????????.";
		int PORT = 465;
		
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", PORT); 
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.ssl.enable", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
	        props.put("mail.smtp.socketFactory.fallback", "true");
	        
	        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(adminId, adminPw);
	            }
	        });
	        
	        MimeMessage msg = new MimeMessage(session);
	        msg.setFrom(new InternetAddress(adminId));
	        
	        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
	        msg.setSubject(subject);
	        msg.setContent(body, "text/html;charset=UTF-8");
	        
	        Transport.send(msg);
            System.out.println("Email sent!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// ??????????????? ???????????? ???????????? ????????? ??????
	@Override
	public String findEmail(String id, String email) {
		String returnEmail = mapper.findEmail(id, email);
		
		return returnEmail;
	}

	@Override
	public boolean pwCheck(String userId, String userPw) {
		
		String dbPw = mapper.pwCheck(userId);

		if(passwordEncoder.matches(userPw, dbPw)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void updatePw(String userId, String newPw) {
		String encodedNewPw = passwordEncoder.encode(newPw);
		
		// ???????????? ??????????????? ????????????
		mapper.updatePw(userId, encodedNewPw);
		
	}

	@Override
	public String getPet(String userId) {
		String pet = mapper.findPet(userId);
		
		return pet;
	}

	@Override
	public void updatePet(String userId, String newPet) {
		
		mapper.updatePet(userId, newPet);
	}

	

	


}