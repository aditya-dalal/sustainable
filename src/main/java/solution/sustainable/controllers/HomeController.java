package solution.sustainable.controllers;

import com.google.inject.Inject;
import solution.sustainable.dao.HomeRepository;
import solution.sustainable.models.Home;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by aditya.dalal on 25/04/18.
 */

@Path("/v1/homes")
public class HomeController {

    private HomeRepository homeRepository;

    @Inject
    public HomeController(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Home register(Home home) {
        return homeRepository.insert(home);
    }

    @Path("/{homeId}/devices/{deviceId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Home registerDevice(@PathParam("homeId") String homeId, @PathParam("deviceId") String deviceId) {
        return homeRepository.addDevice(homeId, deviceId);
    }
}
