package site.tsun.kotlinexamples.common.pdf

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import site.tsun.kotlinexamples.R

val FRAGMENT_PDF_RENDERER_BASIC = "pdf_renderer_basic"

class PdfActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.pdf_container, PdfRendererBasicFragment(), FRAGMENT_PDF_RENDERER_BASIC)
                    .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_info -> {
                AlertDialog.Builder(this)
                        .setMessage(R.string.intro_message)
                        .setPositiveButton(android.R.string.ok, null)
                        .show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}