/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.pulsar.client.impl;

import org.apache.pulsar.client.api.ConsumerStats;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.metrics.ConsumerMetricsTracker;

public class NoOpConsumerMetricsTracker implements ConsumerMetricsTracker {

    static final NoOpConsumerMetricsTracker INSTANCE = new NoOpConsumerMetricsTracker();

    @Override
    public void recordMessageReceived(Message<?> message) {
        // Do nothing
    }

    @Override
    public void recordAcksSent(long numAcks) {
        // Do nothing
    }

    @Override
    public void recordAckFailed() {

    }

    @Override
    public void recordReceiveFailed() {
        // Do nothing
    }

    @Override
    public void recordBatchReceiveFailed() {
        // Do nothing
    }

    @Override
    public void close() {
        // Do nothing
    }

    @Override
    public ConsumerStats getStats() {
        return ConsumerStatsDisabled.INSTANCE;
    }
}
