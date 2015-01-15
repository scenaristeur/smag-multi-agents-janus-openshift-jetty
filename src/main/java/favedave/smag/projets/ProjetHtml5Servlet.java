package favedave.smag.projets;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Timer;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;
import org.eclipse.jetty.websocket.WebSocket.Connection;

public class ProjetHtml5Servlet extends WebSocketServlet{
String projet="vide";
	@Override
	public WebSocket doWebSocketConnect(HttpServletRequest request,
			String protocol) {
		// TODO Auto-generated method stub

		Enumeration<String> parameterNames = request.getParameterNames();

		        while (parameterNames.hasMoreElements()) {
		            String paramName = parameterNames.nextElement();
		            System.out.println(paramName);
		            System.out.println("n");
		            String[] paramValues = request.getParameterValues(paramName);
		            for (int i = 0; i < paramValues.length; i++) {
		                String paramValue = paramValues[i];
		                System.out.println("t" + paramValue);
		                System.out.println("n");
		            }
		        }

		projet = request.getParameter("projet");
		System.out.println(request.toString());
		return new ProjetSocket();
	}
	public class ProjetSocket implements WebSocket.OnTextMessage{
		private Connection connection;
		
		@Override
		public void onClose(int arg0, String arg1) {
			System.out.println("Web socket closed!");
		}

		@Override
		public void onOpen(Connection connection) {
			this.connection=connection;
			//this.timer=new Timer();
		}


		@Override
		public void onMessage(String data) {
			if(data.indexOf("disconnect")>=0){
				connection.close();
				//timer.cancel();
			}else{
				sendMessage();

			}			
		}
		private void sendMessage() {
			if(connection==null||!connection.isOpen()){
				System.out.println("Connection is closed!!");
				return;
			}
			try {
				connection.sendMessage("Recherche des elements du projet "+projet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
