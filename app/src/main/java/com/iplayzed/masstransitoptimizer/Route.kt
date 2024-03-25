package com.iplayzed.masstransitoptimizer

import org.jgrapht.Graph
import org.jgrapht.graph.DefaultWeightedEdge
import java.time.LocalDateTime

data class Route(
    val graph: Graph<Settlement, DefaultWeightedEdge>,
    var hasPet: Boolean = false,
    var hasDiscount: Boolean = false,
    val startTime: LocalDateTime
)
