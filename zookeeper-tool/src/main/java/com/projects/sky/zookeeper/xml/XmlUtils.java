package com.projects.sky.zookeeper.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.projects.sky.zookeeper.common.Commons;

public class XmlUtils {

	/**
	 * Object write into xml file by object and file path
	 * 
	 * @param type
	 * @param path
	 */
	public static <T> void marshallerToXml(T type, String path) {
		FileOutputStream fis = null;
		try {
			JAXBContext context = JAXBContext.newInstance(type.getClass());
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			File file = new File(path);
			fis = new FileOutputStream(file);
			m.marshal(type, fis);
		} catch (Exception e) {
			// TODO
		} finally {
			Commons.close(fis);
		}
	}

	/**
	 * To get object from Class clazz and xml file
	 * 
	 * @param clazz
	 * @param path
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T unmarshallerFromXml(Class<?> clazz, String path) {
		T t = null;
		FileInputStream fis = null;
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller m = context.createUnmarshaller();
			File file = new File(path);
			if (!file.exists()) {
				return null;
			}
			fis = new FileInputStream(file);
			t = (T) m.unmarshal(fis);
		} catch (Exception e) {
			// TODO
		} finally {
			Commons.close(fis);
		}
		return t;
	}

	/**
	 * obj to xml String
	 * 
	 * @param obj
	 * @return
	 */
	public static <T> String marshallerToString(T obj) {
		String result = null;
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			StringWriter writer = new StringWriter();
			marshaller.marshal(obj, writer);
			result = writer.toString();
		} catch (Exception e) {
			// TODO
		}
		return result;
	}

}
