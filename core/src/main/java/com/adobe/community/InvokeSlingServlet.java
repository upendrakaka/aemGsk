package com.adobe.community;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.jackrabbit.commons.JcrUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
//SLing HTTP Client
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * Just a simple DS Component
 */
@Component(metatype = true)
@Service
public class InvokeSlingServlet implements InvokeSling {

	/** Default log. */
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	private String justCall;

	private JSONObject jsondata;

	@SuppressWarnings("deprecation")
	@Override
	public String getClaim() {
        
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod("http://172.16.9.149:8080/api/v1/products/productsList");
		method.addRequestHeader("Authorization","Bearer 3c28ad63e86140c59ff1845f4582f248");
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler(3, false));

		try {
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				log.info("HTTP Method failed: " + method.getStatusLine());
			}
			byte[] responseBody = method.getResponseBody();
			log.info("BODY BODY BODY BODY BODY RESPONSE BYTE!!! >>>>>>>>>>>>>>"+ responseBody.toString());
			String body = new String(responseBody);
			
			log.info("BODY BODY BODY BODY BODY!!! >>>>>>>>>>>>>>"+ body.toString());
			PostMethod postMethod = new PostMethod("http://172.16.105.161:3000/products?id=4");
			StringRequestEntity reqEntity = new StringRequestEntity(body, "application/json", "UTF-8");

			postMethod.setRequestEntity(reqEntity);

			log.info("INVOKED THE AEM BODY string response TEST!!! >>>>>>>>>>>>>>"+ postMethod.getRequestEntity());

			postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(3, false));

			int id = client.executeMethod(postMethod);
			byte[] postResponseBody = postMethod.getResponseBody();
            String reactOut = new String(postResponseBody);
			log.info("REACT OUTPU CHECKING LOG !!! >>>>>>>>>>>>>>"+ reactOut.toString());

			return new String(postResponseBody);

		} catch (Exception e) {
			log.info("ERROR: " + e.getMessage());
		}
		return "There was an error invoking the servlet";
	}

	public String getJustCall() {
		justCall = new String(this.getClaim());
		return justCall;
	}

	public JSONObject getJsondata() throws JSONException {
		JSONObject obj = new JSONObject(this.getClaim());
		return obj;
	}
}