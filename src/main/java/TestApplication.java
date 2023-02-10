import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import rest.TicketResource;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class TestApplication extends Application {


    @Override
    public Set<Class<?>> getClasses() {

        final Set<Class<?>> clazzes = new HashSet<>();

        clazzes.add(TicketResource.class);
        clazzes.add(OpenApiResource.class);


        return clazzes;
    }

}