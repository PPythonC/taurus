package com.example.taurus.service.base;

import cn.hutool.core.lang.Assert;
import com.example.taurus.exception.NotFoundException;
import com.example.taurus.logging.Logger;
import com.example.taurus.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.cert.CollectionCertStoreParameters;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudService<DOMAIN, ID> implements CrudService<DOMAIN, ID> {
    private final Logger log = Logger.getLogger(getClass());
    private final String domainName;
    private final BaseRepository<DOMAIN, ID> repository;

    protected AbstractCrudService(BaseRepository<DOMAIN, ID> repository) {
        this.repository = repository;
        Class<DOMAIN> domainClass = (Class<DOMAIN>) fetchType(0);
        domainName = domainClass.getSimpleName();
    }

    private Type fetchType(int index) {
        Assert.isTrue(index >= 0 && index <= 1, "type index must be between 0 to 1");
        return ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[index];
    }

    @Override
    public List<DOMAIN> listAll() {
        return repository.findAll();
    }

    @Override
    public List<DOMAIN> listAll(Sort sort) {
        Assert.notNull(sort, "Sort infor must be null");
        return repository.findAll(sort);
    }

    @Override
    public Page<DOMAIN> listAll(Pageable pageable) {
        Assert.notNull(pageable, "Pageable info must be null");
        return repository.findAll(pageable);
    }

    @Override
    public List<DOMAIN> listAllByIds(Collection<ID> ids) {
        return CollectionUtils.isEmpty(ids) ? Collections.emptyList() : repository.findAllById(ids);
    }

    @Override
    public List<DOMAIN> listAllByIds(Collection<ID> ids, Sort sort) {
        Assert.notNull(sort, "Sort info must not be null");
        return CollectionUtils.isEmpty(ids) ? Collections.emptyList() : repository.findAllByIdIn(ids, sort);
    }

    @Override
    public Optional<DOMAIN> fetchById(ID id) {
        Assert.notNull(id, "id must not be null");
        return repository.findById(id);
    }

    @Override
    public DOMAIN getById(ID id) {
        return fetchById(id).orElseThrow(() -> new NotFoundException(domainName + " was not found"));
    }

    @Override
    public DOMAIN getByIdOfNullable(ID id) {
        return fetchById(id).orElse(null);
    }

    @Override
    public boolean existsById(ID id) {
        Assert.notNull(id, domainName + " id must not be null");
        return repository.existsById(id);
    }

    @Override
    public void mustExistById(ID id) {
        if (!existsById(id)) {
            throw new NotFoundException(domainName + " was not exist");
        }
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public DOMAIN create(DOMAIN domain) {
        Assert.notNull(domain, domainName + " data must not be null");

        return repository.save(domain);
    }

    @Override
    public List<DOMAIN> createInBatch(Collection<DOMAIN> domains) {
        return CollectionUtils.isEmpty(domains) ? Collections.emptyList() : repository.saveAll(domains);
    }

    @Override
    public DOMAIN update(DOMAIN domain) {
        Assert.notNull(domain, domainName + " data must not be null");

        return repository.saveAndFlush(domain);
    }

    @Override
    public List<DOMAIN> updateInBatch(Collection<DOMAIN> domains) {
        return CollectionUtils.isEmpty(domains) ? Collections.emptyList() : repository.saveAll(domains);
    }

    @Override
    public DOMAIN removeById(ID id) {
        // Get non null domain by id
        DOMAIN domain = getById(id);

        // Remove it
        remove(domain);

        // return the deleted domain
        return domain;
    }

    @Override
    public DOMAIN removeByIdOfNullable(ID id) {
        return fetchById(id).map(domain -> {
            remove(domain);
            return domain;
        }).orElse(null);
    }

    @Override
    public void remove(DOMAIN domain) {
        Assert.notNull(domain, domainName + " data must not be null");

        repository.delete(domain);
    }

    @Override
    public void removeInBatch(Collection<ID> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            log.warn(domainName + " id collection is empty");
            return;
        }
        repository.deleteByIdIn(ids);
    }

    @Override
    public void removeAll(Collection<DOMAIN> domains) {
        if (CollectionUtils.isEmpty(domains)) {
            log.warn(domainName + " collection is empty");
            return;
        }
        repository.deleteInBatch(domains);
    }

    @Override
    public void removeAll() {
        repository.deleteAll();
    }
}
