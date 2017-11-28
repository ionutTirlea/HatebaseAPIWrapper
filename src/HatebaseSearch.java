/**
 * Created by ionut.tirlea on 19/03/2017.
 */
import utils.SSLUtilities;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class HatebaseSearch {

    private boolean getHatebaseSearchResultJSON(String requestURL, String fileName){

        if (requestURL == null || requestURL.isEmpty()){
            return false;
        }

        if(!requestURL.startsWith("https://api.hatebase.org/v3-0/")) {
            return false;
        }

        if (fileName == null || fileName.isEmpty()) {
            return false;
        }

        if(!fileName.contains(".json")){
            return false;
        }
        try {

            SSLUtilities.trustAllCertificates();

            URL url = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");


            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + connection.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

            // Save the API JSON response to specified file
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);


            String output;
            while ((output = br.readLine()) != null) {
                bw.write(output);
            }

            bw.close();
            br.close();
            connection.disconnect();
            System.out.println("Executed Hatebase API call :requestURL=" + requestURL);
            return true;

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return false;
    }
    public boolean getHatebaseSearchResultJSON(RequestModel requestModel, String fileName){

        if (requestModel == null) {
            return false;
        }

        String requestURL = requestModel.getRequestURL();

        if (requestURL == null || requestURL.isEmpty()){
            return false;
        }

        if(!requestURL.startsWith("https://api.hatebase.org/v3-0/")) {
            return false;
        }

        if (fileName == null || fileName.isEmpty()) {
            return false;
        }

        if(!fileName.contains(".json")){
            return false;
        }
        return this.getHatebaseSearchResultJSON(requestURL,fileName);
    }

    /* The response will be batched to contain a maximum of 10000 results */
    public HashMap<String,ResponseModel> getHateBaseSearchResponse(RequestModel requestModel){
        /*TO-DO*/
        getHatebaseSearchResultJSON(requestModel,"");
        return null;
    }
}
