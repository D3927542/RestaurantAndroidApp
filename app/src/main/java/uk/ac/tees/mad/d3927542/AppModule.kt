package uk.ac.tees.mad.d3927542

import android.content.Context
import androidx.room.Room
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uk.ac.tees.mad.d3927542.Repository.AuthRepo
import uk.ac.tees.mad.d3927542.Repository.IAuthRepo
import uk.ac.tees.mad.d3927542.dao.IDao
import uk.ac.tees.mad.d3927542.interfaces.IRestaurantRepo
import uk.ac.tees.mad.d3927542.network.DbContext
import uk.ac.tees.mad.d3927542.repository.RestaurantRepository

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideDb(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(
        context,
        DbContext::class.java,
        "RestaurantMenu.db"
    ).allowMainThreadQueries().build()

    @Provides
    fun provideUserDao(
        ctx : DbContext
    ) = ctx.dao

    @Provides
    fun provideRepository(
        dao : IDao,
        @ApplicationContext context: Context
    ): IRestaurantRepo = RestaurantRepository(
        dao, context
    )

    @Provides
    fun provideFirebase(
        @ApplicationContext
        context: Context
    ) = FirebaseApp.initializeApp(context)

    @Provides
    fun provideFirebaseAuthInstance() =
        FirebaseAuth
            .getInstance()

    @Provides
    fun provideFirebaseRepo(
        context: FirebaseAuth
    ): IAuthRepo = AuthRepo(context)

}