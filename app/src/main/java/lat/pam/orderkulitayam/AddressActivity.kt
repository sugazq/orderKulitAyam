package lat.pam.orderkulitayam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        val edtNama = findViewById<EditText>(R.id.edtNama)
        val edtAlamat = findViewById<EditText>(R.id.edtAlamat)
        val edtPatokan = findViewById<EditText>(R.id.edtPatokan)
        val btnKirim = findViewById<Button>(R.id.btnOrderKirim)

        btnKirim.setOnClickListener {
            val nama = edtNama.text.toString()
            val intent = Intent(this, ConfirmationActivity::class.java)
            intent.putExtra("nama", nama)
            startActivity(intent)
        }
    }
}
