// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: item.proto

package com.xiaonei.xce.socialgraph.proto;

public final class GeneratedItems {
  private GeneratedItems() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public static final class Item extends
      com.google.protobuf.GeneratedMessage {
    // Use Item.newBuilder() to construct.
    private Item() {
      initFields();
    }
    private Item(boolean noInit) {}
    
    private static final Item defaultInstance;
    public static Item getDefaultInstance() {
      return defaultInstance;
    }
    
    public Item getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.xiaonei.xce.socialgraph.proto.GeneratedItems.internal_static_xce_socialgraph_Item_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.xiaonei.xce.socialgraph.proto.GeneratedItems.internal_static_xce_socialgraph_Item_fieldAccessorTable;
    }
    
    // required int32 id = 1;
    public static final int ID_FIELD_NUMBER = 1;
    private boolean hasId;
    private int id_ = 0;
    public boolean hasId() { return hasId; }
    public int getId() { return id_; }
    
    // optional int32 field = 2;
    public static final int FIELD_FIELD_NUMBER = 2;
    private boolean hasField;
    private int field_ = 0;
    public boolean hasField() { return hasField; }
    public int getField() { return field_; }
    
    // optional string message = 3;
    public static final int MESSAGE_FIELD_NUMBER = 3;
    private boolean hasMessage;
    private java.lang.String message_ = "";
    public boolean hasMessage() { return hasMessage; }
    public java.lang.String getMessage() { return message_; }
    
    // repeated int32 fields = 5;
    public static final int FIELDS_FIELD_NUMBER = 5;
    private java.util.List<java.lang.Integer> fields_ =
      java.util.Collections.emptyList();
    public java.util.List<java.lang.Integer> getFieldsList() {
      return fields_;
    }
    public int getFieldsCount() { return fields_.size(); }
    public int getFields(int index) {
      return fields_.get(index);
    }
    
    // repeated string messages = 6;
    public static final int MESSAGES_FIELD_NUMBER = 6;
    private java.util.List<java.lang.String> messages_ =
      java.util.Collections.emptyList();
    public java.util.List<java.lang.String> getMessagesList() {
      return messages_;
    }
    public int getMessagesCount() { return messages_.size(); }
    public java.lang.String getMessages(int index) {
      return messages_.get(index);
    }
    
    private void initFields() {
    }
    public final boolean isInitialized() {
      if (!hasId) return false;
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (hasId()) {
        output.writeInt32(1, getId());
      }
      if (hasField()) {
        output.writeInt32(2, getField());
      }
      if (hasMessage()) {
        output.writeString(3, getMessage());
      }
      for (int element : getFieldsList()) {
        output.writeInt32(5, element);
      }
      for (java.lang.String element : getMessagesList()) {
        output.writeString(6, element);
      }
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      if (hasId()) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, getId());
      }
      if (hasField()) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, getField());
      }
      if (hasMessage()) {
        size += com.google.protobuf.CodedOutputStream
          .computeStringSize(3, getMessage());
      }
      {
        int dataSize = 0;
        for (int element : getFieldsList()) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(element);
        }
        size += dataSize;
        size += 1 * getFieldsList().size();
      }
      {
        int dataSize = 0;
        for (java.lang.String element : getMessagesList()) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeStringSizeNoTag(element);
        }
        size += dataSize;
        size += 1 * getMessagesList().size();
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    public static com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> {
      private com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item result;
      
      // Construct using com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item.newBuilder()
      private Builder() {}
      
      private static Builder create() {
        Builder builder = new Builder();
        builder.result = new com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item();
        return builder;
      }
      
      protected com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item internalGetResult() {
        return result;
      }
      
      public Builder clear() {
        if (result == null) {
          throw new IllegalStateException(
            "Cannot call clear() after build().");
        }
        result = new com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item();
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(result);
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item.getDescriptor();
      }
      
      public com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item getDefaultInstanceForType() {
        return com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item.getDefaultInstance();
      }
      
      public boolean isInitialized() {
        return result.isInitialized();
      }
      public com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item build() {
        if (result != null && !isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return buildPartial();
      }
      
      private com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        if (!isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return buildPartial();
      }
      
      public com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item buildPartial() {
        if (result == null) {
          throw new IllegalStateException(
            "build() has already been called on this Builder.");
        }
        if (result.fields_ != java.util.Collections.EMPTY_LIST) {
          result.fields_ =
            java.util.Collections.unmodifiableList(result.fields_);
        }
        if (result.messages_ != java.util.Collections.EMPTY_LIST) {
          result.messages_ =
            java.util.Collections.unmodifiableList(result.messages_);
        }
        com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item returnMe = result;
        result = null;
        return returnMe;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item) {
          return mergeFrom((com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item other) {
        if (other == com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item.getDefaultInstance()) return this;
        if (other.hasId()) {
          setId(other.getId());
        }
        if (other.hasField()) {
          setField(other.getField());
        }
        if (other.hasMessage()) {
          setMessage(other.getMessage());
        }
        if (!other.fields_.isEmpty()) {
          if (result.fields_.isEmpty()) {
            result.fields_ = new java.util.ArrayList<java.lang.Integer>();
          }
          result.fields_.addAll(other.fields_);
        }
        if (!other.messages_.isEmpty()) {
          if (result.messages_.isEmpty()) {
            result.messages_ = new java.util.ArrayList<java.lang.String>();
          }
          result.messages_.addAll(other.messages_);
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                return this;
              }
              break;
            }
            case 8: {
              setId(input.readInt32());
              break;
            }
            case 16: {
              setField(input.readInt32());
              break;
            }
            case 26: {
              setMessage(input.readString());
              break;
            }
            case 40: {
              addFields(input.readInt32());
              break;
            }
            case 42: {
              int length = input.readRawVarint32();
              int limit = input.pushLimit(length);
              while (input.getBytesUntilLimit() > 0) {
                addFields(input.readInt32());
              }
              input.popLimit(limit);
              break;
            }
            case 50: {
              addMessages(input.readString());
              break;
            }
          }
        }
      }
      
      
      // required int32 id = 1;
      public boolean hasId() {
        return result.hasId();
      }
      public int getId() {
        return result.getId();
      }
      public Builder setId(int value) {
        result.hasId = true;
        result.id_ = value;
        return this;
      }
      public Builder clearId() {
        result.hasId = false;
        result.id_ = 0;
        return this;
      }
      
      // optional int32 field = 2;
      public boolean hasField() {
        return result.hasField();
      }
      public int getField() {
        return result.getField();
      }
      public Builder setField(int value) {
        result.hasField = true;
        result.field_ = value;
        return this;
      }
      public Builder clearField() {
        result.hasField = false;
        result.field_ = 0;
        return this;
      }
      
      // optional string message = 3;
      public boolean hasMessage() {
        return result.hasMessage();
      }
      public java.lang.String getMessage() {
        return result.getMessage();
      }
      public Builder setMessage(java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  result.hasMessage = true;
        result.message_ = value;
        return this;
      }
      public Builder clearMessage() {
        result.hasMessage = false;
        result.message_ = getDefaultInstance().getMessage();
        return this;
      }
      
      // repeated int32 fields = 5;
      public java.util.List<java.lang.Integer> getFieldsList() {
        return java.util.Collections.unmodifiableList(result.fields_);
      }
      public int getFieldsCount() {
        return result.getFieldsCount();
      }
      public int getFields(int index) {
        return result.getFields(index);
      }
      public Builder setFields(int index, int value) {
        result.fields_.set(index, value);
        return this;
      }
      public Builder addFields(int value) {
        if (result.fields_.isEmpty()) {
          result.fields_ = new java.util.ArrayList<java.lang.Integer>();
        }
        result.fields_.add(value);
        return this;
      }
      public Builder addAllFields(
          java.lang.Iterable<? extends java.lang.Integer> values) {
        if (result.fields_.isEmpty()) {
          result.fields_ = new java.util.ArrayList<java.lang.Integer>();
        }
        super.addAll(values, result.fields_);
        return this;
      }
      public Builder clearFields() {
        result.fields_ = java.util.Collections.emptyList();
        return this;
      }
      
      // repeated string messages = 6;
      public java.util.List<java.lang.String> getMessagesList() {
        return java.util.Collections.unmodifiableList(result.messages_);
      }
      public int getMessagesCount() {
        return result.getMessagesCount();
      }
      public java.lang.String getMessages(int index) {
        return result.getMessages(index);
      }
      public Builder setMessages(int index, java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  result.messages_.set(index, value);
        return this;
      }
      public Builder addMessages(java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  if (result.messages_.isEmpty()) {
          result.messages_ = new java.util.ArrayList<java.lang.String>();
        }
        result.messages_.add(value);
        return this;
      }
      public Builder addAllMessages(
          java.lang.Iterable<? extends java.lang.String> values) {
        if (result.messages_.isEmpty()) {
          result.messages_ = new java.util.ArrayList<java.lang.String>();
        }
        super.addAll(values, result.messages_);
        return this;
      }
      public Builder clearMessages() {
        result.messages_ = java.util.Collections.emptyList();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:xce.socialgraph.Item)
    }
    
    static {
      defaultInstance = new Item(true);
      com.xiaonei.xce.socialgraph.proto.GeneratedItems.internalForceInit();
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:xce.socialgraph.Item)
  }
  
  public static final class Items extends
      com.google.protobuf.GeneratedMessage {
    // Use Items.newBuilder() to construct.
    private Items() {
      initFields();
    }
    private Items(boolean noInit) {}
    
    private static final Items defaultInstance;
    public static Items getDefaultInstance() {
      return defaultInstance;
    }
    
    public Items getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.xiaonei.xce.socialgraph.proto.GeneratedItems.internal_static_xce_socialgraph_Items_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.xiaonei.xce.socialgraph.proto.GeneratedItems.internal_static_xce_socialgraph_Items_fieldAccessorTable;
    }
    
    // repeated .xce.socialgraph.Item items = 4;
    public static final int ITEMS_FIELD_NUMBER = 4;
    private java.util.List<com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item> items_ =
      java.util.Collections.emptyList();
    public java.util.List<com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item> getItemsList() {
      return items_;
    }
    public int getItemsCount() { return items_.size(); }
    public com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item getItems(int index) {
      return items_.get(index);
    }
    
    private void initFields() {
    }
    public final boolean isInitialized() {
      for (com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item element : getItemsList()) {
        if (!element.isInitialized()) return false;
      }
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      for (com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item element : getItemsList()) {
        output.writeMessage(4, element);
      }
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      for (com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item element : getItemsList()) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(4, element);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    public static com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> {
      private com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items result;
      
      // Construct using com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items.newBuilder()
      private Builder() {}
      
      private static Builder create() {
        Builder builder = new Builder();
        builder.result = new com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items();
        return builder;
      }
      
      protected com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items internalGetResult() {
        return result;
      }
      
      public Builder clear() {
        if (result == null) {
          throw new IllegalStateException(
            "Cannot call clear() after build().");
        }
        result = new com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items();
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(result);
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items.getDescriptor();
      }
      
      public com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items getDefaultInstanceForType() {
        return com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items.getDefaultInstance();
      }
      
      public boolean isInitialized() {
        return result.isInitialized();
      }
      public com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items build() {
        if (result != null && !isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return buildPartial();
      }
      
      private com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        if (!isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return buildPartial();
      }
      
      public com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items buildPartial() {
        if (result == null) {
          throw new IllegalStateException(
            "build() has already been called on this Builder.");
        }
        if (result.items_ != java.util.Collections.EMPTY_LIST) {
          result.items_ =
            java.util.Collections.unmodifiableList(result.items_);
        }
        com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items returnMe = result;
        result = null;
        return returnMe;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items) {
          return mergeFrom((com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items other) {
        if (other == com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items.getDefaultInstance()) return this;
        if (!other.items_.isEmpty()) {
          if (result.items_.isEmpty()) {
            result.items_ = new java.util.ArrayList<com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item>();
          }
          result.items_.addAll(other.items_);
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                return this;
              }
              break;
            }
            case 34: {
              com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item.Builder subBuilder = com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item.newBuilder();
              input.readMessage(subBuilder, extensionRegistry);
              addItems(subBuilder.buildPartial());
              break;
            }
          }
        }
      }
      
      
      // repeated .xce.socialgraph.Item items = 4;
      public java.util.List<com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item> getItemsList() {
        return java.util.Collections.unmodifiableList(result.items_);
      }
      public int getItemsCount() {
        return result.getItemsCount();
      }
      public com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item getItems(int index) {
        return result.getItems(index);
      }
      public Builder setItems(int index, com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item value) {
        if (value == null) {
          throw new NullPointerException();
        }
        result.items_.set(index, value);
        return this;
      }
      public Builder setItems(int index, com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item.Builder builderForValue) {
        result.items_.set(index, builderForValue.build());
        return this;
      }
      public Builder addItems(com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item value) {
        if (value == null) {
          throw new NullPointerException();
        }
        if (result.items_.isEmpty()) {
          result.items_ = new java.util.ArrayList<com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item>();
        }
        result.items_.add(value);
        return this;
      }
      public Builder addItems(com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item.Builder builderForValue) {
        if (result.items_.isEmpty()) {
          result.items_ = new java.util.ArrayList<com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item>();
        }
        result.items_.add(builderForValue.build());
        return this;
      }
      public Builder addAllItems(
          java.lang.Iterable<? extends com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item> values) {
        if (result.items_.isEmpty()) {
          result.items_ = new java.util.ArrayList<com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item>();
        }
        super.addAll(values, result.items_);
        return this;
      }
      public Builder clearItems() {
        result.items_ = java.util.Collections.emptyList();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:xce.socialgraph.Items)
    }
    
    static {
      defaultInstance = new Items(true);
      com.xiaonei.xce.socialgraph.proto.GeneratedItems.internalForceInit();
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:xce.socialgraph.Items)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_xce_socialgraph_Item_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_xce_socialgraph_Item_fieldAccessorTable;
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_xce_socialgraph_Items_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_xce_socialgraph_Items_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\nitem.proto\022\017xce.socialgraph\"T\n\004Item\022\n\n" +
      "\002id\030\001 \002(\005\022\r\n\005field\030\002 \001(\005\022\017\n\007message\030\003 \001(" +
      "\t\022\016\n\006fields\030\005 \003(\005\022\020\n\010messages\030\006 \003(\t\"-\n\005I" +
      "tems\022$\n\005items\030\004 \003(\0132\025.xce.socialgraph.It" +
      "emB3\n!com.xiaonei.xce.socialgraph.protoB" +
      "\016GeneratedItems"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_xce_socialgraph_Item_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_xce_socialgraph_Item_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_xce_socialgraph_Item_descriptor,
              new java.lang.String[] { "Id", "Field", "Message", "Fields", "Messages", },
              com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item.class,
              com.xiaonei.xce.socialgraph.proto.GeneratedItems.Item.Builder.class);
          internal_static_xce_socialgraph_Items_descriptor =
            getDescriptor().getMessageTypes().get(1);
          internal_static_xce_socialgraph_Items_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_xce_socialgraph_Items_descriptor,
              new java.lang.String[] { "Items", },
              com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items.class,
              com.xiaonei.xce.socialgraph.proto.GeneratedItems.Items.Builder.class);
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }
  
  public static void internalForceInit() {}
  
  // @@protoc_insertion_point(outer_class_scope)
}