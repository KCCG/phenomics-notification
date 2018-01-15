package au.org.garvan.kccg.notifications.web;

import au.org.garvan.kccg.notifications.dto.EmailNotificationRequestDto;
import au.org.garvan.kccg.notifications.engine.EmailNotificationAdapterImpl;
import au.org.garvan.kccg.notifications.model.EmailAttachmentInfo;
import au.org.garvan.kccg.notifications.model.NotificationRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 27/11/17.
 */
@RestController
public class EmailController {

    @Autowired
    EmailNotificationAdapterImpl engine;


    @ApiOperation(value = "sendEmail", nickname = "sendEmail")
    @RequestMapping(value = "/email", method = RequestMethod.POST, produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = boolean.class, responseContainer = "list"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public boolean sendEmail(@ApiParam("request") @RequestBody EmailNotificationRequestDto emailNotificationRequestDto) {
        List<EmailAttachmentInfo> listAttatchment = new ArrayList<>();
        NotificationRequest notificationRequest = new NotificationRequest(emailNotificationRequestDto.getMessage(),
                emailNotificationRequestDto.getSubject(),
                emailNotificationRequestDto.getToRecipients(),
                emailNotificationRequestDto.getCcRecipients(),
                emailNotificationRequestDto.getBccRecipients(),
                emailNotificationRequestDto.getUniqueID(),
                listAttatchment,
                emailNotificationRequestDto.getSender(),
                "0");

        engine.sendMessage(notificationRequest);
        return true;
    }


}


