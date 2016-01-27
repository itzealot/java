package com.apusic.sicp.util;

import java.io.IOException;
import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.apusic.sicp.SicpRuntimeException;

/**
 * XMLUtil
 * 
 * @author zt
 *
 */
public final class XMLUtil {
	private XMLUtil() {
	}

	/**
	 * 
	 * 
	 * @param document
	 * @param format
	 * @return
	 */
	public static String toString(Document document, OutputFormat format) {
		StringWriter out = new StringWriter();
		try {
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(document);
		} catch (IOException e) {
			throw new SicpRuntimeException(e);
		}
		return out.toString();
	}
}
