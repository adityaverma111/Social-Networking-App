package com.example.krishna

class DataClass {
    var companyName: String? = null
    var companyPosition : String? = null
    var courceName: String? = null
    var companyLink: String? = null
    constructor(companyName: String?,companyPosition: String?,  courceName: String?, companyLink: String?){
        this.companyName = companyName
        this.companyPosition = companyPosition
        this.courceName = courceName
        this.companyLink = companyLink
    }
}