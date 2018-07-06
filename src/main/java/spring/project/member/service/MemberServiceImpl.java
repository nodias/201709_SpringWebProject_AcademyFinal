package spring.project.member.service;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.inject.Inject;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import spring.project.member.dao.MemberDao;
import spring.project.member.dto.MemberDto;
import spring.project.member.dto.UserVO;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDao memberDao;

	// 로그인
	@Override
	public UserVO login(MemberDto memberDto) throws Exception {
		return memberDao.login(memberDto);
	}

	@Override
	public UserVO naverLoginProcess(MemberDto memberDto) throws Exception {
		UserVO userVO = memberDao.n_login(memberDto);
		if (userVO != null) {
			return userVO;
		}
		return null;

		// String name = memberDto.getName();
		// if (cid == id) { // id가 존재, 바로 로그인
		// return memberDto;
		// request.setAttribute(id, "id");
		// request.setAttribute(name, "name");
		// url = "/member/join";
		// } else { // id가 없음, 회원가입으로
		// return null;
		// HttpSession session = request.getSession();
		// MemberDto memberDto = memberDao.getMember(id);
		// session.setAttribute("id", id);
		// session.setAttribute("name", memberDto.getName());
		// session.setAttribute("grade", memberDto.getGrade());
		// url = "/index";
		// }
	}

	@Override
	public int join(MemberDto memberDto) throws Exception {
		return memberDao.insertMember(memberDto);
	}

	@Override
	public String pwfind(MemberDto memberDto) throws Exception {
		String id = memberDto.getId();
		if (memberDao.confirmId(id).equals(id)) {
			SecureRandom random = new SecureRandom();
			String token = new BigInteger(130, random).toString(32);
			memberDto.setToken(token);
			int suc = memberDao.updateMemberToken(memberDto);
			if (suc == SUCCESS) {
				naverMailSend(memberDto);
			} else {
				System.out.println("fail");
			}
		} else {
			log.debug("에런데??");
			return null;
		}

		return null;
	}

	@Override
	public String naverMailSend(MemberDto memberDto) throws Exception {
		// 메일 관련 정보
		String host = "smtp.naver.com";
		final String username = "nodias46@naver.com";
		final String password = "roqkfgkwk!";
		int port = 465;

		// 메일 내용
		String id = memberDto.getId();
		String token = memberDto.getToken();
		String recipient = id;
		String subject = "MovieR 비밀번호 찾기 서비스";
		String body = "<h3>MovieR의 " + id + "의 비밀번호 찾기를 요청하셨습니다. 맞으시면 아래의 링크를 클릭해 주세요.</h3><br>"
				+ " <a href='http://localhost:8081/MovieR/member/pwfind2?id=" + id + "&token=" + token
				+ "'>비밀번호 변경하러 가기!!</a>";

		Properties props = System.getProperties();

		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", host);

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			String un = username;
			String pw = password;

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(un, pw);
			}
		});
		session.setDebug(true); // for debug

		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress("nodias46@naver.com"));

			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			msg.setSubject(subject);
			msg.setSentDate(new Date());

			// 파일 첨부시에는 BodyPart를 사용
			// msg.setText(body);

			// 파일첨부를 위한 Multipart
			Multipart multipart = new MimeMultipart();

			// BodyPart를 생성
			BodyPart bodyPart = new MimeBodyPart();
			bodyPart.setText(body);

			// 1. Multipart에 BodyPart를 붙인다.
			multipart.addBodyPart(bodyPart);

			// 2. 이미지를 첨부한다.
			bodyPart = new MimeBodyPart();
			String filename = "C:/Users/nodia/Downloads/111이미지/base.jpg";
			DataSource source = (DataSource) new FileDataSource(filename);
			bodyPart.setDataHandler(new DataHandler(source));
			bodyPart.setFileName(filename);
			// Trick is to add the content-id header here
			bodyPart.setHeader("Content-ID", "image_id");
			multipart.addBodyPart(bodyPart);

			// third part for displaying image in the email body
			bodyPart = new MimeBodyPart();
			bodyPart.setContent("<h1>MovieR을 이용해주셔서 감사합니다.</h1>" + "<img src='cid:image_id'><br>" + body,
					"text/html; charset=utf-8");
			multipart.addBodyPart(bodyPart);

			// 이메일 메시지의 내용에 Multipart를 붙인다.
			msg.setContent(multipart, "text/html; charset=utf-8");
			Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String pwfind2(MemberDto memberDto) throws Exception {
		String id = memberDto.getId();
		String token = memberDto.getToken();
		String pw = memberDto.getPw();
		if (token.equals(memberDao.updateMemberConfirm(id))) {
			int suc = memberDao.updateMemberPWC(memberDto);
			if (suc == SUCCESS) {
			} else {
				System.out.println("뭔가 문제가 생김111");
			}
		} else {
			System.out.println("뭔가 문제가 생김1221");
		}
		;
		return null;
	}

	@Override
	public void userConfProcess(MemberDto memberDto, HttpSession httpSession, PrintWriter out) throws Exception {
		String msg = "b";
		UserVO userVO = (UserVO) httpSession.getAttribute("login");
		String id = (String) userVO.getId();
		String name = memberDto.getName();
		String pw = memberDto.getPw();
		String new_pw = memberDto.getNew_pw();
		memberDto.setId(id);
		if (pw.equals("")) { // 닉네임만 변경 할때
			memberDao.updateMemberID(memberDto);
			userVO.setName(name);
			httpSession.setAttribute("login", userVO);
		} else {
			if (memberDao.userCheck(id).equals(pw)) {
				memberDto.setPw(new_pw);
				memberDao.updateMemberPW(memberDto);
				userVO.setName(name);
				httpSession.setAttribute("login", userVO);
			} else {
				msg = "a";
			}
			out.print(msg);
		}
	}

}

// @Override
// public String pwfind(HttpServletRequest request, HttpServletResponse
// response) throws Exception {
// String url = "redirect:/member/pwfind1";
// System.out.println("PwfindProcess");
// SecureRandom random = new SecureRandom();
// String token = new BigInteger(130, random).toString(32);
// String id = request.getParameter("id");
// if (memberDao.confirmId(id) != null) {
// MemberDto memberDto = new MemberDto();
// memberDto.setId(id);
// memberDto.setToken(token);ㅈㅈ
// int suc = memberDao.updateMemberToken(memberDto);
// System.out.println(suc);
// if (suc == SUCCESS) {
// request.setAttribute("id", id);
// request.setAttribute("token", token);
// naverMailSend(request, response);
// url = "redirect:/member/pwfind3";
// } else {
// System.out.println("fail");
// }
// }
// return url;
// }
//
// @Override
// public String join(HttpServletRequest request, HttpServletResponse response)
// throws Exception {
// System.out.println("join method");
// String id = request.getParameter("id");
// String pw = request.getParameter("pw");
// String name = request.getParameter("name");
// String img = request.getParameter("img");
// String reg_date = request.getParameter("reg_date");
// String mod_date = request.getParameter("mod_date");
// String grade = request.getParameter("grade");
// MemberDto memberDto = new MemberDto();
// memberDto.setId(id);
// memberDto.setPw(pw);
// memberDto.setName(name);
// memberDto.setImg(img);
// memberDto.setReg_date(reg_date);
// memberDto.setMod_date(mod_date);
// memberDto.setGrade(grade);
// int result = memberDao.insertMember(memberDto);
// String url = null;
// if (result == SUCCESS) {
// url = "redirect:/member/login";
// } else if (result == FAIL) {
// url = "redirect:/member/join";
// }
// return url;
//
// }
//
// @Override
// public String logout(HttpServletRequest request, HttpServletResponse
// response) throws Exception {
// System.out.println("위치 : Logout");
// String url = "redirect:../index";
// HttpSession session = request.getSession();
// session.invalidate();
// return url;
// }
//
// @Override
// public String pwfind(HttpServletRequest request, HttpServletResponse
// response) throws Exception {
// String url = "redirect:/member/pwfind1";
// System.out.println("PwfindProcess");
// SecureRandom random = new SecureRandom();
// String token = new BigInteger(130, random).toString(32);
// String id = request.getParameter("id");
// if (memberDao.confirmId(id) != null) {
// MemberDto memberDto = new MemberDto();
// memberDto.setId(id);
// memberDto.setToken(token);
// int suc = memberDao.updateMemberToken(memberDto);
// System.out.println(suc);
// if (suc == SUCCESS) {
// request.setAttribute("id", id);
// request.setAttribute("token", token);
// naverMailSend(request, response);
// url = "redirect:/member/pwfind3";
// } else {
// System.out.println("fail");
// }
// }
// return url;
// }
//

//
// @Override
// public String pwfind2(HttpServletRequest request, HttpServletResponse
// response) throws Exception {
// String id = request.getParameter("id");
// String token = request.getParameter("token");
// String pw = request.getParameter("pw");
// String url = "";
// if (token.equals(memberDao.updateMemberConfirm(id))) {
// MemberDto memberDto = new MemberDto();
// memberDto.setId(id);
// memberDto.setPw(pw);
// int suc = memberDao.updateMemberPWC(memberDto);
// if (suc == SUCCESS) {
// url = "redirect:/member/login";
// } else {
// System.out.println("뭔가 문제가 생김111");
// }
// } else {
// System.out.println("뭔가 문제가 생김1221");
// }
// ;
// return url;
// }
//

//
// @Override
// public String adminMemberList(HttpServletRequest request, HttpServletResponse
// response) throws Exception {
// System.out.println("위치 : AdminMemberList");
// String url = "/member/adminMemberList";
// HttpSession session = request.getSession();
// if (!(((UserVO)session.getAttribute("login")).getId().equals("admin"))) {
// url = "/index";
// }
// List<MemberDto> list = memberDao.getList();
//
// request.setAttribute("arraylist", list);
// return url;
// }
//
// @Override
// public String adminMemberInfo(HttpServletRequest request, HttpServletResponse
// response) throws Exception {
// String url = "/member/adminMemberInfo";
// String id = request.getParameter("id");
// MemberDto memberDto = memberDao.getMember(id);
// request.setAttribute("dto", memberDto);
// return url;
// }
//
// @Override
// public String adminMemberDelete(HttpServletRequest request,
// HttpServletResponse response) throws Exception {
// String url = "redirect:/member/adminMemberList";
// String id = request.getParameter("id");
//// memberDao.deleteMember(id);
// return url;
// }
//
// @Override
// public void imageThum(HttpServletRequest request, HttpServletResponse
// response) throws Exception {
// request.setCharacterEncoding("UTF-8");
// response.setContentType("text/html;charset=UTF-8");
// HttpSession session = request.getSession();
// UserVO userVO = (UserVO) session.getAttribute("login");
// String id = userVO.getId();
// PrintWriter out = response.getWriter();
// String imagePath =
// "D:\\PROGRAMMING\\SPRING\\BEGIN\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp4\\wtpwebapps\\movier\\resources\\img";
// int size = 1 * 1024 * 1024;
// String filename = "";
// try {
// MultipartRequest multipartRequest = new MultipartRequest(request, imagePath,
// size, "UTF-8", new DefaultFileRenamePolicy());
// Enumeration<?> enumeration = multipartRequest.getFileNames();
// log.info("wow4");
// String file = (String) enumeration.nextElement();
// log.info("wow5");
// filename = multipartRequest.getFilesystemName(file);
// } catch (Exception e) {
// log.info("wow6");
// e.printStackTrace();
// }
//
// ParameterBlock parameterBlock = new ParameterBlock();
// parameterBlock.add(imagePath + "\\" + filename);
// RenderedOp renderedOp = JAI.create("fileload", parameterBlock);
// BufferedImage bufferedImage = renderedOp.getAsBufferedImage();
// BufferedImage bufferedImage2 = new BufferedImage(200, 200,
// BufferedImage.TYPE_INT_RGB);
// Graphics2D graphics2d = bufferedImage2.createGraphics();
// graphics2d.drawImage(bufferedImage, 0, 0, 200, 200, null);
// File file = new File(imagePath + "/" + id + ".jpg");
// File file2 = new File(imagePath + "/" + id + "_ajax.jpg");
// ImageIO.write(bufferedImage2, "jpg", file);
// ImageIO.write(bufferedImage2, "jpg", file2);
// out.println("-썸네일 이미지-<br><img
// src=http://localhost:8081/MovieR/resources/img/" + id + ".jpg>");
// }
//
//
// @Override
// public void userConfProcess(HttpServletRequest request, HttpServletResponse
// response) throws Exception {
//
// System.out.println("위치 : UserConfProcess");
// String msg = "설정이 적용되었습니다.";
// msg = "b";
// MemberDto memberDto = new MemberDto();
// HttpSession session = request.getSession();
// UserVO userVO = (UserVO)session.getAttribute("login");
// String id = (String)userVO.getId();
// String name = request.getParameter("name");
// String pw = request.getParameter("pw");
// String new_pw = request.getParameter("new_pw");
// System.out.println(id + ":" + name + ":" + pw + ":" + new_pw);
// memberDto.setId(id);
// memberDto.setName(name);
// userVO.setId(id);
// userVO.setName(name);
// System.out.println(pw);
// if (request.getParameter("pw") == "") { // 닉네임만 변경 할때
// memberDao.updateMemberID(memberDto);
// session.setAttribute("login", userVO);
// } else {
// System.out.println(memberDao.userCheck(id));
// System.out.println("aasdas");
// System.out.println(pw);
// if (memberDao.userCheck(id).equals(pw)) {
// memberDao.updateMemberPW(memberDto);
// session.setAttribute("login", userVO);
// memberDto.setPw(new_pw);
// } else {
// msg = "a";
// }
// PrintWriter out = response.getWriter();
// out.print(msg);
// System.out.println("printWriter 직후 : name");
// }
// }
