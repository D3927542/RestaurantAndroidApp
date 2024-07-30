package uk.ac.tees.mad.d3927542.network

import androidx.room.Database
import androidx.room.RoomDatabase
import uk.ac.tees.mad.d3927542.Category
import uk.ac.tees.mad.d3927542.Product
import uk.ac.tees.mad.d3927542.dao.IDao

@Database(entities = [Product::class, Category::class], version = 1)
abstract class DbContext: RoomDatabase() {
    abstract val dao: IDao
}