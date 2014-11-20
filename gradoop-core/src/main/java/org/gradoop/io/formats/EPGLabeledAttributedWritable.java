package org.gradoop.io.formats;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.hadoop.io.ObjectWritable;
import org.apache.hadoop.io.Writable;
import org.gradoop.model.Attributed;
import org.gradoop.model.Labeled;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by martin on 20.11.14.
 */
public class EPGLabeledAttributedWritable implements Labeled, Attributed,
  Writable {

  private final List<String> labels;

  private final Map<String, Object> properties;

  public EPGLabeledAttributedWritable() {
    labels = Lists.newArrayList();
    properties = Maps.newHashMap();
  }

  public EPGLabeledAttributedWritable(Iterable<String> labels, Map<String,
    Object> properties) {
    this.labels = Lists.newArrayList(labels);
    this.properties = properties;
  }

  @Override
  public Iterable<String> getPropertyKeys() {
    return properties.keySet();
  }

  @Override
  public Object getProperty(String key) {
    return properties.get(key);
  }

  @Override
  public Iterable<String> getLabels() {
    return labels;
  }

  @Override
  public void write(DataOutput dataOutput)
    throws IOException {
    // labels
    dataOutput.writeInt(labels.size());
    for (String label : labels) {
      dataOutput.writeUTF(label);
    }
    // properties
    dataOutput.writeInt(properties.size());
    ObjectWritable ow = new ObjectWritable();
    for (Map.Entry<String, Object> property : properties.entrySet()) {
      dataOutput.writeUTF(property.getKey());
      ow.set(property.getValue());
      ow.write(dataOutput);
    }
  }

  @Override
  public void readFields(DataInput dataInput)
    throws IOException {
    // labels
    final int labelCount = dataInput.readInt();
    for (int i = 0; i < labelCount; i++) {
      labels.add(dataInput.readUTF());
    }

    // properties
    ObjectWritable ow = new ObjectWritable();
    final int propertyCount = dataInput.readInt();
    for (int i = 0; i < propertyCount; i++) {
      String key = dataInput.readUTF();
      ow.readFields(dataInput);
      Object value = ow.get();
      properties.put(key, value);
    }
  }
}
