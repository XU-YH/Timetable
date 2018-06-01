package com.example.me07;

public abstract class RetrieveData {
		protected String path;
		protected String cookie;
		protected String referer;
		protected String params;
		
		public RetrieveData(String path, String cookie, String referer, String params){
			this.path = path;
			this.cookie = cookie;
			this.referer = referer;
			this.params = params;
		}
		
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public String getCookie() {
			return cookie;
		}
		public void setCookie(String cookie) {
			this.cookie = cookie;
		}
		public String getReferer() {
			return referer;
		}
		public void setReferer(String referer) {
			this.referer = referer;
		}
		public String getParams() {
			return params;
		}
		public void setParams(String params) {
			this.params = params;
		}
		
		public abstract StringBuffer data() throws Exception;

}
