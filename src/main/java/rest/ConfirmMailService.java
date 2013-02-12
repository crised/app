package rest;

import service.UserService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ResourceBundle;

/**
 * Date: 2/5/13
 * Time: 3:19 PM
 */

@Path("/{entry:.*}")
public class ConfirmMailService {

    @Context
    UriInfo uriInfo;

    @Inject
    ResourceBundle rB;

    @Inject
    UserService userService;


    @GET
    public Response getAllData() {

        Boolean confirmed =
                userService.activateUser(uriInfo.getAbsolutePath().toString());

        if (confirmed == true) {

            return Response.status(200)
                    .entity(rB.getString("confirmation.emailSuccess"))
                    .build();


        } else

            return Response.status(200)
                    .entity(rB.getString("confirmation.emailFailure"))
                    .build();


    }

}
