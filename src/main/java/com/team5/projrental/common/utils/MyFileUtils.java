package com.team5.projrental.common.utils;

import com.team5.projrental.common.exception.base.WrapRuntimeException;
import com.team5.projrental.common.exception.checked.FileNotContainsDotException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.team5.projrental.common.exception.ErrorCode.SERVER_ERR_MESSAGE;

@Component
public class MyFileUtils {

    @Value("${file.base-package}")
    private String basePath;

    /**
     * 하나의 사진 파일 저장
     *
     * @param multipartFile
     * @param category
     * @return StoredFileInfo
     * @throws FileNotContainsDotException
     */
    public String savePic(MultipartFile multipartFile, String category, String pk) throws FileNotContainsDotException {
        String originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename == null || !originalFilename.contains(".")) {
            throw new FileNotContainsDotException();
        }
        String storeName = generateRandomFileName(multipartFile.getOriginalFilename()); //aegeg.jpg
        File packagePath = new File(generateStoredPath(category, pk)); // basePackage + category + pk
        File file = new File(packagePath, storeName);

        if (!packagePath.exists()) {
            file.mkdirs();
        }
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            throw new WrapRuntimeException(SERVER_ERR_MESSAGE);
        }
        return storeName;
    }

    // 파일 업로드 배운 후 완성시킬 예정.


    /**
     * 2개 이상의 사진 파일 저장
     * [savePic 내부 호출]
     *
     * @param multipartFiles
     * @param category
     * @return List<StoredFileInfo>
     * @throws FileNotContainsDotException
     */
    public List<String> savePic(List<MultipartFile> multipartFiles, String category, String pk) throws FileNotContainsDotException {
        List<String> result = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            savePic(multipartFile,  category , pk);
        }
        return result;
    }

    private String generateStoredPath(String category, String pk) {
        return Paths.get(this.basePath, category, pk).toString();
    }

    private String generateRandomFileName(String fileName) {
        return UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
    }

    //

    public void delFolderTrigger(String relativePayh) {
        delFolder(basePath + relativePayh);
    }

    public void delFolder(String folderPath) {
        File folder = new File( folderPath);

        if (folder.exists()) {
            File[] files = folder.listFiles();

            for (File file : files) {
                if (file.isDirectory()) {
                    delFolder(file.getAbsolutePath());
                } else {
                    file.delete();
                }
            }
            folder.delete();
        }
    }
}
