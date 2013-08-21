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
 * A skeletal implementation of {@link ChannelBufferFactory}.
 *
 * @author The Netty Project (netty-dev@lists.jboss.org)
 * @author Trustin Lee (tlee@redhat.com)
 *
 * @version $Rev: 1685 $, $Date: 2009-08-28 16:15:49 +0900 (금, 28 8 2009) $
 */
public abstract class AbstractChannelBufferFactory implements ChannelBufferFactory {

    private final ByteOrder defaultOrder;

    /**
     * Creates a new factory whose default {@link ByteOrder} is
     * {@link ByteOrder#BIG_ENDIAN}.
     */
    protected AbstractChannelBufferFactory() {
        this(ByteOrder.BIG_ENDIAN);
    }

    /**
     * Creates a new factory with the specified default {@link ByteOrder}.
     *
     * @param defaultOrder the default {@link ByteOrder} of this factory
     */
    protected AbstractChannelBufferFactory(ByteOrder defaultOrder) {
        if (defaultOrder == null) {
            throw new NullPointerException("defaultOrder");
        }
        this.defaultOrder = defaultOrder;
    }

    public ChannelBuffer getBuffer(int capacity) {
        return getBuffer(getDefaultOrder(), capacity);
    }

    public ByteOrder getDefaultOrder() {
        return defaultOrder;
    }
}
