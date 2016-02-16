package info.civiliancraft.ChromaSound.WebServer;

import info.civiliancraft.ChromaSound.Main;

import java.io.File;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
 
public class WebServer {
    public static void runServer() {
        Server server = new Server(8000);
 
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[] { "index.html" });
 
        new File(Main.getPlugin(Main.class).getDataFolder(), "httdocs").mkdirs();
        resource_handler.setResourceBase(new File(Main.getPlugin(Main.class).getDataFolder(), "httdocs").getAbsolutePath());
 
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { resource_handler, new DefaultHandler() });
        server.setHandler(handlers);
 
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
}
