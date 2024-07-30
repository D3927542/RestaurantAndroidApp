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

class RestaurantRepository(private val dao: IDao, private val context: Context) : IRestaurantRepo {
    override suspend fun getProducts(): Flow<List<Product>> {
        return withContext(IO) {
            dao.getAllProducts()
        }
    }

    override suspend fun getCategories(): Flow<List<Category>> {
        return withContext(IO){
            dao.getAllCategories()
        }
    }

    override suspend fun runMigration() {
        return withContext(IO){
            var data = 1
            async { data = dao.getCount() }.await()
            if(data == 0){
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