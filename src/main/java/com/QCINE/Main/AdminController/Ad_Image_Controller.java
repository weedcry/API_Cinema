package com.QCINE.Main.AdminController;

import com.QCINE.Main.DTO.FileResponse;
import com.QCINE.Main.security.config.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin/image")
public class Ad_Image_Controller {
    private AmazonClient amazonClient;

    @Autowired
    Ad_Image_Controller(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<FileResponse> uploadFile(@RequestPart(value = "file") MultipartFile file) {
        System.out.println("Upload file called ");
        return new ResponseEntity<FileResponse>(amazonClient.uploadFile(file), HttpStatus.OK);
    }

    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
    }

}
