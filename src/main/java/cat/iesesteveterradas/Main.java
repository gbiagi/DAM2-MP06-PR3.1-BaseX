package cat.iesesteveterradas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.basex.api.client.ClientSession;
import org.basex.core.*;
import org.basex.core.cmd.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        // Initialize connection details
        String host = "127.0.0.1";
        int port = 1984;
        String username = "admin"; // Default username
        String password = "admin"; // Default password
        String pathQuery1 = "data/input/query1.xquery";
        String pathQuery2 = "data/input/query2.xquery";
        String pathQuery3 = "data/input/query3.xquery";
        String pathQuery4 = "data/input/query4.xquery";

        // Establish a connection to the BaseX server
        try (ClientSession session = new ClientSession(host, port, username, password)) {
            logger.info("Connected to BaseX server.");

            session.execute(new Open("anime"));

            String myQuery = "";

            // Query 1
            // Read the query from the file
            myQuery = readQuery(pathQuery1);
            // Execute the query
            String result = session.execute(new XQuery(myQuery));
            // Save the result in a xml file
            saveResult("query1", result);
            // Print the result
            logger.info("Query Saved:");
            //logger.info(result);

            // Query 2
            // Read the query from the file
            myQuery = readQuery(pathQuery2);
            // Execute the query
            result = session.execute(new XQuery(myQuery));
            // Save the result in a xml file
            saveResult("query2", result);
            // Print the result
            logger.info("Query Saved:");
            //logger.info(result);

            // Query 3
            // Read the query from the file
            myQuery = readQuery(pathQuery3);
            // Execute the query
            result = session.execute(new XQuery(myQuery));
            // Save the result in a xml file
            saveResult("query3", result);
            // Print the result
            logger.info("Query Saved:");
            //logger.info(result);

            // Query 4
            // Read the query from the file
            myQuery = readQuery(pathQuery4);
            // Execute the query
            result = session.execute(new XQuery(myQuery));
            // Save the result in a xml file
            saveResult("query4", result);
            // Print the result
            logger.info("Query Saved:");
            //logger.info(result);

        } catch (BaseXException e) {
            logger.error("Error connecting or executing the query: " + e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public static String readQuery(String path) {
        // read the query file from data
        String query = "";
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                query += scanner.nextLine();
            }
            scanner.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return query;
    }
    public static void saveResult(String name, String result) {
        // save the result in a file
        File fileResult = new File("data/output/" + name + ".xml");
        try {
            fileResult.createNewFile();
            FileWriter myWriter = new FileWriter(fileResult);
            myWriter.write(result);
            myWriter.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
