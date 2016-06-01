package com.sky.projects.apache.common.avro.entity;

@org.apache.avro.specific.AvroGenerated
public class User extends org.apache.avro.specific.SpecificRecordBase
		implements org.apache.avro.specific.SpecificRecord {
	public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse(
			"{\"type\":\"record\",\"name\":\"User\",\"namespace\":\"com.sky.projects.apache.avro\",\"fields\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"favoriteNumber\",\"type\":[\"int\",\"null\"]},{\"name\":\"favoriteColor\",\"type\":[\"string\",\"null\"]}]}");

	public static org.apache.avro.Schema getClassSchema() {
		return SCHEMA$;
	}

	public java.lang.CharSequence name;
	public java.lang.Integer favoriteNumber;
	public java.lang.CharSequence favoriteColor;

	public User() {
	}

	public User(java.lang.CharSequence name, java.lang.Integer favoriteNumber, java.lang.CharSequence favoriteColor) {
		this.name = name;
		this.favoriteNumber = favoriteNumber;
		this.favoriteColor = favoriteColor;
	}

	public org.apache.avro.Schema getSchema() {
		return SCHEMA$;
	}

	public java.lang.Object get(int field$) {
		switch (field$) {
		case 0:
			return name;
		case 1:
			return favoriteNumber;
		case 2:
			return favoriteColor;
		default:
			throw new org.apache.avro.AvroRuntimeException("Bad index");
		}
	}

	public void put(int field$, java.lang.Object value$) {
		switch (field$) {
		case 0:
			name = (java.lang.CharSequence) value$;
			break;
		case 1:
			favoriteNumber = (java.lang.Integer) value$;
			break;
		case 2:
			favoriteColor = (java.lang.CharSequence) value$;
			break;
		default:
			throw new org.apache.avro.AvroRuntimeException("Bad index");
		}
	}

	public java.lang.CharSequence getName() {
		return name;
	}

	public void setName(java.lang.CharSequence value) {
		this.name = value;
	}

	public java.lang.Integer getFavoriteNumber() {
		return favoriteNumber;
	}

	public void setFavoriteNumber(java.lang.Integer value) {
		this.favoriteNumber = value;
	}

	public java.lang.CharSequence getFavoriteColor() {
		return favoriteColor;
	}

	public void setFavoriteColor(java.lang.CharSequence value) {
		this.favoriteColor = value;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static Builder newBuilder(Builder other) {
		return new Builder(other);
	}

	public static Builder newBuilder(User other) {
		return new Builder(other);
	}

	public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<User>
			implements org.apache.avro.data.RecordBuilder<User> {

		private java.lang.CharSequence name;
		private java.lang.Integer favoriteNumber;
		private java.lang.CharSequence favoriteColor;

		private Builder() {
			super(SCHEMA$);
		}

		private Builder(Builder other) {
			super(other);
			if (isValidValue(fields()[0], other.name)) {
				this.name = data().deepCopy(fields()[0].schema(), other.name);
				fieldSetFlags()[0] = true;
			}
			if (isValidValue(fields()[1], other.favoriteNumber)) {
				this.favoriteNumber = data().deepCopy(fields()[1].schema(), other.favoriteNumber);
				fieldSetFlags()[1] = true;
			}
			if (isValidValue(fields()[2], other.favoriteColor)) {
				this.favoriteColor = data().deepCopy(fields()[2].schema(), other.favoriteColor);
				fieldSetFlags()[2] = true;
			}
		}

		private Builder(User other) {
			super(SCHEMA$);
			if (isValidValue(fields()[0], other.name)) {
				this.name = data().deepCopy(fields()[0].schema(), other.name);
				fieldSetFlags()[0] = true;
			}
			if (isValidValue(fields()[1], other.favoriteNumber)) {
				this.favoriteNumber = data().deepCopy(fields()[1].schema(), other.favoriteNumber);
				fieldSetFlags()[1] = true;
			}
			if (isValidValue(fields()[2], other.favoriteColor)) {
				this.favoriteColor = data().deepCopy(fields()[2].schema(), other.favoriteColor);
				fieldSetFlags()[2] = true;
			}
		}

		public java.lang.CharSequence getName() {
			return name;
		}

		public Builder setName(java.lang.CharSequence value) {
			validate(fields()[0], value);
			this.name = value;
			fieldSetFlags()[0] = true;
			return this;
		}

		public boolean hasName() {
			return fieldSetFlags()[0];
		}

		public Builder clearName() {
			name = null;
			fieldSetFlags()[0] = false;
			return this;
		}

		public java.lang.Integer getFavoriteNumber() {
			return favoriteNumber;
		}

		public Builder setFavoriteNumber(java.lang.Integer value) {
			validate(fields()[1], value);
			this.favoriteNumber = value;
			fieldSetFlags()[1] = true;
			return this;
		}

		public boolean hasFavoriteNumber() {
			return fieldSetFlags()[1];
		}

		public Builder clearFavoriteNumber() {
			favoriteNumber = null;
			fieldSetFlags()[1] = false;
			return this;
		}

		public java.lang.CharSequence getFavoriteColor() {
			return favoriteColor;
		}

		public Builder setFavoriteColor(java.lang.CharSequence value) {
			validate(fields()[2], value);
			this.favoriteColor = value;
			fieldSetFlags()[2] = true;
			return this;
		}

		public boolean hasFavoriteColor() {
			return fieldSetFlags()[2];
		}

		public Builder clearFavoriteColor() {
			favoriteColor = null;
			fieldSetFlags()[2] = false;
			return this;
		}

		@Override
		public User build() {
			try {
				User record = new User();
				record.name = fieldSetFlags()[0] ? this.name : (java.lang.CharSequence) defaultValue(fields()[0]);
				record.favoriteNumber = fieldSetFlags()[1] ? this.favoriteNumber
						: (java.lang.Integer) defaultValue(fields()[1]);
				record.favoriteColor = fieldSetFlags()[2] ? this.favoriteColor
						: (java.lang.CharSequence) defaultValue(fields()[2]);
				return record;
			} catch (Exception e) {
				throw new org.apache.avro.AvroRuntimeException(e);
			}
		}
	}
}
