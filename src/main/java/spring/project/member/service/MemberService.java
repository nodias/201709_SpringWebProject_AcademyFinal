package spring.project.member.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpSession;

import spring.project.member.dto.MemberDto;
import spring.project.member.dto.UserVO;

public interface MemberService {
	public static final int SUCCESS = 1;
	public static final int FAIL = 0;

	public UserVO login(MemberDto memberDto) throws Exception;

	public int join(MemberDto memberDto) throws Exception;

	public String pwfind(MemberDto memberDto) throws Exception;

	public String naverMailSend(MemberDto memberDto) throws Exception;

	public String pwfind2(MemberDto memberDto) throws Exception;

	public UserVO naverLoginProcess(MemberDto memberDto) throws Exception;
	
	public void userConfProcess(MemberDto memberDto, HttpSession httpSession, PrintWriter out) throws Exception;
	
	// public String adminMemberList(HttpServletRequest request, HttpServletResponse
	// response) throws Exception;
	// public String adminMemberInfo(HttpServletRequest request, HttpServletResponse
	// response) throws Exception;
	// public String adminMemberDelete(HttpServletRequest request,
	// HttpServletResponse response) throws Exception;
	// public void imageThum(HttpServletRequest request, HttpServletResponse
	// response) throws Exception;
	// public String withdraw(HttpServletRequest request, HttpServletResponse
	// response) throws Exception;

}
