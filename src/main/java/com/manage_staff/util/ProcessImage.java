package com.manage_staff.util;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.firebase.cloud.StorageClient;
import com.manage_staff.config.FirebaseInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Slf4j
public class ProcessImage {
    public static synchronized String upload(MultipartFile image, String folder) throws IOException {
        if (!Objects.equals(image.getOriginalFilename(), "")) {
            FirebaseInitializer.getFirebaseApp();
            // Lấy đối tượng Storage từ Firebase
            Storage storage = StorageClient.getInstance().bucket().getStorage();
            String bucketName = StorageClient.getInstance().bucket().getName();
            // Đọc dữ liệu từ MultipartFile

            byte[] data = image.getBytes();
//            byte[] data = reduceSize(image, 600, 600);

            String contentType = image.getContentType();
            if (contentType == null) {
                contentType = "application/octet-stream"; // Đặt loại nội dung mặc định nếu không có
            }
            var imageName = image.getOriginalFilename();
            assert imageName != null;
            String fileName = UUID.randomUUID() +
                    imageName.substring(imageName.lastIndexOf("."));


            // Tạo BlobInfo với tên, đường dẫn tệp và loại nội dung
            BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, folder + fileName)
                    .setContentType(contentType) // Xác định loại nội dung ở đây
                    .build();
            // Tải ảnh lên Firebase Storage
            Blob blob = storage.create(blobInfo, data);


            // Lấy liên kết tải xuống
            return "https://firebasestorage.googleapis.com/v0/b/" + blobInfo.getBucket() + "/o/" + blob.getName().replace("/", "%2F") + "?alt=media";
        } else {
            return null;
        }
    }


    public static String saveImageInServer(MultipartFile image, Path resourcePath) throws IOException {


        // Tạo đường dẫn đến thư mục resource
        StringBuilder fileNames = new StringBuilder();


        if (!Objects.equals(image.getOriginalFilename(), "")) {
            String imgName = UUID.randomUUID() + image.getOriginalFilename();
            Path fileNameAndPath = Paths.get(resourcePath.toString(), imgName);
            fileNames.append(imgName);
            Files.write(fileNameAndPath, image.getBytes());
        }
        return fileNames.toString();
    }
}
