package org.apache.pulsar.client.impl.metrics;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.metrics.ConsumerMetricsTracker;
import org.apache.pulsar.client.api.metrics.MetricsTrackerFactory;
import org.apache.pulsar.client.api.metrics.ProducerMetricsTracker;
import org.apache.pulsar.client.impl.NoOpConsumerMetricsTracker;
import org.apache.pulsar.client.impl.NoOpProducerMetricsTracker;

import java.util.Map;

public class NoOpMetricsTrackerFactory implements MetricsTrackerFactory {
    @Override
    public void configure(Map<String, String> params) {
        // Do nothing
    }
    
    @Override
    public ProducerMetricsTracker create(PulsarClient client, Producer<?> producer) {
        return new NoOpProducerMetricsTracker();
    }

    @Override
    public ConsumerMetricsTracker create(PulsarClient client, Consumer<?> consumer) {
        return new NoOpConsumerMetricsTracker();
    }
}
