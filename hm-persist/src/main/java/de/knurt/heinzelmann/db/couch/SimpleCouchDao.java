/*
 * Copyright 2005 - 2009 by KNURT Systeme (http://www.knurt.de)
 *
 * Licensed under the Creative Commons License Attribution-NonCommercial 3.0 Unported;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://creativecommons.org/licenses/by-nc/3.0/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.knurt.heinzelmann.db.couch;

import java.util.ArrayList;
import java.util.List;

import org.jcouchdb.db.Database;
import org.jcouchdb.db.Response;
import org.jcouchdb.document.ValueAndDocumentRow;
import org.jcouchdb.document.ViewAndDocumentsResult;

/**
 * a couch dao based on jcouchdb
 * @author Daniel Oltmanns
 * @since 1.00-SNAPSHOT (08/27/2010)
 * @version 1.00-SNAPSHOT (08/27/2010)
 */
public class SimpleCouchDao implements CouchDao {
	private Database database;

	public void setDatabase(Database database) {
		this.database = database;
	}

	public boolean insert(Object document) {
		boolean result = true;
		try {
			this.database.createDocument(document);
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public <R> List<R> getAll(String uri, Class<R> resultClass) {
		List<R> result = new ArrayList<R>();
		Class<R> valueClass = resultClass;
		Class<R> documentClass = resultClass;
		ViewAndDocumentsResult<R, R> value = this.database.query(uri, valueClass, documentClass, null, null, null);
		for (ValueAndDocumentRow<R, R> row : value.getRows()) {
			result.add(row.getDocument());
		}
		return result;
	}

	public Response getResponse(String uri) {
		return this.database.getServer().get("/" + this.database.getName() + "/" + uri);
	}

	@Override
	public String getNextUuid() {
		return this.database.getServer().getUUIDs(1).get(0);
	}

	@Override
	public <R> R getOne(String docId, Class<R> resultClass) {
		return this.database.getDocument(resultClass, docId);
	}

	@Override
	public Response put(String body) {
		return this.database.getServer().put("/" + this.database.getName() + "/" + this.getNextUuid(), body);
	}

	@Override
	public void update(Object document) {
		this.database.updateDocument(document);
	}
	/** construct SimpleCouchDao */
	public SimpleCouchDao(String dbHost, int dbPort, String dbName) {
		this.database = new Database(dbHost, dbPort, dbName);
	}
	public SimpleCouchDao(String dbName) {
		this("127.0.0.1", 5984, dbName);
	}

	@Override
	public Database getDatabase() {
		return this.database;
	}
}
