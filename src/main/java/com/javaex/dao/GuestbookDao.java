package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;


@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	//방명록 출력
	public List<GuestbookVo> getGuestList() {
		List<GuestbookVo> gbList = sqlSession.selectList("guestbook.getGuestList");
		return gbList;
	}
	
	//방명록 추가
	public int guestInsert(GuestbookVo gbVo) {
		int count = sqlSession.insert("guestbook.guestInsert", gbVo);
		return count;
	}
	
	//방명록 삭제
	public int guestDelete(GuestbookVo gbVo) {
		int count = sqlSession.delete("guestbook.guestDelete", gbVo);
		return count;
	}
	
	
	
//이전 방식////////////////////////////////////////////////////////////////////////////////////////	
	/*
	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	
	
	private void getConnection() {
		 2. Connection 얻어오기
		conn = DriverManager.getConnection(url, id, pw);
		System.out.println("접속성공");
	}
	
	private void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	*/
	/*
	//Guestbook 출력
	public List<GuestbookVo> getGuestList2() {
		
		List<GuestbookVo> guestList = new ArrayList<GuestbookVo>();
		
		getConnection();
		
		try {

			// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
			String query = "";
			query += " select no ";
			query += "         ,name ";
			query += "         ,password ";
			query += "         ,content ";
			query += "         ,to_char(reg_date, 'YYYY-MM-DD HH24:MI:SS') \"reg_date\" ";
			query += " from guestbook ";
			query += " order by no ";


			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String reg_date = rs.getString("reg_date");

				guestList.add(new GuestbookVo(no, name, password, content, reg_date));
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return guestList;
	}
	
	//Guestbook 추가
	public int guestInsert2(GuestbookVo guestVo) {
		int count = -1;
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " insert into guestbook ";
			query += " values (seq_guestbook_no.nextval, ?, ?, ?, sysdate) ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기
			
			guestVo.setContent(guestVo.getContent().replace("\r\n","<br>"));

			pstmt.setString(1, guestVo.getName());
			pstmt.setString(2, guestVo.getPassword());
			pstmt.setString(3, guestVo.getContent());
			

			count = pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			System.out.println("[" + count + "건 추가되었습니다.]");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return count;
	}
	
	//Guestbook 찾기
	public GuestbookVo getGuest(int delNo) {
		GuestbookVo guest = null;
		getConnection();
		
		try {

			// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
			String query = "";
			query += " select no ";
			query += "         ,name ";
			query += "         ,password ";
			query += "         ,content ";
			query += "         ,to_char(reg_date, 'YYYY-MM-DD HH:MI:SS') \"reg_date\" ";
			query += " from guestbook ";
			query += " where no = ? ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기
			pstmt.setInt(1, delNo);

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String reg_date = rs.getString("reg_date");

				guest = new GuestbookVo(no, name, password, content, reg_date);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return guest;
	}
	
	//Guestbook 삭제
	public int guestDelete2(int delNo, String delPw) {
		int count = -1;
		
		getConnection();
		
		try {

			// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
			String query = "";
			query += " delete guestbook ";
			query += " where no = ? ";
			query += " and password = ? ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기
			pstmt.setInt(1, delNo);
			pstmt.setString(2, delPw);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("["+count+"건 삭제 되었습니다.]");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;
	}
	*/
}
