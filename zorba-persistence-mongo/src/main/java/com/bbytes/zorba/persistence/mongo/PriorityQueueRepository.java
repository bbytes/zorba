package com.bbytes.zorba.persistence.mongo;

import com.bbytes.zorba.domain.PriorityQueue;
import com.bbytes.zorba.persistence.IPriorityQueueDao;

@Deprecated
public interface PriorityQueueRepository extends ZorbaRepository<PriorityQueue>, 
	IPriorityQueueDao {

}
