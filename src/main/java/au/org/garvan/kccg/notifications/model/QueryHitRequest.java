package au.org.garvan.kccg.notifications.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedbackRequest
{

    @ApiModelProperty
    private String pmid;

    @JsonProperty
    @ApiModelProperty
    private String feedback;

    @JsonProperty
    @ApiModelProperty
    private FeedbackAnnotationItem annotation;

    @JsonProperty
    @ApiModelProperty
    private String sendersAddress;


    @JsonProperty
    @ApiModelProperty
    private String timeStamp;


    @Override
    public String toString(){
        return String.format("From:%s | Article ID:%s | FeedBack:%s | AnnotationID:%s",
                sendersAddress, pmid,feedback,annotation.getId());
    }
}
