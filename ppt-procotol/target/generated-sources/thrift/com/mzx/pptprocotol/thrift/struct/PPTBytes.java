/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.mzx.pptprocotol.thrift.struct;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2016-01-08")
public class PPTBytes implements org.apache.thrift.TBase<PPTBytes, PPTBytes._Fields>, java.io.Serializable, Cloneable, Comparable<PPTBytes> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("PPTBytes");

  private static final org.apache.thrift.protocol.TField PPT_DETAIL_FIELD_DESC = new org.apache.thrift.protocol.TField("pptDetail", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField BYTES_FIELD_DESC = new org.apache.thrift.protocol.TField("bytes", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new PPTBytesStandardSchemeFactory());
    schemes.put(TupleScheme.class, new PPTBytesTupleSchemeFactory());
  }

  public PPTDetail pptDetail; // required
  public ByteBuffer bytes; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    PPT_DETAIL((short)1, "pptDetail"),
    BYTES((short)2, "bytes");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // PPT_DETAIL
          return PPT_DETAIL;
        case 2: // BYTES
          return BYTES;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.PPT_DETAIL, new org.apache.thrift.meta_data.FieldMetaData("pptDetail", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, PPTDetail.class)));
    tmpMap.put(_Fields.BYTES, new org.apache.thrift.meta_data.FieldMetaData("bytes", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , true)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(PPTBytes.class, metaDataMap);
  }

  public PPTBytes() {
  }

  public PPTBytes(
    PPTDetail pptDetail,
    ByteBuffer bytes)
  {
    this();
    this.pptDetail = pptDetail;
    this.bytes = org.apache.thrift.TBaseHelper.copyBinary(bytes);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public PPTBytes(PPTBytes other) {
    if (other.isSetPptDetail()) {
      this.pptDetail = new PPTDetail(other.pptDetail);
    }
    if (other.isSetBytes()) {
      this.bytes = org.apache.thrift.TBaseHelper.copyBinary(other.bytes);
    }
  }

  public PPTBytes deepCopy() {
    return new PPTBytes(this);
  }

  @Override
  public void clear() {
    this.pptDetail = null;
    this.bytes = null;
  }

  public PPTDetail getPptDetail() {
    return this.pptDetail;
  }

  public PPTBytes setPptDetail(PPTDetail pptDetail) {
    this.pptDetail = pptDetail;
    return this;
  }

  public void unsetPptDetail() {
    this.pptDetail = null;
  }

  /** Returns true if field pptDetail is set (has been assigned a value) and false otherwise */
  public boolean isSetPptDetail() {
    return this.pptDetail != null;
  }

  public void setPptDetailIsSet(boolean value) {
    if (!value) {
      this.pptDetail = null;
    }
  }

  public byte[] getBytes() {
    setBytes(org.apache.thrift.TBaseHelper.rightSize(bytes));
    return bytes == null ? null : bytes.array();
  }

  public ByteBuffer bufferForBytes() {
    return org.apache.thrift.TBaseHelper.copyBinary(bytes);
  }

  public PPTBytes setBytes(byte[] bytes) {
    this.bytes = bytes == null ? (ByteBuffer)null : ByteBuffer.wrap(Arrays.copyOf(bytes, bytes.length));
    return this;
  }

  public PPTBytes setBytes(ByteBuffer bytes) {
    this.bytes = org.apache.thrift.TBaseHelper.copyBinary(bytes);
    return this;
  }

  public void unsetBytes() {
    this.bytes = null;
  }

  /** Returns true if field bytes is set (has been assigned a value) and false otherwise */
  public boolean isSetBytes() {
    return this.bytes != null;
  }

  public void setBytesIsSet(boolean value) {
    if (!value) {
      this.bytes = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case PPT_DETAIL:
      if (value == null) {
        unsetPptDetail();
      } else {
        setPptDetail((PPTDetail)value);
      }
      break;

    case BYTES:
      if (value == null) {
        unsetBytes();
      } else {
        setBytes((ByteBuffer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case PPT_DETAIL:
      return getPptDetail();

    case BYTES:
      return getBytes();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case PPT_DETAIL:
      return isSetPptDetail();
    case BYTES:
      return isSetBytes();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof PPTBytes)
      return this.equals((PPTBytes)that);
    return false;
  }

  public boolean equals(PPTBytes that) {
    if (that == null)
      return false;

    boolean this_present_pptDetail = true && this.isSetPptDetail();
    boolean that_present_pptDetail = true && that.isSetPptDetail();
    if (this_present_pptDetail || that_present_pptDetail) {
      if (!(this_present_pptDetail && that_present_pptDetail))
        return false;
      if (!this.pptDetail.equals(that.pptDetail))
        return false;
    }

    boolean this_present_bytes = true && this.isSetBytes();
    boolean that_present_bytes = true && that.isSetBytes();
    if (this_present_bytes || that_present_bytes) {
      if (!(this_present_bytes && that_present_bytes))
        return false;
      if (!this.bytes.equals(that.bytes))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_pptDetail = true && (isSetPptDetail());
    list.add(present_pptDetail);
    if (present_pptDetail)
      list.add(pptDetail);

    boolean present_bytes = true && (isSetBytes());
    list.add(present_bytes);
    if (present_bytes)
      list.add(bytes);

    return list.hashCode();
  }

  @Override
  public int compareTo(PPTBytes other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetPptDetail()).compareTo(other.isSetPptDetail());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPptDetail()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.pptDetail, other.pptDetail);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetBytes()).compareTo(other.isSetBytes());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBytes()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.bytes, other.bytes);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("PPTBytes(");
    boolean first = true;

    sb.append("pptDetail:");
    if (this.pptDetail == null) {
      sb.append("null");
    } else {
      sb.append(this.pptDetail);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("bytes:");
    if (this.bytes == null) {
      sb.append("null");
    } else {
      org.apache.thrift.TBaseHelper.toString(this.bytes, sb);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (pptDetail != null) {
      pptDetail.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class PPTBytesStandardSchemeFactory implements SchemeFactory {
    public PPTBytesStandardScheme getScheme() {
      return new PPTBytesStandardScheme();
    }
  }

  private static class PPTBytesStandardScheme extends StandardScheme<PPTBytes> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, PPTBytes struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // PPT_DETAIL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.pptDetail = new PPTDetail();
              struct.pptDetail.read(iprot);
              struct.setPptDetailIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // BYTES
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.bytes = iprot.readBinary();
              struct.setBytesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, PPTBytes struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.pptDetail != null) {
        oprot.writeFieldBegin(PPT_DETAIL_FIELD_DESC);
        struct.pptDetail.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.bytes != null) {
        oprot.writeFieldBegin(BYTES_FIELD_DESC);
        oprot.writeBinary(struct.bytes);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class PPTBytesTupleSchemeFactory implements SchemeFactory {
    public PPTBytesTupleScheme getScheme() {
      return new PPTBytesTupleScheme();
    }
  }

  private static class PPTBytesTupleScheme extends TupleScheme<PPTBytes> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, PPTBytes struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetPptDetail()) {
        optionals.set(0);
      }
      if (struct.isSetBytes()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetPptDetail()) {
        struct.pptDetail.write(oprot);
      }
      if (struct.isSetBytes()) {
        oprot.writeBinary(struct.bytes);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, PPTBytes struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.pptDetail = new PPTDetail();
        struct.pptDetail.read(iprot);
        struct.setPptDetailIsSet(true);
      }
      if (incoming.get(1)) {
        struct.bytes = iprot.readBinary();
        struct.setBytesIsSet(true);
      }
    }
  }

}
