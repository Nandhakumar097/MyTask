package com.example.mytask;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ImageActivity extends AppCompatActivity {

    TextView title,description;
    ImageView image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_activity);
        title=findViewById(R.id.title);
        description=findViewById(R.id.description);
        image=findViewById(R.id.image);

        ResponseData data = (ResponseData) Objects.requireNonNull(getIntent().getExtras()).get("DATA");
        title.setText(data.getName());
        description.setText(data.getDescription());
        Picasso.get().load(data.getUrl()).into(image);

    }
}
