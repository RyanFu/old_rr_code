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
package org.jboss.netty.channel.xnio;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.xnio.Connector;

/**
 * A client-side {@link ChannelFactory} which uses
 * <a href="http://www.jboss.org/xnio/">JBoss XNIO</a> as its I/O provider.
 *
 * @author The Netty Project (netty-dev@lists.jboss.org)
 * @author Trustin Lee (tlee@redhat.com)
 * @version $Rev: 1685 $, $Date: 2009-08-28 16:15:49 +0900 (금, 28 8 2009) $
 */
@SuppressWarnings("unchecked")
public class XnioClientChannelFactory implements ChannelFactory {

    private final Connector xnioConnector;
    private final XnioClientChannelSink sink;

    public XnioClientChannelFactory(Connector xnioConnector) {
        if (xnioConnector == null) {
            throw new NullPointerException("xnioConnector");
        }
        this.xnioConnector = xnioConnector;
        sink = new XnioClientChannelSink();
    }

    public Channel newChannel(ChannelPipeline pipeline) {
        return new XnioClientChannel(this, pipeline, sink, xnioConnector);
    }

    public void releaseExternalResources() {
        // Nothing to release.
    }
}
