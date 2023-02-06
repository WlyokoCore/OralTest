package com.example.oraltest;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;


import com.example.oraltest.databinding.FragmentSecondBinding;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    DocReader Dr= new DocReader();
    DocWriter Dw= new DocWriter();
    ArrayList<Integer> S = new ArrayList<Integer>();
    int num= 0;
    String[]  q;
    String[]  a;
    int l ;
    int k ;
    int Rnum[];
    double BrushNum;
    double right=0;
    double d= 0;
    String store = MainActivity.store;




    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public static int[] randomCommon(int min, int max, int n){
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while(count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if(num == result[j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result[count] = num;
                count++;
            }
        }
        return result;
    }


    public void initiate(){
        right=0;
        if(Dw.readFileData("a.txt",getActivity().getApplicationContext())==null){
            System.out.println("isEmpty!");

        }else{
            S=Dw.readFileData("a.txt",getActivity().getApplicationContext());
        }

        //System.out.println("S when ini"+S);
        q =(String[])Dr.readerXml(store,"Ques","Question",getActivity().getApplicationContext()).clone();
        a =(String[])Dr.readerXml(store,"Ques","Answer",getActivity().getApplicationContext()).clone();
        l = q.length;
        k = a.length;
        System.out.println("k="+k);
        System.out.println("l="+l);
        Rnum= randomCommon(0,l,l-1);
        for(int a= 0;a<Rnum.length;a++){
            System.out.println(Rnum[a]);
        }
        BrushNum =0;

        binding.buttonSecond.setVisibility(View.VISIBLE);

        binding.buttonSecond2.setVisibility(View.GONE);

        binding.buttonSecond3.setVisibility(View.GONE);

        binding.buttonSecond4.setVisibility(View.GONE);

        binding.textviewSecond.setVisibility(View.VISIBLE);

        binding.textViewAnswer.setVisibility(View.GONE);


    }


    public void SwitchToAnswer(){

        binding.buttonSecond.setVisibility(View.GONE);

        binding.buttonSecond2.setVisibility(View.VISIBLE);

        binding.buttonSecond3.setVisibility(View.VISIBLE);

        binding.buttonSecond4.setVisibility(View.VISIBLE);

        binding.textviewSecond.setVisibility(View.GONE);

        binding.textViewAnswer.setVisibility(View.VISIBLE);

    }
    public void SwitchToQuestions(){

        binding.buttonSecond.setVisibility(View.VISIBLE);

        binding.buttonSecond2.setVisibility(View.GONE);

        binding.buttonSecond3.setVisibility(View.GONE);

        binding.buttonSecond4.setVisibility(View.GONE);

        binding.textviewSecond.setVisibility(View.VISIBLE);

        binding.textViewAnswer.setVisibility(View.GONE);

    }
    public void SwitchForwardProblem(){

            BrushNum++;
            if(BrushNum>14){
                System.out.println(right+"   "+BrushNum);
                d=right/BrushNum*100;
                DecimalFormat df = new DecimalFormat("0.00");
                if(d<50){
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                            .setTitle("分数")
                            .setMessage("本次分数为\n"+(df.format(d))+"分"+"\n"+"您的口试有不合格的风险")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialogInterface, int i) {

                                    return;
                                }
                            })
                            .create();
                    alertDialog.setCancelable(false);
                    alertDialog.show();
                }else if(d>=50&&d<100){
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                            .setTitle("分数")
                            .setMessage("本次分数为\n"+(df.format(d))+"分")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialogInterface, int i) {

                                    return;
                                }
                            })
                            .create();
                    alertDialog.setCancelable(false);
                    alertDialog.show();
                }else if(d==100){
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                            .setTitle("分数")
                            .setMessage("本次分数为\n"+(df.format(d))+"分"+"您已经无敌辣")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialogInterface, int i) {

                                    return;
                                }
                            })
                            .create();

                    alertDialog.setCancelable(false);
                    alertDialog.show();

                }
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        num++;
        if(num<l-1){
            if(q[Rnum[num]]!=null){
                binding.textviewSecond.setText(q[Rnum[num]]);
            }
            if(a[Rnum[num]]!=null){
                binding.textViewAnswer.setText(a[Rnum[num]]);
            }

        }else{
            num--;
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                    .setTitle("提示")
                    .setMessage("已经是最后一道题了")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialogInterface, int i) {
                            return;
                        }
                    })
                    .create();

            alertDialog.show();
        }

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initiate();
        if(q[Rnum[num]]!=null){
            binding.textviewSecond.setText(q[Rnum[num]]);
        }
        if(a[Rnum[num]]!=null){
            binding.textViewAnswer.setText(a[Rnum[num]]);
        }



        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchToAnswer();
            }
        });
        binding.buttonSecond2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                right++;
                SwitchForwardProblem();
                SwitchToQuestions();
            }
        });
        binding.buttonSecond3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchForwardProblem();
                SwitchToQuestions();
            }

        });
        binding.buttonSecond4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchToQuestions();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}