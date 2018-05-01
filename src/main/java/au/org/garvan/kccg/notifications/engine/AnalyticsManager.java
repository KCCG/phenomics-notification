package au.org.garvan.kccg.notifications.engine;

import au.org.garvan.kccg.notifications.model.FeedbackRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class AnalyticsManager {
    private static Logger log = LoggerFactory.getLogger(AnalyticsManager.class);

    private String settings = "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String connectionString = "";
    private String databaseName = "";
    private String userName= "";
    private String password= "";
    private String feedbackTableName = "";

    private Connection connect = null;



//    dbhandlers:
    //    mysql:
        //    connection:
            //    string:
            //    username:
            //    password:
        //    feedbacktablename:
        //    querylogstablename:

    @Autowired
    public AnalyticsManager(
            @Value("${spring.dbhandlers.mysql.connection.string}") String connString,
            @Value("${spring.dbhandlers.mysql.connection.dbname}") String dbName,
            @Value("${spring.dbhandlers.mysql.connection.username}") String user,
            @Value("${spring.dbhandlers.mysql.connection.password}") String pass,
            @Value("${spring.dbhandlers.mysql.feedbacktablename}") String fbTable){


        connectionString = connString;
        databaseName = dbName;
        userName = user;
        password = pass;
        feedbackTableName = fbTable;

        log.info(String.format("Initializing Analytics Manager. ConString:%s | DBName:%s | FeedBackTable:%s",
                connectionString,databaseName,feedbackTableName));


    }

    private void queryDataBase(String executableQuery) {
        log.debug(String.format("Executing query:\n %s",executableQuery));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Setup the connection with the DB
        try {
            String jdbcString =
                    String.format("%s/%s?%s&user=%s&password=%s",
                            connectionString, databaseName, settings, userName, password);

            connect = DriverManager
                    .getConnection(jdbcString);
            Statement setupStatement = connect.createStatement();
            setupStatement.execute(executableQuery);
//            setupStatement.executeBatch();
            setupStatement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Async
    public void storeFeedback(FeedbackRequest feedbackRequest) {
        log.info(String.format("Received a feedback:%s", feedbackRequest.toString()));
        String query = QueryGenerator.getFeedBackQuery(feedbackRequest,feedbackTableName);
        queryDataBase(query);
    }


    private static class QueryGenerator{

        public static String getFeedBackQuery(FeedbackRequest feedbackRequest, String tableName){

            String values = String.format(
                    "(\"%s\",\"%s\",\"%s\",\"%s\",%d,%d,\"%s\",\"%s\")",
                    feedbackRequest.getPmid(),
                    feedbackRequest.getAnnotation().getId(),
                    feedbackRequest.getAnnotation().getText(),
                    feedbackRequest.getAnnotation().getType(),
                    feedbackRequest.getAnnotation().getStartIndex(),
                    feedbackRequest.getAnnotation().getEndIndex(),
                    feedbackRequest.getFeedback(),
                    feedbackRequest.getTimeStamp()
            );
            String query = "INSERT INTO "  + tableName + "(" +
                    "pmid,\n" +
                    "annotationId,\n" +
                    "annotationText,\n" +
                    "annotationType,\n" +
                    "offsetBegin,\n" +
                    "offsetEnd,\n" +
                    "feedback,\n" +
                    "timeStamp)\n" +
                    "VALUES\n" +
                    values +
                    ";";
            return query;
        }

    }

}
