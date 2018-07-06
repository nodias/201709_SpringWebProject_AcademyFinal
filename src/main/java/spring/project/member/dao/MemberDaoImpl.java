package spring.project.member.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import spring.project.member.dto.MemberDto;
import spring.project.member.dto.UserVO;
//asd
@Repository
public class MemberDaoImpl implements MemberDao {
    
    @Inject
    private SqlSession sqlSession;
    
    //namespace
    private final String NAMESPACE ="spring.project.mapper.memberMapper";

    @Override
    public int insertMember(MemberDto memberDto) throws Exception {
	return sqlSession.insert(NAMESPACE+".insertMember", memberDto);
    }

    @Override
    public String confirmId(String id) throws Exception {
	return sqlSession.selectOne(NAMESPACE+".confirmId", id);
    }

    @Override
    public String updateMemberConfirm(String id) throws Exception {
	return sqlSession.selectOne(NAMESPACE+".updateMemberConfirm", id);
    }

    @Override
    public String userCheck(String id) throws Exception {
	return sqlSession.selectOne(NAMESPACE+".userCheck",id);
    }

    @Override
    public MemberDto getMember(String id) throws Exception {
	return sqlSession.selectOne(NAMESPACE+".getMember", id);
    }

    @Override
    public List<MemberDto> getList() throws Exception {
	return sqlSession.selectList(NAMESPACE+".getList");
    }

    @Override
    public int updateMember(MemberDto memberDto) throws Exception {
	return sqlSession.update(NAMESPACE+".updateMember", memberDto);
    }

    @Override
    public int updateMemberPW(MemberDto memberDto) throws Exception {
	return sqlSession.update(NAMESPACE+".updateMemberPW", memberDto);
    }

    @Override
    public int updateMemberID(MemberDto memberDto) throws Exception {
	return sqlSession.update(NAMESPACE+".updateMemberID", memberDto);
    }

    @Override
    public int updateMemberToken(MemberDto memberDto) throws Exception {
	System.out.println("updateMemberToken");
	return sqlSession.update(NAMESPACE+".updateMemberToken", memberDto);
    }

    @Override
    public int updateMemberPWC(MemberDto memberDto) throws Exception {
	return sqlSession.update(NAMESPACE+".updateMemberPWC", memberDto);
    }

	@Override
	public UserVO login(MemberDto memberDto) throws Exception {
		return sqlSession.selectOne(NAMESPACE+".login", memberDto);
	}

	@Override
	public UserVO n_login(MemberDto memberDto) throws Exception {
		return sqlSession.selectOne(NAMESPACE+".n_login", memberDto);
	}
}