package com.argo.ai.assessment

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class JsonCombinerTest {
	private val colors = """{
	  "colors": [
	    {
	      "name": "logan",
	      "color": "red"
	    },
	    {
	      "name": "leo",
	      "color": "green"
	    },
	    {
	      "name": "sara",
	      "color": ""
	    },
	    {
	      "name": "kylo",
	      "color": "brown"
	    }
	  ]
	}"""

	private val states = """{
	  "state": [
	    {
	      "name": "logan",
	      "state": "pa"
	    },
	    {
	      "name": "leo",
	      "state": "ca"
	    },
	    {
	      "name": "sara",
	      "state": "ny"
	    },
	    {
	      "name": "kylo",
	      "state": ""
	    },
	    {
	      "name": "joe",
	      "state": "fl"
	    }
	  ]
	}"""

	private val expectedCombinedLogan = Combined("logan", "red", "pa")
	private val expectedCombinedLeo = Combined("leo", "green", "ca")
	private val expectedCombinedSara = Combined("sara", "", "ny")
	private val expectedCombinedKylo = Combined("kylo", "brown", "")

	@Test
	fun testSomeLibraryMethod() {
		val combined: List<Combined> = JsonCombiner
				.combinedAdapter
				.fromJson(JsonCombiner.mergeColorAndState(colors, states))
				.orEmpty()

		assertThat(combined).containsExactlyInAnyOrder(
				expectedCombinedLogan,
				expectedCombinedLeo,
				expectedCombinedSara,
				expectedCombinedKylo)
	}
}

