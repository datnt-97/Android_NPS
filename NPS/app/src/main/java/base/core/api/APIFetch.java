package base.core.api;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.net.CookieStore;

public class APIFetch extends MultiDexApplication {
    public static final String TAG = APIFetch.class.getSimpleName();
    private static APIFetch mInstance;
    private Context mContext;
    private RequestQueue mRequestQueue;

    private APIFetch(Context mContext) {
        this.mContext = mContext;
    }

    public static synchronized APIFetch getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new APIFetch(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {

        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}

