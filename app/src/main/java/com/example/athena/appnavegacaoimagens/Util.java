package com.example.athena.appnavegacaoimagens;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Util {

    public static List<Bitmap> dowloadImageList(List<URL> urls)throws IOException{
        List<Bitmap> images;
        images = null;
        InputStream inputStream;
        Bitmap image;

        for (URL url: urls) {
            inputStream = url.openStream();
            image = BitmapFactory.decodeStream(inputStream);

            images.add(image);
            inputStream.close();
        }
        return images;
    }
}
