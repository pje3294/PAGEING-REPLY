package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JNDI;

/*memid varchar(15) primary key,
name varchar(15),
passwd varchar(10),
day date default sysdate
*/

public class MemberDAO {
	Connection conn;
	PreparedStatement pstmt = null;
	ResultSet rs;

	public boolean insert(MemberVO vo) {
		conn = JNDI.getConnection();
		String sql = "insert into member values (?,?,?,sysdate)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMemid());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPasswd());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return true;

	}

	public boolean login(MemberVO vo) {
		conn = JNDI.getConnection();
		String sql = "select memid, passwd from member where memid=?";
		boolean result = false;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMemid());
			rs = pstmt.executeQuery();
			rs.next();
			if (rs.getString("passwd").equals(vo.getPasswd())) 
				result = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return result;

	}
	
	public ArrayList<MemberVO> selectAll(){
		ArrayList<MemberVO> datas = new ArrayList<MemberVO>();
		conn = JNDI.getConnection();
		String sql = "select * from (select * from member order by day desc) where rownum>=1 and rownum <=3";
		//String sql = "select * from user order by date desc limit 0,3";
		//select * from (select * from MESSAGES order by mdate desc) where rownum>=1 and rownum <=3
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO data = new MemberVO();
				data.setDay(rs.getDate("day"));
				data.setMemid(rs.getString("memid"));
				data.setName(rs.getString("name"));
				data.setPasswd(rs.getString("passwd"));
				datas.add(data);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return datas;
	}

}
