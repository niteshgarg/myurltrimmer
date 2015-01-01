package com.urltrimmer.dao;

import com.urltrimmer.domain.URL;


public interface URLDao extends GenericDao<URL, Long> {

	public URL getURL(String url);

	public URL getURLByRandmonId(long randomId);

}
