package org.apache.helix.gateway.base.statemodel;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.helix.participant.statemachine.StateModelFactory;

// mock master slave state model factory
public class MockOFModelFactory extends StateModelFactory<MockOFStateModel> {
  private MockTransition _transition;

  public MockOFModelFactory() {
    this(null);
  }

  public MockOFModelFactory(MockTransition transition) {
    _transition = transition;
  }

  public void setTrasition(MockTransition transition) {
    _transition = transition;

    // set existing transition
    for (String resource : getResourceSet()) {
      for (String partition : getPartitionSet(resource)) {
        MockOFStateModel stateModel = getStateModel(resource, partition);
        stateModel.setTransition(transition);
      }
    }
  }

  @Override
  public MockOFStateModel createNewStateModel(String resourceName, String partitionKey) {
    MockOFStateModel model = new MockOFStateModel(_transition);

    return model;
  }
}
