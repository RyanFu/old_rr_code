/*
 * Copyright 2009 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.jboss.netty.handler.codec.protobuf;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferInputStream;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineCoverage;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.Message;

/**
 * Decodes a received {@link ChannelBuffer} into a
 * <a href="http://code.google.com/p/protobuf/">Google Protocol Buffers</a>
 * {@link Message}.  Please note that this decoder must be used with a proper
 * {@link FrameDecoder} such as {@link LengthFieldBasedFrameDecoder} if you are
 * using a stream-based transport such as TCP/IP.  A typical setup for TCP/IP
 * would be:
 * <pre>
 * {@link ChannelPipeline} pipeline = ...;
 *
 * // Decoders
 * pipeline.addLast("frameDecoder",
 *                  new {@link LengthFieldBasedFrameDecoder}(1048576, 0, 4, 0, 4));
 * pipeline.addLast("protobufDecoder",
 *                  new {@link ProtobufDecoder}(MyMessage.getDefaultInstance()));
 *
 * // Encoder
 * pipeline.addLast("frameEncoder", new {@link LengthFieldPrepender}(4));
 * pipeline.addLast("protobufEncoder", new {@link ProtobufEncoder}());
 * </pre>
 * and then you can use a {@code MyMessage} instead of a {@link ChannelBuffer}
 * as a message:
 * <pre>
 * void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
 *     MyMessage req = (MyMessage) e.getMessage();
 *     MyMessage res = MyMessage.newBuilder().setText(
 *                               "Did you say '" + req.getText() + "'?").build();
 *     ch.write(res);
 * }
 * </pre>
 *
 * @author The Netty Project (netty-dev@lists.jboss.org)
 * @author Trustin Lee (tlee@redhat.com)
 *
 * @version $Rev:231 $, $Date:2008-06-12 16:44:50 +0900 (목, 12 6월 2008) $
 *
 * @apiviz.landmark
 */
@ChannelPipelineCoverage("all")
public class ProtobufDecoder extends OneToOneDecoder {

    private final Message prototype;
    private final ExtensionRegistry extensionRegistry;

    /**
     * Creates a new instance.
     */
    public ProtobufDecoder(Message prototype) {
        this(prototype, null);
    }

    public ProtobufDecoder(Message prototype, ExtensionRegistry extensionRegistry) {
        if (prototype == null) {
            throw new NullPointerException("prototype");
        }
        this.prototype = prototype.getDefaultInstanceForType();
        this.extensionRegistry = extensionRegistry;
    }

    @Override
    protected Object decode(
            ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
        if (!(msg instanceof ChannelBuffer)) {
            return msg;
        }

        if (extensionRegistry == null) {
            return prototype.newBuilderForType().mergeFrom(
                    new ChannelBufferInputStream((ChannelBuffer) msg)).build();
        } else {
            return prototype.newBuilderForType().mergeFrom(
                    new ChannelBufferInputStream((ChannelBuffer) msg),
                    extensionRegistry).build();
        }
    }
}
