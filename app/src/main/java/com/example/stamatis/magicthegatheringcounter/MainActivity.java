package com.example.stamatis.magicthegatheringcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Keys
    private static final String PLAYER_A_HP = "player_A_HP";
    private static final String PLAYER_B_HP = "player_B_HP";
    private static final String PLAYER_A_POISON = "player_A_Poison";
    private static final String PLAYER_B_POISON = "player_B_Poison";

    //Finals
    final int MAX_HEALTH = 25; // n >= 0
    final int MAX_POISON = 10; // 0 <= n <= 10

    // Variables
    private int playerAHealth = MAX_HEALTH;
    private int playerBHealth = MAX_HEALTH;
    private int playerAPoison = 0;
    private int playerBPoison = 0;

    // UI Components
    private TextView playerAViewHP;
    private TextView playerBViewHP;
    private TextView playerAViewPoison;
    private TextView playerBViewPoison;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitViews();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(PLAYER_A_HP, playerAHealth);
        savedInstanceState.putInt(PLAYER_B_HP, playerBHealth);
        savedInstanceState.putInt(PLAYER_A_POISON, playerAPoison);
        savedInstanceState.putInt(PLAYER_B_POISON, playerBPoison);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        playerAHealth = savedInstanceState.getInt(PLAYER_A_HP);
        playerBHealth = savedInstanceState.getInt(PLAYER_B_HP);
        playerAPoison = savedInstanceState.getInt(PLAYER_A_POISON);
        playerBPoison = savedInstanceState.getInt(PLAYER_B_POISON);
        UpdateUI();
    }

    // Initialization
    private void InitViews(){
        // Text Views
        playerAViewHP = (TextView) findViewById(R.id.txtPlayerAHealth);
        playerBViewHP = (TextView) findViewById(R.id.txtPlayerBHealth);
        playerAViewPoison = (TextView) findViewById(R.id.txtPlayerAPoison);
        playerBViewPoison = (TextView) findViewById(R.id.txtPlayerBPoison);
    }

    // Health management methods
    public void SubtractHealth(View button){
        if (button.getId() == R.id.btnHPMinusA && playerAHealth > 0)
            playerAHealth--;
        else if(button.getId() == R.id.btnHPMinusB && playerBHealth > 0)
            playerBHealth--;

        UpdateHealth();
    }

    public void AddHealth(View button){
        if (button.getId() == R.id.btnHPPlusA)
            playerAHealth++;
        else if(button.getId() == R.id.btnHPPlusB)
            playerBHealth++;

        UpdateHealth();
    }

    // Poison management methods
    public void AddPoison(View button){
        if (button.getId() == R.id.btnPoisonPlusA && playerAPoison < MAX_POISON)
            playerAPoison++;
        else if (button.getId() == R.id.btnPoisonPlusB && playerBPoison < MAX_POISON)
            playerBPoison++;

        UpdatePoison();
    }

    public void SubtractPoison(View button){
        if (button.getId() == R.id.btnPoisonMinusA && playerAPoison > 0)
            playerAPoison--;
        else if (button.getId() == R.id.btnPoisonMinusB && playerBPoison > 0)
            playerBPoison--;

        UpdatePoison();
    }

    public void ResetValues(View view){
        playerAHealth = MAX_HEALTH;
        playerBHealth = MAX_HEALTH;
        playerAPoison = 0;
        playerBPoison = 0;
        UpdateHealth();
        UpdatePoison();
    }

    // UI Methods
    private void UpdateUI(){
        UpdateHealth();
        UpdatePoison();
    }

    private void UpdateHealth(){
        playerAViewHP.setText(String.valueOf(playerAHealth));
        playerBViewHP.setText(String.valueOf(playerBHealth));
    }

    private void UpdatePoison(){
        playerAViewPoison.setText(String.valueOf(playerAPoison));
        playerBViewPoison.setText(String.valueOf(playerBPoison));
    }
}
