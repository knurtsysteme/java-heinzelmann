/*
 * Copyright 2009-2010 by KNURT Systeme (http://www.knurt.de)
 *
 * Licensed under the Creative Commons License Attribution-NonCommercial-No Derivative Works 3.0 Unported;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://creativecommons.org/licenses/by-nc-nd/3.0/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.knurt.javaheinzelmann;

import static org.junit.Assert.assertEquals;

import org.apache.velocity.VelocityContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.knurt.heinzelmann.util.velocity.VelocityStringRenderUtil;

public class VelocityStringRenderUtilTest {

	public VelocityStringRenderUtilTest() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Test
	public void construction() {
		String template = "abc $foo cba";
		VelocityContext context = new VelocityContext();
		context.put("foo", "bar");
		assertEquals("abc bar cba", VelocityStringRenderUtil.getInstance().getRendered(template, context));

		template = "abc $foo cba";
		context = new VelocityContext();
		context.put("foo", "rab");
		assertEquals("abc rab cba", VelocityStringRenderUtil.getInstance().getRendered(template, context));
	}

}
