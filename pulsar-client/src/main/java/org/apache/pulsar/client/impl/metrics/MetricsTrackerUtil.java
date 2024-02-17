package org.apache.pulsar.client.impl.metrics;

import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.metrics.MetricsTrackerFactory;

import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class MetricsTrackerUtil {

    /**
     * Create an instance of the MetricsTrackerFactory.
     *
     * @param metricsTrackerFactoryClassName
     *            name of the MetricsTrackerFactory you want to use
     * @param paramMap
     *            map which represents parameters for the MetricsTrackerFactory to use
     * @return instance of the MetricsTrackerFactory
     * @throws PulsarClientException.InvalidConfigurationException
     */
    public static final MetricsTrackerFactory create(String metricsTrackerFactoryClassName,
                                                     Map<String, String> paramMap)
            throws PulsarClientException.InvalidConfigurationException {
        try {
            if (isNotBlank(metricsTrackerFactoryClassName)) {
                Class<?> metricsTrackerFactoryClass = Class.forName(metricsTrackerFactoryClassName);
                MetricsTrackerFactory metricsTrackerFactory = (MetricsTrackerFactory) metricsTrackerFactoryClass
                        .getDeclaredConstructor().newInstance();
                metricsTrackerFactory.configure(paramMap);
                return metricsTrackerFactory;
            } else {
                return new NoOpMetricsTrackerFactory();
            }
        } catch (Throwable t) {
            throw new PulsarClientException.InvalidConfigurationException(t);
        }
    }
}
