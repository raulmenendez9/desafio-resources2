package sv.edu.bitlab.raul

data class Account (val accountName: String?,
                    val accountEmail: String?,
                    val accountPhone: String?,
                    val accountFoundOutBy: String?,
                    val accountImage: String?){
    constructor():this("","","","","")
}