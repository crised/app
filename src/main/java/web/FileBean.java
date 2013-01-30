package web;

import org.primefaces.event.FileUploadEvent;
import util.Loggable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Logger;

@Named
@SessionScoped
public class FileBean implements Serializable {

    @Inject
    AdBean adBean;

    @Inject
    Logger log;


    @PostConstruct
    public void init() {
        log.info("File Bean Created");
    }

    @PreDestroy
    public void destroy() {
        log.info("File Bean Destroyed");
    }


    public void handleFileUpload(FileUploadEvent event) {

        adBean.fileUpload(event.getFile());


    }

}
