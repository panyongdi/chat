package com.chat.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chat.mongo.entiy.Message;
import com.chat.protocol.ProtocolSend;
import com.chat.utils.BeanUtils;
import com.chat.utils.JwtUtil;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;

/**
 * @Author：silence
 * @Description:
 * @Date:Create in 2021/3/4 9:22
 */
@Slf4j
public class ChatTest {

    public static void main(String[] args) throws Exception {
//        String message = "{\"token\":\"eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2MTUzNjQxOTcsInN1YiI6IjIiLCJleHAiOjE2MTYyODU3OTd9._ltUgtNw8FNNLrOmTbEBjP1HNe2cSt-pU8AAH5QNqPJsyV1ZMzYbLHgXacgPskYtscaUkxl7EbG09gJ_-FTUfg\",\"friendId\":\"1\",\"message\":\"23\"," +
//                "\"linkId\":\"222\",\"protocol\":2,\"type\":1,\"userId\":\"2\"}";
//        ProtocolSend protocolSend = JSON.parseObject(message, ProtocolSend.class);
//        Message message1 = new Message();
//        BeanUtils.copyValue(message1, protocolSend);


        getWechatData("is4XlpeuohUvVNfxmVYSDQ==",
                "BjwNknX4QKL%2FO92KOMD%2F0aOMEw975L65SC9YesOtLSndS4UjE6%2BsbzSdPlf%2FdWhjyl4GFX4Qq5Su%2Bhus15izRZ0cSH%2B%2BmaCiwSma%2FVj2plTM6CQKo%2FSdYgZhTnlQ6xYdXxZ4YWJK%2FtS%2BpWu0mVVkrIC9N6Ivpr5ZJ0gOHJtmT%2Bp4Mjs7phMUFV3leoduyTgs7qtrqw5KIJeLsVrG2GeiLQ%3D%3D", "TrsEi/Ds0Iy78+PN096r0g==");


//        NioEventLoopGroup bossGroup=new NioEventLoopGroup(2);
//        NioEventLoopGroup workerGroup=new NioEventLoopGroup(5);
//
//        ServerBootstrap bootstrap=new ServerBootstrap();
//        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
//        bootstrap.option(ChannelOption.TCP_NODELAY, true);
//        bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
//
//        bootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class);
//        bootstrap.childHandler(new ChannelInitializer(){
//
//            protected void initChannel(Channel channel) throws Exception {
//                ChannelPipeline pipeline=channel.pipeline();
//
//                //解码成HttpRequest
//                pipeline.addLast(new HttpClientCodec());
//                //解决发送大内存东西时内存溢出
//                pipeline.addLast(new ChunkedWriteHandler());
//                //解码成FullHttpRequest
//                pipeline.addLast(new HttpObjectAggregator(1024*10));
//                //添加webSocket解码
//                pipeline.addLast(new WebSocketServerProtocolHandler("/chat"));
//
//                //添加自己的解码器
//                pipeline.addLast(new ChatServerHandler());
//            }
//        });
//        //绑定端口
//        ChannelFuture channelFuture=bootstrap.bind(9096).sync();
//
//        //端口端口
//        channelFuture.channel().closeFuture().sync();
    }

    //加密微信用户信息
    public static JSONObject getWechatData(String session_key, String encrypData, String iv) {

        JSONObject data = new JSONObject();
        System.out.println(("session_key==>" + session_key));
        // 被加密的数据
        byte[] dataByte = com.sun.org.apache.xerces.internal.impl.dv.util.Base64.decode(encrypData);
        // 加密秘钥
        byte[] keyByte = com.sun.org.apache.xerces.internal.impl.dv.util.Base64.decode(session_key);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AESUtil/CBC/PKCS5Padding");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AESUtil");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AESUtil");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                data = JSONObject.parseObject(result);
            }
            return data;
        } catch (Exception e) {
            System.out.println("加密错误码======>>>>> {}");
            e.printStackTrace();
        }
        return null;
    }

}
