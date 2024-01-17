package com.team5.projrental.common.utils;

import com.team5.projrental.common.Const;
import com.team5.projrental.common.exception.base.WrapRuntimeException;
import com.team5.projrental.common.exception.checked.FileNotContainsDotException;
import com.team5.projrental.product.model.innermodel.StoredFileInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.team5.projrental.common.utils.ErrorCode.SERVER_ERR_MESSAGE;

@Component
public class MyFileUtils {

    @Value("${file.base-package}")
    private String basePath;

    // 파일 업로드 배운 후 완성시킬 예정.

    /**
     * 하나의 사진 파일 조회
     *
     * @param pic
     * @return Resource
     */
    public Resource getPic(StoredFileInfo pic) {



        /* TODO: 1/9/24
            차후 파일 업로드 배우면 수정.
            --by Hyunmin */
        return new FileSystemResource("test");
    }

    // 파일 업로드 배운 후 완성시킬 예정.

    /**
     * 2개 이상의 사진 파일 조회
     * [getPic 내부 호출]
     *
     * @param pic
     * @return List<Resource>
     */
    public List<Resource> getPic(List<StoredFileInfo> pic) {
        List<Resource> results = new ArrayList<>();
        pic.forEach(p -> results.add(getPic(p)));
        return results;
    }

    // 파일 업로드 배운 후 완성시킬 예정.

    /**
     * 하나의 사진 파일 저장
     *
     * @param multipartFile
     * @param category
     * @return StoredFileInfo
     * @throws FileNotContainsDotException
     */
    public StoredFileInfo savePic(MultipartFile multipartFile, String category) throws FileNotContainsDotException {
        String originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename == null || !originalFilename.contains(".")) {
            throw new FileNotContainsDotException();
        }
        String storeName = generateRandomFileName(multipartFile.getOriginalFilename()); //aegeg.jpg
        String storedName = generateStoredPath(category, storeName); // category/fileName;

        Path path = Paths.get(this.basePath, storedName);

        try {
            multipartFile.transferTo(path);
        } catch (IOException e) {
            throw new WrapRuntimeException(SERVER_ERR_MESSAGE);
        }
        return new StoredFileInfo(originalFilename, storedName);
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
    public List<StoredFileInfo> savePic(List<MultipartFile> multipartFiles, String category) throws FileNotContainsDotException {
        List<StoredFileInfo> result = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            savePic(multipartFile, category);
        }
        return result;
    }

    private String generateStoredPath(String category, String fileName) {
        return Paths.get(category, fileName).toString();
    }

    private String generateRandomFileName(String fileName) {
        return UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
    }
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
