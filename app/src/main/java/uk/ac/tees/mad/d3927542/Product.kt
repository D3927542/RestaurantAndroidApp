package uk.ac.tees.mad.d3927542

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    var name: String? = null,
    var category: String? = null,
    var price: Int? = null,
    var description: String? = null,
    var image: String? = null,
    var isLiked : Boolean = false,
    var isDeleted : Boolean = false,
)

@Entity
data class Category (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    var name: String? = null,
    var description: String? = null,
    var image: String? = null,
    var isDeleted : Boolean = false,
    )

fun getProductList(context: Context) = listOf<Product>(
    Product(name = "Burger", category = "Fast food", price = 60, description = R.string.burger.toString(), image = "https://firebasestorage.googleapis.com/v0/b/restaurantmenuapp-66a54.appspot.com/o/burger.png?alt=media&token=588ee9e7-55e8-4e84-aee4-9f839c8fc0c4"),
    Product(name = "Coffee", category = "Drink", price = 50, description = R.string.coffee.toString(), image = "https://firebasestorage.googleapis.com/v0/b/restaurantmenuapp-66a54.appspot.com/o/coffee.png?alt=media&token=0a0447f1-e474-445c-b3de-ea6767582a3f"),
    Product(name = "Coca Cola", category = "Drink", price = 70, description = R.string.coke.toString(), image =  "https://firebasestorage.googleapis.com/v0/b/restaurantmenuapp-66a54.appspot.com/o/colddrink.png?alt=media&token=d8a5aed7-d099-491c-83c4-7c167bda11e9"),
    Product(name = "Chocolate Ice Cream", category = "Ice Cream", price = 30, description = R.string.ChocolateIceCream.toString(), image =  "https://firebasestorage.googleapis.com/v0/b/restaurantmenuapp-66a54.appspot.com/o/icecream.png?alt=media&token=80c0081c-429c-4a7a-8b94-4228857c4c36"),
    Product(name = "Pizza", category = "Fast food", price = 130, description = R.string.pizza.toString(), image =  "https://firebasestorage.googleapis.com/v0/b/restaurantmenuapp-66a54.appspot.com/o/pizza.png?alt=media&token=d39a1da3-26e2-42ca-b075-6aaa116e5aa1"),
    Product(name = "Sandwich", category = "Fast food", price = 40, description = R.string.sandwich.toString(), image =  "https://firebasestorage.googleapis.com/v0/b/restaurantmenuapp-66a54.appspot.com/o/sandwich.png?alt=media&token=ee472850-1cb9-4d61-bb4a-043893cd5dff"),
    Product(name = "Taco", category = "Fast food", price = 59, description = R.string.taco.toString(), image =  "https://firebasestorage.googleapis.com/v0/b/restaurantmenuapp-66a54.appspot.com/o/taco.png?alt=media&token=51ee48d1-b983-4aaf-aa83-50e3caa9ec79"),
    Product(name = "Veggies", category = "North Indian", price = 100, description = R.string.veggies.toString(), image =  "https://firebasestorage.googleapis.com/v0/b/restaurantmenuapp-66a54.appspot.com/o/veggies.png?alt=media&token=ff6a2a44-f8de-47d5-97f2-37531a029a1e"),
    Product(name = "Dosa", category = "South Indian", price = 60, description = R.string.dosa.toString(), image =  "https://firebasestorage.googleapis.com/v0/b/restaurantmenuapp-66a54.appspot.com/o/dosa.png?alt=media&token=452b8df0-d39c-4d0e-a7dc-72eb176167db"),
)

fun getCategoryList(context: Context) = listOf<Category>(
    Category(name = "Fast food", description = R.string.fastfood.toString(), image =  "https://firebasestorage.googleapis.com/v0/b/restaurantmenuapp-66a54.appspot.com/o/fastfood.png?alt=media&token=af4a3fe4-9fae-490c-85ff-376a9d64fbd7"),
    Category(name = "Drink", description = "", image =  "https://firebasestorage.googleapis.com/v0/b/restaurantmenuapp-66a54.appspot.com/o/colddrink.png?alt=media&token=d8a5aed7-d099-491c-83c4-7c167bda11e9"),
    Category(name = "Ice Cream", description = R.string.ChocolateIceCream.toString(), image =  "https://firebasestorage.googleapis.com/v0/b/restaurantmenuapp-66a54.appspot.com/o/icecream.png?alt=media&token=80c0081c-429c-4a7a-8b94-4228857c4c36"),
    Category(name = "North Indian", description = "", image =  "https://firebasestorage.googleapis.com/v0/b/restaurantmenuapp-66a54.appspot.com/o/northindian.png?alt=media&token=1ce1a050-d2e5-4b60-a6b2-64dd9dba8b28"),
    Category(name = "South Indian", description = "", image =  "https://firebasestorage.googleapis.com/v0/b/restaurantmenuapp-66a54.appspot.com/o/dosa.png?alt=media&token=452b8df0-d39c-4d0e-a7dc-72eb176167db"),
)
