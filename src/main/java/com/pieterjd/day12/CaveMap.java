package com.pieterjd.day12;

import com.google.common.graph.EndpointPair;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import lombok.Getter;

import javax.annotation.CheckForNull;
import java.util.Set;

public class CaveMap {
    @Getter
    MutableValueGraph<Cave, Integer> graph;

    public CaveMap() {
        graph = ValueGraphBuilder.undirected().build();
    }

    @CanIgnoreReturnValue
    public boolean addNode(Cave cave) {
        return graph.addNode(cave);
    }

    @CanIgnoreReturnValue
    @CheckForNull
    public Integer putEdgeValue(Cave cave, Cave n1, Integer integer) {
        return graph.putEdgeValue(cave, n1, integer);
    }

    @CanIgnoreReturnValue
    public boolean removeNode(Cave cave) {
        return graph.removeNode(cave);
    }

    @CanIgnoreReturnValue
    @CheckForNull
    public Integer removeEdge(Cave cave, Cave n1) {
        return graph.removeEdge(cave, n1);
    }

    public Set<Cave> nodes() {
        return graph.nodes();
    }

    public Set<EndpointPair<Cave>> edges() {
        return graph.edges();
    }

    public Set<Cave> predecessors(Cave cave) {
        return graph.predecessors(cave);
    }

    public Set<Cave> successors(Cave cave) {
        return graph.successors(cave);
    }
}
