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
package org.jboss.netty.buffer;

import java.nio.ByteOrder;

/**
 * A {@link ChannelBufferFactory} which merely allocates a heap buffer with
 * the specified capacity.  {@link HeapChannelBufferFactory} should perform
 * very well in most situations because it relies on the JVM garbage collector,
 * which is highly optimized for heap allocation.
 *
 * @author The Netty Project (netty-dev@lists.jboss.org)
 * @author Trustin Lee (tlee@redhat.com)
 * @version $Rev: 1685 $, $Date: 2009-08-28 16:15:49 +0900 (금, 28 8 2009) $
 */
public class HeapChannelBufferFactory extends AbstractChannelBufferFactory {

    private static final HeapChannelBufferFactory INSTANCE_BE =
        new HeapChannelBufferFactory(ByteOrder.BIG_ENDIAN);

    private static final HeapChannelBufferFactory INSTANCE_LE =
        new HeapChannelBufferFactory(ByteOrder.LITTLE_ENDIAN);

    public static ChannelBufferFactory getInstance() {
        return INSTANCE_BE;
    }

    public static ChannelBufferFactory getInstance(ByteOrder endianness) {
        if (endianness == ByteOrder.BIG_ENDIAN) {
            return INSTANCE_BE;
        } else if (endianness == ByteOrder.LITTLE_ENDIAN) {
            return INSTANCE_LE;
        } else if (endianness == null) {
            throw new NullPointerException("endianness");
        } else {
            throw new IllegalStateException("Should not reach here");
        }
    }

    /**
     * Creates a new factory whose default {@link ByteOrder} is
     * {@link ByteOrder#BIG_ENDIAN}.
     */
    public HeapChannelBufferFactory() {
        super();
    }

    /**
     * Creates a new factory with the specified default {@link ByteOrder}.
     *
     * @param defaultOrder the default {@link ByteOrder} of this factory
     */
    public HeapChannelBufferFactory(ByteOrder defaultOrder) {
        super(defaultOrder);
    }

    public ChannelBuffer getBuffer(ByteOrder order, int capacity) {
        return ChannelBuffers.buffer(order, capacity);
    }
}
