package spring.project.member.dao;

import java.util.List;

import spring.project.member.dto.MemberDto;
import spring.project.member.dto.UserVO;

//asd
public interface MemberDao {
	
	public UserVO login(MemberDto memberDto) throws Exception;
	
	public UserVO n_login(MemberDto memberDto) throws Exception;
	
    public int insertMember(MemberDto memberDto) throws Exception;

    public String confirmId(String id) throws Exception;

    public String updateMemberConfirm(String id) throws Exception;

    public String userCheck(String id) throws Exception;

    public MemberDto getMember(String id) throws Exception;

    public List<MemberDto> getList() throws Exception;

    public int updateMember(MemberDto memberDto) throws Exception;

    public int updateMemberPW(MemberDto memberDto) throws Exception;

    public int updateMemberID(MemberDto memberDto) throws Exception;

    public int updateMemberToken(MemberDto memberDto) throws Exception;

    public int updateMemberPWC(MemberDto memberDto) throws Exception;


}
