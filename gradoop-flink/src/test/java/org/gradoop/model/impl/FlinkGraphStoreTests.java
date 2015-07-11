/*
 * This file is part of Gradoop.
 *
 * Gradoop is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Gradoop is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Gradoop.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.gradoop.model.impl;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.gradoop.model.EPFlinkTest;
import org.gradoop.model.store.EPGraphStore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnitParamsRunner.class)
public class FlinkGraphStoreTests extends EPFlinkTest {

  private EPGraphStore graphStore;

  public FlinkGraphStoreTests() {
    graphStore = createSocialGraph();
  }

  @Test
  @Parameters({"0, 3, 4", "1, 3, 4", "2, 4, 6", "3, 3, 4"})
  public void testGetGraph(long graphID, long expectedVertexCount,
    long expectedEdgeCount) throws Exception {
    EPGraph g = graphStore.getGraph(graphID);

    assertNotNull("graph was null", g);
    assertEquals("vertex set has the wrong size", expectedVertexCount,
      g.getVertices().size());
    assertEquals("edge set has the wrong size", expectedEdgeCount,
      g.getEdges().size());
  }

  @Test
  public void testGetDatabaseGraph() throws Exception {
    EPGraph dbGraph = graphStore.getDatabaseGraph();

    assertNotNull("database graph was null", dbGraph);
    assertEquals("vertex set has the wrong size", 11,
      dbGraph.getVertices().size());
    assertEquals("edge set has the wrong size", 24, dbGraph.getEdges().size());
  }
}