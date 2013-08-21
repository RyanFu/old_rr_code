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
package org.jboss.netty.container.microcontainer;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.logging.JBossLoggerFactory;
import org.jboss.netty.util.internal.ExecutorUtil;
import org.jboss.netty.util.internal.UnterminatableExecutor;

/**
 * A factory bean that provides the common resources required by
 * {@link ChannelFactory} implementations.
 *
 * @author The Netty Project (netty-dev@lists.jboss.org)
 * @author Trustin Lee (tlee@redhat.com)
 * @version $Rev: 1685 $, $Date: 2009-08-28 16:15:49 +0900 (금, 28 8 2009) $
 */
public class NettyResourceFactory {
    private Executor executor;
    private Executor unterminatableExecutor;

    public synchronized void create() {
        if (executor != null) {
            return;
        }

        executor = Executors.newCachedThreadPool();
        unterminatableExecutor = new UnterminatableExecutor(executor);
    }

    public void start() {
        InternalLoggerFactory.setDefaultFactory(new JBossLoggerFactory());
    }

    public synchronized void stop() {
        if (executor != null) {
            ExecutorUtil.terminate(executor);
        }
    }

    public synchronized void destroy() {
        executor = null;
        unterminatableExecutor = null;
    }

    public synchronized Executor getChannelFactoryExecutor() {
        return unterminatableExecutor;
    }
}
