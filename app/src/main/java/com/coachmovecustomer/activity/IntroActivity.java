package com.coachmovecustomer.activity;

import android.content.Intent;
import android.os.Bundle;

import com.coachmovecustomer.R;
import com.coachmovecustomer.utils.Const;
import com.coachmovecustomer.utils.PrefStore;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughActivity;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughCard;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends FancyWalkthroughActivity {
    PrefStore mPrefStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPrefStore = new PrefStore(this);
        FancyWalkthroughCard fancyWalkThroughCard1 =
                new FancyWalkthroughCard("Duração",
                        "O tempo de duração de todas as modalidades é de 50 minutos.",
                        R.drawable.co_logo);
        FancyWalkthroughCard fancyWalkThroughCard2 =
                new FancyWalkthroughCard("Número do cliente",
                        "A quantidade máxima de clientes por aula é de 1 para Nutrição e 6 para atividade física.",
                        R.drawable.co_logo);
        FancyWalkthroughCard fancyWalkThroughCard3 =
                new FancyWalkthroughCard("Contratar Treinador",
                        "É proibido contratar os Coachs por fora do App, os Coachs podem responder judicialmente por isto.",
                        R.drawable.co_logo);
        FancyWalkthroughCard fancyWalkThroughCard4 =
                new FancyWalkthroughCard("Consciência",
                        "Ao clicar aqui você está ciente do seu estado de saúde e se considera apto para as atividades que assim escolher.",
                        R.drawable.co_logo);
        FancyWalkthroughCard fancyWalkThroughCard5 =
                new FancyWalkthroughCard("Regra do Horário de Funcionamento",
                        "Verifique os horários de funcionamento do local de treinamento e caso vá treinar em academias se não serão cobradas taxas, se caso forem cobradas é de responsabilidade do cliente o pagamento.",
                        R.drawable.co_logo);
        FancyWalkthroughCard fancyWalkThroughCard6 =
                new FancyWalkthroughCard("Consulta do plano",
                        "O valor cobrado pela consulta nutricional está incluso 50 minutos presenciais para a anamnese e o envio de um plano alimentar.",
                        R.drawable.co_logo);
FancyWalkthroughCard fancyWalkThroughCard7 =
                new FancyWalkthroughCard("Reagendar",
                        "Se o profissional não aparecer para o treino/dieta, por favor utilize o chat e marque uma nova aula, não tendo necessidade de agendar novamente pelo app. ",
                        R.drawable.co_logo);

        fancyWalkThroughCard1.setBackgroundColor(R.color.white);
        fancyWalkThroughCard1.setIconLayoutParams(300, 300, 0, 0, 0, 0);
        fancyWalkThroughCard2.setBackgroundColor(R.color.white);
        fancyWalkThroughCard2.setIconLayoutParams(300, 300, 0, 0, 0, 0);
        fancyWalkThroughCard3.setBackgroundColor(R.color.white);
        fancyWalkThroughCard3.setIconLayoutParams(300, 300, 0, 0, 0, 0);
        fancyWalkThroughCard4.setBackgroundColor(R.color.white);
        fancyWalkThroughCard4.setIconLayoutParams(300, 300, 0, 0, 0, 0);
        fancyWalkThroughCard5.setBackgroundColor(R.color.white);
        fancyWalkThroughCard5.setIconLayoutParams(300, 300, 0, 0, 0, 0);
        fancyWalkThroughCard6.setBackgroundColor(R.color.white);
        fancyWalkThroughCard6.setIconLayoutParams(300, 300, 0, 0, 0, 0);
        fancyWalkThroughCard7.setBackgroundColor(R.color.white);
        fancyWalkThroughCard7.setIconLayoutParams(300, 300, 0, 0, 0, 0);
        //
        fancyWalkThroughCard1.setTitleTextSize(16f);
        fancyWalkThroughCard1.setDescriptionTextSize(12f);


        List<FancyWalkthroughCard> pages = new ArrayList<>();

        pages.add(fancyWalkThroughCard1);
        pages.add(fancyWalkThroughCard2);
        pages.add(fancyWalkThroughCard3);
        pages.add(fancyWalkThroughCard4);
        pages.add(fancyWalkThroughCard5);
        pages.add(fancyWalkThroughCard6);
        pages.add(fancyWalkThroughCard7);

        for (FancyWalkthroughCard page : pages) {
            page.setTitleColor(R.color.black);
            page.setDescriptionColor(R.color.black);
        }
        setFinishButtonTitle("Iniciar");
        showNavigationControls(true);
        setColorBackground(R.color.colorPrimary);
        //setImageBackground(R.drawable.restaurant);
        setInactiveIndicatorColor(R.color.grey_600);
        setActiveIndicatorColor(R.color.colorPrimary);
        setOnboardPages(pages);
    }

    @Override
    public void onFinishButtonPressed() {
        mPrefStore.saveString(Const.FIRST_TIME_VISIT, "1");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
