// Author Michael Meier micmine4@gmail.com
package net.wfs.web.engine;

import net.wfs.web.engine.db.DatabaseConnectorFactory;
import net.wfs.web.engine.WebComponent.IWebComponentEngine;
import net.wfs.web.engine.WebComponent.WebComponentImpl;
import net.wfs.web.engine.Wfs.IWfsEngine;
import net.wfs.web.engine.Wfs.WfsImpl;

/**
 * Created by michael on 5/13/17.
 */
public final class EngineFactory {
    private static EngineFactory instance = new EngineFactory();

    private IWfsEngine wfsEngineEngine;
    private IWebComponentEngine webComponentEngine;
    private DatabaseConnectorFactory bdEngine;



    private EngineFactory() {
        wfsEngineEngine = new WfsImpl();
        webComponentEngine = new WebComponentImpl();
    }

    public static EngineFactory getInstance() {
        return instance;
    }

    public IWfsEngine getWfsEngine() {
        return wfsEngineEngine;
    }

    public IWebComponentEngine getWebComponentEngine() {
        return webComponentEngine;
    }

    @Deprecated
    public DatabaseConnectorFactory getDb() {
        return bdEngine;
    }



}
