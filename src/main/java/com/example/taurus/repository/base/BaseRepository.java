package com.example.taurus.repository.base;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;

import java.util.List;


/**
 * 基础仓库接口
 * @param <DOMAIN>
 * @param <ID>
 */
@NoRepositoryBean
public interface BaseRepository<DOMAIN, ID> extends JpaRepository<DOMAIN, ID> {
    /**
     * 查找通过编号
     *
     * @param ids
     * @param sort
     * @return
     */
    @NonNull
    List<DOMAIN> findAllByIdIn(@NonNull Iterable<ID> ids, @NonNull Sort sort);

    /**
     * 删除通过编号
     *
     * @param ids
     * @return
     */
    long deleteByIdIn(@NonNull Iterable<ID> ids);
}
