/*
 * Copyright (C) 2013 The Zorba Open Source Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.bbytes.zorba.messaging.rabbitmq.domain;

import java.sql.Connection;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * 
 * @author Thanneer
 * 
 * @version
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Queue {

	/**
	 * The name of the queue
	 */
	private String name;

	/**
	 * Virtual host name
	 */
	private String vHost;

	/**
	 * id of the Erlang process associated with the queue
	 */
	private String pid;

	/**
	 * id of the Erlang process representing the connection which is the exclusive owner of the
	 * queue, or empty if the queue is non-exclusive
	 */
	private String connectionPid;

	/**
	 * Connection on which the queue is based
	 */
	private Connection connection;

	/**
	 * id of the Erlang process representing the channel of the exclusive consumer subscribed to
	 * this queue, or empty if there is no exclusive consumer
	 */
	private String exclusiveChannelPid;

	/**
	 * consumer tag of the exclusive consumer subscribed to this queue, or empty if there is no
	 * exclusive consumer
	 */
	private String exclusiveConsumerTag;

	/**
	 * Whether the queue will be deleted automatically when no longer used
	 */
	private Boolean autoDelete=false;

	/**
	 * Whether or not the queue survives server restarts
	 */
	private Boolean durable=true;

	/**
	 * Queue arguments
	 */
	private Map<String, String> arguments;

	/**
	 * Sum of ready, unacknowledged and uncommitted messages
	 */
	private long messages;

	/**
	 * number of messages ready to be delivered to clients
	 */
	private long messages_ready;

	/**
	 * number of messages delivered to clients but not yet acknowledged
	 */
	private long messages_unacknowledged;

	/**
	 * Number of consumers
	 */
	private long consumers;

	/**
	 * Bytes of memory consumed by the Erlang process for the queue, including stack, heap and
	 * internal structures
	 */
	private long memory;
	
	/**
	 * Idle time information 
	 */
	private String idle_since;

	/**
	 *
	 */
	// TODO : Find the usage of this field
	private Object backingQueueStatus;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getvHost() {
		return vHost;
	}

	public void setvHost(String vHost) {
		this.vHost = vHost;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getConnectionPid() {
		return connectionPid;
	}

	public void setConnectionPid(String connectionPid) {
		this.connectionPid = connectionPid;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public String getExclusiveChannelPid() {
		return exclusiveChannelPid;
	}

	public void setExclusiveChannelPid(String exclusiveChannelPid) {
		this.exclusiveChannelPid = exclusiveChannelPid;
	}

	public String getExclusiveConsumerTag() {
		return exclusiveConsumerTag;
	}

	public void setExclusiveConsumerTag(String exclusiveConsumerTag) {
		this.exclusiveConsumerTag = exclusiveConsumerTag;
	}

	public Boolean isAutoDelete() {
		return autoDelete;
	}

	public void setAutoDelete(Boolean autoDelete) {
		this.autoDelete = autoDelete;
	}

	public Boolean isDurable() {
		return durable;
	}

	public void setDurable(Boolean durable) {
		this.durable = durable;
	}

	public Map<String, String> getArguments() {
		return arguments;
	}

	public void setArguments(Map<String, String> arguments) {
		this.arguments = arguments;
	}

	public long getMessages() {
		return messages;
	}

	public void setMessages(long messages) {
		this.messages = messages;
	}


	/**
	 * @return the messages_ready
	 */
	public long getMessages_ready() {
		return messages_ready;
	}

	/**
	 * @param messages_ready the messages_ready to set
	 */
	public void setMessages_ready(long messages_ready) {
		this.messages_ready = messages_ready;
	}

	/**
	 * @return the messages_unacknowledged
	 */
	public long getMessages_unacknowledged() {
		return messages_unacknowledged;
	}

	/**
	 * @param messages_unacknowledged the messages_unacknowledged to set
	 */
	public void setMessages_unacknowledged(long messages_unacknowledged) {
		this.messages_unacknowledged = messages_unacknowledged;
	}

	public long getConsumers() {
		return consumers;
	}

	public void setConsumers(long consumers) {
		this.consumers = consumers;
	}

	public long getMemory() {
		return memory;
	}

	public void setMemory(long memory) {
		this.memory = memory;
	}

	/**
	 * @return the idle_since
	 */
	public String getIdle_since() {
		return idle_since;
	}

	/**
	 * @param idle_since the idle_since to set
	 */
	public void setIdle_since(String idle_since) {
		this.idle_since = idle_since;
	}

	public Object getBackingQueueStatus() {
		return backingQueueStatus;
	}

	public void setBackingQueueStatus(Object backingQueueStatus) {
		this.backingQueueStatus = backingQueueStatus;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Queue queue = (Queue) o;

		if (name != null ? !name.equals(queue.name) : queue.name != null)
			return false;
		if (vHost != null ? !vHost.equals(queue.vHost) : queue.vHost != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Queue{durable=" + durable + ", name='" + name + "', vHost='" + vHost + "', autoDelete=" + autoDelete
				+ ", arguments=" + arguments + '}';
	}
}
