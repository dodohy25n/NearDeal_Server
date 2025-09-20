package hello.neardeal_server.store.service;

import hello.neardeal_server.member.entity.Owner;
import hello.neardeal_server.store.dto.StoreDetailResponse;
import hello.neardeal_server.store.dto.StoreRequest;
import hello.neardeal_server.store.entity.Store;
import hello.neardeal_server.store.repository.StoreRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;

    @Value("${file.path}")
    private String storagePath;

    @Transactional
    // 상점 등록
    public Long createStore(StoreRequest request, MultipartFile file){

//        Long owner = 1L;

        // 멤버 폴더 생성 (nearDealImage/{memberId})
        Path memberDir = Paths.get(storagePath)
                .resolve(String.valueOf(request.getStoreName()))
                .normalize()
                .toAbsolutePath();
        try {
            Files.createDirectories(memberDir);
        } catch (IOException e) {
            throw new IllegalStateException("파일 저장 폴더 생성 실패: " + memberDir, e);
        }

        // 사진 저장
//        List<String> imageUrls = new ArrayList<>();
//        if(files != null){
//            for(int i = 0; i < files.length; i++){
//                MultipartFile file = files[i];
//                String publicUrl = createUrl(request.getStoreName(), file, i, memberDir);
//                imageUrls.add(publicUrl);
//            }
//        }

        String publicUrl = createUrl(request.getStoreName(), file, 0, memberDir);

        // todo: owner 만든 뒤 해야함
        Store store = Store.create(request, publicUrl, new Owner());
        Store save = storeRepository.save(store);

        return save.getId();
    }



    // 상점 정보 상세 조회
    public StoreDetailResponse findStoreDetail(Long storeId){
        Store store = findOne(storeId);

        return StoreDetailResponse.entityToResponse(store);

    }

    // 상점 전체 목록 조회
    // 상점 필터링 목록 조회
    // 상점 정보 수정
    // 상점 정보 삭제
    // 내 가게 스탬프 목록 조회


    public Store findOne(Long storeId){
        return storeRepository.findById(storeId).orElseThrow(() -> new RuntimeException("찾는 store가 없습니다"));
    }

    // 확장자 불러오기
    private String getSafeExtension(String originalName) {
        if (originalName == null) return "";
        String name = Paths.get(originalName).getFileName().toString();
        int dot = name.lastIndexOf('.');
        if (dot < 0) return "";
        return name.substring(dot + 1).toLowerCase().replaceAll("[^a-z0-9]", "");
    }


    // Url만들기
    private String createUrl(String storeName, MultipartFile file, int index, Path memberDir) {
        String ext = getSafeExtension(file.getOriginalFilename());
        String newName = (index +1) + (ext.isEmpty() ? "" : "." + ext);

        // 상대 경로를 부모 경로에 붙여주는 역할
        Path fullPath = memberDir.resolve(newName).normalize();

        try (InputStream in = file.getInputStream()) {
            Files.copy(in, fullPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IllegalStateException("파일 저장 실패: " + newName, e);
        }

        String encodedStoreName = UriUtils.encodePathSegment(storeName, StandardCharsets.UTF_8);
        String encodedFile  = UriUtils.encodePathSegment(newName, StandardCharsets.UTF_8);

        return "/files/" + encodedStoreName+ "/" + encodedFile;
    }

}
