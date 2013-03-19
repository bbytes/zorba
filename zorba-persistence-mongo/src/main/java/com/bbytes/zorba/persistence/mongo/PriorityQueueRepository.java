package com.bbytes.zorba.persistence.mongo;

import com.bbytes.zorba.domain.PriorityQueue;
import com.bbytes.zorba.persistence.IPriorityQueueDao;

public interface PriorityQueueRepository extends ZorbaRepository<PriorityQueue>, 
	IPriorityQueueDao {

}
