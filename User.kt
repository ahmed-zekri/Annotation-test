import java.util.*

@JsonSerializable
 class User constructor(firstName:String?,lastName:String?,age:Int) {


    @JsonElement
    private var firstName: String? = null


    @JsonElement()
    private var lastName: String? = null


    @JsonElement(key = "personAge")
    private var age: Int = 0
    init {
        this.firstName=firstName
        this.lastName=lastName
        this.age=age
    }
    private val address: String? = null
    @Init
    private fun initNames() {
        firstName = (firstName!!.substring(0, 1).uppercase(Locale.getDefault())
                + firstName!!.substring(1))
        lastName = (lastName!!.substring(0, 1).uppercase(Locale.getDefault())
                + lastName!!.substring(1))
    } // Standard getters and setters
}