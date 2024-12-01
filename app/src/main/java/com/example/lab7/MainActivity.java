package com.example.lab7;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText etFruitId;
    private Button btnGetFruit;
    private TextView tvFruitInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ view
        etFruitId = findViewById(R.id.etFruitId);
        btnGetFruit = findViewById(R.id.btnGetFruit);
        tvFruitInfo = findViewById(R.id.tvFruitInfo);

        // Tạo Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.104:3003/") // Thay bằng IP của máy chủ
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServices apiServices = retrofit.create(ApiServices.class);

        // Xử lý sự kiện khi nhấn nút
        btnGetFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etFruitId.getText().toString().trim();

                if (id.isEmpty()) {
                    tvFruitInfo.setText("Please enter a valid Fruit ID.");
                    return;
                }

                // Gọi API để lấy chi tiết trái cây
                Call<Fruit> call = apiServices.getFruitById(id);
                call.enqueue(new Callback<Fruit>() {
                    @Override
                    public void onResponse(Call<Fruit> call, Response<Fruit> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Fruit fruit = response.body();
                            tvFruitInfo.setText("Name: " + fruit.getName() +
                                    "\nColor: " + fruit.getColor() +
                                    "\nPrice: $" + fruit.getPrice());
                        } else {
                            tvFruitInfo.setText("Fruit not found!");
                        }
                    }

                    @Override
                    public void onFailure(Call<Fruit> call, Throwable t) {
                        tvFruitInfo.setText("Error: " + t.getMessage());
                        Log.e("API", "Failure: " + t.getMessage());
                    }
                });
            }
        });
    }
}

