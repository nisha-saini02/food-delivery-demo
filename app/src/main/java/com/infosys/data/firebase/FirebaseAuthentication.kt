package com.infosys.data.firebase

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseAuthentication (private val context: Activity) {
    private val auth: FirebaseAuth
        get() = Firebase.auth

    /**
     * email authentication
     * */
    fun signupWithEmailPassword(email: String, pwd: String, event: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(context) {
            if (it.isSuccessful) {
                event.invoke(true, null)
            } else {
                event.invoke(false, it.exception?.message)
            }
        }
    }

    fun loginWithEmailPassword(email: String, pwd: String, event: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(context) {
            if (it.isSuccessful) {
                event.invoke(true, null)
            } else {
                event.invoke(false, it.exception?.message)
            }
        }
    }

    /**
     * phone authentication
     * */
    /*private lateinit var verificationId: String

    fun signInWithCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //open HOME
                } else {
                    Toast.makeText(
                        context,
                        task.exception?.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    fun sendVerificationCode(number: String) {
        val options =
            PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(number)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(context)
                .setCallbacks(mCallBack())
                .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun mCallBack(): OnVerificationStateChangedCallbacks {
        return object : OnVerificationStateChangedCallbacks() {
            override fun onCodeSent(s: String, forceResendingToken: ForceResendingToken) {
                super.onCodeSent(s, forceResendingToken)
                verificationId = s
            }

            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                val code = phoneAuthCredential.smsCode

                if (code != null) {
                    verifyCode(code)
                }
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun verifyCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        signInWithCredential(credential)
    }*/
}