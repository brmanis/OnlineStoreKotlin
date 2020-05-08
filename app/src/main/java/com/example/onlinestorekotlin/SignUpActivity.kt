package com.example.onlinestorekotlin

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        sign_up_btnSignUp.setOnClickListener {
            if(sign_up_edtPassword.text.toString().
                equals(sign_up_edtConfirmPW.text.toString())){
                //Registration process
                val signUpURL="http://10.0.2.2/ANDROID_APP/join_new_user.php?email="+
                        sign_up_edtEmail.text.toString() +
                        "&username=" +sign_up_edtUsername.text.toString()+
                        "&password="+sign_up_edtPassword.text.toString()
                val requestQ = Volley.newRequestQueue(this@SignUpActivity)
                val stringRequest = StringRequest(Request.Method.GET, signUpURL, Response.Listener {
                    response->
                    if(response.equals("A user with the same email address already exists")){
                        var dialogBuilder = AlertDialog.Builder(this)
                        dialogBuilder.setTitle("Alert")
                        dialogBuilder.setMessage(response)
                        dialogBuilder.create().show()
                    }else{
                        var dialogBuilder = AlertDialog.Builder(this)
                        dialogBuilder.setTitle("Message")
                        dialogBuilder.setMessage(response)
                        dialogBuilder.create().show()
                    }
                },Response.ErrorListener {
                    error->
                    var dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Alert")
                    dialogBuilder.setMessage(error.message)
                    dialogBuilder.create().show()
                })

                requestQ.add(stringRequest)




            }else{
                var dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Alert")
                dialogBuilder.setMessage("Password Mismatch")
                dialogBuilder.create().show()
            }
        }

    }
}
