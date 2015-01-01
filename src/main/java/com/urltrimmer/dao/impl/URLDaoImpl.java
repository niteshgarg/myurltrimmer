package com.urltrimmer.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.urltrimmer.dao.URLDao;
import com.urltrimmer.domain.URL;
import com.urltrimmer.exception.URLTrimmerException;

@Repository("urlDao")
public class URLDaoImpl extends GenericDaoImpl<URL, Long> implements URLDao {

	private static final Logger logger = Logger.getLogger(URLDaoImpl.class);

	public URLDaoImpl() {
		super(URL.class);
	}

	public URL getURL(String stringUrl) {
		Session session = null;
		URL url = null;
		String queryString = null;
		try {
			session = getSessionFactory().openSession();
			queryString = "from URL ul where ul.originalUrl=:url";
			Query query = session.createQuery(queryString);
			query.setString("url", stringUrl);
			url = (URL) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new URLTrimmerException();
		} finally {
			session.close();
		}
		return url;
	}

	public URL getURLByRandmonId(long randomId) {
		Session session = null;
		URL url = null;
		String queryString = null;
		try {
			session = getSessionFactory().openSession();
			queryString = "from URL ul where ul.ramdomId=:randomId";
			Query query = session.createQuery(queryString);
			query.setLong("randomId", randomId);
			url = (URL) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new URLTrimmerException();
		} finally {
			session.close();
		}
		return url;
	}
}
