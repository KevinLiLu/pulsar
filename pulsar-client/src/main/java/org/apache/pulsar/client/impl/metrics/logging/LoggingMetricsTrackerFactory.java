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

import java.util.Map;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.metrics.ConsumerMetricsTracker;
import org.apache.pulsar.client.api.metrics.MetricsTrackerFactory;
import org.apache.pulsar.client.api.metrics.ProducerMetricsTracker;

public class LoggingMetricsTrackerFactory implements MetricsTrackerFactory {
    @Override
    public void configure(Map<String, String> params) {
        System.out.println("Configuring LoggingMetricsTrackerFactory with configs: " + params);
    }

    @Override
    public ProducerMetricsTracker create(PulsarClient client, Producer<?> producer) {
        return new LoggingProducerMetricsTracker(client, producer);
    }

    @Override
    public ConsumerMetricsTracker create(PulsarClient client, Consumer<?> consumer) {
        return new LoggingConsumerMetricsTracker(client, consumer);
    }

}
