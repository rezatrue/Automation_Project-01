package utilities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LinkValidation {

	public static Logger log = LogManager.getLogger(LinkValidation.class.getName());
	
	public LinkValidation() {
	}
	
	public boolean isRedirectUrlOf(String url) {
		boolean isRedirected = false;
	      try {
			HttpURLConnection cn = (HttpURLConnection)new URL(url).openConnection();
			  cn.setRequestMethod("HEAD");
			  cn.connect();
			  int res = cn.getResponseCode();
			  if(res > 199 && res < 399) {
				  isRedirected = true;
			  }
		} catch (MalformedURLException e) {
			log.info(url +"-- "+ e.getMessage());
		} catch (ProtocolException e) {
			log.info(url +"-- "+ e.getMessage());
		} catch (IOException e) {
			log.info(url +"-- "+ e.getMessage());
		}
		return isRedirected;
	}
	
	
}
