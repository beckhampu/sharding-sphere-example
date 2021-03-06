/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.example.jdbc.main.orche.yaml.zookeeper;

import io.shardingsphere.example.repository.jdbc.repository.RawOrderItemRepository;
import io.shardingsphere.example.repository.jdbc.repository.RawOrderRepository;
import io.shardingsphere.example.repository.jdbc.service.RawDemoService;
import io.shardingsphere.shardingjdbc.orchestration.api.yaml.YamlOrchestrationShardingDataSourceFactory;
import io.shardingsphere.shardingjdbc.orchestration.internal.datasource.OrchestrationShardingDataSource;

import javax.sql.DataSource;
import java.io.File;

public class ShardingOnlyWithDatabases {
    
    private static final boolean LOAD_CONFIG_FROM_REG_CENTER = false;
    
    public static void main(final String[] args) throws Exception {
        DataSource dataSource = YamlOrchestrationShardingDataSourceFactory.createDataSource(getYamlFile());
        new RawDemoService(new RawOrderRepository(dataSource), new RawOrderItemRepository(dataSource)).demo();
        ((OrchestrationShardingDataSource) dataSource).close();
    }
    
    private static File getYamlFile() {
        String path = LOAD_CONFIG_FROM_REG_CENTER ? "/META-INF/orche/zookeeper/cloud/sharding-databases.yaml" : "/META-INF/orche/zookeeper/local/sharding-databases.yaml";
        return new File(ShardingOnlyWithDatabases.class.getResource(path).getFile());
    }
}
