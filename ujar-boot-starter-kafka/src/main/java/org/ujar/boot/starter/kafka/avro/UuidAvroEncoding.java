package org.ujar.boot.starter.kafka.avro;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.apache.avro.Schema;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.Encoder;
import org.apache.avro.reflect.CustomEncoding;

public class UuidAvroEncoding extends CustomEncoding<UUID> {

  public UuidAvroEncoding() {
    schema = Schema.createUnion(List.of(Schema.create(Schema.Type.NULL), Schema.create(Schema.Type.STRING)));
  }

  @Override
  protected void write(Object datum, Encoder out) throws IOException {
    if (datum != null) {
      out.writeIndex(1);
      out.writeString(datum.toString());
    } else {
      out.writeIndex(0);
      out.writeNull();
    }
  }

  @Override
  protected UUID read(Object reuse, Decoder in) throws IOException {
    int index = in.readIndex();
    if (index == 1) {
      return UUID.fromString(in.readString());
    } else {
      in.readNull();
      return null;
    }
  }
}
