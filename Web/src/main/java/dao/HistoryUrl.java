package dao;

import javax.servlet.http.HttpServletRequest;

public class HistoryUrl {
	String urlLast;

	public String getUrlLast() {
		return urlLast != null ? "."+ urlLast :"index.jsp";
	}

	public void setUrlLast(String url) {
		this.urlLast = url;
	}

	public void saveHistoryUrl(HttpServletRequest request) {
		StringBuilder requestURL = new StringBuilder(request.getServletPath());
		
		String queryString = request.getQueryString();
		System.out.println(requestURL);
		if (queryString == null) {
			setUrlLast(requestURL.toString());
		} else {
			setUrlLast(requestURL.append('?').append(queryString).toString());
		}
	}

}
