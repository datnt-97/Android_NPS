package base.core.api;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import base.MainActivity;
import base.core.lib.constant;
import base.core.mudules.database.AppDatabase;
import base.core.mudules.database.CookieDB;
import microsoft.aspnet.signalr.client.Platform;
import microsoft.aspnet.signalr.client.SignalRFuture;
import microsoft.aspnet.signalr.client.http.BasicAuthenticationCredentials;
import microsoft.aspnet.signalr.client.http.android.AndroidPlatformComponent;
import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.HubProxy;
import microsoft.aspnet.signalr.client.hubs.SubscriptionHandler2;

public class SocketController {

    private static SocketController socketInstance;
    private static HubConnection hubConnection;
    private static String hubName;
    private static String urlSocket = "";
    private HubProxy hub;

    private SocketController() {
        urlSocket = MainActivity.appServerModel.getUrl() + constant.URL_SOCKET;
    }

    public static SocketController getInstance() {
        if (socketInstance == null) {
            Platform.loadPlatformComponent(new AndroidPlatformComponent());
            socketInstance = new SocketController();
            hubConnection = new HubConnection(urlSocket) {
                @Override
                public Map<String, String> getHeaders() {
//                    Set-Cookie: <cookie-name>=<cookie-value>;
                    CookieDB cookieDB = MainActivity.appDatabase.cookieDAO().findById(constant.DB_FIRST_ID);
                    HashMap<String, String> headers = new HashMap<String, String>();
                    if (cookieDB != null) {
                        headers.put(constant.COOKIE_KEY_SET, constant.COOKIE_KEY + "=" + cookieDB.getToken());
                    }
                    return headers;
                }

            };
        }
        return socketInstance;
    }

    public void disconnect() {
        hubConnection.disconnect();
    }

    public void connectHubByName(String hubName) throws ExecutionException, InterruptedException {
        hub = hubConnection.createHubProxy(hubName);
        SignalRFuture<Void> awaitConnection = hubConnection.start();
        awaitConnection.get();
    }

    public HubProxy connectHubChat() throws ExecutionException, InterruptedException {
        if (hub == null) {
            hub = hubConnection.createHubProxy(constant.SOCKET_HUB_CHAT);
            SignalRFuture<Void> awaitConnection = hubConnection.start();
            awaitConnection.get();
        }

        return hub;
    }

    public String getHubName() {
        return hubName;
    }

    public void setHubName(String hubName) {
        this.hubName = hubName;
    }

    public static HubConnection getHubConnection() {
        return hubConnection;
    }

    public static void setHubConnection(HubConnection hubConnection) {
        SocketController.hubConnection = hubConnection;
    }

    public static String getUrlSocket() {
        return urlSocket;
    }

    public static void setUrlSocket(String urlSocket) {
        SocketController.urlSocket = urlSocket;
    }

    public HubProxy getHub() {
        return hub;
    }

}
