package NettyHTTP;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static io.netty.handler.codec.http.HttpResponseStatus.CONTINUE;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HTTPHandler extends SimpleChannelInboundHandler<Object> {
    private HttpRequest request;
    private String requestBody;
    private long correlationId;
    volatile String responseBody;
    ExecutorService executorService = Executors.newCachedThreadPool();


    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
        ctx.fireChannelReadComplete();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest request = this.request = (HttpRequest) msg;
            if (HttpHeaders.is100ContinueExpected(request)) {
                send100Continue(ctx);
            }

        }
        if (msg instanceof HttpContent) {
            HttpContent httpContent = (HttpContent) msg;
            ByteBuf content = httpContent.content();
//            setRequestBody(content.toString(CharsetUtil.UTF_8));
            ctx.fireChannelRead(content.copy());
        }
        if (msg instanceof LastHttpContent) {
//            LastHttpContent trailer = (LastHttpContent) msg;
            HttpObject trailer = (HttpObject) msg;
//            writeresponse(trailer, ctx);
        }
    }


    private static void send100Continue(ChannelHandlerContext ctx) {
    	System.out.println("lala");
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
                CONTINUE);
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}

//import io.netty.channel.ChannelHandler.Sharable;
//  import io.netty.channel.ChannelHandlerContext;
//  import io.netty.channel.ChannelInboundHandlerAdapter;
//  
//  /**
//23   * Handler implementation for the echo server.
//24   */
//  @Sharable
//  public class HTTPHandler extends ChannelInboundHandlerAdapter {
//  
//      @Override
//      public void channelRead(ChannelHandlerContext ctx, Object msg) {
//          ctx.write(msg);
//      }
//  
//      @Override
//      public void channelReadComplete(ChannelHandlerContext ctx) {
//          ctx.flush();
//      }
//  
//      @Override
//      public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//          // Close the connection when an exception is raised.
//          cause.printStackTrace();
//          ctx.close();
//      }
//  }

