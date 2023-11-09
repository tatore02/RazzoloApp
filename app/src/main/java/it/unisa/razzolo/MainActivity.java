package it.unisa.razzolo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.unisa.razzolo.model.Word;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        wordAdapter = new WordAdapter(this, R.layout.activity_listview, R.id.listView);
        listView.setAdapter(wordAdapter);

        for (int i=1; i<=16; i++) {
            @SuppressLint("DiscouragedApi")
            int viewId = getResources().getIdentifier("box" + i, "id", getPackageName());
            boxes[i-1] = findViewById(viewId);
        }

    }

    public void onClickRunBtn(View view) {
        int i=0,j=0;
        for(EditText e : boxes){
            char c;
            if(e.getText().length() == 1){
                c = e.getText().charAt(0);
                if(Character.isLetter(c)){
                    matrix[i][j] = c;
                    if((j%4) == 3){
                        i++;
                        j=0;
                    }
                    else j++;
                }
                else{
                    Toast.makeText(this, "Compila correttamente tutte le caselle!", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            else{
                Toast.makeText(this, "Compila correttamente tutte le caselle!", Toast.LENGTH_LONG).show();
                return;
            }
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            final var background = new Background(matrix);
            background.run();
            handler.post(() -> {
                wordAdapter.clear();
                Set<String> foundWords = background.getFoundWords();
                for(String s : foundWords)
                    wordAdapter.add(new Word(s,s.length()));
            });
        });
    }

    private final char[][] matrix = new char[4][4];
    private final EditText[] boxes = new EditText[16];
    private ListView listView;
    private WordAdapter wordAdapter;
}