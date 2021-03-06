package au.org.garvan.kccg.notifications.web;

import au.org.garvan.kccg.notifications.engine.AnalyticsManager;
import au.org.garvan.kccg.notifications.model.FeedbackRequest;
import au.org.garvan.kccg.notifications.model.QueryHitRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ahmed on 27/11/17.
 */
@RestController
public class QueryHitController {

    @Autowired
    AnalyticsManager engine;


    @ApiOperation(value = "sendQueryHit", nickname = "sendQueryHit")
    @RequestMapping(value = "/queryhits", method = RequestMethod.POST, produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = boolean.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public boolean sendFeedback(@ApiParam("queryhit") @RequestBody QueryHitRequest queryHitRequest) {

        engine.storeQueryHit(queryHitRequest);
        return true;
    }


}


