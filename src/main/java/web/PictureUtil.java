package web;

import model.Ad;
import model.Picture;
import org.primefaces.model.UploadedFile;
import service.PictureService;
import util.Loggable;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

@Loggable
public class PictureUtil implements Serializable {

    @Inject
    PictureService pictureService;

    @Inject
    private Logger log;


    private Picture pic;
    private Picture thumb;


    public void createImage(UploadedFile uploadedFile, Ad ad) {

        pic = pictureService.newPic();

        String imgPath = "/var/www/html/" + pic.getId() + ".jpg";
        String imgPathTn = "/var/www/html/" + pic.getId() + "_tn" + ".jpg";
        String webImgPath = "http://192.168.1.10/" + pic.getId() + ".jpg";  // absolute path

        BufferedImage img = compressWriteImage(uploadedFile, Paths.get(imgPath));

        createWriteThumbNail(img, Paths.get(imgPathTn));

        pic.setPath(webImgPath);
        pic.setAd(ad);
        pictureService.updatePicture(pic);
        try{
            Thread.sleep(10000);
        } catch(InterruptedException e){
            log.severe(e.getMessage());
        }


    }

    public BufferedImage compressWriteImage(UploadedFile uploadedFile, Path path) {

        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(uploadedFile.getInputstream());
            ImageIO.write(bimg, "jpg", path.toFile());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bimg;

    }

    public void createWriteThumbNail(BufferedImage image, Path path) {

        BufferedImage thumb = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        thumb.createGraphics().drawImage(image.getScaledInstance(200, 200, Image.SCALE_SMOOTH), 0, 0, null);

        try {
            ImageIO.write(thumb, "jpg", path.toFile());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
