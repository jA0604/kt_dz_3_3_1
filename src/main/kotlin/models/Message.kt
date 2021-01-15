package models

data class Message(
    val id: Int = 0,
    val isRead: Boolean = false,
    val content: String = ""
        ) {
}