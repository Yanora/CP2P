package webSocket;


import java.util.Map;

import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;
	
@ServerEndpoint("/actions")
public class WebSocketServer {
	
	@Inject
	private SessionHandler sessionHandler = new SessionHandler();

	@OnOpen
		public void open(Session session) {
		sessionHandler.addSession(session);
	}

	@OnClose
		public void close(Session session) {
		sessionHandler.removeSession(session);
	}

	@OnError
		public void onError(Throwable error, Session session) {
		sessionHandler.removeSession(session);
		//Logger.getLogger(WebSocketServer.class.getName()).log(Level.SEVERE, "test", error);
	}

	@OnMessage
		public void handleMessage(String message, Session session) throws JSONException {
		
		JSONObject jsonMessage = new JSONObject(message);

	}

	public void sendMessage(Map<String, Integer> values, String room) {
		//System.out.println("send message "+room);
		sessionHandler.sendMessage(new JSONObject(values));
	}
}   