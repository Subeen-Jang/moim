package com.moim.member;
import com.moim.stat.*;
import com.moim.noimg.*;
import com.moim.info.*;
import com.moim.review.*;
import java.sql.*;
import java.util.*;

import com.moim.review.ReviewDTO;

public class MemberDAO {
	
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	
	public MemberDAO() {
		
	}
	/**회원가입 메서드*/
	public int joinMem(MemberDTO dto) {
		try {
			conn=com.moim.db.MoimDB.getConn();
			String sql="insert into moim_member values(moim_member_idx.nextval,?,?,?,?,?,?,?,sysdate,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getId());
			ps.setString(3, dto.getPwd());
			ps.setString(4, dto.getEmail());
			ps.setString(5, dto.getLocal());
			ps.setInt(6,dto.getAge());
			ps.setString(7, dto.getHobby());
			ps.setInt(8, dto.getManager());
			int count=ps.executeUpdate();
			return count;
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			try {
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch(Exception e2) {
				
			}
		}
	}
	
	/**아이디 중복 체크 메서드*/
	public boolean checkId(String id) {
		try {
			conn=com.moim.db.MoimDB.getConn();
			String sql="select name from moim_member where id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,id);
			rs=ps.executeQuery();
			return rs.next();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally {
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch(Exception e2){
				
			}
		}
		
	}
	
	/**로그인 관련 메서드*/
	public MemberDTO login(String id, String pwd) {
		try {
			conn=com.moim.db.MoimDB.getConn();
			String sql="select * from moim_member where id=? and pwd=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd);
			rs=ps.executeQuery();
			MemberDTO dto=null;
			if(rs.next()) {
				int idx=rs.getInt("idx");
				String name=rs.getString("name");
				String email=rs.getString("email");
				String local=rs.getString("local");
				int age=rs.getInt("age");
				String hobby=rs.getString("hobby");
				java.sql.Date joindate=rs.getDate("joindate");
				int manager=rs.getInt("manager");
				
				dto=new MemberDTO(idx,name,id,pwd,email,local,age,hobby,joindate,manager);
			}
			return dto;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch(Exception e2) {
				
			}
		}
	}
	
	/**멤버 조회 메서드*/
	public MemberDTO getMem(int idx) {
		try {
			conn=com.moim.db.MoimDB.getConn();
			String sql="select * from moim_member where idx=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, idx);
			rs=ps.executeQuery();
			MemberDTO dto=null;
			if(rs.next()) {
				String name=rs.getString("name");
				String id=rs.getString("id");
				String pwd=rs.getString("pwd");
				String email=rs.getString("email");
				String local=rs.getString("local");
				int age=rs.getInt("age");
				String hobby=rs.getString("hobby");
				dto=new MemberDTO(idx, name, id, pwd, email, local, age, hobby, null, age);
			}
			return dto;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch(Exception e2) {}
		}
	}
	/**수정 메서드*/
	public int updateMem(MemberDTO dto) {
		try {
			conn=com.moim.db.MoimDB.getConn();
			String sql="update moim_member set name=?,id=?,pwd=?,email=?,local=?,age=?,hobby=? where idx=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getId());
			ps.setString(3, dto.getPwd());
			ps.setString(4, dto.getEmail());
			ps.setString(5, dto.getLocal());
			ps.setInt(6, dto.getAge());
			ps.setString(7, dto.getHobby());
			ps.setInt(8, dto.getIdx());
			int count=ps.executeUpdate();
			return count;
		}catch(Exception e) {
			e.printStackTrace();
			return -2;
		}finally {
			try {
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch(Exception e2) {}
		}
	}
	/**댓글 조회 메서드*/
	public ArrayList<NoimgDTO> getMyQna(int category,int idx_member){
		try {
			conn=com.moim.db.MoimDB.getConn();
			String sql="select * from moim_noimg where category=? and idx_member=?";
			ps=conn.prepareStatement(sql);
			ArrayList<NoimgDTO> arr=new ArrayList<NoimgDTO>();
			ps.setInt(1, category);
			ps.setInt(2, idx_member);
			rs=ps.executeQuery();
			while(rs.next()) {
				int idx_info=rs.getInt("idx_info");
				int idx=rs.getInt("idx");
				String writer=rs.getString("writer");
				String subject=rs.getString("subject");
				String content=rs.getString("content");
				java.sql.Date writedate=rs.getDate("writedate");
				int ref=rs.getInt("ref");
				int lev=rs.getInt("lev");
				int sunbun=rs.getInt("sunbun");
				
				NoimgDTO dto=new NoimgDTO(idx, idx_member, idx_info, category, writer, subject, content, writedate, ref, lev, sunbun);
				arr.add(dto);
			}
			return arr;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch(Exception e2) {}
		}
	}
	/**후기 조회 메서드*/
	public ArrayList<ReviewDTO> getMyReview(int category,int idx_member){
		try {
			conn=com.moim.db.MoimDB.getConn();
			String sql="select * from moim_review where category=? and idx_member=?";
			ps=conn.prepareStatement(sql);
			ArrayList<ReviewDTO> arr=new ArrayList<ReviewDTO>();
			ps.setInt(1, category);
			ps.setInt(2, idx_member);
			rs=ps.executeQuery();
			while(rs.next()) {
				int idx=rs.getInt("idx");
				String moimname=rs.getString("moimname");
				String writer=rs.getString("writer");
				String local=rs.getString("local");
				String hobby=rs.getString("hobby");
				String subject=rs.getString("subject");
				String content=rs.getString("content");
				String img=rs.getString("img");
				java.sql.Date writedate=rs.getDate("writedate");
				
				ReviewDTO dto=new ReviewDTO(idx, idx_member, moimname, writer, local, hobby, subject, content, img, writedate);
				arr.add(dto);
			}
			return arr;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch(Exception e2) {}
		}
	}
	/**모임 카테고리 가져오기 메서드*/
	public HashMap<Integer,String> moimCategory(){
		try {
			conn=com.moim.db.MoimDB.getConn();
			String sql="select idx,hobby from moim_info";
			ps=conn.prepareStatement(sql);
			HashMap<Integer,String> map=new HashMap<>();
			rs=ps.executeQuery();
			
			while(rs.next()) {
				int idx=rs.getInt(1);
				String hobby=rs.getString(2);
				map.put(idx, hobby);
			}
			return map;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch(Exception e2) {}
		}
	}
	
	/**모임 인원 가져오기 메서드*/
	public ArrayList<InfoDTO> getNowMem(int idx){
		try {
			conn=com.moim.db.MoimDB.getConn();
			String sql="select nowmem,maxmem from moim_info where idx=?";
			ps=conn.prepareStatement(sql);
			ArrayList<InfoDTO> arr=new ArrayList<InfoDTO>();
			ps.setInt(1, idx);
			rs=ps.executeQuery();
			while(rs.next()) {
				String hobby=rs.getString("hobby");
				String moimname=rs.getString("moimname");
				String content=rs.getString("content");
				String local=rs.getString("local");
				int nowmem=rs.getInt("nowmem");
				int maxmem=rs.getInt("maxmem");
				String imt=rs.getString("img");
				
				InfoDTO dto=new InfoDTO(idx, hobby, moimname, content, local, nowmem, maxmem, imt);
				arr.add(dto);
			}
			return arr;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch(Exception e2) {}
		}
	}
	/**모임이름 가져오기 메서드*/
	public HashMap<Integer, String> moimName(){
		try {
			conn=com.moim.db.MoimDB.getConn();
			String sql="select idx,moimname from moim_info";
			ps=conn.prepareStatement(sql);
			HashMap<Integer, String> map=new HashMap<>();
			rs=ps.executeQuery();
			
			while(rs.next()) {
				int idx=rs.getInt(1);
				String moimname=rs.getString(2);
				map.put(idx, moimname);
			}
			return map;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch(Exception e2) {}
		}
	}
	
	/**참여중인 모임 조회 메서드*/
	public ArrayList<StatDTO> getMyStat(int idx_member){
		try {
			conn=com.moim.db.MoimDB.getConn();
			String sql="select * from moim_stat where idx_member=?";
			ps=conn.prepareStatement(sql);
			ArrayList<StatDTO> arr=new ArrayList<StatDTO>();
			ps.setInt(1, idx_member);
			rs=ps.executeQuery();
			while(rs.next()) {
				int idx=rs.getInt("idx");
				int idx_info=rs.getInt("idx_info");
				int stat=rs.getInt("stat");
				java.sql.Date joindate=rs.getDate("joindate");
				String content=rs.getString("content");
				StatDTO dto=new StatDTO(idx, idx_member, idx_info, stat, joindate, content);
				arr.add(dto);
			}
			return arr;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch(Exception e2) {}
		}
	}
	
	/**총 작성 수 관련 메서드*/
	/**cul 
	 * 1.idx_member=1
	 * 2.and category=2
	 * 3.and stat in(1,0)
	 * 4.and stat=0*/
	
	public int getTotal(String table,int idx_member,int cul) {
		try {
			conn=com.moim.db.MoimDB.getConn();
			String sql="select count(*) from ? where idx_member=? ";
			if(table=="moim_review"&&cul==1) {
				ps=conn.prepareStatement(sql);
			}else if(table=="moim_noimg"&&cul==2) {
				sql=sql+"and category=2";
				ps=conn.prepareStatement(sql);
			}else if(table=="moim_stat"&&cul==3) {
				sql=sql+"and stat in(1,0)";
				ps=conn.prepareStatement(sql);
			}else if(table=="moim_stat"&&cul==4){
				sql=sql+"and stat=0";
				ps=conn.prepareStatement(sql);
			}
			ps=conn.prepareStatement(sql);
			int count=0;
			ps.setString(1, table);
			ps.setInt(2, idx_member);
			ps.setInt(3, cul);
			rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			return count;
		}catch(Exception e) {
			e.printStackTrace();
			return 1;
		}finally {
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch(Exception e2) {}
		}
	}
	

//	/**탈퇴하기 메서드*/
//	public int dropMem(int idx) {
//		try {
//			conn=com.moim.db.MoimDB.getConn();
//			String sql="delete from ";
//			ps=conn.prepareStatement(sql);
//			ps.setInt(1, idx);
//			int count=ps.executeUpdate();
//			return count;
//		}catch(Exception e) {
//			e.printStackTrace();
//			return -1;
//		}finally {
//			try {
//				if(ps!=null)ps.close();
//				if(conn!=null)conn.close();
//			}catch(Exception e2) {}
//		}
//	}

}