package com.chat.shardingjdbc.algorithm;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;
import java.util.Iterator;

/**
 * @Author：silence
 * @Description:
 * @Date:Create in 2021/3/30 14:51
 */
public class TestDatabaseAlgorithm implements PreciseShardingAlgorithm<Long> {


    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        System.out.println("TestDatabaseAlgorithm");
        String target = null;
        Iterator<String> iterator = collection.iterator();
        for (int i = 0; i < collection.size(); i++) {
            target = iterator.next();
            if (preciseShardingValue.getValue() % 2 == 0) {
                break;
            }
        }
        return target;
    }
}
