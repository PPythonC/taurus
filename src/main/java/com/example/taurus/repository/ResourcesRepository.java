package com.example.taurus.repository;

import com.example.taurus.model.domain.Resources;
import com.example.taurus.model.domain.Tag;
import com.example.taurus.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResourcesRepository extends BaseRepository<Resources, Long> {
    /**
     * 通过资源的名称查找资源
     *
     * @param resourcesName
     * @return
     */
    Resources findResourcesByResourcesName(String resourcesName);

    Resources findResourcesByResourcesId(Long resourceId);

    @Query(value = "select * from taurus_resource order by resourcesCreated desc limit 5", nativeQuery = true)
    List<Resources> findTopFive();

    List<Resources> findResourcesByResourcesOrigin(Integer i);

    Page<Resources> findResourcesByResourcesOrigin(Integer i, Pageable pageable);

    List<Resources> findResourcesByResourcesType(String resourceType);

    Page<Resources> findResourcesByResourcesType(String resourceType, Pageable pageable);

    List<Resources> findResourcesByTags(Tag tag);

    Page<Resources> findResourcesByTags(Tag tag, Pageable pageable);

    Integer countAllBy();

    @Query(value = "select * from taurus_resource order by resourcesCreated desc LIMIT:limit", nativeQuery = true)
    List<Resources> getResourcesByLimit(@Param(value = "limit") int limit);

}
