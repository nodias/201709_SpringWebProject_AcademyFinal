package spring.project.member.controller;

import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.project.member.dto.MemberDto;
import spring.project.member.dto.UserVO;
import spring.project.member.service.MemberService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/member/")
public class MemberController {

	@Inject
	private MemberService memberService;

	// 멤버 뷰
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login(@ModelAttribute("memberDto") MemberDto memberDto) {
	}

	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginProcess(MemberDto memberDto, HttpSession httpSession, Model model) throws Exception {
		UserVO userVO = memberService.login(memberDto);
		model.addAttribute("userVO", userVO);
	}

	@RequestMapping(value = "/naverLoginProcess", method = RequestMethod.GET)
	public String naverLoginProcess(MemberDto memberDto, HttpSession httpSession, Model model) throws Exception {
		// 네이버 로그인 후 받은 아이디, 닉네임을 이용, 회원가입 or 로그인
		UserVO userVO = memberService.naverLoginProcess(memberDto);
		if (userVO != null) {
			model.addAttribute("userVO", userVO);
			return "redirect:../index";
		} else {
			model.addAttribute("memberDto", memberDto);
			return "member/join";
		}
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void join() {
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinProcess(MemberDto memberDto) throws Exception {
		int ri = memberService.join(memberDto);
		if (ri == 1) {
			return "member/login";
		}
		return "member/join";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutProcess(HttpSession httpSession) {
		httpSession.invalidate();
		return "redirect:/index";
	}

	// 비번찾기 폼
	@RequestMapping(value = "/pwfind1", method = RequestMethod.GET)
	public String pwfind1() {
		return "member/pwfind1";
	}

	// 토큰처리
	@RequestMapping(value = "/pwfindProcess", method = RequestMethod.POST)
	public String pwfindProcess(MemberDto memberDto) throws Exception {
		memberService.pwfind(memberDto);
		return "redirect:/index";
	}

	// 비번변경 폼
	@RequestMapping(value = "/pwfind2", method = RequestMethod.GET)
	public String pwfind2() {
		return "member/pwfind2";
	}

	// 비번변경 처리
	@RequestMapping(value = "/pwfindProcess2", method = RequestMethod.POST)
	public String pwfindProcess2(MemberDto memberDto) throws Exception {
		memberService.pwfind2(memberDto);
		return "redirect:/member/pwfind3";
	}

	// 비번변경 완료
	@RequestMapping(value = "/pwfind3", method = RequestMethod.GET)
	public String pwfind3() {
		return "member/pwfind3";
	}

	@RequestMapping(value = "/callback", method = RequestMethod.GET)
	public String callback() {
		return "member/callback";
	}

	@RequestMapping(value = "/userConfProcess", method = RequestMethod.POST)
	public void userConfProcess(@ModelAttribute MemberDto memberDto, HttpSession httpSession, PrintWriter out) throws Exception {
		System.out.println(memberDto.toString());
		memberService.userConfProcess(memberDto, httpSession, out);
	}

	// @RequestMapping(value = "/imageThum", method = RequestMethod.POST)
	// public void imageThum() throws Exception {
	// memberService.imageThum();
	// }

	//
	// @RequestMapping(value = "/adminMemberList", method = RequestMethod.GET)
	// public String adminMemberList(HttpServletRequest request, HttpServletResponse
	// response) throws Exception {
	// return memberService.adminMemberList(request, response);
	// }
	//
	// @RequestMapping(value = "/adminMemberInfo", method = RequestMethod.GET)
	// public String adminMemberInfo(HttpServletRequest request, HttpServletResponse
	// response) throws Exception {
	// return memberService.adminMemberInfo(request, response);
	// }
	//
	// @RequestMapping(value = "/adminMemberDelete", method = RequestMethod.GET)
	// public String adminMemberDelete(HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// return memberService.adminMemberDelete(request, response);
	// }

}
