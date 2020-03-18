package chapter6;

import java.util.ArrayList;
import java.util.List;

public class SingleThreadedRenderer {

    void renderPage(CharSequence source) {
        renderText(source);
        List<ImageData> imageData = new ArrayList<>();
        for (ImageInfo imageInfo : scanForImageInfo(source)) {
            imageData.add(imageInfo.downloadImage());
        }
        for (ImageData image : imageData) {
            renderImage(image);
        }
    }

    private void renderImage(ImageData image) {

    }

    private ImageInfo[] scanForImageInfo(CharSequence source) {
        return new ImageInfo[0];
    }

    private void renderText(CharSequence source) {

    }

}
