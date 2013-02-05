package rest;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Date: 2/5/13
 * Time: 3:19 PM
 */

@Path("/{entry:.*}")
public class ConfirmMailService {

    @Context
    UriInfo uriInfo;


    @GET
    public Response getAllData() {

        return Response.status(200)
                .entity(uriInfo.getAbsolutePath().toString())
                .build();

    }

}
