package favedave.smag.projets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;

import org.apache.jena.atlas.json.JSON;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;
import org.eclipse.jetty.websocket.WebSocket.Connection;
import org.janusproject.kernel.Kernel;
import org.janusproject.kernel.agent.ChannelManager;
import org.janusproject.kernel.agent.Kernels;
import org.janusproject.kernel.channels.Channel;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFactory;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.query.ResultSetRewindable;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;

import favedave.smag.jena.sparql.AgentStateChannel;

public class ProjetHtml5Servlet extends WebSocketServlet{
String projet="vide";
private String message;
private AgentProjetChannel channel;
private String projetDetails;
private String projetMethode;
private String projetDoap;

	@Override
	public WebSocket doWebSocketConnect(HttpServletRequest request,
			String protocol) {
		// TODO Auto-generated method stub

		Kernel k = Kernels.get();
		ProjetAgent projetAgent = new ProjetAgent();
		k.launchLightAgent(projetAgent,projet);
	    ChannelManager channelManager = k.getChannelManager();
	    channel = projetAgent.getChannel( AgentProjetChannel.class);
		
		Enumeration<String> parameterNames = request.getParameterNames();

		        while (parameterNames.hasMoreElements()) {
		            String paramName = parameterNames.nextElement();
		            System.out.println(paramName);
		           // System.out.println("\n");
		            String[] paramValues = request.getParameterValues(paramName);
		            for (int i = 0; i < paramValues.length; i++) {
		                String paramValue = paramValues[i];
		                System.out.println("\t" + paramValue);
		             //   System.out.println("\n");
		            }
		        }

		projet = request.getParameter("projet");
		message="Recherche des elements du projet "+projet+"<h2>"+
				"A faire : construire la page du projet<br>"+
				"<a href=\"http://fuseki-smag0.rhcloud.com/ds/query?query="+
				"select+*+where+%7B%3Chttp%3A%2F%2Fsmag0.blogspot.fr%2Fns%2Fsmag0%23"+
		projet+
		"%3E%0D%0A+%3Fpropriete+%3Fvaleur%7D&output=xml&stylesheet=%2Fxml-to-html.xsl\">"+
		"Voir les propriétés du projet</a></h2>";
		System.out.println(request.toString());
		return new ProjetSocket();
	}
	public class ProjetSocket implements WebSocket.OnTextMessage{

			private Connection connection;
			private Timer timer; 
			
			@Override
			public void onOpen(Connection connection) {
				this.connection=connection;
				this.timer=new Timer();
			}

			@Override
			public void onClose(int arg0, String arg1) {
				timer.cancel();
				System.out.println("Web socket closed!");
			}

			@Override
			public void onMessage(String data) {
				if(data.indexOf("disconnect")>=0){
					connection.close();
					timer.cancel();
					data=null;
				}else{
					sendMessage();

				}			
			}
			

			private void sendMessage() {
				if(connection==null||!connection.isOpen()){
					System.out.println("Connection is closed!!");
					return;
				}
				timer.schedule(new TimerTask() {
						
						private String exFirstAtt;
						private String firstAtt;

						@Override
						public void run() {
							try{
							//	System.out.println("Running task");
							//	connection.sendMessage(getMyJsonTicker());
							    // Check if the agent accept to interact
							    if (channel!=null) {
							      // Display the agent's state
							      System.out.println("Etat "+
							           channel.getEtat());
							      //retour de l'attribut
							     exFirstAtt=firstAtt;
	firstAtt=" <div><bold>Retour : "+channel.getEtat()+"</bold>"+
			"<div>"+channel.getProjetDetail()+"</div>"+
			"<div>"+channel.getProjetDoap()+"</div>"+
			"<div>"+channel.getProjetMethode()+"</div>"
							   ;
							   // firstAtt=channel.getResultat();

							     if (exFirstAtt!=firstAtt){
							      connection.sendMessage(firstAtt);
							    //	 connection.sendMessage(reponseJson.toString());
							     }

							    }
							    else {
							      System.err.println("The agent does not accept to interact");
							      timer.cancel();
							    }
								
								
							}
							catch (IOException e) {
								e.printStackTrace();
							}
						}
					}, new Date(),5000);
			}
			
		}
	}
