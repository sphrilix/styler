/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.metron.common.bolt;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import org.apache.metron.common.configuration.ConfigurationType;
import org.apache.metron.common.configuration.ConfigurationsUtils;
import org.apache.metron.common.configuration.EnrichmentConfigurations;
import org.apache.metron.common.zookeeper.configurations.ConfigurationsUpdater;
import org.apache.metron.common.zookeeper.configurations.EnrichmentUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ConfiguredEnrichmentBolt extends ConfiguredBolt<EnrichmentConfigurations> {

  private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


  public ConfiguredEnrichmentBolt(String zookeeperUrl) {
    super(zookeeperUrl);
  }

  @Override
  protected ConfigurationsUpdater<EnrichmentConfigurations> createUpdater() {
    return new EnrichmentUpdater(this, this::getConfigurations);
  }
}
