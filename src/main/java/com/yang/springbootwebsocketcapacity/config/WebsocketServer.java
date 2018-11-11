package com.yang.springbootwebsocketcapacity.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Yang
 * @date: 2018/9/20 22:59
 * @description:
 */
@Slf4j
@Component
@ServerEndpoint("/websocket/{id}")
public class WebsocketServer {

    private Integer id;

    private Session session;

    private static final Map<Integer, Session> SESSION_BOX = new ConcurrentHashMap<>(1000);

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("id") Integer id) {
        this.id = id;
        this.session = session;
        this.addSession(this.id, this.session);
        log.info("有新窗口开始监听:" + id + ",当前在线人数为");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        this.removeSession(this.id);
        log.info("有一连接关闭！当前在线人数为");
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {

    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    public void addSession(Integer id, Session session) {
        SESSION_BOX.put(id, session);
    }

    public void removeSession(Integer id) {
        SESSION_BOX.remove(id);

    }

    public void send() {
        System.out.println("++++++++++++" + SESSION_BOX.size());
        SESSION_BOX.values().forEach(it -> {
            try {
                it.getBasicRemote().sendText("===========");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
