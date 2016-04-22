package com.sky.projects.spring.annotation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Resource {
	private String[] strings;

	private String[] strings2;

	private List<String> lists = new ArrayList<String>();

	private Set<String> sets = new HashSet<String>();

	private Map<String, String> maps = new HashMap<String, String>();

	private Message[] messages;
	private List<Message> lsMessages;
	private Set<Message> setMessages;
	private Map<String, Message> mapMessages;

	public String[] getStrings() {
		return strings;
	}

	public void setStrings(String[] strings) {
		this.strings = strings;
	}

	public String[] getStrings2() {
		return strings2;
	}

	public void setStrings2(String[] strings2) {
		this.strings2 = strings2;
	}

	public List<String> getLists() {
		return lists;
	}

	public void setLists(List<String> lists) {
		this.lists = lists;
	}

	public Set<String> getSets() {
		return sets;
	}

	public void setSets(Set<String> sets) {
		this.sets = sets;
	}

	public Map<String, String> getMaps() {
		return maps;
	}

	public void setMaps(Map<String, String> maps) {
		this.maps = maps;
	}

	public Message[] getMessages() {
		return messages;
	}

	public void setMessages(Message[] messages) {
		this.messages = messages;
	}

	public List<Message> getLsMessages() {
		return lsMessages;
	}

	public void setLsMessages(List<Message> lsMessages) {
		this.lsMessages = lsMessages;
	}

	public Set<Message> getSetMessages() {
		return setMessages;
	}

	public void setSetMessages(Set<Message> setMessages) {
		this.setMessages = setMessages;
	}

	public Map<String, Message> getMapMessages() {
		return mapMessages;
	}

	public void setMapMessages(Map<String, Message> mapMessages) {
		this.mapMessages = mapMessages;
	}

}