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
public class TSortStruct implements org.apache.thrift.TBase<TSortStruct, TSortStruct._Fields>, java.io.Serializable, Cloneable, Comparable<TSortStruct> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TSortStruct");

  private static final org.apache.thrift.protocol.TField SORTED_FIELD_FIELD_DESC = new org.apache.thrift.protocol.TField("sortedField", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField DESC_FIELD_DESC = new org.apache.thrift.protocol.TField("desc", org.apache.thrift.protocol.TType.I32, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TSortStructStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TSortStructTupleSchemeFactory());
  }

  public String sortedField; // required
  public int desc; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SORTED_FIELD((short)1, "sortedField"),
    DESC((short)2, "desc");

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
        case 1: // SORTED_FIELD
          return SORTED_FIELD;
        case 2: // DESC
          return DESC;
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
  private static final int __DESC_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SORTED_FIELD, new org.apache.thrift.meta_data.FieldMetaData("sortedField", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DESC, new org.apache.thrift.meta_data.FieldMetaData("desc", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TSortStruct.class, metaDataMap);
  }

  public TSortStruct() {
  }

  public TSortStruct(
    String sortedField,
    int desc)
  {
    this();
    this.sortedField = sortedField;
    this.desc = desc;
    setDescIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TSortStruct(TSortStruct other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetSortedField()) {
      this.sortedField = other.sortedField;
    }
    this.desc = other.desc;
  }

  public TSortStruct deepCopy() {
    return new TSortStruct(this);
  }

  @Override
  public void clear() {
    this.sortedField = null;
    setDescIsSet(false);
    this.desc = 0;
  }

  public String getSortedField() {
    return this.sortedField;
  }

  public TSortStruct setSortedField(String sortedField) {
    this.sortedField = sortedField;
    return this;
  }

  public void unsetSortedField() {
    this.sortedField = null;
  }

  /** Returns true if field sortedField is set (has been assigned a value) and false otherwise */
  public boolean isSetSortedField() {
    return this.sortedField != null;
  }

  public void setSortedFieldIsSet(boolean value) {
    if (!value) {
      this.sortedField = null;
    }
  }

  public int getDesc() {
    return this.desc;
  }

  public TSortStruct setDesc(int desc) {
    this.desc = desc;
    setDescIsSet(true);
    return this;
  }

  public void unsetDesc() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __DESC_ISSET_ID);
  }

  /** Returns true if field desc is set (has been assigned a value) and false otherwise */
  public boolean isSetDesc() {
    return EncodingUtils.testBit(__isset_bitfield, __DESC_ISSET_ID);
  }

  public void setDescIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __DESC_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case SORTED_FIELD:
      if (value == null) {
        unsetSortedField();
      } else {
        setSortedField((String)value);
      }
      break;

    case DESC:
      if (value == null) {
        unsetDesc();
      } else {
        setDesc((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case SORTED_FIELD:
      return getSortedField();

    case DESC:
      return getDesc();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case SORTED_FIELD:
      return isSetSortedField();
    case DESC:
      return isSetDesc();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TSortStruct)
      return this.equals((TSortStruct)that);
    return false;
  }

  public boolean equals(TSortStruct that) {
    if (that == null)
      return false;

    boolean this_present_sortedField = true && this.isSetSortedField();
    boolean that_present_sortedField = true && that.isSetSortedField();
    if (this_present_sortedField || that_present_sortedField) {
      if (!(this_present_sortedField && that_present_sortedField))
        return false;
      if (!this.sortedField.equals(that.sortedField))
        return false;
    }

    boolean this_present_desc = true;
    boolean that_present_desc = true;
    if (this_present_desc || that_present_desc) {
      if (!(this_present_desc && that_present_desc))
        return false;
      if (this.desc != that.desc)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_sortedField = true && (isSetSortedField());
    list.add(present_sortedField);
    if (present_sortedField)
      list.add(sortedField);

    boolean present_desc = true;
    list.add(present_desc);
    if (present_desc)
      list.add(desc);

    return list.hashCode();
  }

  @Override
  public int compareTo(TSortStruct other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetSortedField()).compareTo(other.isSetSortedField());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSortedField()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sortedField, other.sortedField);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDesc()).compareTo(other.isSetDesc());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDesc()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.desc, other.desc);
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
    StringBuilder sb = new StringBuilder("TSortStruct(");
    boolean first = true;

    sb.append("sortedField:");
    if (this.sortedField == null) {
      sb.append("null");
    } else {
      sb.append(this.sortedField);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("desc:");
    sb.append(this.desc);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TSortStructStandardSchemeFactory implements SchemeFactory {
    public TSortStructStandardScheme getScheme() {
      return new TSortStructStandardScheme();
    }
  }

  private static class TSortStructStandardScheme extends StandardScheme<TSortStruct> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TSortStruct struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SORTED_FIELD
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.sortedField = iprot.readString();
              struct.setSortedFieldIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // DESC
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.desc = iprot.readI32();
              struct.setDescIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TSortStruct struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.sortedField != null) {
        oprot.writeFieldBegin(SORTED_FIELD_FIELD_DESC);
        oprot.writeString(struct.sortedField);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(DESC_FIELD_DESC);
      oprot.writeI32(struct.desc);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TSortStructTupleSchemeFactory implements SchemeFactory {
    public TSortStructTupleScheme getScheme() {
      return new TSortStructTupleScheme();
    }
  }

  private static class TSortStructTupleScheme extends TupleScheme<TSortStruct> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TSortStruct struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetSortedField()) {
        optionals.set(0);
      }
      if (struct.isSetDesc()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetSortedField()) {
        oprot.writeString(struct.sortedField);
      }
      if (struct.isSetDesc()) {
        oprot.writeI32(struct.desc);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TSortStruct struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.sortedField = iprot.readString();
        struct.setSortedFieldIsSet(true);
      }
      if (incoming.get(1)) {
        struct.desc = iprot.readI32();
        struct.setDescIsSet(true);
      }
    }
  }

}

