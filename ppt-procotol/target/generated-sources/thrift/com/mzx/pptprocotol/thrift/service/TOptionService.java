/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.mzx.pptprocotol.thrift.service;

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
public class TOptionService {

  public interface Iface {

    public com.mzx.pptprocotol.thrift.struct.PPTBytes swichPPTPage(com.mzx.pptprocotol.thrift.struct.PPTDetail parm) throws org.apache.thrift.TException;

  }

  public interface AsyncIface {

    public void swichPPTPage(com.mzx.pptprocotol.thrift.struct.PPTDetail parm, org.apache.thrift.async.AsyncMethodCallback resultHandler) throws org.apache.thrift.TException;

  }

  public static class Client extends org.apache.thrift.TServiceClient implements Iface {
    public static class Factory implements org.apache.thrift.TServiceClientFactory<Client> {
      public Factory() {}
      public Client getClient(org.apache.thrift.protocol.TProtocol prot) {
        return new Client(prot);
      }
      public Client getClient(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
        return new Client(iprot, oprot);
      }
    }

    public Client(org.apache.thrift.protocol.TProtocol prot)
    {
      super(prot, prot);
    }

    public Client(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
      super(iprot, oprot);
    }

    public com.mzx.pptprocotol.thrift.struct.PPTBytes swichPPTPage(com.mzx.pptprocotol.thrift.struct.PPTDetail parm) throws org.apache.thrift.TException
    {
      send_swichPPTPage(parm);
      return recv_swichPPTPage();
    }

    public void send_swichPPTPage(com.mzx.pptprocotol.thrift.struct.PPTDetail parm) throws org.apache.thrift.TException
    {
      swichPPTPage_args args = new swichPPTPage_args();
      args.setParm(parm);
      sendBase("swichPPTPage", args);
    }

    public com.mzx.pptprocotol.thrift.struct.PPTBytes recv_swichPPTPage() throws org.apache.thrift.TException
    {
      swichPPTPage_result result = new swichPPTPage_result();
      receiveBase(result, "swichPPTPage");
      if (result.isSetSuccess()) {
        return result.success;
      }
      throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT, "swichPPTPage failed: unknown result");
    }

  }
  public static class AsyncClient extends org.apache.thrift.async.TAsyncClient implements AsyncIface {
    public static class Factory implements org.apache.thrift.async.TAsyncClientFactory<AsyncClient> {
      private org.apache.thrift.async.TAsyncClientManager clientManager;
      private org.apache.thrift.protocol.TProtocolFactory protocolFactory;
      public Factory(org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.protocol.TProtocolFactory protocolFactory) {
        this.clientManager = clientManager;
        this.protocolFactory = protocolFactory;
      }
      public AsyncClient getAsyncClient(org.apache.thrift.transport.TNonblockingTransport transport) {
        return new AsyncClient(protocolFactory, clientManager, transport);
      }
    }

    public AsyncClient(org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.transport.TNonblockingTransport transport) {
      super(protocolFactory, clientManager, transport);
    }

    public void swichPPTPage(com.mzx.pptprocotol.thrift.struct.PPTDetail parm, org.apache.thrift.async.AsyncMethodCallback resultHandler) throws org.apache.thrift.TException {
      checkReady();
      swichPPTPage_call method_call = new swichPPTPage_call(parm, resultHandler, this, ___protocolFactory, ___transport);
      this.___currentMethod = method_call;
      ___manager.call(method_call);
    }

    public static class swichPPTPage_call extends org.apache.thrift.async.TAsyncMethodCall {
      private com.mzx.pptprocotol.thrift.struct.PPTDetail parm;
      public swichPPTPage_call(com.mzx.pptprocotol.thrift.struct.PPTDetail parm, org.apache.thrift.async.AsyncMethodCallback resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
        super(client, protocolFactory, transport, resultHandler, false);
        this.parm = parm;
      }

      public void write_args(org.apache.thrift.protocol.TProtocol prot) throws org.apache.thrift.TException {
        prot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("swichPPTPage", org.apache.thrift.protocol.TMessageType.CALL, 0));
        swichPPTPage_args args = new swichPPTPage_args();
        args.setParm(parm);
        args.write(prot);
        prot.writeMessageEnd();
      }

      public com.mzx.pptprocotol.thrift.struct.PPTBytes getResult() throws org.apache.thrift.TException {
        if (getState() != org.apache.thrift.async.TAsyncMethodCall.State.RESPONSE_READ) {
          throw new IllegalStateException("Method call not finished!");
        }
        org.apache.thrift.transport.TMemoryInputTransport memoryTransport = new org.apache.thrift.transport.TMemoryInputTransport(getFrameBuffer().array());
        org.apache.thrift.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
        return (new Client(prot)).recv_swichPPTPage();
      }
    }

  }

  public static class Processor<I extends Iface> extends org.apache.thrift.TBaseProcessor<I> implements org.apache.thrift.TProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class.getName());
    public Processor(I iface) {
      super(iface, getProcessMap(new HashMap<String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>>()));
    }

    protected Processor(I iface, Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends Iface> Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> getProcessMap(Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      processMap.put("swichPPTPage", new swichPPTPage());
      return processMap;
    }

    public static class swichPPTPage<I extends Iface> extends org.apache.thrift.ProcessFunction<I, swichPPTPage_args> {
      public swichPPTPage() {
        super("swichPPTPage");
      }

      public swichPPTPage_args getEmptyArgsInstance() {
        return new swichPPTPage_args();
      }

      protected boolean isOneway() {
        return false;
      }

      public swichPPTPage_result getResult(I iface, swichPPTPage_args args) throws org.apache.thrift.TException {
        swichPPTPage_result result = new swichPPTPage_result();
        result.success = iface.swichPPTPage(args.parm);
        return result;
      }
    }

  }

  public static class AsyncProcessor<I extends AsyncIface> extends org.apache.thrift.TBaseAsyncProcessor<I> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncProcessor.class.getName());
    public AsyncProcessor(I iface) {
      super(iface, getProcessMap(new HashMap<String, org.apache.thrift.AsyncProcessFunction<I, ? extends org.apache.thrift.TBase, ?>>()));
    }

    protected AsyncProcessor(I iface, Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends AsyncIface> Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase,?>> getProcessMap(Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      processMap.put("swichPPTPage", new swichPPTPage());
      return processMap;
    }

    public static class swichPPTPage<I extends AsyncIface> extends org.apache.thrift.AsyncProcessFunction<I, swichPPTPage_args, com.mzx.pptprocotol.thrift.struct.PPTBytes> {
      public swichPPTPage() {
        super("swichPPTPage");
      }

      public swichPPTPage_args getEmptyArgsInstance() {
        return new swichPPTPage_args();
      }

      public AsyncMethodCallback<com.mzx.pptprocotol.thrift.struct.PPTBytes> getResultHandler(final AsyncFrameBuffer fb, final int seqid) {
        final org.apache.thrift.AsyncProcessFunction fcall = this;
        return new AsyncMethodCallback<com.mzx.pptprocotol.thrift.struct.PPTBytes>() { 
          public void onComplete(com.mzx.pptprocotol.thrift.struct.PPTBytes o) {
            swichPPTPage_result result = new swichPPTPage_result();
            result.success = o;
            try {
              fcall.sendResponse(fb,result, org.apache.thrift.protocol.TMessageType.REPLY,seqid);
              return;
            } catch (Exception e) {
              LOGGER.error("Exception writing to internal frame buffer", e);
            }
            fb.close();
          }
          public void onError(Exception e) {
            byte msgType = org.apache.thrift.protocol.TMessageType.REPLY;
            org.apache.thrift.TBase msg;
            swichPPTPage_result result = new swichPPTPage_result();
            {
              msgType = org.apache.thrift.protocol.TMessageType.EXCEPTION;
              msg = (org.apache.thrift.TBase)new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.INTERNAL_ERROR, e.getMessage());
            }
            try {
              fcall.sendResponse(fb,msg,msgType,seqid);
              return;
            } catch (Exception ex) {
              LOGGER.error("Exception writing to internal frame buffer", ex);
            }
            fb.close();
          }
        };
      }

      protected boolean isOneway() {
        return false;
      }

      public void start(I iface, swichPPTPage_args args, org.apache.thrift.async.AsyncMethodCallback<com.mzx.pptprocotol.thrift.struct.PPTBytes> resultHandler) throws TException {
        iface.swichPPTPage(args.parm,resultHandler);
      }
    }

  }

  public static class swichPPTPage_args implements org.apache.thrift.TBase<swichPPTPage_args, swichPPTPage_args._Fields>, java.io.Serializable, Cloneable, Comparable<swichPPTPage_args>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("swichPPTPage_args");

    private static final org.apache.thrift.protocol.TField PARM_FIELD_DESC = new org.apache.thrift.protocol.TField("parm", org.apache.thrift.protocol.TType.STRUCT, (short)1);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new swichPPTPage_argsStandardSchemeFactory());
      schemes.put(TupleScheme.class, new swichPPTPage_argsTupleSchemeFactory());
    }

    public com.mzx.pptprocotol.thrift.struct.PPTDetail parm; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      PARM((short)1, "parm");

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
          case 1: // PARM
            return PARM;
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
      tmpMap.put(_Fields.PARM, new org.apache.thrift.meta_data.FieldMetaData("parm", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, com.mzx.pptprocotol.thrift.struct.PPTDetail.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(swichPPTPage_args.class, metaDataMap);
    }

    public swichPPTPage_args() {
    }

    public swichPPTPage_args(
      com.mzx.pptprocotol.thrift.struct.PPTDetail parm)
    {
      this();
      this.parm = parm;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public swichPPTPage_args(swichPPTPage_args other) {
      if (other.isSetParm()) {
        this.parm = new com.mzx.pptprocotol.thrift.struct.PPTDetail(other.parm);
      }
    }

    public swichPPTPage_args deepCopy() {
      return new swichPPTPage_args(this);
    }

    @Override
    public void clear() {
      this.parm = null;
    }

    public com.mzx.pptprocotol.thrift.struct.PPTDetail getParm() {
      return this.parm;
    }

    public swichPPTPage_args setParm(com.mzx.pptprocotol.thrift.struct.PPTDetail parm) {
      this.parm = parm;
      return this;
    }

    public void unsetParm() {
      this.parm = null;
    }

    /** Returns true if field parm is set (has been assigned a value) and false otherwise */
    public boolean isSetParm() {
      return this.parm != null;
    }

    public void setParmIsSet(boolean value) {
      if (!value) {
        this.parm = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case PARM:
        if (value == null) {
          unsetParm();
        } else {
          setParm((com.mzx.pptprocotol.thrift.struct.PPTDetail)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case PARM:
        return getParm();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case PARM:
        return isSetParm();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof swichPPTPage_args)
        return this.equals((swichPPTPage_args)that);
      return false;
    }

    public boolean equals(swichPPTPage_args that) {
      if (that == null)
        return false;

      boolean this_present_parm = true && this.isSetParm();
      boolean that_present_parm = true && that.isSetParm();
      if (this_present_parm || that_present_parm) {
        if (!(this_present_parm && that_present_parm))
          return false;
        if (!this.parm.equals(that.parm))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      List<Object> list = new ArrayList<Object>();

      boolean present_parm = true && (isSetParm());
      list.add(present_parm);
      if (present_parm)
        list.add(parm);

      return list.hashCode();
    }

    @Override
    public int compareTo(swichPPTPage_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = Boolean.valueOf(isSetParm()).compareTo(other.isSetParm());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetParm()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.parm, other.parm);
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
      StringBuilder sb = new StringBuilder("swichPPTPage_args(");
      boolean first = true;

      sb.append("parm:");
      if (this.parm == null) {
        sb.append("null");
      } else {
        sb.append(this.parm);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      // check for sub-struct validity
      if (parm != null) {
        parm.validate();
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

    private static class swichPPTPage_argsStandardSchemeFactory implements SchemeFactory {
      public swichPPTPage_argsStandardScheme getScheme() {
        return new swichPPTPage_argsStandardScheme();
      }
    }

    private static class swichPPTPage_argsStandardScheme extends StandardScheme<swichPPTPage_args> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, swichPPTPage_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 1: // PARM
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.parm = new com.mzx.pptprocotol.thrift.struct.PPTDetail();
                struct.parm.read(iprot);
                struct.setParmIsSet(true);
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

      public void write(org.apache.thrift.protocol.TProtocol oprot, swichPPTPage_args struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.parm != null) {
          oprot.writeFieldBegin(PARM_FIELD_DESC);
          struct.parm.write(oprot);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class swichPPTPage_argsTupleSchemeFactory implements SchemeFactory {
      public swichPPTPage_argsTupleScheme getScheme() {
        return new swichPPTPage_argsTupleScheme();
      }
    }

    private static class swichPPTPage_argsTupleScheme extends TupleScheme<swichPPTPage_args> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, swichPPTPage_args struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetParm()) {
          optionals.set(0);
        }
        oprot.writeBitSet(optionals, 1);
        if (struct.isSetParm()) {
          struct.parm.write(oprot);
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, swichPPTPage_args struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(1);
        if (incoming.get(0)) {
          struct.parm = new com.mzx.pptprocotol.thrift.struct.PPTDetail();
          struct.parm.read(iprot);
          struct.setParmIsSet(true);
        }
      }
    }

  }

  public static class swichPPTPage_result implements org.apache.thrift.TBase<swichPPTPage_result, swichPPTPage_result._Fields>, java.io.Serializable, Cloneable, Comparable<swichPPTPage_result>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("swichPPTPage_result");

    private static final org.apache.thrift.protocol.TField SUCCESS_FIELD_DESC = new org.apache.thrift.protocol.TField("success", org.apache.thrift.protocol.TType.STRUCT, (short)0);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new swichPPTPage_resultStandardSchemeFactory());
      schemes.put(TupleScheme.class, new swichPPTPage_resultTupleSchemeFactory());
    }

    public com.mzx.pptprocotol.thrift.struct.PPTBytes success; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      SUCCESS((short)0, "success");

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
          case 0: // SUCCESS
            return SUCCESS;
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
      tmpMap.put(_Fields.SUCCESS, new org.apache.thrift.meta_data.FieldMetaData("success", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, com.mzx.pptprocotol.thrift.struct.PPTBytes.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(swichPPTPage_result.class, metaDataMap);
    }

    public swichPPTPage_result() {
    }

    public swichPPTPage_result(
      com.mzx.pptprocotol.thrift.struct.PPTBytes success)
    {
      this();
      this.success = success;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public swichPPTPage_result(swichPPTPage_result other) {
      if (other.isSetSuccess()) {
        this.success = new com.mzx.pptprocotol.thrift.struct.PPTBytes(other.success);
      }
    }

    public swichPPTPage_result deepCopy() {
      return new swichPPTPage_result(this);
    }

    @Override
    public void clear() {
      this.success = null;
    }

    public com.mzx.pptprocotol.thrift.struct.PPTBytes getSuccess() {
      return this.success;
    }

    public swichPPTPage_result setSuccess(com.mzx.pptprocotol.thrift.struct.PPTBytes success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been assigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((com.mzx.pptprocotol.thrift.struct.PPTBytes)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof swichPPTPage_result)
        return this.equals((swichPPTPage_result)that);
      return false;
    }

    public boolean equals(swichPPTPage_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      List<Object> list = new ArrayList<Object>();

      boolean present_success = true && (isSetSuccess());
      list.add(present_success);
      if (present_success)
        list.add(success);

      return list.hashCode();
    }

    @Override
    public int compareTo(swichPPTPage_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(other.isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetSuccess()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.success, other.success);
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
      StringBuilder sb = new StringBuilder("swichPPTPage_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      // check for sub-struct validity
      if (success != null) {
        success.validate();
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

    private static class swichPPTPage_resultStandardSchemeFactory implements SchemeFactory {
      public swichPPTPage_resultStandardScheme getScheme() {
        return new swichPPTPage_resultStandardScheme();
      }
    }

    private static class swichPPTPage_resultStandardScheme extends StandardScheme<swichPPTPage_result> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, swichPPTPage_result struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 0: // SUCCESS
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.success = new com.mzx.pptprocotol.thrift.struct.PPTBytes();
                struct.success.read(iprot);
                struct.setSuccessIsSet(true);
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

      public void write(org.apache.thrift.protocol.TProtocol oprot, swichPPTPage_result struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.success != null) {
          oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
          struct.success.write(oprot);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class swichPPTPage_resultTupleSchemeFactory implements SchemeFactory {
      public swichPPTPage_resultTupleScheme getScheme() {
        return new swichPPTPage_resultTupleScheme();
      }
    }

    private static class swichPPTPage_resultTupleScheme extends TupleScheme<swichPPTPage_result> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, swichPPTPage_result struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetSuccess()) {
          optionals.set(0);
        }
        oprot.writeBitSet(optionals, 1);
        if (struct.isSetSuccess()) {
          struct.success.write(oprot);
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, swichPPTPage_result struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(1);
        if (incoming.get(0)) {
          struct.success = new com.mzx.pptprocotol.thrift.struct.PPTBytes();
          struct.success.read(iprot);
          struct.setSuccessIsSet(true);
        }
      }
    }

  }

}