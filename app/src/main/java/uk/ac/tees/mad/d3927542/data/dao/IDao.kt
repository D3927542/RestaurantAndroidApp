package uk.ac.tees.mad.d3927542.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.d3927542.Category
import uk.ac.tees.mad.d3927542.Product

@Dao
interface IDao {
    @Query("SELECT * FROM Product where isDeleted = 0")
     fun getAllProducts(): Flow<List<Product>>

    @Query("SELECT * FROM Category where isDeleted = 0")
     fun getAllCategories(): Flow<List<Category>>

    @Query("SELECT COUNT(id) FROM Product where isDeleted = 0")
     fun getCount(): Int

    @Insert
     fun addProduct(product: Product)

    @Insert
     fun addCategory(category: Category)


}