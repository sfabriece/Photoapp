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
public class StorePicturesCom {

    private String request = GlobalVariables.baseUrl + "pictures";
    private DataOutputStream outStream;

    /**
     * Takes a list of pictures as parameter, stores them in a JSON format and
     * sends it to server for storage.
     *
     * @param pictureList ArrayList<Picture>
     * @return HTTP responsecode (Integer)
     * @throws IOException
     */
    public int storePictures(ArrayList<Picture> pictureList) throws IOException {
        URL url = new URL(request);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        connection.connect();

        String body = "[";

        outStream = new DataOutputStream(connection.getOutputStream());
        for (int i = 0; i < pictureList.size(); i++) {
            if (i < pictureList.size() - 1) {
                body += "{\n \"thumbUrl\": " + "\"" + pictureList.get(i).getThumbUrl() + "\",\n";
                body += " \"url\": " + "\"" + pictureList.get(i).getLargeUrl() + "\",\n";
                body += " \"date\": " + "\"" + pictureList.get(i).getUnixDate() + "\",\n";
                body += " \"tag\": " + "\"" + pictureList.get(i).getTag() + "\"\n},";
            } else {
                body += "{\n \"thumbUrl\": " + "\"" + pictureList.get(i).getThumbUrl() + "\",\n";
                body += " \"url\": " + "\"" + pictureList.get(i).getLargeUrl() + "\",\n";
                body += " \"date\": " + "\"" + pictureList.get(i).getUnixDate() + "\",\n";
                body += " \"tag\": " + "\"" + pictureList.get(i).getTag() + "\"\n}";
            }
        }

        body += "]";

        outStream.writeBytes(body);
        outStream.flush();
        outStream.close();

        return connection.getResponseCode();
    }
}
