package com.sky.projects.apache.common.avro.entity;

import java.io.File;
import java.io.IOException;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.Test;

public class TestUser {

	private static final String path = "users.avro";

	@Test
	public void testWrite() throws IOException {
		User user1 = new User("Alyssa", 15, null);

		User user2 = new User("Ben", 7, "red");

		User user3 = new User("Cline", 7, "blue");

		// Serialize user1 and user2 to disk
		File file = new File(path);
		DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
		DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
		dataFileWriter.create(user1.getSchema(), file);

		dataFileWriter.append(user1);
		dataFileWriter.append(user2);
		dataFileWriter.append(user3);

		dataFileWriter.close();
	}

	@Test
	public void testRead() throws IOException {
		File file = new File(path);

		DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
		DataFileReader<User> dataFileReader = new DataFileReader<User>(file, userDatumReader);
		User user = null;
		while (dataFileReader.hasNext()) {
			user = dataFileReader.next(user);
			System.out.println(user);
		}

		dataFileReader.close();
	}
}
