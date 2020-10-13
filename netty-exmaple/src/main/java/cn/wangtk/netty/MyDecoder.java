package cn.wangtk.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.ByteBuffer;
import java.util.List;

public class MyDecoder extends ReplayingDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte[] byteString = new byte[in.readableBytes()];
        System.out.println(new String(byteString, "utf-8"));

        ByteBuffer buffer1 = ByteBuffer.allocate(1024);
        ByteBuffer buffer2 = ByteBuffer.allocateDirect(1024);
        ByteBuf buffer3 = Unpooled.buffer(1024);
        ByteBuf buffer4 = Unpooled.directBuffer(2048);


    }
}
