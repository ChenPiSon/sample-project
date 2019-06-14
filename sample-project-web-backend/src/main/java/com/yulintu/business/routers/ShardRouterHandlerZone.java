package com.yulintu.business.routers;

import com.yulintu.thematic.data.sharding.ShardElement;
import com.yulintu.thematic.data.sharding.ShardMethodInvokeMetadata;
import com.yulintu.thematic.data.sharding.ShardRouter;
import com.yulintu.thematic.data.sharding.ShardRouterHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;
import java.util.Properties;

/**
 * 查询zone分库
 */
@Component
@ShardRouter
public class ShardRouterHandlerZone implements ShardRouterHandler {

    @Override
    public boolean inspect(ShardMethodInvokeMetadata metadata, ShardElement shard) {

        String type = metadata.getShardType();
        Properties properties = shard.getProperties();

        if (!"zone".equals(type))
            return false;
        if (!properties.containsKey("zone"))
            return false;

        String zone = (String) properties.get("zone");
        if (StringUtils.isBlank(zone))
            return false;

        HashMap<String, Object> args = metadata.getArgs();
        Optional<Object> first = args.values().stream().findFirst();
        if (!first.isPresent())
            return false;

        String code = (String) first.get();
        if (StringUtils.isBlank(code))
            return false;

        if ("86".equals(code))
            return true;

        //return zone.startsWith(code) || code.startsWith(zone);
        return  code.startsWith(zone);
    }
}
