package com.ict.db;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class DAO {
	private static SqlSession ss;

	private synchronized static SqlSession getSession() {
		if (ss == null) {
			ss = DBService.getFactory().openSession(false);// �닔�룞 commit()
		}
		return ss;
	}
	
	public static List<BVO> getSelectAll(){
		List<BVO> list = new ArrayList<BVO>();
				
		list = getSession().selectList("list");
		
		
		return list;
	}
	
	public static int getInsert(BVO bvo) {
		int result = 0;
		result = getSession().insert("insert", bvo);
		ss.commit();
		
		return result;
	}

	public static BVO getOneList(String b_idx) {
		BVO bvo = new BVO();
		bvo = getSession().selectOne("onelist", b_idx);
		
		return bvo;
	}

	public static int getDelete(BVO bvo) {
		int result = 0;
		result = getSession().delete("delete", bvo);
		ss.commit();
		
		return result;
	}

	public static int getUpdate(BVO bvo) {
		int result = 0;
		result = getSession().update("update", bvo);
		ss.commit();
		
		return result;
	}

	public static int getHitup(String b_idx) {
		int result = 0;
		result = getSession().update("hitup",b_idx);
		
		return result;
	}

	public static List<CVO> getClist(String b_idx) {
		List<CVO> clist = null;
		 clist = getSession().selectList("clist", b_idx);
		
		return clist ;
	}
	
	//댓글

	public static int getComm_Insert(CVO cvo) {
		int result = 0;
		result = getSession().insert("comm_insert", cvo);
		
		return result;
	}
}
