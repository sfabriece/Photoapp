package repository;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import model.Picture;

/**
 *
 * @author Emil
 */
public class DeletePicturesCom {

    private String request = GlobalVariables.baseUrl + "pictures";
    private DataOutputStream outStream;

    /*
     * Takes an ArrayList with picture urls as strings, and sends it in a JSON
     * format to the server, for deletion.
     * Returns the HTTP respons code
     */
    public int deletePictures(ArrayList<Picture> imageList) throws IOException {
        if (imageList.size() < 1) {
            return 0;
        }
        URL url = new URL(request);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        connection.connect();

        String body = "[";

        outStream = new DataOutputStream(connection.getOutputStream());
        for (int i = 0; i < imageList.size(); i++) {
            if (i != imageList.size() - 1) {
                body = body + " {\"url\": " + "\"" + imageList.get(i).getLargeUrl() + "\"},";
            } else {
                body = body + " {\"url\": " + "\"" + imageList.get(i).getLargeUrl() + "\"";
            }
        }
        body = body + "}]";
        outStream.writeBytes(body);
        outStream.flush();
        outStream.close();
        final int responseCode = connection.getResponseCode();
        return responseCode;
    }
}
