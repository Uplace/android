package es.uplace.uplace.domain;

data class Photo(val id: Long, val name: String, val description: String,
                 val photo: ByteArray, val photoContentType: String,
                 val thumbnail: Boolean, val Property: Property)
