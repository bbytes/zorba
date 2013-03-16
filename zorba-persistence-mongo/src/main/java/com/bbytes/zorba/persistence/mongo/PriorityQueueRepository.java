package com.bbytes.zorba.persistence.mongo;

import com.bbytes.zorba.domain.IPriorityQueue;
import com.bbytes.zorba.persistence.IPriorityQueueDao;

public interface PriorityQueueRepository extends ZorbaRepository<IPriorityQueue>, 
	IPriorityQueueDao {

}
