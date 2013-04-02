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
package com.bbytes.zorba.domain.testing;

import java.io.IOException;

import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.MongoDbFactory;

import com.mongodb.DB;
import com.mongodb.Mongo;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

/**
 * An implementation of {@link MongoDbFactory} that will be started in emdedded mode
 * 
 * @author Dhanush Gopinath
 * 
 * @version
 */
public class EmbeddedMongoDBFactory implements IEmbeddedMongoDBFactory {

	private String databaseName;
	private MongodExecutable mongodExecutable;
	private DB db;
	
	public EmbeddedMongoDBFactory(String databaseName) {
		this.databaseName = databaseName;
	}

	@Override
	public DB getDb() throws DataAccessException {
		return getDb(databaseName);
	}

	@Override
	public DB getDb(String dbName) throws DataAccessException {
		//return if db already exists
		if(db!=null &&db.getName().equals(dbName)) {
			return db;
		}
		try {
			int port = 12345;
			MongodConfig mongodConfig = new MongodConfig(Version.Main.V2_0, port, Network.localhostIsIPv6());
			MongodStarter runtime = MongodStarter.getDefaultInstance();
			mongodExecutable = runtime.prepare(mongodConfig);
			mongodExecutable.start();
			Mongo mongo = new Mongo("localhost", port);
			db = mongo.getDB(dbName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return db;
	}

	@Override
	public void stopDB(String databaseName) {
		if(db!=null && mongodExecutable!=null)
			mongodExecutable.stop();
		
	}

}
