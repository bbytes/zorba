package com.bbytes.zorba.persistence.mongo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bbytes.zorba.domain.IEntity;
import com.bbytes.zorba.persistence.IZorbaDao;

public interface ZorbaRepository<T extends IEntity> extends
		PagingAndSortingRepository<T, String>, IZorbaDao<T> {

}
