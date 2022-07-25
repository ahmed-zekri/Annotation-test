import java.lang.reflect.Field

fun Field.getKey(className: Class<out Annotation>): String {

    if (isAnnotationPresent(className)) {
        var key = ""
        try {
            key = Regex("\"[\\w\\d]*\"").find(annotations[0].toString())!!.groups[0]!!.value.replace("\"", "")


        } catch (_: Exception) {


        }
        return key.ifBlank { name }
    }
    return ""


}