/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.ArrayList;
import model.Picture;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Emil
 */
public class StorePicturesComTest {

    /**
     * Test of storePictures method, of class StorePicturesCom.
     */
    @Test
    public void testStorePictures() throws Exception {
        System.out.println("storePictures");
        ArrayList<Picture> pictureList = new ArrayList();
        Picture p1 = new Picture("http://d3j5vwomefv46c.cloudfront.net/photos/large/415070043.jpg",
                "http://d3j5vwomefv46c.cloudfront.net/photos/thumb/415070043.jpg");
        Picture p2 = new Picture("http://d3j5vwomefv46c.cloudfront.net/photos/large/415070047.jpg",
                "http://d3j5vwomefv46c.cloudfront.net/photos/thumb/415070047.jpg");

        pictureList.add(p1);
        pictureList.add(p2);

        StorePicturesCom instance = new StorePicturesCom();

        int expResult = 200;
        int result = instance.storePictures(pictureList);
        System.out.println(result);
        assertEquals(expResult, result);
    }
}