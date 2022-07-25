import java.util.stream.Collectors


class ObjectToJsonConverter {

    fun convertToJson(`object`: Any): String {
        return try {
            checkIfSerializable(`object`)
            initializeObject(`object`)
            getJsonString(`object`)
        } catch (e: Exception) {
            throw JsonSerializationException(e.message)
        }
    }

    private fun getJsonString(`object`: Any): String {
        val clazz: Class<*> = `object`.javaClass
        val jsonElementsMap: MutableMap<String, Any> = HashMap()
        for (field in clazz.declaredFields) {
            field.isAccessible = true
            if (field.isAnnotationPresent(JsonElement::class.java)) {
                jsonElementsMap[field.getKey(JsonElement::class.java)] = field[`object`]
            }
        }

        val jsonString = jsonElementsMap.entries
            .stream()
            .map { (key, value): Map.Entry<String, Any> ->
                ("\"" + key + "\":\""
                        + value + "\"")
            }
            .collect(Collectors.joining(","))
        return "{$jsonString}"
    }

    private fun initializeObject(`object`: Any) {
        val clazz: Class<*> = `object`.javaClass
        for (method in clazz.declaredMethods) {
            if (method.isAnnotationPresent(Init::class.java)) {
                method.isAccessible = true
                method.invoke(`object`)
            }
        }
    }

    private fun checkIfSerializable(`object`: Any) {
        val clazz: Class<*> = `object`.javaClass
        if (!clazz.isAnnotationPresent(JsonSerializable::class.java)) {
            throw JsonSerializationException(
                "The class "
                        + clazz.simpleName
                        + " is not annotated with JsonSerializable"
            )
        }
    }
}

class JsonSerializationException(message: String?) : Throwable() {

}



