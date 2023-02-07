
package com.example.myapplication

import android.content.Context
import android.graphics.BitmapFactory
import android.widget.ImageView
import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable


data  class itemClass( val title:String, val imageResource: String,  val price:Double) :
    Serializable {

    companion object {
        fun getMenuItems(filename: String, context: Context): ArrayList<itemClass> {
            //create ArrayList of Book objects
            val menuItemList = ArrayList<itemClass>()

            try {
                //read json file
                val inputStream = context.assets.open(filename)
                val buffer = ByteArray(inputStream.available())
                inputStream.read(buffer)
                inputStream.close()

                //convert input to JSON
                val json = JSONObject(String(buffer, Charsets.UTF_8))
                val menuItems = json.getJSONArray("FoodItem")

                //extract strings from the JSON objects
                //create new Book objects and add them to the List
                for (i in 0..(menuItems.length() - 1)) {
                   /* val imageName = menuItems.getJSONObject(i).getString("image_url")
                    val imageStream = context.assets.open("imgs/$imageName")
                    val bitmap = BitmapFactory.decodeStream(imageStream)*/
                    menuItemList.add(
                        itemClass(
                        menuItems.getJSONObject(i).getString("name"),
                        menuItems.getJSONObject(i).getString("image_url"),
                       //     bitmap,
                        menuItems.getJSONObject(i).getDouble("price"),)
                    )
                }

            } catch (e: JSONException) {
                e.printStackTrace()
            }

            //return the List of Book objects
            return menuItemList
        }
    }
}
