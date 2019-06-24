package com.example.taurus.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.StrBuilder;
import com.example.taurus.model.domain.Resources;
import com.example.taurus.model.domain.Tag;
import com.example.taurus.repository.ResourcesRepository;
import com.example.taurus.service.ResourcesService;
import com.example.taurus.service.base.AbstractCrudService;
import com.example.taurus.utils.TaurusUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class ResourcesServiceImpl extends AbstractCrudService<Resources, Long> implements ResourcesService {
    private final ResourcesRepository resourcesRepository;

    protected ResourcesServiceImpl(ResourcesRepository resourcesRepository) {
        super(resourcesRepository);
        this.resourcesRepository = resourcesRepository;
    }

    @Override
    public Resources removeById(Long resourcesId) {
        final Optional<Resources>resources=fetchById(resourcesId);
        resourcesRepository.delete(resources.get());
        return resources.get();
    }

    @Override
    public Resources updateResourcesName(Long resourcesId, String name) {
        final Optional<Resources> resources= fetchById(resourcesId);
        resources.get().setResourcesName(name);
        return resourcesRepository.save(resources.get());
    }

    @Override
    public Resources findByResourceId(Long resourceId) {
        return resourcesRepository.findResourcesByResourcesId(resourceId);
    }

    @Override
    public Page<Resources> findResourcesOrigin(Integer i, Pageable pageable) {
        return resourcesRepository.findResourcesByResourcesOrigin(i,pageable);
    }

    @Override
    public List<Resources> findResourcesOrigin(Integer i) {
        return resourcesRepository.findResourcesByResourcesOrigin(i);
    }

    @Override
    public Page<Resources> findAll(Pageable pageable) {
        return resourcesRepository.findAll(pageable);
    }

    @Override
    public List<Resources> findAll() {
        return resourcesRepository.findAll();
    }

    @Override
    public List<Resources> getRecentFive() {
        return resourcesRepository.findTopFive();
    }

    @Override
    public List<Resources> getRecentResource(int limit) {
        return resourcesRepository.getResourcesByLimit(limit);
    }

    @Override
    public Page<Resources> findType(String type, Pageable pageable) {
        return resourcesRepository.findResourcesByResourcesType(type,pageable);
    }

    @Override
    public List<Resources> findType(String type) {
        return resourcesRepository.findResourcesByResourcesType(type);
    }

    @Override
    public Page<Resources> findTags(Tag tag, Pageable pageable) {
        return resourcesRepository.findResourcesByTags(tag,pageable);
    }

    @Override
    public List<Resources> findTags(Tag tag) {
        return resourcesRepository.findResourcesByTags(tag);
    }

    @Override
    public Map<String, String> upload(MultipartFile file, HttpServletRequest request) {
        Map<String, String> resultMap;
        resultMap = this.attachUpload(file, request);
        return resultMap;
    }

    @Override
    public Map<String, String> attachUpload(MultipartFile file, HttpServletRequest request) {
        final Map<String, String> resultMap = new HashMap<>(6);
        final String dateString = DateUtil.format(DateUtil.date(), "yyyyMMddHHmmss");
        try {
            //用户目录
            final StrBuilder uploadPath = new StrBuilder(System.getProperties().getProperty("user.home"));
            uploadPath.append("/taurus/");
            uploadPath.append("upload/");
            uploadPath.append(DateUtil.thisYear()).append("/").append(DateUtil.thisMonth()).append("/");
            final File mediaPath = new File(uploadPath.toString());
            if (!mediaPath.exists()) {
                if (!mediaPath.mkdirs()) {
                    resultMap.put("success", "0");
                    return resultMap;
                }

            }
            final StrBuilder nameWithOutSuffix = new StrBuilder(file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf('.')).replaceAll(" ", "_"));
            nameWithOutSuffix.append(dateString);
            nameWithOutSuffix.append(new Random().nextInt(1000));
            final String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.') + 1);

            final StrBuilder fileName = new StrBuilder(nameWithOutSuffix);
            fileName.append(".");
            fileName.append(fileSuffix);

            file.transferTo(new File(mediaPath.getAbsoluteFile(), fileName.toString()));
            final StrBuilder fullPath = new StrBuilder(mediaPath.getAbsolutePath());
            fullPath.append("/");
            fullPath.append(fileName);

            final StrBuilder fullSmallPath = new StrBuilder(mediaPath.getAbsolutePath());
            fullSmallPath.append("/");
            fullSmallPath.append(nameWithOutSuffix);
            fullSmallPath.append("_small.");
            fullSmallPath.append(fileSuffix);

            Thumbnails.of(fullPath.toString()).size(256, 256).keepAspectRatio(false).toFile(fullSmallPath.toString());
            final StrBuilder filePath = new StrBuilder("/upload/");
            filePath.append(DateUtil.thisYear());
            filePath.append("/");
            filePath.append(DateUtil.thisMonth());
            filePath.append("/");
            filePath.append(fileName);

            final StrBuilder fileSmallPath = new StrBuilder("/upload/");
            fileSmallPath.append(DateUtil.thisYear());
            fileSmallPath.append("/");
            fileSmallPath.append(DateUtil.thisMonth());
            fileSmallPath.append("/");
            fileSmallPath.append(nameWithOutSuffix);
            fileSmallPath.append("_small.");
            fileSmallPath.append(fileSuffix);

            final String size = TaurusUtils.parseSize(new File(fullPath.toString()).length());
            final String wh = TaurusUtils.getImageWh(new File(fullPath.toString()));

            resultMap.put("fileName", fileName.toString());
            resultMap.put("filePath", filePath.toString());
            resultMap.put("smallPath", fileSmallPath.toString());
            resultMap.put("suffix", fileSuffix);
            resultMap.put("size", size);
            resultMap.put("wh", wh);
            resultMap.put("location", "server");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
