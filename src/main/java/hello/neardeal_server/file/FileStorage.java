package hello.neardeal_server.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Objects;

@Component
public class FileStorage {

    private final Path storageRoot;
    private final String urlPrefix;

    public FileStorage(
            @Value("${file.path}") String storagePath,
            @Value("${file.url-prefix:/files/}") String urlPrefix
    ) {
        this.storageRoot = Paths.get(Objects.requireNonNull(storagePath)).normalize().toAbsolutePath();
        this.urlPrefix   = urlPrefix.endsWith("/") ? urlPrefix : urlPrefix + "/";
    }

    // 확장자 불러오기
    private String getSafeExtension(String originalName) {
        if (originalName == null) return "";
        String name = Paths.get(originalName).getFileName().toString();
        int dot = name.lastIndexOf('.');
        if (dot < 0) return "";
        return name.substring(dot + 1).toLowerCase().replaceAll("[^a-z0-9]", "");
    }

    // 파일 저장 + 공개 URL 반환
    public String createUrl(String folderName, MultipartFile file, int index) {
        try {
            // 폴더 생성하기
            Path dir = storageRoot.resolve(folderName).normalize().toAbsolutePath();
            Files.createDirectories(dir);

            String ext = getSafeExtension(file.getOriginalFilename());
            String newName = (index + 1) + (ext.isEmpty() ? "" : "." + ext);

            // 상대 경로를 부모 경로에 붙여주는 역할
            Path fullPath = dir.resolve(newName).normalize().toAbsolutePath();
            if (!fullPath.startsWith(storageRoot)) {
                throw new SecurityException("Invalid target path: " + fullPath);
            }

            try (InputStream in = file.getInputStream()) {
                Files.copy(in, fullPath, StandardCopyOption.REPLACE_EXISTING);
            }

            String encFolder = UriUtils.encodePathSegment(folderName, StandardCharsets.UTF_8);
            String encFile   = UriUtils.encodePathSegment(newName, StandardCharsets.UTF_8);
            return urlPrefix + encFolder + "/" + encFile;

        } catch (IOException e) {
            throw new IllegalStateException("파일 저장 실패", e);
        }
    }

    // 파일 삭제하기
    public void deleteByPublicUrl(String publicUrl) {
        if (publicUrl == null || publicUrl.isBlank()) return;

        String prefix = urlPrefix.endsWith("/") ? urlPrefix : urlPrefix + "/";
        String relative = publicUrl.startsWith(prefix) ? publicUrl.substring(prefix.length()) : publicUrl;
        String decoded  = URLDecoder.decode(relative, StandardCharsets.UTF_8);

        Path target = storageRoot.resolve(decoded).normalize().toAbsolutePath();
        if (!target.startsWith(storageRoot)) {
            throw new SecurityException("Invalid path: " + publicUrl);
        }

        try {
            Files.deleteIfExists(target);

            // 상위 폴더 비었으면 정리
            Path parent = target.getParent();
            if (parent != null && Files.isDirectory(parent)) {
                try (var s = Files.list(parent)) {
                    if (s.findAny().isEmpty()) Files.deleteIfExists(parent);
                }
            }
        } catch (Exception e) {
            System.err.println("이미지 삭제 실패: " + target + " - " + e.getMessage());
        }
    }
}
