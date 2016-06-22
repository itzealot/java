package com.sky.projects.spark.streaming.decoder;

import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.util.Utf8;

import kafka.utils.VerifiableProperties;

public class AvroSDecoder implements kafka.serializer.Decoder<String[]> {

	private DatumReader<GenericRecord> reader;

	public AvroSDecoder(VerifiableProperties props) {
		String schema = props.getString("KafkaTopicSchema");
		reader = new GenericDatumReader<GenericRecord>(new Schema.Parser().parse(schema));
	}

	@Override
	public String[] fromBytes(byte[] source) {
		Decoder decoder = DecoderFactory.get().binaryDecoder(source, null);
		GenericRecord record = null;

		try {
			record = reader.read(null, decoder);
		} catch (IOException e) {
			throw new RuntimeException("decoder the byte array error.", e);
		}

		int fieldSize = record.getSchema().getFields().size();

		String[] desline = new String[fieldSize];
		for (int i = 0; i < fieldSize; i++) {
			Utf8 var = (Utf8) record.get(i);
			desline[i] = var.toString();
		}

		return desline;
	}

}
