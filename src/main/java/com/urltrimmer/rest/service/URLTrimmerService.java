package com.urltrimmer.rest.service;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.urltrimmer.domain.URL;
import com.urltrimmer.domain.URLTrimmerResponse;
import com.urltrimmer.service.URLService;
import com.urltrimmer.util.URLTrimmerUtil;

@Path("/urltrimmer-service")
public class URLTrimmerService {

	private static final Logger logger = Logger
			.getLogger(URLTrimmerService.class);

	ApplicationContext appContext = new ClassPathXmlApplicationContext(
			"../applicationContext.xml");

	@Path("/text")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String text() {
		return "Hello Jersey";
	}

	@GET
	@Path("/get-trimmed-url")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTrimmedUrl(@QueryParam("url") String url) {

		URLService urlService = (URLService) appContext.getBean("urlService");
		URLTrimmerResponse urlTrimmerResponse = new URLTrimmerResponse();

		String message = URLTrimmerUtil.validateUrl(url);

		if (null != message) {
			urlTrimmerResponse.setStatusMessage(message);
			return Response.status(200).entity(urlTrimmerResponse).build();
		}
		URL urlExisting = urlService.getURL(url);

		if (null == urlExisting) {
			urlExisting = new URL();
			urlExisting.setOriginalUrl(url);
			urlExisting.setCreated(new Date());
			urlExisting
					.setRamdomId((long) Math.floor(Math.random() * 1000000000L) + 1000000000L);
			urlService.saveURL(urlExisting);

			urlTrimmerResponse.setStatusMessage("SUCCESS");

			urlTrimmerResponse.setUrl(url.substring(0, 11) + "."
					+ urlExisting.getShortUrl());
			return Response.status(200).entity(urlTrimmerResponse).build();
		} else {
			urlTrimmerResponse.setStatusMessage("SUCCESS");
			urlTrimmerResponse.setUrl(url.substring(0, 11) + "."
					+ urlExisting.getShortUrl());
			return Response.status(200).entity(urlTrimmerResponse).build();
		}
	}

	@GET
	@Path("/get-original-url")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOriginalUrl(@QueryParam("url") String url) {

		URLService urlService = (URLService) appContext.getBean("urlService");
		URLTrimmerResponse urlTrimmerResponse = new URLTrimmerResponse();

		String message = URLTrimmerUtil.validateUrl(url);

		if (null != message) {
			urlTrimmerResponse.setStatusMessage(message);
			return Response.status(200).entity(urlTrimmerResponse).build();
		}

		url = url.substring(12, url.length());
		Long urlId = URL.shortCodeToId(url);

		URL urlExisting = urlService.getURLByRandmonId(urlId);

		if (null == urlExisting) {
			urlTrimmerResponse
					.setStatusMessage("This URL does not exists in our application");
			return Response.status(200).entity(urlTrimmerResponse).build();
		} else {
			urlTrimmerResponse.setStatusMessage("SUCCESS");
			urlTrimmerResponse.setUrl(urlExisting.getOriginalUrl());
			return Response.status(200).entity(urlTrimmerResponse).build();
		}
	}

}
