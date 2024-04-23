package br.edu.ifsuldeminas.mch.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ifsuldeminas.mch.calc";

    private boolean hasDot = false;

    private Button buttonZero, buttonUm, buttonDois, buttonTres, buttonQuatro, buttonCinco, buttonSeis,
            buttonSete, buttonOito, buttonNove;
    private Button buttonDelete, buttonReset, buttonPorcento, buttonDivisao, buttonMult,
            buttonSub, buttonSoma, buttonIgual, buttonVirgula;
    private TextView textViewResultado;
    private TextView textViewUltimaExpressao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();
    }

    private void initialization() {
        buttonZero = findViewById(R.id.buttonZeroID);
        buttonUm = findViewById(R.id.buttonUmID);
        buttonDois = findViewById(R.id.buttonDoisID);
        buttonTres = findViewById(R.id.buttonTresID);
        buttonQuatro = findViewById(R.id.buttonQuatroID);
        buttonCinco = findViewById(R.id.buttonCincoID);
        buttonSeis = findViewById(R.id.buttonSeisID);
        buttonSete = findViewById(R.id.buttonSeteID);
        buttonOito = findViewById(R.id.buttonOitoID);
        buttonNove = findViewById(R.id.buttonNoveID);

        buttonDelete = findViewById(R.id.buttonDeleteID);
        buttonReset = findViewById(R.id.buttonResetID);
        buttonPorcento = findViewById(R.id.buttonPorcentoID);
        buttonDivisao = findViewById(R.id.buttonDivisaoID);
        buttonMult = findViewById(R.id.buttonMultiplicacaoID);
        buttonSub = findViewById(R.id.buttonSubtracaoID);
        buttonSoma = findViewById(R.id.buttonSomaID);
        buttonIgual = findViewById(R.id.buttonIgualID);
        buttonVirgula = findViewById(R.id.buttonVirgulaID);

        textViewResultado = findViewById(R.id.textViewResultadoID);
        textViewUltimaExpressao = findViewById(R.id.textViewUltimaExpressaoID);

        setEvents();
    }

    private void setEvents() {
        buttonZero.setOnClickListener(v -> {
            AddExpression("0", true);
        });
        buttonUm.setOnClickListener(v -> {
            AddExpression("1", true);
        });
        buttonDois.setOnClickListener(v -> {
            AddExpression("2", true);
        });
        buttonTres.setOnClickListener(v -> {
            AddExpression("3", true);
        });
        buttonQuatro.setOnClickListener(v -> {
            AddExpression("4", true);
        });
        buttonCinco.setOnClickListener(v -> {
            AddExpression("5", true);
        });
        buttonSeis.setOnClickListener(v -> {
            AddExpression("6", true);
        });
        buttonSete.setOnClickListener(v -> {
            AddExpression("7", true);
        });
        buttonOito.setOnClickListener(v -> {
            AddExpression("8", true);
        });
        buttonNove.setOnClickListener(v -> {
            AddExpression("9", true);
        });

        buttonVirgula.setOnClickListener(v -> {
            String expression = textViewUltimaExpressao.getText().toString();
            try{
                if(notTwoOperators(expression.charAt(expression.length() - 1)) && !hasDot) {
                    expression += '.';
                    textViewUltimaExpressao.setText(expression);
                    hasDot = false;
                }
            }catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        });

        buttonSoma.setOnClickListener(v -> {
            String expression = textViewUltimaExpressao.getText().toString();
            try{
                if(expression.isEmpty()) {
                    continueExpression('+');
                }

                if(notTwoOperators(expression.charAt(expression.length() - 1))) {
                    expression += '+';
                    textViewUltimaExpressao.setText(expression);
                    hasDot = false;
                } else {
                    char at = expression.charAt(expression.length() - 2);
                    if (!(expression.charAt(expression.length() - 1) == '-' && at == '+' ||
                            expression.charAt(expression.length() - 1) == '-' && at == '/' ||
                            expression.charAt(expression.length() - 1) == '-' && at == '*'
                    )) {
                        expression = expression.substring(0, expression.length() - 1);
                        expression += "+";
                        textViewUltimaExpressao.setText(expression);
                    }
                }
            }catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        });

        buttonSub.setOnClickListener(v -> {
            String expression = textViewUltimaExpressao.getText().toString();
            try{
                if(expression.isEmpty()) {
                    continueExpression('-');
                }

                if (expression.equals("") || (expression.charAt(expression.length() - 1)) != '-' || notTwoOperators(expression.charAt(expression.length() - 1))) {
                    expression += "-";
                    textViewUltimaExpressao.setText(expression);
                    hasDot = false;
                }
            }catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        });

        buttonMult.setOnClickListener(v -> {
            String expression = textViewUltimaExpressao.getText().toString();
            try{
                if(expression.isEmpty()) {
                    continueExpression('*');
                }

                if(notTwoOperators(expression.charAt(expression.length() - 1))) {
                    expression += '*';
                    textViewUltimaExpressao.setText(expression);
                    hasDot = false;
                }else {
                    char at = expression.charAt(expression.length() - 2);
                    if (!(expression.charAt(expression.length() - 1) == '-' && at == '+' ||
                            expression.charAt(expression.length() - 1) == '-' && at == '/' ||
                            expression.charAt(expression.length() - 1) == '-' && at == '*'
                    )) {
                        expression = expression.substring(0, expression.length() - 1);
                        expression += "*";
                        textViewUltimaExpressao.setText(expression);
                    }
                }
            }catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        });

        buttonDivisao.setOnClickListener(v -> {
            String expression = textViewUltimaExpressao.getText().toString();
            try{
                if(expression.isEmpty()) {
                    continueExpression('/');
                }

                if(notTwoOperators(expression.charAt(expression.length() - 1))) {
                    expression += '/';
                    textViewUltimaExpressao.setText(expression);
                    hasDot = false;
                } else {
                    char at = expression.charAt(expression.length() - 2);
                    if (!(expression.charAt(expression.length() - 1) == '-' && at == '+' ||
                            expression.charAt(expression.length() - 1) == '-' && at == '/' ||
                            expression.charAt(expression.length() - 1) == '-' && at == '*'
                    )) {
                        expression = expression.substring(0, expression.length() - 1);
                        expression += "/";
                        textViewUltimaExpressao.setText(expression);
                    }
                }
            }catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        });

        buttonPorcento.setOnClickListener(v -> {
            String expression = textViewUltimaExpressao.getText().toString();
            try{
                if(expression.isEmpty()) {
                    continueExpression('%');
                }

                if(notTwoOperators(expression.charAt(expression.length() - 1))) {
                    expression += '%';
                    textViewUltimaExpressao.setText(expression);
                    hasDot = false;
                }
            }catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        });

        buttonReset.setOnClickListener(v -> {
            textViewUltimaExpressao.setText("");
            textViewResultado.setText("0");
        });

        buttonDelete.setOnClickListener(v -> {
            TextView expression = findViewById(R.id.textViewUltimaExpressaoID);
            String string = textViewUltimaExpressao.getText().toString();

            if(!string.isEmpty()) {
                byte var0 = 0;
                int var1 = string.length() - 1;
                String txtExpression = string.substring(var0, var1);
                expression.setText(txtExpression);
            }
            textViewResultado.setText("");
        });

        buttonIgual.setOnClickListener(v -> {
            Calculable expressionEvaluator = null;
            try {
                String expression = textViewUltimaExpressao.getText().toString();
                expressionEvaluator = new ExpressionBuilder(expression).build();
                double result = expressionEvaluator.calculate();

                textViewUltimaExpressao.setText(expression);
                textViewResultado.setText(Double.toString(result));
            } catch (ArithmeticException e) {
                Log.e(TAG, "Error: Division by zero or invalid expression", e);
                textViewResultado.setText("Error");
            } catch (NumberFormatException e) {
                Log.e(TAG, "Error: Invalid number format", e);
                textViewResultado.setText("Error");
            }catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }
        });
    }

    public void AddExpression(String string, boolean cleanData) {

        if(textViewResultado.getText().equals("")) {
            textViewUltimaExpressao.setText(" ");
        }

        if(cleanData == true) {
            textViewResultado.setText(" ");
            textViewUltimaExpressao.append(string);
        }else {
            textViewUltimaExpressao.append(textViewResultado.getText());
            textViewUltimaExpressao.append(string);
            textViewResultado.setText(" ");
        }
    }

    public boolean notTwoOperators(char lastChar) {
        return lastChar != '+' && lastChar != '-' && lastChar != '/' && lastChar != '*' && lastChar != '%' && lastChar != '.';
    }

    public void continueExpression(char operator) {
        String expression = textViewUltimaExpressao.getText().toString();
        expression += textViewResultado.getText();
        expression += operator;
        textViewUltimaExpressao.setText(expression);
        textViewResultado.setText('0');
        hasDot = false;
    }
}