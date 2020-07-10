/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditHistoryDeleteEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class AuditHistoryDeleteEventTypeNode extends AuditHistoryUpdateEventTypeNode implements AuditHistoryDeleteEventType {
    public AuditHistoryDeleteEventTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyTypeNode> getUpdatedNodeNode() {
        return getPropertyNode(AuditHistoryDeleteEventType.UPDATED_NODE);
    }

    public CompletableFuture<NodeId> getUpdatedNode() {
        return getProperty(AuditHistoryDeleteEventType.UPDATED_NODE);
    }

    public CompletableFuture<StatusCode> setUpdatedNode(NodeId value) {
        return setProperty(AuditHistoryDeleteEventType.UPDATED_NODE, value);
    }
}
