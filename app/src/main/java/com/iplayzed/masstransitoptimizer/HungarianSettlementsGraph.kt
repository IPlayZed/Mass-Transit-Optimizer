package com.iplayzed.masstransitoptimizer

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.jgrapht.graph.DefaultDirectedWeightedGraph
import org.jgrapht.graph.DefaultWeightedEdge
import java.io.File
import kotlin.random.Random

class HungarianSettlementsGraph(private val context: Context) {
    private val graph = DefaultDirectedWeightedGraph<Settlement,
            DefaultWeightedEdge>(DefaultWeightedEdge::class.java)

    fun openFileChooser(activity: Activity, requestCode: Int) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*"
        }
    }
    fun readSettlementsFromCsv(dataFile: File) {
        csvReader().readAllWithHeader(dataFile).forEach { row ->
            val vertex = Settlement(row["name"]!!, row["county"]!!)
            graph.addVertex(vertex)
        }
    }

    fun createConnections() {
        val vertices = graph.vertexSet();
        val random = Random(System.currentTimeMillis());

        for (vertex in vertices) {
            val otherVertices = vertices.filter { it != vertex }
            for (otherVertex in otherVertices) {
                if (random.nextBoolean()) {
                    val newEdge = graph.addEdge(vertex, otherVertex)
                    if (newEdge != null) {
                        graph.setEdgeWeight(newEdge, random.nextDouble(0.0,250.0))
                    }
                }
            }
        }
    }
}