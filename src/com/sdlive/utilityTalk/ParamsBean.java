package com.sdlive.utilityTalk;

public class ParamsBean{
	private String key;
	private String values;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
	public ParamsBean(String key, String values) { 
		this.key = key;
		this.values = values;
	}
	public ParamsBean(String key, int values) { 
		this.key = key;
		this.values = values+"";
	}
	public ParamsBean(String key, long values) { 
		this.key = key;
		this.values = values+"";
	}
	public ParamsBean(String key, boolean values) { 
		this.key = key;
		this.values = values+"";
	}
	
}