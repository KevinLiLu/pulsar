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
package org.apache.pulsar.client.impl.metrics.logging;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.metrics.ConsumerMetricsTracker;
import org.apache.pulsar.client.impl.ConsumerImpl;
import org.apache.pulsar.client.impl.ConsumerStatsDisabled;
import org.apache.pulsar.client.impl.ConsumerStatsRecorder;
import org.apache.pulsar.client.impl.ConsumerStatsRecorderImpl;
import org.apache.pulsar.client.impl.MultiTopicConsumerStatsRecorderImpl;
import org.apache.pulsar.client.impl.MultiTopicsConsumerImpl;
import org.apache.pulsar.client.impl.PulsarClientImpl;

public class LoggingConsumerMetricsTracker implements ConsumerMetricsTracker {
    private final ConsumerStatsRecorder statsRecorder;

    public LoggingConsumerMetricsTracker(PulsarClient pulsarClient, Consumer<?> consumer) {
        if (consumer instanceof MultiTopicsConsumerImpl) {
            this.statsRecorder = new MultiTopicConsumerStatsRecorderImpl(consumer);
        } else if (consumer instanceof ConsumerImpl){
            this.statsRecorder = new ConsumerStatsRecorderImpl((PulsarClientImpl) pulsarClient, null, consumer);
        } else {
            this.statsRecorder = new ConsumerStatsDisabled();
        }
    }

    @Override
    public void recordMessageReceived(Message<?> message) {
        statsRecorder.updateNumMsgsReceived(message);
    }

    @Override
    public void recordAcksSent(long numAcks) {
        statsRecorder.incrementNumAcksSent(numAcks);
    }

    @Override
    public void recordAckFailed() {
        statsRecorder.incrementNumAcksFailed();
    }

    @Override
    public void recordReceiveFailed() {
        statsRecorder.incrementNumReceiveFailed();
    }

    @Override
    public void recordBatchReceiveFailed() {
        statsRecorder.incrementNumBatchReceiveFailed();
    }

    @Override
    public void close() {
        statsRecorder.reset();
    }

    @Override
    public ConsumerStatsRecorder getStats() {
        return statsRecorder;
    }
}
