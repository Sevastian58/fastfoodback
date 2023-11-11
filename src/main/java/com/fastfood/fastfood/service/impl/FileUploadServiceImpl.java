package com.fastfood.fastfood.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadServiceImpl  {

    private final String UPLOAD_DIR = "/path/to/upload/directory";

    /*@Override
    public void uploadFile(InputStream inputStream) {
        try {
            byte[] bytes = inputStream.readAllBytes();
            Path path = Paths.get(UPLOAD_DIR + "/" + UUID.randomUUID().toString());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

    public static void updateFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        Path filePath = uploadPath.resolve(fileName);

        if (Files.exists(filePath)) {
            // Si el archivo existe, sobrescribe la imagen
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioe) {
                throw new IOException("Could not update image file: " + fileName, ioe);
            }
        } else {
            // Si el archivo no existe, lanza una excepción o realiza el manejo adecuado.
            throw new FileNotFoundException("Image file not found: " + fileName);
        }
    }
}
