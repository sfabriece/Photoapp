package imageGetters;

import java.io.InputStreamReader;
import model.Picture;
import com.google.gson.*;

/**
 *
 * @author T
 */
public class InstagramParser {

    private JsonArray jsonPictures;
    JsonObject obj;

    /**
     * Finds the location of the pictures in the InputStreamReader, returns them
     * as a JsonArray.
     * @param reader (InputStreamReader)
     * @return jsonPictures (JsonArray)
     */
    public JsonArray parse(InputStreamReader reader) {
        JsonParser parser = new JsonParser();
        obj = parser.parse(reader).getAsJsonObject();
        jsonPictures = obj.get("data").getAsJsonArray();
        return jsonPictures;
    }

    /**
     * Finds the next url in the InputStreamReader and returns it as a string.
     * @return next_url (String)
     */
    public String getNextUrl() {
        JsonElement next_url = obj.get("pagination");
        if (next_url != null) {
            String url = next_url.getAsJsonObject().get("next_url").getAsString();
            if (url != null) {
                return url;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Finds the URLs in the JsonElement and makes a type Picture out of them.
     * @param j (JsonElement)
     * @return picture (model.Picture)
     */
    public Picture addToList(JsonElement j) {
        JsonObject jsonPicture = j.getAsJsonObject();

        Picture picture = new Picture();

        JsonElement images = jsonPicture.get("images");
        JsonElement thumbImage = images.getAsJsonObject().get("thumbnail"); //thumbnail=150*150/standard_resolution=612*612
        String url = thumbImage.getAsJsonObject().get("url").getAsString();
        picture.thumbUrl = url;

        JsonElement largeImage = images.getAsJsonObject().get("standard_resolution");
        String largeUrl = largeImage.getAsJsonObject().get("url").getAsString();
        picture.largeUrl = largeUrl;

        return picture;
    }
}
