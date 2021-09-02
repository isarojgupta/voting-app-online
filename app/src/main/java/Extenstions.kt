import android.app.Activity
import android.widget.Toast

object Extenstions {
    fun Activity.toast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}