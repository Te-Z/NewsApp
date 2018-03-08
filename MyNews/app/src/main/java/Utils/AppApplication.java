package Utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by maverick on 08/03/18.
 */

public class AppApplication extends Application{

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //MultiDex.install(this);
    }
}
