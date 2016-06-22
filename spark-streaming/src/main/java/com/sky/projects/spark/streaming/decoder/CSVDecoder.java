package com.sky.projects.spark.streaming.decoder;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.sky.projects.spark.streaming.common.SysConstants;

import kafka.utils.VerifiableProperties;

public class CSVDecoder implements kafka.serializer.Decoder<List<String[]>> {
	private static String encoding = null;

	public CSVDecoder(VerifiableProperties props) {
		if (props == null) {
			encoding = "UTF8";
		} else {
			encoding = props.getString("serializer.encoding", "UTF8");
		}
	}

	@Override
	public List<String[]> fromBytes(byte[] source) {
		String message = null;
		try {
			message = new String(source, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("decoder the byte array error.", e);
		}

		String[] messages = message.split(SysConstants.OBJECT_SPLITER);
		List<String[]> lists = new ArrayList<>();
		int len = messages.length;
		for (int i = 0; i < len; i++) {
			lists.add(messages[i].split(SysConstants.FILED_SPLITER));
		}

		return lists;
	}

}
