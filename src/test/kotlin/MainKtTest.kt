import org.junit.Assert.*
import org.junit.Test
import services.ChatService

class MainKtTest {
    private val chatService = ChatService()

    @Test
    fun addMessage() {
        val expected = 2

        chatService.addMessage(idContact = 23, content = "Test text 1")
        chatService.addMessage(idContact = 23, content = "Test text 2")
        chatService.addMessage(idContact = 23, content = "Test text 3")

        chatService.addMessage(idContact = 24, content = "Test text 24-1")
        chatService.addMessage(idContact = 24, content = "Test text 24-2")
        chatService.addMessage(idContact = 24, content = "Test text 24-3")

        val result = chatService.unreadChatCount

        assertEquals(expected, result)
    }

    @Test
    fun readContent() {
        val expected = "Test text 2"

        chatService.addMessage(idContact = 23, content = "Test text 1")
        chatService.addMessage(idContact = 23, content = "Test text 2")
        chatService.addMessage(idContact = 23, content = "Test text 3")

        chatService.addMessage(idContact = 24, content = "Test text 24-1")
        chatService.addMessage(idContact = 24, content = "Test text 24-2")
        chatService.addMessage(idContact = 24, content = "Test text 24-3")

        val listMessages = chatService.getChatById(idContact = 23).getMessages()
        val result = listMessages.filter { it.id == 1 }.get(0).content

        assertEquals(expected, result)
    }

    @Test
    fun delMessage() {
        val expected = 2

        chatService.addMessage(idContact = 23, content = "Test text 1")
        chatService.addMessage(idContact = 23, content = "Test text 2")
        chatService.addMessage(idContact = 23, content = "Test text 3")

        chatService.addMessage(idContact = 24, content = "Test text 24-1")
        chatService.addMessage(idContact = 24, content = "Test text 24-2")
        chatService.addMessage(idContact = 24, content = "Test text 24-3")

        chatService
            .getChatById(idContact = 23)
            .getMessages()
            .filter { it.id == 1 }

        chatService
                   .getChatById(idContact = 24)
                   .deleteMessage(2)
        val result = chatService.getChatById(idContact = 24).getMessages().size

        assertEquals(expected, result)
    }

    @Test
    fun removeChat() {
        val expected = 1

        chatService.addMessage(idContact = 23, content = "Test text 1")
        chatService.addMessage(idContact = 23, content = "Test text 2")
        chatService.addMessage(idContact = 23, content = "Test text 3")

        chatService.addMessage(idContact = 24, content = "Test text 24-1")
        chatService.addMessage(idContact = 24, content = "Test text 24-2")
        chatService.addMessage(idContact = 24, content = "Test text 24-3")

        val id = chatService
            .getChatById(idContact = 23)
            .id

        chatService
            .removeChat(id)


        val result = chatService.getChats().size

        assertEquals(expected, result)
    }
}