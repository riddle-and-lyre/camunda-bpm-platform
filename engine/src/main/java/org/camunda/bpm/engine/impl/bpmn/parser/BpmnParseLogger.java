/* Licensed under the Apache License, Version 2.0 (the "License");
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
package org.camunda.bpm.engine.impl.bpmn.parser;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.impl.ProcessEngineLogger;

/**
 * @author Stefan Hentschel.
 */
public class BpmnParseLogger extends ProcessEngineLogger {

  // LOGGING

  public void parsingElement(String elementType, String elementId) {
    logDebug("001", "Parsing element from type '{}' with id '{}'", elementType, elementId);
  }

  public void ignoringNonExecutableProcess(String elementId) {
    logInfo("002", "Ignoring non-executable process with id '{}'. Set the attribute isExecutable=\"true\" to deploy " +
      "this process.", elementId);
  }

  public void missingIsExecutableAttribute(String elementId) {
    logInfo("003", "Process with id '{}' has no attribute isExecutable. Better set the attribute explicitly, " +
      "especially to be compatible with future engine versions which might change the default behavior.", elementId);
  }

  public void parsingFailure(Throwable cause) {
    logError("004", "Unexpected Exception with message: {} ", cause.getMessage());
  }

  // EXCEPTIONS

  public ProcessEngineException parsingProcessException(Exception cause) {
    return new ProcessEngineException(exceptionMessage("009", "Error while parsing process"), cause);
  }

  public void exceptionWhileGeneratingProcessDiagram(Throwable t) {
    logError(
        "010",
        "Error while generating process diagram, image will not be stored in repository", t);
  }

  public ProcessEngineException messageEventSubscriptionWithSameNameExists(String resourceName, String eventName) {
    throw new ProcessEngineException(exceptionMessage(
        "011",
        "Cannot deploy process definition '{}': there already is a message event subscription for the message with name '{}'.", resourceName, eventName));
  }

  public ProcessEngineException conditionalEventSubscriptionWithSameConditionExists(String resourceName, String expr) {
    throw new ProcessEngineException(exceptionMessage(
        "012",
        "Cannot deploy process definition '{}': there already is a conditional event subscription with the same condition '{}'.", resourceName, expr));
  }

}
