package model.msg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.common.JNDI;

/*rid int primary key,
	mid int,
	memid varchar(15),
	day date default sysdate,
	rmsg varchar(50),
	constraint msgrp foreign key (mid) references message (mid) on delete cascade*/

public class ReplyDAO {
	Connection conn;
	PreparedStatement pstmt;
	
	public boolean insert(ReplyVO vo) {
		// ¡Úmessage DB¿¡ replyCountµµ 1Áõ°¡µÇ¾ßµÊ  => Æ®·£Àè¼Ç  (insert + update)
		// ¡ÚUPDATE SET MESSAGE WHERE MID=? -> replycount= replycount+1

		conn = JNDI.getConnection();
		//insert into reply values((select nvl(max(rid),0)+1 from reply),1,'timo',sysdate,'´ñ±Û1');
		String sql ="insert into reply values ((select nvl(max(rid),0)+1 from reply), ?,?,sysdate,?)";
		String sql2= "update message set replycount= replycount+1 where mid=?";
		
		boolean check = false;
		
		try {
			conn.setAutoCommit(false);  // Æ®·£Àè¼Ç À§ÇØ ¿ÀÅä Ä¿¹Ô ²ô±â
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMid());
			pstmt.setString(2, vo.getMemid());
			pstmt.setString(3, vo.getRmsg());
			pstmt.executeUpdate();
			
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, vo.getMid());
			pstmt.executeUpdate();
			check = true; // ´ñ±Û »ðÀÔ ¼º°ø & ´ñ±Û ¼ö + 1 ¼º°ø
		
			
			if(check) { 
				conn.commit();
			}else { // ¼º°ø¸øÇÏ¸é,, ·Ñ¹é
				conn.rollback();
			}
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			System.out.println("ReplayDAO-insert ¿À·ù ·Î±ë");
			e.printStackTrace();
		
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return true;
		
	} 

	public boolean delete(ReplyVO vo) { 
		conn = JNDI.getConnection();
		String sql = "delete from reply where rid=?";
		String sql2 ="update message set replycount=replycount-1 where mid=?";
		
		// UPDATE SET MESSAGE WHERE MID=? -> replycount= replycount-1
		
		boolean check = false;
		
		try {
			conn.setAutoCommit(false); //¿ÀÅäÄ¿¹Ô  ²û
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getRid());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, vo.getMid());
			pstmt.executeUpdate();
			check = true;
			System.out.println(check);
			if(check) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return true;
	
	}
	
	
	
	
	
	
}
