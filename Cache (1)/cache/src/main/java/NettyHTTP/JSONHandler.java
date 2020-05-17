package NettyHTTP;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

import java.util.concurrent.Future;

import io.netty.handler.codec.http.*;
import static io.netty.handler.codec.http.HttpHeaders.Names.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONArray;
import org.json.JSONObject;


public class JSONHandler extends SimpleChannelInboundHandler<Object> {

	 private HttpRequest request;
	 private String requestBody;

	 private long correlationId;
	 
	 volatile String responseBody;
	 ExecutorService executorService = Executors.newCachedThreadPool();
	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
		System.out.println("JSON HANDLER");
		//
	//	HttpContent httpContent = (HttpContent) o;
		//
		
		
		
		ByteBuf buffer = (ByteBuf) o;
		JSONObject jsonObject = new JSONObject(buffer.toString(CharsetUtil.UTF_8));
		JSONArray ja = new JSONArray();
		jsonObject.put("code","200");
		HttpResponseStatus status = null;
		 if (!jsonObject.has("User")){
	            status = new HttpResponseStatus(Integer.parseInt((String) jsonObject
	                    .get("code")),
	                    Integer.parseInt((String) jsonObject.get("code")) == 200 ? "Ok"
	                            : "Bad Request");
		 } else{
	            status = new HttpResponseStatus(Integer.parseInt((String) jsonObject
	                    .get("code")), (String) jsonObject.get("User"));
	            }
		 
		// boolean keepAlive = HttpHeaders.isKeepAlive(request);


		//.addListener(ChannelFutureListener.CLOSE);
		
	
		//channelHandlerContext.close();
		// System.out.println(response.toString());

//		 String user = jsonObject.getString("User");
//		 String text = jsonObject.getString("Comment");
//		 String postId = jsonObject.getString("PostId");

		 String type = jsonObject.getString("Type");
		 if(type.equals("insert")){
		 String user = jsonObject.getString("User");
		 String text = jsonObject.getString("Comment");
		 String postId = jsonObject.getString("PostId");
		 comments.Client client = new comments.Client(user, text, postId);
		 client.runInsert();
		 }else if(type.equals("like")){
		 String user = jsonObject.getString("User");
		 String postId = jsonObject.getString("PostId");
		 comments.Client client = new comments.Client(user,postId);
		 client.runLike();
		 }else if(type.equals("unlike")){
		 String user = jsonObject.getString("User");
		 String postId = jsonObject.getString("PostId");
		 comments.Client client = new comments.Client(user, postId);
		 client.runUnlike();
		 }else if(type.equals("getCom")){
		 String postId = jsonObject.getString("PostId");
		 comments.Client client = new comments.Client(postId);
		 client.runGetCom();
		 ja = client.runGetComJ();
		 }
		 
		 FullHttpResponse response;
		 
		 if(ja.length() == 0){
			  response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
		                status, Unpooled.copiedBuffer(jsonObject.toString(), CharsetUtil.UTF_8));
		 }else{
		 
			 response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
	                status, Unpooled.copiedBuffer(ja.toString(), CharsetUtil.UTF_8));
		 }
		 
		System.out.println(jsonObject.toString());
		//FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, 
//				HttpResponseStatus.OK,
//				Unpooled.copiedBuffer(jsonObject.toString(), CharsetUtil.UTF_8));
		response.headers().set("Content-type", "application/json; charset=UTF-8");
		
		response.headers().set(CONTENT_LENGTH,
             response.content().readableBytes());
     response.headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);

		System.out.println(response);
		
		channelHandlerContext.writeAndFlush(response);
		 
		 }

		// System.out.println(user);
		// System.out.println(text);
		// System.out.println(postId);

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {

		// System.out.println("in");
		//ctx.flush();
		ctx.close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

}
