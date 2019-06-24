package com.example.taurus.service;

import com.example.taurus.model.domain.Resources;
import com.example.taurus.model.domain.Tag;
import com.example.taurus.service.base.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ResourcesService extends CrudService<Resources, Long> {
    Resources removeById(Long resourcesId);

    Resources updateResourcesName(Long resourcesId, String name);

    Resources findByResourceId(Long resourceId);

    Page<Resources> findResourcesOrigin(Integer i,Pageable pageable);

    List<Resources> findResourcesOrigin(Integer i);

    Page<Resources> findAll(Pageable pageable);

    List<Resources> findAll();
    List<Resources> getRecentFive();
    List<Resources> getRecentResource(int limit);

    Page<Resources> findType(String type,Pageable pageable);

    List<Resources> findType(String type);
    Page<Resources> findTags(Tag tag, Pageable pageable);

    List<Resources> findTags(Tag tag);

    /**
     * 文件上传
     * @param file
     * @param request
     * @return
     */
    Map<String, String> upload(MultipartFile file, HttpServletRequest request);

    /**
     * 文件上传
     * @param file
     * @param request
     * @return
     */
    Map<String,String>attachUpload(MultipartFile file,HttpServletRequest request);

}
