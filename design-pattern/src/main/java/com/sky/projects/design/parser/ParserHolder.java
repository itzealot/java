package com.sky.projects.design.parser;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.sky.projects.design.parser.support.IntParser;
import com.sky.projects.design.parser.support.StringParser;

/**
 * 解析器持有类
 * 
 * @author zealot
 */
public class ParserHolder {
	private ParserHolder() {
	}

	private static ParserHolder instance = new ParserHolder();
	private static final Map<String, Parser<?>> map = new HashMap<String, Parser<?>>();

	public static ParserHolder getInstance() {
		return instance;
	}

	public ParserHolder addStringParser() {
		map.put(StringParser.class.getName(), new StringParser());
		return instance;
	}

	public ParserHolder addIntParser() {
		map.put(IntParser.class.getName(), new IntParser());
		return instance;
	}

	public Parser<?> get(String key) {
		return map.get(key);
	}

	public Collection<Parser<?>> values() {
		return map.values();
	}

	public Set<String> keys() {
		return map.keySet();
	}

	public static void main(String[] args) {
		ParserHolder.getInstance().addIntParser().addStringParser();
		System.out.println(ParserHolder.getInstance().values());
		System.out.println(ParserHolder.getInstance().keys());
	}
}
