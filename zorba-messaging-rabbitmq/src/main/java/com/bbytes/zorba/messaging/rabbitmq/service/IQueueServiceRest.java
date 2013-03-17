/*
 * Copyright (C) 2013 The Zorba Open Source Project 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package com.bbytes.zorba.messaging.rabbitmq.service;

import java.util.List;

import javax.naming.Binding;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.bbytes.zorba.messaging.rabbitmq.domain.Queue;

/**
 * 
 *
 * @author Thanneer
 *
 * @version 0.0.1
 */
@Path("queues")
public interface IQueueServiceRest {

	/**
	 * A list of all queues.
	 *
	 * @return
	 */
	@GET
	List<Queue> getQueues();

	/**
	 * A list of all queues in a given virtual host.
	 *
	 * @param vhost
	 * @return
	 */
	@GET
	@Path("{vhost}")
	List<Queue> getQueues(@PathParam("vhost") String vhost);

	/**
	 * An individual queue.
	 *
	 * @param vhost
	 * @param name
	 * @return
	 */
	@GET
	@Path("{vhost}/{name}")
	Queue getQueue(@PathParam("vhost") String vhost,
			@PathParam("name") String name);

	/**
	 * To PUT a queue, you will need a body looking something like this:
	 * {"auto_delete":false,"durable":true,"arguments":[]}
	 *
	 * @param vhost
	 * @param name
	 * @param queue
	 */
	@PUT
	@Consumes("application/json")
	@Path("{vhost}/{name}")
	void putQueue(@PathParam("vhost") String vhost,
			@PathParam("name") String name, Queue queue);

	@DELETE
	@Path("{vhost}/{name}")
	void deleteQueue(@PathParam("vhost") String vhost,
			@PathParam("name") String name);

	@GET
	@Path("{vhost}/{queue}/bindings")
	List<Binding> getBindings(@PathParam("vhost") String vhost,
			@PathParam("queue") String queue);

	@DELETE
	@Path("{vhost}/{name}/contents")
	void deleteQueueContent(@PathParam("vhost") String vhost,
			@PathParam("name") String name);
}
