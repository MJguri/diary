package mj.guri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import mj.guri.vo.MemberVO;

public class MemberDAO {// 데이터베이스 연동
	
	private JdbcTemplate jdbcTemplate;

	// 생성자를 통한 주입
	public MemberDAO(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}
	
	// 공통의 RowMapper 를 꺼내 봅시다.
	private RowMapper<MemberVO> rowMapper =
			new RowMapper<MemberVO>() {
					@Override
					public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
						MemberVO m = new MemberVO(rs.getString("userid"), rs.getString("userpwd"), rs.getString("nickname"));
						
						return m;
					}
			};
	
	///------------------------------------------------------------
	// 사용자 인증 처리 메서드
	public int userCheck(String userid, String userpwd) {
		int result = -1; // 존재하지않는 회원 
		
		String sql = "SELECT * FROM diary_member WHERE userid=?";
		
		List<MemberVO> pwd = jdbcTemplate.query(sql, rowMapper, userid);
			
		if(pwd.size() != 0 && pwd.get(0).getUserpwd().equals(userpwd)) {
			result = 1; // 로그인 성공
		}else if(pwd.size() != 0 && !pwd.get(0).getUserpwd().equals(userpwd)){
			result = 0; // 비밀번호가 null이거나, 비밀번호가 일치하지 않는 경우
		}
		
		return result;
	}
	//-----------------------------------------------------------------------
	// 아이디를 통해서 인증받는 회원 정보를 읽어오는 메서드
		public List<MemberVO> getMember(String userid) {
			String sql = "SELECT * FROM diary_member WHERE userid=?";
			
			List<MemberVO> result = jdbcTemplate.query(sql, rowMapper, userid);
			
			return result;
		}
	//--------------------------------------------------------------------------
	// 회원 정보를 업데이트 하기 위한 쿼리를 작동시킬 메서드
	public void updateMember(MemberVO mVo) {
		
		String sql = "UPDATE diary_member SET userid=?, userpwd=?, nickname=? WHERE userid=?";
		jdbcTemplate.update(sql, mVo.getUserid(), mVo.getUserpwd(),mVo.getNickname(),mVo.getUserid());		
		
	}
	//------------------------------------------------------------------
	// 입력받은 회원 정보로 회원 가입 처리를 할 메서드
	public int insertMember(MemberVO mVo) {
		int result;
		
		String sql="INSERT INTO diary_member VALUES(?,?,?)";
		
		result = jdbcTemplate.update(sql, mVo.getUserid(), mVo.getUserpwd(),mVo.getNickname());
		return result;
	}
	// -------------------------------------------------------------
	//아이디를 중복 체크하기 위한 메서드
	public int confirmID(String userid) {
		int result =-1;
		
		String sql = "SELECT * FROM diary_member WHERE userid=?";
		
		List<MemberVO> confirmId = jdbcTemplate.query(sql, rowMapper, userid);
		
		if(confirmId.size() == 0) {
			result = -1;
		}
		else {
			result = 1;
		}
		
		return result;
	}
	
	
}





