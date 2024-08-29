package com.interyard.usedgoods.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.interyard.main.dao.DAO;
import com.interyard.usedgoods.vo.UsedGoodsVO;
import com.interyard.util.db.DB;
import com.webjjang.util.page.PageObject;

public class UsedGoodsDAO extends DAO {

	
	// 1.리스트
	public List<UsedGoodsVO> list(PageObject pageObject) throws Exception {
	    List<UsedGoodsVO> list = null;
	    try {
	        con = DB.getConnection();
	        pstmt = con.prepareStatement(getListSQL(pageObject));
	        int idx = 0;
	        idx = setSearchData(pageObject, pstmt, idx);
	        pstmt.setLong(++idx, pageObject.getStartRow());
	        pstmt.setLong(++idx, pageObject.getEndRow());
	        rs = pstmt.executeQuery();
	        if (rs != null) {
	            while (rs.next()) {
	                if (list == null)
	                    list = new ArrayList<UsedGoodsVO>();
	                UsedGoodsVO vo = new UsedGoodsVO();
	                vo.setUgno(rs.getLong("ugno"));
	                vo.setTitle(rs.getString("title"));
	                vo.setId(rs.getString("id"));
	                vo.setPrice(rs.getLong("price"));
	                vo.setWriteDate(rs.getString("writeDate"));
	                vo.setImage(rs.getString("image"));
	                list.add(vo);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        DB.close(con, pstmt, rs);
	    }
	    return list;
	}
	
	
	// 1-2. 전체 데이터 개수 
	public Long getTotalRow(PageObject pageObject) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		Long totalRow = null;
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 TOTALROW
			System.out.println("UsedGoodsDAO.getTotalRow().sql=" + TOTALROW + getSearch(pageObject, true));
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(TOTALROW
					// 전체 데이터 개수 쿼리인 경우 검색 조건이 있으면 where를 붙여라:true
					+ getSearch(pageObject, true));
			int idx = 0;
			idx = setSearchData(pageObject, pstmt, idx);
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 또는 담기
			if (rs != null && rs.next()) {
				totalRow = rs.getLong(1);
			} // end of if
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		} // end of try ~ catch ~ finally
		// 결과 데이터를 리턴해 준다.
		return totalRow;
	} // end of getTotalRow()

	
	// 2.상세보기
	public UsedGoodsVO view(Long ugno) throws Exception {
	    // 결과를 저장할 수 있는 변수 선언.
	    UsedGoodsVO vo = null;
	    try {
	        // 1. 드라이버 확인 - DB
	        // 2. 연결
	        con = DB.getConnection();
	        // 3. sql - 아래 LIST
	        // 4. 실행 객체 & 데이터 세팅
	        pstmt = con.prepareStatement(VIEW);
	        pstmt.setLong(1, ugno);
	        // 5. 실행
	        rs = pstmt.executeQuery();
	        // 6. 표시 또는 담기
	        if (rs != null && rs.next()) {
	            // rs -> vo
	            vo = new UsedGoodsVO();
	            vo.setUgno(rs.getLong("ugno"));
	            vo.setTitle(rs.getString("title"));
	            vo.setContent(rs.getString("content"));
	            vo.setId(rs.getString("id"));
	            vo.setPrice(rs.getLong("price"));
	            vo.setWriteDate(rs.getString("writeDate"));
	            vo.setImage(rs.getString("image"));
	            vo.setUgstatus(rs.getString("ugstatus"));
	            System.out.println("조회된 데이터: " + vo); // 디버깅 로그 추가
	        } else {
	            System.out.println("해당 번호의 데이터가 없습니다. 번호: " + ugno); // 디버깅 로그 추가
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        // 7. 닫기
	        DB.close(con, pstmt, rs);
	    } // end of try ~ catch ~ finally
	    // 결과 데이터를 리턴해 준다.
	    return vo;
	} // end of view()


	
	// 3. 등록
	public int write(UsedGoodsVO vo) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;

		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(WRITE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getId());
			pstmt.setString(4, vo.getImage());
			pstmt.setLong(5, vo.getPrice());
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			System.out.println();
			System.out.println("*** 등록이 완료 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			throw new Exception("예외 발생 : 상품 등록 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of increase()
	
	
	//4.수정 처리
	public int update(UsedGoodsVO vo) throws Exception {
	    // 결과를 저장할 수 있는 변수 선언.
	    int result = 0;

	    try {
	        // 1. 드라이버 확인 - DB
	        // 2. 연결
	        con = DB.getConnection();
	        // 3. sql - 아래 UPDATE
	        // 4. 실행 객체 & 데이터 세팅
	        pstmt = con.prepareStatement(UPDATE);
	        pstmt.setString(1, vo.getTitle());
	        pstmt.setLong(2, vo.getPrice());
	        pstmt.setString(3, vo.getContent());
	        pstmt.setString(4, vo.getImage());
	        pstmt.setString(5, vo.getUgstatus());
	        pstmt.setLong(6, vo.getUgno());
	        pstmt.setString(7, vo.getId());
	        
	        // 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
	        result = pstmt.executeUpdate();
	        // 6. 표시 또는 담기
	        if (result == 0) { // 글번호가 존재하지 않는다. -> 예외로 처리한다.
	            throw new Exception("예외 발생 : 번호가 맞지 않거나 본인 글이 아닙니다. 정보를 확인해 주세요.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        // 특별한 예외는 그냥 전달한다.
	        if (e.getMessage().indexOf("예외 발생") >= 0)
	            throw e;
	        // 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
	        else
	            throw new Exception("예외 발생 : 이미지 게시판 수정 DB 처리 중 예외가 발생했습니다.");
	    } finally {
	        // 7. 닫기
	        DB.close(con, pstmt);
	    }
	    // 결과 데이터를 리턴해 준다.
	    return result;
	} // end of update()
	
	
	// 5. 삭제
	// ImageController - (Execute) - ImageDeleteService - [ImageDAO.delete()]
	public int delete(UsedGoodsVO vo) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;

		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 UPDATE
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(DELETE);
			pstmt.setLong(1, vo.getUgno());
			pstmt.setString(2, vo.getId());
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			if (result == 0) { // 글번호가 존재하지 않거나 비번 틀림. -> 예외로 처리한다.
				throw new Exception("예외 발생 : 번호 맞지 않거나 본인 글이 아닙니다. 정보를 확인해 주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 특별한 예외는 그냥 전달한다.
			if (e.getMessage().indexOf("예외 발생") >= 0)
				throw e;
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			else
				throw new Exception("예외 발생 : 상품삭제 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of delete()
	
		
	// 쿼리
	
	final String LIST = ""
			+ " select ugno, title, id, price, writeDate, image"
			+ " from ( "
				+ " select rownum rnum, ugno, title, id, price, writeDate, image  "
				+ " from ( "
					+ " select u.ugno, u.title, m.id, u.price, "
					+ " to_char(u.writeDate, 'yyyy-mm-dd') writeDate, "
					+ " u.image "
					+ " from usedgoods u , member m "
					+ " where 1=1 ";
	
	private String TOTALROW = "select count(*) from usedgoods ";
	
	
	private String getListSQL(PageObject pageObject) {
		String sql = LIST; 
		String word = pageObject.getWord();
		// 검색 쿼리 추가 - where를 추가 안한다. : false
		sql += getSearch(pageObject, false);
		sql += " and (m.id = u.id) ";
		sql += " order by ugno desc "
				+ " ) "
				+ " ) where rnum between ? and ? ";
		return sql;
	}
	
	
	private String getSearch(PageObject pageObject, boolean isWhere) {
		String sql = "";
		String key = pageObject.getKey();
		String word = pageObject.getWord();
		if(word != null && !word.equals("")) {
			// where 붙이기 처리
			if(isWhere) sql += " where 1=1 ";
			sql += " and ( 1=0 ";
		// key안에 t가 포함되어 있으면 title로 검색을 한다.
			if(key.indexOf("t") >= 0) sql += " or title like ? ";
			if(key.indexOf("c") >= 0) sql += " or content like ? ";
			if(key.indexOf("f") >= 0) sql += " or fileName like ? ";
			sql += " ) ";
		}
		return sql;
	}
	
	
	// 검색 쿼리의 ? 데이터를 세팅하는 메서드
	private int setSearchData(PageObject pageObject, PreparedStatement pstmt, int idx) throws SQLException {
		String key = pageObject.getKey();
		String word = pageObject.getWord();
		if (word != null && !word.equals("")) {
			// key안에 t가 포함되어 있으면 title로 검색을 한다.
			if (key.indexOf("t") >= 0)
				pstmt.setString(++idx, "%" + word + "%");
			if (key.indexOf("c") >= 0)
				pstmt.setString(++idx, "%" + word + "%");
			if (key.indexOf("f") >= 0)
				pstmt.setString(++idx, "%" + word + "%");
		}
		return idx;
	}
	
	
	final String VIEW = "select u.ugno, u.title, u.content, u.id, u.price, "
		    + " to_char(u.writeDate, 'yyyy-mm-dd') writeDate, u.image, u.ugstatus "
		    + " from usedgoods u, member m "
		    + " where (u.ugno = ?) and (m.id = u.id) and (u.ugstatus in ('판매중', '판매완료')) ";

	
	
	final String WRITE = "insert into usedgoods "
			+ " ( ugno, title, content, id, image, price) "
			+ " values(usedgoods_seq.nextval, ?, ?, ?, ?, ?)";
	
	
	final String UPDATE = "update usedgoods "
            + " set title = ?, price = ?, content = ?, image = ?, ugstatus=?"
            + " where ugno = ? and id = ?  ";
	
	
	final String DELETE= "delete from usedgoods "
			+ " where ugno = ? and id = ? "; 
	
}//end of class