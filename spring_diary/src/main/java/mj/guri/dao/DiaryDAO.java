package mj.guri.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import mj.guri.vo.CreateDiaryVO;
import mj.guri.vo.DiaryVO;
import mj.guri.vo.UpdateDiaryVO;

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
	public int insertDiary(CreateDiaryVO cdVo) {
		int result =-1;
		
		String sql = "INSERT INTO DIARY_BOARD VALUES ( DIARY_SEQ.NEXTVAL , ? , ? , ? , SYSDATE)";
		
		result = jdbcTemplate.update(sql,cdVo.getUserid(),cdVo.getTitle(),cdVo.getContent());
		
		return result;
	}
	
	// 아이디를 통해서 일기장 목록 읽어오는 메서드
	public List<DiaryVO> getDiary(String userid) {
		
		String sql = "SELECT * FROM DIARY_BOARD WHERE USERID=? ORDER BY DIARYCODE DESC";
		
		List<DiaryVO> dList = jdbcTemplate.query(sql, rowMapper, userid);
		
		return dList;
	}
	
	
	public void updateDiary(UpdateDiaryVO udVo) {
		
		String sql = "UPDATE DIARY_BOARD SET TITLE = ? , CONTENT = ? WHERE DIARYCODE = ?";
		
		jdbcTemplate.update(sql, udVo.getTitle(),udVo.getContent(),udVo.getDiaryCode());
		
	}
	
	public DiaryVO selectDiaryByCode(int diaryCode) {
		
		String sql = "SELECT * FROM DIARY_BOARD WHERE DIARYCODE = ?";
		List<DiaryVO> result = jdbcTemplate.query(sql, rowMapper, diaryCode);
	
		return result.isEmpty()?null:result.get(0);
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
