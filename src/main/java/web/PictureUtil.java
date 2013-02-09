package web;

import exception.PictureException;
import model.Ad;
import model.Picture;
import org.jboss.logging.Logger;
import org.primefaces.model.UploadedFile;
import service.PictureService;
import util.Loggable;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

@Loggable
public class PictureUtil implements Serializable {

    @Inject
    PictureService pictureService;

    static final Logger log = Logger.getLogger(PictureUtil.class);

    @Inject
    ResourceBundle rB;


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


    }

    public BufferedImage compressWriteImage(UploadedFile uploadedFile, Path path) {

        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(uploadedFile.getInputstream());

            if(bimg.getWidth()<= 300 || bimg.getHeight() <=200){
                throw new PictureException(rB.getString("pictureUtil.imageSmall"));
            }

            ImageIO.write(bimg, "jpg", path.toFile());

        } catch (IOException e) {
            // read & write
            log.warn(e.getMessage());
            throw new PictureException(rB.getString("pictureUtil.IOException"));
        } catch (IllegalArgumentException e) {
            // read & write - If it is null
            log.warn(e.getMessage());
            throw new PictureException(rB.getString("pictureUtil.IllegalArgumentException"));
        }
        return bimg;

    }

    public void createWriteThumbNail(BufferedImage image, Path path) {

        BufferedImage thumb = new BufferedImage(300, 200, BufferedImage.TYPE_INT_RGB);
        thumb.createGraphics().drawImage(image.getScaledInstance(300, 200, Image.SCALE_SMOOTH), 0, 0, null);

        try {
            ImageIO.write(thumb, "jpg", path.toFile());
        } catch (IOException e) {
            log.warn(e.getMessage());
            throw new PictureException(rB.getString("pictureUtil.IOException"));
        } catch (IllegalArgumentException e) {
            log.warn(e.getMessage());
            throw new PictureException(rB.getString("pictureUtil.IllegalArgumentException"));
        }

    }


}
