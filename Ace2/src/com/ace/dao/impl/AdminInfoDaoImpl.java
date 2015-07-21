package com.ace.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ace.dao.AdminInfoDao;
import com.ace.model.AdminInfo;
import com.ace.model.PageVO;

@Repository("adminInfoDao")
public class AdminInfoDaoImpl implements AdminInfoDao {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public AdminInfo selectAdminInfoByEmail(AdminInfo vo) {
		Session session = this.sessionFactory.getCurrentSession();
				
		Criteria criteria = session.createCriteria(AdminInfo.class)
				.add(Restrictions.eq("email", vo.getEmail()));
		
		vo = (AdminInfo)criteria.uniqueResult();		
		
/*		List<AdminInfo> AdminInfoList = session.createQuery("from AdminInfo").list();
		
		for(AdminInfo p : AdminInfoList) {
			System.out.println("AdminInfo List : " + p);			
		}*/
		
		return vo;
	}

	public AdminInfo selectAdminInfo(AdminInfo vo) {
		Session session = this.sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(AdminInfo.class)
				.add(Restrictions.eq("email", vo.getEmail()))
				.add(Restrictions.eq("password", vo.getPassword()));
		
		vo = (AdminInfo)criteria.uniqueResult();		
		
		return vo;
	}

	public void insertAdminInfo(AdminInfo vo) {
		Session session = this.sessionFactory.getCurrentSession();
        //Save the Model object
        session.persist(vo);
	}

	public List<AdminInfo> selectAdminInfoList(PageVO vo) {
		Session session = this.sessionFactory.getCurrentSession();
		//List<AdminInfo> AdminInfoList = session.createQuery("from AdminInfo").list();
		
		String hql = "FROM AdminInfo";
        Query query = session.createQuery(hql);
        query.setFirstResult(vo.getFirstIndex());
        query.setMaxResults(vo.getLastIndex());
        List<AdminInfo> results = query.list();
		
/*		for(AdminInfo p : AdminInfoList) {
			System.out.println("AdminInfo List : " + p);
		}*/
		return results;
	}

	public int selectAdminInfoListCnt(PageVO vo) {
		Session session = this.sessionFactory.getCurrentSession();
		
		//Projections example
		long count = (long)session.createCriteria(AdminInfo.class)
				.setProjection(Projections.rowCount())
				.uniqueResult();
		
		return Integer.parseInt(String.valueOf(count));
	}

	public AdminInfo selectAdminInfoById(AdminInfo vo) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from AdminInfo where id = :id");
		query.setString("id", vo.getId());
		AdminInfo p = (AdminInfo) query.uniqueResult();
/*		Session session = this.sessionFactory.getCurrentSession();
		AdminInfo p = (AdminInfo)session.load(AdminInfo.class, vo.getId());*/
		return p;		
	}

	public void updateAdminInfo(AdminInfo vo) {
		Session session = this.sessionFactory.getCurrentSession();
        //Save the Model object
		session.update(vo);
	}

	public void deleteAdminInfo(AdminInfo vo) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("Delete From AdminInfo Where id=:id");
		query.setString("id", vo.getId());
		int result = query.executeUpdate();
		System.out.println("Address Delete Status="+result);
		
/*		Session session = this.sessionFactory.getCurrentSession();
		AdminInfo p = (AdminInfo)session.load(AdminInfo.class, vo.getId());
		if(p != null) {
			session.delete(p);
		}	*/
	}

	public void updateLoginFail(String email) {
		// TODO Auto-generated method stub

	}

	public void updateLoginSuccess(AdminInfo vo) {
		// TODO Auto-generated method stub

	}

}
