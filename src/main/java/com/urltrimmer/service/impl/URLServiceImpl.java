package com.urltrimmer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urltrimmer.dao.URLDao;
import com.urltrimmer.domain.URL;
import com.urltrimmer.service.URLService;

@Service("urlService")
public class URLServiceImpl implements URLService {

	@Autowired
	private URLDao urlDao;

	public URL getURL(long id) {
		return urlDao.get(id);
	}

	public void saveURL(URL url) {
		urlDao.save(url);
	}

	public URL getURL(String url) {
		return urlDao.getURL(url);
	}

	public URL getURLByRandmonId(long randomId) {
		return urlDao.getURLByRandmonId(randomId);
	}
}
