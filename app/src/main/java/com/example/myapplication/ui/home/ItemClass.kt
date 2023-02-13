
package com.example.myapplication.ui.home

import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable


data  class itemClass( val title:String, val imageResource: String,  val price:Double, val category:String) :
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
                val json = JSONObject(String(buffer, Charsets.UTF_8))
                val menuItems = json.getJSONArray("FoodItem")


                for (i in 0 until menuItems.length()) {
                    menuItemList.add(
                        itemClass(
                        menuItems.getJSONObject(i).getString("name"),
                        menuItems.getJSONObject(i).getString("image_url"),
                        menuItems.getJSONObject(i).getDouble("price"),
                        menuItems.getJSONObject(i).getString("category"))
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

