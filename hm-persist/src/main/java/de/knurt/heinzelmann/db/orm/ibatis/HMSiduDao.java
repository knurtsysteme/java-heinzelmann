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
package de.knurt.heinzelmann.db.orm.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import de.knurt.heinzelmann.db.DiuDao;
import de.knurt.heinzelmann.util.query.Identificable;

/**
 * basic orm operations for ibatis mapping.
 * convention over configuration. and:<br />
 * this has many conventions:
 * // NICE statt namenskonvention den echten namespacesupport
 * <ul>
 *  <li>all managed objects must implement {@link Identificable}</li>
 *  <li>all ids of ibatis mapping must named as {@link #getNamespace()).[delete|update|insert|selectAll]</li>
 *  <li>class to manage must be named as the namespace</li>
 * </ul>
 *@see Identificable
 * @param <T> the accessed class of o of the da<b>o</b>
 * @author Daniel Oltmanns
 * @since 0.20090628
 * @version 0.20091104
 */
public abstract class HMSiduDao<T extends Identificable> extends SqlMapClientDaoSupport implements DiuDao<T> {

    protected abstract String getNamespace();

    private String getOrmId(String suffix) {
	return this.getNamespace() + "." + suffix;
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
	return this.getSqlMapClientTemplate().queryForList(this.getOrmId("selectAll"));
    }

    @Override
    public void insert(T object) {
	Integer id = (Integer) this.getSqlMapClientTemplate().insert(this.getOrmId("insert"), object);
	object.setId(id.intValue());
    }

    @Override
    public void update(T object) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(T object) {
	this.getSqlMapClientTemplate().delete(this.getOrmId("delete"), object);
    }
}