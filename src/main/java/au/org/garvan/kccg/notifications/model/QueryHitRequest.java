package au.org.garvan.kccg.notifications.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryHitRequest {

    @JsonProperty
    @ApiModelProperty
    List<String> searchItems;

    @JsonProperty
    @ApiModelProperty
    List<String> filterItems;

    @JsonProperty
    @ApiModelProperty
    private String queryKey;

    @JsonProperty
    @ApiModelProperty
    private boolean history;

    @JsonProperty
    @ApiModelProperty
    private String sendersAddress;

    @JsonProperty
    @ApiModelProperty
    private String timeStamp;


    @Override
    public String toString() {
        return String.format("From:%s | QueryKey:%s ",
                sendersAddress, queryKey);
    }
}
