package bots

import com.vdurmont.emoji.EmojiParser
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendDocument
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import java.util.ArrayList

class WCCBot : TelegramLongPollingBot() {



    override fun getBotUsername(): String {
        //return bot username
        // If bot username is @HelloKotlinBot, it must return
        return "PatyBot"
    }

    override fun getBotToken(): String {
        // Return bot token from BotFather
        return System.getenv("TELEGRAM_KEY")
    }

    override fun onUpdateReceived(update: Update?) {
        if (update?.hasMessage() == true) {
            val sendMessage = SendMessage()
            sendMessage.chatId = update.message.chatId.toString()
            sendMessage.replyMarkup = ReplyKeyboardRemove(true)
            val idChat = update.message.chatId.toString()
            val nameSender = update.message.from.firstName
            val command = update.message.text


            if (command != null) {
                when {
                    command.startsWith("/opcoes") -> {
                        val opcoes = listOf(
                            "/start - Seleciona essa opção para voltar ao menu inicial \uD83C\uDF77",
                            "/site - Acesse nosso site",
                            "/cursos - On-line e Presencial sobre o universo dos vinhos \uD83C\uDF77",
                            "/blog - Acesse o nosso blog!",
                            "/kits -Alguns de nossos kits de vinho \uD83C\uDF77",
                            "/miro -Nosso template do negócio"


                        )
                        sendMessage.text = opcoes.joinToString(separator = "\n")
                        execute(sendMessage)
                    }

                    command.startsWith("/start") -> {
                        sendMessage.text = "Olá $nameSender!\uD83D\uDE00 \n Bem vindo ao mundo de degustação de vinhos!\uD83C\uDF77\uD83C\uDF77 \nSelecione  /opcoes para iniciar o atendimento!"
                        execute(sendMessage)
                    }
                    command.startsWith("/site") -> {
                        sendMessage.text = " Acesse nosso site : https://rb.gy/2ns6pa"
                        execute(sendMessage)
                    }
                    command.startsWith("/cursos") -> {
                        sendMessage.text = " Nós temos 02 opções de cursos : On-line e Presencial"


                        val keyboardMarkup = ReplyKeyboardMarkup()
                        val keyboard: MutableList<KeyboardRow> = ArrayList()
                        val row = KeyboardRow()
                        row.add("On-line")
                        row.add("Presencial")


                        keyboard.add(row)
                        keyboardMarkup.keyboard = keyboard
                        sendMessage.replyMarkup = keyboardMarkup
                        execute(sendMessage)
                    }
                    command.startsWith("On-line") -> {
                        sendMessage.text =
                            "Parabéns pela sua decisão !!! \uD83D\uDE00 \n" +
                                    "Você terá uma imersão completa no universo do vinho podendo assistir as aulas a hora que você quiser com suporte de dúvidas e no final do curso você terá direito a um brinde de uma garrafa tinto ou espumante e no final do curso seu certificado.\n" +
                                    "Acesse o site e faça a sua inscrição: https://rb.gy/2ns6pa "
                        execute(sendMessage)

                    }
                    command.startsWith("Presencial") -> {
                        sendMessage.text = "Parabéns pela sua decisão! \uD83C\uDF77 \n " +
                                "Aqui você terá uma imersão completa com nossos melhores sommeliers no universo do vinho com direito a degustações e um brinde de uma garrafa tinto ou espumante e no final do curso o seu certificado." +
                                " Acesse o site e faça a sua inscrição: https://shorturl.at/fqCP7"
                        execute(sendMessage)
                    }

                    command.startsWith("/blog") -> {
                        val ind = (0..2).shuffled().first()
                        val blog = listOf(
                            "https://clubedevinhos.com/artigos/10-melhores-vinhos-brancos-para-servir-ceia-natal",
                            "https://rb.gy/2ns6pa",
                            "https://mybest-brazil.com.br/17923"

                        )
                        sendMessage.text = blog[ind]
                        execute(sendMessage)
                    }
                    command.startsWith("/kits") -> {
                        val ind = (0..1).shuffled().first()

                        val images = listOf(
                            "https://res.cloudinary.com/dac23jyqr/image/upload/v1637346164/5C25A45E-D4B3-4D2C-87E6-503D89E590B6_vcq37n.jpg",
                            "https://res.cloudinary.com/dac23jyqr/image/upload/v1637346164/CFE8E83B-5917-4F1E-96A0-F882C93C8EFD_j9mrmn.jpg"
                        )

                        val sendDocument = SendDocument().apply {
                            this.chatId = idChat
                            this.document = InputFile().setMedia(images[ind])
                            this.parseMode = "MarkdownV2"
                        }
                        execute(sendDocument)
                    }
                    command.startsWith("/miro") -> {
                        sendMessage.text = "Acesse o nosso site de template de negócio:\n https://miro.com/app/board/o9J_ljzwlC4=/ "
                        execute(sendMessage)
                    }

                }
            }
        }
    }
}

