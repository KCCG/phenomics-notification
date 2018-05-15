package au.org.garvan.kccg.notifications.engine;
import au.org.garvan.kccg.notifications.model.FeedbackAnnotationItem;
import au.org.garvan.kccg.notifications.model.FeedbackRequest;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class AnalyticsManagerTest {

    AnalyticsManager analyticsManager = new AnalyticsManager("jdbc:mysql://localhost:3306","AnalyticsProd","root", "", "AnalyticsFeedback","AnalyticsQueryHit");

    @Test
    public void storeFeedback() {

        FeedbackRequest feedbackRequest = new FeedbackRequest(
                "29000000",
                "good",
                new FeedbackAnnotationItem("HP:10023","textPhenotype","PHENOTYPE",5,15),
                "0.0.0.0",
                LocalDateTime.now().toString()
        );

        analyticsManager.storeFeedback(feedbackRequest);
    }
}