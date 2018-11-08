package com.manager.task.configure;

import com.google.common.collect.Lists;
import com.manager.task.common.constants.GlobalData;
import com.manager.task.task.DynamicTask;
import com.manager.task.task.common.ReconnectJob;
import com.manager.task.ws.HuobiWebSocket;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author chao
 * @since 2018-11-08
 */
@Component
public class StartRunner implements CommandLineRunner {

    @Autowired
    private DynamicTask dynamicTask;

    @Override
    public void run(String... strings) throws Exception {
        initWebSocket();
    }

    private void initWebSocket() throws Exception{
//        HuobiWebSocket huobiWebSocket = new HuobiWebSocket(new URI("wss://api.huobi.pro/ws"),
//                1, Lists.newArrayList("ethbtc"));
        HuobiWebSocket huobiWebSocket = new HuobiWebSocket(new URI("wss://api.huobi.pro/ws"),1, null);
        huobiWebSocket.setConnectionLostTimeout(30);
        huobiWebSocket.connect();

        GlobalData.webSocketClientMap.put("huobi-ws-detail", huobiWebSocket);
        dynamicTask.createOrRescheduleWithData("huobi-ws",
                "detail", String.format("/%s * * * * ? ", 5),
                ReconnectJob.class, huobiWebSocket);
    }
}
