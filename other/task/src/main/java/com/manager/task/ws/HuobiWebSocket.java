package com.manager.task.ws;

import com.alibaba.fastjson.JSONObject;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.util.StringUtils;

/**
 * huobi webSocket
 *
 * @author chao
 */
@Slf4j
public class HuobiWebSocket extends WebSocketClient {

    private List<String> symbols;

    /**
     * 1-symbol.detail
     */
    private int type;

    public HuobiWebSocket(URI uri, int type, List<String> symbols) {
        super(uri);
        if (symbols == null) {
            this.symbols = new ArrayList<>();
        } else {
            this.symbols = symbols;
        }
        this.type = type;
    }

    @Override
    public void onOpen(ServerHandshake handshakeData) {
        log.info("huobi wss connected");
        for (String symbol : symbols) {
            JSONObject req = new JSONObject();
            if (type == 1) {
                req.put("sub", String.format("market.%s.detail", symbol));
            }
            req.put("id", "12312312");
            send(req.toString());
        }
    }

    public void sub(String symbol) {
        if (!symbols.contains(symbol)) {
            symbols.add(symbol);
            JSONObject req = new JSONObject();
            if (type == 1) {
                req.put("sub", String.format("market.%s.detail", symbol));
            }
            req.put("id", 12312312);
            this.send(req.toJSONString());
        }
    }

    public void unsub(String symbol) {
        if (symbols.remove(symbol)) {
            JSONObject req = new JSONObject();
            if (type == 1) {
                req.put("unsub", String.format("market.%s.detail", symbol));
            }
            req.put("id", 12312312);
            this.send(req.toJSONString());
        }
    }

    @Override
    public void onMessage(String message) {
        log.info("onMessage(String message):{}", message);
    }

    @Override
    public void onMessage(ByteBuffer bytes) {
        try {
            String message = new String(gzipDecompress(bytes.array()), "UTF-8");
            if (!StringUtils.isEmpty(message)) {
                JSONObject jsonObject = JSONObject.parseObject(message);
                String op = jsonObject.getString("op");
                if (message.indexOf("ping") > 0) {
                    send(message.replace("ping", "pong"));
                } else if (!"auth".equals(op)) {
                    if (type == 1) {
                        // 处理数据
                        log.info(message);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("connection close:{},reason:{},remote:{}", code, reason, remote);
    }

    @Override
    public void onError(Exception e) {
        String message = "";
        try {
            message = new String(e.getMessage().getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        log.info("has error ,the message is :" + message);
    }

    /**
     * gzip解压客户端发来的数据
     */
    private byte[] gzipDecompress(byte[] depressData) throws Exception {
        try (ByteArrayInputStream is = new ByteArrayInputStream(depressData);
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                GZIPInputStream gis = new GZIPInputStream(is)) {

            byte data[] = new byte[1024];
            int offset;
            while (-1 != (offset = gis.read(data, 0, 1024))) {
                os.write(data, 0, offset);
            }
            return os.toByteArray();
        }
    }
}
