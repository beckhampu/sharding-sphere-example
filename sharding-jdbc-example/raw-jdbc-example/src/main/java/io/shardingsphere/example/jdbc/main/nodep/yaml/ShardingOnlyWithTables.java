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

package io.shardingsphere.example.jdbc.main.nodep.yaml;

import io.shardingsphere.example.repository.jdbc.repository.RawOrderItemRepository;
import io.shardingsphere.example.repository.jdbc.repository.RawOrderRepository;
import io.shardingsphere.example.repository.jdbc.service.RawDemoService;
import io.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;

public class ShardingOnlyWithTables {
    
    public static void main(final String[] args) throws Exception {
        DataSource dataSource = YamlShardingDataSourceFactory.createDataSource(getYamlFile());
        new RawDemoService(new RawOrderRepository(dataSource), new RawOrderItemRepository(dataSource)).demo();
    }
    
    private static File getYamlFile() {
        return new File(ShardingOnlyWithTables.class.getResource("/META-INF/nodep/sharding-tables.yaml").getFile());
    }
}
