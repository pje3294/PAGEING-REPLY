package model.msg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JNDI;

/*mid int primary key,
memid varchar(15),
msg varchar(100),
favcount int default 0,
replycount int default 0,
day date default sysdate*/

public class MessageDAO {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	// MessageDAO  -> selectAll() ==> message 1개랑 reply 여러개
	
	
	//public ArrayList<MsgSet> selectAll(String memid, int cnt)
	public ArrayList<MsgSet> selectAll(String memid, int mcnt){
		ArrayList<MsgSet> datas = new ArrayList<MsgSet>();
		conn = JNDI.getConnection();
		String sql;
		
		//select * from reply where rownum <= 3 order by datatime;
		
		//전체
		try {
			if(memid == null || (memid.equals(""))) { //기본적으로는 message 봄
				//select * from (select * from message order by datetime desc) where rownum<=3
				sql= "select * from (select * from message order by day desc)  where rownum<= ?";
				//sql="select * from message where rownum <= ? order by day desc";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mcnt);
				
			}
			
			//특정회원
			else {
				sql="select * from (select * from message order by day desc) where memid=? and rownum <= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memid);
				pstmt.setInt(2, mcnt);
			}
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				// 그 message 해당하는 reply들을 꺼낸 후, rlist에 넣음 
				MsgSet ms = new MsgSet(); // Msgset을 만들기위해
				MessageVO m = new MessageVO(); // MessageVO랑 
				ArrayList<ReplyVO> rlist = new ArrayList<ReplyVO>();  // rlist 대기 시킴
				
				// 50번째 줄에서 꺼낸 rs들 MessageVO m에 넣음
				m.setMid(rs.getInt("mid"));
				m.setMsg(rs.getNString("msg"));
				m.setFavcount(rs.getInt("favcount"));
				m.setMemid(rs.getString("memid"));
				m.setDay(rs.getDate("day"));
				
				
				// 꺼낸 메세지에 해당되는 댓글들을 꺼냄
				String rsql = "select * from reply where mid=? order by day desc";
				pstmt = conn.prepareStatement(rsql);
				pstmt.setInt(1, rs.getInt("mid"));
				//MessageVO를 보관하고있는 기존의 rs는 필요함으로 rrs하나 더 만듦
				ResultSet rrs = pstmt.executeQuery(); 
				int rcnt = 0;
				while(rrs.next()) {
					ReplyVO r = new ReplyVO();
					r.setDay(rrs.getDate("day"));
					r.setMid(rrs.getInt("mid"));
					r.setRid(rrs.getInt("rid"));
					r.setRmsg(rrs.getString("rmsg"));
					r.setMemid(rrs.getString("memid"));
					rlist.add(r);
					rcnt++; //댓글수
				}
				m.setReplycount(rcnt); //댓글수 몇개인지 넣어줌 
				
				
				// message랑 해당 rlist를 묶어서 MsgSet ms에 넣어서 제공!
				ms.setM(m);		
				ms.setRlist(rlist);
				datas.add(ms);
				rrs.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return datas;
		
	}
	
	
	public boolean insert(MessageVO vo) {
		conn = JNDI.getConnection();
		//insert into message values((select nvl(max(mid),0)+1 from message), 'timo','★★★', 2, 3, sysdate);
		String sql="insert into message values((select nvl(max(mid),0)+1 from message), ?,?, 0, 0, sysdate)";
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMemid());
			pstmt.setString(2,vo.getMsg());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return true;
	}
	
	public boolean delete(MessageVO vo) {
		conn = JNDI.getConnection();
		String sql="delete from message where mid=?";
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return true;
	}
	
	public void update(MessageVO vo) {
		System.out.println("update() 확인");
		conn = JNDI.getConnection();
		String sql="update message set favcount=favcount+1 where mid=?";
		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMid());
			pstmt.executeUpdate(); 
			System.out.println("update try문 확인");
		} catch (SQLException e) {
			System.out.println("MessageDAO-update 오류 로깅");
			e.printStackTrace();
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
	}
	
	
}
