package com.manager.task.common.constants;

import java.util.HashMap;
import java.util.Map;
import org.java_websocket.client.WebSocketClient;

/**
 * global data
 *
 * @author chao
 */
public class GlobalData {

    /**
     * webSocket对象
     */
    public static Map<String, WebSocketClient> webSocketClientMap = new HashMap<>();
}
