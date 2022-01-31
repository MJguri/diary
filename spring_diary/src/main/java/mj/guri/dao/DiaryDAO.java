package mj.guri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import mj.guri.vo.DiaryVO;
import mj.guri.vo.MemberVO;

public class DiaryDAO {
	private JdbcTemplate jdbcTemplate;

	// 생성자를 통한 주입
	public DiaryDAO(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}
	
	// 공통의 RowMapper 를 꺼내 봅시다.
	private RowMapper<DiaryVO> rowMapper =
			new RowMapper<DiaryVO>() {
					@Override
					public DiaryVO mapRow(ResultSet rs, int rowNum) throws SQLException {
						DiaryVO m = new DiaryVO(rs.getInt("diaryCode"), 
								rs.getString("userid"), 
								rs.getString("title"),
								rs.getString("content"),
								rs.getString("diaryDate"));
						
						return m;
					}
			};
	
	///------------------------------------------------------------
	
	// 일기장 작성 메서드
	public int insertDiary(DiaryVO dVo) {
		int result =-1;
		
		String sql = "INSERT INTO DIARY_BOARD VALUES ( DIARY_SEQ.NEXTVAL , ? , ? , ? , SYSDATE)";
		
		result = jdbcTemplate.update(sql,dVo.getUserid(),dVo.getTitle(),dVo.getContent());
		
		return result;
	}
	
	// 아이디를 통해서 일기장 목록 읽어오는 메서드
	public List<DiaryVO> getDiary(String userid) {
		
		String sql = "SELECT * FROM DIARY_BOARD WHERE USERID=? ORDER BY DIARYCODE DESC";
		
		List<DiaryVO> dList = jdbcTemplate.query(sql, rowMapper, userid);
		
		return dList;
	}
	
	
	public void updateDiary(DiaryVO dVo) {
		
		String sql = "UPDATE DIARY_BOARD SET TITLE = ? , CONTENT = ? WHERE DIARYCODE = ?";
		
		jdbcTemplate.update(sql, dVo.getTitle(),dVo.getContent(),dVo.getDiaryCode());
		
	}
	
	public DiaryVO selectDiaryByCode(int diaryCode) {
		
		String sql = "SELECT * FROM DIARY_BOARD WHERE DIARYCODE = ?";
		DiaryVO dVo = (DiaryVO)jdbcTemplate.query(sql, rowMapper, diaryCode);
	
		return dVo;
	}
	
	public void deleteDiaryByCode(int diaryCode) {
		
		String sql = "DELETE FROM DIARY_BOARD WHERE DIARYCODE = ?";
		
		jdbcTemplate.update(sql, diaryCode);
		
	}
	
	public List<DiaryVO> selectTargetBoard(int section, int pageNum){
		
		String sql = " SELECT * FROM "
				+ " (SELECT ROWNUM as RN, DIARYCODE, USERID, TITLE, CONTENT, DIARYDATE FROM "
				+ " (SELECT * FROM DIARY_BOARD ORDER BY DIARYCODE DESC))"
				+ " WHERE RN BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+(?)*10";
		
		List<DiaryVO> list = jdbcTemplate.query(sql, rowMapper, section, pageNum, section, pageNum);
			
		return list;
	}
	
	//전체 게시글 수
	public int selectAllNumBoard() {
		
		String sql = "SELECT COUNT(*) FROM DIARY_BOARD";
		Integer cnt = jdbcTemplate.queryForObject(sql, Integer.class);
		
		return cnt;
	}
	
}
