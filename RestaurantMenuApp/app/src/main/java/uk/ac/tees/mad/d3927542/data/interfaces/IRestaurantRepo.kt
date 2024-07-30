package uk.ac.tees.mad.d3927542.interfaces

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.d3927542.Category
import uk.ac.tees.mad.d3927542.Product

interface IRestaurantRepo {
    suspend fun runMigration()
    suspend fun getProducts(): Flow<List<Product>>
    suspend fun getCategories(): Flow<List<Category>>
}