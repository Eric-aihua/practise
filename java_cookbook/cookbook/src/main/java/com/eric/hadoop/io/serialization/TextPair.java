package com.eric.hadoop.io.serialization;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

/**
 * 自定义一个Writable类，该类包含两个字段firt以及second
 * 
 * @author Eric.sunah 2014年12月5日
 * 
 */
class TextPair implements WritableComparable<TextPair> {


  private Text first;
  private Text second;



  public TextPair() {
    super();
    first = new Text();
    second = new Text();
  }

  public TextPair(String first, String second) {
    super();
    this.first = new Text(first);
    this.second = new Text(second);
  }


  public TextPair(Text first, Text second) {
    super();
    this.first = first;
    this.second = second;
  }

  public void readFields(DataInput input) throws IOException {
    first.readFields(input);
    second.readFields(input);
  }

  public void write(DataOutput output) throws IOException {
    first.write(output);
    second.write(output);
  }



  @Override
  public String toString() {
    return "TextPair [first=" + first + ", second=" + second + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((first == null) ? 0 : first.hashCode());
    result = prime * result + ((second == null) ? 0 : second.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    TextPair other = (TextPair) obj;
    if (first == null) {
      if (other.first != null)
        return false;
    } else if (!first.equals(other.first))
      return false;
    if (second == null) {
      if (other.second != null)
        return false;
    } else if (!second.equals(other.second))
      return false;
    return true;
  }

  public int compareTo(TextPair target) {
    if (target.first.compareTo(this.first) != 0) {
      return target.first.compareTo(this.first);
    } else {
      return target.second.compareTo(this.second);
    }
  }
}
