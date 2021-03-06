package eu.optique.api.mapping.impl.sesame;

import eu.optique.api.mapping.LibConfiguration;
import eu.optique.api.mapping.R2RMLMappingManager;
import eu.optique.api.mapping.R2RMLMappingManagerFactory;
import eu.optique.api.mapping.impl.R2RMLMappingManagerImpl;

public class SesameR2RMLMappingManagerFactory implements R2RMLMappingManagerFactory {

    /**
     * @return A R2RMLMappingManager configured with Sesame.
     */
    @Override
    public R2RMLMappingManager getR2RMLMappingManager() {
        LibConfiguration jc = new SesameConfiguration();
        R2RMLMappingManager mm = new R2RMLMappingManagerImpl(jc);
        return mm;
    }

}
