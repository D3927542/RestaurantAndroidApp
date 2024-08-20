package uk.ac.tees.mad.d3927542.repository

import android.content.Context
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import uk.ac.tees.mad.d3927542.Category
import uk.ac.tees.mad.d3927542.Product
import uk.ac.tees.mad.d3927542.dao.IDao
import uk.ac.tees.mad.d3927542.getCategoryList
import uk.ac.tees.mad.d3927542.getProductList
import uk.ac.tees.mad.d3927542.interfaces.IRestaurantRepo
import java.util.Properties
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class RestaurantRepository(private val dao: IDao, private val context: Context) : IRestaurantRepo {
    override suspend fun getProducts(): Flow<List<Product>> {
        return withContext(IO) {
            dao.getAllProducts()
        }
    }

    override suspend fun getCategories(): Flow<List<Category>> {
        return withContext(IO) {
            dao.getAllCategories()
        }
    }

    override suspend fun sendEmail(email: String?) {
        withContext(IO) {
            val properties = Properties().apply {
                put("mail.smtp.host", "smtp.gmail.com")
                put("mail.smtp.port", "465")
                put("mail.smtp.auth", "true")
                put("mail.smtp.ssl.enable", "true")
            }

            val session = Session.getInstance(properties, object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(
                        "sandhyasanju0189@gmail.com",
                        "tjas ebmb xwta ajrp"
                    )
                }
            })

            try {
                val message = MimeMessage(session).apply {
                    setFrom(InternetAddress("sandhyasanju0189@gmail.com"))
                    setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(email)
                    )
                    subject = "Your Restaurant Menu App order"
                    setText(
                        "Hello,\n" +
                                "Thank you for your order. We’ll send a confirmation when your order ships. " +
                                "If you would like to view the status of your order or make any changes to it, please visit Your Orders on our Mobile App."
                    )
                }

                Transport.send(message)
            } catch (e: MessagingException) {
                e.printStackTrace()
            }
        }
    }

    override suspend fun runMigration() {
        return withContext(IO) {
            var data = 1
            async { data = dao.getCount() }.await()
            if (data == 0) {
                getProductList(context).forEach {
                    dao.addProduct(it)
                }

                getCategoryList(context).forEach {
                    dao.addCategory(it)
                }
            }
        }
    }
}