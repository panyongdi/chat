package com.chat.shardingjdbc.algorithm;

import com.chat.shardingjdbc.utils.DateUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * @Author：silence
 * @Description:
 * @Date:Create in 2021/3/30 14:51
 */
public class TestTableAlgorithm implements PreciseShardingAlgorithm<Date> {


    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Date> preciseShardingValue) {
        System.out.println("TestTableAlgorithm");
        String target = null;
        Iterator<String> iterator = collection.iterator();
        for (int i = 0; i < collection.size(); i++) {
            target = iterator.next();
            //分片的大于某个某个时间
            Date date = preciseShardingValue.getValue();
            String table = preciseShardingValue.getLogicTableName();
            String dateStr = DateUtils.parseDateToStr("yyyyMM", date);
            String tableReal = table + "_" + dateStr;
            if (target.equals(tableReal)) {
                break;
            }

        }
        return target;
    }
}
