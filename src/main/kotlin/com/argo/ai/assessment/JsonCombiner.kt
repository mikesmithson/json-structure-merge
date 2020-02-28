package com.argo.ai.assessment

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types.newParameterizedType
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


object JsonCombiner {
	private val moshi = Moshi.Builder()
			.add(KotlinJsonAdapterFactory())
			.build()

	private val colorsAdapter: JsonAdapter<Colors> = moshi.adapter(newParameterizedType(Colors::class.java, MutableList::class.java))
	private val statesAdapter: JsonAdapter<States> = moshi.adapter(newParameterizedType(States::class.java, MutableList::class.java))
	val combinedAdapter: JsonAdapter<List<Combined>> = moshi.adapter(newParameterizedType(MutableList::class.java, Combined::class.java))

	fun mergeColorAndState(colorsJson: String, stateJson: String): String {

		val nameToColors = colorsAdapter.fromJson(colorsJson)?.colors
				?.asSequence()
				.orEmpty()
				.map { it.name to it.color }
				.toMap()
		val states = statesAdapter.fromJson(stateJson)?.state.orEmpty()

		return combinedAdapter.toJson(
				states.map { Combined(it.name, nameToColors[it.name].orEmpty(), it.state) })
	}
}

@JsonClass(generateAdapter = true)
data class Color(val name: String, val color: String)

data class Colors(val colors: List<Color>)

@JsonClass(generateAdapter = true)
data class State(val name: String, val state: String)

data class States(val state: List<State>)

@JsonClass(generateAdapter = true)
data class Combined(val name: String, val color: String, val state: String)

