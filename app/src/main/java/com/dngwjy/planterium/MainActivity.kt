package com.dngwjy.planterium

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {
val dataBase:FirebaseDatabase= FirebaseDatabase.getInstance()
    val dataRef : DatabaseReference = dataBase.reference
    val controlling :DatabaseReference=dataRef.child("controlling").child("auto")
    val pompa:DatabaseReference=dataRef.child("controller").child("pompa")
    val pakan:DatabaseReference=dataRef.child("controller").child("pakan")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        controlling.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Log.d("error","failed ${p0.message}")
            }

            override fun onDataChange(p0: DataSnapshot) {
                val value = p0.getValue(Boolean::class.java)
                Log.d("auto", "Value is: $value")
                if (value != null) {
                    setSwitch(value)
                }
            }
        })
        pakan.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val value = p0.getValue(Boolean::class.java)
                Log.d("pakan", "Value is: $value")
            }
        })

        pompa.addValueEventListener( object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {


            }

            override fun onDataChange(p0: DataSnapshot) {
                val value=p0.getValue(Boolean::class.java)
                Log.d("pompa","value is $value")

            }

        })

      switch1.setOnCheckedChangeListener{
          buttonView, isChecked ->
          if(isChecked){
          controlling.setValue(true)
          }else{
              controlling.setValue(false)
          }

      }
        button.setOnClickListener {
            pakan.setValue(true)
        }
        button2.setOnClickListener {
            pompa.setValue(true)
        }


    }
    fun setSwitch(state:Boolean){
        when(state){
            true-> switch1.isChecked=state
            else -> switch1.isChecked=state
        }
    }
}