package market;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;

import common.DBConnPool;



public class marketApplicationDAO extends DBConnPool{

	public marketApplicationDAO() {
		super();
	} 
	//게시물의 개수 카운트
	public int selectCountCleaning(Map<String, Object> map) {
    	
        int totalCount = 0;
        
        String query = "SELECT COUNT(*) FROM marketApplication";
        query += "   WHERE  app_type LIKE '%cleaning%'";
        if (map.get("searchWord") != null) {
            query += " AND " + map.get("searchField") + " "
                   + " LIKE '%" + map.get("searchWord") + "%'";
        }
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            totalCount = rs.getInt(1);
        }
        catch (Exception e) {
            System.out.println("게시물 카운트 중 예외 발생");
            e.printStackTrace();
        }

        return totalCount;
    }
	
	
	
	 public List<marketApplicationDTO> selectListCleaning(Map<String,Object> map) {
        List<marketApplicationDTO> board = new Vector<marketApplicationDTO>();
        String query = " SELECT clean_type, clean_area, name, date1, idx "
        			 + " FROM marketApplication WHERE app_type LIKE '%cleaning%'";

        if (map.get("searchWord") != null)
        {
            query += " AND " + map.get("searchField")
                   + " LIKE '%" + map.get("searchWord") + "%' ";
        }

        query += " ORDER BY idx DESC LIMIT ?, ? ";

        try {
            psmt = con.prepareStatement(query);
            psmt.setInt(1, Integer.parseInt(map.get("start").toString()));
            psmt.setInt(2, Integer.parseInt(map.get("end").toString()));
            rs = psmt.executeQuery();

            while (rs.next()) {
            	marketApplicationDTO dto = new marketApplicationDTO();

                dto.setClean_type(rs.getString(1));
                dto.setClean_area(rs.getString(2));
                dto.setName(rs.getString(3));
                dto.setDate(rs.getString(4));
                dto.setIdx(rs.getInt(5));
                board.add(dto);
            }
        }
        catch (Exception e) {
            System.out.println("게시물 조회 중 예외 발생");
            e.printStackTrace();
        }
        return board;
    }
	 
	 
	
	public int insertClean(marketApplicationDTO dto) {

        int result = 0;
        try {
            String query = "INSERT INTO marketApplication ( "
                         + " name, address, phone1, phone2, email, date1, submit_type, app_type,"
                         + " clean_type, clean_area, others) "
                         + " VALUES ( "
                         + " ?,?,?,?,?,?,?,'cleaning',?,?,?)";
            psmt = con.prepareStatement(query);
            psmt.setString(1, dto.getName());
            psmt.setString(2, dto.getAddress());
            psmt.setString(3, dto.getPhone1());
            psmt.setString(4, dto.getPhone2());
            psmt.setString(5, dto.getEmail());
            psmt.setString(6, dto.getDate());
            psmt.setString(7, dto.getSubmit_type());
            psmt.setString(8, dto.getClean_type());
            psmt.setString(9, dto.getClean_area());
            psmt.setString(10, dto.getOthers());
            result = psmt.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("게시물 입력 중 예외 발생");
            e.printStackTrace();
        }
        return result;
    }
	
	
	///////////////////////////////
	 public int deleteClean(String to) { 
        int result = 0;

        try {
        	//쿼리문 작성
            String query = "DELETE FROM marketApplication WHERE idx=?"; 
            //prepared객체 생성 및 인파라미터 설정
            psmt = con.prepareStatement(query); 
            psmt.setString(1, to);  
            //쿼리실행
            result = psmt.executeUpdate(); 
        } 
        catch (Exception e) {
            System.out.println("게시물 삭제 중 예외 발생");
            e.printStackTrace();
        }

        return result;  
    }
	 
	
	//체험학습
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//게시물의 개수 카운트
	public int selectCountExp(Map<String, Object> map) {
        int totalCount = 0;
        String query = "SELECT COUNT(*) FROM marketApplication";
        query += " WHERE app_type LIKE '%experience%' ";
        if (map.get("searchWord") != null) {
            query += " AND " + map.get("searchField") + " "
                   + " LIKE '%" + map.get("searchWord") + "%'";
        }
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            totalCount = rs.getInt(1);
        }
        catch (Exception e) {
            System.out.println("게시물 카운트 중 예외 발생");
            e.printStackTrace();
        }

        return totalCount;
    }
	
	
	
	 public List<marketApplicationDTO> selectListExp(Map<String,Object> map) {
        List<marketApplicationDTO> board = new Vector<marketApplicationDTO>();
        String query = " SELECT idx, name, ex_disabled, ex_helpingtool, "
                     + " phone1, phone2, email, ex_type, date1, submit_type "
                     + " FROM marketApplication WHERE app_type LIKE '%experience%'";

        if (map.get("searchWord") != null)
        {
            query += " AND " + map.get("searchField")
                   + " LIKE '%" + map.get("searchWord") + "%' ";
        }

        query += " ORDER BY idx DESC LIMIT ?, ? ";

        try {
            psmt = con.prepareStatement(query);
            psmt.setInt(1, Integer.parseInt(map.get("start").toString()));
            psmt.setInt(2, Integer.parseInt(map.get("end").toString()));
            rs = psmt.executeQuery();

            while (rs.next()) {
            	marketApplicationDTO dto = new marketApplicationDTO();

            	dto.setIdx(rs.getInt(1));
            	dto.setName(rs.getString(2));
            	dto.setEx_disabled(rs.getString(3));
            	dto.setEx_helpingtool(rs.getString(4));
            	dto.setPhone1(rs.getString(5));
            	dto.setPhone2(rs.getString(6));
            	dto.setEmail(rs.getString(7));
            	dto.setEx_type(rs.getString(8));
            	dto.setDate(rs.getString(9));
            	dto.setSubmit_type(rs.getString(10));

                board.add(dto);
            }
        }
        catch (Exception e) {
            System.out.println("게시물 조회 중 예외 발생");
            e.printStackTrace();
        }
        return board;
    }
	 
	 
	
	public int insertExp(marketApplicationDTO dto) {

        int result = 0;
        try {
            String query = "INSERT INTO marketApplication ( "
                         + " name, ex_disabled, ex_typeofdisability, "
                         + " ex_helpingtool, ex_nameoftool, phone1, phone2, "
                         + " email, ex_type, date1, submit_type, app_type, others) "
                         + " VALUES ( "
                         + " ?,?,?,?,?,?,?,?,?,?,?,'experience',?)";
            psmt = con.prepareStatement(query);
            psmt.setString(1, dto.getName());
            psmt.setString(2, dto.getEx_disabled());
            psmt.setString(3, dto.getEx_typeofdisability());
            psmt.setString(4, dto.getEx_helpingtool());
            psmt.setString(5, dto.getEx_nameoftool());
            psmt.setString(6, dto.getPhone1());
            psmt.setString(7, dto.getPhone2());
            psmt.setString(8, dto.getEmail());
            psmt.setString(9, dto.getEx_type());
            psmt.setString(10, dto.getDate());
            psmt.setString(11, dto.getSubmit_type());
            psmt.setString(12, dto.getOthers());
            result = psmt.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("게시물 입력 중 예외 발생");
            e.printStackTrace();
        }
        return result;
    }
}
