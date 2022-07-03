package org.ujar.boot.starter.kafka.avro;

import java.io.IOException;
import java.time.Instant;
import org.apache.avro.LogicalTypes;
import org.apache.avro.Schema;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.Encoder;
import org.apache.avro.reflect.CustomEncoding;

public class InstantAvroEncoding extends CustomEncoding<Instant> {

  public InstantAvroEncoding() {
    schema = Schema.create(Schema.Type.LONG);
    LogicalTypes.timestampMillis().addToSchema(schema);
  }

  @Override
  protected void write(Object datum, Encoder out) throws IOException {
    if (datum == null) {
      throw new NullPointerException("null of instant");
    }
    out.writeLong(((Instant) datum).toEpochMilli());
  }

  @Override
  protected Instant read(Object reuse, Decoder in) throws IOException {
    if (in == null) {
      return null;
    }
    return Instant.ofEpochMilli(in.readLong());
  }
}
