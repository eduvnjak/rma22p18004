package ba.etf.rma22.projekat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import ba.etf.rma22.projekat.viewmodel.UpisIstrazivanjeViewModel

class UpisIstrazivanje: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        // da li je potrebno
        finish()
    }
}