
fun main(){

    val user =  User("Ahmed", "Zekri",25);
    val serializer =  ObjectToJsonConverter();
    val  jsonString = serializer.convertToJson(user);
print(jsonString)
}