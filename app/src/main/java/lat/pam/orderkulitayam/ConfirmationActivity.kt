package lat.pam.orderkulitayam

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        val txtHalo = findViewById<TextView>(R.id.txtHalo)
        val txtTerimaKasih = findViewById<TextView>(R.id.txtTerimaKasih)
        val btnKirim = findViewById<Button>(R.id.btnKirimFinal)

        val nama = intent.getStringExtra("nama") ?: "Guyss"

        txtHalo.text = "Halo $nama,"
        txtTerimaKasih.text =
            "Terima kasih sudah memesan dan mohon tunggu di lokasi sampai kurir mengirimkan pesanan anda."

        btnKirim.setOnClickListener {
            finishAffinity()
        }
    }
}
