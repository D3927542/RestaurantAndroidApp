package uk.ac.tees.mad.d3927542.Repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import uk.ac.tees.mad.d3927542.Route
import javax.inject.Inject


class AuthRepo @Inject constructor(private val context: FirebaseAuth) : IAuthRepo {
    override suspend fun registerUser(
        email: String,
        password: String,
        navController: NavController,
    ) {
        context
            .createUserWithEmailAndPassword(email.trim(), password)
            .addOnSuccessListener{
                Toast.makeText(navController.context, "Success", Toast.LENGTH_SHORT).show()
                navController.navigate(Route.Home.name)
            }
            .addOnFailureListener{
                it.message?.let { it1 -> Log.d("Register", it1) }
                Toast.makeText(navController.context, it.message, Toast.LENGTH_SHORT).show()
            }
    }

    override suspend fun loginUser(
        email: String,
        password: String,
        navController: NavController,
    ) {
        context
            .signInWithEmailAndPassword(email.trim(), password)
            .addOnSuccessListener{
                Toast.makeText(navController.context, "Success", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
                navController.navigate(Route.Home.name)
            }
            .addOnFailureListener{
                it.message?.let { it1 -> Log.d("Cannot login", it1) }
            }
    }

    override suspend fun forgotPass(
        email: String,
        ctx: Context,
    ) {
        context.sendPasswordResetEmail(email)
            .addOnSuccessListener{
                Toast.makeText(ctx, "Password reset link has been sent to your email address", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                it.message?.let { it1 -> Log.d("Forgot password", it1) }
                Toast.makeText(ctx, it.message, Toast.LENGTH_SHORT).show()
            }
    }

    override suspend fun logout(navController: NavController) {
        context.signOut()
        if(context.currentUser == null)
        {
            Toast.makeText(navController.context, "Logged Out.", Toast.LENGTH_SHORT).show()
            navController.popBackStack()

            navController.navigate(Route.login.name)
        }
    }

    override fun getCurrentuser(): FirebaseUser? = context.currentUser
}