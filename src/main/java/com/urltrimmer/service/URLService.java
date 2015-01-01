package com.urltrimmer.service;

import com.urltrimmer.domain.URL;

public interface URLService {

	public URL getURL(long id);

	public URL getURL(String url);

	public URL getURLByRandmonId(long randomId);

	public void saveURL(URL url);
}
