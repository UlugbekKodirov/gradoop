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

import org.gradoop.model.impl.operators.summarization.Summarization;
import org.gradoop.model.impl.operators.summarization.SummarizationGroupCombine;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class LogicalGraphSummarizeGroupCombineTest extends
  LogicalGraphSummarizeTestBase {

  public LogicalGraphSummarizeGroupCombineTest(TestExecutionMode mode) {
    super(mode);
  }

  @Override
  public Summarization<DefaultVertexData, DefaultEdgeData, DefaultGraphData>
  getSummarizationImpl(
    String vertexGroupingKey, boolean useVertexLabel, String edgeGroupingKey,
    boolean useEdgeLabel) {
    return new SummarizationGroupCombine<>(vertexGroupingKey, edgeGroupingKey,
      useVertexLabel, useEdgeLabel);
  }
}